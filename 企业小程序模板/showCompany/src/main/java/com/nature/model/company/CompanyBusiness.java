package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @description 公司服务
 * @author len
 */
@Entity
public class CompanyBusiness {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "companyBusinessId", unique = true, nullable = false)
	/** 标识 */
	private Integer companyBusinessId;
	
	@OneToOne
	private Company company;
	
	@Column(name="business1",length=8)
	//业务1
	private String business1;
	
	@Column(name="business2",length=8)
	//业务2
	private String business2;
	
	@Column(name="business3",length=8)
	//业务3
	private String business3;
	
	@Column(name="business4",length=8)
	//业务4
	private String business4;
	
	@Column(name="showPic1",length=150)
	//展示图1
	private String showPic1;
	
	@Column(name="showPic2",length=150)
	//展示图2
	private String showPic2;
	
	@Column(name="showPic3",length=150)
	//展示图3
	private String showPic3;

	
	@Override
	public String toString() {
		return "CompanyBusiness [companyBusinessId=" + companyBusinessId + ", business1=" + business1 + ", business2="
				+ business2 + ", business3=" + business3 + ", business4=" + business4 + ", showPic1=" + showPic1
				+ ", showPic2=" + showPic2 + ", showPic3=" + showPic3 + "]";
	}

	/****************************** setter and getter *********************************/

	public Integer getCompanyBusinessId() {
		return companyBusinessId;
	}

	public Company getCompany() {
		return company;
	}

	public String getBusiness1() {
		return business1;
	}

	public String getBusiness2() {
		return business2;
	}

	public String getBusiness3() {
		return business3;
	}

	public String getBusiness4() {
		return business4;
	}

	public String getShowPic1() {
		return showPic1;
	}

	public String getShowPic2() {
		return showPic2;
	}

	public String getShowPic3() {
		return showPic3;
	}
	
	public void setCompanyBusinessId(Integer companyBusinessId) {
		this.companyBusinessId = companyBusinessId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setBusiness1(String business1) {
		this.business1 = business1;
	}

	public void setBusiness2(String business2) {
		this.business2 = business2;
	}

	public void setBusiness3(String business3) {
		this.business3 = business3;
	}

	public void setBusiness4(String business4) {
		this.business4 = business4;
	}

	public void setShowPic1(String showPic1) {
		this.showPic1 = showPic1;
	}

	public void setShowPic2(String showPic2) {
		this.showPic2 = showPic2;
	}

	public void setShowPic3(String showPic3) {
		this.showPic3 = showPic3;
	}
	
	
	
	
	
	
	
}
