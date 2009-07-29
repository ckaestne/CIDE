package de.ovgu.cide.export.physical.internal;

import java.util.ArrayList;
import java.util.List;

import de.ovgu.cide.export.CopiedNaiveASTFlattener;
import de.ovgu.cide.export.physical.ahead.FSTComposerJavaPrettyPrinter;
import de.ovgu.cide.export.useroptions.BooleanUserOption;
import de.ovgu.cide.export.useroptions.IUserOption;

public class FeatureHouseOptions implements IPhysicalOptions {

	BooleanUserOption derivativesInSubdirectories = new BooleanUserOption(
			"Derivatives in subdirectories", true);

	public boolean getFlattenPackages() {
		return false;
	}

	public boolean getMethodObjectsInStaticToplevelClass() {
		return false;
	}

	public boolean getDerivativesInSubdirectories() {
		return derivativesInSubdirectories.getValue();
	}

	public boolean isDebugPrintInnerClassRefinements() {
		return false;
	}

	public CopiedNaiveASTFlattener getPrettyPrinter() {
		return new FSTComposerJavaPrettyPrinter(this);
	}

	public String getFileHeader(String layerName) {
		return "";
	}

	public String getRemovePackagePrefix() {
		return null;
	}

	public String getFilename(String className) {
		return className + ".java";
	}

	public List<IUserOption> getUserOptions() {
		List<IUserOption> result = new ArrayList<IUserOption>(2);
		result.add(derivativesInSubdirectories);
		return result;
	}
}
