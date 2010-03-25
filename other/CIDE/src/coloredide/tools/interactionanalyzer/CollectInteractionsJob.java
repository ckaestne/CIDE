package coloredide.tools.interactionanalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import coloredide.configuration.CodeSegment;
import coloredide.configuration.CodeSegmentCalculator;
import coloredide.features.Feature;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.validator.ColoredSourceFileIteratorJob;

public class CollectInteractionsJob extends ColoredSourceFileIteratorJob {

	public static class InteractionPosition {
		InteractionPosition(ASTNode node) {
			start = node.getStartPosition();
			length = node.getLength();
			name = nodeToStr(node);
			CompilationUnit astcompUnit = (CompilationUnit) node.getRoot();
			compUnit = (ICompilationUnit) astcompUnit.getJavaElement();
		}

		public ICompilationUnit compUnit;

		public int start, length;

		public String name;
	}

	public final Set<Set<Feature>> interactions = new HashSet<Set<Feature>>();

	// occurrences by feature set
	public final Map<Set<Feature>, Set<InteractionPosition>> occurences = new HashMap<Set<Feature>, Set<InteractionPosition>>();

	// occurrences by individual feature, may overlap
	public final Map<Feature, Set<InteractionPosition>> occurencesByFeature = new HashMap<Feature, Set<InteractionPosition>>();

	public final Set<Feature> usedFeatures = new HashSet<Feature>();

	public final Map<Derivative, Set<InteractionPosition>> derivatives = new HashMap<Derivative, Set<InteractionPosition>>();

	public int overallAnnotationCount = 0;
	public final Map<Feature, Integer> bytesPerFeature = new HashMap<Feature, Integer>();
	public int bytesUncolored = 0;
	public int bytesColored = 0;

	private Tree tree;
	private final static Set<Feature> NOCOLORS = Collections
			.unmodifiableSet(new HashSet<Feature>());

	public CollectInteractionsJob(IProject project, Tree resultTree) {
		super(project, "Collecting Interactions",
				"Collecting Interactions from");
		this.tree = resultTree;
	}

	@Override
	protected void processSource(final IColoredJavaSourceFile source)
			throws JavaModelException, CoreException {

		CompilationUnit ast = source.getAST();
		ast.accept(new ASTVisitor() {
			public void postVisit(ASTNode node) {
				boolean hasOwnColors = source.getColorManager().getOwnColors(
						node).size() > 0;
				if (hasOwnColors) {
					Set<Feature> colors = source.getColorManager().getColors(
							node);
					colors = cleanInteractions(colors);
					if (colors.size() > 1)
						interactions.add(colors);
					if (!colors.isEmpty())
						usedFeatures.addAll(colors);
					addOccurence(colors, node);
					addDerivative(node, source);
				}
				super.postVisit(node);
			}

		});
		List<CodeSegment> segments = CodeSegmentCalculator.getCodeSegments(ast,
				source.getColorManager());
		processSegments(segments, ast.getLength());
	}

