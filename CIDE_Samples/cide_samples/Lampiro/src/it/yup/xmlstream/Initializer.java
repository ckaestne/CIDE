/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: Initializer.java 1028 2008-12-09 15:44:50Z luca $
*/

package it.yup.xmlstream;

import java.util.Hashtable;

/**
 * A class that can be called during the initialization of an XmlStream
 * Each initializer is triggered using the {@link Initializer#start(XmlStream)} method, 
 * and it must call {@link BoshStream#nextInitializer()} when finished 
 * 
 */
public abstract class Initializer {

	protected BasicXmlStream stream;
	protected String namespace = null;
	public boolean optional = true;
	
	/**
	 * Base initializer for all authenticators
	 * @param namespace 
	 * 		the unique identifier for this initializer; same as the namespace
	 * 		in stream features
	 * @param optional
	 * 		if true this initialiazer may be skipped
	 */
	protected Initializer(String namespace, boolean optional) {
		this.namespace = namespace;
		this.optional = optional;
	}
	
	public abstract void start(BasicXmlStream stream);
	
	public boolean matchFeatures(Hashtable features) {
		return features.containsKey(namespace);
	}
}
