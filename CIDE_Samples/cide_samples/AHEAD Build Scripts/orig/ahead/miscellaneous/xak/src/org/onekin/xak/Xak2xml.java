/**
 * 
 */
package org.onekin.xak;

import java.io.*;


import org.w3c.dom.*;
import org.onekin.util.ParserUtils;
import org.onekin.xak.namespace.XakContext;

/**
 * @author struji
 * @author ibanf
 * 
 */
public class Xak2xml
{
	final public static String	XSL_XAK2XML	= "xak2xml.xsl";
	
	public Xak2xml ()
	{
		this.init () ;
	}
	
	public void init ()
	{
		try
		{
			ParserUtils.bindPrefixToNamespaceURI(XakContext.NS_PREFIX, XakContext.NS_URI);

		} 
		catch (Exception ex)
		{
			System.err.println(ex.getMessage() + "\n");
			ex.printStackTrace(System.err);
		}
	}
	
	public void dirXak2xml (String sDir)
	{
		File fDir = new File (sDir) ;
		
		if (!fDir.exists()) return ;
		
		String sFiles[] = fDir.list() ;
		try
		{			
			for (int i = 0; i < sFiles.length; i++)
			{
				String sIFile = sFiles[i] ;
				this.fileXak2xml(sIFile) ;
			}
		} 
		catch (Exception ex)
		{
			System.out.println(ex.getMessage() + "\n");
			ex.printStackTrace(System.out);
		}
	}
	
	public void fileXak2xml (File fSrc)
	{
		this.fileXak2xml(fSrc.getAbsoluteFile()) ;
	}
	
	public void fileXak2xml (String sSrc)
	{
		try
		{	
			if (sSrc.contains(".xak"))
			{
				File fSrc = new File(sSrc);
				String sTgtExt = this.getExtension (fSrc) ;
				String sTgt = sSrc.replace(".xak", "." + sTgtExt) ;
				
				System.out.println("XAK2XML: [xak: " + sSrc + "] -> [xml: " + sTgt + "]") ;
				
				Document docXml = ParserUtils.parseFile(fSrc);
				this.removeXakAnnotation(docXml);
				ParserUtils.doc2prettyprint(sTgt, docXml);
			}				
		} 
		catch (Exception ex)
		{
			System.out.println(ex.getMessage() + "\n");
			ex.printStackTrace(System.out);
		}
	}

	private void removeXakAnnotation(Document docXml){
	  //eliminar los atributos @xak:type, @xak:artifact, @xak:interface
      Element elDoc = docXml.getDocumentElement();
	  elDoc.removeAttribute("xak:type");
	  elDoc.removeAttribute("xak:artifact");
	  elDoc.removeAttribute("xak:interface");
	  //eliminar los atributos @xak:module
	  NodeList nlwXakModule = ParserUtils.selectNodes(docXml,"//*[@xak:module]");
//	  System.out.println(nlwXakModule.getLength());
	  for(int i=0;i<nlwXakModule.getLength();i++){
		Element el = (Element) nlwXakModule.item(i);
		el.removeAttribute("xak:module");
	  }
	  //eliminar el namespace xak
	  elDoc.removeAttribute("xmlns:xak");
	  //eliminar los elementos xak:cdata
/*
	  NodeList nlXakCdata = ParserUtils.selectNodes(nodo,"//xak:cdata");
	  for(int i=0;i<nlXakCdata.getLength();i++){
		Element elCdata = (Element) nlXakCdata.item(i);
		Node nCdata = elCdata.getFirstChild();
		Node parent = elCdata.getParentNode();
		parent.replaceChild(nCdata,elCdata);
	  }
*/
	}
	


	
	/**
	 * @param fFile File
	 * @return
	 */
	private String getExtension(File fFile)
	{		
	try
		{
    		Document doc = ParserUtils.parseFile(fFile);
			// System.out.println("Definition:\n" + ParserUtils.nodeToString(doc)) ;
			String sType = doc.getDocumentElement().getAttribute("xak:type") ;
			// System.out.println("Type: " + sType) ;
			if (sType==null || sType.equalsIgnoreCase("")) return "xml" ;
			else return sType ;
		} 
    catch (Exception ex)
    {
			System.err.println(ex.getClass() + " - Parsing error: " + ex.getLocalizedMessage());
			ex.printStackTrace();
		} 
    return "xml" ;
  }
	
	/**
	 * @param args
	 */
/*
	public static void main(String[] args)
	{
		Xak2xml xh = new Xak2xml () ;
		// xh.xak2xml(args[0]) ;
		String sDir = "E:/Users/Ikerkuntza/Proyectos/OnekinProjects/04XmlRefinements/04.SourceCode/06.Tools/ahead-xak-src/example" ;
		String sFilename = "result.xak";
		String sPath = sDir + "/" + sFilename;
		xh.fileXak2xml(sPath);
		//xh.dirXak2xml(sDir) ;		
	}
	*/
}
