package de.ovgu.cide.export.physical.internal;

import java.util.ArrayList;
import java.util.List;

import de.ovgu.cide.export.CopiedNaiveASTFlattener;
import de.ovgu.cide.export.physical.ahead.JakPrettyPrinter;
import de.ovgu.cide.export.useroptions.BooleanUserOption;
import de.ovgu.cide.export.useroptions.IUserOption;
import de.ovgu.cide.export.useroptions.StringUserOption;

public class JakOptions implements IPhysicalOptions {

	BooleanUserOption derivativesInSubdirectories = new BooleanUserOption(
			"Derivatives in subdirectories", true);
	StringUserOption packagePrefix = new StringUserOption(
			"Packages (prefix) to remove", "");

	public boolean getFlattenPackages() {
		return true;
	}

	public boolean getMethodObjectsInStaticToplevelClass() {
		return true;
	}

	public boolean getDerivativesInSubdirectories() {
		return derivativesInSubdirectories.getValue();
	}

	public boolean isDebugPrintInnerClassRefinements() {
		return false;
	}

	public String getFileHeader(String layerName) {
		return "layer " + layerName + ";\n\n";

	}

	public String getRemovePackagePrefix() {
		return packagePrefix.getValue();
	}

	public CopiedNaiveASTFlattener getPrettyPrinter() {
		return new JakPrettyPrinter(this);
	}

	public String getFilename(String className) {
		return className + ".jak";
	}

	public List<IUserOption> getUserOptions() {
		List<IUserOption> result = new ArrayList<IUserOption>(2);
		result.add(derivativesInSubdirectories);
		result.add(packagePrefix);
		return result;
	}
}
