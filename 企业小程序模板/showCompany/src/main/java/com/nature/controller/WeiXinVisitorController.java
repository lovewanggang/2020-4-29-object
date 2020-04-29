package com.nature.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.nature.action.BaseAction;
import com.nature.model.account.SystemUser;
import com.nature.service.CompanyService;
import com.nature.service.JobService;
import com.nature.service.ProductService;
import com.nature.service.SystemUserBLL;
import com.nature.util.EncrypDES;

@ParentPackage("json-default")
@Namespace("/visitor")
public class WeiXinVisitorController extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private CompanyService companyService;
	@Resource
	private SystemUserBLL systemUserBLL;
	@Resource
	private ProductService productService;
	@Resource
	private JobService jobService;
	
	private Integer id;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private String SCcode;
	private String logs;
	private String searchValue;
	
	@Action(value = "searchProduct", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String searchProduct(){
		SystemUser user = systemUserBLL.getSystemUser(logs);
		//更新历史搜索词，最多8个
		String history = user.getSearchHistory();
		if(searchValue != null && !(searchValue.equals(""))){
			if (history == null) {
				user.setSearchHistory(searchValue);
				systemUserBLL.updateSystemUser(user);
			}else {
				String[] historys = history.split(",");
				String str = searchValue;
				int count = 8;
				if (historys.length<8) {
					count = historys.length;
				}
				for (int i = 0; i < count; i++) {
					if (historys[i].equals(searchValue)) {
						continue;
					}
					str = str + "," + historys[i];
				}
				user.setSearchHistory(str);
				systemUserBLL.updateSystemUser(user);
				
			}
		}
		
		productService.searchProduct(searchValue,dataMap);
		return SUCCESS;
	}
	@Action(value = "querySearchHistory", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String querySearchHistory(){
		SystemUser user = systemUserBLL.getSystemUser(logs);
		String[] historys = user.getSearchHistory().split(",");
		List<String > historyList = new ArrayList<String>();
		for (String string : historys) {
			historyList.add(string);
		}
		dataMap.put("historyList", historyList);
		return SUCCESS;
	}
	
	@Action(value = "detailActivity", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String detailActivity(){
		companyService.detailActivity(id,dataMap);
		return SUCCESS;
	}
	@Action(value = "detailJob", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String detailJob(){
		jobService.detailJob(id,SCcode,dataMap);
		return SUCCESS;
	}
	@Action(value = "detailProduct", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String detailProduct(){
		SystemUser user = systemUserBLL.getSystemUser(logs);
		companyService.productVisit(user,id,SCcode);
		productService.detailProduct(id,dataMap);
		return SUCCESS;
	}
	
	@Action(value = "productHomePage", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String productHomePage(){
		productService.productHomePage(SCcode,dataMap);
		return SUCCESS;
	}
	
	@Action(value = "findProductClassByBCid", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String findProductClassByBCid(){
		productService.findProductClassByBCid(id,dataMap);
		return SUCCESS;
	}
	
	
	/**
	 * @param SCcode（companyAccountId）
	 * @return company对象
	 * @return companyBusiness对象
	 * @return companyHonors对象
	 * @return companyPartners对象
	 * @return companyJobInfo对象
	 */
	@Action(value = "showCompany", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String showCompany(){
		companyService.queryCompanyInfo(SCcode,dataMap);
		return SUCCESS;
	}
	
	/**
	 * 查看某个活动的文档
	 * @param id(活动文章的ID)
	 * @return companyActivities对象
	 */
	@Action(value = "showActivity", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String showActivity(){
		companyService.showActivity(id, dataMap);
		return SUCCESS;
	}
	
	/**
	 * 查看招聘的信息
	 * @param id(招聘的ID)
	 * @return companyJobInfo对象
	 */
	@Action(value = "showJobInfo", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String showJobInfo(){
		companyService.showJobInfo(id, dataMap);
		return SUCCESS;
	}
	
	/**
	 * 用户访问记录
	 * @param SCcode  logs
	 */
	@Action(value = "userVisit", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String userVisit() {
		SystemUser user = systemUserBLL.getSystemUser(logs);
		
		companyService.userVisit(user,SCcode);
		
		return SUCCESS;
	}
	
	/**
	 * 产品被访问记录
	 * @param SCcode  logs
	 */
	@Action(value = "productVisit", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String productVisit() {
		SystemUser user = systemUserBLL.getSystemUser(logs);
		
		companyService.productVisit(user,id,SCcode);
		
		return SUCCESS;
	}
	
	
	/*********************************** setter and getter ****************************************/

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}

	public JobService getJobService() {
		return jobService;
	}
	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}
	public SystemUserBLL getSystemUserBLL() {
		return systemUserBLL;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public ProductService getProductService() {
		return productService;
	}

	public Integer getId() {
		return id;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public String getSCcode() {
		return SCcode;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public void setSystemUserBLL(SystemUserBLL systemUserBLL) {
		this.systemUserBLL = systemUserBLL;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public void setSCcode(String sCcode) {
		SCcode = sCcode;
	}
	
}
