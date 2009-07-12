package de.ovgu.cide.samples.wizards;


import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.osgi.framework.Bundle;

public class SampleNewWizard extends Wizard implements INewWizard {

	public static final String ID = "de.ovgu.cide.samples";
	private static final String CIDE_EXAMPLE_DIR = "cide_samples";//$NON-NLS-1$
	
	private SampleNewWizardPage mainPage;
	private String samplePath = "";
	

	/**
	 * Constructor for SampleNewWizard.
	 */
	public SampleNewWizard() {
		super();
		setNeedsProgressMonitor(true);
		      
	}
	
	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		mainPage = new SampleNewWizardPage(samplePath);
		addPage(mainPage);
		
	}
	
	 /* (non-Javadoc)
     * Method declared on IWorkbenchWizard.
     */
    public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
        setWindowTitle("CIDE Example Import");
        
        
        //get the path for the examples - it can be a jar-file or folder structure
        try {
			Bundle bundle = Platform.getBundle(ID); 
			URL realURL = FileLocator.resolve(bundle.getEntry("/"));
			samplePath = realURL.getPath();
			
			//check if is jar file
			if (samplePath.startsWith("file")) {
				samplePath = samplePath.substring(5, samplePath.length()-2);
			}
			else {
				//is folder
				samplePath += CIDE_EXAMPLE_DIR;
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }

    /* (non-Javadoc)
     * Method declared on IWizard.
     */
    public boolean performCancel() {
    	mainPage.performCancel();
        return true;
    }

    /* (non-Javadoc)
     * Method declared on IWizard.
     */
    public boolean performFinish() {
       return mainPage.createProjects();
    }

}