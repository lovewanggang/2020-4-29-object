package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CompanyContactQQ {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "companyContactQQId", unique = true, nullable = false)
	/** 标识 */
	private Integer companyContactQQId;
	
	@ManyToOne
	private Company company;
	
	@Column(name="comQQ",length=30)
	private String comQQ;
	
	@Column(name="sequence")
	//序号
	private Integer sequence;

	@Override
	public String toString() {
		return "CompanyContactQQ [companyContactQQId=" + companyContactQQId + ", comQQ=" + comQQ + "]";
	}
	
	/****************************** setter and getter *********************************/

	public Integer getCompanyContactQQId() {
		return companyContactQQId;
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

	public String getComQQ() {
		return comQQ;
	}

	public void setCompanyContactQQId(Integer companyContactQQId) {
		this.companyContactQQId = companyContactQQId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setComQQ(String comQQ) {
		this.comQQ = comQQ;
	}
	
	
}
