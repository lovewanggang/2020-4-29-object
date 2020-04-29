package com.nature.model.product;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @description
 * @author len
 * @createDate 2018年4月19日
 */
@Entity
public class ProductParamerValue {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "productParamerValueId", unique = true, nullable = false)
	/** 标识 */
	private Integer productParamerValueId;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private ProductParamer productParamer;
	
	@Column(name="productValue",length=30)
	//值
	private String productValue;

	@Override
	public String toString() {
		return "ProductParamerValue [productParamerValueId=" + productParamerValueId + ", productValue=" + productValue
				+ "]";
	}
	
	/****************************** setter and getter *********************************/
	

	public Integer getProductParamerValueId() {
		return productParamerValueId;
	}

	public Product getProduct() {
		return product;
	}

	public ProductParamer getProductParamer() {
		return productParamer;
	}

	public String getProductValue() {
		return productValue;
	}

	public void setProductParamerValueId(Integer productParamerValueId) {
		this.productParamerValueId = productParamerValueId;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setProductParamer(ProductParamer productParamer) {
		this.productParamer = productParamer;
	}

	public void setProductValue(String productValue) {
		this.productValue = productValue;
	}
	
	
	
	
}
