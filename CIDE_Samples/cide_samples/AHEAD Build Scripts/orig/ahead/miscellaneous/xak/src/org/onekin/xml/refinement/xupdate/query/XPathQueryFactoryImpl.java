package org.onekin.xml.refinement.xupdate.query;

import org.infozone.tools.xml.queries.XPathQuery;
import org.infozone.tools.xml.queries.XPathQueryFactory;
import org.infozone.tools.xml.queries.XPathQueryConfigurationException;

/**
 * @author Felipe
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public final class XPathQueryFactoryImpl extends XPathQueryFactory {

      public XPathQueryFactoryImpl() {
      }


      public XPathQuery newXPathQuery() throws XPathQueryConfigurationException {
          return new XPathQueryImpl();
      }

}
