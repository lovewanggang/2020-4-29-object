package com.nature.util.tag;

import java.io.IOException;
import java.io.Writer;


import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class Times extends Component{
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Times(ValueStack arg0) {   
        super(arg0);   
    }   
	
	@Override  
	    public boolean start(Writer writer) {
	        boolean result = super.start(writer);
	       // System.out.println(name);
	        if(name.equals("")){
	        	return false;
	        }
	        try {
	        	java.util.Date date = new java.util.Date(Long.parseLong(name));
	        	String str=DateTime.getDate(date);
	            writer.write(str);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        return result;
	    }   
}
