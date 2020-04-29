package com.nature.model.manage;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.nature.model.account.SystemUser;
import com.nature.model.company.Company;
import com.nature.model.product.Product;

@Entity
public class ProductVisit {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "productVisitId", unique = true, nullable = false)
	/** 标识 */
	private Integer productVisitId;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Company company;
	
	@ManyToOne
	private SystemUser user;
	
	@Column(name="visitTime")
	private Date visitTime;

	public Integer getProductVisitId() {
		return productVisitId;
	}

	public Product getProduct() {
		return product;
	}

	public SystemUser getUser() {
		return user;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setProductVisitId(Integer productVisitId) {
		this.productVisitId = productVisitId;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	
	
	

}
