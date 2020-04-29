package com.nature.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nature.dao.base.ISystemUserDAO;
import com.nature.dao.company.ICompanyAccountDAO;
import com.nature.dao.company.ICompanyActivitiesDAO;
import com.nature.dao.company.ICompanyBusinessDAO;
import com.nature.dao.company.ICompanyContactPhoneDAO;
import com.nature.dao.company.ICompanyContactQQDAO;
import com.nature.dao.company.ICompanyDAO;
import com.nature.dao.company.ICompanyHonorsDAO;
import com.nature.dao.company.ICompanyImageDAO;
import com.nature.dao.company.ICompanyJobInfoDAO;
import com.nature.dao.company.ICompanyPartnersDAO;
import com.nature.dao.company.ICompanyPosterDAO;
import com.nature.dao.company.ICompanyShowVedioDAO;
import com.nature.dao.company.ICompanyWeiXinDAO;
import com.nature.dao.company.IModuleDAO;
import com.nature.dao.manage.IProductVisitDAO;
import com.nature.dao.manage.IUserVisitDAO;
import com.nature.dao.product.IProductDAO;
import com.nature.model.account.SystemUser;
import com.nature.model.company.Company;
import com.nature.model.company.CompanyAccount;
import com.nature.model.company.CompanyActivities;
import com.nature.model.company.CompanyBusiness;
import com.nature.model.company.CompanyContactPhone;
import com.nature.model.company.CompanyContactQQ;
import com.nature.model.company.CompanyHonors;
import com.nature.model.company.CompanyImage;
import com.nature.model.company.CompanyJobInfo;
import com.nature.model.company.CompanyPartners;
import com.nature.model.company.CompanyPoster;
import com.nature.model.company.CompanyShowVedio;
import com.nature.model.company.CompanyWeiXin;
import com.nature.model.manage.ProductVisit;
import com.nature.model.manage.UserVisit;
import com.nature.model.module.Module_on_off;
import com.nature.model.product.Product;
import com.nature.util.GetLatAndLngUtil;


@Service
public class CompanyService {

	@Resource
	private ICompanyDAO companyDAO;
	@Resource
	private ISystemUserDAO systemUserDAO;
	@Resource
	private ICompanyBusinessDAO companyBusinessDAO;
	@Resource
	private ICompanyHonorsDAO companyHonorsDAO;
	@Resource
	private ICompanyPartnersDAO companyPartnersDAO;
	@Resource
	private ICompanyJobInfoDAO companyJobInfoDAO;
	@Resource
	private ICompanyAccountDAO companyAccountDAO;
	@Resource
	private ICompanyActivitiesDAO companyActivitiesDAO;
	@Resource
	private ICompanyContactQQDAO companyContactQQDAO;
	@Resource
	private ICompanyContactPhoneDAO companyContactPhoneDAO;
	@Resource 
	private ICompanyShowVedioDAO companyShowVedioDAO;
	@Resource
	private ICompanyImageDAO companyImageDAO;
	@Resource
	private ICompanyPosterDAO companyPosterDAO;
	@Resource
	private IModuleDAO moduleDAO;
	@Resource
	private IProductDAO productDAO;
	@Resource
	private ICompanyWeiXinDAO companyWeiXinDAO;
	@Resource
	private IUserVisitDAO userVisitDAO;
	@Resource
	private IProductVisitDAO productVisitDAO;
	
	
	public List<Product> queryHotProduct(Integer companyId) {
		return productDAO.queryHotProductByComId(companyId);
	}

	/**
	 * @param id
	 */
	public void delPartner(Integer id) {
		companyPartnersDAO.delete(companyPartnersDAO.get(CompanyPartners.class, id));
	}
	
	/**
	 * @param ids
	 */
	public void delHonors(List<Integer> ids) {
		for (Integer id : ids) {
			companyHonorsDAO.delete(companyHonorsDAO.get(CompanyHonors.class, id));
		} 
		
	}

	/**
	 * @param sessionAccount
	 * @param picUrl
	 */
	public void savepartner(Integer companyId, String picUrl) {
		CompanyPartners partner = new CompanyPartners();
		Company company = companyDAO.get(Company.class, companyId);
		partner.setPartnerLogo(picUrl);
		partner.setCompany(company);
		companyPartnersDAO.save(partner);
	}
	/**
	 * @param sessionAccount
	 * @param picUrl
	 */
	public void saveHonor(Integer companyId, String picUrl) {
		CompanyHonors honor = new CompanyHonors();
		Company company = companyDAO.get(Company.class, companyId);
		honor.setCompany(company);
		honor.setChPic(picUrl);
		companyHonorsDAO.save(honor);
	}

	/**
	 * @param ids
	 */
	public void editHotProduct(List<Integer> ids) {
		Product product = null;
		for (Integer id : ids) {
			product = productDAO.get(Product.class, id);
			product.setHot(0);
			productDAO.update(product);
		}
		
	}
	
	/**
	 * @param sessionAccount
	 * @param dataMap
	 */
	public void queryBusiness(Integer companyId, Map<String, Object> dataMap) {
		CompanyBusiness business = companyBusinessDAO.queryBusiness(companyId);
		String hql = "from Module_on_off where company.companyId = "+companyId;
		Module_on_off onoff = moduleDAO.find(hql).get(0);
		dataMap.put("business", business);
		if (onoff.isBusiness_on_off()) {
			dataMap.put("onoff", 1);
		}else {
			dataMap.put("onoff", 0);
			
		}
	}


