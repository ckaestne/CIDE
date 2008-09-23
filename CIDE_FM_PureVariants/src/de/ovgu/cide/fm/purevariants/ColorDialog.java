/*
 * Created on Feb 15, 2005
 *
 * Copyright (c) 2005 pure-systems GmbH
 * All rights reserved
 */
package de.ovgu.cide.fm.purevariants;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import coloredide.utils.ColorHelper;

import com.ps.consul.eclipse.core.Modeling;
import com.ps.consul.eclipse.core.internal.model.gen.api.IConstantAPI;
import com.ps.consul.eclipse.core.model.IPVElement;
import com.ps.consul.eclipse.core.model.IPVProperty;
import com.ps.consul.eclipse.core.model.IPVValue;
import com.ps.consul.eclipse.core.model.IProperty;
import com.ps.consul.eclipse.core.model.ModelConstants;
import com.ps.consul.eclipse.ui.synchronised.SyncModelUpdater;
import com.ps.xml.ID;

public class ColorDialog extends Dialog {

	// all textfields
	IPVElement m_Parent; // parent feature if any
	IPVElement m_Current; // feature to process or null

	// IPVRelation m_Relation;

	Composite m_MainComposite;
	private Text nameField;
	private RGB currentColor;
	private Text colorField;

	/**
	 * Create the dialog object and initialize the input parameter.
	 * 
	 * @param shell
	 *            The parent shell.
	 * @param parent
	 *            The parent feature.
	 * @param current
	 *            The feature to process or null for create a new feature.
	 */
	ColorDialog(Shell shell, IPVElement parent, IPVElement current) {
		super(shell);
		shell.setRedraw(true);
		setShellStyle(shell.getStyle() | SWT.RESIZE | SWT.MAX);

		m_Parent = parent;
		m_Current = current;
		
		
	}

	// create layout data for single line controls
	// private GridData colLayoutData(int num) {
	// GridData data = new GridData();
	// data.horizontalAlignment = GridData.FILL;
	// data.horizontalSpan = num;
	// data.grabExcessHorizontalSpace = true;
	// return data;
	// }

	private void load() {
		if (m_Current==null)return;
		
		nameField.setText(m_Current.getVName());
//		currentColor=
			
			IPVProperty prop = m_Current.getPropertyWithName("Color");
			IPVValue val = prop.getFirstValue();
			IConstantAPI c = (IConstantAPI)val;
			currentColor=ColorHelper.str2rgb(c.getContent());
		
	}

