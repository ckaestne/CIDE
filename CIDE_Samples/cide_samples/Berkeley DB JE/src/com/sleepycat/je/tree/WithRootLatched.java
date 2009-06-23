/*-
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2002-2006
 *      Sleepycat Software.  All rights reserved.
 *
 * $Id: WithRootLatched.java,v 1.1 2006/05/06 09:00:18 ckaestne Exp $
 */

package com.sleepycat.je.tree;

import com.sleepycat.je.DatabaseException;

public interface WithRootLatched {
    
    /**
     * doWork is called while the tree's root latch is held.
     */
    public IN doWork(ChildReference root)
	throws DatabaseException;
}
