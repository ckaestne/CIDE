/**
 * 
 */
package org.onekin.xak;

import java.io.BufferedReader;
import java.io.File;
import java.util.Enumeration;
import java.util.Vector;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.onekin.util.ParserUtils;
import org.onekin.xak.refinement.Loader;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * @author struji
 * 
 */
public class XakValidator
{
	static final String	JAXP_SCHEMA_LANGUAGE	= "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	static final String	W3C_XML_SCHEMA				= "http://www.w3.org/2001/XMLSchema";

	static final String	XSD_XAK								= "xak2.xsd";

	private Validator		_validator;

	private String			_sXsdFile;
	
	private boolean _bVerbose ;

	public XakValidator()
	{
		_sXsdFile = null;		
		this.init();
	}

	public XakValidator(String sXsdFile)
	{
		_sXsdFile = sXsdFile;
		this.init();
	}

	/**
	 * 
	 */
	private void init()
	{
		_validator = null;
		_bVerbose = false ;
		try
		{
			// create a SchemaFactory capable of understanding WXS schemas
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			Source schemaFile = null;
			Schema schema = null;

			if (_sXsdFile == null)
			{
				// JAR: load a WXS schema, represented by a Schema instance
				Loader loader = new Loader();
				BufferedReader brXsdXak = loader.getResourceAsStrean(XSD_XAK);
				schemaFile = new StreamSource(brXsdXak);
				schema = factory.newSchema(schemaFile);
			} else
			{
				// LOCAL load a WXS schema, represented by a Schema instance
				schemaFile = new StreamSource(new File(_sXsdFile));
				schema = factory.newSchema(schemaFile);
			}
			// create a Validator instance, which can be used to validate an instance
			// document
			_validator = schema.newValidator();
		} catch (Exception ex)
		{
			System.err.println("Error while initing Validator");
			ex.printStackTrace();
		}
		return;
	}
	
	public boolean validate (Document docInput, String sRefine)
	{
		// Validate docInput
		// this.log ("1) Validate INPUT (TODO)") ;
		// boolean bIn = this.validate
		
		this.log ("2) Validate REFINE") ;
		boolean bRef = this.validateSyntax(sRefine) ;
		
		this.log ("3) Validate REFINE - SafeGen") ;
		Document docRefine = ParserUtils.parseFile(new File(sRefine)) ;
		
		boolean bArtifacts = this.validateModArtifacts(docInput, docRefine) ;
		
		boolean bExtends = this.validateModExtends(docInput, docRefine) ;
		
		boolean bSuper = this.validateModSuper(docRefine) ;
		
		boolean bOverride = this.validateModOverride(docRefine) ;			
		
		boolean bResult = bRef & bArtifacts & bExtends & bSuper & bOverride ;
		return bResult ;
	}

	/**
	 * @param docInput
	 * @return
	 */
	public boolean validateXikInterface(String sXakDoc)
	{
		this.log("XIK Validation") ;		
		Document docXak = ParserUtils.parseFile(new File(sXakDoc)) ;
		this.log("XAK Doc \n" + ParserUtils.nodeToString(docXak)) ;		
		String sXakInterface = docXak.getDocumentElement().getAttribute("xak:interface") ;		
		if (sXakInterface == null)
		{
			this.log("NO XIK Interface") ;
			return false ;
		}		
		else 
		{
			// Has interface
			System.out.println("XIK interface: " + sXakInterface);
			Xik intf = new Xik (sXakInterface) ;
			Enumeration enumXikModules = intf.getModules() ;			
			// Validate modules
			while(enumXikModules.hasMoreElements())
			{
				String sXikId = (String) enumXikModules.nextElement() ;
				this.log("XIK module: " + sXikId) ;
				// String sXPath = "//@xak:module" ; // + sXikId + "'";
				String sXPath = "*[@module='" + sXikId + "']" ;
				NodeList nlExist = ParserUtils.selectNodes(docXak, sXPath) ;
				this.log("Node exists\n" + ParserUtils.nodeToString(nlExist)) ;				 
			}
		}		
		return true;
	}

