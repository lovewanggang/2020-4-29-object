package com.nature.service;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.nature.dao.base.ISystemUserDAO;
import com.nature.model.account.SystemUser;
import com.nature.model.wxmini.WxPlatform;
import com.nature.util.ConstantsUtil;
import com.nature.util.RandomUtil;
import com.nature.util.SignUtil;
import com.nature.util.TimeUtil;

@Service
public class SystemUserBLL {
	@Resource
	private ISystemUserDAO SystemUserDAO;
	
	@Resource
	private WxPlatformBLL wxPlatformBLL;

	public SystemUser getSystemUser(String id) {
		return SystemUserDAO.get(SystemUser.class, id);
	}

	public boolean saveSystemUser(SystemUser SystemUser) {
		try {
			SystemUserDAO.save(SystemUser);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateSystemUser(SystemUser SystemUser) {
		try {
			SystemUserDAO.update(SystemUser);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setSystemUserDAO(ISystemUserDAO SystemUserDAO) {
		this.SystemUserDAO = SystemUserDAO;
	}



	/*
	 * 根据openId获取用户
	 */
	public SystemUser getUserByOpenId(String openId)
	{
		try {
			return this.SystemUserDAO.getUserByOpenId(openId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public Map<String, Object> toSaveAttestationPhoto(Map<String, Object> dataMap){
        HttpServletRequest request = ServletActionContext.getRequest();
        String nonceStr = RandomUtil.getRandomStr();
        String timestamp = TimeUtil.getTimeStamp();
           dataMap.put("nonceStr", nonceStr);
           dataMap.put("timestamp",timestamp);
           WxPlatform wxPlatform = wxPlatformBLL.getWxPlatformByAppId(ConstantsUtil.PUBLIC_APP_ID);
           dataMap.put("signature",SignUtil.getSignature(wxPlatform.getJsapi_ticket(), nonceStr, timestamp, SignUtil.getUrl(request)));
        return dataMap;
    }

	
}
