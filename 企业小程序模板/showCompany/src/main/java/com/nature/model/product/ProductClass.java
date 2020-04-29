package com.nature.model.product;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
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
 * @description 产品类别
 * @author len
 * @createDate 2018年4月19日
 */
@Entity
public class ProductClass {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "productClassId", unique = true, nullable = false)
	/** 标识 */
	private Integer productClassId;
	
	@ManyToOne
	private Company company;
	
	@ManyToOne
	private ProductBigClass productBigClass;
	
	@Column(name="pcName",length=30)
	//类别名称
	private String pcName;
	
	@Column(name="pcClassPic",length=150)
	//产品类别代表图
	private String pcClassPic;
	
	@Column(name="pcDetail")
	//类别描述
	private String pcDetail;
	
	@Column(name="status")
	//0是隐藏，1是显示
	private Integer status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "productClass")
	private List<Product> products = new ArrayList<Product>();
	
	
	@Override
	public String toString() {
		return "ProductClass [productClassId=" + productClassId + ", pcName=" + pcName + ", pcClassPic=" + pcClassPic
				+ ", pcDetail=" + pcDetail + "]";
	}

	/****************************** setter and getter *********************************/
	
	public Integer getProductClassId() {
		return productClassId;
	}

	public Company getCompany() {
		return company;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public ProductBigClass getProductBigClass() {
		return productBigClass;
	}


	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public void setProductBigClass(ProductBigClass productBigClass) {
		this.productBigClass = productBigClass;
	}

	public String getPcClassPic() {
		return pcClassPic;
	}

	public void setPcClassPic(String pcClassPic) {
		this.pcClassPic = pcClassPic;
	}

	public String getPcDetail() {
		return pcDetail;
	}

	public void setPcDetail(String pcDetail) {
		this.pcDetail = pcDetail;
	}

	public String getPcName() {
		return pcName;
	}

	public void setProductClassId(Integer productClassId) {
		this.productClassId = productClassId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
	
	
	
	
}
