package com.nature.util;
/**
 * 
 * @title ConstantsUtil.java
 * @description ��ֵ����
 * @author Sun <sun@chenzhenyu.name>
 * @version V1.0 2012-12-14
 * @modified 2012-12-14
 */
public class ConstantsUtil {

	
	//默认每一页条数
	public final static int PAGE_SIZE = 10;
	//提现确认时间
	public final static int CONFIRM_WD_TEME = 3;
	//订单确认时间
	public final static int CONFIRM_ORDER_TEME = 9;
	//一天毫秒数
	public final static long ONE_DAY = 24 * 3600 * 1000;
	//5分钟毫秒数
	public final static long  CODE_TIMEOUT = 5 * 60 * 1000;
	
	/*public static String HOST_URL = "http://yshyouji.oicp.net/ZhongDaFabrics";
	public static String HOST_PATH = "http://yshyouji.oicp.net";
	public static String VIDEO_URL = "http://yshyouji.oicp.net/ZhongDaFabrics/video/";
	*/
	/*public static String HOST_URL = "http://localhost:8080/showcompany";*/
	public static String HOST_URL = "https://wxnature.nature-nt.com";
	public static String HOST_PATH = "https://xcxdemo.nature-nt.com";
	public static String VIDEO_URL = "https://xcxdemo.nature-nt.com/video/";
	
	//微信支付key
			public static String KEY = "983a67d7256bf5575bd0eb449d849a2a";
			//购买成功通知模板
			public static String templateId = "A6DIrg5HBP66ABhaxHPKMg5_ygrpi9cT7srz6cDXVMA";
	//小试牛刀
	public static String PUBLIC_APP_ID = "wx4500b35b07c0de59";
	public static String PUBLIC_APP_SECRET = "09fc9d78f06d32262f2b65fa262fa61b";
	
	//微信支付回调地址
	public static String TENPAY_NOTIFY_URL = HOST_URL+"/wxPayNotifyUrl.a";
	public static String TENPAY_RECHARGE_NOTIFY_URL = HOST_URL+"/wxRechargeNotifyUrl.a";
	//微信商户id
	public static String PUBLIC_MCH_ID = "1490076032";
	
	
	
	
}
