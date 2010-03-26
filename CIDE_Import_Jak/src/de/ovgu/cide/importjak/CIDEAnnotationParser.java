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

package de.ovgu.cide.importjak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import de.ovgu.cide.editor.SelectionFinder;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.utils.StrUtils;

public class CIDEAnnotationParser implements IResourceVisitor {

	class AnnotationMarker {

		private int startPosition;
		private int endPosition;
		Collection<String> orFeatures;

		public AnnotationMarker(int startPosition, Collection<String> orFeatures) {
			this.startPosition = startPosition;
			this.orFeatures = orFeatures;
			endPosition = -1;
		}

		@Override
		public String toString() {
			return startPosition + "-" + endPosition + ": " + orFeatures;
		}

		public Set<IFeature> getColors(IFeatureModel featureModel) {
			Set<IFeature> features = featureModel.getFeatures();
			Set<IFeature> result = new HashSet<IFeature>();
			List<String> orFeature = new ArrayList<String>(orFeatures);
			Collections.sort(orFeature);

			for (IFeature feature : features) {
				if (feature.getName().equals(
						JakImporter.getOrFeatureStr(orFeature)))

					result.add(feature);
			}

			assert !features.isEmpty() : "No feature found, should be "
					+ orFeature;

			return result;
		}
	}

	private IProgressMonitor monitor;

	public CIDEAnnotationParser(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	public boolean visit(IResource resource) throws CoreException {
		if (resource instanceof IFile) {
			if (monitor != null)
				monitor.subTask("Importing annotations: " + resource.getName());
			try {
				ColoredSourceFile csFile = ColoredSourceFile
						.getColoredSourceFile((IFile) resource);

				if (csFile.isColored())
					loadAnnotations(csFile);

			} catch (FeatureModelNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	private void loadAnnotations(final ColoredSourceFile csFile)
			throws CoreException {
		System.out.println(csFile);

		try {
			String content = StrUtils.strFromInputStream(csFile.getResource()
					.getContents(true));

			final List<AnnotationMarker> annotations = findAnnotations(content);

			final SourceFileColorManager cm = csFile.getColorManager();
			cm.beginBatch();
			ISourceFile ast = csFile.getAST();

			for (AnnotationMarker annotation : annotations) {

				List<IASTNode> nodes = new ArrayList<IASTNode>();
				ast.accept(new SelectionFinder(nodes, annotation.startPosition,
						annotation.endPosition - annotation.startPosition,
						false));

				Set<IFeature> colors = annotation.getColors(csFile
						.getFeatureModel());
				for (IASTNode node : nodes)
					cm.setColors(node, colors);
			}

			cm.endBatch();

			String newContent = stripTextualAnnotations(content);

			newContent = prettyPrint(newContent);

			csFile.getResource().setContents(
					StrUtils.strToInputStream(newContent), true, true, null);

		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String prettyPrint(String content) {
		CodeFormatter formatter = ToolFactory.createCodeFormatter(JavaCore
				.getOptions(), ToolFactory.M_FORMAT_EXISTING);

		TextEdit edit = formatter.format(CodeFormatter.K_COMPILATION_UNIT,
				content, 0, content.length(), 0, null);
		IDocument document = new Document(content);
		try {
			if (edit != null)
				edit.apply(document);
		} catch (MalformedTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return document.get();
	}

	private String stripTextualAnnotations(String content) {
		return content.replaceAll("/\\*IF\\[.*?\\]\\*/", "").replaceAll(
				"/\\*ENDIF\\[.*?\\]\\*/", "");
	}

	List<AnnotationMarker> findAnnotations(String content) {
		Stack<AnnotationMarker> annotationStack = new Stack<AnnotationMarker>();
		final List<AnnotationMarker> annotations = new LinkedList<AnnotationMarker>();
		int offset = 0;
		int ifdefOffset = content.indexOf("/*IF");
		int endifOffset = content.indexOf("/*ENDIF");

		while (ifdefOffset >= 0 || endifOffset >= 0) {
			if (ifdefOffset >= 0 && ifdefOffset < endifOffset) {
				annotationStack.push(new AnnotationMarker(ifdefOffset + offset,
						parseFeatures(content, ifdefOffset)));
				content = content.substring(ifdefOffset + 1);
				offset += ifdefOffset + 1;
			}
			if (ifdefOffset < 0 || endifOffset < ifdefOffset) {
				AnnotationMarker start = annotationStack.pop();
				start.endPosition = findEnd(content, endifOffset) + offset;
				annotations.add(0, start);
				content = content.substring(endifOffset + 1);
				offset += endifOffset + 1;
			}
			ifdefOffset = content.indexOf("/*IF");
			endifOffset = content.indexOf("/*ENDIF");
		}
		System.out.println(annotations);
		return annotations;
	}

	private int findEnd(String content, int endifOffset) {
		return endifOffset + content.substring(endifOffset).indexOf("]*/") + 4;
	}

	Collection<String> parseFeatures(String content, int ifdefOffset) {
		content = content.substring(ifdefOffset);
		content = content.substring(content.indexOf('[') + 1, content
				.indexOf(']'));

		String[] features = content.split(" \\| ");

		return Arrays.asList(features);
	}

}
