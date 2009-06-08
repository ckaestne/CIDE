package de.ovgu.cide.editor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;

import cide.gparser.ParseException;

import de.ovgu.cide.af.AlternativeAnnotationManager;
import de.ovgu.cide.editor.keepcolors.ColorCacheManager;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.languages.LanguageExtensionManager;
import de.ovgu.cide.languages.LanguageExtensionProxy;

/**
 * this class encapsulates all code that is needed by multiple colored editors
 * (e.g., the colored java editor, the colored text editor, etc)
 * 
 * this includes creating the context menu and the status-bar
 * 
 * @author ckaestne
 * 
 */
public class ColoredEditorExtensions {

	public static interface IColoredEditor {
		ColoredSourceFile getSourceFile();

		IFeatureModel getFeatureModel();

		boolean isDirty();

		ISourceViewer getSourceViewerR();

		ISelectionProvider getSelectionProvider();
		
		IDocumentProvider getDocumentProvider();
		
		IDocument getDocument();
		
		IEditorInput getEditorInput();
		
		void doSave(IProgressMonitor progressMonitor);
	}

	public static interface IProjectionColoredEditor extends IColoredEditor {
		IProjectionColorManager getProjectionColorManager();
	}

	private IColoredEditor editor;
	private AlternativeAnnotationManager altAnnotationManager;

	public ColoredEditorExtensions(IColoredEditor editor) {
		this.editor = editor;
	}

	private Text errorLabel;
	private Control mainControl;
	private String errDetails;
	ColorCacheManager keepColorManager;
	private static Color RED = Display.getCurrent().getSystemColor(
			SWT.COLOR_RED);
	private static Color GREEN = Display.getCurrent().getSystemColor(
			SWT.COLOR_GREEN);
	private static Color YELLOW = Display.getCurrent().getSystemColor(
			SWT.COLOR_YELLOW);
	private static Color LIGHTRED = Display.getCurrent().getSystemColor(
			SWT.COLOR_INFO_BACKGROUND);

	public void createErrorPanel(final Composite parent) {
		final FormLayout fl = new FormLayout();
		fl.spacing = 4;
		parent.setLayout(fl);

		errorLabel = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP
				| SWT.READ_ONLY);
		errorLabel.setText("Errormessage goes here");
		// errorPanel.setLayout(new FillLayout(SWT.HORIZONTAL));

		FormData erFD = new FormData(-1, 50);
		erFD.right = new FormAttachment(100, 0);
		erFD.left = new FormAttachment(0, 0);
		erFD.top = new FormAttachment(0, 0);
		errorLabel.setLayoutData(erFD);

