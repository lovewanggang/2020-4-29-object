package com.nature.util.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class DayTag extends org.apache.struts2.views.jsp.ComponentTagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8893811207140276454L;
	private String name;

	public void setName(String name) {
		this.name = name;
	}
	@Override  
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {   
        return new Days(arg0);   
    }   
  
    //   
    @Override
	protected void populateParams() {   
        super.populateParams();   
        Days days = (Days)component;   
        days.setName(name);
         
    }   
}
