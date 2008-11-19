/**
 * 
 */
package de.ovgu.cide;

public enum ChangeType {
	// feature renamed
	NAME,
	// feature assigned a new color
	COLOR,
	// visibility flag of feature changed
	VISIBILITY,
	// feature was added to feature model
	ADD,
	// feature was removed from feature model
	REMOVE,
	// feature's dependencies in the feature model were changed (e.g. was
	// optional before and is mandatory now, or crosstree constraint added)
	DEPENDENCY
}