	/**
	 * This method validates syntax of a given XAK refinement file
	 * 
	 * @param sXmlFile
	 *          String XML file
	 * @return
	 */
	public boolean validateSyntax(String sXmlFile)
	{
		try
		{			
			_validator.validate(new StreamSource(new File(sXmlFile)));
			this.log("OK VALIDATION XSD ['" + sXmlFile + "']") ;
		} 
		catch (Exception ex)
		{
			System.err.println("Error while VALIDATING");
			System.err.println("XAK file: " + sXmlFile);
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Validate the syntax of an input XAK document
	 * 
	 * @param docInput
	 *          Document
	 * @return
	 */
	public boolean validateSyntax(Document docInput)
	{
		try
		{
			_validator.validate(new DOMSource(docInput));
			this.log("OK VALIDATION XSD ['" + "" + "']") ;
		} 
		catch (Exception ex)
		{
			System.err.println("Error while VALIDATING");
			System.err.println("XAK Doc: \n" + ParserUtils.nodeToString(docInput));
			ex.printStackTrace();
			return false;
		}
		return true;
	}
	
	private boolean validateModArtifacts (Document docParent, Document docRefine)
	{
		// Validate that xak:artifact exists in previous document
		this.log("Checking <xak:refines>");
		String sXPath = "//@artifact";
		Node ndParent = ParserUtils.selectSingleNode(docParent, sXPath) ;
		String sParentArtifact = ndParent.getNodeValue() ;
		
		Node ndChild = ParserUtils.selectSingleNode(docRefine, sXPath) ;
		String sChildArtifact = ndChild.getNodeValue() ;
		
		if (sParentArtifact.equalsIgnoreCase(sChildArtifact))
		{
			this.log("EXISTS xak:artifact [parent: " + sParentArtifact + "] [child: " + sChildArtifact + "]");
			return true ;
		}		  
		else 
			this.log("DO NOT EXIST xak:artifact [parent: " + sParentArtifact + "] [child: " + sChildArtifact + "]");
		return false ;
	}
	private boolean validateModExtends (Document docParent, Document docRefine)
	{		
		// Validate that xak:modules exists in previous document
		this.log("Checking <xak:extends>");
		String sXPath = "//@module";

		NodeList nlParent = ParserUtils.selectNodes(docParent, sXPath) ;
		Vector vParent = new Vector () ;
		// this.log(nlParent.getLength());
		for (int i = 0; i < nlParent.getLength(); i++)
		{
			String sAdd = nlParent.item(i).getNodeValue() ;
			// this.log(sAdd);
			vParent.add(sAdd) ;			
		}		
		sXPath = "//*[name()='xak:extends']";
		NodeList nlModule = ParserUtils.selectNodes(docRefine, sXPath) ;
		
		for (int i = 0; i < nlModule.getLength(); i++)
		{
			Node ndModule = nlModule.item(i) ;
			String sModuleName = ndModule.getAttributes().getNamedItem("xak:module").getNodeValue() ;
			
			if (vParent.contains(sModuleName))
			{
				this.log("EXISTS [xak:module: " + sModuleName + "]");
			}
			else
				this.log("DO NO EXIST [xak:module: " + sModuleName + "]");			
		}		
		return true ;
	}
	private boolean validateModSuper (Document docRefine)
	{
		// Validate that xak:super xak:module exists		
		// Validate that super is invoking the same module
		this.log("Checking <xak:super>");
		String sXPath = "//*[name()='xak:super']";
		NodeList nlSuper = ParserUtils.selectNodes(docRefine, sXPath) ;
		for (int i = 0; i < nlSuper.getLength(); i++)
		{
			Node ndSuper = nlSuper.item(i) ;
			String sSuperName = ndSuper.getAttributes().getNamedItem("xak:module").getNodeValue() ;
			Node ndParentSuper = ndSuper.getParentNode() ;
			String sParentName = ndParentSuper.getAttributes().getNamedItem("xak:module").getNodeValue() ;
			
			// this.log("EXISTS 'xak:module' [xak:super: " + sSuperName + "] [xak:extends: " + sParentName + "]");
			if (sSuperName.equalsIgnoreCase(sParentName))
			{
				this.log("OK USE super [xak:module: " + sParentName + "]");
			}
			else
				this.log("ERROR USE super [xak:module: " + sParentName + "]");	
			
		}
		return true ;
	}
	/**
	 * @param docInput
	 * @param docRefine
	 * @return
	 */
	private boolean validateModOverride(Document docRefine)
	{
		this.log("Checking <xak:extends> - overriding") ;
		String sXPathExtends = "//*[name()='xak:extends']";
		String sXPathSuper = "*[name()='xak:super']";
		
		NodeList nlModule = ParserUtils.selectNodes(docRefine, sXPathExtends) ;
		
		for (int i = 0; i < nlModule.getLength(); i++)
		{
			Node ndModule = nlModule.item(i) ;
			String sModuleName = ndModule.getAttributes().getNamedItem("xak:module").getNodeValue() ;
			Node ndSuper = ParserUtils.selectSingleNode(ndModule, sXPathSuper) ;
 			if (ndSuper==null)
 			{
 				this.log("WARNING OVERRIDE [xak:module: " + sModuleName + "]");
 			}							
		}		
		return true ;
	}
	
	/**
	 * Set logger to verbose
	 */
	public void setVerbose(boolean bSet)
	{
		_bVerbose = bSet ;		
	}
	
	/**
	 * This method logs a message
	 * @param sMessage
	 * -not deprecated-
	 */
	private String log(String sMessage)
	{
		// logger.debug(sMessage+"\n");		
		if (_bVerbose) System.out.println(sMessage);
		return sMessage;
	}
	/*
	 * @param args
	 */
/*	public static void main(String[] args)
	{
		String sPath = "E:/Salva/CodeProjects/Eclipse/3.1/AHEAD_XRefine/schema";

		String sXsd = sPath + "/" + XSD_XAK;
		String sXml = sPath + "/test-xsd-ref.xak";

		XakValidator xv = new XakValidator(sXsd);
		boolean bResult = xv.validateSyntax(sXml);

		this.log("Result: " + bResult);
	} */
	public static void main(String[] args)
	{
		String sPath = "E:/Salva/CodeProjects/Eclipse/3.1/AHEAD_XRefine/transform";
		
		String sXakParent = sPath + "/bib.xak";
		Document docXakParent = ParserUtils.parseFile(new File(sXakParent)) ;

		String sXakRef = sPath + "/refine.xak";		
		Document docXakRef = ParserUtils.parseFile(new File(sXakRef)) ;

		XakValidator xv = new XakValidator();
		boolean bResult = xv.validateModExtends(docXakParent, docXakRef);

		xv.log("Result: " + bResult);
	}	
}
