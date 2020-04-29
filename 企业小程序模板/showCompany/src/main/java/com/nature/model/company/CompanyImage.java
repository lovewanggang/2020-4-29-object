package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CompanyImage {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "companyImageId", unique = true, nullable = false)
	/** 标识 */
	private Integer companyImageId;
	
	@ManyToOne
	private Company company;
	
	@Column(name="imgUrl",length=150)
	//企业形象图片
	private String imgUrl;
	
	@Column(name="sequence")
	//图片序号
	private Integer sequence;
	


	@Override
	public String toString() {
		return "CompanyImage [companyImageId=" + companyImageId + ", imgUrl=" + imgUrl + ", sequence=" + sequence + "]";
	}

	public Integer getCompanyImageId() {
		return companyImageId;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setCompanyImageId(Integer companyImageId) {
		this.companyImageId = companyImageId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	
}
