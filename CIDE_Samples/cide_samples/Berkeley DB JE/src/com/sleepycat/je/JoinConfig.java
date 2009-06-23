/*-
 * See the file LICENSE for redistribution information.
 *
 * Copyright (c) 2002-2006
 *      Sleepycat Software.  All rights reserved.
 *
 * $Id: JoinConfig.java,v 1.1 2006/05/06 08:59:26 ckaestne Exp $
 */

package com.sleepycat.je;

/**
 * Javadoc for this public class is generated
 * via the doc templates in the doc_src directory.
 */
public class JoinConfig implements Cloneable {

    /*
     * For internal use, to allow null as a valid value for
     * the config parameter.
     */
    static JoinConfig DEFAULT = new JoinConfig();

    private boolean noSort;

    /**
     * Javadoc for this public method is generated via
     * the doc templates in the doc_src directory.
     */
    public JoinConfig() {
    }

    /**
     * Javadoc for this public method is generated via
     * the doc templates in the doc_src directory.
     */
    public void setNoSort(boolean noSort) {
        this.noSort = noSort;
    } 

    /**
     * Javadoc for this public method is generated via
     * the doc templates in the doc_src directory.
     */
    public boolean getNoSort() {
        return noSort;
    } 

    /**
     * Used by SecondaryDatabase to create a copy of the application
     * supplied configuration. Done this way to provide non-public cloning.
     */
    JoinConfig cloneConfig() {
        try {
            return (JoinConfig) super.clone();
        } catch (CloneNotSupportedException willNeverOccur) {
            return null;
        }
    }
}
