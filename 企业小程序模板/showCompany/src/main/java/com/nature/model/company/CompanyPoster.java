package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CompanyPoster {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "companyPosterId", unique = true, nullable = false)
	/** 标识 */
	private Integer companyPosterId;
	
	@ManyToOne
	private Company company;
	
	@Column(name="posterUrl",length=150)
	//公司海报图片
	private String posterUrl;

	@Override
	public String toString() {
		return "CompanyPoster [companyPosterId=" + companyPosterId + ", posterUrl=" + posterUrl + "]";
	}

	public Integer getCompanyPosterId() {
		return companyPosterId;
	}

	public Company getCompany() {
		return company;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setCompanyPosterId(Integer companyPosterId) {
		this.companyPosterId = companyPosterId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}
	
	
}
