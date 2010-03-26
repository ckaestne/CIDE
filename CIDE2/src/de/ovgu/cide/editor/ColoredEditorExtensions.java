/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

package de.ovgu.cide.editor;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.texteditor.IDocumentProvider;

import de.ovgu.cide.editor.inlineprojection.IProjectionColorManager;
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

	/**
	 * functionality to be provided by all editors
	 * 
	 * @author ckaestne
	 * 
	 */
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

	/**
	 * not all editors may support projections/views. if they do, they must
	 * implement IProjectionColoredEditor in addition to IColoredEditor
	 * 
	 * @author ckaestne
	 * 
	 */
	public static interface IProjectionColoredEditor extends IColoredEditor {
		IProjectionColorManager getProjectionColorManager();

		IAction getToggleProjectionAction();
	}

	private IColoredEditor editor;

	// private AlternativeAnnotationManager altAnnotationManager;

	public ColoredEditorExtensions(IColoredEditor editor) {
		this.editor = editor;
	}

	private Text errorLabel;
	private Control mainControl;
	private String errDetails;
	ColorCacheManager keepColorManager;
	private Button detailsBtn;
	private Text errorState;
	private static Color RED = Display.getCurrent().getSystemColor(
			SWT.COLOR_RED);
	private static Color GREEN = Display.getCurrent().getSystemColor(
			SWT.COLOR_GREEN);
	private static Color YELLOW = Display.getCurrent().getSystemColor(
			SWT.COLOR_YELLOW);
	private static Color LIGHTRED = Display.getCurrent().getSystemColor(
			SWT.COLOR_INFO_BACKGROUND);

	public void createErrorPanel(final Composite parent) {
		GridLayout layout = new GridLayout(
				editor instanceof IProjectionColoredEditor ? 4 : 3, false);
		layout.verticalSpacing = 4;
		layout.marginWidth = layout.marginHeight = 0;
		parent.setLayout(layout);

		errorState = new Text(parent, SWT.READ_ONLY);
		errorState.setText("");
		GridData esD = new GridData(5, 5);
		esD.horizontalIndent = esD.verticalIndent = 4;
		errorState.setLayoutData(esD);

		errorLabel = new Text(parent, SWT.READ_ONLY);
		errorLabel.setText("Errormessage goes here");
		GridData elD = new GridData(GridData.FILL_HORIZONTAL);
		elD.grabExcessHorizontalSpace = true;
		errorLabel.setLayoutData(elD);

		detailsBtn = new Button(parent, SWT.PUSH);
		detailsBtn.setText("Details");

		if (editor instanceof IProjectionColoredEditor) {
			new ActionContributionItem(((IProjectionColoredEditor) editor)
					.getToggleProjectionAction()).fill(parent);

		}
		updateErrorPanel();
	}

	public void alignErrorPanel(Composite parent) {
		mainControl = parent.getChildren()[0];

		GridData data = new GridData(GridData.FILL_VERTICAL);
		data.horizontalSpan = editor instanceof IProjectionColoredEditor ? 4
				: 3;
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.verticalAlignment = SWT.FILL;
		data.horizontalAlignment = SWT.FILL;
		mainControl.setLayoutData(data);

		parent.layout();
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
			errorState.setBackground(errClr);
			errorLabel.update();
			errorState.update();
			detailsBtn
					.setEnabled(errDetails != null && errDetails.length() > 0);
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
		return editor.getDocumentProvider().getAnnotationModel(
				editor.getEditorInput());
	}

	// public AlternativeAnnotationManager getAltAnnotationManager() {
	// // return altAnnotationManager;
	// }

	public void fillContextMenu(IMenuManager menu) {
		if (!editor.isDirty()) {
			ColoredSourceFile sourceFile = editor.getSourceFile();

			if (sourceFile != null) {
				SelectionActionsContext context = new SelectionActionsContext(
						sourceFile, editor.getSelectionProvider()
								.getSelection(), this, !sourceFile
								.alternativesArePossible());
				IFeatureModel fm = sourceFile.getFeatureModel();

				if (context.canColorNodes()) {
					List<IFeature> visibleFeatures = new ArrayList<IFeature>(fm
							.getVisibleFeatures());
					Collections.sort(visibleFeatures);

					if (ToggleTextColorAction.lastFeature != null)
						menu.add(new ToggleLastColorAction(context,
								ToggleTextColorAction.lastFeature));
					for (IFeature feature : visibleFeatures) {
						menu.add(new ToggleTextColorAction(context, feature));
					}

					menu.add(new ToggleAllFeatureSubmenu(context, fm
							.getFeatures()));
					menu.add(new NewFeatureAction(context, fm));
				}

				if (editor instanceof IProjectionColoredEditor)
					menu.add(new ColorProjectionSubmenu(
							(IProjectionColoredEditor) editor, context));

				if (context.canCreateAlternatives()) {
					MenuManager mm = new MenuManager("Alternative code");
					menu.add(mm);

					// // Eine Alternative soll nur dann angelegt werden können,
					// wenn das aktive Codefragment min. eine Farbe hat,
					// // die es nicht von einem Elternknoten erbt.
					// if (context.nodesHaveNonInheritedColors())
					// mm.add(new CreateAlternativeAction(context));
					//					
					// mm.add(new SwitchAlternativeSubmenu(context));
				}
			}
		}
	}

	public void installAlternativeAnnotations() {
		// altAnnotationManager = new
		// AlternativeAnnotationManager(getAnnotationModel());
		// try {
		// if (editor.getSourceFile().isColored())
		// altAnnotationManager.setAnnotations(editor.getSourceFile().getAltFeatureManager().getAlternativeNodesWithActiveParent());
		// } catch (CoreException e) {
		// markCoreException(e);
		// } catch (ParseException e) {
		// markParseException(e);
		// }
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
