package com.nature.model.manage;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.transaction.Transactional;

import com.nature.model.account.SystemUser;
import com.nature.model.company.Company;

@Entity
public class UserVisit {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userVisitId", unique = true, nullable = false)
	/** 标识 */
	private Integer userVisitId;
	
	@ManyToOne
	private Company company;
	
	@ManyToOne
	private SystemUser user;
	
	@Column(name="visitTime")
	private Date visitTime;
	
	@Transient
	private String days;
	@Transient
	private String weeks;
	@Transient
	private String months;

	public Integer getUserVisitId() {
		return userVisitId;
	}

	@Override
	public String toString() {
		return "UserVisit [userVisitId=" + userVisitId + ", visitTime=" + visitTime + "]";
	}

	public Company getCompany() {
		return company;
	}

	public SystemUser getUser() {
		return user;
	}

	public String getDays() {
		return days;
	}

	public String getWeeks() {
		return weeks;
	}

	public String getMonths() {
		return months;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setUserVisitId(Integer userVisitId) {
		this.userVisitId = userVisitId;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	
	
	
	
}
