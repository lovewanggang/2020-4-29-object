package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @description 公司视频展示
 * @author len
 */
@Entity
public class CompanyShowVedio {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "companyShowVedioId", unique = true, nullable = false)
	/** 标识 */
	private Integer companyShowVedioId;
	
	@ManyToOne
	private Company company;
	
	@Column(name="vedioUrl",length=150)
	//视频存储路径
	private String vedioUrl;
	
	@Column(name="vedioCreateTime")
	//上传时间
	private Date vedioCreateTime;
	
	@Column(name="vedioTitle",length=18)
	//视频标题(规定为18个字符之内)
	private String vedioTitle;
	
	@Column(name="vedioDetail")
	//描述
	private String vedioDetail;

	@Override
	public String toString() {
		return "CompanyShowVedio [companyShowVedioId=" + companyShowVedioId + ", vedioUrl=" + vedioUrl
				+ ", vedioCreateTime=" + vedioCreateTime + ", vedioTitle=" + vedioTitle + ", vedioDetail=" + vedioDetail
				+ "]";
	}
	
	/****************************** setter and getter *********************************/

	public Integer getCompanyShowVedioId() {
		return companyShowVedioId;
	}

	public Company getCompany() {
		return company;
	}

	public String getVedioUrl() {
		return vedioUrl;
	}

	public Date getVedioCreateTime() {
		return vedioCreateTime;
	}

	public String getVedioTitle() {
		return vedioTitle;
	}

	public String getVedioDetail() {
		return vedioDetail;
	}

	public void setCompanyShowVedioId(Integer companyShowVedioId) {
		this.companyShowVedioId = companyShowVedioId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}

	public void setVedioCreateTime(Date vedioCreateTime) {
		this.vedioCreateTime = vedioCreateTime;
	}

	public void setVedioTitle(String vedioTitle) {
		this.vedioTitle = vedioTitle;
	}

	public void setVedioDetail(String vedioDetail) {
		this.vedioDetail = vedioDetail;
	}
	
	
	
}
