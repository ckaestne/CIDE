/*-
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2002-2006
 *      Sleepycat Software.  All rights reserved.
 *
 * $Id: InternalException.java,v 1.1 2006/05/06 09:00:30 ckaestne Exp $
 */

package com.sleepycat.je.utilint;

import com.sleepycat.je.DatabaseException;

/**
 * Some internal inconsistency exception.
 */
public class InternalException extends DatabaseException {
    
    public InternalException() {
	super();
    }

    public InternalException(String message) {
	super(message);
    }
}
