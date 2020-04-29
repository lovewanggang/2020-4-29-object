package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @description 公司招聘
 * @author len
 */
@Entity
public class CompanyJobInfo {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "companyJobInfoId", unique = true, nullable = false)
	/** 标识 */
	private Integer companyJobInfoId;
	
	@ManyToOne
	private Company company;
	
	@Column(name="cjiName",length=30)
	//职位名称
	private String cjiName;
	
	@Column(name="cjiSalary",length=30)
	//月薪
	private String cjiSalary;
	
	@Column(name="cjiAddr",length=100)
	//工作地址
	private String cjiAddr;
	
	@Column(name="cjiExp",length=20)
	//工作经验
	private String cjiExp;
	
	@Column(name="cjiEdu",length=20)
	//学历
	private String cjiEdu;
	
	@Column(name="cjiDetail",length=1000)
	//职位描述
	private String cjiDetail;
	
	@Column(name="creatTime")
	//创建时间
	private String creatTime;

	@Override
	public String toString() {
		return "CompanyJobInfo [companyJobInfoId=" + companyJobInfoId + ", cjiName=" + cjiName + ", cjiSalary="
				+ cjiSalary + ", cjiAddr=" + cjiAddr + ", cjiExp=" + cjiExp + ", cjiEdu=" + cjiEdu + ", cjiDetail="
				+ cjiDetail + "]";
	}

	/****************************** setter and getter *********************************/

	public Integer getCompanyJobInfoId() {
		return companyJobInfoId;
	}

	public Company getCompany() {
		return company;
	}

	public String getCjiName() {
		return cjiName;
	}

	public String getCjiSalary() {
		return cjiSalary;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}

	public String getCjiAddr() {
		return cjiAddr;
	}

	public String getCjiExp() {
		return cjiExp;
	}

	public String getCjiEdu() {
		return cjiEdu;
	}

	public String getCjiDetail() {
		return cjiDetail;
	}

	public void setCompanyJobInfoId(Integer companyJobInfoId) {
		this.companyJobInfoId = companyJobInfoId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setCjiName(String cjiName) {
		this.cjiName = cjiName;
	}

	public void setCjiSalary(String cjiSalary) {
		this.cjiSalary = cjiSalary;
	}

	public void setCjiAddr(String cjiAddr) {
		this.cjiAddr = cjiAddr;
	}

	public void setCjiExp(String cjiExp) {
		this.cjiExp = cjiExp;
	}

	public void setCjiEdu(String cjiEdu) {
		this.cjiEdu = cjiEdu;
	}

	public void setCjiDetail(String cjiDetail) {
		this.cjiDetail = cjiDetail;
	}
	
	
	
}
