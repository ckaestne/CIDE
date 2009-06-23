/*-
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2002-2006
 *      Sleepycat Software.  All rights reserved.
 *
 * $Id: TrackingInfo.java,v 1.1 2006/05/06 09:00:11 ckaestne Exp $
 */

package com.sleepycat.je.tree;

import com.sleepycat.je.utilint.DbLsn;

/**
 * Tracking info packages some tree tracing info.
 */
public class TrackingInfo {
    private long lsn;
    private long nodeId;
    
    public TrackingInfo(long lsn, long nodeId) {
        this.lsn = lsn;
        this.nodeId = nodeId;
    }

    public String toString() {
        return "lsn=" + DbLsn.getNoFormatString(lsn) +
            " node=" + nodeId;
    }
}
