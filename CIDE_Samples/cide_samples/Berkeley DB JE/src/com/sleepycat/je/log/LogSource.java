/*-
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2002-2006
 *      Sleepycat Software.  All rights reserved.
 *
 * $Id: LogSource.java,v 1.1 2006/05/06 08:59:57 ckaestne Exp $
 */

package com.sleepycat.je.log;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.sleepycat.je.DatabaseException;

/**
 * A class that implements LogSource can return portions of the log.
 */
interface LogSource {

    /**
     * We're done with this log source.
     */
    void release() throws DatabaseException;

    /** 
     * Fill the destination byte array with bytes. The offset indicates the
     * absolute log file position.
     */
    ByteBuffer getBytes(long fileOffset) throws IOException;

    /** 
     * Fill the destination byte array with the requested number of bytes.  The
     * offset indicates the absolute position in the log file.
     */
    ByteBuffer getBytes(long fileOffset, int numBytes)
        throws IOException;
}
