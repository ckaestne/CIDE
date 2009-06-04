package coloredide.export2jak;

public class JakExportOptions {

	static {
		resetDefaultFST();
	}

	public static boolean DERIVATIVES_IN_SUBDIRECTORIES;

	public static boolean DEBUG_PRINTINNERCLASSREFINEMENTS;

	public static OutputType OUTPUTTYPE;

	public enum OutputType {
		JAK, FST_JAVA
	};

	/**
	 * if true, than method objects are refactored into static top-level
	 * classes, i.e., a this pointer is passed and own files are created.
	 * 
	 * otherwise method objects are just created and refined as non-static inner
	 * classes
	 */
	public static boolean METHODOBJECTS_IN_STATICTOPLEVELCLASS;

	/**
	 * move all classes to top level classes (necessary for AHEAD)
	 */
	public static boolean FLATTEN_PACKAGES;

	/**
	 * if all classes are moved to top level classes, remove all import
	 * declarations that start with this prefix (null if none), e.g.
	 * "com.sleepycat"
	 */
	public static String REMOVE_PACKAGES_PREFIX;

	private static void resetDefaultBoth() {
		DERIVATIVES_IN_SUBDIRECTORIES = false;
		DEBUG_PRINTINNERCLASSREFINEMENTS = false;
		REMOVE_PACKAGES_PREFIX = "";
	}

	public static void resetDefaultFST() {
		resetDefaultBoth();
		OUTPUTTYPE = OutputType.FST_JAVA;
		METHODOBJECTS_IN_STATICTOPLEVELCLASS = false;
		FLATTEN_PACKAGES = false;
	}

	public static void resetDefaultAHEAD() {
		resetDefaultBoth();
		OUTPUTTYPE = OutputType.JAK;
		METHODOBJECTS_IN_STATICTOPLEVELCLASS = true;
		FLATTEN_PACKAGES = true;
	}

}
