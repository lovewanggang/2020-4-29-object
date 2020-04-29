package com.nature.util;

public class PowerType {
	
	public final static int CHECK_ALL_ORDER = 1;
	public final static int ORDER_SET_IMPORTANT = 2;
	public final static int ORDER_IDU = 4;
	public final static int ORDER_SET_STATUS = 8;
	public final static int CUSTOMER_OP = 16;
	public final static int DELIVERY_OP = 32;
	public final static int ACCOUNT_OP = 64;
	
	public static boolean checkPower(int power, int req) {
		if (power / req % 2 == 1) {
			return true;
		}
		return false;
	}

}
