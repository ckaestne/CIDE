package coloredide.astview;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import cide.gast.ASTNode;

public class TestPersistenceAction extends Action {
	private static Set<String> set = new HashSet<String>();

	private TreeViewer viewer;

	public TestPersistenceAction(TreeViewer viewer) {
		this.viewer = viewer;
	}

	private ASTNode getSelection() {
		IStructuredSelection selection = (IStructuredSelection) viewer
				.getSelection();
		for (Iterator iter = selection.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (element instanceof ASTNode) {
				return (ASTNode) element;
			}
		}
		return null;
	}

	public void update() {
		ASTNode node = getSelection();
		String id = "";
		if (node != null)
			id = node.getId();
		setText("set (" + id + ")");
		setEnabled(node != null);
		setChecked(isChecked(this, node));
	}

	public void run() {
		performAction(this, getSelection());
	}

	/**
	 * @see com.ibm.lab.soln.jdt.excerpt.ChangeIMemberFlagAction#performAction(IAction,
	 *      IMember)
	 */
	protected boolean performAction(IAction action, ASTNode member) {
		if (member == null)
			return false;
		String i = member.getId();
		if (set.contains(i)) {
			set.remove(i);
		} else {
			set.add(i);
		}
		return true;
	}

	/**
	 * @see com.ibm.lab.soln.jdt.excerpt.ChangeIMemberFlagAction#isChecked(IAction,
	 *      IMember)
	 */
	protected boolean isChecked(IAction action, ASTNode member) {
		return set.contains(member.getId());
	}
}