	private void processSegments(List<CodeSegment> segments, int documentLength) {
		Set<Feature> currentColors = null;
		for (CodeSegment seg : segments) {
			if (currentColors != seg.getColors()) {
				// two adjacent annotations are counted as a single annotation
				overallAnnotationCount++;
			}
			currentColors = seg.getColors();
			if (currentColors != null && currentColors.size() > 0) {
				documentLength -= seg.getLength();
				for (Feature color : currentColors){
					int oldLength=0;
					if (bytesPerFeature.get(color)!=null)oldLength=bytesPerFeature.get(color).intValue();
					bytesPerFeature.put(color, oldLength
							+ seg.getLength());
				}
				bytesColored += seg.getLength();
			}

		}
		bytesUncolored += documentLength;
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
				printMiscStats();
			}

		});
	}

	/**
	 * prints statistics about overall number of interactions and annotated
	 * lines of code (per feature and alltogether)
	 */
	private void printMiscStats() {
		TreeItem misc = new TreeItem(tree, SWT.DEFAULT);
		misc.setText("Size");

		TreeItem annotations = new TreeItem(misc, SWT.DEFAULT);
		annotations.setText("#Annotations: "+overallAnnotationCount);

		TreeItem coloredBytes = new TreeItem(misc, SWT.DEFAULT);
		coloredBytes.setText("#Colored Bytes: "+bytesColored);
		TreeItem uncoloredBytes = new TreeItem(misc, SWT.DEFAULT);
		uncoloredBytes.setText("#Uncolored Bytes: "+bytesUncolored);
		for (Entry<Feature, Integer> e:bytesPerFeature.entrySet()){
			
			TreeItem featureBytes = new TreeItem(misc, SWT.DEFAULT);
			featureBytes.setText(e.getKey().getName(projects[0])+": "+e.getValue());
			
		}
			
	}

	/**
	 * prints all occurrences of feature code. not related to interactions, but
	 * same collection process, therefore now own view yet.
	 */
	protected void printAllOccurrences() {
		TreeItem allOccurrences = new TreeItem(tree, SWT.DEFAULT);
		allOccurrences.setText("Feature Code");
		for (Feature f : sort(usedFeatures)) {
			createItem2(allOccurrences, f.getShortName(projects[0]),
					occurencesByFeature.get(f));
		}
		setCountingCaption(allOccurrences);
	}

	private void clearTree() {
		tree.removeAll();
	}

	private void printInteractionNumberDevelopment() {
		List<Feature> features = sort(usedFeatures);
		Set<Set<Feature>> leftInteractions = new HashSet<Set<Feature>>(
				interactions);
		Set<Feature> knownFeatures = new HashSet<Feature>();
		int idx = 0, foundInteractions = 0;
		System.out.println("Stats:");
		for (Feature feature : features) {
			idx++;
			knownFeatures.add(feature);
			for (Iterator<Set<Feature>> iter = leftInteractions.iterator(); iter
					.hasNext();) {
				Set<Feature> interaction = iter.next();
				if (knownFeatures.containsAll(interaction)) {
					foundInteractions++;
					iter.remove();
				}
			}
			System.out.println(idx + " (" + feature.getShortName(projects[0])
					+ ") - " + foundInteractions);

		}
	}

	private void printAllInteractions() {
		TreeItem allInteractions = new TreeItem(tree, SWT.DEFAULT);
		allInteractions.setText("All interactions");
		for (Set<Feature> inter : interactions) {
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
			createItem2(allDerivatives, d.getDerivativeStr(), derivatives
					.get(d));
		}
		setCountingCaption(allDerivatives);
	}

	private void setCountingCaption(TreeItem i) {
		i.setText(i.getText() + " (" + i.getItemCount() + ")");
	}

	private void printInteractionsByFeature() {
		TreeItem allInteractions = new TreeItem(tree, SWT.DEFAULT);
		allInteractions.setText("Interactions by Feature");
		for (Feature f : sort(usedFeatures)) {
			TreeItem featureItem = createItem(allInteractions, f
					.getShortName(projects[0]), null);
			for (Set<Feature> inter : interactions)
				if (inter.contains(f))
					createItem(featureItem, getInteractionName(inter), inter);
			setCountingCaption(featureItem);
		}
		setCountingCaption(allInteractions);
	}

	private List<Feature> sort(Set<Feature> set) {
		ArrayList<Feature> result = new ArrayList<Feature>(set);
		Collections.sort(result);
		return result;
	}

	private String getInteractionName(Set<Feature> inter) {
		String txt = "";
		boolean first = true;

		List<Feature> l = sort(inter);
		for (Feature f : l) {
			if (first)
				first = false;
			else
				txt += " - ";
			txt += f.getShortName(projects[0]);
		}
		return txt;
	}

	private TreeItem createItem(TreeItem parent, String text,
			Set<Feature> interaction) {
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

	private static String nodeToStr(ASTNode node) {
		CompilationUnit compUnit = (CompilationUnit) node.getRoot();
		ICompilationUnit icomp = (ICompilationUnit) compUnit.getJavaElement();
		return icomp.getResource().getName() + " "
				+ node.getClass().getSimpleName() + " ("
				+ node.getStartPosition() + "-" + node.getStartPosition()
				+ node.getLength() + ")";
	}

	private void addOccurence(Set<Feature> colors, ASTNode node) {
		InteractionPosition p = new InteractionPosition(node);

		Set<InteractionPosition> occ = occurences.get(colors);
		if (occ == null) {
			occ = new HashSet<InteractionPosition>();
			occurences.put(colors, occ);
		}
		occ.add(p);

		for (Feature color : colors) {
			occ = occurencesByFeature.get(color);
			if (occ == null) {
				occ = new HashSet<InteractionPosition>();
				occurencesByFeature.put(color, occ);
			}
			occ.add(p);
		}
	}

	private void addDerivative(ASTNode node, IColoredJavaSourceFile source) {
		Derivative d = new TensorDerivative(node, source);
		Set<InteractionPosition> occ = derivatives.get(d);
		if (occ == null) {
			occ = new HashSet<InteractionPosition>();
			derivatives.put(d, occ);
		}
		occ.add(new InteractionPosition(node));
	}

	private Set<Feature> cleanInteractions(Set<Feature> colors) {
		HashSet<Feature> result = new HashSet<Feature>(colors);
		for (Feature f : colors) {
			Set<Feature> parent = f.getRequiredFeatures(projects[0]);
			result.removeAll(parent);
		}
		return result;
	}
}
