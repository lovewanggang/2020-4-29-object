package com.nature.action;

import java.io.IOException;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.alibaba.fastjson.JSON;
import com.nature.model.company.Company;
import com.nature.model.company.CompanyAccount;
import com.nature.util.ConstantsUtil;
import com.nature.util.HttpRequestDeviceUtils;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements RequestAware,
		SessionAware, ApplicationAware {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> request;
	private Map<String, Object> session;
	private Map<String, Object> application;

	private String requestType;

	private static String appId = ConstantsUtil.PUBLIC_APP_ID;
	protected static String hosturl = ConstantsUtil.HOST_URL;
	
	public static String getHosturl() {
		return hosturl;
	}
	
	public BaseAction() {
		if (HttpRequestDeviceUtils.isMobileDevice(ServletActionContext
				.getRequest())) {
			this.requestType = "_phone";
		} else {
			this.requestType = "";
		}
	}

	public void writeJson(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object,
					"yyyy-MM-dd HH:mm:ss");
			ServletActionContext.getResponse().setContentType(
					"text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void putSessionAccount(String string, Integer id){
		session.put(string, id);
	}
	
	public void putSessionAccount(Integer companyId) {
		session.put("company", companyId);
	}
	
	public Object getSessionAccount(String str){
		return session.get(str);
	}
	
	/**
	 * 移除session用户实体
	 */
	public void removeSessionAccount() {
		session.remove("company");
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getApplication() {
		return application;
	}

	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

}
