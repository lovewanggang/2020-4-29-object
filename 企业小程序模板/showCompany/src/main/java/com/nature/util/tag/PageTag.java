package com.nature.util.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ContextBeanTag;

import com.opensymphony.xwork2.util.ValueStack;

public class PageTag<T> extends ContextBeanTag{
	/**
	 * 
	 */
	private static final long serialVersionUID = -513590606041037052L;
	private String name;
//	private String action;
	private String encode;
	private String escapeAmp;


	@Override  
	public Component getBean(ValueStack arg0, HttpServletRequest arg1, HttpServletResponse arg2) {   
        return new Page<T>(arg0);   
    }   
	
    //   
    @SuppressWarnings("unchecked")
	@Override
	protected void populateParams() {   
        super.populateParams();   
        Page p = (Page)component;   
        p.setName(name);
        
//        if (action != null) {
//            p.setAction(action);
//        }
        
        if (encode != null) {
            p.setEncode(Boolean.valueOf(encode).booleanValue());
        }

        if (escapeAmp != null) {
            p.setEscapeAmp(Boolean.valueOf(escapeAmp).booleanValue());
        }
    }
    
	public void setName(String name) {
		this.name = name;
	}
	
//	public void setAction(String action) {
//		this.action = action;
//	}
	
	public void setEncode(String encode) {
		this.encode = encode;
	}

	public void setEscapeAmp(String escapeAmp) {
		this.escapeAmp = escapeAmp;
	}
}

