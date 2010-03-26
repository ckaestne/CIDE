/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: DataForm.java 1220 2009-02-27 09:41:06Z luca $
*/

/**
 * 
 */
package it.yup.xmpp.packets;

import java.util.Hashtable;
import java.util.Vector;

import it.yup.xml.Element;

/**
 *
 */
public class DataForm {

	public static final String X = "x";
	public static final String NAMESPACE = "jabber:x:data";
	public static final String LISTO_NAMESPACE = "jabber:x:listo";

	public static final String TITLE = "title";
	public static final String INSTRUCTIONS = "instructions";
	public static final String FIELD = "field";

	public static final String FLD_DESC = "desc";
	public static final String FLD_REQUIRED = "required";
	public static final String FLD_VALUE = "value";
	public static final String FLD_OPTION = "option";
	public static final String FLD_REPORTED = "reported";
	public static final String FLD_VAR = "var";
	public static final String FLD_TYPE = "type";
	public static final String FLD_LABEL = "label";

	public static final String TYPE_FORM = "form";
	public static final String TYPE_RESULT = "result";
	public static final String TYPE_CANCEL = "cancel";
	public static final String TYPE_SUBMIT = "submit";

	public static final String FLT_BOOLEAN = "boolean";
	public static final String FLT_FIXED = "fixed";
	public static final String FLT_HIDDEN = "hidden";
	public static final String FLT_JIDMULTI = "jid-multi";
	public static final String FLT_JIDSINGLE = "jid-single";
	public static final String FLT_LISTMULTI = "list-multi";
	public static final String FLT_LISTSINGLE = "list-single";
	public static final String FLT_TXTMULTI = "text-multi";
	public static final String FLT_TXTPRIV = "text-private";
	public static final String FLT_TXTSINGLE = "text-single";
	public static final String FLT[] = { FLT_BOOLEAN, FLT_FIXED, FLT_HIDDEN,
			FLT_JIDMULTI, FLT_JIDSINGLE, FLT_LISTMULTI, FLT_LISTSINGLE,
			FLT_TXTMULTI, FLT_TXTPRIV, FLT_TXTSINGLE };

	/** the form type, one of TYPE_* constants */
	public String type;
	/** the form title */
	public String title;
	/** the form instructions, may be multi-line separated by '\n' */
	public String instructions;
	/** the form field definition */
	public Vector fields;
	/** the form results */
	public Vector results;

	public DataForm(Element form) {
		fields = new Vector();
		results = new Vector();

		type = form.getAttribute(Stanza.ATT_TYPE);
		if (TYPE_FORM.equals(type)) {
			type = TYPE_FORM;
			parseForm(form);
		} else if (TYPE_RESULT.equals(type)) {
			type = TYPE_RESULT;
			Element repo = form.getChildByName(NAMESPACE, "reported");
			if (repo != null) {
				/* form has multiple items, form definition is in "reported" 
				 * form results are in "item" elements*/
				parseForm(repo);
				Element[] children=form.getChildren();
				for (int i = 0; i < children.length; i++) {
					Element e = children[i];
					if ("item".equals(e.name)) {
						parseItem(e);
					}
				}
			} else {
				parseForm(form);
				Hashtable res = new Hashtable();
				for (int i = 0; i < fields.size(); i++) {
					Field fld = (Field) fields.elementAt(i);
					String fname = fld.varName;
					res.put(fname, fld.dValue == null ? "" : fld.dValue);
				}
				results.addElement(res);
			}
		} else {
			// ??? invalid types to show... ???
			return;
		}

	}

	/**
	 * Parse an item in a result type form.
	 * @param item
	 * 		the item to parse
	 */
	private void parseItem(Element item) {

		Hashtable res = new Hashtable();
		Element [] children = item.getChildren();
		for (int i = 0; i < children.length; i++) {
			Element e = children[i];
			if (!FIELD.equals(e.name)) {
				/* ??? should not be... */
				continue;
			}
			String var = e.getAttribute(FLD_VAR);
			Element eval = e.getChildByName(NAMESPACE, FLD_VALUE);
			if (eval == null) {
				/* ??? error */
				continue;
			}
			res.put(var, eval.getText());
		}

		results.addElement(res);
	}

