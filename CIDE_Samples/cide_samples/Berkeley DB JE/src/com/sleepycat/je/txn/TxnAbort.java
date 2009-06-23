/*-
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2002-2006
 *      Sleepycat Software.  All rights reserved.
 *
 * $Id: TxnAbort.java,v 1.1 2006/05/06 08:59:03 ckaestne Exp $
 */

package com.sleepycat.je.txn;

import com.sleepycat.je.log.LogEntryType;

/**
 * This class writes out a transaction commit or transaction end record.
 */
public class TxnAbort extends TxnEnd {
    public TxnAbort(long id, long lastLsn) {
        super(id, lastLsn);
    }
    
    /**
     * For constructing from the log.
     */
    public TxnAbort() {
    }

    /*
     * Log support
     */

    /**
     * @see TxnEnd#getLogType
     */
    public LogEntryType getLogType() {
        return LogEntryType.LOG_TXN_ABORT;
    }

    protected String getTagName() {
        return "TxnAbort";
    }
}
