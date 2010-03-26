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

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Tree;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.ColoredSourceFileIteratorJob;
import de.ovgu.cide.utils.StrUtils;

public class CollectPageStatistics extends ColoredSourceFileIteratorJob {

	private static final int LINES_PER_PAGE = 50;
	int globalStats[];

	public CollectPageStatistics(IProject project, Tree tree) {
		super(project, "collect page statistics", "pagestat");
		globalStats = new int[6];
	}

	private final JTextArea textArea = new JTextArea();

	@SuppressWarnings("unchecked")
	@Override
	protected void processSource(final ColoredSourceFile source)
			throws CoreException {

		try {
			textArea.setText(StrUtils.strFromInputStream(source.getResource()
					.getContents()));
			int lines = textArea.getLineCount();
			int pages = (int) Math.ceil((double) lines
					/ (double) (LINES_PER_PAGE / 2));
			final HashSet<IFeature> featuresOnPage[] = new HashSet[pages];

			ISourceFile ast = source.getAST();

			ast.accept(new ASTVisitor() {
				@Override
				public boolean visit(IASTNode node) {
					Set<IFeature> nodeColor = source.getColorManager()
							.getColors(node);
					if (nodeColor.size() > 0)
						try {
							int startLine = textArea.getLineOfOffset(node
									.getStartPosition());
							int endLine = textArea.getLineOfOffset(node
									.getStartPosition()
									+ node.getLength());

							addFeaturesToPages(startLine, endLine, nodeColor);
						} catch (BadLocationException e) {
							e.printStackTrace();

						}
					return super.visit(node);
				}

				private void addFeaturesToPages(int startLine, int endLine,
						Set<IFeature> nodeColor) {
					for (int i = 0; i < featuresOnPage.length; i++) {
						int pageStart = i * (LINES_PER_PAGE / 2);
						int pageEnd = pageStart + LINES_PER_PAGE;
						if (startLine < pageEnd && endLine >= pageStart) {
							HashSet<IFeature> pageColors = featuresOnPage[i];
							if (pageColors == null)
								featuresOnPage[i] = pageColors = new HashSet<IFeature>();
							pageColors.addAll(nodeColor);
						}
					}
				}

			});

			int stats[] = new int[6];
			for (int i = 0; i < featuresOnPage.length; i++) {
				HashSet<IFeature> pageColors = featuresOnPage[i];
				if (pageColors == null)
					stats[0]++;
				else
					stats[colorsToP(pageColors.size())]++;
			}
			print(source, stats);
			addToGlobal(stats);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void finish() {
		print(null, globalStats);
		super.finish();
	}

	private void addToGlobal(int[] stats) {
		for (int i = 0; i < stats.length; i++) {
			globalStats[i] += stats[i];
		}
	}

	private void print(ColoredSourceFile source, int[] stats) {
		if (source != null)
			System.out.print(source.getName() + ": ");
		for (int i = 0; i < stats.length; i++) {
			System.out.print(stats[i] + " ");
		}
		System.out.println();
	}

	private int colorsToP(int size) {
		if (size <= 4)
			return size;
		if (size <= 7)
			return 4;
		return 5;
	}
}