	/**
	 * Parse the fields definition in a "form" or a "result" type data form
	 * @param form
	 * 		The field definition
	 */
	private void parseForm(Element form) {
		Element [] children = form.getChildren();
		for (int i = 0; i < children.length; i++) {
			Element e = children[i];
			if (FIELD.equals(e.name)) {
				Field fld = new Field(e);
				if (fld.varName == null && fld.type == FLT_FIXED) {
					fld.varName = "_fixed_" + i;
					//fld.varName = "";
				}
				fields.addElement(fld);
			}
			// XXX: there can be more than one instruction line
			if(INSTRUCTIONS.equals(e.name)) {
				instructions = (instructions == null ? e.getText() : instructions + "\n" + e.getText());
			}
			if(TITLE.equals(e.name)) {
				title = e.getText();
			}
		}
	}

	/**
	 * Creates an XML element with the current data of an user-input form.
	 * @return
	 * 		The XML element with the user input data. Returns a value only if 
	 * 		the form is of type "submit" or "cancel". Returns <code>null</code>
	 * 		if the form is of type "result" or "form".
	 */
	public Element getResultElement() {

		if (type == TYPE_FORM || type == TYPE_RESULT) { return null; }

		Element el = new Element(NAMESPACE, X);
		el.setAttribute(Stanza.ATT_TYPE, type);
		/* cancel, non mando nient'altro??? */
		if (type == TYPE_CANCEL) { return el; }

		for (int i = 0; i < fields.size(); i++) {

			Field fld = (Field) fields.elementAt(i);

			if (fld.type == FLT_FIXED) {
				continue;
			}

			Element ch = el.addElement(NAMESPACE, FIELD);
			ch.setAttribute(FLD_TYPE, fld.type);
			ch.setAttribute(FLD_VAR, fld.varName);

			if (fld.type == FLT_BOOLEAN || fld.type == FLT_HIDDEN
					|| fld.type == FLT_JIDSINGLE || fld.type == FLT_TXTPRIV
					|| fld.type == FLT_TXTSINGLE || fld.type == FLT_LISTSINGLE) {
				Element val = ch.addElement(NAMESPACE, FLD_VALUE);
				val.addText(fld.dValue);
				continue;
			}

			if (fld.type == FLT_JIDMULTI || fld.type == FLT_LISTMULTI
					|| fld.type == FLT_TXTMULTI) {
				/* XXX: This is maybe wrong, if no value has been set by user, no "<value>" 
				 * tag should be reported? standard doesn't address this issue... */
				if (fld.dValue == null) {
					Element val = ch.addElement(NAMESPACE, FLD_VALUE);
					val.addText("");
					continue;
				}
				int p = 0, q = 0;
				do {
					Element val = ch.addElement(NAMESPACE, FLD_VALUE);
					q = fld.dValue.indexOf('\n', p);
					if (q == -1) {
						val.addText(fld.dValue.substring(p));
					} else {
						val.addText(fld.dValue.substring(p, q));
						p = q + 1;
					}
				} while (q != -1);
				continue;
			}
		}

		return el;
	}

	/**
	 * Un campo della form
	 */
	public class Field {
		/** field description */
		public String desc;
		/** mandatory field */
		public boolean required;
		/** default value */
		public String dValue;
		/**  field var */
		public String varName;
		/** field type */
		public String type;
		/** lable */
		public String label;
		/** available options */
		public Vector options;

		public Field(Element f) {

			options = new Vector();
			type = FLT_TXTSINGLE;

			varName = f.getAttribute(FLD_VAR);
			String t = f.getAttribute(FLD_TYPE);
			label = f.getAttribute(FLD_LABEL);

			// type normalization
			for (int i = 0; i < FLT.length; i++) {
				if (FLT[i].equals(t)) {
					type = FLT[i];
					break;
				}
			}

			Element [] children = f.getChildren();
			for (int i = 0; i < children.length; i++) {
				Element e = children[i];
				if (FLD_DESC.equals(e.name)) {
					desc = e.getText();
					continue;
				}
				if (FLD_REQUIRED.equals(e.name)) {
					required = true;
					continue;
				}
				if (FLD_VALUE.equals(e.name)) {
					if (dValue == null) {
						dValue = e.getText();
					} else {
						dValue += "\n" + e.getText();
					}
					continue;
				}
				if (FLD_OPTION.equals(e.name)) {
					options.addElement(getOption(e));
				}

			}
		}

		/**
		 * The returned strings contain VALUE/LABEL (LABEL set to VALUE if not present)
		 * @param e
		 * @return
		 */
		private String[] getOption(Element e) {
			String[] opt = new String[2];
			Element v = e.getChildByName(NAMESPACE, FLD_VALUE);
			if (v != null) {
				opt[0] = v.getText();
			}
			String att = e.getAttribute(FLD_LABEL);
			if (att != null) {
				opt[1] = att;
			} else {
				opt[1] = opt[0];
			}
			return opt;
		}

	}

}
