package coloredide.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
	
	
	public static InputStream strToInputStream(String txt){
		return new ByteArrayInputStream(txt.getBytes());	
	}
	
}
