package de.ovgu.cide.samples.wizards;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.IOverwriteQuery;
//import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
//import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
//import org.eclipse.ui.internal.ide.StatusUtil;
//import org.eclipse.ui.internal.wizards.datatransfer.DataTransferMessages;
//import org.eclipse.ui.internal.wizards.datatransfer.ILeveledImportStructureProvider;
//import org.eclipse.ui.internal.wizards.datatransfer.TarLeveledStructureProvider;
//import org.eclipse.ui.internal.wizards.datatransfer.TarEntry;
//import org.eclipse.ui.internal.wizards.datatransfer.TarException;
//import org.eclipse.ui.internal.wizards.datatransfer.TarFile;
//import org.eclipse.ui.internal.wizards.datatransfer.TarLeveledStructureProvider;
//import org.eclipse.ui.internal.wizards.datatransfer.ZipLeveledStructureProvider;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.ui.wizards.datatransfer.FileSystemStructureProvider;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;

import de.ovgu.cide.samples.utils.CommentParser;
import de.ovgu.cide.samples.utils.RequirementCategory;
import de.ovgu.cide.samples.utils.ZipStructureProvider;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (mpe).
 */

public class SampleNewWizardPage extends WizardPage implements
IOverwriteQuery {
//	private Text containerText;
//	private Text fileText;

	//private ISelection selection;
	/**
	 * The name of the folder containing metadata information for the workspace.
	 */
	public static final String METADATA_FOLDER = ".metadata"; //$NON-NLS-1$
	
	/**
	 * The import structure provider.
	 * 
	 * @since 3.4
	 */
	private ZipStructureProvider structureProvider;
	
	
	private CheckboxTreeViewer projectsList;
	private Text descBox;
	private Text requirementBox;
	private ScrolledComposite requirementSC;
	
	
	private ProjectRecord[] selectedProjects = new ProjectRecord[0];
	private IProject[] wsProjects;
	private String samplePath;
	/**
	 * Constructor for SampleNewWizardPage.
	 * 
	 * @param pageName
	 */
	public SampleNewWizardPage(String samplePath) {
		super("Select CIDE Example(s)");
		setTitle("Select CIDE Example(s) which you would like to explore");
		this.samplePath = samplePath;
	}
	
	public void createControl(Composite parent) {

		initializeDialogUnits(parent);

		Composite workArea = new Composite(parent, SWT.NONE);
		setControl(workArea);

		workArea.setLayout(new GridLayout());
		workArea.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

		createProjectsList(workArea);
		createDescriptionArea(workArea);
		createRequirementsArea(workArea);
		
		updateProjectsList(samplePath);
		

		Dialog.applyDialogFont(workArea);

	}
	
	/**
	 * Create the checkbox list for the found projects.
	 * 
	 * @param workArea
	 */
	private void createProjectsList(Composite workArea) {

		Label title = new Label(workArea, SWT.NONE);
		
		title.setText("Choosable Examples");

		Composite listComposite = new Composite(workArea, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 0;
		layout.makeColumnsEqualWidth = false;
		listComposite.setLayout(layout);

		listComposite.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL | GridData.FILL_BOTH));

		projectsList = new CheckboxTreeViewer(listComposite, SWT.BORDER);
		GridData listData = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.GRAB_VERTICAL | GridData.FILL_BOTH);
		projectsList.getControl().setLayoutData(listData);

		projectsList.setContentProvider(new ITreeContentProvider() {

			public Object[] getChildren(Object parentElement) {
				return null;
			}

			public Object[] getElements(Object inputElement) {
				return getValidProjects();
			}

			public boolean hasChildren(Object element) {
				return false;
			}

			public Object getParent(Object element) {
				return null;
			}


			public void dispose() {}

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {}

		});

		projectsList.setLabelProvider(new LabelProvider() {
			public String getText(Object element) {
				return ((ProjectRecord) element).getProjectLabel();
			}
		});

		projectsList.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				setPageComplete(projectsList.getCheckedElements().length > 0);
			}
		});

		projectsList.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection iss = (IStructuredSelection) event.getSelection();
					descBox.setText(((ProjectRecord) iss.getFirstElement()).getDescription());
					performRequirementCheck(((ProjectRecord) iss.getFirstElement()).getRequirements());
						
				}
						
			}
		}
		
		);
		
		projectsList.setInput(this);
		projectsList.setComparator(new ViewerComparator());
		
		createSelectionButtons(listComposite);
	}
	
	private void createDescriptionArea(Composite workArea) {
		
		Label title = new Label(workArea, SWT.NONE);
		title.setText("Description");
		
		descBox = new Text(workArea, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		descBox.setText ("");
		
		GridData dbDG = new GridData(GridData.FILL_BOTH);
		dbDG.minimumHeight = 75;
		descBox.setLayoutData(dbDG);

	}
	
	private void createRequirementsArea(Composite workArea) {
				
		requirementBox = new Text(workArea, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);		
		GridData rbDG = new GridData(GridData.FILL_BOTH);
		rbDG.minimumHeight = 50;
		requirementBox.setLayoutData(rbDG);
		requirementBox.setText ("");
		requirementBox.setVisible(false);
		
		
	}
	/**
	 * Create the selection buttons in the listComposite.
	 * 
	 * @param listComposite
	 */
	private void createSelectionButtons(Composite listComposite) {
		Composite buttonsComposite = new Composite(listComposite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		buttonsComposite.setLayout(layout);

		buttonsComposite.setLayoutData(new GridData(
				GridData.VERTICAL_ALIGN_BEGINNING));

		Button selectAll = new Button(buttonsComposite, SWT.PUSH);
		selectAll.setText("Select All");
		selectAll.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				projectsList.setCheckedElements(selectedProjects);
				setPageComplete(projectsList.getCheckedElements().length > 0);
			}
		});
		Dialog.applyDialogFont(selectAll);
		setButtonLayoutData(selectAll);

		Button deselectAll = new Button(buttonsComposite, SWT.PUSH);
		deselectAll.setText("Deselect All");
		deselectAll.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				projectsList.setCheckedElements(new Object[0]);
				setPageComplete(false);
			}
		});
		Dialog.applyDialogFont(deselectAll);
		setButtonLayoutData(deselectAll);

	}
	
	public boolean isPluginAvailable(String category, String PluginID) {
		//TODO!!!
		return false;
	}

	public void performRequirementCheck(List requirements) {
		String reqMessage = "";
		Iterator i = requirements.iterator();
		String categoryName;
		while (i.hasNext()) {
			RequirementCategory cat = (RequirementCategory) i.next();
			
			//get the category name
			categoryName = cat.getCategory();
	
			//get all plugins which need to be checked
			Set plugins = cat.getPluginIds();
			for (Object object : plugins) {
				
				if (!isPluginAvailable(categoryName, (String)object))
					reqMessage += "Warning: " + cat.getErrorMsg((String)object) + "\n";
			}
		}
		
		requirementBox.setText(reqMessage);
		if (reqMessage.equals(""))
			requirementBox.setVisible(false);
		else
			requirementBox.setVisible(true);
			
	}
	
	/**
	 * Update the list of projects based on path. Method declared public only
	 * for test suite.
	 * 
	 * @param path
	 */
	public void updateProjectsList(final String path) {
		// on an empty path empty selectedProjects
		if (path == null || path.length() == 0) {
			setMessage("Select a directory to search for existing Eclipse projects.");
			selectedProjects = new ProjectRecord[0];
			projectsList.refresh(true);
			projectsList.setCheckedElements(selectedProjects);
			setPageComplete(projectsList.getCheckedElements().length > 0);
			return;
		}

		final File directory = new File(path);
		
		
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {

				/*
				 * (non-Javadoc)
				 * 
				 * @see
				 * org.eclipse.jface.operation.IRunnableWithProgress#run(org
				 * .eclipse.core.runtime.IProgressMonitor)
				 */
				public void run(IProgressMonitor monitor) {

					monitor
							.beginTask("Searching for projects", 100);
					selectedProjects = new ProjectRecord[0];
					Collection files = new ArrayList();
					monitor.worked(10);
					
//					if (ArchiveFileManipulations.isTarFile(path)) {
//						TarFile sourceTarFile = getSpecifiedTarSourceFile(path);
//						if (sourceTarFile == null) {
//							return;
//						}
//
//						structureProvider = new TarLeveledStructureProvider(
//								sourceTarFile);
//						Object child = structureProvider.getRoot();
//
//						if (!collectProjectFilesFromProvider(files, child, 0,
//								monitor)) {
//							return;
//						}
//						Iterator filesIterator = files.iterator();
//						selectedProjects = new ProjectRecord[files.size()];
//						int index = 0;
//						monitor.worked(50);
//						monitor
//								.subTask("Processing results");
//						while (filesIterator.hasNext()) {
//							selectedProjects[index++] = (ProjectRecord) filesIterator
//									.next();
//						}
//					} else 
					if (isZipFile(path)) {
						ZipFile sourceFile = getSpecifiedZipSourceFile(path);
						if (sourceFile == null) {
							return;
						}
						structureProvider = new ZipStructureProvider(
								sourceFile);
						Object child = structureProvider.getRoot();

						if (!collectProjectFilesFromProvider(files, child, 0,
								monitor)) {
							return;
						}
						Iterator filesIterator = files.iterator();
						selectedProjects = new ProjectRecord[files.size()];
						int index = 0;
						monitor.worked(50);
						monitor
								.subTask("Processing results");
						while (filesIterator.hasNext()) {
							selectedProjects[index++] = (ProjectRecord) filesIterator
									.next();
						}
					}

					else if (directory.isDirectory()) {

						if (!collectProjectFilesFromDirectory(files, directory,
								null, monitor)) {
							return;
						}
						Iterator filesIterator = files.iterator();
						selectedProjects = new ProjectRecord[files.size()];
						int index = 0;
						monitor.worked(50);
						monitor
								.subTask("Processing results");
						while (filesIterator.hasNext()) {
							File file = (File) filesIterator.next();
							selectedProjects[index] = new ProjectRecord(file);
							index++;
						}
					} else {
						monitor.worked(60);
					}
					monitor.done();
				}

			});
		} catch (InvocationTargetException e) {
//TODO: Log?		IDEWorkbenchPlugin.log(e.getMessage(), e);
		} catch (InterruptedException e) {
			// Nothing to do if the user interrupts.
		}

		projectsList.refresh(true);
		projectsList.setCheckedElements(getValidProjects());
		if (getValidProjects().length < selectedProjects.length) {
			setMessage("Some examples were hidden because they exist in the workspace directory",
					WARNING);
		}
		setPageComplete(projectsList.getCheckedElements().length > 0);
	}
	
	/**
	 * Answer a handle to the zip file currently specified as being the source.
	 * Return null if this file does not exist or is not of valid format.
	 */
	private ZipFile getSpecifiedZipSourceFile(String fileName) {
		if (fileName.length() == 0) {
			return null;
		}

		try {
			return new ZipFile(fileName);
		} catch (ZipException e) {
			displayErrorDialog("Source file is not a valid Zip file.");
		} catch (IOException e) {
			displayErrorDialog("Source file could not be read.");
		}

		return null;
	}
	
