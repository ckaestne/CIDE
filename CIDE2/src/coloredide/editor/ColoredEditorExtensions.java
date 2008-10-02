package coloredide.editor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IMenuManager;
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

import coloredide.editor.keepcolors.ColorCacheManager;
import coloredide.features.IFeature;
import coloredide.features.IFeatureModel;
import coloredide.features.source.ColoredSourceFile;
import coloredide.languages.LanguageExtensionManager;
import coloredide.languages.LanguageExtensionProxy;

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
	}
	public static interface IProjectionColoredEditor extends IColoredEditor {

		IProjectionColorManager getProjectionColorManager();
		
	}

	private IColoredEditor editor;

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
		String result = "Installed language extensions: \n";
		List<LanguageExtensionProxy> extensions = LanguageExtensionManager
				.getInstance().getLanguageExtensions();
		if (extensions.size() == 0)
			result += "\tnone.";

		for (LanguageExtensionProxy extension : extensions) {
			result += "\t- " + extension.getName() + " ("
					+ extension.printFileExtensions(", ") + ")\n";
		}

		return result;
	}
	
	public void fillContextMenu(IMenuManager menu) {
		if (!editor.isDirty()) {
			ColoredSourceFile sourceFile = editor.getSourceFile();
			ToggleTextColorContext context = new ToggleTextColorContext(
					sourceFile, editor.getSelectionProvider().getSelection());

			IFeatureModel fm = sourceFile.getFeatureModel();
			List<IFeature> visibleFeatures = new ArrayList<IFeature>(fm
					.getVisibleFeatures());
			Collections.sort(visibleFeatures);
			for (IFeature feature : visibleFeatures) {
				menu.add(new ToggleTextColorAction(context, feature));
			}
			menu.add(new ToggleAllFeatureSubmenu(context, fm.getFeatures()));
			menu.add(new NewFeatureAction(context,fm));
			
			if (editor instanceof IProjectionColoredEditor)
				menu.add(new ColorProjectionSubmenu((IProjectionColoredEditor)editor, context));
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

	public void initKeepColorManager() {
		keepColorManager = new ColorCacheManager(editor);
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
