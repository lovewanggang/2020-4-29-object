package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CompanyContactPhone {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "companyContactPhoneId", unique = true, nullable = false)
	/** 标识 */
	private Integer companyContactPhoneId;
	
	@ManyToOne
	private Company company;
	
	@Column(name="comPhone",length=30)
	private String comPhone;
	
	@Column(name="sequence")
	//序号
	private Integer sequence;

	@Override
	public String toString() {
		return "CompanyContactPhone [companyContactPhoneId=" + companyContactPhoneId + ", comPhone=" + comPhone + "]";
	}

	/****************************** setter and getter *********************************/
	
	public Integer getCompanyContactPhoneId() {
		return companyContactPhoneId;
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

	public String getComPhone() {
		return comPhone;
	}

	public void setCompanyContactPhoneId(Integer companyContactPhoneId) {
		this.companyContactPhoneId = companyContactPhoneId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setComPhone(String comPhone) {
		this.comPhone = comPhone;
	}
	
	
}
