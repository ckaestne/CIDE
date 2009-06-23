/*-
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2002-2006
 *      Sleepycat Software.  All rights reserved.
 *
 * $Id: PutMode.java,v 1.1 2006/05/06 09:00:23 ckaestne Exp $
 */

package com.sleepycat.je.dbi;

/**
 * Internal class used to distinguish which variety of putXXX() that
 * Cursor.putInternal() should use.
 */
public class PutMode {
    public static final PutMode NODUP =       new PutMode();
    public static final PutMode CURRENT =     new PutMode();
    public static final PutMode OVERWRITE =   new PutMode();
    public static final PutMode NOOVERWRITE = new PutMode();
}
