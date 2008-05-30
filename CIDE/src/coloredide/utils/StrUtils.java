package coloredide.utils;

import java.util.Collection;

public class StrUtils {

	public static String implode(String[] strs, String glue) {
		boolean first = true;
		String result = "";
		for (String s : strs) {
			if (first)
				first = false;
			else
				result += glue;
			result += s;
		}
		return result;
	}

	public static String implode(Collection elements, String glue) {
		boolean first = true;
		String result = "";
		for (Object element : elements) {
			if (first)
				first = false;
			else
				result += glue;
			result += element.toString();
		}
		return result;
	}

	public static String firstUp(String input) {
		if (input == null || input.equals(""))
			return input;
		return Character.toUpperCase(input.charAt(0)) + input.substring(1);
	}
}
