package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @description 公司的合作伙伴
 * @author len
 */
@Entity
public class CompanyPartners {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "companyPartnersId", unique = true, nullable = false)
	/** 标识 */
	private Integer companyPartnersId;
	
	@OneToOne
	private Company company;
	
	@Column(name="partnerLogo",length=150)
	//合作商logo
	private String partnerLogo;
	
	@Column(name="sequence")
	//序号
	private Integer sequence;
	

	@Override
	public String toString() {
		return "CompanyPartners [companyPartnersId=" + companyPartnersId + ", partnerLogo=" + partnerLogo + "]";
	}

	/****************************** setter and getter *********************************/
	
	public Integer getCompanyPartnersId() {
		return companyPartnersId;
	}

	public Company getCompany() {
		return company;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getPartnerLogo() {
		return partnerLogo;
	}

	public void setCompanyPartnersId(Integer companyPartnersId) {
		this.companyPartnersId = companyPartnersId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setPartnerLogo(String partnerLogo) {
		this.partnerLogo = partnerLogo;
	}
	
	
	
}