		updateErrorPanel();
	}

	public void markFileEdited() {
		errClr = (LIGHTRED);
		errMsg = ("File edited in editor. Colors disabled until file is saved again.");
		errDetails = "";
		updateErrorPanel();
	}

	public void markNotColored() {
		errClr = (YELLOW);
		errMsg = ("Cannot color file. No language extension for file type installed.");
		errDetails = getSupportedLanguageExtensionsMsg();
		updateErrorPanel();
	}

	public void markNoFeatureModel() {
		errClr = (YELLOW);
		errMsg = ("Cannot color file. No feature model provided.");
		errDetails = "Install feature model plugin!";
		updateErrorPanel();
	}

	public void markCoreException(CoreException e) {
		errClr = (RED);
		errMsg = ("CoreException: " + e.getMessage());

		errDetails = getStackTrace(e);
		updateErrorPanel();
	}

	public void markASTOk(long time) {
		errClr = (GREEN);
		errMsg = "Parsing successful (" + time + " ms).";
		errDetails = "";
		updateErrorPanel();
	}

	public void markParseException(Throwable e) {
		errClr = (RED);
		errMsg = ("ParseException: " + e.getMessage());
		errDetails = getStackTrace(e);
		updateErrorPanel();
	}

	private String getStackTrace(Throwable e) {
		StringWriter w = new StringWriter();
		e.printStackTrace(new PrintWriter(w));
		return w.getBuffer().toString();
	}

	private String errMsg = "";
	private Color errClr = GREEN;

	private void updateErrorPanel() {
		if (errorLabel != null && !errorLabel.isDisposed()) {
			errorLabel.setText(errMsg
					+ (errDetails == null || errDetails.length() == 0 ? ""
							: "\n\n" + errDetails));
			errorLabel.setBackground(errClr);
			errorLabel.update();
		}
	}

	private String getSupportedLanguageExtensionsMsg() {
		String result = "Enabled language extensions: \n";
		List<LanguageExtensionProxy> extensions = LanguageExtensionManager
				.getInstance().getEnabledLanguageExtensions();
		if (extensions.size() == 0)
			result += "\tnone.";

		for (LanguageExtensionProxy extension : extensions) {
			result += "\t- " + extension.getName() + " ("
					+ extension.printFileExtensions(", ") + ")\n";
		}

		return result;
	}
	
	public IDocument getDocument() {
		return editor.getDocument();
	}
	
	public IAnnotationModel getAnnotationModel() {
		return editor.getDocumentProvider().getAnnotationModel(editor.getEditorInput());
	}
	
	public AlternativeAnnotationManager getAltAnnotationManager() {
		return altAnnotationManager;
	}
	
	public void fillContextMenu(IMenuManager menu) {
		if (!editor.isDirty()) {
			ColoredSourceFile sourceFile = editor.getSourceFile();
			
			if (sourceFile != null) {
				SelectionActionsContext context = 
					new SelectionActionsContext(sourceFile, editor.getSelectionProvider().getSelection(), this, !sourceFile.alternativesArePossible());
				IFeatureModel fm = sourceFile.getFeatureModel();
				
				if (context.canColorNodes()) {
					List<IFeature> visibleFeatures = new ArrayList<IFeature>(fm.getVisibleFeatures());
					Collections.sort(visibleFeatures);

					for (IFeature feature : visibleFeatures) {
						menu.add(new ToggleTextColorAction(context, feature));
					}

					menu.add(new ToggleAllFeatureSubmenu(context, fm.getFeatures()));
					menu.add(new NewFeatureAction(context, fm));
				}

				if (editor instanceof IProjectionColoredEditor)
					menu.add(new ColorProjectionSubmenu((IProjectionColoredEditor) editor, context));
				
				if (context.canCreateAlternatives()) {
					MenuManager mm = new MenuManager("Alternative code");
					menu.add(mm);

					// Eine Alternative soll nur dann angelegt werden können, wenn das aktive Codefragment min. eine Farbe hat,
					// die es nicht von einem Elternknoten erbt.
					if (context.nodesHaveNonInheritedColors())
						mm.add(new CreateAlternativeAction(context));
					
					mm.add(new SwitchAlternativeSubmenu(context));
				}
			}
		}
	}

	public void alignErrorPanel(Composite parent) {
		mainControl = parent.getChildren()[0];
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(errorLabel);
		data.bottom = new FormAttachment(100, 0);
		mainControl.setLayoutData(data);

		parent.layout();
	}
	
	public void installAlternativeAnnotations() {
		altAnnotationManager = new AlternativeAnnotationManager(getAnnotationModel());		
		try {
			if (editor.getSourceFile().isColored())
			altAnnotationManager.setAnnotations(editor.getSourceFile().getAltFeatureManager().getAlternativeNodesWithActiveParent());
		} catch (CoreException e) {
			markCoreException(e);
		} catch (ParseException e) {
			markParseException(e);
		}
	}
	
	public ColorCacheManager getColorCacheManager() {
		return keepColorManager;
	}

	public void initKeepColorManager() {
		keepColorManager = new ColorCacheManager(editor);
	}
	
	public void invalidateTextPresentation() {
		editor.getSourceViewerR().invalidateTextPresentation();
	}
	
	public void save() {
		editor.doSave(null);
	}

	public void afterSave(boolean wasDirty) {
		if (wasDirty && !editor.isDirty()) {
			editor.getSourceFile().refreshAST();
			keepColorManager.reassignColors();
			editor.getSourceViewerR().invalidateTextPresentation();
			// update the AST views
		}
	}
}