	/**
	 * create the area of the dialog box.
	 * <p>
	 * If we wanna put content on our dialog we have to use this method. It
	 * gives us a composite where we can put our gui stuff like buttons,
	 * textfields... onto. you can return the same component with your stuff
	 * added as the control.
	 * </p>
	 */
	protected Control createDialogArea(Composite main) {

		m_MainComposite = main;
		Composite parent = new Composite(main, SWT.RESIZE);
		GridData data = new GridData(GridData.FILL_BOTH);
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		parent.setLayoutData(data);
		parent.setLayout(new GridLayout(3, false));

		// name: text
		// color: selector

		Label nameCaption = new Label(parent, SWT.NONE);
		nameCaption.setText("Name:");
		nameField = new Text(parent, SWT.SINGLE + SWT.BORDER);

		GridData d = new GridData();
		d.horizontalSpan = 2;
		d.grabExcessHorizontalSpace = true;
		nameField.setData(d);
		nameField.setEnabled(true);
		new Label(parent, SWT.NONE);

		Label colorCaption = new Label(parent, SWT.NONE);
		colorCaption.setText("Color:");
		colorField = new Text(parent, SWT.SINGLE + SWT.BORDER + SWT.READ_ONLY);
		setColor(currentColor);
		Button colorButton = new Button(parent, SWT.PUSH);
		colorButton.setText("Select");
		colorButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				org.eclipse.swt.widgets.ColorDialog cd = new org.eclipse.swt.widgets.ColorDialog(
						getShell());
				cd.setRGB(currentColor);
				RGB newColor = cd.open();
				if (newColor != null)
					setColor(newColor);
			}
		});

		d = new GridData();
		d.grabExcessHorizontalSpace = true;
		colorField.setData(d);
		d = new GridData();
		d.widthHint = 5;
		colorButton.setData(d);
		
		load();
		
		parent.pack();

		return parent;
	}

	protected void setColor(RGB newColor) {
		currentColor = newColor;
		colorField.setText(ColorHelper.rgb2str(currentColor));
	}

	protected void okPressed() {
		if (m_Current != null) {
			/*
			 * If we have a current feature we update the feature with the new
			 * data.
			 */
			updateFeature();
		} else {
			/*
			 * We have no feature so we create a new one.
			 */
			createFeature();
		}
		super.okPressed();
	}

	private void createFeature() {
		/*
		 * All operation on models is done by the server. So we need to create
		 * the server message. The communication is done in XML. At the first
		 * step we setup a new XML document by calling createDocument() of the
		 * Modeling class. The Modeling class provides all necessary methods to
		 * compose server messages. <b>You are not allowed to create the plain
		 * XML messages by yourself. </b>
		 */
		Document doc = Modeling.createDocument();
		Node cmdList = Modeling.createCmdList(doc, m_Parent.getModelContainer()
				.getID());
		/*
		 * Create the AddElement part of the message. We set the parent id and
		 * children relation typ so the server will automaticly connect the
		 * added element with its parent. For the relation typ we use the
		 * defined constants of the ModelConstants interface.
		 */
		Node addmsg = Modeling.createAddElementMsg(doc, m_Parent
				.getModelContainer().getID(), m_Parent.getID(),
				ModelConstants.MANDATORY_TYPE);
		cmdList.appendChild(addmsg);
		/*
		 * Create the element to add. The createElement() method builds the XML
		 * structure for the element. The element id is created by the
		 * constructor of the ID class. The class of the added element is
		 * "ps:feature" (FEATURE_CLASS). As type we use our private typ
		 * "ps:simplefeature".
		 */
		ID id = new ID();
		Node elem = Modeling.createElement(doc, id, nameField.getText(),
				ModelConstants.PART_CLASS, "ps:color", null);

		// elem = m_RestrictionPane.createRestriction(cmdList, elem, false,
		// m_FeaturePane.getUniqueName());

		/*
		 * We create the nodes for visible name,description and unique name and
		 * append them under the createelement message
		 */
		Node vname = Modeling.createVName(doc, nameField.getText(), null);
		// Node desc = Modeling.createDesc(doc, m_FeaturePane.getDescription(),
		// null);
		elem.appendChild(vname);
		// elem.appendChild(desc);

		/*
		 * Now we call the createRelation method to append our relation node if
		 * we added a relation. the false meens, that we're not in update mode.
		 * This is important because we have different server messages for
		 * create and update.
		 */
		// m_RelationPane.createRelation(elem, false);
		/*
		 * Same like createRelation but for an Attribute ;)
		 */
		createProperty(elem, false);

		/*
		 * After creating the feature XML structure we have to add it to the
		 * AddElement message structure.
		 */
		addmsg.appendChild(elem);

		try {
			/*
			 * To update the model the SyncModelUpdater class is used. This
			 * class perfoms the call to the server and waits for the replay. By
			 * setting "true" as third argument we get a wait pointer during the
			 * server communication.
			 */
			SyncModelUpdater.updateModel(m_Parent.getModelContainer(), cmdList,
					true);

		} catch (CoreException e) {
			MessageDialog.openError(getShell(), "Create Feature",
					"Failed to create new feature.\n\n" + e.getMessage());
		}
	}

	/**
	 * puts a createproperty message on the given Node to tell the server that
	 * we want to add a property to the current feature. The property name is
	 * "TestAttribute" and the value you can type in on the dialog.
	 * 
	 * @param elem
	 * @param update
	 *            update the property or really create it.
	 */
	public void createProperty(Node elem, boolean update) {
		Document doc = elem.getOwnerDocument();

		Node chprop;
		if (!update) {
			chprop = Modeling.createPropertiesNode(doc);
		} else {
			chprop = Modeling.createAddPropertyMsg(doc);
		}
		Node prop = Modeling.createProperty(doc, new ID(), "Color",
				ModelConstants.ATTRIBUTES_INTEGER_TYPE, new Boolean(false),
				new Boolean(false), false, null);
		chprop.appendChild(prop);
		Node attr = Modeling.createConstant(doc, new ID(), null, ColorHelper
				.rgb2str(currentColor), null);
		prop.appendChild(attr);

		// elem.appendChild(chprop);

		elem.appendChild(chprop);

	}

	/**
	 * update a feature.
	 * <p>
	 * this method creates the server message to update an existing feature.
	 *</p>
	 */
	private void updateFeature() {
		Document doc = Modeling.createDocument();
		Node cmdList = Modeling.createCmdList(doc, m_Parent.getModelContainer()
				.getID());
		Node upmsg = Modeling.createChangeElementMsg(doc, m_Current);
		// m_RestrictionPane.createRestriction(cmdList, upmsg, true,
		// m_FeaturePane.getUniqueName());
		Node namemsg = Modeling.createNewNameMsg(doc, nameField.getText());
		upmsg.appendChild(namemsg);

		Node vnamemsg = Modeling.createNewVNameMsg(doc, nameField.getText(),
				null);
		upmsg.appendChild(vnamemsg);
		createProperty(upmsg, true);
		cmdList.appendChild(upmsg);

		try {
			SyncModelUpdater.updateModel(m_Parent.getModelContainer(), cmdList,
					true);
		} catch (CoreException e) {
			MessageDialog.openError(getShell(), "Update Feature",
					"Failed to update feature.\n\n" + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Create Simple Feature");
	}
}