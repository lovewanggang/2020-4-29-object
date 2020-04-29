package com.nature.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nature.dao.base.IWxPlatformDAO;
import com.nature.dao.company.ICompanyAccountDAO;
import com.nature.model.company.CompanyAccount;
import com.nature.model.wxmini.AccessToken;
import com.nature.model.wxmini.JsapiTicket;
import com.nature.model.wxmini.WxPlatform;
import com.nature.util.ConstantsUtil;
import com.nature.util.weixin.util.WxMiniUtil;

@Service
public class WxPlatformBLL {
	@Resource
	private IWxPlatformDAO wxPlatformDAO;
	
	
	
	public WxPlatform getWxPlatformByAppId(String appid) {
		WxPlatform wxPlatform = wxPlatformDAO.get(WxPlatform.class, appid);
		if (wxPlatform == null) {
			wxPlatform = getWxPlatformByWeiXin();
		}
		return wxPlatform;
	}

	@Transactional
	public WxPlatform getWxPlatform(String id) {
		return wxPlatformDAO.get(WxPlatform.class, id);
	}

	public WxPlatform getWxPlatformByWeiXin() {
		AccessToken accessToken = WxMiniUtil.getAccessToken(
				ConstantsUtil.PUBLIC_APP_ID, ConstantsUtil.PUBLIC_APP_SECRET);
		if (accessToken == null) {
			return null;
		}
		JsapiTicket jsapiTicket = WxMiniUtil.getJsapiTicket(accessToken
				.getToken());
		if (jsapiTicket == null) {
			return null;
		}
		WxPlatform wxPlatform = new WxPlatform();
		wxPlatform.setAccess_token(accessToken.getToken());
		wxPlatform.setJsapi_ticket(jsapiTicket.getTicket());
		wxPlatform.setAppId(ConstantsUtil.PUBLIC_APP_ID);
		wxPlatform.setCreateTime(System.currentTimeMillis());
		if (!saveWxPlatform(wxPlatform)) {
			updateWxPlatform(wxPlatform);
		}

		return wxPlatform;
	}

	@Transactional(readOnly=true)
	public List<WxPlatform> listWxplPlatforms() {
		String hql = "from WxPlatform";
		List<WxPlatform> list = wxPlatformDAO.find(hql);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	@Transactional
	public boolean saveWxPlatform(WxPlatform wxPlatform) {
		try {
			Serializable s = wxPlatformDAO.saveWxPlatform(wxPlatform);
			System.out.println(s);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean updateWxPlatform(WxPlatform wxPlatform) {
		try {
			wxPlatformDAO.update(wxPlatform);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void setWxPlatformDAO(IWxPlatformDAO wxPlatformDAO) {
		this.wxPlatformDAO = wxPlatformDAO;
	}


	public IWxPlatformDAO getWxPlatformDAO() {
		return wxPlatformDAO;
	}



}
