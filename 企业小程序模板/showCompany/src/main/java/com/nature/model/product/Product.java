package com.nature.model.product;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.nature.model.company.Company;


/**
 * @description
 * @author len
 * @createDate 2018年4月19日
 */
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "productId", unique = true, nullable = false)
	/** 标识 */
	private Integer productId;
	
	@ManyToOne
	private Company company;
	
	@ManyToOne
	//产品所属类别
	private ProductClass productClass;
	
	@Column(name="productDetail")
	//描述
	private String productDetail;
	
	@Column(name="showProduct",length=1000)
	//图文详情
	private String showProduct;
	
	
	@Column(name="producStock")
	//库存
	private Integer producStock;
	
	@Column(name="productNum",length=30)
	//编号
	private String productNum;
	
	@Column(name="productHot")
	//热度
	private Integer productHot;
	
	@Column(name="newProduct")
	//新品
	private Integer newProduct;
	
	@Column(name="hot")
	//是否是人气产品（0或1，最多四个人气产品）
	private Integer hot;
	
	@Column(name="status")
	//0是隐藏，1是显示
	private Integer status;
	
	@Column(name="createTime")
	//创建时间
	private Date createTime;
	
	
	@Column(name="productPic1",length=100)
	//产品图片
	private String productPic1;
	@Column(name="productPic2",length=100)
	//产品图片
	private String productPic2;
	@Column(name="productPic3",length=100)
	//产品图片
	private String productPic3;
	@Column(name="productPic4",length=100)
	//产品图片
	private String productPic4;
	@Column(name="productPic5",length=100)
	//产品图片
	private String productPic5;
	

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productDetail=" + productDetail + ", showProduct=" + showProduct
				+ ", producStock=" + producStock + ", productNum=" + productNum + ", productHot=" + productHot
				+ ", newProduct=" + newProduct + ", hot=" + hot + ", status=" + status + ", createTime=" + createTime
				+ ", productPic1=" + productPic1 + ", productPic2=" + productPic2 + ", productPic3=" + productPic3
				+ ", productPic4=" + productPic4 + ", productPic5=" + productPic5 + "]";
	}


	/****************************** setter and getter *********************************/

	public Integer getProductId() {
		return productId;
	}


	public Integer getStatus() {
		return status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getShowProduct() {
		return showProduct;
	}

	public Integer getProducStock() {
		return producStock;
	}

	public void setProducStock(Integer producStock) {
		this.producStock = producStock;
	}

	public void setShowProduct(String showProduct) {
		this.showProduct = showProduct;
	}

	public String getProductPic1() {
		return productPic1;
	}

	public String getProductPic2() {
		return productPic2;
	}

	public String getProductPic3() {
		return productPic3;
	}

	public String getProductPic4() {
		return productPic4;
	}

	public String getProductPic5() {
		return productPic5;
	}

	public void setProductPic1(String productPic1) {
		this.productPic1 = productPic1;
	}

	public void setProductPic2(String productPic2) {
		this.productPic2 = productPic2;
	}

	public void setProductPic3(String productPic3) {
		this.productPic3 = productPic3;
	}

	public void setProductPic4(String productPic4) {
		this.productPic4 = productPic4;
	}

	public void setProductPic5(String productPic5) {
		this.productPic5 = productPic5;
	}

	public Company getCompany() {
		return company;
	}

	public ProductClass getProductClass() {
		return productClass;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public String getProductNum() {
		return productNum;
	}

	public Integer getNewProduct() {
		return newProduct;
	}

	public Integer getHot() {
		return hot;
	}

	public void setNewProduct(Integer newProduct) {
		this.newProduct = newProduct;
	}

	public void setHot(Integer hot) {
		this.hot = hot;
	}

	public Integer getProductHot() {
		return productHot;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public void setProductHot(Integer productHot) {
		this.productHot = productHot;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setProductClass(ProductClass productClass) {
		this.productClass = productClass;
	}
	
	
	
}
