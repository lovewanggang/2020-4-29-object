package com.nature.model.module;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.nature.model.company.Company;

@Entity
public class Module_on_off {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "moduleId", unique = true, nullable = false)
	/** 标识 */
	private Integer moduleId;
	
	@OneToOne
	private Company company;
	
	@Column(name="business_on_off")
	//提供服务
	private boolean business_on_off = true;
	
	@Column(name="hotProduct_on_off")
	//人气商品
	private boolean hotProduct_on_off = true;
	
	@Column(name="comVedio_on_off")
	//视频展示
	private boolean comVedio_on_off = true;
	
	@Column(name="comActivity_on_off")
	//团队风采
	private boolean comActivity_on_off = true;
	
	@Column(name="comImage_on_off")
	//公司形象
	private boolean comImage_on_off = true;
	
	@Column(name="comHonors_on_off")
	//公司荣誉
	private boolean comHonors_on_off = true;
	
	@Column(name="comPartner_on_off")
	//合作伙伴
	private boolean comPartner_on_off = true;
	
	@Column(name="comJob_on_off")
	//公司招聘
	private boolean comJob_on_off = true;
	
	@Column(name="comProduct_on_off")
	//公司产品
	private boolean comProduct_on_off = true;

	@Override
	public String toString() {
		return "Module_on_off [moduleId=" + moduleId + ", business_on_off=" + business_on_off + ", hotProduct_on_off="
				+ hotProduct_on_off + ", comVedio_on_off=" + comVedio_on_off + ", comActivity_on_off="
				+ comActivity_on_off + ", comImage_on_off=" + comImage_on_off + ", comHonors_on_off=" + comHonors_on_off
				+ ", comPartner_on_off=" + comPartner_on_off + ", comJob_on_off=" + comJob_on_off
				+ ", comProduct_on_off=" + comProduct_on_off + "]";
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public Company getCompany() {
		return company;
	}

	public boolean isBusiness_on_off() {
		return business_on_off;
	}

	public boolean isHotProduct_on_off() {
		return hotProduct_on_off;
	}

	public boolean isComVedio_on_off() {
		return comVedio_on_off;
	}

	public boolean isComActivity_on_off() {
		return comActivity_on_off;
	}

	public boolean isComImage_on_off() {
		return comImage_on_off;
	}

	public boolean isComHonors_on_off() {
		return comHonors_on_off;
	}

	public boolean isComPartner_on_off() {
		return comPartner_on_off;
	}

	public boolean isComJob_on_off() {
		return comJob_on_off;
	}

	public boolean isComProduct_on_off() {
		return comProduct_on_off;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setBusiness_on_off(boolean business_on_off) {
		this.business_on_off = business_on_off;
	}

	public void setHotProduct_on_off(boolean hotProduct_on_off) {
		this.hotProduct_on_off = hotProduct_on_off;
	}

	public void setComVedio_on_off(boolean comVedio_on_off) {
		this.comVedio_on_off = comVedio_on_off;
	}

	public void setComActivity_on_off(boolean comActivity_on_off) {
		this.comActivity_on_off = comActivity_on_off;
	}

	public void setComImage_on_off(boolean comImage_on_off) {
		this.comImage_on_off = comImage_on_off;
	}

	public void setComHonors_on_off(boolean comHonors_on_off) {
		this.comHonors_on_off = comHonors_on_off;
	}

	public void setComPartner_on_off(boolean comPartner_on_off) {
		this.comPartner_on_off = comPartner_on_off;
	}

	public void setComJob_on_off(boolean comJob_on_off) {
		this.comJob_on_off = comJob_on_off;
	}

	public void setComProduct_on_off(boolean comProduct_on_off) {
		this.comProduct_on_off = comProduct_on_off;
	}
	
	
	
}
