package com.nature.util;

import java.security.MessageDigest;

public class MD5 {

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
}
