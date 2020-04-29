package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.nature.model.account.SystemUser;

@Entity
public class Company implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "companyId", unique = true, nullable = false)
	/** 标识 */
	private Integer companyId;
	
	@OneToOne
	private SystemUser user;
	
	@Column(name="comName",length=50)
	//公司名称
	private String comName;
	
	@Column(name="comLogo",length=150)
	//公司Logo
	private String comLogo;
	
	@Column(name="comProfile")
	//公司介绍
	private String comProfile;
	
	@Column(name="comContact",length=20)
	//联系人
	private String comContact;
	
	@Column(name="comContactLogo",length=150)
	//联系人头像
	private String comContactLogo;
	
	@Column(name="comFax",length=30)
	//传真
	private String comFax;
	
	@Column(name="comEmail",length=30)
	//邮箱
	private String comEmail;
	
	@Column(name="sendEmailTime")
	//发送邮件的时间（一天只能发一次）
	private Date sendEmailTime;
	
	@Column(name="comProvince")
	//公司地址---省
	private String comProvince;
	@Column(name="comCity")
	//公司地址---市
	private String comCity;
	@Column(name="comDistrict")
	//公司地址---区
	private String comDistrict;
	@Column(name="comAddr")
	//公司详细地址
	private String comAddr;
	
	@Column(name="comLongitude")
	//公司位置经度
	private Double comLongitude;
	
	@Column(name="comLatitude")
	//公司位置纬度
	private Double comLatitude;
	
	@Column(name="comQRcode",length=150)
	//二维码图片
	private String comQRcode;
	
	@Column(name="comStatus")
	//公司状态，0是冻结，1是正常状态
	private Integer comStatus = 1;
	
	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", comName=" + comName + ", comLogo=" + comLogo + ", comProfile="
				+ comProfile + ", comContact=" + comContact + ", comContactLogo=" + comContactLogo + ", comFax="
				+ comFax + ", comEmail=" + comEmail + ", sendEmailTime=" + sendEmailTime + ", comProvince="
				+ comProvince + ", comCity=" + comCity + ", comDistrict=" + comDistrict + ", comAddr=" + comAddr
				+ ", comLongitude=" + comLongitude + ", comLatitude=" + comLatitude + ", comQRcode=" + comQRcode
				+ ", comStatus=" + comStatus + "]";
	}
	/****************************** setter and getter *********************************/

	public Integer getCompanyId() {
		return companyId;
	}
	public SystemUser getUser() {
		return user;
	}
	
	public Double getComLongitude() {
		return comLongitude;
	}
	public Double getComLatitude() {
		return comLatitude;
	}
	public void setComLongitude(Double comLongitude) {
		this.comLongitude = comLongitude;
	}
	public void setComLatitude(Double comLatitude) {
		this.comLatitude = comLatitude;
	}
	public Date getSendEmailTime() {
		return sendEmailTime;
	}
	public void setSendEmailTime(Date sendEmailTime) {
		this.sendEmailTime = sendEmailTime;
	}
	public Integer getComStatus() {
		return comStatus;
	}
	public void setComStatus(Integer comStatus) {
		this.comStatus = comStatus;
	}
	public String getComProvince() {
		return comProvince;
	}
	public String getComQRcode() {
		return comQRcode;
	}
	public void setComQRcode(String comQRcode) {
		this.comQRcode = comQRcode;
	}
	public String getComCity() {
		return comCity;
	}
	public String getComDistrict() {
		return comDistrict;
	}
	public void setComProvince(String comProvince) {
		this.comProvince = comProvince;
	}
	public void setComCity(String comCity) {
		this.comCity = comCity;
	}
	public void setComDistrict(String comDistrict) {
		this.comDistrict = comDistrict;
	}
	public String getComName() {
		return comName;
	}
	public String getComContactLogo() {
		return comContactLogo;
	}
	public void setComContactLogo(String comContactLogo) {
		this.comContactLogo = comContactLogo;
	}
	public String getComLogo() {
		return comLogo;
	}
	public String getComProfile() {
		return comProfile;
	}
	public String getComContact() {
		return comContact;
	}
	public String getComFax() {
		return comFax;
	}
	public String getComEmail() {
		return comEmail;
	}
	public String getComAddr() {
		return comAddr;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public void setUser(SystemUser user) {
		this.user = user;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public void setComLogo(String comLogo) {
		this.comLogo = comLogo;
	}
	public void setComProfile(String comProfile) {
		this.comProfile = comProfile;
	}
	public void setComContact(String comContact) {
		this.comContact = comContact;
	}
	public void setComFax(String comFax) {
		this.comFax = comFax;
	}
	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}
	public void setComAddr(String comAddr) {
		this.comAddr = comAddr;
	}
	
}
