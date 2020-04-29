package com.nature.util;

public class StringUtil {
	
	public static boolean isEmpty(String string) {
		return string == null || string.equals("");
	}
	
	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}
	
}
