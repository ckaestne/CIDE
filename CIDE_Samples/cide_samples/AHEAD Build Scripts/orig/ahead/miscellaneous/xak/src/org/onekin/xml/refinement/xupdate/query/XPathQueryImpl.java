/*
 * Created on Feb 8, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.onekin.xml.refinement.xupdate.query;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeFilter;

import org.infozone.tools.xml.queries.XObject;
import org.infozone.tools.xml.queries.XPathQuery;

import javax.xml.xpath.*;

/* modificar por los de JAXP */
//import org.apache.xalan.xpath.xml.PrefixResolver;
//import org.apache.xalan.xpath.xml.PrefixResolverDefault;
//import org.apache.xalan.xpath.xml.XMLParserLiaisonDefault;

/**
 * @author Felipe
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class XPathQueryImpl implements XPathQuery{

  private String              qstring;

  private Node                rootNode;

  private Node                namespace;

  private NodeFilter          filter;
  
  private boolean		hasChanged = true;

  private XPath               xpathprocessor;
  
  private XPathExpression			xpathExpression;

//  private PrefixResolver      prefixResolver;
  
  
  public XPathQueryImpl() {

	    //get an XPath processor
	    try
	    {
	      XPathFactory xpfactory = XPathFactory.newInstance();
	      xpathprocessor = xpfactory.newXPath();
	    }
	    catch (Exception e)
	    {
	      System.out.println("Something goes wrong while XPathQueryImpl") ;
	    }			    
	    
  }
      

  public void setQString( String qstring ) throws Exception {
      this.qstring = qstring;
      this.hasChanged = true;
  }


  public void setNamespace( Node namespace ) throws Exception {
      this.namespace = namespace;
      this.hasChanged = true;
  }


  public void setNodeFilter( NodeFilter filter ) throws Exception {
      this.filter = filter;
      this.hasChanged = true;
  }
  
  
  protected void prepare() throws Exception {
      // Create an object to resolve namespace prefixes.
      // XPath namespaces are resolved from the input root node's
      // document element if it is a root node, or else the current root node.
//      prefixResolver = namespace != null ? new PrefixResolverDefault (namespace):
  //                                         new PrefixResolverDefault (rootNode);

      // parse the specified Query-String and build an Parse-Tree
    //  parser.initXPath (xpath, qstring, prefixResolver);
      xpathExpression = xpathprocessor.compile(qstring);

      hasChanged = false;
  }
  
  
  /**
   * Execute the xpath.
   *
   * @param rootNode The node from which the query should start or null.
   * @param nameSpace The node that resolves namespace queries or null.
   * @param filter The node filter which is to apply while querying or null.
   * @return The XObject insulating the query result.
   */
  public XObject execute( Node rootNode ) throws Exception {
      if (rootNode.getNodeType() == Node.DOCUMENT_NODE) {
          rootNode = ((Document)rootNode).getDocumentElement ();
      }
      
      this.rootNode = rootNode;
      prepare();
      
      // execute the XPath query on the specified root node
  	  NodeList result = (NodeList) xpathExpression.evaluate(rootNode, XPathConstants.NODESET);
      return new XObjectImpl (result);
  }
}