	/**
	 * @param companyId 
	 * @param business
	 */
	public void saveOrUpdateBusiness(Integer companyId, CompanyBusiness business) {
		Company company = companyDAO.get(Company.class, companyId);
		business.setCompany(company);
		companyBusinessDAO.saveOrUpdate(business);
		
	}



	public void detailActivity(Integer id, Map<String, Object> dataMap) {
		CompanyActivities activity = companyActivitiesDAO.get(CompanyActivities.class, id);
		dataMap.put("activity", activity);
		
	}
	
	
	/**
	 * @param sCcode
	 * @return
	 */
	public CompanyAccount getComAccountById(String sCcode) {
		CompanyAccount account;
		try {
			account = companyAccountDAO.get(CompanyAccount.class, sCcode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}

	/**
	 * @param sessionAccount
	 * @return
	 */
	public int checkComImageNum(Integer companyId) {
		return companyImageDAO.checkComImageNum(companyId);
	}
	
	/**
	 * @param sessionAccount
	 */
	public void delCompanyImage(Integer companyId) {
		CompanyImage image = companyImageDAO.get(CompanyImage.class, companyId);
		companyImageDAO.delete(image);
	}

	/**
	 * @param sessionAccount
	 * @return
	 */
	public int checkPosterNum(Integer companyId) {
		return companyPosterDAO.checkPosterNum(companyId);
	}
	
	public List<String> queryCompanyImage(Integer companyId, Map<String, Object> dataMap) {
		String hql = "from Module_on_off where company.companyId = "+companyId;
		Module_on_off onoff = moduleDAO.find(hql).get(0);
		if (onoff.isComImage_on_off()) {
			dataMap.put("onoff", 1);
		}else {
			dataMap.put("onoff", 0);
		}
		
		return companyImageDAO.queryCompanyImage(companyId);
	}
	
	/**
	 * @param id
	 * @return
	 */
	public CompanyActivities findOneActivity(Integer id) {
		return companyActivitiesDAO.get(CompanyActivities.class, id);
	}
	
	/**
	 * @param companyActivities
	 */
	public void updateComActivity(CompanyActivities companyActivities) {
		CompanyActivities activity = companyActivitiesDAO.get(CompanyActivities.class, companyActivities.getCompanyActivitiesId());
		activity.setActivityTitle(companyActivities.getActivityTitle());
		activity.setActivityContent(companyActivities.getActivityContent());
		activity.setActivityTime(companyActivities.getActivityTime());
		companyActivitiesDAO.update(activity);
	}
	
	/**
	 * @param id 
	 * @param vedio
	 */
	public void addVedio(Integer id, CompanyShowVedio vedio) {
		Company company = companyDAO.get(Company.class, id);
		vedio.setCompany(company);
		vedio.setVedioCreateTime(new Date());
		companyShowVedioDAO.save(vedio);
	}
	
	/**
	 * @param vedio
	 */
	public void updateVedio(CompanyShowVedio vedio) {
		CompanyShowVedio v = companyShowVedioDAO.get(CompanyShowVedio.class, vedio.getCompanyShowVedioId());
		v.setVedioTitle(vedio.getVedioTitle());
		v.setVedioUrl(vedio.getVedioUrl());
		companyShowVedioDAO.update(v);
	}
	
	/**
	 * @param dataMap 
	 * @param sessionAccount
	 * @return
	 */
	public CompanyShowVedio findVedio(Integer companyId, Map<String, Object> dataMap) {
		String hql = "from Module_on_off where company.companyId = "+companyId;
		Module_on_off onoff = moduleDAO.find(hql).get(0);
		if (onoff.isComVedio_on_off()) {
			dataMap.put("onoff", 1);
		}else {
			dataMap.put("onoff", 0);
			
		}
		return companyShowVedioDAO.findVedio(companyId);
	}
	
	/**
	 * @param id
	 */
	public void delComActivity(Integer id) {
		companyActivitiesDAO.delete(companyActivitiesDAO.get(CompanyActivities.class, id));
	}
	
	/**
	 * @param sessionAccount
	 * @param companyActivities
	 */
	public void addComActivity(Integer companyId, CompanyActivities companyActivities) {
		Company company = companyDAO.get(Company.class, companyId);
		if(companyActivities.getActivityTitlePic().length()<20){
			companyActivities.setActivityTitlePic(null);
		}
		companyActivities.setCompany(company);
		companyActivities.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		companyActivitiesDAO.save(companyActivities);
	}
	
	public List<CompanyActivities> queryComActivities(Integer companyId, Map<String, Object> dataMap) {
		String hql = "from Module_on_off where company.companyId = "+companyId;
		Module_on_off onoff = moduleDAO.find(hql).get(0);
		if (onoff.isComActivity_on_off()) {
			dataMap.put("onoff", 1);
		}else {
			dataMap.put("onoff", 0);
		}
		
		Company company = companyDAO.get(Company.class, companyId);
		return companyActivitiesDAO.queryComActivities(company.getCompanyId());
	}
	
	/**
	 * @param company
	 * @param posters 
	 */
	public void updateComInfo(Company company, String[] companyContactPhones, String[] posters) {
		Company com = companyDAO.get(Company.class, company.getCompanyId());
		com.setComLogo(company.getComLogo());
		com.setComName(company.getComName());
		com.setComProvince(company.getComProvince());
		com.setComCity(company.getComCity());
		com.setComDistrict(company.getComDistrict());
		
		//获取经纬度
		Map<String, BigDecimal> map = GetLatAndLngUtil.getLatAndLngByAddressFTX(company.getComAddr());
		com.setComLongitude(map.get("lng").doubleValue());
		com.setComLatitude(map.get("lat").doubleValue());                                            
		
		
		
		com.setComAddr(company.getComAddr());
		com.setComProfile(company.getComProfile());
		companyDAO.update(com);
		List<CompanyContactPhone> comPhones = companyContactPhoneDAO.queryCompanyContactPhoneByComId(company.getCompanyId());
		CompanyContactPhone phone = null;
		if (companyContactPhones.length > 0) {
			for (int i = 0; i < companyContactPhones.length; i++) {
				boolean isNew = true;
				for (int j = 0; j < comPhones.size(); j++) {
					boolean isDel = true;
					if (companyContactPhones[i].equals(comPhones.get(j).getComPhone())) {
						isNew = false;
					}
					for (int k = 0; k < companyContactPhones.length; k++) {
						if(comPhones.get(j).getComPhone().equals(companyContactPhones[k])){
							isDel = false;
						}
					}
					if (isDel) {
						companyContactPhoneDAO.delete(comPhones.get(j));
					}
				}
				if (isNew) {
					phone = new CompanyContactPhone();
					phone.setCompany(company);
					phone.setComPhone(companyContactPhones[i]);
					companyContactPhoneDAO.save(phone);
				}
			}
		}else {
			for (CompanyContactPhone companyContactPhone : comPhones) {
				companyContactPhoneDAO.delete(companyContactPhone);
			}
		}
		
		List<CompanyPoster> companyPosters = companyPosterDAO.queryPosterByComId(com.getCompanyId());
		CompanyPoster companyPoster = null;
		//如果posters.size()==0?
		if(posters.length>0){
			//添加新加的
			for (int i = 0; i < posters.length; i++) {
				boolean isNew = true;
				for (int j = 0; j < companyPosters.size(); j++) {
					if (posters[i].equals(companyPosters.get(j).getPosterUrl())) {
						isNew = false;
					}
					
					
				}
				if(isNew){
					companyPoster = new CompanyPoster();
					companyPoster.setCompany(company);
					companyPoster.setPosterUrl(posters[i]);
					companyPosterDAO.save(companyPoster);
				}
			}
			
			for (int i = 0; i < companyPosters.size(); i++) {
				boolean isDel = true;
				for (int j = 0; j < posters.length; j++) {
					if (companyPosters.get(i).getPosterUrl().equals(posters[i])) {
						isDel = false;
					}
				}
				if (isDel) {
					companyPosterDAO.delete(companyPosters.get(i));
				}
			}
		}else {
			for (CompanyPoster cp : companyPosters) {
				companyPosterDAO.delete(cp);
			}
		}
	}
	
	/**
	 * @param id
	 * @param dataMap
	 */
	public void showJobInfo(Integer id, Map<String, Object> dataMap) {
		CompanyJobInfo companyJobInfo = companyJobInfoDAO.get(CompanyJobInfo.class, id);
		dataMap.put("companyJobInfo", companyJobInfo);
		
	}
	
	public void showActivity(Integer id, Map<String, Object> dataMap) {
		CompanyActivities companyActivities = companyActivitiesDAO.get(CompanyActivities.class, id);
		dataMap.put("companyActivities", companyActivities);
	}
	
	public Company login(String account, String password){
		List<CompanyAccount> companyAccounts = companyAccountDAO.queryComAccountByAccountAndPassword(account, password);
		if (companyAccounts.size() > 0) {
			return companyAccounts.get(0).getCompany();
		}else {
			return null;
		}
	}
	
	public void queryCompanyInfoByComId(Integer companyId, Map<String, Object> dataMap) {
		Company company = companyDAO.get(Company.class, companyId);
		List<CompanyPoster> companyPosters = companyPosterDAO.queryPosterByComId(companyId);
		//联系
		List<CompanyContactQQ> qq = companyContactQQDAO.queryCompanyContactQQByComId(companyId);
		List<CompanyContactPhone> phone = companyContactPhoneDAO.queryCompanyContactPhoneByComId(companyId);
		List<CompanyWeiXin> weiXins = companyWeiXinDAO.queryComWeixinByComId(companyId);
		dataMap.put("company", company);
		dataMap.put("posters", companyPosters);
		dataMap.put("qq", qq);
		dataMap.put("phone", phone);
		dataMap.put("weixins", weiXins);
	}
	
	public void queryCompanyInfo(String SCcode, Map<String, Object> dataMap){
		Company company = companyAccountDAO.getCompanyByAccountId(SCcode);
		Integer companyId = company.getCompanyId();
		CompanyBusiness companyBusiness = companyBusinessDAO.getCompanyBusinessByComId(companyId);
		List<CompanyHonors> companyHonors = companyHonorsDAO.queryCompanyHonorsByComId(companyId);
		List<CompanyPartners> companyPartners = companyPartnersDAO.queryCompanyPartnersByComId(companyId);
		List<CompanyJobInfo> companyJobInfo = companyJobInfoDAO.queryCompanyJobInfoByComId(companyId);
		List<CompanyContactQQ> companyContactQQ = companyContactQQDAO.queryCompanyContactQQByComId(companyId);
		List<CompanyContactPhone> companyContactPhone = companyContactPhoneDAO.queryCompanyContactPhoneByComId(companyId);
		List<CompanyPoster> companyPosters = companyPosterDAO.queryPosterByComId(companyId);
		List<Product> products = productDAO.queryHotProductByComId(companyId);
		Module_on_off module = moduleDAO.getModuleByComId(companyId);
		CompanyShowVedio vedio = companyShowVedioDAO.findVedio(companyId);
		List<CompanyActivities> companyActivities = companyActivitiesDAO.queryComActivities(companyId); 
		List<String> companyImages = companyImageDAO.queryCompanyImage(companyId);
		dataMap.put("company", company);
		dataMap.put("module", module);
		dataMap.put("products", products);
		dataMap.put("vedio", vedio);
		dataMap.put("companyImages", companyImages);
		dataMap.put("companyActivities", companyActivities);
		dataMap.put("companyContactQQ", companyContactQQ);
		dataMap.put("companyContactPhone", companyContactPhone);
		dataMap.put("companyBusiness", companyBusiness);
		dataMap.put("companyHonors", companyHonors);
		dataMap.put("companyPartners", companyPartners);
		dataMap.put("companyJobInfo", companyJobInfo);
		dataMap.put("companyPosters", companyPosters);
	}

	
	
	/*********************************** setter and getter ****************************************/

	public ICompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public IProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(IProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public IModuleDAO getModuleDAO() {
		return moduleDAO;
	}

	public void setModuleDAO(IModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}

	public ICompanyImageDAO getCompanyImageDAO() {
		return companyImageDAO;
	}

	public ICompanyPosterDAO getCompanyPosterDAO() {
		return companyPosterDAO;
	}

	public void setCompanyImageDAO(ICompanyImageDAO companyImageDAO) {
		this.companyImageDAO = companyImageDAO;
	}

	public void setCompanyPosterDAO(ICompanyPosterDAO companyPosterDAO) {
		this.companyPosterDAO = companyPosterDAO;
	}

	public ICompanyShowVedioDAO getCompanyShowVedioDAO() {
		return companyShowVedioDAO;
	}

	public void setCompanyShowVedioDAO(ICompanyShowVedioDAO companyShowVedioDAO) {
		this.companyShowVedioDAO = companyShowVedioDAO;
	}

	public ICompanyAccountDAO getCompanyAccountDAO() {
		return companyAccountDAO;
	}



	public IProductVisitDAO getProductVisitDAO() {
		return productVisitDAO;
	}

	public void setProductVisitDAO(IProductVisitDAO productVisitDAO) {
		this.productVisitDAO = productVisitDAO;
	}

	public IUserVisitDAO getUserVisitDAO() {
		return userVisitDAO;
	}

	public void setUserVisitDAO(IUserVisitDAO userVisitDAO) {
		this.userVisitDAO = userVisitDAO;
	}

	public ICompanyActivitiesDAO getCompanyActivitiesDAO() {
		return companyActivitiesDAO;
	}

	public void setCompanyActivitiesDAO(ICompanyActivitiesDAO companyActivitiesDAO) {
		this.companyActivitiesDAO = companyActivitiesDAO;
	}

	public void setCompanyAccountDAO(ICompanyAccountDAO companyAccountDAO) {
		this.companyAccountDAO = companyAccountDAO;
	}



	public ICompanyWeiXinDAO getCompanyWeiXinDAO() {
		return companyWeiXinDAO;
	}

	public void setCompanyWeiXinDAO(ICompanyWeiXinDAO companyWeiXinDAO) {
		this.companyWeiXinDAO = companyWeiXinDAO;
	}

	public ISystemUserDAO getSystemUserDAO() {
		return systemUserDAO;
	}


	public ICompanyBusinessDAO getCompanyBusinessDAO() {
		return companyBusinessDAO;
	}


	public ICompanyHonorsDAO getCompanyHonorsDAO() {
		return companyHonorsDAO;
	}


	public ICompanyPartnersDAO getCompanyPartnersDAO() {
		return companyPartnersDAO;
	}


	public ICompanyJobInfoDAO getCompanyJobInfoDAO() {
		return companyJobInfoDAO;
	}


	public void setCompanyDAO(ICompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}


	public void setSystemUserDAO(ISystemUserDAO systemUserDAO) {
		this.systemUserDAO = systemUserDAO;
	}


	public void setCompanyBusinessDAO(ICompanyBusinessDAO companyBusinessDAO) {
		this.companyBusinessDAO = companyBusinessDAO;
	}


	public void setCompanyHonorsDAO(ICompanyHonorsDAO companyHonorsDAO) {
		this.companyHonorsDAO = companyHonorsDAO;
	}


	public void setCompanyPartnersDAO(ICompanyPartnersDAO companyPartnersDAO) {
		this.companyPartnersDAO = companyPartnersDAO;
	}

	public ICompanyContactQQDAO getCompanyContactQQDAO() {
		return companyContactQQDAO;
	}

	public ICompanyContactPhoneDAO getCompanyContactPhoneDAO() {
		return companyContactPhoneDAO;
	}

	public void setCompanyContactQQDAO(ICompanyContactQQDAO companyContactQQDAO) {
		this.companyContactQQDAO = companyContactQQDAO;
	}

	public void setCompanyContactPhoneDAO(ICompanyContactPhoneDAO companyContactPhoneDAO) {
		this.companyContactPhoneDAO = companyContactPhoneDAO;
	}

	public void setCompanyJobInfoDAO(ICompanyJobInfoDAO companyJobInfoDAO) {
		this.companyJobInfoDAO = companyJobInfoDAO;
	}

	/**
	 * @param sessionAccount
	 * @param job
	 */
	public void addJob(Integer companyId, CompanyJobInfo job) {
		Company company = companyDAO.get(Company.class, companyId);
		job.setCompany(company);
		job.setCreatTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		companyJobInfoDAO.save(job);
	}

	/**
	 * @param id
	 * @return
	 */
	public CompanyJobInfo findOneJob(Integer id) {
		return companyJobInfoDAO.get(CompanyJobInfo.class, id);
	}

	/**
	 * @param dataMap 
	 * @param sessionAccount
	 * @return
	 */
	public List<CompanyJobInfo> findJob(Integer companyId, Map<String, Object> dataMap) {
		String hql2 = "from Module_on_off where company.companyId = "+companyId;
		Module_on_off onoff = moduleDAO.find(hql2).get(0);
		if (onoff.isComJob_on_off()) {
			dataMap.put("onoff", 1);
		}else {
			dataMap.put("onoff", 0);
			
		}
		String hql ="from CompanyJobInfo where company.companyId = "+companyId+" order by companyJobInfoId desc";
		List<CompanyJobInfo> jobInfos = companyJobInfoDAO.find(hql);
		 return jobInfos;
	}

	/**
	 * @param sessionAccount
	 * @param company
	 * @param comQQs 
	 * @param comPhones 
	 */
	public void updateCompanyInfo(Integer companyId, Company company, String[] phoneStrs, String[] weixinStrs,String[] qqStrs){
		Company companyInfo = companyDAO.get(Company.class, companyId);
		List<CompanyContactQQ> qqs = companyContactQQDAO.queryCompanyContactQQByComId(companyId);
		List<CompanyContactPhone> phones = companyContactPhoneDAO.queryCompanyContactPhoneByComId(companyId);
		List<CompanyWeiXin> weiXins = companyWeiXinDAO.queryComWeixinByComId(companyId);
		companyInfo.setComContact(company.getComContact());
		companyInfo.setComContactLogo(company.getComContactLogo());
		companyInfo.setComName(company.getComName());
		companyInfo.setComFax(company.getComFax());
		companyInfo.setComEmail(company.getComEmail());
		companyInfo.setComAddr(company.getComAddr());
		companyInfo.setComQRcode(company.getComQRcode());
		companyDAO.update(companyInfo);
		
		//添加新的电话
		if (phoneStrs != null && phoneStrs.length>0) {
			CompanyContactPhone phone = null;
			for (int i = 0; i < phoneStrs.length; i++) {
				boolean isNew = true;
				for (int j = 0; j < phones.size(); j++) {
					if (phoneStrs[i].equals(phones.get(j).getComPhone())) {
						isNew = false;
						phones.get(j).setSequence(i);//更新序号
						companyContactPhoneDAO.update(phones.get(j));
					}
					
				}
				if (isNew && phoneStrs[i].length() > 0) {//防止空字符串造成业务异常
					phone = new CompanyContactPhone();
					phone.setCompany(company);
					phone.setComPhone(phoneStrs[i]);
					phone.setSequence(i);//更新序号
					companyContactPhoneDAO.save(phone);
				}
			}
			
		}
		//删除被删除的电话
		if (phones != null && phones.size() > 0) {
			for (int i = 0; i < phones.size(); i++) {
				boolean isDel = true;
				for (int j = 0; j < phoneStrs.length; j++) {
					if (phones.get(i).getComPhone().equals(phoneStrs[j])) {
						isDel = false;
					}
				}
				if (isDel) {
					companyContactPhoneDAO.delete(phones.get(i));
				}
			}
		}
		
		//添加新的qq
				if (qqStrs != null && qqStrs.length>0) {
					CompanyContactQQ qq = null;
					for (int i = 0; i < qqStrs.length; i++) {
						boolean isNew = true;
						for (int j = 0; j < qqs.size(); j++) {
							if (qqStrs[i].equals(qqs.get(j).getComQQ())) {
								isNew = false;
								qqs.get(j).setSequence(i);//更新序号
								companyContactQQDAO.update(qqs.get(j));
							}
							
						}
						if (isNew && qqStrs[i].length() > 0) {//防止空字符串造成业务异常
							qq = new CompanyContactQQ();
							qq.setCompany(company);
							qq.setComQQ(qqStrs[i]);
							qq.setSequence(i);//更新序号
							companyContactQQDAO.save(qq);
						}
					}
					
				}
				//删除被删除的qq
				if (qqs != null && qqs.size() > 0) {
					for (int i = 0; i < qqs.size(); i++) {
						boolean isDel = true;
						for (int j = 0; j < qqStrs.length; j++) {
							if (qqs.get(i).getComQQ().equals(qqStrs[j])) {
								isDel = false;
							}
						}
						if (isDel) {
							companyContactQQDAO.delete(qqs.get(i));
						}
					}
				}		
				
				//添加新的微信
				if (weixinStrs != null && weixinStrs.length>0) {
					CompanyWeiXin weixin = null;
					for (int i = 0; i < weixinStrs.length; i++) {
						boolean isNew = true;
						for (int j = 0; j < weiXins.size(); j++) {
							if (weixinStrs[i].equals(weiXins.get(j).getWeixin())) {
								isNew = false;
								weiXins.get(j).setSequence(i);//更新序号
								companyWeiXinDAO.update(weiXins.get(j));
							}
							
						}
						if (isNew && weixinStrs[i].length() > 0) {//防止空字符串造成业务异常
							weixin = new CompanyWeiXin();
							weixin.setCompany(company);
							weixin.setWeixin(weixinStrs[i]);
							weixin.setSequence(i);//更新序号
							companyWeiXinDAO.save(weixin);
						}
					}
					
				}
				//删除被删除的微信
				if (weiXins != null && weiXins.size() > 0) {
					for (int i = 0; i < weiXins.size(); i++) {
						boolean isDel = true;
						for (int j = 0; j < weixinStrs.length; j++) {
							if (weiXins.get(i).getWeixin().equals(weixinStrs[j])) {
								isDel = false;
							}
						}
						if (isDel) {
							companyWeiXinDAO.delete(weiXins.get(i));
						}
					}
				}		
		
	}

	/**
	 * @param id
	 */
	public void delPhone(Integer id) {
		companyContactPhoneDAO.delete(companyContactPhoneDAO.get(CompanyContactPhone.class, id));
	}

	/**
	 * @param id
	 */
	public void delQQ(Integer id) {
		companyContactQQDAO.delete(companyContactQQDAO.get(CompanyContactQQ.class, id));
		
	}

	/**
	 * @param sessionAccount
	 * @param dataMap
	 */
	public CompanyAccount findComAccount(Integer companyId, Map<String, Object> dataMap) {
		CompanyAccount account = companyAccountDAO.findComAccount(companyId);
		dataMap.put("account", account);
		return account;
	}

	/**
	 * @param sessionAccount
	 * @param str
	 */
	public int updateAccountPassword(Integer companyId, String str) {
		String[] strs = str.split(",");
		CompanyAccount account = companyAccountDAO.findComAccount(companyId);
		if (account.getLoginPassword().equals(strs[0])) {
			account.setLoginPassword(strs[1]);
			companyAccountDAO.update(account);
			return 0;
		}else {
			return -1;
		}
		
	}

	/**
	 * @param sessionAccount
	 * @param strings
	 */
	public void updateCompanyImage(Integer companyId, String[] strings) {
		
		Company company = companyDAO.get(Company.class, companyId);
		List<CompanyImage > comImgs =  companyImageDAO.queryComImage(companyId);
		//删除已有图片
		if (comImgs != null && comImgs.size()>0) {
			for (int i = 0; i < comImgs.size(); i++) {
				boolean isDel = true;
				for (int j = 0; j < strings.length; j++) {
					if (comImgs.get(i).getImgUrl().equals(strings[j])) {
						isDel = false;
					}
				}
				if (isDel) {
					companyImageDAO.delete(comImgs.get(i));
				}
			}
		}
		//添加没有的图片
		if (strings != null && strings.length>0) {
			CompanyImage companyImage = null;
			for (int i = 0; i < strings.length; i++) {
				boolean isNew = true;
				for (int j = 0; j < comImgs.size(); j++) {
					if (strings[i].equals(comImgs.get(j).getImgUrl())) {
						isNew = false;
					}
				}
				if (isNew) {
					companyImage = new CompanyImage();
					companyImage.setCompany(company);
					companyImage.setImgUrl(strings[i]);
					companyImageDAO.save(companyImage);
				}
			}
		}
	}

	/**
	 * @param dataMap 
	 * @param sessionAccount
	 */
	public List<CompanyHonors> queryHonorsImage(Integer companyId, Map<String, Object> dataMap) {
		String hql = "from Module_on_off where company.companyId = "+companyId;
		Module_on_off onoff = moduleDAO.find(hql).get(0);
		if (onoff.isComHonors_on_off()) {
			dataMap.put("onoff", 1);
		}else {
			dataMap.put("onoff", 0);
			
		}
		List<CompanyHonors> honors = companyHonorsDAO.queryCompanyHonorsByComId(companyId);
		return honors;
	}

	/**
	 * @param sessionAccount
	 * @param honors
	 */
	public void updateHonorsImage(Integer companyId, String[] honorUrls) {
		Company company = companyDAO.get(Company.class, companyId);
		List<CompanyHonors> honorsA = companyHonorsDAO.queryCompanyHonorsByComId(companyId);
		CompanyHonors honor = null;
		//添加新图片
		if (honorUrls != null && honorUrls.length>0) {
			for (int i = 0; i < honorUrls.length; i++) {
				boolean isNew = true;
				for (int j = 0; j < honorsA.size(); j++) {
					if (honorUrls[i].equals(honorsA.get(j).getChPic())) {
						isNew = false;
						honorsA.get(j).setSequence(i);//更新序号
						companyHonorsDAO.update(honorsA.get(j));
					}
					
				}
				if (isNew && honorUrls[i].length() > 0) {//防止空字符串造成业务异常
					honor = new CompanyHonors();
					honor.setCompany(company);
					honor.setChPic(honorUrls[i]);
					honor.setSequence(i);//更新序号
					companyHonorsDAO.save(honor);
				}
			}
		}
		//删除被删除的图片
		if (honorsA != null && honorsA.size() > 0) {
			for (int i = 0; i < honorsA.size(); i++) {
				boolean isDel = true;
				for (int j = 0; j < honorUrls.length; j++) {
					if (honorsA.get(i).getChPic().equals(honorUrls[j])) {
						isDel = false;
					}
					
				}
				if (isDel) {
					companyHonorsDAO.delete(honorsA.get(i));
				}
			}
		}
	}

	public List<CompanyPartners> queryPartnerImage(Integer companyId, Map<String, Object> dataMap) {
		String hql = "from Module_on_off where company.companyId = "+companyId;
		Module_on_off onoff = moduleDAO.find(hql).get(0);
		if (onoff.isComPartner_on_off()) {
			dataMap.put("onoff", 1);
		}else {
			dataMap.put("onoff", 0);
			
		}
		
		List<CompanyPartners> partners = companyPartnersDAO.queryCompanyPartnersByComId(companyId);
		return partners;
	}

	public void updatePartnerImage(Integer companyId, String[] partnerStrs) {
		Company company = companyDAO.get(Company.class, companyId);
		List<CompanyPartners> partners = companyPartnersDAO.queryCompanyPartnersByComId(companyId);
		//添加新图片
		if (partnerStrs != null && partnerStrs.length>0) {
			CompanyPartners partner = null;
			for (int i = 0; i < partnerStrs.length; i++) {
				boolean isNew = true;
				for (int j = 0; j < partners.size(); j++) {
					if (partnerStrs[i].equals(partners.get(j).getPartnerLogo())) {
						isNew = false;
						partners.get(j).setSequence(i);//更新序号
						companyPartnersDAO.update(partners.get(j));
					}
					
				}
				if (isNew && partnerStrs[i].length() > 0) {//防止空字符串造成业务异常
					partner = new CompanyPartners();
					partner.setCompany(company);
					partner.setPartnerLogo(partnerStrs[i]);
					partner.setSequence(i);//更新序号
					companyPartnersDAO.save(partner);
				}
			}
			
		}
		//删除被删除的图片
		if (partners != null && partners.size() > 0) {
			for (int i = 0; i < partners.size(); i++) {
				boolean isDel = true;
				for (int j = 0; j < partnerStrs.length; j++) {
					if (partners.get(i).getPartnerLogo().equals(partnerStrs[j])) {
						isDel = false;
					}
					
				}
				if (isDel) {
					companyPartnersDAO.delete(partners.get(i));
				}
			}
		}
	}

	public void delJob(Integer id) {
		companyJobInfoDAO.delete(companyJobInfoDAO.get(CompanyJobInfo.class, id));
		
	}

	/**
	 * @param job
	 */
	public void updateJob(CompanyJobInfo job) {
		CompanyJobInfo j = companyJobInfoDAO.get(CompanyJobInfo.class, job.getCompanyJobInfoId());
		j.setCjiAddr(job.getCjiAddr());
		j.setCjiDetail(job.getCjiDetail());
		j.setCjiEdu(job.getCjiEdu());
		j.setCjiExp(job.getCjiExp());
		j.setCjiName(job.getCjiName());
		j.setCjiSalary(job.getCjiSalary());
		j.setCreatTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		companyJobInfoDAO.update(j);
	}

	/**
	 * @param sessionAccount
	 */
	public void businessOnOff(Integer companyId) {
		String hql = "from Module_on_off where company.companyId = "+companyId;
		Module_on_off onoff = moduleDAO.find(hql).get(0);
		boolean bonoff = onoff.isBusiness_on_off();
		if (bonoff) {
			onoff.setBusiness_on_off(false);
		}else {
			onoff.setBusiness_on_off(true);
		}
		moduleDAO.update(onoff);
	}

	/**
	 * @param sessionAccount
	 */
	public void vedioOnoff(Integer sessionAccount) {
		String hql = "from Module_on_off where company.companyId = "+sessionAccount;
		Module_on_off onoff = moduleDAO.find(hql).get(0);
		boolean bonoff = onoff.isComVedio_on_off();
		if (bonoff) {
			onoff.setComVedio_on_off(false);
		}else {
			onoff.setComVedio_on_off(true);
		}
		moduleDAO.update(onoff);
		
	}

	/**
	 * @param sessionAccount
	 */
	public void activityOnoff(Integer sessionAccount) {
		String hql = "from Module_on_off where company.companyId = "+sessionAccount;
		Module_on_off onoff = moduleDAO.find(hql).get(0);
		boolean bonoff = onoff.isComActivity_on_off();
		if (bonoff) {
			onoff.setComActivity_on_off(false);
		}else {
			onoff.setComActivity_on_off(true);
		}
		moduleDAO.update(onoff);
		
	}

	/**
	 * @param sessionAccount
	 */
	public void companyImageOnoff(Integer sessionAccount) {
		String hql = "from Module_on_off where company.companyId = "+sessionAccount;
		Module_on_off onoff = moduleDAO.find(hql).get(0);
		boolean bonoff = onoff.isComImage_on_off();
		if (bonoff) {
			onoff.setComImage_on_off(false);
		}else {
			onoff.setComImage_on_off(true);
		}
		moduleDAO.update(onoff);
	}

	/**
	 * @param sessionAccount
	 */
	public void honorsImageOnoff(Integer sessionAccount) {
		String hql = "from Module_on_off where company.companyId = "+sessionAccount;
		Module_on_off onoff = moduleDAO.find(hql).get(0);
		boolean bonoff = onoff.isComHonors_on_off();
		if (bonoff) {
			onoff.setComHonors_on_off(false);
		}else {
			onoff.setComHonors_on_off(true);
		}
		moduleDAO.update(onoff);
		
	}

	/**
	 * @param sessionAccount
	 */
	public void partnerOnoff(Integer sessionAccount) {
		String hql = "from Module_on_off where company.companyId = "+sessionAccount;
		Module_on_off onoff = moduleDAO.find(hql).get(0);
		boolean bonoff = onoff.isComPartner_on_off();
		if (bonoff) {
			onoff.setComPartner_on_off(false);
		}else {
			onoff.setComPartner_on_off(true);
		}
		moduleDAO.update(onoff);
		
	}

	/**
	 * @param sessionAccount
	 */
	public void jobOnoff(Integer sessionAccount) {
		String hql = "from Module_on_off where company.companyId = "+sessionAccount;
		Module_on_off onoff = moduleDAO.find(hql).get(0);
		boolean bonoff = onoff.isComJob_on_off();
		if (bonoff) {
			onoff.setComJob_on_off(false);
		}else {
			onoff.setComJob_on_off(true);
		}
		moduleDAO.update(onoff);
		
	}

	/**
	 * @param user
	 * @param sCcode
	 */
	public void userVisit(SystemUser user, String sCcode) {
		CompanyAccount account = companyAccountDAO.get(CompanyAccount.class, sCcode);
		System.out.println(">>>>>>>company>>>>>>>"+account.getCompany());
		UserVisit visit = new UserVisit();
		visit.setCompany(account.getCompany());
		visit.setUser(user);
		visit.setVisitTime(new Date());
		userVisitDAO.save(visit);
	}

	/**
	 * @param user
	 * @param sCcode 
	 * @param id
	 */
	public void productVisit(SystemUser user, Integer productId, String sCcode) {
		CompanyAccount account = companyAccountDAO.get(CompanyAccount.class, sCcode);
		Product product = productDAO.get(Product.class, productId);
		ProductVisit visit = new ProductVisit();
		visit.setProduct(product);
		visit.setUser(user);
		visit.setCompany(account.getCompany());
		visit.setVisitTime(new Date());
		productVisitDAO.save(visit);
	}

	/**
	 * @param type(1是日，2是周，3是月)
	 * @return 
	 */
	public List<Object> queryUserVisit(Integer companyId,Integer type) {
		StringBuffer sql = new StringBuffer();
		if (type == 1) {
//			sql.append("select DATE_FORMAT(visitTime,'%Y%m/%d') as days,count(userVisitId) from userVisit where company_companyId = "+companyId+" group by days");
			sql.append("SELECT DATE_FORMAT(a.click_date,'%Y-%m-%d'),ifnull(b.count,0) as count ");
			sql.append("from ( ");
			sql.append("SELECT date_sub(curdate(), interval 14 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT date_sub(curdate(), interval 13 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT date_sub(curdate(), interval 12 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT date_sub(curdate(), interval 11 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT date_sub(curdate(), interval 10 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT date_sub(curdate(), interval 9 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT date_sub(curdate(), interval 8 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT date_sub(curdate(), interval 7 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT date_sub(curdate(), interval 6 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT date_sub(curdate(), interval 5 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT date_sub(curdate(), interval 4 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT date_sub(curdate(), interval 3 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT date_sub(curdate(), interval 2 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT date_sub(curdate(), interval 1 day) as click_date ");
			sql.append("union all ");
			sql.append("SELECT curdate() as click_date ");
			sql.append(" ) a left join (");
			sql.append(" SELECT DATE_FORMAT(visitTime,'%Y-%m-%d') as days,count(userVisitId) as count from userVisit where company_companyId = "+companyId+" group by days");
			sql.append(" ) b on a.click_date = b.days");
		}else if(type == 2){
			//放弃，没什么必要做
			return null;
		}else if(type == 3){
//			sql.append("select DATE_FORMAT(visitTime,'%Y%m') as months,count(userVisitId) from userVisit where company_companyId = "+companyId+" group by months");
			sql.append("SELECT a.click_date,ifnull(b.count,0) as count");
			sql.append(" from ( ");
			sql.append(" SELECT DATE_FORMAT(date_sub(curdate(), interval 5 month),'%Y%m') as click_date");
			sql.append(" union all");
			sql.append(" SELECT DATE_FORMAT(date_sub(curdate(), interval 4 month),'%Y%m') as click_date");
			sql.append(" union all");
			sql.append(" SELECT DATE_FORMAT(date_sub(curdate(), interval 3 month),'%Y%m') as click_date");
			sql.append(" union all");
			sql.append(" SELECT DATE_FORMAT(date_sub(curdate(), interval 2 month),'%Y%m') as click_date");
			sql.append(" union all");
			sql.append(" SELECT DATE_FORMAT(date_sub(curdate(), interval 1 month),'%Y%m') as click_date");
			sql.append(" union all ");
			sql.append(" SELECT DATE_FORMAT(curdate(),'%Y%m') as click_date");//本月
			sql.append(") a left join (");
			sql.append(" SELECT DATE_FORMAT(visitTime,'%Y%m') as months,count(userVisitId) as count from userVisit where company_companyId = "+companyId+" group by months");
			sql.append(") b on a.click_date = b.months");
			
		}else {
			return null;
		}
		
		List<Object> visits = userVisitDAO.findBySql(sql.toString());
		return visits;
	}

	/**
	 * @param sessionAccount
	 * @return
	 */
	public List<UserVisit> queryTodayVisitor(Integer companyId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		String hql = "from UserVisit where company_companyId = "+companyId+" and visitTime between '"+date+" 00:00:00' and '"+date+" 23:59:59') ";
		List<UserVisit> visits = userVisitDAO.find(hql);
		
		return visits;
	}

	/**
	 * @param sessionAccount
	 * @return
	 */
	public List<Object> queryTodayProductVisited(Integer companyId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		String sql = "select p.productPic1,p.productNum,count(p.productId) from productVisit as pv,product as p where p.productId = pv.product_productId and pv.company_companyId = "+companyId+" and visitTime between '"+date+" 00:00:00' and '"+date+" 23:59:59' group by p.productId";
		List<Object> visits = userVisitDAO.findBySql(sql);
		return visits;
	}

	/**
	 * @param sessionAccount
	 */
	public void productOnoff(Integer sessionAccount) {
		String hql = "from Module_on_off where company.companyId = "+sessionAccount;
		Module_on_off onoff = null;
		try {
			onoff = moduleDAO.find(hql).get(0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean bonoff = onoff.isComProduct_on_off();
		if (bonoff) {
			onoff.setComProduct_on_off(false);
		}else {
			onoff.setComProduct_on_off(true);
		}
		moduleDAO.update(onoff);
		
	}

	

}
