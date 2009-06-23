/*-
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2002-2006
 *      Sleepycat Software.  All rights reserved.
 *
 * $Id: NodeLogEntry.java,v 1.1 2006/05/06 09:01:38 ckaestne Exp $
 */

package com.sleepycat.je.log.entry;

/**
 * Implemented by all LogEntry classes that provide a node ID.
 */
public interface NodeLogEntry extends LogEntry {

    /**
     * Returns the node ID.  This value is redundant with the main item (Node)
     * of a log entry.  It is returned separately so that it can be obtained
     * when the entry's main item (Node) is not loaded.  Partial loading is an
     * optimization for recovery.
     */
    long getNodeId();
}
