package coloredide.export;

import java.util.ArrayList;
import java.util.List;

import coloredide.export2AspectJ.AspectJExportFactory;
import coloredide.export2jak.JakExportFactory;
import coloredide.export2pp.PPExportFactory;

public class ExportFactoryProvider {

	private final static List<ExportFactory> factories = new ArrayList<ExportFactory>();

	public static void addExportFactory(ExportFactory f) {
		factories.add(f);
	}

	public static ExportFactory[] getFactories() {
		return factories.toArray(new ExportFactory[factories.size()]);
	}
	
	
	static {
		  addExportFactory(new AspectJExportFactory());
		  addExportFactory(new JakExportFactory());
		  addExportFactory(new PPExportFactory());
	}

}
