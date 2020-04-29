package com.nature.model.wxmini;

/** 
 * 微信通用接口凭证 
 *  
 * @author hhy 
 * @date 2018-2-5
 */  
public class SessionKey {
	// 小程序用户唯一标识
    private String openId;  
    // 会话密钥
    private String session_key;  
    // 用户在开放平台的唯一标识符
    private String unionid;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	@Override
	public String toString() {
		return "SessionKey [openId=" + openId + ", session_key=" + session_key + ", unionid=" + unionid + "]";
	}
   
}
