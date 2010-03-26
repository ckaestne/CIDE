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

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.javacc.parser.Main;

public class JavaCCTask extends Task {

	private String grammarFileName;
	private String targetDirectory;

	public void execute() throws BuildException {
		int result = 0;
		try {
			result = Main.mainProgram(new String[] {
					"-OUTPUT_DIRECTORY:" + targetDirectory, grammarFileName });
		} catch (Exception e) {
			e.printStackTrace();
			throw new BuildException(e);
		}
		if (result != 0)
			throw new BuildException("Build failed with errorcode " + result);
	}

	public void setGrammarFileName(String g) {
		this.grammarFileName = g;
	}

	public void setTargetDirectory(String td) {
		this.targetDirectory = td;
	}

}
