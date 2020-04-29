package com.nature.model.wxmini;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class WxPlatform {
    
	private String appId;
	private String appSecret;
	private String access_token;
	private String jsapi_ticket;
	private long createTime;
	@Id
	@GeneratedValue(generator="paymentableGenerator")
	@GenericGenerator(name="paymentableGenerator", strategy = "assigned")
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String accessToken) {
		access_token = accessToken;
	}
	public String getJsapi_ticket() {
		return jsapi_ticket;
	}
	public void setJsapi_ticket(String jsapiTicket) {
		jsapi_ticket = jsapiTicket;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "WxPlatform [appId=" + appId + ", appSecret=" + appSecret + ", access_token=" + access_token
				+ ", jsapi_ticket=" + jsapi_ticket + ", createTime=" + createTime + "]";
	}
	
	
}
