package de.ovgu.cide.export.physical.internal;

import java.util.ArrayList;
import java.util.List;

import de.ovgu.cide.export.CopiedNaiveASTFlattener;
import de.ovgu.cide.export.physical.ahead.JakPrettyPrinter;
import de.ovgu.cide.export.useroptions.BooleanUserOption;
import de.ovgu.cide.export.useroptions.IUserOption;
import de.ovgu.cide.export.useroptions.StringUserOption;

public class JakOptions implements IPhysicalOptions {

	BooleanUserOption derivativesInSubdirectories = new BooleanUserOption("Derivatives in subdirectories",true);
	StringUserOption packagePrefix = new StringUserOption("Packages (prefix) to remove","");

	@Override
	public boolean getFlattenPackages() {
		return true;
	}

	@Override
	public boolean getMethodObjectsInStaticToplevelClass() {
		return true;
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
	public String getFileHeader(String layerName) {
		return "layer " + layerName + ";\n\n";

	}

	@Override
	public String getRemovePackagePrefix() {
		return packagePrefix.getValue();
	}

	@Override
	public CopiedNaiveASTFlattener getPrettyPrinter() {
		return new JakPrettyPrinter(this);
	}

	@Override
	public String getFilename(String className) {
		return className + ".jak";
	}

	@Override
	public List<IUserOption> getUserOptions() {
		List<IUserOption> result = new ArrayList<IUserOption>(2);
		result.add(derivativesInSubdirectories);
		result.add(packagePrefix);
		return result;
	}
}
