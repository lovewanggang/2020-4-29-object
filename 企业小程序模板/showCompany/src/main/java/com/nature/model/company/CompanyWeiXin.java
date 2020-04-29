package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CompanyWeiXin {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "companyPosterId", unique = true, nullable = false)
	/** 标识 */
	private Integer companyPosterId;
	
	@ManyToOne
	private Company company;
	
	@Column(name="weixin",length=50)
	//微信号
	private String weixin;
	
	@Column(name="sequence")
	//序号
	private Integer sequence;

	@Override
	public String toString() {
		return "CompanyWeiXin [companyPosterId=" + companyPosterId + ", weixin=" + weixin + "]";
	}

	public Integer getCompanyPosterId() {
		return companyPosterId;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Company getCompany() {
		return company;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setCompanyPosterId(Integer companyPosterId) {
		this.companyPosterId = companyPosterId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	
	
	
	
}
