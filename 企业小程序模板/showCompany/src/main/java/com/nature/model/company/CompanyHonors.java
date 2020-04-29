package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * @description 公司的荣誉资质
 * @author len
 */
@Entity
public class CompanyHonors {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "companyHonorsId", unique = true, nullable = false)
	/** 标识 */
	private Integer companyHonorsId;
	
	@ManyToOne
	private Company company;
	
	@Column(name="chPic",length=200)
	//荣誉证书地址
	private String chPic;
	
	@Column(name="sequence")
	//图片序号
	private Integer sequence;


	@Override
	public String toString() {
		return "CompanyHonors [companyHonorsId=" + companyHonorsId + ", chPic=" + chPic + ", sequence=" + sequence
				+ "]";
	}

	/****************************** setter and getter *********************************/
	

	public Company getCompany() {
		return company;
	}

	public Integer getCompanyHonorsId() {
		return companyHonorsId;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public void setCompanyHonorsId(Integer companyHonorsId) {
		this.companyHonorsId = companyHonorsId;
	}

	public String getChPic() {
		return chPic;
	}


	public void setCompany(Company company) {
		this.company = company;
	}

	public void setChPic(String chPic) {
		this.chPic = chPic;
	}
	
	
	
}
