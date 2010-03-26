/**
 * 
 */
package org.onekin.xak;

import java.io.File;
import java.util.Enumeration;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilderFactory;

import org.onekin.util.ParserUtils;
import org.w3c.dom.Document;

import org.w3c.dom.NodeList;

/**
 * @author struji
 *
 */
public class Xik
{
	private File _fFileXik ;
	private Document _docXik ;
	
	private String	_sXikId;
	private String	_sXikSchema;

	public Xik ()
	{
		_fFileXik = null ;	
		_docXik = null ;
		
		_sXikId = null ;
		_sXikSchema = null ;
	}
	
	public Xik (String sFile)
	{
		this.load(sFile) ;
	}
	
	/**
	 * This method loads a file representation 
	 * @param sFile String
	 */
	public void load (String sFile)
	{
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setValidating(false);    
    try
		{
    	_fFileXik  = new File(sFile);
			_docXik = factory.newDocumentBuilder().parse(_fFileXik);
			this.log("XIK Doc:\n" + ParserUtils.nodeToString(_docXik)) ;
			
			_sXikId = _docXik.getDocumentElement().getAttribute("xik:id") ;			
			_sXikSchema = _docXik.getDocumentElement().getAttribute("xik:schema") ;			
			
			this.log ("XIK LOAD  [id: " + _sXikId + "]\t[schema:" + _sXikSchema + "]") ;	   
		} 
    catch (Exception e)
    {
    	this.log (e.getClass() + ": " + e.getMessage()) ;
    }
	}
	/**
	 * Get modules
	 */
	public Enumeration getModules()
	{
		Vector modules = new Vector () ;
		String sXPath = "//*[name()='xik:module']";
		NodeList nl = ParserUtils.selectNodes(_docXik, sXPath) ;
		for (int i = 0; i < nl.getLength(); i++)
		{
			String sModuleId = nl.item(i).getAttributes().getNamedItem("xik:id").getNodeValue() ;
			// System.out.println("Selecting: " + ParserUtils.nodeToString(nl.item(i)));
			// System.out.println("sId: " + sModuleId);
			modules.add(sModuleId) ;
		}
		return modules.elements() ;
	}
	/**
	 * Unified logger point
	 * @param string
	 */
	private void log(String sMessage)
	{
		System.out.println(sMessage) ;		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String sFile = "e:/Salva/CodeProjects/Eclipse/3.1/AHEAD_XRefine/Regression/xak2/bib.xik" ;
		
		Xik myXik = new Xik () ;
		myXik.load(sFile) ;
		myXik.getModules () ;
	}
}
