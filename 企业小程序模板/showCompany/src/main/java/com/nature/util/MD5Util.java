package com.nature.util;

import java.security.MessageDigest;

public class MD5Util {

	public static String getMD5String(String s) {
		
	    try {
		     byte[] btInput = s.getBytes();
		     MessageDigest mdInst = MessageDigest.getInstance("MD5");
		     mdInst.update(btInput);
		     byte[] md = mdInst.digest();
		     StringBuffer sb = new StringBuffer();
		     for (int i = 0; i < md.length; i++) {
			      int val = (md[i]) & 0xff;
			      if (val < 16)
			    	sb.append("0");
			      	sb.append(Integer.toHexString(val));
		     }
		     return sb.toString();
	    } catch (Exception e) {	    	
	    	return null;
	    }
	}
	
	public static String getMD5String(String s,String charsetname){
		try {
		     byte[] btInput = s.getBytes(charsetname);
		     MessageDigest mdInst = MessageDigest.getInstance("MD5");
		     mdInst.update(btInput);
		     byte[] md = mdInst.digest();
		     StringBuffer sb = new StringBuffer();
		     for (int i = 0; i < md.length; i++) {
			      int val = (md[i]) & 0xff;
			      if (val < 16)
			    	sb.append("0");
			      	sb.append(Integer.toHexString(val));
		     }
		     return sb.toString();
	    } catch (Exception e) {
	    	return null;
	    }
	}
	
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
