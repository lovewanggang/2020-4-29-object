package com.nature.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.nature.action.BaseAction;
import com.nature.model.account.AdminAccount;
import com.nature.model.company.Company;
import com.nature.model.company.CompanyAccount;
import com.nature.service.ManageService;
import com.nature.util.ConstantsUtil;
import com.nature.util.CookieUtil;
import com.nature.util.ImageCompressUtil;


@ParentPackage("basePackage")
@Namespace("/manage")
public class ManageController extends BaseAction  {

	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	
	@Resource
	private ManageService manageService;
	
	
	private Integer id;
	private String  idStr;
	private String str;
	private String str2;
	
	private List<CompanyAccount> companyAccounts;
	private CompanyAccount account;
	
	
	private File picFile;
	private File image;
	private String imageContentType;
	private String imgUrl;
	private String imageFileName;
	private String registerDate;
	
	/**
	 * 1是没搜索到，0是通过
	 * @return
	 */
	@Action(value = "login", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" })
	})
	public String login(){
		List<AdminAccount> accounts = manageService.login(str, str2);
		
		if (accounts == null) {
			dataMap.put("code", 1);
		}else {
			putSessionAccount("manage", accounts.get(0).getAdminAccountId());
			dataMap.put("code", 0);
		}
		
		return SUCCESS;
	}
	@Action(value = "toWelcome", results = {
			@Result(name = "success",location="/WEB-INF/pages/manage/welcome.jsp")
	})
	public String toWelcome(){
		return SUCCESS;
	}
	
	@Action(value = "queryComAccountInfo", results = {
			@Result(name = "success", location="/pages/manage/listComAccount.jsp")
	})
	public String queryComAccountInfo(){
		companyAccounts = manageService.queryComAccountInfo();
		return SUCCESS;
	}
	
	@Action(value = "findOneComAccount", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" })
	})
	public String findOneComAccount(){
		account = manageService.findOneComAccount(idStr);
		dataMap.put("account",account);
		return SUCCESS;
	}
	
	/**
	 * 冻结，解冻
	 * @return
	 */
	@Action(value = "updateComStatus", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" })
	})
	public String updateComStatus(){
		manageService.updateComStatus(id,str);
		return SUCCESS;
	}
	
	
	@Action(value = "reminded2Pay", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" })
	})
	public String reminded2Pay(){
		 manageService.reminded2Pay(id,dataMap);
		return SUCCESS;
	}

	@Action(value = "register", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" })
	})
	public String register(){
		account = manageService.register(str,str2,dataMap);
		return SUCCESS;
	}
	
	@Action(value = "upload_business_license", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" })
	})
	public String upload_business_license(){
		String picUrl = uploadPic(image,"upload/upload-pic/company/business_License");
		dataMap.put("picUrl", picUrl);
		return SUCCESS;
	}
	
	@Action(value = "updateComAccount", results = {
			@Result(name = "success", type = "json", params = { "root", "dataMap" })
	})
	public String updateComAccount(){
		System.out.println("========registerDate========="+registerDate);
		account.setRegisterDate(registerDate);
		System.out.println(account);
		manageService.updateComAccount(account);
		return SUCCESS;
	}
	
	
	/**
	 * @param picFile
	 * @param rootPath 保存的地址（分两个，一个是compress的）示例：upload
	 * @return 
	 */
	private String uploadPic(File picFile, String rootPath){
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
	            in.close();
	            out.close();
	        } catch (FileNotFoundException e1) {
	            e1.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        System.out.println("+++++++++++++++++++++"+imgUrl);
	        return ConstantsUtil.HOST_URL+"/"+rootPath+"/"+imgUrl;
	}
	
	/***************************************** setter and getter  ****************************************/
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public ManageService getManageService() {
		return manageService;
	}

	public CompanyAccount getAccount() {
		return account;
	}

	public void setAccount(CompanyAccount account) {
		this.account = account;
	}

	public File getPicFile() {
		return picFile;
	}

	public void setPicFile(File picFile) {
		this.picFile = picFile;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getStr() {
		return str;
	}

	public String getStr2() {
		return str2;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	public List<CompanyAccount> getCompanyAccounts() {
		return companyAccounts;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public void setStr2(String str2) {
		this.str2 = str2;
	}

	public void setCompanyAccounts(List<CompanyAccount> companyAccounts) {
		this.companyAccounts = companyAccounts;
	}
	
}
