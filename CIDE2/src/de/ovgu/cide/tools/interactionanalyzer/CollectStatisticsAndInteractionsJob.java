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

package de.ovgu.cide.tools.interactionanalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.ColoredSourceFileIteratorJob;

public class CollectStatisticsAndInteractionsJob extends
		ColoredSourceFileIteratorJob {

	public static class InteractionPosition {
		InteractionPosition(IASTNode node, IFile file) {
			start = node.getStartPosition();
			length = node.getLength();
			this.file = file;
			name = nodeToStr(node, file);
		}

		public IFile file;

		public int start, length;

		public String name;

		private static String nodeToStr(IASTNode node, IFile file) {
			return file.getName() + " " + node.getClass().getSimpleName()
					+ " (" + node.getStartPosition() + "-"
					+ (node.getStartPosition() + node.getLength()) + ")";
		}
	}

	public final Set<Set<IFeature>> interactions = new HashSet<Set<IFeature>>();

	// occurrences by feature set
	public final Map<Set<IFeature>, Set<InteractionPosition>> occurences = new HashMap<Set<IFeature>, Set<InteractionPosition>>();

	// occurrences by individual feature, may overlap
	public final Map<IFeature, Set<InteractionPosition>> occurencesByFeature = new HashMap<IFeature, Set<InteractionPosition>>();

	public final Set<IFeature> usedFeatures = new HashSet<IFeature>();

	public final Map<Derivative, Set<InteractionPosition>> derivatives = new HashMap<Derivative, Set<InteractionPosition>>();

	private Tree tree;

	private int annoationsCount = 0;

	private int classCount = 0;

	private int methodCount = 0;

	private int fieldCount = 0;

	public CollectStatisticsAndInteractionsJob(IProject project, Tree resultTree) {
		super(project, "Collecting Interactions",
				"Collecting Interactions from");
		this.tree = resultTree;
	}

	@Override
	protected void processSource(final ColoredSourceFile source)
			throws CoreException {
		try {
			ISourceFile ast;
			ast = source.getAST();

			ast.accept(new ASTVisitor() {
				/**
				 * collect derivative statistics
				 */
				public void postVisit(IASTNode node) {
					boolean hasOwnColors = source.getColorManager()
							.getOwnColors(node).size() > 0;
					if (hasOwnColors) {
						Set<IFeature> colors = source.getColorManager()
								.getColors(node);
						colors = cleanInteractions(colors);
						if (colors.size() > 1)
							interactions.add(colors);
						if (!colors.isEmpty())
							usedFeatures.addAll(colors);
						addOccurence(colors, node, source.getResource());
						addDerivative(node, source);
					}
					super.postVisit(node);
				}

				/**
				 * collect general statistics
				 * 
				 * annotation counts only if neither parent nor previous sibling
				 * already has it (exception: annotations on files always count
				 * as annotation)
				 */
				@Override
				public boolean visit(IASTNode node) {
					// own colors cleared by parent colors, in case they overlap
					Set<IFeature> ownColors = new HashSet<IFeature>(source
							.getColorManager().getOwnColors(node));
					if (node.getParent() != null)
						ownColors.removeAll(source.getColorManager().getColors(
								node.getParent()));
					else
						ownColors.addAll(source.getColorManager().getColors(
								node));
					if (!ownColors.isEmpty()) {
						IASTNode previousSibling = findPreviousSibling(node);
						if (previousSibling == null
								|| !sameColors(previousSibling, node))
							annoationsCount++;
					}

					return super.visit(node);
				}

				private IASTNode findPreviousSibling(IASTNode node) {
					IASTNode previousSibling = null;
					if (node.getLocationInParent() != null) {
						IASTNode[] siblings = node.getLocationInParent()
								.getChildren();
						for (IASTNode sibling : siblings) {
							if (sibling == node)
								break;
							previousSibling = sibling;
						}
					}
					return previousSibling;
				}

				private boolean sameColors(IASTNode previousSibling,
						IASTNode node) {
					return source
							.getColorManager()
							.getColors(node)
							.equals(source.getColorManager().getColors(
									previousSibling));
				}
			});
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void finish() {
		tree.getDisplay().syncExec(new Runnable() {

			public void run() {
				clearTree();
				printAllInteractions();
				printInteractionsByFeature();
				printInteractionNumberDevelopment();
				printDerivatives();
				printAllOccurrences();
				printAnnotationCount();
			}
		});
	}

	/**
	 * prints all occurrences of feature code. not related to interactions, but
	 * same collection process, therefore now own view yet.
	 */
	protected void printAllOccurrences() {
		TreeItem allOccurrences = new TreeItem(tree, SWT.DEFAULT);
		allOccurrences.setText("IFeature Code");
		for (IFeature f : sort(usedFeatures)) {
			createItem2(allOccurrences, f.getName(), occurencesByFeature.get(f));
		}
		setCountingCaption(allOccurrences);
	}

	/**
	 * prints general statistics about the number of annotations
	 */
	protected void printAnnotationCount() {
		TreeItem statisticsItem = new TreeItem(tree, SWT.DEFAULT);
		statisticsItem.setText("General Statistics");
		createItem2(statisticsItem, "Annoations: " + annoationsCount, null);
		createItem2(statisticsItem, "Classes: " + classCount, null);
		createItem2(statisticsItem, "Methods: " + methodCount, null);
		createItem2(statisticsItem, "Fields: " + fieldCount, null);

	}

	private void clearTree() {
		tree.removeAll();
	}

	private void printInteractionNumberDevelopment() {
		List<IFeature> features = sort(usedFeatures);
		Set<Set<IFeature>> leftInteractions = new HashSet<Set<IFeature>>(
				interactions);
		Set<IFeature> knownFeatures = new HashSet<IFeature>();
		int idx = 0, foundInteractions = 0;
		System.out.println("Stats:");
		for (IFeature feature : features) {
			idx++;
			knownFeatures.add(feature);
			for (Iterator<Set<IFeature>> iter = leftInteractions.iterator(); iter
					.hasNext();) {
				Set<IFeature> interaction = iter.next();
				if (knownFeatures.containsAll(interaction)) {
					foundInteractions++;
					iter.remove();
				}
			}
			System.out.println(idx + " (" + feature.getName() + ") - "
					+ foundInteractions);

		}
	}

	private void printAllInteractions() {
		TreeItem allInteractions = new TreeItem(tree, SWT.DEFAULT);
		allInteractions.setText("All interactions");
		for (Set<IFeature> inter : interactions) {
			String txt = getInteractionName(inter);
			createItem(allInteractions, txt, inter);
			System.out.println("[" + txt + "]");
		}
		setCountingCaption(allInteractions);
	}

	private void printDerivatives() {
		TreeItem allDerivatives = new TreeItem(tree, SWT.DEFAULT);
		allDerivatives.setText("All derivatives");
		ArrayList<Derivative> derivativeList = new ArrayList<Derivative>(
				derivatives.keySet());
		Collections.sort(derivativeList);
		for (Derivative d : derivativeList) {
			createItem2(allDerivatives, d.getDerivativeStr(),
					derivatives.get(d));
		}
		setCountingCaption(allDerivatives);
	}

	private void setCountingCaption(TreeItem i) {
		i.setText(i.getText() + " (" + i.getItemCount() + ")");
	}

	private void printInteractionsByFeature() {
		TreeItem allInteractions = new TreeItem(tree, SWT.DEFAULT);
		allInteractions.setText("Interactions by IFeature");
		for (IFeature f : sort(usedFeatures)) {
			TreeItem featureItem = createItem(allInteractions, f.getName(),
					null);
			for (Set<IFeature> inter : interactions)
				if (inter.contains(f))
					createItem(featureItem, getInteractionName(inter), inter);
			setCountingCaption(featureItem);
		}
		setCountingCaption(allInteractions);
	}

	private List<IFeature> sort(Set<IFeature> set) {
		ArrayList<IFeature> result = new ArrayList<IFeature>(set);
		Collections.sort(result);
		return result;
	}

	private String getInteractionName(Set<IFeature> inter) {
		String txt = "";
		boolean first = true;

		List<IFeature> l = sort(inter);
		for (IFeature f : l) {
			if (first)
				first = false;
			else
				txt += " - ";
			txt += f.getName();
		}
		return txt;
	}

	private TreeItem createItem(TreeItem parent, String text,
			Set<IFeature> interaction) {
		TreeItem r = new TreeItem(parent, SWT.DEFAULT);
		r.setText(text);
		if (interaction != null) {
			Set<InteractionPosition> occ = occurences.get(interaction);
			if (occ != null && !occ.isEmpty())
				for (InteractionPosition pos : occ) {
					TreeItem o = new TreeItem(r, SWT.DEFAULT);
					o.setText(pos.name);
					o.setData(pos);
				}
			setCountingCaption(r);
		}
		return r;
	}

	private TreeItem createItem2(TreeItem parent, String text,
			Set<InteractionPosition> occurences) {
		TreeItem r = new TreeItem(parent, SWT.DEFAULT);
		r.setText(text);
		if (occurences != null) {
			for (InteractionPosition pos : occurences) {
				TreeItem o = new TreeItem(r, SWT.DEFAULT);
				o.setText(pos.name);
				o.setData(pos);
			}
			setCountingCaption(r);
		}
		return r;
	}

	private void addOccurence(Set<IFeature> colors, IASTNode node, IFile file) {
		InteractionPosition p = new InteractionPosition(node, file);

		Set<InteractionPosition> occ = occurences.get(colors);
		if (occ == null) {
			occ = new HashSet<InteractionPosition>();
			occurences.put(colors, occ);
		}
		occ.add(p);

		for (IFeature color : colors) {
			occ = occurencesByFeature.get(color);
			if (occ == null) {
				occ = new HashSet<InteractionPosition>();
				occurencesByFeature.put(color, occ);
			}
			occ.add(p);
		}
	}

	private void addDerivative(IASTNode node, ColoredSourceFile source) {
		Derivative d = new TensorDerivative(node, source);
		Set<InteractionPosition> occ = derivatives.get(d);
		if (occ == null) {
			occ = new HashSet<InteractionPosition>();
			derivatives.put(d, occ);
		}
		occ.add(new InteractionPosition(node, source.getResource()));
	}

	private Set<IFeature> cleanInteractions(Set<IFeature> colors) {
		HashSet<IFeature> result = new HashSet<IFeature>(colors);
		for (IFeature f : colors) {
			Set<IFeature> parent = f.getRequiredFeatures();
			result.removeAll(parent);
		}
		return result;
	}
}
