package com.nature.model.company;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @description
 * @author len
 * @createDate 2018年5月9日
 */
public class PayRecord {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "payRecordId", unique = true, nullable = false)
	/** 标识 */
	private Integer payRecordId;
	
	@OneToOne
	private CompanyAccount account;
	
	@Column(name="payDate")
	//支付时间
	private Date payDate;
	
	@Column(name="amount")
	//金额
	private String amount;
	
	@Column(name="remark")
	//备注
	private String remark;

	@Override
	public String toString() {
		return "PayRecord [payRecordId=" + payRecordId + ", payDate=" + payDate + ", amount=" + amount + ", remark="
				+ remark + "]";
	}

	public Integer getPayRecordId() {
		return payRecordId;
	}

	public CompanyAccount getAccount() {
		return account;
	}

	public Date getPayDate() {
		return payDate;
	}

	public String getAmount() {
		return amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setPayRecordId(Integer payRecordId) {
		this.payRecordId = payRecordId;
	}

	public void setAccount(CompanyAccount account) {
		this.account = account;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
