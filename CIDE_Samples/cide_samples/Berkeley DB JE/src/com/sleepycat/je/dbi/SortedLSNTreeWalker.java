/*-
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2002-2006
 *      Sleepycat Software.  All rights reserved.
 *
 * $Id: SortedLSNTreeWalker.java,v 1.1 2006/05/06 09:00:26 ckaestne Exp $
 */

package com.sleepycat.je.dbi;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.cleaner.OffsetList;
import com.sleepycat.je.log.LogEntryType;
import com.sleepycat.je.tree.BIN;
import com.sleepycat.je.tree.DBIN;
import com.sleepycat.je.tree.DIN;
import com.sleepycat.je.tree.IN;
import com.sleepycat.je.tree.Node;
import com.sleepycat.je.utilint.DbLsn;

/**
 * Class to walk over the tree using sorted LSN fetching for parts of the tree
 * that are not in memory.  Returns LSNs for each node in the tree
 * <b>except</b> the root IN, but in an arbitrary order (i.e. not key
 * order). The caller is responsible for getting the root IN's LSN explicitly.
 * <p>
 * A calllback function specified in the constructor is executed for each LSN
 * found.
 * <p>
 * The walker works in two phases.  The first phase is to gather and return all
 * the INs from the INList that match the database being iterated over.  For
 * each IN, all of the LSNs of the children are passed to the callback method
 * (processLSN).  If the child was not in memory, it is added to a list of LSNs
 * to read.  When all of the in-memory INs have been processed, the list of
 * LSNs that were harvested are sorted.
 * <p>
 * Then for each of the sorted LSNs, the target is fetched, the type
 * determined, and the LSN and type passed to the callback method for
 * processing.  LSNs of the children of those nodes are retrieved and the
 * process repeated until there are no more nodes to be fetched for this
 * database's tree.
 */
public class SortedLSNTreeWalker {

    /*
     * The interface for calling back to the user with each LSN.
     */
    public interface TreeNodeProcessor {
	void processLSN(long childLSN, LogEntryType childType);
    }

    protected DatabaseImpl dbImpl;
    private EnvironmentImpl envImpl;

    /* 
     * Save the root LSN at construction time, because the root may be
     * nulled out before walk() executes.
     */
    private long rootLsn; 

    /* Indicates whether db has allowDuplicates set. */
    private boolean dups;

    /*
     * Set removeINsFromINList to true if INs read from the INList should be
     * removed.
     */
    private boolean removeINsFromINList;

    /*
     * Whether to call DatabaseImpl.finishedINListHarvest().
     */
    private boolean setDbState;

    /*
     * An array (and index) of LSNs that were accumulated in a previous pass
     * over the tree.
     */
    private long[] currentLSNs;
    private int currentLSNIdx = 0;

    /*
     * A list of LSNs being accumulated.  Once they have been accumulated, they
     * will be moved to currentLSNs, fetched, and returned to the user.
     *
     * Store this in two OffsetLists, one for the file number portion of the
     * LSN and the other for the file offset portion since OffsetLists can only
     * store ints, not longs.
     */
    private OffsetList accumulatedLSNFileNumbers;
    private OffsetList accumulatedLSNFileOffsets;

    private TreeNodeProcessor callback;

    /*
     * If true, then walker should also accumulate LNs and pass them in sorted
     * order to the TreeNodeProcessor callback method.
     */
    protected boolean accumulateLNs = false;

    /* 
     * @param rootLsn is passed in addition to the dbImpl, because the
     * root may be nulled out on the dbImpl before walk() is called.
     */
    public SortedLSNTreeWalker(DatabaseImpl dbImpl,
			       boolean removeINsFromINList,
			       boolean setDbState,
                               long rootLsn,
			       TreeNodeProcessor callback)
	throws DatabaseException {

	/* This iterator is used on both deleted and undeleted databases. */
	this.dbImpl = dbImpl;
	this.envImpl = dbImpl.getDbEnvironment();
	if (envImpl == null) {
	    throw new DatabaseException
		("environmentImpl is null for target db " +
                 dbImpl.getDebugName());
	}
	this.dups = dbImpl.getSortedDuplicates();

	this.removeINsFromINList = removeINsFromINList;
	this.setDbState = setDbState;
        this.rootLsn = rootLsn;
	this.callback = callback;
	currentLSNs = new long[0];
	currentLSNIdx = 0;
    }

    /*
     * Return true if some INs were found on the INList for this db.
     */
    private boolean extractINsForDb(INList inList)
	throws DatabaseException {

	boolean foundSome = false;

        /* Search the INList and put all qualifying INs into another list. */
        Set foundSet = new HashSet();
        long memoryChange = 0;
        MemoryBudget mb = envImpl.getMemoryBudget();
	inList.latchMajor();
	try {
            /* consolidate the INList first. */
            inList.latchMinorAndDumpAddedINs();

	    Iterator iter = inList.iterator();
	    while (iter.hasNext()) {
		IN thisIN = (IN) iter.next();
		if (thisIN.getDatabase() == dbImpl) {
		    foundSome = true;
		    if (removeINsFromINList) {
			iter.remove();
                        memoryChange += (thisIN.getAccumulatedDelta() -
                                         thisIN.getInMemorySize());
                        thisIN.setInListResident(false);
		    }
                    foundSet.add(thisIN);
		}
	    }
        } catch (DatabaseException e) {
            /* Update the memory budget with any changes. */
            mb.updateTreeMemoryUsage(memoryChange);
            throw e;
	} finally {
	    inList.releaseMajorLatch();
	}

        /* 
         * Do processing outside of INList latch in order to reduce lockout
         * of checkpointing and eviction. 
         */
        if (foundSome) {
            Iterator iter = foundSet.iterator();
            while (iter.hasNext()) {
                IN thisIN = (IN) iter.next();
                accumulateLSNs(thisIN);
            }
        }

        /* 
         * Update the memory in one fell swoop after releasing all references
         * to INs in order to reduce contention on memory budget contention
         * latch. Wait until all references to INs are released.
         */
        foundSet = null;
        mb.updateTreeMemoryUsage(memoryChange);

	return foundSome;
    }

