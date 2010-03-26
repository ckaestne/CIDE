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

package de.ovgu.cide.typing.jdt;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import cide.gparser.ParseException;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.AstidWrapperWithParents;
import de.ovgu.cide.language.jdt.JDTLanguageExtension;
import de.ovgu.cide.language.jdt.JDTParserWrapper;

/**
 * this class caches the assignment of colors to JavaElement features.
 * 
 * otherwise only nodes inside one file are colored. but to check bindings it is
 * necessary to know the colors of the target methods in other classes. to not
 * need to read the colors for the whole ast of the target classes, this
 * JavaElementColorManager caches the colors for all elements that are potential
 * targets for bindings.
 * 
 * the data is stored between eclipse runs and can be updated asynchronously
 * with a builder.
 * 
 * @author cKaestner
 * 
 */
public class BindingProjectColorCache implements Serializable {

	private static final long serialVersionUID = 3L;

	private final HashMap<String, Set<IFeature>> bindingKeys2colors = new HashMap<String, Set<IFeature>>();
	
	private IJavaProject project;

	public BindingProjectColorCache(IJavaProject project) {
		this.project = project;
	}

	/**
	 * called after an item's color is changed. cycles through all children an
	 * searches for java elements that need to be updated.
	 * 
	 * @param nodes
	 * @param file
	 */
	void updateASTColors(ASTNode node, final ColoredSourceFile file) {
		node.accept(new ASTVisitor() {
			public boolean visit(MethodDeclaration node) {
				String key = null;
				IMethodBinding binding = node.resolveBinding();
				if (binding != null) {
					IJavaElement javaElement = binding.getJavaElement();
					if (javaElement instanceof IMethod)
						key = ((IMethod) javaElement).getKey();
						
				}
				if (key != null) {
					Set<IFeature> colors = getColor(file, node);
					update(bindingKeys2colors, key, colors);

					//add param keys
					for (int paramIdx = 0; paramIdx < node.parameters().size(); paramIdx++) {
						ASTNode param = (ASTNode) node.parameters().get(
								paramIdx);
						
						update(bindingKeys2colors, getParamKey(key, paramIdx),
								getColor(file, param));
						
					}
					
					//add exception keys
					for (int excIdx = 0; excIdx < node.thrownExceptions().size(); excIdx++) {
						Name exception = (Name) node.thrownExceptions().get(
								excIdx);
						
						ITypeBinding excBinding = exception.resolveTypeBinding();
						
						if (excBinding == null)
							continue;
						
						update(bindingKeys2colors, getExceptionKey(key, excBinding.getKey()),
								getColor(file, exception));
						
					}

				}
				return super.visit(node);
			}

			private Set<IFeature> getColor(final ColoredSourceFile file,
					ASTNode node) {
				return file.getColorManager().getColors(new AstidWrapperWithParents(node));
			}

			private void update(HashMap<String, Set<IFeature>> map, String key,
					Set<IFeature> colors) {
				
				if (colors != null && colors.size() > 0)
					map.put(key, colors);
				else
					map.remove(key);
			}

			public boolean visit(VariableDeclarationFragment node) {
				visitVD(node);
				return super.visit(node);
			}

			public boolean visit(SingleVariableDeclaration node) {
				visitVD(node);
				return super.visit(node);
			}

			public void visitVD(VariableDeclaration node) {
				String key = null;
				IVariableBinding binding = node.resolveBinding();
				if (binding != null) {
					IJavaElement javaElement = binding.getJavaElement();
					if (javaElement instanceof IField)
						key = ((IField) javaElement).getKey();
				}
				if (key != null)
					update(bindingKeys2colors, key, getColor(file, node));
			}

			@Override
			public boolean visit(TypeDeclaration node) {
				ITypeBinding binding = node.resolveBinding();
				if (binding != null) {
					update(bindingKeys2colors, binding.getKey(), getColor(file,
							node));

				}
				return super.visit(node);
			}
		});
	}

	private final static Set<IFeature> NOCOLORS = Collections.EMPTY_SET;

	public Set<IFeature> getColors(String bindingKey) {
		Set<IFeature> colors = bindingKeys2colors.get(bindingKey);
		if (colors != null)
			return colors;
		return NOCOLORS;
	}

	public Set<IFeature> getColors(IMethodBinding method) {
		return getColors(method.getKey());
	}

	public Set<IFeature> getColors(IVariableBinding field) {
		return getColors(field.getKey());
	}

	// colors for a parameter
	public Set<IFeature> getColors(IMethodBinding method, int paramIdx) {
		return getColors(getParamKey(method.getKey(), paramIdx));
	}

	// colors for a parameter
	public Set<IFeature> getColors(String methodKey, int paramIdx) {
		return getColors(getParamKey(methodKey, paramIdx));
	}

	public static String getParamKey(String methodKey, int paramIdx) {
		return methodKey + "/" + paramIdx;
	}

	public static String getExceptionKey(String methodKey, String exceptionKey) {
		return methodKey + "/" + exceptionKey;
	}
	
	public Set<IFeature> getColors(ITypeBinding type) {
		return getColors(type.getKey());

	}

	 /**
	 * rebuilds the cache for the entire java project
	 * 
	 * @throws JavaModelException
	 * @throws FeatureModelNotFoundException
	 */
	public void rebuildEntireProject() throws JavaModelException,
			FeatureModelNotFoundException {
		bindingKeys2colors.clear();
		for (IPackageFragment packageFragment : project.getPackageFragments())
			for (ICompilationUnit compilationUnit : packageFragment
					.getCompilationUnits()) {
				if (!(compilationUnit.getResource() instanceof IFile))
					continue;

				ColoredSourceFile file = ColoredSourceFile
						.getColoredSourceFile((IFile) compilationUnit
								.getResource());

				rebuildFile(compilationUnit, file);
			}
	}

	public void clear() {
		bindingKeys2colors.clear();
	}

	protected void rebuildFile(ICompilationUnit compilationUnit,
			ColoredSourceFile file) {
		if (!file.isColored())
			return;
		if (!file.getLanguageExtension().getId().equals(
				JDTLanguageExtension.LANGUAGE_EXTENSION_ID))
			return;

		try {
			CompilationUnit ast = JDTParserWrapper
					.parseCompilationUnit(compilationUnit);
			updateASTColors(ast, file);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
