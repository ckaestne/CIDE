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

package cide.astgen.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import cide.astgen.Main;

public class AstgenTask extends Task {

	private String grammarFileName;
	private String targetDirectory;
	private String targetPackage;

	public void execute() throws BuildException {
		Main m = new Main(grammarFileName.substring(0, grammarFileName
				.lastIndexOf('.')));
		try {
			m.runGenerator(grammarFileName, new File(targetDirectory),
					targetPackage);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new BuildException(e);
		}
	}

	public void setGrammarFileName(String g) {
		this.grammarFileName = g;
	}

	public void setTargetDirectory(String td) {
		this.targetDirectory = td;
	}

	public void setTargetPackage(String tp) {
		this.targetPackage = tp;
	}

}
