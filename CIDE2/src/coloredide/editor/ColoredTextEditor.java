package coloredide.editor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;

import cide.gast.ASTNode;
import cide.gparser.ParseException;
import coloredide.CIDECorePlugin;
import coloredide.ASTColorChangedEvent;
import coloredide.ColorListChangedEvent;
import coloredide.ColoredIDEImages;
import coloredide.FileColorChangedEvent;
import coloredide.IColorChangeListener;
import coloredide.ColorListChangedEvent.ChangeType;
import coloredide.editor.inlineprojection.InlineProjectionSourceViewer;
import coloredide.editor.inlineprojection.InlineProjectionSupport;
import coloredide.editor.keepcolors.ColorCacheManager;
import coloredide.features.Feature;
import coloredide.features.FeatureManager;
import coloredide.features.source.ColoredSourceFile;
import coloredide.languages.LanguageExtensionManager;
import coloredide.languages.LanguageExtensionProxy;
import coloredide.utils.EditorUtility;

public class ColoredTextEditor extends AbstractDecoratedTextEditor implements
		IColorChangeListener {

	public static final String EDITOR_ID = "coloredIDE.ColoredCompilationUnitEditor";
	// private Composite errorPanel;
	private Text errorLabel;
	private Control mainControl;
	private String errDetails;
	private ColorCacheManager keepColorManager;
	private static Color RED = Display.getCurrent().getSystemColor(
			SWT.COLOR_RED);
	private static Color GREEN = Display.getCurrent().getSystemColor(
			SWT.COLOR_GREEN);
	private static Color YELLOW = Display.getCurrent().getSystemColor(
			SWT.COLOR_YELLOW);
	private static Color LIGHTRED = Display.getCurrent().getSystemColor(
			SWT.COLOR_INFO_BACKGROUND);

	public ColoredSourceFile getSourceFile() {
		IFile file = EditorUtility.getFileInput(this);
		return ColoredSourceFile.getColoredSourceFile(file);
	}

	@Override
	protected ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int styles) {
		InlineProjectionSourceViewer viewer = new InlineProjectionSourceViewer(
				parent, ruler, getOverviewRuler(), isOverviewRulerVisible(),
				styles);

		return viewer;
	}

	protected IProject getProject() {
		IFile file = EditorUtility.getFileInput(this);
		return file.getProject();
	}

	@Override
	public void editorContextMenuAboutToShow(IMenuManager menu) {
		if (!isDirty()) {
			ColoredSourceFile sourceFile = getSourceFile();
			ToggleTextColorContext context = new ToggleTextColorContext(
					sourceFile, this.getSelectionProvider().getSelection());

			List<Feature> visibleFeatures = FeatureManager
					.getVisibleFeatures(this.getProject());
			for (Feature feature : visibleFeatures) {
				menu.add(new ToggleTextColorAction(context, feature));
			}
			menu.add(new ToggleAllFeatureSubmenu(context, FeatureManager
					.getFeatures()));

			menu.add(new ColorProjectionSubmenu(this, context));
		}

		super.editorContextMenuAboutToShow(menu);
	}

	@Override
	public Image getTitleImage() {
		Class x = ColoredSourceFile.class;

		Image t = ColoredIDEImages.getImage(ColoredIDEImages.COLOREDJ);
		if (t != null)
			return t;
		else
			return super.getTitleImage();
	}

	@Override
	public void dispose() {
		CIDECorePlugin.getDefault().removeColorChangeListener(this);

		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {

		setSourceViewerConfiguration(new ColoredSourceViewerConfiguration(
				getSourceFile(), this));

		super.createPartControl(parent);

		// projection
		InlineProjectionSupport projectionSupport = new InlineProjectionSupport(
				getSourceViewerI(), getAnnotationAccess(), getSharedColors());
		projectionSupport.install();
		getSourceViewerI().doOperation(ProjectionViewer.TOGGLE);
		getSourceViewerI().disableProjection();
		getSourceViewerI().enableInlineProjection();

		// error panel
		createErrorPanel(parent);

		mainControl = parent.getChildren()[0];
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(errorLabel);
		data.bottom = new FormAttachment(100, 0);
		mainControl.setLayoutData(data);

		parent.layout();

		CIDECorePlugin.getDefault().addColorChangeListener(this);

		// color caches
		keepColorManager = new ColorCacheManager(this);
	}

	public InlineProjectionSourceViewer getSourceViewerI() {
		return (InlineProjectionSourceViewer) getSourceViewer();
	}

	private void createErrorPanel(final Composite parent) {
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

	public void astColorChanged(ASTColorChangedEvent event) {
		IDocument doc = getSourceViewer().getDocument();
		if (event.getColoredSourceFile() != getSourceFile())
			return;

		if (!(getSourceViewer() instanceof ITextViewerExtension2))
			getSourceViewer().invalidateTextPresentation();
		else {

			int offset = doc.getLength();
			int endOffset = 0;
			for (ASTNode node : event.getAffectedNodes()) {
				offset = Math.min(offset, node.getStartPosition());
				endOffset = Math.max(endOffset, node.getStartPosition()
						+ node.getLength());
			}
			if (offset < endOffset) {
				int length = endOffset - offset;
				((ITextViewerExtension2) getSourceViewer())
						.invalidateTextPresentation(offset, length);
			}
		}
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		boolean wasDirty = isDirty();
		super.doSave(progressMonitor);

		if (wasDirty && !isDirty()) {
			getSourceFile().refreshAST();
			keepColorManager.reassignColors();
			getSourceViewer().invalidateTextPresentation();
			// update the AST views
		}
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
	private ProjectionColorManager projectionColorManager;

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

	public ProjectionColorManager getProjectionColorManager() {
		if (projectionColorManager == null)
			projectionColorManager = new ProjectionColorManager(this);
		return projectionColorManager;
	}

	public void fileColorChanged(FileColorChangedEvent event) {
		// update entire file of color of this file has changed
		IContainer folder = getSourceFile().getResource().getParent();
		while (folder != null) {
			if (event.getAffectedFolders().contains(folder)) {
				asyncInvalidateTextRepresentation();
				break;
			}
			folder = folder.getParent();
		}
	}

	private void asyncInvalidateTextRepresentation() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				getSourceViewer().invalidateTextPresentation();
			}
		});
	}

	public void colorListChanged(ColorListChangedEvent event) {
		// redraw on color or visibility changes in the feature model
		if (event.anyChangeOf(ChangeType.COLOR)
				|| event.anyChangeOf(ChangeType.VISIBILITY))
			asyncInvalidateTextRepresentation();
	}
}
