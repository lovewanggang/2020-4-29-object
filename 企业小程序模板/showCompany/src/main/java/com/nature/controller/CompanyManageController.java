package com.nature.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.nature.action.BaseAction;
import com.nature.model.account.SystemUser;
import com.nature.model.company.Company;
import com.nature.model.company.CompanyAccount;
import com.nature.model.company.CompanyActivities;
import com.nature.model.company.CompanyBusiness;
import com.nature.model.company.CompanyContactPhone;
import com.nature.model.company.CompanyContactQQ;
import com.nature.model.company.CompanyHonors;
import com.nature.model.company.CompanyJobInfo;
import com.nature.model.company.CompanyPartners;
import com.nature.model.company.CompanyPoster;
import com.nature.model.company.CompanyShowVedio;
import com.nature.model.company.CompanyWeiXin;
import com.nature.model.manage.ProductVisit;
import com.nature.model.manage.UserVisit;
import com.nature.model.product.Product;
import com.nature.model.product.ProductBigClass;
import com.nature.model.product.ProductClass;
import com.nature.service.CompanyService;
import com.nature.service.ManageService;
import com.nature.service.ProductService;
import com.nature.service.SystemUserBLL;
import com.nature.util.ConstantsUtil;
import com.nature.util.CookieUtil;
import com.nature.util.ImageCompressUtil;

