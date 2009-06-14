package de.ovgu.cide.export.virtual.internal;

import java.util.Set;

import de.ovgu.cide.features.IFeature;

/**
 * how to print annotations? note: we assume ifdef semantics, i.e. annotations
 * may be nested, but always close in the reverse order
 * 
 * 
 * @author ckaestne
 * 
 */
public interface IPPExportOptions {
	/**
	 * should the start and end instructions be printed in a new line? (i.e.
	 * should a line break be enforced before?)
	 * 
	 * the instruction is responsible for the linebreak at the end itself
	 * 
	 * @return
	 */
	boolean inNewLine();

	/**
	 * get the code statement(s) to begin an annotation block
	 * 
	 * @param f
	 *            set of features annotated for the current element
	 * @return
	 */
	String getStartInstruction(Set<IFeature> f);

	/**
	 * get the code statement(s) to ennd an annotation block
	 * 
	 * @param f
	 *            set of features annotated for the current element
	 * @return
	 */
	String getEndInstruction(Set<IFeature> f);

}
