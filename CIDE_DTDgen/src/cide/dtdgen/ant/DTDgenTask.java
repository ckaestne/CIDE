package cide.dtdgen.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import cide.dtdgen.DTDgen;

public class DTDgenTask extends Task {

	private String dtdInputFile;
	private String gCIDEOutputFile;

	public void execute() throws BuildException {
		DTDgen m = new DTDgen(new File(dtdInputFile), new File(gCIDEOutputFile));
		try {
			m.run();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new BuildException(e);
		}
	}

	public void setDtdInputFile(String g) {
		this.dtdInputFile = g;
	}

	public void setGCIDEOutputFile(String td) {
		this.gCIDEOutputFile = td;
	}

}