//@ParentPackage("basePackage")
@ParentPackage("company-manage-package")
@Namespace("/company")
public class CompanyManageController extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private CompanyService companyService;
	@Resource
	private SystemUserBLL systemUserBLL;
	@Resource
	private ProductService productService;
	@Resource
	private ManageService manageService;
	
	private Integer id;
	private Integer id2;
	private List<Integer> ids;
	private String str;
	private String str2;
	private String str3;
	private List<String > strs;
	private List<String > strs2;
	private Integer status;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private File Wxfile;
	private String strParamA;
	private String strParamB;
	private Integer intParamA;
	private Integer intParamB;
	private Integer autoLogin;//1是自动登录，0是不自动登录
	private String companyAccountId;
	private File picFile;
	private File image;
	private String imageContentType;
	private String imgUrl;
	private String imageFileName;
	private Company company;
	private CompanyContactPhone companyContactPhone;
	private List<CompanyContactPhone> comPhones;
	private List<CompanyContactQQ> comQQs;
	private List<CompanyActivities> comActivities;
	private List<CompanyPoster> posters;
	private List<CompanyHonors> honors;
	private List<CompanyPartners> partners;
	private List<CompanyWeiXin> weixins;
	private List<Product> products;
	private List<CompanyJobInfo> jobs;
	private List<ProductBigClass> bigClasses;
	
	private CompanyActivities companyActivities;
	private CompanyShowVedio vedio;
	private List<String> companyImage;
	private CompanyBusiness business;
	private CompanyJobInfo job;
	private ProductBigClass bigClass;
	private ProductClass productClass;
	private CompanyAccount account;
	private Integer on_off;
	private Product product;
	
	
	
	@Action(value = "toLogin", results = {
			@Result(name = "success", location="/pages/company/login.jsp"),
			@Result(name = "pass",location="/WEB-INF/pages/company/welcome.jsp")
		})
	public String toLogin(){
		String cookie = CookieUtil.findCookie("autoLogin", ServletActionContext.getRequest());
		System.out.println("=============toLogin==========cookie===="+cookie);
		if(cookie==null || cookie.equals("")){
			return SUCCESS;
		}else {
			this.putSessionAccount(Integer.parseInt(cookie));
			return "pass";
		}
		
	}
	
	@Action(value = "login", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" })
	})
	public String login(){
		Company company = companyService.login(strParamA, strParamB);
		if (company != null) {
			this.putSessionAccount(company.getCompanyId());
			dataMap.put("code", 0);
			dataMap.put("result", "loginSuccess");
			if (autoLogin != null && autoLogin != 0) {
				CookieUtil.addCookie("autoLogin", company.getCompanyId().toString(), ServletActionContext.getResponse());
			}
			return SUCCESS;
		}else {
			dataMap.put("code", -1);
			dataMap.put("result", "帐号或密码错误");
			return SUCCESS;
		}
	}
	
	@Action(value = "toWelcome", results = {
			@Result(name = "success",location="/WEB-INF/pages/company/welcome.jsp")
	})
	public String toWelcome(){
		return SUCCESS;
	}
	
	/**
	 * @param strParamA(帐号)，strParamB(密码)
	 * @return result （0是成功，-1是没有该帐号）
	 */
	@Action(value = "updatePassword", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String updatePassword(){
		manageService.updatePassword(strParamA, strParamB, dataMap);
		return SUCCESS;
	}
	
	/**
	 * 1.01.01  获取公司信息
	 * @return company对象
	 */
	@Action(value = "queryCompanyInfo", results = {
			@Result(name = "success", location="/pages/company/companyInfo.jsp") })
	public String queryCompanyInfo(){
		companyService.queryCompanyInfoByComId((Integer)getSessionAccount("company"),dataMap);
		company = (Company) dataMap.get("company");
		posters = (List<CompanyPoster>)dataMap.get("posters");
		comPhones = (List<CompanyContactPhone>) dataMap.get("phone");
		System.out.println(comPhones);
		return SUCCESS;
	}
	
	/**
	 * 1.01.02上传海报
	 * @return
	 */
	@Action(value = "uploadComPoster", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String uploadComPoster() {
		System.out.println(imageContentType);
		//验证数据库中有几张海报了，最多五张
		int count = companyService.checkPosterNum((Integer)getSessionAccount("company"));
		System.out.println(count);
		if (count < 5) {
			System.out.println("========imageFileName======"+imageFileName);
			String picUrl = this.uploadPic(image, "upload/upload-pic/company/poster", 428);
			System.out.println("========picUrl======"+picUrl);
			dataMap.put("picUrl", picUrl);
			dataMap.put("code", 0);
			return SUCCESS;
		}else {
			dataMap.put("code", -1);//图片数量已满
			return SUCCESS;
		}
	}
	
	/**
	 * 1.01.03 上传公司logo
	 * @return
	 */
	@Action(value = "uploadComLogo", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String uploadComLogo() {
		String picUrl = this.uploadPic(image, "upload/upload-pic/company/comLogo", 102);
		dataMap.put("picUrl", picUrl);
		return SUCCESS;
	}
	
	/**
	 * 1.01.04 更新公司信息
	 * @return
	 */
	@Action(value = "updateComInfo", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String updateComInfo() {
		System.out.println(str);
		companyService.updateComInfo(company,str2.split(","),str.split(","));
		return SUCCESS;
	}
	/**
	 * 1.02.00 “我们提供”开关
	 * @return
	 */
	@Action(value = "businessOnOff", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String businessOnOff() {
		companyService.businessOnOff((Integer)getSessionAccount("company"));
		return SUCCESS;
	}
	
	/**
	 * 1.02.01 获取“我们提供”信息
	 * @return
	 */
	@Action(value = "queryBusiness", results = {
			@Result(name = "success", location="/pages/company/companyBusiness.jsp") })
	public String queryBusiness() {
		companyService.queryBusiness((Integer)getSessionAccount("company"),dataMap);
		business = (CompanyBusiness) dataMap.get("business");
		on_off = (Integer) dataMap.get("onoff");
		return SUCCESS;
	}
	/**
	 * 1.02.02 上传展示图片
	 * @return
	 */
	@Action(value = "uploadBusinessPic", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String uploadBusinessPic() {
		String picUrl = this.uploadPic(image, "upload/upload-pic/company/business", 689);
		dataMap.put("picUrl", picUrl);
		return SUCCESS;
	}
	
	/**
	 * 1.02.02 添加或编辑“我们提供”
	 * @return
	 */
	@Action(value = "saveOrUpdateBusiness", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String saveOrUpdateBusiness() {
		companyService.saveOrUpdateBusiness((Integer)getSessionAccount("company"),business);
		return SUCCESS;
	}
	
	/**
	 * 1.03.01 修改人气产品（从列表删除）
	 * @param ids(被剔除的产品的ID列表)
	 */
	@Action(value = "editHotProduct", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String editHotProduct() {
		String[] strings = str.split(",");
		ids = new ArrayList<Integer>();
		for (int i = 0; i < strings.length; i++) {
			ids.add(Integer.parseInt(strings[i]));
		}
		companyService.editHotProduct(ids);
		return SUCCESS;
	}
	
	/**
	 * 1.03.02 获取人气产品列表
	 * @param 
	 */
	@Action(value = "queryHotProduct", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String queryHotProduct() {
		products = companyService.queryHotProduct((Integer)getSessionAccount("company"));
		
		return SUCCESS;
	}
	
	/**
	 * 1.04.00 视频信息开关
	 * @return
	 */
	@Action(value = "vedioOnoff", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String vedioOnoff() {
		companyService.vedioOnoff((Integer)getSessionAccount("company"));
		return SUCCESS;
	}
	
	/**
	 * 1.04.01  获取视频信息
	 * @return company对象
	 */
	@Action(value = "findVedio", results = {
			@Result(name = "success", location="/pages/company/showVedio.jsp") })
	public String findVedio(){
		vedio = companyService.findVedio((Integer)getSessionAccount("company"),dataMap);
		on_off = (Integer) dataMap.get("onoff");
		return SUCCESS;
	}
	
	/**
	 * 1.04.02 修改视频信息
	 * @return
	 */
	@Action(value = "updateVedio", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String updateVedio() {
		System.out.println("=================updateVedio=============");
		companyService.updateVedio(vedio);
		return SUCCESS;
	}
	
	/**
	 * 1.04.03 添加视频信息
	 * @return
	 */
	@Action(value = "addVedio", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String addVedio() {
		System.out.println("=================addVedio=============");
		companyService.addVedio((Integer)getSessionAccount("company"),vedio);
		return SUCCESS;
	}
	
	/**
	 * 1.04.04 上传视频
	 * @return
	 */
	@Action(value = "uploadVedio", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String uploadVedio() {
		String vedioUrl = this.uploadFile(image,imageFileName,"/upload/upload-vedio");
		dataMap.put("vedioUrl", vedioUrl);
		return SUCCESS;
	}
	
	/**
	 * 1.05.00 团队风采文章开关
	 * @return
	 */
	@Action(value = "activityOnoff", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String activityOnoff() {
		companyService.activityOnoff((Integer)getSessionAccount("company"));
		return SUCCESS;
	}
	
	/**
	 * 1.05.01 团队风采文章列表
	 * @return
	 */
	@Action(value = "queryComActivities", results = {
			@Result(name = "success", location="/pages/company/companyActivity.jsp") })
	public String queryComActivities() {
		comActivities = companyService.queryComActivities((Integer)getSessionAccount("company"),dataMap);
		on_off = (Integer) dataMap.get("onoff");
		return SUCCESS;
	}
	
	/**
	 * 1.05.02 添加团队风采文章
	 * @return
	 */
	@Action(value = "addComActivity", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String addComActivity() {
		companyService.addComActivity((Integer)getSessionAccount("company"),companyActivities);
		return SUCCESS;
	}
	
	/**
	 * 1.05.03 上传文章封面图片
	 * @return
	 */
	@Action(value = "uploadActivityPic", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String uploadActivityPic() {
		String picUrl = this.uploadPic(image, "upload/upload-pic/company/activityPic", 150);
		dataMap.put("picUrl", picUrl);
		return SUCCESS;
	}
	
	/**
	 * 1.05.04 删除团队风采文章
	 * @return
	 */
	@Action(value = "delComActivity", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String delComActivity() {
		companyService.delComActivity(id);
		return SUCCESS;
	}
	
	/**
	 * 1.05.05 获取某个团队风采文章
	 * @return
	 */
	@Action(value = "findOneComActivity", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String findOneComActivity() {
		companyActivities = companyService.findOneActivity(id);
		System.out.println(companyActivities);
		dataMap.put("companyActivities", companyActivities);
		return SUCCESS;
	}
	
	/**
	 * 1.05.06 编辑团队风采文章
	 * @return
	 */
	@Action(value = "updateComActivity", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String updateComActivity() {
		companyService.updateComActivity(companyActivities);
		return SUCCESS;
	}
	
	/**
	 * 1.06.00 企业形象开关
	 * @return
	 */
	@Action(value = "companyImageOnoff", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String companyImageOnoff() {
		companyService.companyImageOnoff((Integer)getSessionAccount("company"));
		return SUCCESS;
	}
	
	
	/**
	 * 1.06.01 获取企业形象图片列表
	 * @return
	 */
	@Action(value = "ourImage", results = {
			@Result(name = "success", location="/pages/company/companyImage.jsp") })
	public String ourImage() {
		companyImage = companyService.queryCompanyImage((Integer)getSessionAccount("company"),dataMap);
		on_off = (Integer) dataMap.get("onoff");
		return SUCCESS;
	}
	
	/**
	 * 1.06.02 更新企业形象
	 * @return
	 */
	@Action(value = "updateCompanyImage", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String updateCompanyImage() {
		System.out.println("==========companyId=========="+(Integer)getSessionAccount("company"));
		companyService.updateCompanyImage((Integer)getSessionAccount("company"),str.split(","));
		return SUCCESS;
	}
	
	/**
	 * 1.06.03 上传企业形象图片
	 * @return
	 */
	@Action(value = "uploadCompanyImage", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String uploadCompanyImage() {
		System.out.println("=============companyId=============="+(Integer)getSessionAccount("company"));
		int count = companyService.checkComImageNum((Integer)getSessionAccount("company"));
		System.out.println("==========已有企业形象图============"+count);
		if (count < 5) {
			String picUrl = this.uploadPic(image, "upload/upload-pic/company/comImage", 428);
			dataMap.put("picUrl", picUrl);
			dataMap.put("code", 0);
			dataMap.put("count", count);
			return SUCCESS;
		}else {
			dataMap.put("code", -1);//图片数量已满
			dataMap.put("count", count);
			return SUCCESS;
		}
	}
	
	/**
	 * 1.07.00 证书图片开关
	 * @return
	 */
	@Action(value = "honorsImageOnoff", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String honorsImageOnoff() {
		companyService.honorsImageOnoff((Integer)getSessionAccount("company"));
		return SUCCESS;
	}
	
	/**
	 * 1.07.01 上传证书图片
	 * @return
	 */
	@Action(value = "uploadHonorsImage", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String uploadHonorsImage() {
		String picUrl = this.uploadPic(image, "upload/upload-pic/company/honorPic", 400);
		dataMap.put("picUrl", picUrl);
		return SUCCESS;
	}
	
	/**
	 * 1.07.02 删除证书图片
	 * @return
	 */
	@Action(value = "delHonorsImage", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String delHonorsImage() {
		companyService.delHonors(ids);
		return SUCCESS;
	}
	/**
	 * 1.07.03 更新证书图片
	 * @return
	 */
	@Action(value = "updateHonorsImage", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String updateHonorsImage() {
		companyService.updateHonorsImage((Integer)getSessionAccount("company"),str.split(","));
		return SUCCESS;
	}
	
	/**
	 * 1.07.04 获取公司所有的证书图片
	 * @return
	 */
	@Action(value = "queryHonorsImage", results = {
			@Result(name = "success", location="/pages/company/companyHonor.jsp") })
	public String queryHonorsImage() {
		honors = companyService.queryHonorsImage((Integer)getSessionAccount("company"),dataMap);
		on_off = (Integer) dataMap.get("onoff");
		return SUCCESS;
	}
	/**
	 * 1.08.00 合作伙伴开关
	 * @return
	 */
	@Action(value = "partnerOnoff", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String partnerOnoff() {
		companyService.partnerOnoff((Integer)getSessionAccount("company"));
		return SUCCESS;
	}
	
	/**
	 * 1.08.01 上传合作伙伴图片
	 * @return
	 */
	@Action(value = "uploadPartnerImage", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String uploadPartnerImage() {
		System.out.println("==========image========="+image);
		String picUrl = this.uploadPic(image, "upload/upload-pic/company/partnerPic", 100);
		dataMap.put("picUrl", picUrl);
		return SUCCESS;
	}
	
	/**
	 * 1.08.02 删除合作伙伴图片
	 * @return
	 */
	@Action(value = "delPartnerImage", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String delPartnerImage() {
		companyService.delPartner(id);
		return SUCCESS;
	}
	
	/**
	 * 1.08.03 查询公司合作伙伴图片
	 * @return
	 */
	@Action(value = "queryPartnerImage", results = {
			@Result(name = "success", location="/pages/company/companyPartner.jsp") })
	public String queryPartnerImage() {
		partners = companyService.queryPartnerImage((Integer)getSessionAccount("company"),dataMap);
		on_off = (Integer) dataMap.get("onoff");
		return SUCCESS;
	}
	/**
	 * 1.08.03 更新公司合作伙伴图片
	 * @return
	 */
	@Action(value = "updatePartnerImage", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String updatePartnerImage() {
		companyService.updatePartnerImage((Integer)getSessionAccount("company"),str.split(","));
		return SUCCESS;
	}
	
	/**
	 * 1.09.00 招聘开关
	 * @return
	 */
	@Action(value = "jobOnoff", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String jobOnoff() {
		companyService.jobOnoff((Integer)getSessionAccount("company"));
		return SUCCESS;
	}
	
	/**
	 * 1.09.01 加入我们---添加招聘信息
	 * @return
	 */
	@Action(value = "addJob", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String addJob() {
		companyService.addJob((Integer)getSessionAccount("company"),job);
		return SUCCESS;
	}
	/**
	 * 1.09.02 加入我们---获取某条招聘信息
	 * @return
	 */
	@Action(value = "findOneJob", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String findOneJob() {
		job = companyService.findOneJob(id);
		dataMap.put("job", job);
		return SUCCESS;
	}
	/**
	 * 1.09.03 加入我们---获取全部招聘信息
	 * @return
	 */
	@Action(value = "findJob", results = {
			@Result(name = "success", location="/pages/company/listJob.jsp") })
	public String findJob() {
		jobs = companyService.findJob((Integer)getSessionAccount("company"),dataMap);
		on_off = (Integer) dataMap.get("onoff");
		return SUCCESS;
	}
	/**
	 * 1.09.04 加入我们---删除招聘信息
	 * @return
	 */
	@Action(value = "delJob", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String delJob() {
		companyService.delJob(id);
		return SUCCESS;
	}
	/**
	 * 1.09.05 加入我们---更新招聘信息
	 * @return
	 */
	@Action(value = "updateJob", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String updateJob() {
		companyService.updateJob(job);
		return SUCCESS;
	}
	
	
	/**
	 * 1.10.01 联系我们---获取公司信息，等待修改
	 * @return
	 */
	@Action(value = "getComInfo", results = {
			@Result(name = "success",location="/pages/company/bottomPage.jsp") })
	public String getComInfo() {
		companyService.queryCompanyInfoByComId((Integer)getSessionAccount("company"),dataMap);
		company = (Company) dataMap.get("company");
		posters = (List<CompanyPoster>)dataMap.get("posters");
		comPhones = (List<CompanyContactPhone>) dataMap.get("phone");
		comQQs = (List<CompanyContactQQ>) dataMap.get("qq");
		weixins = (List<CompanyWeiXin>) dataMap.get("weixins");
		return SUCCESS;
	}
	/**
	 * 1.10.02 联系我们---上传personLogo
	 * @return
	 */
	@Action(value = "uploadLogoPic", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String uploadLogoPic() {
		String picUrl = this.uploadPic(image, "upload/upload-pic/company/CEOpic", 168);
		dataMap.put("picUrl", picUrl);
		return SUCCESS;
	}
	/**
	 * 1.10.03 联系我们---更新修改的信息
	 * @return
	 */
	@Action(value = "updateCompanyInfo", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String updateCompanyInfo() {
		companyService.updateCompanyInfo((Integer)getSessionAccount("company"),company,str.split(","),str2.split(","),str3.split(","));
		return SUCCESS;
	}
	/**
	 * 1.10.04 联系我们---删除电话
	 * @return
	 */
	@Action(value = "delPhone", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String delPhone() {
		companyService.delPhone(id);
		return SUCCESS;
	}
	/**
	 * 1.10.04 联系我们---删除qq
	 * @return
	 */
	@Action(value = "delQQ", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String delQQ() {
		companyService.delQQ(id);
		return SUCCESS;
	}
	
	/**
	 * 1.10.05  联系我们---上传二维码
	 * @return
	 */
	@Action(value = "uploadQRcode", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String uploadQRcode() {
		String picUrl = this.uploadPic(image, "upload/upload-pic/company/QRcode", 240);
		dataMap.put("picUrl", picUrl);
		return SUCCESS;
	}
	
	/**
	 * 2.01.01  添加分类
	 * @return
	 */
	@Action(value = "addBigClass", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String addBigClass() {
		productService.addBigClass((Integer)getSessionAccount("company"),bigClass,str,productClass);
		return SUCCESS;
	}
	/**
	 * 2.01.02 上传子分类介绍图
	 * @return
	 */
	@Action(value = "uploadClassPic", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String uploadClassPic() {
		String picUrl = this.uploadPic(image, "upload/upload-pic/product/productClass", 689);
		dataMap.put("picUrl", picUrl);
		return SUCCESS;
	}
	
	/**
	 * 2.02.01 分类列表---获取分类数据
	 * @return
	 */
	@Action(value = "queryProClass", results = {
			@Result(name = "success", location="/pages/product/listProductClass.jsp") })
	public String queryProClass() {
		bigClasses = productService.queryProClass((Integer)getSessionAccount("company"));
		System.out.println(bigClasses);
		return SUCCESS;
	}
	/**
	 * 2.02.02 分类列表---添加子分类
	 * @return
	 */
	@Action(value = "addProductClass", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String addProductClass() {
		productService.queryProClass((Integer)getSessionAccount("company"),id,productClass);
		return SUCCESS;
	}
	/**
	 * 2.02.03 分类列表---获取大类信息
	 * @return
	 */
	@Action(value = "findOneBigClass", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String findOneBigClass() {
		bigClass = productService.findOneBigClass(id);
		dataMap.put("bigClass", bigClass);
		return SUCCESS;
	}
	
	/**
	 * 2.02.04  分类列表---修改大类和屬性信息
	 * @return
	 */
	@Action(value = "editBigClass", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String editBigClass() {
		productService.editBigClass(bigClass,str);
		return SUCCESS;
	}
	
	/**
	 * 2.02.05  分类列表---隐藏或显示大类
	 * @return
	 */
	@Action(value = "updateBigClassStatus", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String updateBigClassStatus() {
		productService.updateBigClassStatus(id,status);
		return SUCCESS;
	}
	
	/**
	 * 2.02.06  分类列表---隐藏或显示小类
	 * @return
	 */
	@Action(value = "updateClassStatus", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String updateClassStatus() {
		productService.updateClassStatus(id,status);
		return SUCCESS;
	}
	/**
	 * 2.02.07  分类列表---删除大类
	 * @return
	 */
	@Action(value = "delBigClass", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String delBigClass() {
		productService.delBigClass(id);
		return SUCCESS;
	}
	/**
	 * 2.02.08  分类列表---删除小类
	 * @return
	 */
	@Action(value = "delClass", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String delClass() {
		productService.delClass(id);
		return SUCCESS;
	}
	/**
	 * 2.02.09  分类列表---获取一个ProductClass
	 * @return
	 */
	@Action(value = "findOneProductClass", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String findOneProductClass() {
		productClass = productService.findOneProductClass(id);
		dataMap.put("productClass", productClass);
		return SUCCESS;
	}
	/**
	 * 2.02.10  分类列表---修改小类信息
	 * @return
	 */
	@Action(value = "editProductClass", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String editProductClass() {
		productService.editProductClass(productClass);
		return SUCCESS;
	}
	
	/**
	 * 2.03.00  产品开关
	 * @return
	 */
	@Action(value = "productOnoff", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String productOnoff() {
		System.out.println("========productOnoff==========");
		companyService.productOnoff((Integer)getSessionAccount("company"));
		return SUCCESS;
	}
	
	/**
	 * 2.03.01 上传产品图片
	 * @return
	 */
	@Action(value = "uploadProductPic", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String uploadProductPic() {
		String picUrl = this.uploadPic(image, "upload/upload-pic/product/productPic", 689);
		dataMap.put("picUrl", picUrl);
		return SUCCESS;
	}
	/**
	 * 2.03.02 获取分类信息
	 * @return
	 */
	@Action(value = "queryClassInfo", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String queryClassInfo() {
		bigClasses = productService.queryClassInfo((Integer)getSessionAccount("company"));
		int count = productService.queryHotProductCount((Integer)getSessionAccount("company"));
		dataMap.put("count", count);
		dataMap.put("bigClasses", bigClasses);
		return SUCCESS;
	}
	
	/**
	 * 2.03.02 获取分类信息
	 * @return
	 */
	@Action(value = "findOneProduct", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String findOneProduct() {
		productService.findOneProduct(id, dataMap);
		return SUCCESS;
	}
	
	/**
	 * 2.03.03 添加产品
	 * @return
	 */
	@Action(value = "addProduct", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String addProduct() {
		productService.addProduct(product,id,id2,str,str2);
		dataMap.put("bigClasses", bigClasses);
		return SUCCESS;
	}
	
	/**
	 * 2.03.04 查询人气产品个数
	 * @return
	 */
	@Action(value = "queryHotProductCount", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String queryHotProductCount() {
		int count = productService.queryHotProductCount((Integer)getSessionAccount("company"));
		dataMap.put("count", count);
		return SUCCESS;
	}
	/**
	 * 2.03.03 添加产品
	 * @return
	 */
	@Action(value = "updateProduct", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String updateProduct() {
		productService.updateProduct(product,id,id2,str,str2);
		return SUCCESS;
	}
	
	/**
	 * 2.04.01 产品列表
	 * @return
	 */
	@Action(value = "listProduct", results = {
			@Result(name = "success", location="/pages/product/listProduct.jsp") })
	public String listProduct() {
		products = productService.listProduct((Integer)getSessionAccount("company"),dataMap);
		on_off = (Integer) dataMap.get("onoff");
		dataMap.put("bigClasses", bigClasses);
		return SUCCESS;
	}
	/**
	 * 2.04.02 删除某个产品
	 * @return
	 */
	@Action(value = "delProduct", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String delProduct() {
		productService.delProduct(id);
		return SUCCESS;
	}
	/**
	 * 2.04.03 上架
	 * @return
	 */
	@Action(value = "shangjia", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String shangjia() {
		productService.updateProduct(str,1);
		return SUCCESS;
	}
	/**
	 * 2.04.04 下架
	 * @return
	 */
	@Action(value = "xiajia", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String xiajia() {
		productService.updateProduct(str,0);
		return SUCCESS;
	}
	/**
	 * 2.04.05 批量删除
	 * @return
	 */
	@Action(value = "delPros", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String delPros() {
		String [] ids = str.split(",");
		for (int i = 0; i < ids.length; i++) {
			productService.delProduct(Integer.parseInt(ids[i]));
		}
		return SUCCESS;
	}
	
	
	/**
	 * 3.01.01  帐户管理---获取公司帐号信息
	 * @return
	 */
	@Action(value = "findComAccount", results = {
			@Result(name = "success", location="/pages/account/accountInfo.jsp") })
	public String findComAccount() {
		account = companyService.findComAccount((Integer)getSessionAccount("company"),dataMap);
		return SUCCESS;
	}
	
	/**
	 * 3.01.02  帐户管理---修改公司帐号密码
	 * @param str(旧密码,新密码)
	 */
	@Action(value = "updateAccountPassword", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String updateAccountPassword() {
		int code = companyService.updateAccountPassword((Integer)getSessionAccount("company"),str);
		dataMap.put("code", code);
		return SUCCESS;
	}
	
	
	/**
	 * 3.01.03  帐户管理---获取公司帐号信息
	 * @return
	 */
	@Action(value = "findOneAccount", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String findOneAccount() {
		companyService.findComAccount((Integer)getSessionAccount("company"),dataMap);
		return SUCCESS;
	}
	/**
	 * 3.03.01  帐户管理---获取小程序的浏览量
	 * @param id(1是日，2是周，3是月)
	 */
	@Action(value = "queryUserVisit", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String queryUserVisit() {
		List<Object> userVisits = companyService.queryUserVisit((Integer)getSessionAccount("company"),id);
		dataMap.put("userVisits", userVisits);
		return SUCCESS;
	}
	
	/**
	 * 3.03.01  帐户管理---查询今天的访客列表
	 * @param 
	 */
	@Action(value = "queryTodayVisitor", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String queryTodayVisitor() {
		List<UserVisit> visitors = companyService.queryTodayVisitor((Integer)getSessionAccount("company"));
		dataMap.put("visitors", visitors);
		return SUCCESS;
	}
	
	/**
	 * 3.03.01  帐户管理---查询今天的产品浏览列表
	 * @param 
	 */
	@Action(value = "queryTodayProductVisited", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String queryTodayProductVisited() {
		List<Object> productVisits = companyService.queryTodayProductVisited((Integer)getSessionAccount("company"));
		dataMap.put("productVisits", productVisits);
		return SUCCESS;
	}
	
	
	/**
	 * 保存原图
	 * 压缩图片
	 * 返回压缩后图片的路径
	 * @param picFile
	 * @param rootPath 保存的地址（分两个，一个是compress的）示例：upload
	 * @return 
	 */
	private String uploadPic(File picFile, String rootPath, int max){
		System.out.println("==========uploadPic=============");
		if (picFile == null) {
	            return null;
	        }
	        InputStream in = null;
	        OutputStream out = null;
	        String serverRealPath1 = null;
	        try {
	            in = new FileInputStream(picFile);
	            serverRealPath1 = ServletActionContext.getServletContext().getRealPath("/");
	            String serverRealPath = serverRealPath1+rootPath+"/";
	            File uploadFile = new File(serverRealPath);
	            
	            if  (!uploadFile .exists()  && !uploadFile .isDirectory())      
	            {       
	                System.out.println("//目录不存在");  
	                uploadFile.mkdirs();    
	            } 
	            // 设置文件在服务器上的存储路径, 以便读取
	            long time = System.currentTimeMillis();
	            imgUrl = time
	                    + "."
	                    + this.imageFileName.substring(
	                            this.imageFileName
	                                    .lastIndexOf(".") + 1,
	                            this.imageFileName.length());// this.getImageFileName();
	            File targetFile = new File(serverRealPath, imgUrl);// +this.imageFileName
	            HttpServletRequest request = ServletActionContext.getRequest();
	            
	            out = new FileOutputStream(targetFile);
	            byte[] buffer = new byte[1024 * 1024];
	            int length;
//保存原图	            
	            while ((length = in.read(buffer)) > 0) {
	                out.write(buffer, 0, length);
	            }
	            
	            BufferedImage imgSrc = ImageIO.read(targetFile);
	            System.out.println("======限定的长或宽========"+max);
	            // 得到源图宽  
	            int old_w = imgSrc.getWidth();  
	            System.out.println("=========源图宽========"+old_w);
	            // 得到源图长  
		        int old_h = imgSrc.getHeight(); 
		        System.out.println("=========源图长 ========"+old_h);
		        if (old_w > max ||  old_h > max ) {
					
				
		        String compressServerRealPath = serverRealPath +"compress/";
	            if (!(old_w < max)) {
//保存压缩图片（图片的名称加一个前缀sc）	            	
	            	ImageCompressUtil.Tosmallerpic(serverRealPath + imgUrl, compressServerRealPath +"sc" + imgUrl, max, 1f);
				}else {
					File uploadF = new File(compressServerRealPath);
		            
		            if  (!uploadF.exists() && !uploadF.isDirectory())      
		            {       
		                System.out.println("//目录不存在");  
		                uploadF.mkdirs();    
		            } 
//保存压缩图片（图片的名称加一个前缀sc）	            	
		            File targetF = new File(compressServerRealPath, "sc"+imgUrl);// +this.imageFileName
		            
		            out = new FileOutputStream(targetFile);
		            byte[] b = new byte[1024 * 1024];
		            int len;
		            while ((len = in.read(buffer)) > 0) {
		                out.write(buffer, 0, length);
		            }
				}
	            in.close();
	            out.close();
		        }else {
		        	in.close();
		        	out.close();
		        return ConstantsUtil.HOST_URL+"/"+rootPath+"/"+imgUrl;
				}
	        } catch (FileNotFoundException e1) {
	            e1.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        return ConstantsUtil.HOST_URL+"/"+rootPath+"/compress/"+"sc"+imgUrl;
	}
	
	 /** 
     * * 图片文件读取 
     *  
     * @param srcImgPath 
     * @return length(图片的长)、width(图片的宽)
	 * @throws IOException 
     */  
	@Action(value = "checkInputImg", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" }) })
	public String checkInputImg() throws IOException {
		BufferedImage srcImage = ImageIO.read(picFile);
		dataMap.put("length", srcImage.getHeight());
		dataMap.put("width", srcImage.getWidth());
        return SUCCESS;  
    } 
	
	

	/**
	 * 上传文件
	 * @param image
	 * @param imageFileName
	 * @param uploadPath 上传路径
	 * @return 
	 */
	private String uploadFile(File image, String imageFileName, String uploadPath){
		System.out.println("imageFileName++++++++++++++++++++"+imageFileName);
		System.out.println("image++++++++++++++++++"+image);
		if (image == null) {
	            return null;
	        }
	        InputStream in = null;
	        OutputStream out = null;
	        
	        try {
	        	
	            in = new FileInputStream(image);
	            String serverRealPath = ServletActionContext
	                    .getServletContext().getRealPath(uploadPath);
	            File uploadFile = new File(serverRealPath);
	            
	            if  (!uploadFile .exists()  && !uploadFile .isDirectory())      
	            {       
	                System.out.println("//不存在");  
	                uploadFile.mkdirs();    
	            } else   
	            {  
	                System.out.println("//目录存在");  
	            }  
	            // 设置文件在服务器上的存储路径, 以便读取
	            long time = System.currentTimeMillis();
	            imgUrl = time
	                    + "."
	                    + this.imageFileName.substring(
	                            this.imageFileName
	                                    .lastIndexOf(".") + 1,
	                            this.imageFileName.length());// this.getImageFileName();
	            File targetFile = new File(serverRealPath, imgUrl);// +this.imageFileName
	            HttpServletRequest request = ServletActionContext
	                    .getRequest();
	           
	            // imgStr="images/voucher/"+time+this.getVoucherImgFileName();
	            // 直接使用struts2提供的文件操作工具类, 将要上传的文件存储到服务器
	            // FileUtils.copyFile(sourceFile, targetFile);
	            // Image src=javax.imageio.ImageIO.read(targetFile);
	            
	            out = new FileOutputStream(targetFile);
	            byte[] buffer = new byte[1024 * 1024];
	            int length;
	            while ((length = in.read(buffer)) > 0) {
	                out.write(buffer, 0, length);
	            }
	            in.close();
	            out.close();
	        } catch (FileNotFoundException e1) {
	            e1.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        System.out.println("+++++++++++++++++++++"+imgUrl);
	        return ConstantsUtil.HOST_URL+uploadPath+"/"+imgUrl;
	}
	
	
	
	/*********************************** setter and getter ****************************************/


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public CompanyBusiness getBusiness() {
		return business;
	}

	public CompanyJobInfo getJob() {
		return job;
	}

	public List<String> getStrs() {
		return strs;
	}

	public void setStrs(List<String> strs) {
		this.strs = strs;
	}

	public void setJob(CompanyJobInfo job) {
		this.job = job;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public void setBusiness(CompanyBusiness business) {
		this.business = business;
	}

	public List<String> getCompanyImage() {
		return companyImage;
	}

	public Integer getId2() {
		return id2;
	}

	public void setId2(Integer id2) {
		this.id2 = id2;
	}

	public void setCompanyImage(List<String> companyImage) {
		this.companyImage = companyImage;
	}

	public CompanyShowVedio getVedio() {
		return vedio;
	}

	public void setVedio(CompanyShowVedio vedio) {
		this.vedio = vedio;
	}

	public List<CompanyHonors> getHonors() {
		return honors;
	}

	public void setHonors(List<CompanyHonors> honors) {
		this.honors = honors;
	}

	public String getStr() {
		return str;
	}

	public List<CompanyPartners> getPartners() {
		return partners;
	}

	public CompanyAccount getAccount() {
		return account;
	}

	public void setAccount(CompanyAccount account) {
		this.account = account;
	}

	public void setPartners(List<CompanyPartners> partners) {
		this.partners = partners;
	}
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	public void setStr(String str) {
		this.str = str;
	}

	public String getStr3() {
		return str3;
	}

	public List<Product> getProducts() {
		return products;
	}

	public List<ProductBigClass> getBigClasses() {
		return bigClasses;
	}

	public void setBigClasses(List<ProductBigClass> bigClasses) {
		this.bigClasses = bigClasses;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setStr3(String str3) {
		this.str3 = str3;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public CompanyActivities getCompanyActivities() {
		return companyActivities;
	}

	public void setCompanyActivities(CompanyActivities companyActivities) {
		this.companyActivities = companyActivities;
	}

	public List<CompanyActivities> getComActivities() {
		return comActivities;
	}

	public void setComActivities(List<CompanyActivities> comActivities) {
		this.comActivities = comActivities;
	}

	public CompanyContactPhone getCompanyContactPhone() {
		return companyContactPhone;
	}

	public List<CompanyContactPhone> getComPhones() {
		return comPhones;
	}

	public List<CompanyWeiXin> getWeixins() {
		return weixins;
	}

	public void setWeixins(List<CompanyWeiXin> weixins) {
		this.weixins = weixins;
	}

	public List<CompanyPoster> getPosters() {
		return posters;
	}

	public void setPosters(List<CompanyPoster> posters) {
		this.posters = posters;
	}

	public void setComPhones(List<CompanyContactPhone> comPhones) {
		this.comPhones = comPhones;
	}

	public List<CompanyJobInfo> getJobs() {
		return jobs;
	}

	public void setJobs(List<CompanyJobInfo> jobs) {
		this.jobs = jobs;
	}

	public ProductClass getProductClass() {
		return productClass;
	}

	public void setProductClass(ProductClass productClass) {
		this.productClass = productClass;
	}

	public void setCompanyContactPhone(CompanyContactPhone companyContactPhone) {
		this.companyContactPhone = companyContactPhone;
	}

	public String getCompanyAccountId() {
		return companyAccountId;
	}

	public void setCompanyAccountId(String companyAccountId) {
		this.companyAccountId = companyAccountId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ManageService getManageService() {
		return manageService;
	}

	public List<CompanyContactQQ> getComQQs() {
		return comQQs;
	}

	public void setComQQs(List<CompanyContactQQ> comQQs) {
		this.comQQs = comQQs;
	}

	public Integer getAutoLogin() {
		return autoLogin;
	}

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	public void setAutoLogin(Integer autoLogin) {
		this.autoLogin = autoLogin;
	}

	public Integer getIntParamA() {
		return intParamA;
	}

	public Integer getIntParamB() {
		return intParamB;
	}

	public String getStr2() {
		return str2;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public void setIntParamA(Integer intParamA) {
		this.intParamA = intParamA;
	}

	public void setIntParamB(Integer intParamB) {
		this.intParamB = intParamB;
	}

	public String getStrParamA() {
		return strParamA;
	}

	public ProductBigClass getBigClass() {
		return bigClass;
	}

	public void setBigClass(ProductBigClass bigClass) {
		this.bigClass = bigClass;
	}

	public String getStrParamB() {
		return strParamB;
	}

	public void setStrParamA(String strParamA) {
		this.strParamA = strParamA;
	}

	public void setStrParamB(String strParamB) {
		this.strParamB = strParamB;
	}

	public File getPicFile() {
		return picFile;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public Integer getOn_off() {
		return on_off;
	}

	public void setOn_off(Integer on_off) {
		this.on_off = on_off;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public List<String> getStrs2() {
		return strs2;
	}

	public void setStrs2(List<String> strs2) {
		this.strs2 = strs2;
	}

	public Company getCompany() {
		return company;
	}

	public void setPicFile(File picFile) {
		this.picFile = picFile;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public CompanyService getCompanyService() {
		return companyService;
	}

	public SystemUserBLL getSystemUserBLL() {
		return systemUserBLL;
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

	public File getWxfile() {
		return Wxfile;
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

	public void setWxfile(File wxfile) {
		Wxfile = wxfile;
	}
	
	
	
}
