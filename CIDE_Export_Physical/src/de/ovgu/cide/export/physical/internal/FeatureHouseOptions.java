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

	@Override
	public boolean getFlattenPackages() {
		return false;
	}

	@Override
	public boolean getMethodObjectsInStaticToplevelClass() {
		return false;
	}

	@Override
	public boolean getDerivativesInSubdirectories() {
		return derivativesInSubdirectories.getValue();
	}

	@Override
	public boolean isDebugPrintInnerClassRefinements() {
		return false;
	}

	@Override
	public CopiedNaiveASTFlattener getPrettyPrinter() {
		return new FSTComposerJavaPrettyPrinter(this);
	}

	@Override
	public String getFileHeader(String layerName) {
		return "";
	}

	@Override
	public String getRemovePackagePrefix() {
		return null;
	}

	@Override
	public String getFilename(String className) {
		return className + ".java";
	}

	@Override
	public List<IUserOption> getUserOptions() {
		List<IUserOption> result = new ArrayList<IUserOption>(2);
		result.add(derivativesInSubdirectories);
		return result;
	}
}
