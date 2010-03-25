package coloredide.tools.colorref;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;

public class ColorRefAction extends Action {

//	private ToggleTextColorContext context;
//	private ITypeBinding binding;
	private IResource resource;


	public ColorRefAction(IResource resource) {
		this.setText("Color References...");
		if (resource==null)
			resource = ResourcesPlugin.getWorkspace().getRoot();
		this.resource=resource;

//		this.context = context;
//
//		Set<ASTNode> nodes = context.getSelectedNodes();
//		if (nodes.size() != 1) {
//			this.setEnabled(false);
//			return;
//		}
//		ASTNode node = nodes.iterator().next();
//		if (node instanceof TypeDeclaration){
//			ITypeBinding binding = ((TypeDeclaration)node).resolveBinding();
//			if (binding instanceof TypeBinding)
//				this.binding=binding;
//			else return;
//		}else return;
	}

	@Override
	public void run() {
		ColorRefDialog dialog = new ColorRefDialog(Display.getCurrent()
				.getActiveShell(),resource);
		dialog.open();
	}

}
