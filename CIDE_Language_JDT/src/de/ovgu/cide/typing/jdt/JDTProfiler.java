package de.ovgu.cide.typing.jdt;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.compiler.BuildContext;
import org.eclipse.jdt.core.compiler.CompilationParticipant;

public class JDTProfiler extends CompilationParticipant {

	private long start;

	public JDTProfiler() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isActive(IJavaProject project) {
		return true;
	}

	@Override
	public void buildFinished(IJavaProject project) {
		super.buildFinished(project);
		System.out.println("Compiled " + project + " in "
				+ (System.currentTimeMillis() - start) + " ms");
	}

	@Override
	public void buildStarting(BuildContext[] files, boolean isBatch) {
		start = System.currentTimeMillis();
		super.buildStarting(files, isBatch);
	}
}
