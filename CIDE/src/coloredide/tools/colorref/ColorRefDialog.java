package coloredide.tools.colorref;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.internal.ui.text.correction.AssistContext;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jdt.ui.text.java.IInvocationContext;
import org.eclipse.jdt.ui.text.java.IProblemLocation;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

import coloredide.ColoredIDEPlugin;
import coloredide.features.Feature;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.tools.quickfix.ColorMarkerResolutionGenerator;
import coloredide.utils.EditorUtility;
import coloredide.validator.ColorProblem;

@SuppressWarnings("restriction")
public class ColorRefDialog extends Dialog {

	public class ProceedAction implements SelectionListener {

		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub

		}

		public void widgetSelected(SelectionEvent e) {
			idx++;
			updateButtons();
			updateDisplay();
		}

	}

	public class ColorAction implements SelectionListener {

		private ASTNode node;

		private IColoredJavaSourceFile source;

		private ITypeBinding binding;

		public ColorAction(IColoredJavaSourceFile source, ASTNode node,
				ITypeBinding binding) {
			this.node = node;
			this.source = source;
			this.binding = binding;
		}

		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub

		}

		public void widgetSelected(SelectionEvent e) {
			Set<Feature> nodeColor = source.getColorManager().getColors(node);
			Set<Feature> targetColor = ColoredIDEPlugin.getDefault().colorCache
					.getColors(source.getProject(), binding);
			Set<Feature> missingColors = new HashSet<Feature>();
			missingColors.addAll(targetColor);
			missingColors.removeAll(nodeColor);

			source.getColorManager().beginBatch();
			for (Feature color : missingColors) {
				source.getColorManager().addColor(node, color);
			}
			source.getColorManager().endBatch();
//			ASTViewPlugin.getDefault().notifyListeners(
//					new ColorChangedEvent(this, node, source));
		}

	}

	private ArrayList<IMarker> typeMissmatch; // @jve:decl-index=0:

	private Composite container;

	private Button nextBtn;

	private Button backBtn;

	private int idx = 0;

	private Composite display;

	protected ColorRefDialog(Shell parentShell,IResource resource) {
		super(parentShell);
		init(resource);
	}

	private void init(IResource resource) {
		try {
			IMarker[] markers = resource
					.findMarkers(ColorProblem.TYPEID, false,
							IResource.DEPTH_INFINITE);

			typeMissmatch = new ArrayList<IMarker>();
			for (IMarker marker : markers) {
				if (marker.getAttribute(ColorProblem.PARAM_PROBLEMTYPE, 0) == ColorProblem.TYPE
						.getID())
					typeMissmatch.add(marker);
			}

		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	protected Control createDialogArea(Composite parent) {
		container = (Composite) super.createDialogArea(parent);

		updateDisplay();

		return container;
	}

	
	private void updateDisplay() {
		if (display != null && !display.isDisposed())
			display.dispose();
		display = new Composite(container, SWT.NONE);
		display.setBackground(new Color(Display.getDefault(),
				new RGB(200, 0, 0)));
		display.setLayoutData(new GridData(GridData.FILL_BOTH));
		RowLayout layout = new RowLayout(SWT.VERTICAL);
		display.setLayout(layout);

		try {
			IMarker marker = typeMissmatch.get(idx);
			while (marker != null && !marker.exists()) {
				typeMissmatch.remove(marker);
				if (typeMissmatch.size() <= idx)
					idx--;
				if (idx < 0)
					marker = null;
				else
					marker = typeMissmatch.get(idx);
			}

			if (typeMissmatch.size() == 0) {
				new Label(display, SWT.NONE).setText("No type problems found");
				return;
			}

			new Label(display, SWT.NONE).setText("Type Problem " + (idx + 1)
					+ " of " + typeMissmatch.size());

			ICompilationUnit cu = ColorMarkerResolutionGenerator
					.getCompilationUnit(marker);
			if (cu != null) {
				IEditorInput input = EditorUtility.getEditorInput(cu);
				IEditorPart editor = JavaUI.openInEditor(cu);
				if (input != null) {
					IProblemLocation location = ColorMarkerResolutionGenerator
							.findProblemLocation(input, marker);
					if (location != null) {
//						List<IMarkerResolution> resolutions = new ArrayList<IMarkerResolution>();
						IInvocationContext context = new AssistContext(cu,
								location.getOffset(), location.getLength());
						ASTNode node = ColorMarkerResolutionGenerator.findNode(
								context, ColorMarkerResolutionGenerator
										.getASTId(marker));
						IColoredJavaSourceFile source = ColoredJavaSourceFile
								.getColoredJavaSourceFile(context
										.getCompilationUnit());

						if (node == null || source == null) {
							new Label(display, SWT.NONE)
									.setText("Problem not found");
							return;
						}

						ITypeBinding binding = null;
						if (node instanceof Type) {
							binding = ((Type) node).resolveBinding();
						}
						if (binding == null) {
							new Label(display, SWT.NONE)
									.setText("Binding cannot be resolved");
							return;
						}

						if (editor instanceof ITextEditor)
							((ITextEditor) editor).selectAndReveal(node
									.getStartPosition(), node.getLength());

						while (node != null) {
							Button b = new Button(display, SWT.PUSH);
							b.setText("Color "
									+ node.getClass().getSimpleName());
							b.setToolTipText(node.toString());
							b.addSelectionListener(new ColorAction(source,
									node, binding));
							b.addSelectionListener(new ProceedAction());
							node = node.getParent();
						}
					}
				}
			}
		} catch (Exception e) {
		} finally {
			display.pack(true);
			display.layout();
			container.pack();
			container.redraw();
		}

	}

	protected void createButtonsForButtonBar(Composite parent) {
		backBtn = createButton(parent, IDialogConstants.BACK_ID,
				IDialogConstants.BACK_LABEL, true);
		nextBtn = createButton(parent, IDialogConstants.NEXT_ID,
				IDialogConstants.NEXT_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);

		nextBtn.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			public void widgetSelected(SelectionEvent e) {
				idx++;
				updateButtons();
				updateDisplay();
			}
		});
		backBtn.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			public void widgetSelected(SelectionEvent e) {
				idx--;
				updateButtons();
				updateDisplay();
			}
		});

		updateButtons();
	}

	private void updateButtons() {
		nextBtn.setEnabled(typeMissmatch.size() > idx + 1);
		backBtn.setEnabled(idx > 0);
	}

	protected Point getInitialSize() {
		return new Point(500, 375);
	}

}
