package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/** 
 * 公司活动
 * @description
 * @author len
 */
@Entity
public class CompanyActivities {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "companyActivitiesId", unique = true, nullable = false)
	/** 标识 */
	private Integer companyActivitiesId;
	
	@ManyToOne
	private Company company;
	
	@Column(name="activityContent",length=1000)
	//内容
	private String activityContent;
	
	@Column(name="createTime")
	//创建时间(方便小程序获取时间，设置成string类型)
	private String  createTime;
	
	@Column(name="activityTime")
	//活动举办时间
	private Date activityTime;
	
	@Column(name="activityTitle",length=50)
	//标题
	private String activityTitle;
	
	@Column(name="activityPluckText",length=100)
	//摘文
	private String activityPluckText;
	
	@Column(name="activityTitlePic",length=150)
	//标题图片路径
	private String activityTitlePic;
	
	@Override
	public String toString() {
		return "CompanyActivities [companyActivitiesId=" + companyActivitiesId + ", activityContent=" + activityContent
				+ ", createTime=" + createTime + ", activityTitle=" + activityTitle + "]";
	}
	
	/****************************** setter and getter *********************************/

	public Integer getCompanyActivitiesId() {
		return companyActivitiesId;
	}

	public String getActivityTitlePic() {
		return activityTitlePic;
	}

	public void setActivityTitlePic(String activityTitlePic) {
		this.activityTitlePic = activityTitlePic;
	}

	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

	public Company getCompany() {
		return company;
	}
	
	public String getActivityContent() {
		return activityContent;
	}


	public String getActivityTitle() {
		return activityTitle;
	}

	public void setCompanyActivitiesId(Integer companyActivitiesId) {
		this.companyActivitiesId = companyActivitiesId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getActivityPluckText() {
		return activityPluckText;
	}

	public void setActivityPluckText(String activityPluckText) {
		this.activityPluckText = activityPluckText;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	
	
}
