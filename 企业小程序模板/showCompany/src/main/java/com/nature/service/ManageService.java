package com.nature.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nature.dao.base.IAdminAccountDAO;
import com.nature.dao.base.IWxPlatformDAO;
import com.nature.dao.company.ICompanyAccountDAO;
import com.nature.dao.company.ICompanyDAO;
import com.nature.dao.company.IModuleDAO;
import com.nature.model.account.AdminAccount;
import com.nature.model.company.Company;
import com.nature.model.company.CompanyAccount;
import com.nature.model.module.Module_on_off;
import com.nature.model.wxmini.AccessToken;
import com.nature.model.wxmini.JsapiTicket;
import com.nature.model.wxmini.WxPlatform;
import com.nature.util.ConstantsUtil;
import com.nature.util.RandomCodeGenerator;
import com.nature.util.SendEmailUtil;
import com.nature.util.TimeUtil;
import com.nature.util.weixin.util.WxMiniUtil;


@Service
public class ManageService {

	@Resource
	private ICompanyAccountDAO companyAccountDAO;
	@Resource
	private IAdminAccountDAO adminAccountDAO;
	@Resource
	private ICompanyDAO companyDAO;
	@Resource
	private IModuleDAO moduleDAO;
	@Resource
	private IWxPlatformDAO wxPlatformDAO;
	
	/**
	 * 注册
	 * 生成小程序识别码
	 * 生成登录帐号密码
	 * @param appID
	 * @param appSecret
	 * @param dataMap 
	 */
	public CompanyAccount register (String appID, String appSecret, Map<String, Object> dataMap){
		String hql = "from CompanyAccount where wxAppID = '"+appID+"'";
		List<CompanyAccount> accounts = companyAccountDAO.find(hql);
		if (accounts.size()>0) {
			dataMap.put("code", -1);
			return null;
		}else {
			dataMap.put("code", 0);
		}
		Company company = new Company();
		companyDAO.save(company);
		Module_on_off module_on_off = new Module_on_off();
		module_on_off.setCompany(company);
		moduleDAO.save(module_on_off);
		CompanyAccount companyAccount = new CompanyAccount();
		companyAccount.setCompany(company);
		companyAccount.setWxAppID(appID);
		companyAccount.setWxAppSecret(appSecret);
		companyAccount.setCompanyAccountId("SC"+RandomCodeGenerator.getRandomNum(6));
		String loginName = checkLoginName(RandomCodeGenerator.getRandomNum(9));
		companyAccount.setLoginAccount(loginName);
		companyAccount.setLoginPassword(RandomCodeGenerator.getRandom(6));
		companyAccountDAO.save(companyAccount);
		dataMap.put("account", companyAccount);
		
		//小程序加入到WxPlatform
		//获取token jsapi_ticket
		WxPlatform wxPlatform = new WxPlatform();
		wxPlatform.setAppId(appID);
		wxPlatform.setAppSecret(appSecret);
		AccessToken accessToken = WxMiniUtil.getAccessToken(appID,appSecret);
		if (accessToken != null) {
			wxPlatform.setAccess_token(accessToken.getToken());
			JsapiTicket jsapiTicket = WxMiniUtil.getJsapiTicket(accessToken.getToken());
			wxPlatform.setJsapi_ticket(jsapiTicket.getTicket());
			wxPlatform.setCreateTime(System.currentTimeMillis());
			wxPlatformDAO.save(wxPlatform);
		}else {
			System.out.println("------get accessToken fail---------- ：PUBLIC_APP_ID:" + appID);
		}
		return companyAccount;
		
	}
	
	protected String checkLoginName(String loginName){
		String hql = "from CompanyAccount where loginAccount = '"+loginName+"'";
		if (companyAccountDAO.find(hql).size() > 0) {
			loginName = RandomCodeGenerator.getRandomNum(9);
			loginName = checkLoginName(loginName);
			return loginName;
		}else {
			return loginName;
		}
	}
	
	/**
	 * 修改登录密码
	 * @param account
	 * @param password
	 */
	public void updatePassword(String accountName, String password, Map<String, Object> dataMap){
		String hql = "from CompanyAccount where loginAccount = '"+accountName+"'";
		List<CompanyAccount> companyAccounts = companyAccountDAO.find(hql);
		if (companyAccounts.size() > 0) {
			companyAccounts.get(0).setLoginPassword(password);
			companyAccountDAO.update(companyAccounts.get(0));
			dataMap.put("result", 0);//修改成功
		}else {
			dataMap.put("result", -1);//没有该帐号
		}
		
	}
	
