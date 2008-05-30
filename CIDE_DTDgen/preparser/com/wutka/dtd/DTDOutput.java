package com.wutka.dtd;

import java.io.*;

/** Defines the method used for writing DTD information to a PrintWriter
 *
 * @author Mark Wutka
 * @version $Revision: 1.16 $ $Date: 2002/07/19 01:20:11 $ by $Author: wutka $
 */
public interface DTDOutput
{
    public void write(PrintWriter out) throws IOException;
}
