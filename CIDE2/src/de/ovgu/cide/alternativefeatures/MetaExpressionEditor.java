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

package de.ovgu.cide.alternativefeatures;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * * NOTE: the entire functionality for alternative features was implemented as
 * part of a master's thesis (available in German here:
 * http://wwwiti.cs.uni-magdeburg.de/~ckaestne/thesisrosenthal.pdf) the
 * functionality has been deactivated mostly, but the code is still included.
 * 
 */
public class MetaExpressionEditor {

	private Shell sShell = null; // @jve:decl-index=0:visual-constraint="-40,50"
	private Text alt1 = null;
	private Text alt2 = null;
	private Label defaultLabel = null;
	private Label defaultLabel1 = null;
	private Label defaultLabel2 = null;
	private Button okbutton = null;
	private Button cancelbutton = null;
	private Button newaltbutton = null;
	private Text text1 = null;
	private Text text2 = null;
	private Button selectbutton1 = null;
	private Button selectbutton2 = null;
	private Button delbutton1 = null;
	private Button delbutton2 = null;
	private Composite composite = null;
	private Button newbutton = null;
	private Composite composite1 = null;
	private Button button = null;
	private Button button1 = null;
	private Text text = null;
	private Label label = null;
	private Label label1 = null;
	private StyledText text12 = null;
	private Label label2 = null;
	private Text text13 = null;
	private Text text14 = null;
	private Button button2 = null;
	private Button button3 = null;
	private Button button4 = null;
	private Button button5 = null;
	private StyledText styledText = null;

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		sShell = new Shell();
		sShell.setText("Meta-Expression for addEdge in Graph.java");
		sShell.setLayout(gridLayout);
		createComposite();
		sShell.setSize(new Point(706, 376));
		newbutton = new Button(sShell, SWT.NONE);
		newbutton.setText("New Alternative");
		createComposite1();

	}

	/**
	 * This method initializes composite
	 * 
	 */
	private void createComposite() {
		GridData gridData3 = new GridData();
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData3.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData3.heightHint = 80;
		gridData3.widthHint = -1;
		gridData3.grabExcessVerticalSpace = true;
		GridData gridData6 = new GridData();
		gridData6.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData5 = new GridData();
		gridData5.grabExcessVerticalSpace = false;
		gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData5.grabExcessHorizontalSpace = true;
		GridData gridData4 = new GridData();
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData4.heightHint = 80;
		gridData4.grabExcessVerticalSpace = true;
		gridData4.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData4.widthHint = -1;
		GridData gridData2 = new GridData();
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.heightHint = 80;
		gridData2.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.widthHint = -1;
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 5;
		GridData gridData = new GridData();
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		composite = new Composite(sShell, SWT.NONE);
		composite.setLayoutData(gridData);
		composite.setLayout(gridLayout2);
		text = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		text.setEnabled(false);
		text.setFont(new Font(Display.getDefault(), "Courier New", 8,
				SWT.NORMAL));
		text
				.setText("start.addAdjacent(end);\nend.addAdjacent(start);// undirected\nstart.setWeight(weight);\nend.addWeight(weight);\nreturn (EdgeIfc) start;");
		text.setLayoutData(gridData2);
		label = new Label(composite, SWT.NONE);
		label.setText("default");
		Label filler = new Label(composite, SWT.NONE);
		Label filler1 = new Label(composite, SWT.NONE);
		Label filler2 = new Label(composite, SWT.NONE);
		styledText = new StyledText(composite, SWT.BORDER | SWT.V_SCROLL);
		styledText.setFont(new Font(Display.getDefault(), "Courier New", 8,
				SWT.NORMAL));
		styledText
				.setText("Neighbor e = new Neighbor(end, weight);\naddEdge(start, e);\nreturn e;");
		styledText.setLayoutData(gridData3);
		label1 = new Label(composite, SWT.NONE);
		label1.setText("Feature(s):");
		text13 = new Text(composite, SWT.BORDER);
		text13.setEnabled(true);
		text13.setEditable(true);
		text13.setBackground(new Color(Display.getCurrent(), 196, 128, 196));
		text13.setLayoutData(gridData6);
		text13.setText("NeightborObjects");
		button2 = new Button(composite, SWT.NONE);
		button2.setText("Select...");
		button4 = new Button(composite, SWT.NONE);
		button4.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("/delete_icon.gif")));
		text12 = new StyledText(composite, SWT.BORDER | SWT.V_SCROLL);
		text12.setFont(new Font(Display.getDefault(), "Courier New", 8,
				SWT.NORMAL));
		text12
				.setText("Edge theEdge = new Edge(start, end, weight);\nedges.add(theEdge);\nstart.addNeighbor(new Neighbor(end, theEdge));\nend.addNeighbor(new Neighbor(start, theEdge));// undirected\nreturn theEdge;");
		text12.setLayoutData(gridData4);
		label2 = new Label(composite, SWT.NONE);
		label2.setText("Feature(s):");
		text14 = new Text(composite, SWT.BORDER);
		text14.setText("EdgeObjects");
		text14.setEnabled(true);
		text14.setEditable(true);
		text14.setBackground(new Color(Display.getCurrent(), 255, 196, 128));
		text14.setLayoutData(gridData5);
		button3 = new Button(composite, SWT.NONE);
		button3.setText("Select...");
		button5 = new Button(composite, SWT.NONE);
		button5.setImage(new Image(Display.getCurrent(), getClass()
				.getResourceAsStream("/delete_icon.gif")));
	}

	/**
	 * This method initializes composite1
	 * 
	 */
	private void createComposite1() {
		GridData gridData1 = new GridData();
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 2;
		composite1 = new Composite(sShell, SWT.NONE);
		composite1.setLayout(gridLayout1);
		composite1.setLayoutData(gridData1);
		button = new Button(composite1, SWT.NONE);
		button.setText("OK");
		button1 = new Button(composite1, SWT.NONE);
		button1.setText("Cancel");
	}

	public static void main(String[] args) throws InterruptedException {
		MetaExpressionEditor mee = new MetaExpressionEditor();
		mee.createSShell();
		StyleRange style = new StyleRange(36, 6, null, new Color(Display
				.getDefault(), new RGB(128, 128, 196)));
		mee.text12.setStyleRange(style);
		StyleRange style2 = new StyleRange(31, 6, null, new Color(Display
				.getDefault(), new RGB(128, 128, 196)));
		mee.styledText.setStyleRange(style2);
		mee.sShell.open();
		while (!mee.sShell.isDisposed()) {
			if (!mee.sShell.getDisplay().readAndDispatch())
				mee.sShell.getDisplay().sleep();
		}
		mee.sShell.getDisplay().dispose();
	}
}
