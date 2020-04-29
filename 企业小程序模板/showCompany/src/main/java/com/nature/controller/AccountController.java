package com.nature.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.JSONException;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleIfStatement.Else;
import com.mysql.jdbc.TimeUtil;
import com.youguokj.action.BaseAction;
import com.youguokj.model.AccessToken;
import com.youguokj.model.Account;
import com.youguokj.model.JsapiTicket;
import com.youguokj.model.OAuthAccessToken;
import com.youguokj.model.SampleOrder;
import com.youguokj.model.SystemUser;
import com.youguokj.model.UserCustomer;
import com.youguokj.model.UserStaff;
import com.youguokj.model.WeiXinUnionUser;
import com.youguokj.service.AccountServiceI;
import com.youguokj.service.LoginValidateServiceI;
import com.youguokj.service.SampleOrderServiceI;
import com.youguokj.util.ConstantsUtil;
import com.youguokj.util.MD5;
import com.youguokj.util.SignUtil;
import com.youguokj.util.WeixinUtil;

import net.sf.json.JSONObject;
import sun.security.provider.SHA;

/**
 * 账户管理模块控制器
 * 
 * @author Len
 */
@ParentPackage("basePackage")
@Namespace("/account")
public class AccountController extends BaseAction {

	private static final long serialVersionUID = 1L;
	public static OAuthAccessToken oAuthAccessToken = new OAuthAccessToken();
	public static WeiXinUnionUser wxUser = new WeiXinUnionUser();

	@Resource
	private AccountServiceI accountService;
	@Resource
	private SampleOrderServiceI sampleOrderService;
	@Resource
	private LoginValidateServiceI service;

	/********* public ****************/
	private Integer id;

	private Map<String, Object> retData = new HashMap<String, Object>();
	private String name;
	private String password;
	private List<SampleOrder> sampleOrders;

	private String code;
	private String appid;
	private String hostUrl;
	private String retString;
	private UserCustomer userCustomer;
	private String phoneNum;
	private UserStaff userStaff;
	private WeiXinUnionUser weixinUser;

	@Action(value = "register", results = { @Result(name = "success", location = "/pages/customerIndex_phone.jsp") })
	public String register() {
		System.out.println(userCustomer);
		id = accountService.updateCustomer(userCustomer);
		return SUCCESS;
	}

	@Action(value = "toCustomerIndex", results = {
			@Result(name = "success", location = "/pages/customerLogin_phone.jsp") })
	public String toCustomerIndex() {
		appid = WeixinUtil.APPID;
		hostUrl = ServletActionContext.getRequest().getRequestURL().toString();
		hostUrl = hostUrl.substring(0, hostUrl.indexOf("/", 9));
		return SUCCESS;
	}

	@Action(value = "customerLogin", results = { @Result(name = "success", type = "redirectAction", params = {
			"actionName", "customerLogin2", "namespace", "/account" }) })
	public String customerLogin() throws Exception {
		OAuthAccessToken oAAccessToken = WeixinUtil.getOAuthByCode(code,WeixinUtil.openId_url);
		boolean ATstate = WeixinUtil.checkAccessToken(oAAccessToken.getToken(), oAAccessToken.getOpenid());
		if (!ATstate) {
			oAAccessToken = WeixinUtil.refreshToken(oAAccessToken.getToken());
		}
		wxUser = WeixinUtil.getWeiXinUnionUser(oAAccessToken.getToken(), oAAccessToken.getOpenid());
		
		id = accountService.saveCustomer(wxUser);
		setSessionUserCustomer(accountService.getUserCustm(id)); // 把帐号放到session中，上线
		return SUCCESS;
	}

	@Action(value = "customerLogin2", results = {
			@Result(name = "success", location = "/pages/customerRegister_phone.jsp"),
			@Result(name = "customerIndex", location = "/pages/customerIndex_phone.jsp") })
	public String customerLogin2() throws Exception {
//		boolean customerState = accountService.isRegister(wxUser.getOpenId());
//		if (customerState) {
//			return "customerIndex";
//		}
//		return SUCCESS;
		
		return "customerIndex";
	}

	@Action(value = "staffLogin", results = {
			@Result(name = "success", location = "/pages/staffLogin${requestType}.jsp") })
	public String staffLogin() {
		appid = WeixinUtil.APPID;
		hostUrl = ServletActionContext.getRequest().getRequestURL().toString();
		hostUrl = hostUrl.substring(0, hostUrl.indexOf("/", 9));
		return SUCCESS;
	}

