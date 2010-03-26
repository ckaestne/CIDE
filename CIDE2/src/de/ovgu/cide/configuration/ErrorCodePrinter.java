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

package de.ovgu.cide.configuration;
/*package coloredide.configuration;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.dom.CompilationUnit;

import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.validator.ColorProblem;

public class ErrorCodePrinter extends HideColoredCodePrinter {

	private static class ProblemSeg {
		int begin;

		int end;

		String msg;

		ProblemSeg(int b, int e, String m) {
			begin = b;
			end = e;
			msg = m;
		}

		void merge(ProblemSeg first) {
			end = Math.max(end, first.end);
			msg = msg + "\n" + first.msg;
		}

		public String toString() {
			return "<problem " + begin + "-" + end + " : "
					+ msg.substring(0, 10) + ">";
		}
	}

	private Queue<ProblemSeg> problemQueue;

	protected String printCode(String buffer, CompilationUnit ast) {

		IFile file = ColoredJavaSourceFile.getResource(ast);
		IMarker[] markers = null;
		try {
			// only show errors when all features included, because otherwise
			// the position can move
			if (hiddenColors.isEmpty())
				markers = file.findMarkers(ColorProblem.TYPEID, true,
						IResource.DEPTH_ZERO);
		} catch (CoreException e) {
		}

		problemQueue = interpretMarkers(markers);

		return "<style type=\"text/css\">.error {color:red;text-weight:bold;text-decoration:underline}</style>"
				+ super.printCode(buffer, ast);
	}

	private Queue<ProblemSeg> interpretMarkers(IMarker[] markers) {
		LinkedList<ProblemSeg> result = new LinkedList<ProblemSeg>();
		List<ProblemSeg> tmp = new ArrayList<ProblemSeg>();

		if (markers != null)
			for (IMarker marker : markers) {
				int begin = marker.getAttribute(IMarker.CHAR_START, -1);
				int end = marker.getAttribute(IMarker.CHAR_END, -1);
				String msg = marker.getAttribute(IMarker.MESSAGE, "");
				if (begin >= 0 && end >= 0) {
					tmp.add(new ProblemSeg(begin, end, msg));
				}
			}
		while (!tmp.isEmpty()) {
			ProblemSeg first = tmp.get(0);
			for (ProblemSeg s : tmp) {
				if (s.begin < first.begin)
					first = s;
			}
			tmp.remove(first);
			ProblemSeg last = result.isEmpty() ? null : result.get(result
					.size() - 1);
			if (last != null && last.end > first.begin)
				last.merge(first);
			else
				result.add(first);
		}

		return result;
	}

	protected String printSegment(String buffer, CodeSegment seg) {
		// remove finished problems
		while (problemQueue.peek() != null
				&& problemQueue.peek().end <= seg.getOffset())
			problemQueue.poll();
		ProblemSeg firstProblem = problemQueue.peek();

		if (firstProblem == null || firstProblem.begin > seg.endPosition())
			return super.printSegment(buffer, seg);

		// first problem starts later
		if (firstProblem.begin > seg.getOffset()) {
			CodeSegment firstSeg = seg.copy();
			firstSeg.setEndPosition(firstProblem.begin);
			seg.moveStartPosition(firstProblem.begin);
			return super.printSegment(buffer, firstSeg)
					+ this.printSegment(buffer, seg);
		}
		// problem starts at the beginning of the segment

		// firstproblem goes beyond end
		if (firstProblem.end >= seg.endPosition()) {
			return openProblem(firstProblem) + super.printSegment(buffer, seg)
					+ closeProblem(firstProblem);
		}

		// problem closes inside
		CodeSegment firstSeg = seg.copy();
		firstSeg.setEndPosition(firstProblem.end);
		seg.moveStartPosition(firstProblem.end);
		return openProblem(firstProblem) + super.printSegment(buffer, firstSeg)
				+ closeProblem(firstProblem) + this.printSegment(buffer, seg);
	}

	private String closeProblem(ProblemSeg problem) {
		return "</span>";
	}

	private String openProblem(ProblemSeg problem) {
		return "<span class=\"error\" title=\"" + problem.msg + "\">";
	}
}
*/