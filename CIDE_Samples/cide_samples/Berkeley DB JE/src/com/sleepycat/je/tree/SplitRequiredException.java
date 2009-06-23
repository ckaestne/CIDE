/*-
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2002-2006
 *      Sleepycat Software.  All rights reserved.
 *
 * $Id: SplitRequiredException.java,v 1.1 2006/05/06 09:00:16 ckaestne Exp $
 */

package com.sleepycat.je.tree;

/**
 * Indicates that we need to return to the top of the tree in order to
 * do a forced splitting pass.
 */
class SplitRequiredException extends Exception {
    public SplitRequiredException(){
    }
}
