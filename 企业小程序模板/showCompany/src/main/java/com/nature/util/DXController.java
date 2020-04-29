package com.nature.util;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DXController extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8195423295012169815L;


	private static String appId = ConstantsUtil.PUBLIC_APP_ID;
	protected static String hosturl = ConstantsUtil.HOST_URL;
	
	public static String getHosturl() {
		return hosturl;
	}

	

	public String getParameter(String key) {
		return ServletActionContext.getRequest().getParameter(key);
	}
	
	
	
}
