/*-
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2002-2006
 *      Sleepycat Software.  All rights reserved.
 *
 * $Id: LatchException.java,v 1.1 2006/05/06 09:00:34 ckaestne Exp $
 */

package com.sleepycat.je.latch;

import com.sleepycat.je.DatabaseException;

/**
 * The root of latch related exceptions.
 */

public class LatchException extends DatabaseException {

    public LatchException() {
	super();
    }

    public LatchException(String message) {
	super(message);
    }
}