    /**
     * Find all non-resident nodes, and execute the callback.  The root IN's
     * LSN is not returned to the callback.
     */
    public void walk()
	throws DatabaseException {

	walkInternal();
    }

    protected void walkInternal()
	throws DatabaseException {

	INList inList = envImpl.getInMemoryINs();
	IN root = null;
	if (!extractINsForDb(inList)) {
	    if (rootLsn == DbLsn.NULL_LSN) {
		return;
	    }

	    root = getRootIN(rootLsn);
	    accumulateLSNs(root);
	    releaseRootIN(root);
        }

        if (setDbState) {
            dbImpl.finishedINListHarvest();
        }

	while (true) {
	    maybeGetMoreINs();
	    if (currentLSNs != null &&
		currentLSNIdx < currentLSNs.length) {
                fetchAndProcessLSN(currentLSNs[currentLSNIdx++]);
	    } else {
		break;
	    }
	}
    }

    private void maybeGetMoreINs() {

	if ((currentLSNs != null &&
	     currentLSNIdx >= currentLSNs.length)) {

	    if (accumulatedLSNFileNumbers == null ||
		accumulatedLSNFileNumbers.size() == 0) {

		/* Nothing left to process. Mark completion of second phase. */
		currentLSNs = null;
		currentLSNIdx = Integer.MAX_VALUE;
		return;
	    }

	    long[] tempFileNumbers = accumulatedLSNFileNumbers.toArray();
	    long[] tempFileOffsets = accumulatedLSNFileOffsets.toArray();
	    int nLSNs = tempFileNumbers.length;
	    currentLSNIdx = 0;
	    currentLSNs = new long[nLSNs];
	    for (int i = 0; i < nLSNs; i++) {
		currentLSNs[i] =
		    DbLsn.makeLsn(tempFileNumbers[i], tempFileOffsets[i]);
	    }

	    Arrays.sort(currentLSNs);
	    accumulatedLSNFileNumbers = null;
	    accumulatedLSNFileOffsets = null;
	}
    }

    private void accumulateLSNs(IN in)
	throws DatabaseException {

	boolean accumulate = true;

        /* 
         * If this is the bottom of the tree and we're not accumluating LNs, *
         * then there's no need to accumulate any more LSNs, but we still need
         * to callback with each of them.
         */
	if (!accumulateLNs) {
	    if ((!dups && (in instanceof BIN)) ||
		(in instanceof DBIN)) {

		/*
		 * No need to accumulate the LSNs of a non-dup BIN or a DBIN.
		 */
		accumulate = false;
	    }
	}

	/* 
	 * Process all children, but only accumulate LSNs for children that are
	 * not in memory.
	 */
	for (int i = 0; i < in.getNEntries(); i++) {

	    if (in.isEntryPendingDeleted(i) ||
		in.isEntryKnownDeleted(i)) {
		continue;
	    }

	    long lsn = in.getLsn(i);
	    Node node = in.getTarget(i);
	    if (accumulate && (node == null)) {
                if (accumulatedLSNFileNumbers == null) {
                    accumulatedLSNFileNumbers = new OffsetList();
                    accumulatedLSNFileOffsets = new OffsetList();
                }

                accumulatedLSNFileNumbers.add(DbLsn.getFileNumber(lsn),
                                              false);
                accumulatedLSNFileOffsets.add(DbLsn.getFileOffset(lsn),
                                              false);

		/*
		 * If we're maintaining a map from LSN to owning IN/index,
		 * then update the map here.
		 */
		addToLsnINMap(new Long(lsn), in, i);
                /* callback.processLSN is called when we fetch this LSN. */
            } else {

                /* 
                 * If the child is resident, use that log type, else we can
                 * assume it's a LN.
                 */
                callback.processLSN(lsn,
                                    (node == null) ? LogEntryType.LOG_LN :
                                    node.getLogType());
            }
	}

        /* Handle the DupCountLN for a DIN root. */
        if (in instanceof DIN) {
            if (in.isRoot()) {
                DIN din = (DIN) in;
                callback.processLSN(din.getDupCountLNRef().getLsn(),
                                    LogEntryType.LOG_DUPCOUNTLN);
            }
        }
    }

    /*
     * Fetch the node at 'lsn' and callback to let the invoker process it.  If
     * it is an IN, accumulate LSNs for it.
     */
    private void fetchAndProcessLSN(long lsn)
	throws DatabaseException {

	Node node = fetchLSN(lsn);
	if (node != null) {
	    callback.processLSN(lsn, node.getLogType());

	    if (node instanceof IN) {
		accumulateLSNs((IN) node);
	    }
	}
    }

    /**
     * The default behavior fetches the rootIN from the log, but classes
     * extending this may fetch the root from the tree.
     */
    protected IN getRootIN(long rootLsn)
	throws DatabaseException {

	return (IN) envImpl.getLogManager().get(rootLsn);
    }

    protected void releaseRootIN(IN ignore)
	throws DatabaseException {

	/* 
	 * There's no root IN latch in a vanilla Sorted LSN Tree Walk because
	 * we just fetched the root from the log.
	 */
    }

    protected void addToLsnINMap(Long lsn, IN in, int index) {
    }

    protected Node fetchLSN(long lsn)
	throws DatabaseException {

	return (Node) envImpl.getLogManager().get(lsn);
    }
}
