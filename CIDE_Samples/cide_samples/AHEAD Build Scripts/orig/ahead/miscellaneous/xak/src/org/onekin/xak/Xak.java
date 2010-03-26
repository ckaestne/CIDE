/**
 * 
 */
package org.onekin.xak;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;

import org.onekin.util.ParserUtils;
import org.onekin.xak.refinement.XmlRefinement;
import org.w3c.dom.Document;

import org.apache.log4j.*;

/**
 * @author struji - Salvador Trujillo - The University of the Basque Country Feb
 *         1st, 2006
 * 
 */
public class Xak
{
   static Logger logger = Logger.getLogger(Xak.class);

	static {
	    BasicConfigurator.configure();
	    logger.setLevel(Level.ALL); 
	}
	/**
	 * Constant for command options handling
	 */
	private final int	DEFAULT	= 0;

	private final int	OUTPUT	= 1;

	private final int	COMPOSE	= 2;	
	

	/**
	 *  Method for print Usage Error message
	 */
	public void showUsageError()
	{
		System.out.println("Wrong command line usage");
		System.out.println("Usage xak [options]");
		System.out.println(" -xak2     Composes XAK2 files (Extension .xak is required)");
		System.out.println("           (XAK1 is default)");
		System.out.println(" -v        Validate refinements (only XAK2)");
		System.out.println(" -c        file1 file2 file3 ... ");
		System.out.println("           Composes xml files in order ... file3(file2(file1))");
		System.out.println(" -o        outputFile") ;		
//		System.out.println(" -verbose  Prints verbose composition (all complexity)");		
	}

	/**
	 * Processes the command line options.
	 */
	public void executeCommand(String[] sArgs)
	{
		// Checks to see if the command line is empty
		if (sArgs.length == 0)
		{
			showUsageError();
			return;
		}
		// Auxiliary variables
		boolean bValidate = false; // validate or not the composition
		boolean bVerbose = false; // verbose or quiet
		boolean bXak2 = false; // xak1 or xak2
		int iOption = DEFAULT; // command option chosen
		String sOutputFile = "";
		LinkedList llFilesCompose = new LinkedList();

		// Traverses the command line and get the files to be composed
		for (int i = 0; i < sArgs.length; i++)
		{
			if (sArgs[i].equals("-xak2"))
			{
				bXak2 = true;
				continue;
			}
			if (sArgs[i].equals("-verbose"))
			{
				bVerbose = true;
				continue;
			}
			if (sArgs[i].equals("-v"))
			{
				bValidate = true;
				continue;
			}
			if (sArgs[i].equals("-o"))
			{
				iOption = OUTPUT;
				continue;
			}
			if (sArgs[i].equals("-c"))
			{
				iOption = COMPOSE;
				continue;
			}
			switch (iOption)
			{
				case OUTPUT:
					sOutputFile = sArgs[i];
					break;
				case COMPOSE:
					llFilesCompose.add(sArgs[i]);
					break;
				default: // Wrong usage of the command line
					showUsageError();
					return;
			} // for the switch
		} // for all the arguments

		// Output file name must be set
		if (sOutputFile.equals(""))
		{
			this.showUsageError();
			return;
		}
		// Calls for the composition of files
		this.compose(llFilesCompose, sOutputFile, bValidate, bXak2, bVerbose);
		return;
	} // processCommand

	/**
	 * This method executes composition
	 * @param llFilesCompose
	 * @param bValidate
	 * @param sOutputFile
	 */
	private void compose(LinkedList llFilesCompose, String sOutputFile, boolean bValidate, boolean bXak2, boolean bVerbose)
	{
		XmlRefinement xref = new XmlRefinement();		
		
		if (bXak2) xref.setModeXak2();
		
		if (bValidate) xref.setValidate(bValidate);
		
		if (bVerbose)
		{
			this.showComposeLog(llFilesCompose, bValidate, sOutputFile);
			xref.setVerbose();
		}
				
		try
		{
			// If only one file for compose
			if (llFilesCompose.size() == 1)
			{
				String sBase = (String) llFilesCompose.getFirst();
				if (sBase == null)
					return;
				this.copy(new File(sBase), new File(sOutputFile));
				if (bXak2) // xak2xml
				{
					Xak2xml x2x = new Xak2xml () ;
					x2x.fileXak2xml(sOutputFile) ;				
				}
				return;
			}
			// else
			// Create the builder and parse the file
			String sBase = (String) llFilesCompose.getFirst();
			File fBase = new File(sBase);
			Document docInput = ParserUtils.parseFile(fBase);
			// Validate XIK
			if (bValidate)
			{
				XakValidator xval = new XakValidator () ;
				xval.setVerbose(bVerbose) ;
				// xval.validateXikInterface(sBase) ;				
			}
			// Refine doc
			for (int i = 1; i < llFilesCompose.size(); i++)
			{
				// Load ref file into a DOM
				String sRefFile = (String) llFilesCompose.get(i);
				docInput = xref.refine(docInput, sRefFile);
			}
			// Serialize DOM to file
			ParserUtils.doc2prettyprint(sOutputFile, docInput);			
			
			if (bXak2) // xak2xml
			{
				Xak2xml x2x = new Xak2xml () ;
				x2x.fileXak2xml(sOutputFile) ;				
			}
		}
		catch (Exception e)
		{
			this.log("ERROR composing: " + sOutputFile+"\n");
			e.printStackTrace(System.out);
		}
		return;
	}

	/**
	 * This method copies source file to destination
	 * @param fIn
	 * @param fOut
	 * @return
	 * @throws Exception
	 */
	private boolean copy(File fIn, File fOut) throws Exception
	{
		// this.log("COPYING file: '" + fIn.getAbsolutePath() + "'\n     to file: '" + fOut.getAbsolutePath());
		FileInputStream fis = new FileInputStream(fIn);
		FileOutputStream fos = new FileOutputStream(fOut);
		byte[] buf = new byte[1024];
		int i = 0;
		while ((i = fis.read(buf)) != -1)
		{
			fos.write(buf, 0, i);
		}
		fis.close();
		fos.close();
		return true;
	}

	/**
	 * @param string
	 */
	private void log(String sMessage)
	{
		// logger.debug ("") ;
		System.out.println(sMessage);
		//logger.debug(sMessage);
	}

	/**
	 * This method shows composition LOG
	 * @param llFilesCompose
	 * @param bValidate
	 * @param sOutputFile
	 */
	private void showComposeLog(LinkedList llFilesCompose, boolean bValidate, String sOutputFile)
	{
		String sValidation = (bValidate) ? "ON" : "OFF";
		System.out.println("Compose with VALIDATION: " + sValidation);
		System.out.println("FROM files");
		String sEquation = "";
		for (int i = 0; i < llFilesCompose.size(); i++)
		{
			String sFile = (String) llFilesCompose.get(i);
			System.out.println("  File " + String.valueOf(i) + " : " + sFile);
			sEquation = sFile + "(" + sEquation + ")";
		}
		System.out.println("TO file: " + sOutputFile);
		System.out.println("EQUATION: " + sEquation);
		return;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*
		 * Flags: -v validation set // at present does not work -o output file -c
		 * file1 file2 file3 file4 ... // composes xml files
		 */

		Xak composer = new Xak();
		composer.executeCommand(args);
	}
}
