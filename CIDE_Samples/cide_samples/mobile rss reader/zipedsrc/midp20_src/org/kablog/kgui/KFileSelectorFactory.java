/* Copyright (c) 2001-2005 Todd C. Stellanova, rawthought
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The  above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE. 
 * 
 * 
 */

/* From kablog-j2me. */

// Expand to define DJSR75 define
//#define DNOJSR75
// Expand to define logging define
//#define DNOLOGGING

//#ifdef DJSR75
//@package org.kablog.kgui;
//@
//@import javax.microedition.midlet.MIDlet;
//@
//#ifdef DLOGGING
//@import net.sf.jlogmicro.util.logging.Logger;
//@import net.sf.jlogmicro.util.logging.LogManager;
//@import net.sf.jlogmicro.util.logging.Level;
//#endif
//@
//@public class KFileSelectorFactory {
	//#ifdef DLOGGING
//@    private Logger logger = Logger.getLogger("KFileSelectorImpl");
//@    private boolean fineLoggable = logger.isLoggable(Level.FINE);
//@    private boolean finerLoggable = logger.isLoggable(Level.FINER);
//@    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif
//@
//@	public static KFileSelector getInstance(MIDlet midlet, String title,
//@											String defaultDir, String iconDir) {
//@		try {
//@			KFileSelector newInst = new KFileSelectorImpl();
//@			((KFileSelectorImpl)newInst).init(midlet, title, defaultDir,
//@					iconDir);
//@			return (newInst);
//@		} catch (Throwable t) {
			//#ifdef DLOGGING
//@			Logger logger = Logger.getLogger("KFileSelectorImpl");
//@			logger.severe("KFileSelectorFactory getInstance ", t);
			//#endif
//@			/** Error while executing constructor */
//@			System.out.println("KFileSelectorFactory getInstance " + t.getMessage());
//@			t.printStackTrace();
//@			return null;
//@		}
//@
//@	}
//@
//@	public static KFileSelector getInstance() {
//@		
//@		try {
//@			KFileSelector newInst = new KFileSelectorImpl();
//@			((KFileSelectorImpl)newInst).init();
//@			return (newInst);
//@		} catch (Throwable t) {
			//#ifdef DLOGGING
//@			Logger logger = Logger.getLogger("KFileSelectorImpl");
//@			logger.severe("KFileSelectorFactory getInstance ", t);
			//#endif
//@			/** Error while executing constructor */
//@			System.out.println("KFileSelectorFactory getInstance " + t.getMessage());
//@			t.printStackTrace();
//@			return null;
//@		}
//@
//@	}
//@}
//#endif
