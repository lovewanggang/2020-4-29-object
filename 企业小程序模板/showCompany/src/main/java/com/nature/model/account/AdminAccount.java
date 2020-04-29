package com.nature.model.account;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AdminAccount {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "adminAccountId", unique = true, nullable = false)
	/** 标识 */
	private Integer adminAccountId;
	
	@Column(name="loginName")
	private String loginName;
	
	@Column(name="password")
	private String password;

	public Integer getAdminAccountId() {
		return adminAccountId;
	}

	public String getLoginName() {
		return loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setAdminAccountId(Integer adminAccountId) {
		this.adminAccountId = adminAccountId;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
}
