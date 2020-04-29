package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import java.security.acl.Owner;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class CompanyAccount {

	@Id
	@GeneratedValue(generator="paymentableGenerator")
	@GenericGenerator(name="paymentableGenerator", strategy = "assigned")
	@Column(name = "companyAccountId", unique = true, nullable = false,length=15)
	// ID, 不同小程序过来的识别码,用于识别是哪个公司的
	private String companyAccountId;
	
	@OneToOne
	private Company company;
	
	@Column(name="loginAccount",length=30)
	//登录管理后台的帐号
	private String loginAccount;
	
	@Column(name="loginPassword",length=30)
	//密码
	private String loginPassword;
	
	@Column(name="wxAppID",length=100)
	//小程序的APPID
	private String wxAppID;
	
	@Column(name="wxAppSecret",length=100)
	//小程序的APPSECRET
	private String wxAppSecret;
	
	@Column(name="registerDate")
	//注册日期 yyyy-MM-dd
	private String registerDate;
	
	@Column(name="validityDate")
	//有效期
	private Date validityDate;
	
	@Column(name="owner")
	//拥有者
	private String owner;
	
	@Column(name="bank")
	//开户银行名称
	private String bank;
	
	@Column(name="bankAccount")
	//银行帐号
	private String bankAccount;
	
	@Column(name="scope_of_business")
	//经营范围
	private String scope_of_business;
	
	@Column(name="phoneNum")
	//联系电话
	private String phoneNum;
	
	@Column(name="companyName")
	//公司名称
	private String companyName;
	
	@Column(name="business_license")
	//营业执照地址
	private String business_license;
	
	@Column(name="taocan")
	//套餐
	private Integer taocan;
	

	@Override
	public String toString() {
		return "CompanyAccount [companyAccountId=" + companyAccountId + ", loginAccount=" + loginAccount
				+ ", loginPassword=" + loginPassword + ", wxAppID=" + wxAppID + ", wxAppSecret=" + wxAppSecret
				+ ", registerDate=" + registerDate + ", validityDate=" + validityDate + ", owner=" + owner + ", bank="
				+ bank + ", bankAccount=" + bankAccount + ", scope_of_business=" + scope_of_business + ", phoneNum="
				+ phoneNum + ", companyName=" + companyName + ", business_license=" + business_license + ", taocan="
				+ taocan + "]";
	}


	/****************************** setter and getter *********************************/


	public Company getCompany() {
		return company;
	}


	public String getRegisterDate() {
		return registerDate;
	}


	public String getCompanyName() {
		return companyName;
	}


	public Integer getTaocan() {
		return taocan;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public void setTaocan(Integer taocan) {
		this.taocan = taocan;
	}


	public String getOwner() {
		return owner;
	}


	public String getBank() {
		return bank;
	}


	public String getBankAccount() {
		return bankAccount;
	}


	public String getScope_of_business() {
		return scope_of_business;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public String getBusiness_license() {
		return business_license;
	}


	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}


	public void setOwner(String owner) {
		this.owner = owner;
	}


	public void setBank(String bank) {
		this.bank = bank;
	}


	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}


	public void setScope_of_business(String scope_of_business) {
		this.scope_of_business = scope_of_business;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public void setBusiness_license(String business_license) {
		this.business_license = business_license;
	}


	public String getCompanyAccountId() {
		return companyAccountId;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

	public void setCompanyAccountId(String companyAccountId) {
		this.companyAccountId = companyAccountId;
	}

	public String getLoginAccount() {
		return loginAccount;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public String getWxAppID() {
		return wxAppID;
	}

	public String getWxAppSecret() {
		return wxAppSecret;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public void setWxAppID(String wxAppID) {
		this.wxAppID = wxAppID;
	}

	public void setWxAppSecret(String wxAppSecret) {
		this.wxAppSecret = wxAppSecret;
	}
	
}
