package com.nature.util;

public class VeriCode {

	private Integer codeId;
	private String phone;
	private String code;
	private long createTime;
	
	public VeriCode(){}
	public VeriCode(Integer codeId, String phone, String code, long createTime) {
		super();
		this.codeId = codeId;
		this.phone = phone;
		this.code = code;
		this.createTime = createTime;
	}
	public Integer getCodeId() {
		return codeId;
	}
	public void setCodeId(Integer codeId) {
		this.codeId = codeId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	
}