//	/**
//	 * Answer a handle to the zip file currently specified as being the source.
//	 * Return null if this file does not exist or is not of valid format.
//	 */
//	private TarFile getSpecifiedTarSourceFile(String fileName) {
//		if (fileName.length() == 0) {
//			return null;
//		}
//
//		try {
//			return new TarFile(fileName);
//		} catch (TarException e) {
//			displayErrorDialog("Source file is not a valid tar file.");
//		} catch (IOException e) {
//			displayErrorDialog("Source file could not be read.");
//		}
//
//		return null;
//	}
	

	
	/**
	 * Collect the list of .project files that are under directory into files.
	 * 
	 * @param files
	 * @param monitor
	 * 		The monitor to report to
	 * @return boolean <code>true</code> if the operation was completed.
	 */
	private boolean collectProjectFilesFromProvider(Collection files,
			Object entry, int level, IProgressMonitor monitor) {

		if (monitor.isCanceled()) {
			return false;
		}
		monitor.subTask("Checking: " + 	structureProvider.getLabel(entry));
		List children = structureProvider.getChildren(entry);
		if (children == null) {
			children = new ArrayList(1);
		}
		Iterator childrenEnum = children.iterator();
		while (childrenEnum.hasNext()) {
			Object child = childrenEnum.next();
			if (structureProvider.isFolder(child)) {
				collectProjectFilesFromProvider(files, child, level + 1,
						monitor);
			}
			String elementLabel = structureProvider.getLabel(child);
			if (elementLabel.equals(IProjectDescription.DESCRIPTION_FILE_NAME)) {
				files.add(new ProjectRecord(child, entry, level));
			}
		}
		return true;
	}
	
	/**
	 * Collect the list of .project files that are under directory into files.
	 * 
	 * @param files
	 * @param directory
	 * @param directoriesVisited
	 * 		Set of canonical paths of directories, used as recursion guard
	 * @param monitor
	 * 		The monitor to report to
	 * @return boolean <code>true</code> if the operation was completed.
	 */
	private boolean collectProjectFilesFromDirectory(Collection files,
			File directory, Set directoriesVisited, IProgressMonitor monitor) {

		if (monitor.isCanceled()) {
			return false;
		}
		monitor.subTask("Checking: " + directory.getPath());
		File[] contents = directory.listFiles();
		if (contents == null)
			return false;

		// Initialize recursion guard for recursive symbolic links
		if (directoriesVisited == null) {
			directoriesVisited = new HashSet();
			try {
				directoriesVisited.add(directory.getCanonicalPath());
			} catch (IOException exception) {
//TODO: Log?	
//				StatusManager.getManager().handle(
//						StatusUtil.newStatus(IStatus.ERROR, exception
//								.getLocalizedMessage(), exception));
			}
		}

		// first look for project description files
		final String dotProject = IProjectDescription.DESCRIPTION_FILE_NAME;
		for (int i = 0; i < contents.length; i++) {
			File file = contents[i];
			if (file.isFile() && file.getName().equals(dotProject)) {
				files.add(file);
				
				// add description
				
				// don't search sub-directories since we can't have nested
				// projects
				return true;
			}
		}
		// no project description found, so recurse into sub-directories
		for (int i = 0; i < contents.length; i++) {
			if (contents[i].isDirectory()) {
				if (!contents[i].getName().equals(METADATA_FOLDER)) {
					try {
						String canonicalPath = contents[i].getCanonicalPath();
						if (!directoriesVisited.add(canonicalPath)) {
							// already been here --> do not recurse
							continue;
						}
					} catch (IOException exception) {
//TODO: Log?	
//						StatusManager.getManager().handle(
//								StatusUtil.newStatus(IStatus.ERROR, exception
//										.getLocalizedMessage(), exception));

					}
					collectProjectFilesFromDirectory(files, contents[i],
							directoriesVisited, monitor);
				}
			}
		}
		return true;
	}
	/**
	 * Get the array of valid project records that can be imported from the
	 * source workspace or archive, selected by the user. If a project with the
	 * same name exists in both the source workspace and the current workspace,
	 * it will not appear in the list of projects to import and thus cannot be
	 * selected for import.
	 * 
	 * Method declared public for test suite.
	 * 
	 * @return ProjectRecord[] array of projects that can be imported into the
	 * 	workspace
	 */
	public ProjectRecord[] getValidProjects() {
		List validProjects = new ArrayList();
		for (int i = 0; i < selectedProjects.length; i++) {
			if (!isProjectInWorkspace(selectedProjects[i].getProjectName())) {
				validProjects.add(selectedProjects[i]);
			}
		}
		return (ProjectRecord[]) validProjects
				.toArray(new ProjectRecord[validProjects.size()]);
	}

	/**
	 * Determine if the project with the given name is in the current workspace.
	 * 
	 * @param projectName
	 * 		String the project name to check
	 * @return boolean true if the project with the given name is in this
	 * 	workspace
	 */
	private boolean isProjectInWorkspace(String projectName) {
		if (projectName == null) {
			return false;
		}
		IProject[] workspaceProjects = getProjectsInWorkspace();
		for (int i = 0; i < workspaceProjects.length; i++) {
			if (projectName.equals(workspaceProjects[i].getName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retrieve all the projects in the current workspace.
	 * 
	 * @return IProject[] array of IProject in the current workspace
	 */
	private IProject[] getProjectsInWorkspace() {
		
		if (wsProjects == null) {
			wsProjects = ResourcesPlugin.getWorkspace().getRoot()
					.getProjects();
		}
		return wsProjects;
	}
	
	
	
	/**
	 * Create the selected projects
	 * 
	 * @return boolean <code>true</code> if all project creations were
	 * 	successful.
	 */
	public boolean createProjects() {
		//saveWidgetValues();
		final Object[] selected = projectsList.getCheckedElements();
		WorkspaceModifyOperation op = new WorkspaceModifyOperation() {
			protected void execute(IProgressMonitor monitor)
					throws InvocationTargetException, InterruptedException {
				try {
					monitor.beginTask("", selected.length); //$NON-NLS-1$
					if (monitor.isCanceled()) {
						throw new OperationCanceledException();
					}
					for (int i = 0; i < selected.length; i++) {
						createExistingProject((ProjectRecord) selected[i],
								new SubProgressMonitor(monitor, 1));
					}
				} finally {
					monitor.done();
				}
			}
		};
		// run the new project creation operation
		try {
			getContainer().run(true, true, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			// one of the steps resulted in a core exception
			Throwable t = e.getTargetException();
			String message = "Creation Problems";
			IStatus status;
			if (t instanceof CoreException) {
				status = ((CoreException) t).getStatus();
			} else {
				status = new Status(IStatus.ERROR,
						"org.eclipse.ui.ide", 1, message, t);
			}
			ErrorDialog.openError(getShell(), message, null, status);
			return false;
		}
		closeZipStructureProvider(structureProvider,getShell());
		return true;
	}
	
	
	/**
	 * Create the project described in record. If it is successful return true.
	 * 
	 * @param record
	 * @return boolean <code>true</code> if successful
	 * @throws InterruptedException
	 */
	private boolean createExistingProject(final ProjectRecord record,
			IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		String projectName = record.getProjectName();
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IProject project = workspace.getRoot().getProject(projectName);
		if (record.description == null) {
			// error case
			record.description = workspace.newProjectDescription(projectName);
			IPath locationPath = new Path(record.projectSystemFile
					.getAbsolutePath());

			// If it is under the root use the default location
			if (Platform.getLocation().isPrefixOf(locationPath)) {
				record.description.setLocation(null);
			} else {
				record.description.setLocation(locationPath);
			}
		} else {
			record.description.setName(projectName);
		}
		if (record.projectArchiveFile != null) {
			// import from archive
			List fileSystemObjects = structureProvider
					.getChildren(record.parent);
			structureProvider.setStrip(record.level);
			ImportOperation operation = new ImportOperation(project
					.getFullPath(), structureProvider.getRoot(),
					structureProvider, this, fileSystemObjects);
			operation.setContext(getShell());
			operation.run(monitor);
			return true;
		}
		
		// import from file system
		File importSource = null;
		// import project from location copying files - use default project
		// location for this workspace
		URI locationURI = record.description.getLocationURI();
		// if location is null, project already exists in this location or
		// some error condition occured.
		if (locationURI != null) {
			importSource = new File(locationURI);
			IProjectDescription desc = workspace
					.newProjectDescription(projectName);
			desc.setBuildSpec(record.description.getBuildSpec());
			desc.setComment(record.description.getComment());
			desc.setDynamicReferences(record.description
					.getDynamicReferences());
			desc.setNatureIds(record.description.getNatureIds());
			desc.setReferencedProjects(record.description
					.getReferencedProjects());
			record.description = desc;
		}

		try {
			monitor
					.beginTask("Creating Projects", 100);
			project.create(record.description, new SubProgressMonitor(monitor,
					30));
			project.open(IResource.BACKGROUND_REFRESH, new SubProgressMonitor(
					monitor, 70));
		} catch (CoreException e) {
			throw new InvocationTargetException(e);
		} finally {
			monitor.done();
		}

		// import operation to import project files if copy checkbox is selected
		if (importSource != null) {
			List filesToImport = FileSystemStructureProvider.INSTANCE
					.getChildren(importSource);
			ImportOperation operation = new ImportOperation(project
					.getFullPath(), importSource,
					FileSystemStructureProvider.INSTANCE, this, filesToImport);
			operation.setContext(getShell());
			operation.setOverwriteResources(true); // need to overwrite
			// .project, .classpath
			// files
			operation.setCreateContainerStructure(false);
			operation.run(monitor);
		}

		return true;
	}
	
	/**
	 * Display an error dialog with the specified message.
	 * 
	 * @param message
	 * 		the error message
	 */
	protected void displayErrorDialog(String message) {
		MessageDialog.openError(getContainer().getShell(),"Internal Error", message);
	}

	/**
	 * The <code>WizardDataTransfer</code> implementation of this
	 * <code>IOverwriteQuery</code> method asks the user whether the existing
	 * resource at the given path should be overwritten.
	 * 
	 * @param pathString
	 * @return the user's reply: one of <code>"YES"</code>, <code>"NO"</code>,
	 * 	<code>"ALL"</code>, or <code>"CANCEL"</code>
	 */
	public String queryOverwrite(String pathString) {

		Path path = new Path(pathString);

		String messageString;
		// Break the message up if there is a file name and a directory
		// and there are at least 2 segments.
		if (path.getFileExtension() == null || path.segmentCount() < 2) {
			messageString = pathString + " already exists. Would you like to overwrite it?";
		} else {
			messageString = "Overwrite " + path.lastSegment() + " in folder "+ path.removeLastSegments(1).toOSString() +" ?";
		}

		final MessageDialog dialog = new MessageDialog(getContainer()
				.getShell(), "Question" , null,
				messageString, MessageDialog.QUESTION, new String[] {
						IDialogConstants.YES_LABEL,
						IDialogConstants.YES_TO_ALL_LABEL,
						IDialogConstants.NO_LABEL,
						IDialogConstants.NO_TO_ALL_LABEL,
						IDialogConstants.CANCEL_LABEL }, 0);
		String[] response = new String[] { YES, ALL, NO, NO_ALL, CANCEL };
		// run in syncExec because callback is from an operation,
		// which is probably not running in the UI thread.
		getControl().getDisplay().syncExec(new Runnable() {
			public void run() {
				dialog.open();
			}
		});
		return dialog.getReturnCode() < 0 ? CANCEL : response[dialog
				.getReturnCode()];
	}
	
	
	/**
	 * Performs clean-up if the user cancels the wizard without doing anything
	 */
	public void performCancel() {
		if (structureProvider != null)
			closeZipStructureProvider(structureProvider, getShell());
	}
	
	/* ****************************************************************
	 * HANDLE ZIP FILES
	**************************************************************** */

	/**
	 * Determine whether the file with the given filename is in .zip or .jar
	 * format.
	 * 
	 * @param fileName
	 *            file to test
	 * @return true if the file is in tar format
	 */
	public static boolean isZipFile(String fileName) {
		if (fileName.length() == 0) {
			return false;
		}

		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(fileName);
		} catch (IOException ioException) {
			return false;
		} finally {
			if (zipFile != null) {
				try {
					zipFile.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}

		return true;
	}

	/**
	 * Closes the given structure provider.  Attempts to close the passed zip file. 
	 * 
	 * @param structureProvider
	 *            The structure provider to be closed, can be <code>null</code>				 
	 * @param shell
	 *            The shell to display any possible Dialogs in
	 */
	public static void closeZipStructureProvider(ZipStructureProvider structureProvider, Shell shell) {
			
			try {
				structureProvider.getZipFile().close();
			} catch (IOException e) {
				ErrorDialog.openError(shell, "Could not close file", e.getMessage(), new Status(IStatus.ERROR,
						SampleNewWizard.ID, 1, "Could not close file",e));

			}
	
	}
	
	
	/* ****************************************************************
	 * PROJECT RECORD
	**************************************************************** */
	
	/**
	 * Class declared public only for test suite.
	 * 
	 */
	public class ProjectRecord {
		File projectSystemFile;

		Object projectArchiveFile;

		String projectName;
		CommentParser comment;

		Object parent;

		int level;

		IProjectDescription description;

		/**
		 * Create a record for a project based on the info in the file.
		 * 
		 * @param file
		 */
		ProjectRecord(File file) {
			projectSystemFile = file;
			setProjectName();
		}

		/**
		 * @param file
		 * 		The Object representing the .project file
		 * @param parent
		 * 		The parent folder of the .project file
		 * @param level
		 * 		The number of levels deep in the provider the file is
		 */
		ProjectRecord(Object file, Object parent, int level) {
			this.projectArchiveFile = file;
			this.parent = parent;
			this.level = level;
			setProjectName();
		}

		/**
		 * Set the name of the project based on the projectFile.
		 */
		private void setProjectName() {
			try {
				if (projectArchiveFile != null) {
					InputStream stream = structureProvider
							.getContents(projectArchiveFile);

					// If we can get a description pull the name from there
					if (stream == null) {
						if (projectArchiveFile instanceof ZipEntry) {
							IPath path = new Path(
									((ZipEntry) projectArchiveFile).getName());
							projectName = path.segment(path.segmentCount() - 2);
						} 
//						else if (projectArchiveFile instanceof TarEntry) {
//							IPath path = new Path(
//									((TarEntry) projectArchiveFile).getName());
//							projectName = path.segment(path.segmentCount() - 2);
//						}
						comment = null;
					} else {
						description = ResourcesPlugin.getWorkspace().loadProjectDescription(stream);
						stream.close();
						projectName = description.getName();
						comment = new CommentParser(description.getComment());
					}

				}

				// If we don't have the project name try again
				if (projectName == null) {
					IPath path = new Path(projectSystemFile.getPath());
					// if the file is in the default location, use the directory
					// name as the project name
					if (isDefaultLocation(path)) {
						projectName = path.segment(path.segmentCount() - 2);
						description = ResourcesPlugin.getWorkspace()
								.newProjectDescription(projectName);
						comment = new CommentParser(description.getComment());
					} else {
						description = ResourcesPlugin.getWorkspace()
								.loadProjectDescription(path);
						projectName = description.getName();
						comment = new CommentParser(description.getComment());
					}

				}
			} catch (CoreException e) {
				// no good couldn't get the name
			} catch (IOException e) {
				// no good couldn't get the name
			}
		}

		/**
		 * Returns whether the given project description file path is in the
		 * default location for a project
		 * 
		 * @param path
		 * 		The path to examine
		 * @return Whether the given path is the default location for a project
		 */
		private boolean isDefaultLocation(IPath path) {
			// The project description file must at least be within the project,
			// which is within the workspace location
			if (path.segmentCount() < 2)
				return false;
			return path.removeLastSegments(2).toFile().equals(
					Platform.getLocation().toFile());
		}

		/**
		 * Get the name of the project
		 * 
		 * @return String
		 */
		public String getProjectName() {
			return projectName;
		}
		
		/**
		 * Get the description of the project
		 * 
		 * @return String
		 */
		public String getDescription() {
			return comment == null ? "" : comment.getDescription();
		}
		
		public List getRequirements() {
			return comment == null ? null : comment.getRequirements();
		}
		
		
		/**
		 * Gets the label to be used when rendering this project record in the
		 * UI.
		 * 
		 * @return String the label
		 * @since 3.4
		 */
		public String getProjectLabel() {
			
			return projectName;

		}
	}
	
}