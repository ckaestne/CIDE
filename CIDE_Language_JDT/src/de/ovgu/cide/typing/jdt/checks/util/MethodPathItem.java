package de.ovgu.cide.typing.jdt.checks.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.Modifier;

import de.ovgu.cide.typing.jdt.BindingProjectColorCache;

/**
 * Structure item which helps to store an inherited method.
 * 
 * @author aDreiling
 * 
 */
public class MethodPathItem {

	final private String key;
	final private boolean isAbstract;
	final private boolean isDeclClassAbstract;
	private List<String> paramKeys;
	private Map<String, List<String>> exceptionKeys;

	private final IMethodBinding binding;

	public MethodPathItem(IMethodBinding binding, boolean isDeclClassAbstract) {
		this.binding = binding;
		this.isDeclClassAbstract = isDeclClassAbstract;

		key = binding.getKey();
		isAbstract = Modifier.isAbstract(binding.getModifiers());

	}

	public IMethodBinding getBinding() {
		return binding;

	}

	public boolean isDeclaringClassAbstract() {
		return isDeclClassAbstract;
	}

	public String getKey() {
		return key;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public List<String> getInheritedParamKeys() {

		if (paramKeys != null)
			return paramKeys;

		paramKeys = new ArrayList<String>();
		for (int i = 0; i < binding.getParameterTypes().length; i++) {

			paramKeys.add(BindingProjectColorCache.getParamKey(key, i));

		}

		return paramKeys;

	}

	public Map<String, List<String>> getInheritedExceptionKeys(
			IMethodBinding sourceBinding) {

		if (exceptionKeys != null)
			return exceptionKeys;

		exceptionKeys = new HashMap<String, List<String>>();

		// add cast compatible exception 
		for (ITypeBinding tmpExBinding : sourceBinding.getExceptionTypes()) {

			ArrayList<String> tmpExceKeys = new ArrayList<String>();

			for (ITypeBinding tmpMethExBinding : binding.getExceptionTypes()) {

				if (tmpExBinding.isCastCompatible(tmpMethExBinding))
					tmpExceKeys.add(BindingProjectColorCache.getExceptionKey(
							binding.getKey(), tmpMethExBinding.getKey()));

			}

			exceptionKeys.put(tmpExBinding.getKey(), tmpExceKeys);

		}

		return exceptionKeys;

	}

}