	@Action(value = "wxLogin", results = { @Result(name = "success", type = "redirectAction", params = { "actionName",
			"wxLogin2", "namespace", "/account" }) })
	public String wxLogin() throws Exception {
		OAuthAccessToken oAAccessToken = WeixinUtil.getOAuthByCode(code,WeixinUtil.openId_url);
		boolean ATstate = WeixinUtil.checkAccessToken(oAAccessToken.getToken(), oAAccessToken.getOpenid());
		if (!ATstate) {
			oAAccessToken = WeixinUtil.refreshToken(oAAccessToken.getToken());
		}
		wxUser = WeixinUtil.getWeiXinUnionUser(oAAccessToken.getToken(), oAAccessToken.getOpenid());
		return SUCCESS;
	}

	@Action(value = "wxLogin2", results = { @Result(name = "success", location = "/pages/toRegiterStaff.jsp"),
			@Result(name = "pass", type = "redirectAction", params = { "actionName", "toIndex", "namespace",
					"/account" }) })
	public String wxLogin2() throws Exception {
		int OIState = accountService.checkOpenIdState(wxUser);
		if (OIState == 0) {
			retString = "你的账号还在审核中，暂时不能访问本系统。";
		} else if (OIState == 1 || OIState == 2) {
			UserStaff uStaff = accountService.getUserStaff(oAuthAccessToken.getOpenid());
			setSessionUserStaff(uStaff);
			return "pass";
		} else if (OIState == -10) {
			retString = "OInull";

		}
		return SUCCESS;
	}

	@Action(value = "staffRegister", results = {
			@Result(name = "success", location = "/pages/index${requestType}.jsp") })
	public String staffRegister() throws Exception {
		WeiXinUnionUser wxUser = WeixinUtil.getWeiXinUnionUser(oAuthAccessToken.getToken(),
				oAuthAccessToken.getOpenid());
		UserStaff uStaff = accountService.staffRegister(userStaff, wxUser);
		setSessionUserStaff(uStaff);
		return SUCCESS;
	}

	/*@Action(value = "getOpenId", results = { @Result(name = "success", type = "redirectAction", params = { "actionName",
			"listSampleOrder", "namespace", "/sampleOrder" }), })
	public String getOpenId() throws Exception {
		String code = (String) ServletActionContext.getRequest().getAttribute("code");
		OAuthAccessToken oAuthAccessToken = WeixinUtil.getOAuthByCode(code);
		WeiXinUnionUser weiXinUnionUser = WeixinUtil.getWeiXinUnionUser(oAuthAccessToken.getToken(),
				oAuthAccessToken.getOpenid());
		return SUCCESS;
	}*/

	/*
	@Action(value = "wxConfig", results = { @Result(name = "success", type = "json", params = { "root", "retData" }) })
	public String wxConfig() throws Exception {
		
		 * 1、获取accessToken 2、获取jsapi_ticket 3、生成签名
		 
		// String timeStamp =
		// String.valueOf(Calendar.getInstance().getTimeInMillis());
		// String randomStr = "len";
		// AccessToken accessToken = WeixinUtil.getAccessToken();
		// accessTokenVale = accessToken.getToken();
		// JsapiTicket jsapiTicket = WeixinUtil.getJsapiTicket(accessTokenVale);
		// System.out.println("URL====================="+ServletActionContext.getRequest().getRequestURL().toString());
		// String sign = toSaveAttestationPhoto();
		//
		// retData.put("appid", WeixinUtil.APPID);
		// retData.put("timeStamp", timeStamp);
		// retData.put("randomStr", randomStr);
		// retData.put("signature", sign);
		return SUCCESS;
	}*/

