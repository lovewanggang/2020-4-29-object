package com.nature.model.product;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @description 产品
 * @author len
 * @createDate 2018年4月19日
 */
@Entity
public class ProductParamer {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "productId", unique = true, nullable = false)
	/** 标识 */
	private Integer productId;
	
	@ManyToOne
	private ProductBigClass productBigClass;
	
	@Column(name="paramerName",length=30)
	//属性名称
	private String paramerName;
	
	@Column(name="sequence")
	//序号
	private Integer sequence;

	@Override
	public String toString() {
		return "ProductParamer [productId=" + productId + ", paramerName=" + paramerName + "]";
	}
	

	/****************************** setter and getter *********************************/

	public Integer getProductId() {
		return productId;
	}


	public ProductBigClass getProductBigClass() {
		return productBigClass;
	}


	public Integer getSequence() {
		return sequence;
	}


	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}


	public void setProductBigClass(ProductBigClass productBigClass) {
		this.productBigClass = productBigClass;
	}


	public String getParamerName() {
		return paramerName;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public void setParamerName(String paramerName) {
		this.paramerName = paramerName;
	}
	
}
