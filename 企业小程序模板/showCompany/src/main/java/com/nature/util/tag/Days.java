package com.nature.util.tag;

import java.io.IOException;
import java.io.Writer;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class Days extends Component{
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Days(ValueStack arg0) {   
        super(arg0);   
    }   
	
	@Override  
	    public boolean start(Writer writer) {
	        boolean result = super.start(writer);
	        try {
	        	java.util.Date date = (java.util.Date) findValue(name);
	        	String str=DateTime.getDay(date);
	            writer.write(str);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        return result;
	    }   
}
