package common;

import java.util.Vector;

public class Utils {
	
	public static final String COLORKEY = "COLOR";
	

	public static final String CAESAR = "Caesar";
	
	private static final int CAESARSHIFT = 5;
	
	public static String encodeCeasear(String text) {
		return shiftText(text, CAESARSHIFT);
	}
	
	public static String decodeCeasear(String text) {
		return shiftText(text, -CAESARSHIFT);
	}
	
	private static String shiftText(String text, int n) {
		//init the values
		String encString = "";
		int len = text.length();
		//encode the text
		for (int i = 0; i < len; i++) {
			//shift the character
			encString += (char)((int)text.charAt(i) + n);
		}
		//return the encoded text
		return encString;
	}
	
	public static final String REVERSE = "Reverse";
	
	public static String encodeReverse(String text) {
		return reverseText(text);
	}
	
	public static String decodeReverse(String text) {
		return reverseText(text);
	}
	
	private static String reverseText(String text) {
		//init the values
		String encString = "";
		int len = text.length();
		//encode the text
		for (int i = len -1; i >= 0; i--) {
			encString += text.charAt(i);
		}
		//return the encoded text
		return encString;
	}
	
	
	public static final String CODING1= "COD1";
	public static final String CODING2= "COD2";
	
	private static Vector codingList;
	
	public static Vector getCodingList () {	
		if (codingList == null) {
			codingList = new Vector();
			codingList.add("");
			
			
			codingList.add(CAESAR);

			codingList.add(REVERSE);

		}
		return codingList;
	}
	
	public static void encode(String coding, TextMessage msg) {
		

		if (coding.equals(CAESAR)) {
			msg.setContent(encodeCeasear(msg.getContent()));
			return;
		}

		if (coding.equals(REVERSE)) {
			msg.setContent(encodeReverse(msg.getContent()));
			return;
		}

	}
	
	public static void decode(String coding, TextMessage msg) {
		
		if (coding.equals(CAESAR)) {
			msg.setContent(decodeCeasear(msg.getContent()));
			return;
		}

		
		if (coding.equals(REVERSE)) {
			msg.setContent(decodeReverse(msg.getContent()));
			return;
		}
			
	}

}