	public WeiXinUnionUser oauth1GetOpenid(String code, String appId, String secret) throws Exception {
		HttpClient client = null;
		client = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret="
				+ secret + "&code=" + code + "&grant_type=authorization_code");
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String response = client.execute(httpget, responseHandler);
		Object obj = (Object) response;
		JSONObject jbt = JSONObject.fromObject(obj);
		System.out.println("oauth1GetOpenid=======" + jbt);
		if (!jbt.getString("openid").equals(oAuthAccessToken.getOpenid())) {
			if (oAuthAccessToken.getToken() == null) {
				System.out.println("=================oAuthAccessToken还没存");
				oAuthAccessToken.setOpenid(String.valueOf(jbt.getString("openid")));
				oAuthAccessToken.setToken(String.valueOf(jbt.getString("access_token")));
				oAuthAccessToken.setExpiresIn(Integer.valueOf(jbt.getInt("expires_in")));
				oAuthAccessToken.setRefresh_token(String.valueOf(jbt.getString("refresh_token")));
				oAuthAccessToken.setScope(String.valueOf(jbt.getString("scope")));
				new Thread(new TokenThread()).start();
			}
		}
		WeiXinUnionUser user1 = WeixinUtil.getWeiXinUnionUser(oAuthAccessToken.getToken(),
				oAuthAccessToken.getOpenid());
		return user1;
	}

	class TokenThread implements Runnable {
		@Override
		public void run() {
			try {
				while (true) {
					Thread.sleep((AccountController.oAuthAccessToken.getExpiresIn() - 200) * 1000);
					refreshToken(AccountController.oAuthAccessToken.getRefresh_token());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * @param refresh_token
		 * @return
		 * @throws Exception
		 */
		private void refreshToken(String refresh_token) throws Exception {
			String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
			String requestUrl = url.replace("APPID", WeixinUtil.APPID).replace("REFRESH_TOKEN", refresh_token);
			JSONObject jsonObject = WeixinUtil.doGetStr(requestUrl);
			System.out.println("定时刷新后用户基本信息：" + jsonObject);
			if (null != jsonObject) {
				AccountController.oAuthAccessToken.setOpenid(jsonObject.getString("openid"));
				AccountController.oAuthAccessToken.setToken(jsonObject.getString("access_token"));
				AccountController.oAuthAccessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				AccountController.oAuthAccessToken.setRefresh_token(jsonObject.getString("refresh_token"));
				AccountController.oAuthAccessToken.setScope(jsonObject.getString("scope"));

			}
		}

	}

	@Action(value = "login", results = { @Result(name = "success", type = "json", params = { "root", "retData" }) })
	public String login() {
		System.out.println("login==========");
		Account account = service.validate(name);
		if (account == null) {
			retData.put("retData", "没有此帐号！");
			return SUCCESS;
		} else if (!MD5.getMD5String(password).equals(account.getPassword())) {
			retData.put("retData", "密码不正确！");
			return SUCCESS;
		} else if (account.getStatus() == 0) {
			retData.put("retData", "帐号已经冻结！");
			return SUCCESS;
		} else {
			setSessionAccount(account);
			retData.put("retData", "success");
			return SUCCESS;
		}
	}

	@Action(value = "toIndex", results = { @Result(name = "phone", type = "redirectAction", params = { "actionName",
			"listOrderForRoot", "namespace", "/sampleOrder" }),
			@Result(name = "pc", location = "/pages/index${requestType}.jsp") })
	public String toIndex() {
		if ("_phone".equals(this.getRequestType())) {
			return "phone";
		}
		return "pc";
	}

	@Action(value = "toLogin", results = { @Result(name = "success", location = "/pages/login${requestType}.jsp") })
	public String toLogin() {
		appid = WeixinUtil.APPID;
		hostUrl = ServletActionContext.getRequest().getRequestURL().toString();
		hostUrl = hostUrl.substring(0, hostUrl.indexOf("/", 9));
		return SUCCESS;
	}
	

	/**********************************************
	 * setter and getter
	 *****************************************/

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRetString() {
		return retString;
	}

	public static OAuthAccessToken getoAuthAccessToken() {
		return oAuthAccessToken;
	}

	public Integer getId() {
		return id;
	}

	public static void setoAuthAccessToken(OAuthAccessToken oAuthAccessToken) {
		AccountController.oAuthAccessToken = oAuthAccessToken;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRetString(String retString) {
		this.retString = retString;
	}

	public UserCustomer getUserCustomer() {
		return userCustomer;
	}

	public void setUserCustomer(UserCustomer userCustomer) {
		this.userCustomer = userCustomer;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getHostUrl() {
		return hostUrl;
	}

	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}

	public UserStaff getUserStaff() {
		return userStaff;
	}

	public void setUserStaff(UserStaff userStaff) {
		this.userStaff = userStaff;
	}

	public AccountServiceI getAccountService() {
		return accountService;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public SampleOrderServiceI getSampleOrderService() {
		return sampleOrderService;
	}

	public List<SampleOrder> getSampleOrders() {
		return sampleOrders;
	}

	public void setSampleOrderService(SampleOrderServiceI sampleOrderService) {
		this.sampleOrderService = sampleOrderService;
	}

	public void setSampleOrders(List<SampleOrder> sampleOrders) {
		this.sampleOrders = sampleOrders;
	}

	public void setAccountService(AccountServiceI accountService) {
		this.accountService = accountService;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public WeiXinUnionUser getWeixinUser() {
		return weixinUser;
	}

	public void setWeixinUser(WeiXinUnionUser weixinUser) {
		this.weixinUser = weixinUser;
	}

	public LoginValidateServiceI getService() {
		return service;
	}

	public Map<String, Object> getRetData() {
		return retData;
	}

	public void setService(LoginValidateServiceI service) {
		this.service = service;
	}

	public void setRetData(Map<String, Object> retData) {
		this.retData = retData;
	}

}
