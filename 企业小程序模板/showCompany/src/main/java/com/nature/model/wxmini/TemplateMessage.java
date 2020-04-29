package com.nature.model.wxmini;

import java.util.Map;

/**
 * 模版消息实体类
 * @author liuyuan
 *
 */
public class TemplateMessage {

	private String touser;//用户openid
	private String template_id;//模版id
	private String page;//跳转的小程序页面
	private String formId;//表单提交场景下，为 submit 事件带上的 formId；支付场景下，为本次支付的 prepay_id
	private Map<String, TemplateKeyNote> data;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(String templateId) {
		template_id = templateId;
	}

	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Map<String, TemplateKeyNote> getData() {
		return data;
	}
	public void setData(Map<String, TemplateKeyNote> data) {
		this.data = data;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	
}
