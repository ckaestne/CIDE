/**
 * 
 */
package org.onekin.xak.refinement.ext;

import java.io.File;
import java.util.Vector;

//import org.apache.log4j.Logger;
import org.onekin.util.ParserUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Salva
 *
 */
public class MimeConfReader
{
  //Define a static logger variable so that it references the
  // Logger instance named "MimeConfReader".
  // static Logger logger = Logger.getLogger(MimeConfReader.class);
  
	private String _sMimeFile ;
	
	public MimeConfReader (String sMimeFile)
	{
		_sMimeFile = sMimeFile ;		
	}
	
	public String[] getMimeTypes ()
	{
		 Document dMime = null ;
		 String[] sExtensions = null ;
     try 
     {	      
         // Parse input file
         File fMimeFile = new File (_sMimeFile) ;
         dMime = ParserUtils.parseFile(fMimeFile);
         this.log("File: " + _sMimeFile) ;
         this.log("Content:\n" + ParserUtils.nodeToString(dMime.getDocumentElement())) ;
         // Read mime-types
         NodeList nlMimes = ParserUtils.selectNodes(dMime.getDocumentElement(), "//mime-type") ;
         Vector vMimes = new Vector () ;
         for (int i = 0; i < nlMimes.getLength(); i++)
				{
        	 Node nd = nlMimes.item(i) ;
					 String sMimeType = nd.getFirstChild().getNodeValue() ;
					 System.out.println("Mime [" + String.valueOf(i) + "]: " + sMimeType);
					 vMimes.add(sMimeType) ;
				}
         sExtensions = new String [vMimes.size()] ;
         vMimes.copyInto(sExtensions) ;         
     }
     catch (Exception ex) 
     {
    	 this.log(ex.getMessage()+"\n");
       ex.printStackTrace(System.out);
     }
     return sExtensions ; // new String[]{".xml",".jsp",".tld",".rdf",".xsl",".xslt",".xcd",".xsd",".xcf",".MF",".scxml"} ; 
	}
	public void log (String sMessage)
	{
		System.out.println(sMessage);
		/* if (logger==null) System.out.println(sMessage);
		else logger.debug(sMessage) ; */
	}
/*	public static void main(String[] args) 
	{
		MimeConfReader mimeConf = new MimeConfReader ("E:/Salva/CodeProjects/Eclipse/3.1/AHEAD_XRefine/conf/mimes.xml") ;
		String[] sExt = mimeConf.getMimeTypes() ;
		System.out.println("Files: " + sExt.toString());
		return ;
	} */

}
