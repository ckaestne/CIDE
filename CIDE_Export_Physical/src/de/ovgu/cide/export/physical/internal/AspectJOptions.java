package de.ovgu.cide.export.physical.internal;

import java.util.Collections;
import java.util.List;

import de.ovgu.cide.export.CopiedNaiveASTFlattener;
import de.ovgu.cide.export.useroptions.IUserOption;

public class AspectJOptions implements IPhysicalOptions {

	public boolean getDerivativesInSubdirectories() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getFileHeader(String layerName) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFilename(String className) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getFlattenPackages() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getMethodObjectsInStaticToplevelClass() {
		// TODO Auto-generated method stub
		return false;
	}

	public CopiedNaiveASTFlattener getPrettyPrinter() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRemovePackagePrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isDebugPrintInnerClassRefinements() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<IUserOption> getUserOptions() {
		return Collections.EMPTY_LIST;
	}

}
