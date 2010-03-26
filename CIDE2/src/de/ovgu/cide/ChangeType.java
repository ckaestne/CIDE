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