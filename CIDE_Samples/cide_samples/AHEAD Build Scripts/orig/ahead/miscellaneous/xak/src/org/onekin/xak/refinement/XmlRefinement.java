package org.onekin.xak.refinement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.infozone.tools.xml.queries.XUpdateQuery;
import org.infozone.tools.xml.queries.XUpdateQueryFactory;
import org.onekin.util.ParserUtils;
import org.onekin.xak.XakValidator;
import org.onekin.xak.namespace.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlRefinement
{

	final public static String	XSL_XAK2XR			= "xak2xrefine.xsl";
	final public static String	XSL_XR2XUPDATE	= "xr2xupdate.xslt";

	private Transformer	_tXak2Xr ;
	private Transformer	_tXr2XUpdate ;

	private boolean	_bXak2;
	private boolean	_bVerbose ;
	private boolean	_bValidate;
	
	private XakValidator _xak2Val;

	/**
	 * Constructor
	 */
	public XmlRefinement()
	{
		this.init();
	}
	/**
	 * Constructor
	 * @param sXRefineHomeDir xrefine.home
	 * @deprecated
	 */
	public XmlRefinement(String sXRefineHomeDir)
	{
		this.init();
	}
	/**
	 * This method initializes xrefine tooling
	 *
	 */
	private void init()
	{
		_bXak2 = false;
		_xak2Val = new XakValidator () ;
		_bVerbose = false ;
		_bValidate = false ;
		
		_tXak2Xr				= null;
		_tXr2XUpdate		= null;
		
		try
		{	
			ParserUtils.bindPrefixToNamespaceURI(XakContext.NS_PREFIX, XakContext.NS_URI);
			ParserUtils.bindPrefixToNamespaceURI(XrContext.NS_PREFIX, XrContext.NS_URI);
			Loader loader = new Loader();
			
			// Create transformer for Xak2XRef
			BufferedReader brXslXak2Xr = loader.getResourceAsStrean(XSL_XAK2XR);
			_tXak2Xr = ParserUtils.getTransformer(new StreamSource(brXslXak2Xr));
			
			// Create transformer for XUpdate
			BufferedReader brXsl = loader.getResourceAsStrean(XSL_XR2XUPDATE);
			_tXr2XUpdate = ParserUtils.getTransformer(new StreamSource(brXsl));
			
			// File fXsl = new File (sXsl) ;
			// this.log("TRANSFORMER CLASS: " + _transformerXUpdate.getClass());			
			// this.log("Content of XSL:\n" + brXsl.readLine());
		} 
		catch (Exception ex)
		{
			System.out.println(ex.getMessage() + "\n");
			ex.printStackTrace(System.out);
		}
	}
	/**
	 * This method set mode to XAK2
	 */
	public void setModeXak2()
	{
		_bXak2 = true;
	}
	/**
	 * This method set mode to XAK2
	 */
	public void setValidate(boolean bValidate)
	{
		_bValidate = bValidate ;		
	}	
	/**
	 * @param sXmlFile String
	 * @return
	 */
	private boolean isRefinement(Document doc)
	{
		if (doc == null)
			return false;

		boolean bResult = true;
		try
		{
			Element root = doc.getDocumentElement();
			String sName = root.getLocalName();
			String sUri = root.getNamespaceURI();
			this.log("Refinments root name: "+sName+", uri: "+sUri);
			String sXPath = null ; 
			if (_bXak2 && sName.equals(Constants.REFINES_ELT) &&
					sUri.equals(XakContext.NS_URI))
			   bResult = true;					
			
			else if (sName.equals(Constants.REFINE_ELT) &&
						sUri.equals(XrContext.NS_URI))
				//sXPath = "//*[name()='xr:refine']";
				bResult = true;
			else
				bResult = false;
			// log("bResult =" + String.valueOf(bResult));
			// log("Result: \n" + ParserUtils.nodeToString(nl));
		} catch (Exception ex)
		{
			log("XAK - Parsing error: " + ex.getLocalizedMessage());
			ex.printStackTrace();
		} finally
		{
		}
		return bResult;
	}

	/**
	 * @param sXmlFile String
	 * @return
	 */
	private boolean isRefinement(String sXmlFile)
	{
		Document docXml = ParserUtils.parseFile(new File(sXmlFile));
		return this.isRefinement(docXml);
	}

	/**
	 * This method refines an artifact following XRefine
	 * @param docInput input file
	 * @param docXrFile refinement file
	 * @param sOutFile result file
	 * @return
	 */
	public Document refine(Document docInput, String sXRFile)
	{
		this.log("begin refining...");
		// Is sInputFile a base or a refinement
		boolean bInputIsRefinement = this.isRefinement(docInput);
		boolean bXrFileIsRefinement = this.isRefinement(sXRFile);
		
		if (bInputIsRefinement == false && bXrFileIsRefinement == true)
		{
			this.log("FUNCTION (CONSTANT) = RESULT -> Regular refinement");
			this.log("Input CONSTANT\n" + ParserUtils.nodeToString(docInput));
			String sXR = null ;
			if (_bXak2)
			{
				if (_bValidate)
				{
					boolean bResult = _xak2Val.validate(docInput, sXRFile) ;										
					this.log("validate: " + bResult);
			}				
				this.log("XAK\n" + this.readFile2String(sXRFile));
				sXR = this.xak2xr(sXRFile);								
			}
			else
			{
				// Read Xr from file							
				sXR = this.readFile2String(sXRFile) ; 
			}
			this.log("XR\n" + sXR);
			
			String sXUpdateQuery = this.xr2xup(sXR);
			this.log("XUpdate\n" + sXUpdateQuery);
			
			Document docResult = this.applyXUpdate(sXUpdateQuery, docInput);
			this.log("Output RESULT\n" + ParserUtils.nodeToString(docResult));
			
			return docResult;						
		} 
		else if (bInputIsRefinement == true && bXrFileIsRefinement == true)
		{
			this.log("Composing: FUNCTION (FUNCTION) -> Function refinement");
			if (_bXak2 && _bValidate)
			{
				boolean bResultFile1 = _xak2Val.validateSyntax(docInput) ;
				this.log("VALIDATION XSD ['" + docInput.getBaseURI() + "'] = [: " + bResultFile1 + "]");
				
				boolean bResultFile2 = _xak2Val.validateSyntax(sXRFile) ;
				this.log("VALIDATION XSD ['" + sXRFile + "'] = [: " + bResultFile2 + "]");				
			}
			Document docResult = this.refineTwoFunctions(docInput, sXRFile);
			return docResult;			
		} 
		else if (bInputIsRefinement == false && bXrFileIsRefinement == false)
		{
			this.log("ERROR Composing: CONSTANT (CONSTANT)");
			if (_bXak2)
				this.log("check if the refinement document root element is xak:refines, where xak prefix must be "+XakContext.NS_URI);
			else
				this.log("check if the refinement document root element is xr:refine, where xr prefix must be "+XrContext.NS_URI);
		} 
		else if (bInputIsRefinement == true && bXrFileIsRefinement == false)
		{
			this.log("ERROR Composing: CONSTANT(FUNCTION)");
			// Document doc = ParserUtils.parseFile(new File(sXrFile)) ;
			// this.saveToDocument(sOutFile, doc) ;
			// this.copy(sXrFile, sOutFile);
		}
		return null;
	}
  /**
   * This method reads file's content and returns a String
   * @param String sCompleteFileName
   * @return String
   */
  private String readFile2String (String sCompleteFileName) 
  {
    String sFileContent = null ;
    try 
    {
      FileInputStream fileInputStream = new FileInputStream (sCompleteFileName) ;
      byte [] buffer = new byte [fileInputStream.available()] ;
      fileInputStream.read(buffer) ;
      sFileContent = new String (buffer) ;    
    } 
    catch (Exception ex) 
    {
      ex.printStackTrace();
    } finally 
    {
    }
    return sFileContent ; 
  }
	/**
	 * This method transforms Xak to XRefine
	 * @param sXakFile String name of xak file
	 * @return
	 */
	private String xak2xr(String sXakFile)
	{
		StringWriter sw = new StringWriter () ;
		StreamResult sr = new StreamResult () ;
		sr.setWriter(sw) ;
		try
		{
			File fXakFile = new File(sXakFile);
			_tXak2Xr.transform(new StreamSource(fXakFile), sr);
		} 
		catch (Exception ex)
		{
			this.log(ex.getMessage() + "\n");
			ex.printStackTrace(System.out);
		}        
		return sr.getWriter().toString();
	}	
	/**
	 * This method transforms XRefine to XUpdate
	 * @param sXrFileContent
	 * @return
	 */
	private String xr2xup(String sXrFileContent)
	{
		StringReader sreader = new StringReader (sXrFileContent) ;
		StringWriter sw = new StringWriter () ;
		StreamResult sr = new StreamResult () ;
		sr.setWriter(sw) ;
		try
		{			
			_tXr2XUpdate.transform(new StreamSource(sreader), sr);
		} 
		catch (Exception ex)
		{
			this.log(ex.getMessage() + "\n");
			ex.printStackTrace(System.out);
		}        
		return sr.getWriter().toString();
	}
	
	/**
	 * Parse input and update file, performs XUpdate-query 
	 * and returns the result
	 */
	protected Document applyXUpdate(String sXUpdateQueryContent, Document docInput)
	{
		try
		{
			XUpdateQuery xupdate = XUpdateQueryFactory.newInstance().newXUpdateQuery();
			xupdate.setQString(sXUpdateQueryContent);
			xupdate.execute(docInput);
			// this.log("Result:\n" + ParserUtils.nodeToString(result.getDocumentElement()));
		} catch (Exception ex)
		{
			System.out.println(ex.getMessage() + "\n");
			ex.printStackTrace(System.out);
		}
		return docInput;
	}

	/**
	 * @param inputFile
	 * @param xrFile
	 * @param outFile
	 */
	private Document refineTwoFunctions(Document docInput, Document docXr)
	{
		String sXPath = null ; 
		if (_bXak2)
		{			
			//sXPath = "//*[name()='xak:extends']";
			sXPath = "//xak:extends";
		}
		else
		{					
			//sXPath = "//*[name()='xr:at']";
			sXPath = "//xr:at";
		}		
		Element eRootInput = docInput.getDocumentElement();
		Element eRootXr = docXr.getDocumentElement();
		// Get nodes from xrefine		
		NodeList nl = ParserUtils.selectNodes(eRootXr, sXPath);
		// Import this nodes to output
		for (int i = 0; i < nl.getLength(); i++)
		{
			// Add node to document
			Node ndImported = docInput.importNode(nl.item(i), true);
			// Add new element to document root
			eRootInput.appendChild(ndImported);
		}
		return docInput;
	}

	/**
	 * @param docInput Document input
	 * @param sXrFile String file name
	 */
	private Document refineTwoFunctions(Document docInput, String sXrFile)
	{
		File fXrFile = new File(sXrFile);
		Document docXr = ParserUtils.parseFile(fXrFile);
		
		return this.refineTwoFunctions(docInput, docXr) ;
	}

	/**
	 * @param sInputFile String file name
	 * @param sXrFile String file name
	 */
	private Document refineTwoFunctions(String sInputFile, String sXrFile)
	{
		// Parse input document
		File fInputFile = new File(sInputFile);
		Document docInput = ParserUtils.parseFile(fInputFile);

		File fXrFile = new File(sXrFile);
		Document docXr = ParserUtils.parseFile(fXrFile);
		
		return this.refineTwoFunctions(docInput, docXr) ;
	}

			
	/**
	 * Set logger to verbose
	 */
	public void setVerbose()
	{
		_bVerbose = true ;
		_xak2Val.setVerbose(true) ;
	}
	
	/**
	 * This method logs a message
	 * @param sMessage
	 * -not deprecated-
	 */
	private String log(String sMessage)
	{
		// logger.debug(sMessage+"\n");
		String sXak = (_bXak2) ? "XAK2: " : "XAK1: " ;
		if (_bVerbose) System.out.println(sXak + sMessage);
		return sMessage;
	}

	/*	public static void main(String[] args)
	 {
	 String sXrefHome = "d:/development/xrefine-0.1";
	 XmlRefinement xref = new XmlRefinement(sXrefHome);
	 String sBase = "d:/temp/core/build.xml";
	 String sRef = "d:/temp/bc/build.xml";
	 String sOut = "d:/temp/core-bc-build.xml";
	 xref.refine(sBase, sRef, sOut);
	 return;
	 } */
}