	/**
	 *  1是没搜索到，0是通过
	 * @param str
	 * @param str2
	 * @return
	 */
	public List<AdminAccount> login(String name, String password) {
		List<AdminAccount> accounts = adminAccountDAO.queryAdminAccount(name,password);
		return accounts;
	}
	
	/**
	 * @return
	 */
	public List<CompanyAccount> queryComAccountInfo() {
		String hql = "from CompanyAccount";
		return companyAccountDAO.find(hql);
	}
	public CompanyAccount findOneComAccount(String idStr) {
		return companyAccountDAO.get(CompanyAccount.class, idStr);
	}

	public void updateComAccount(CompanyAccount account) {
		CompanyAccount companyAccount = companyAccountDAO.get(CompanyAccount.class, account.getCompanyAccountId());
		companyAccount.setRegisterDate(account.getRegisterDate());
		companyAccount.setCompanyName(account.getCompanyName());
		companyAccount.setOwner(account.getOwner());
		companyAccount.setPhoneNum(account.getPhoneNum());
		companyAccount.setTaocan(account.getTaocan());
		companyAccount.setBank(account.getBank());
		companyAccount.setBankAccount(account.getBankAccount());
		companyAccount.setScope_of_business(account.getScope_of_business());
		companyAccount.setBusiness_license(account.getBusiness_license());
		companyAccountDAO.update(companyAccount);
		
	}
	
	/**
	 * @param id
	 * @param str
	 */
	public void updateComStatus(Integer id, String str) {
		Company company = companyDAO.get(Company.class, id);
		if ("冻结".equals(str)) {
			company.setComStatus(0);
		}else if("解冻".equals(str)) {
			company.setComStatus(1);
		}
		companyDAO.update(company);
	}

	/**
	 * @param dataMap 
	 * @param id
	 * @return
	 */
	public void reminded2Pay(Integer companyId, Map<String, Object> dataMap) {
		Company company = companyDAO.get(Company.class, companyId);
		Date lastSendTime = company.getSendEmailTime();
		if (lastSendTime != null) {
			Calendar cl = Calendar.getInstance();
			cl.setTime(lastSendTime);
			long from = cl.getTimeInMillis();
			cl.setTime(new Date());
			long to = cl.getTimeInMillis();
			long ms = to - from;
			int hours = (int) ((to - from)/(1000 * 60 * 60));  
			int minutes = (int) ((to - from)/(1000 * 60));
			int second = (int) ((to - from)/(1000));
			if (hours < 24) {
				dataMap.put("str", "已经提醒过了，请在"+TimeUtil.transformTime(86400000-ms)+"后再来提醒");
				dataMap.put("code", -1);
			}else{
				sendEmail(company.getComEmail(),dataMap);
				company.setSendEmailTime(new Date());
				companyDAO.update(company);
			}
		}else {
			sendEmail(company.getComEmail(),dataMap);
			company.setSendEmailTime(new Date());
			companyDAO.update(company);
		}
		
		
		
	}
	
	private void sendEmail(String email,Map<String, Object> dataMap){
		//发送邮件
		String text = "  您好，您在我们公司购买的'企业展示小程序'服务就要到期了，请联系客服继续购买服务，逾期后小程序将暂停服务。<br/>公司电话：13610012468";
		try {
			SendEmailUtil.sendMail(email, "企业展示小程序到期通知", text);
			dataMap.put("code", 0);
		} catch (Exception e) {
			dataMap.put("code", -2);
			e.printStackTrace();
		}
	}
	
	
	/*********************************** setter and getter ****************************************/

	
	public ICompanyAccountDAO getCompanyAccountDAO() {
		return companyAccountDAO;
	}

	public void setCompanyAccountDAO(ICompanyAccountDAO companyAccountDAO) {
		this.companyAccountDAO = companyAccountDAO;
	}

	public IAdminAccountDAO getAdminAccountDAO() {
		return adminAccountDAO;
	}

	public void setAdminAccountDAO(IAdminAccountDAO adminAccountDAO) {
		this.adminAccountDAO = adminAccountDAO;
	}

	public ICompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(ICompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public IModuleDAO getModuleDAO() {
		return moduleDAO;
	}

	public IWxPlatformDAO getWxPlatformDAO() {
		return wxPlatformDAO;
	}

	public void setWxPlatformDAO(IWxPlatformDAO wxPlatformDAO) {
		this.wxPlatformDAO = wxPlatformDAO;
	}

	public void setModuleDAO(IModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}




	
}
