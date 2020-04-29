package com.nature.model.product;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.nature.model.company.Company;

/** 
 * 大类
 * @description
 * @author len
 */
@Entity
public class ProductBigClass {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "productBigClassId", unique = true, nullable = false)
	/** 标识 */
	private Integer productBigClassId;
	
	@ManyToOne
	private Company company;
	
	
	@Column(name="bigClassName",length=20)
	//大类名称
	private String bigClassName;
	
	@Column(name="bigClassDetail")
	//大类描述
	private String bigClassDetail;
	
	@Column(name="bigClassCreateTime")
	//生成时间
	private Date bigClassCreateTime;
	
	@Column(name="status")
	//0是隐藏，1是显示
	private Integer status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "productBigClass")
	private List<ProductParamer> productParamer = new ArrayList<ProductParamer>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "productBigClass")
	private List<ProductClass> productclasses = new ArrayList<ProductClass>();
	
	
	@Override
	public String toString() {
		return "ProductBigClass [productBigClassId=" + productBigClassId + ", bigClassName=" + bigClassName
				+ ", bigClassDetail=" + bigClassDetail + ", bigClassCreateTime=" + bigClassCreateTime + ", status="
				+ status + "]";
	}


	/****************************** setter and getter *********************************/


	public Integer getProductBigClassId() {
		return productBigClassId;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<ProductClass> getProductclasses() {
		return productclasses;
	}

	public void setProductclasses(List<ProductClass> productclasses) {
		this.productclasses = productclasses;
	}

	public String getBigClassName() {
		return bigClassName;
	}

	public String getBigClassDetail() {
		return bigClassDetail;
	}

	public Date getBigClassCreateTime() {
		return bigClassCreateTime;
	}

	public void setProductBigClassId(Integer productBigClassId) {
		this.productBigClassId = productBigClassId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setBigClassName(String bigClassName) {
		this.bigClassName = bigClassName;
	}

	public List<ProductParamer> getProductParamer() {
		return productParamer;
	}

	public void setProductParamer(List<ProductParamer> productParamer) {
		this.productParamer = productParamer;
	}

	public void setBigClassDetail(String bigClassDetail) {
		this.bigClassDetail = bigClassDetail;
	}

	public void setBigClassCreateTime(Date bigClassCreateTime) {
		this.bigClassCreateTime = bigClassCreateTime;
	}
	
	
	
}
