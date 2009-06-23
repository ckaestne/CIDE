/*-
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2002-2006
 *      Sleepycat Software.  All rights reserved.
 *
 * $Id: TreeStats.java,v 1.1 2006/05/06 09:00:17 ckaestne Exp $
 */

package com.sleepycat.je.tree;

/**
 * A class that provides interesting stats about a particular tree.
 */
public final class TreeStats {

    /**
     * Number of times the root was split.
     */
    public int nRootSplits = 0;
}
