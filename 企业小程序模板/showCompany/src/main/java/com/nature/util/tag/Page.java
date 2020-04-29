package com.nature.util.tag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.components.ContextBean;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.nature.util.DataPage;
import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="page", tldTagClass="com.dx.tag.WapPageTag", description="This tag is used to create a pager")
public class Page<T> extends ContextBean{
	private String name;
//	private String action;
	private boolean encode = true;
	private boolean escapeAmp = true;
	
	public Page(ValueStack arg0) {   
        super(arg0);   
    }   
	
	@SuppressWarnings("unchecked")
	@Override  
	public boolean start(Writer writer) {
	        boolean result = super.start(writer);
	        try {
	        	DataPage <T> drugs = (DataPage<T>) findValue(name);
	        	String str=construct(drugs, encode, escapeAmp);
	            writer.write(str);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
	        return result;
	 }
	
	@SuppressWarnings("unchecked")
	public String construct(DataPage<T> page, boolean encode, boolean escapeAmp) {
		//int pageSize = page.getPageSize();
		DataPageUtil<T> dataPageUtil = new DataPageUtil<T>(page);
		int pageCount = dataPageUtil.getPageCount();
		int pageNo = dataPageUtil.getPageNo();
		//int totalRecord = page.getTotalRecord();
		
		String url=ServletActionContext.getRequest().getContextPath();
		if(ServletActionContext.getActionMapping().getNamespace().endsWith("/"))
		{
			url=url+ServletActionContext.getActionMapping().getNamespace()+ServletActionContext.getActionMapping().getName()+"."+ServletActionContext.getActionMapping().getExtension();
		}
		else
			url=url+ServletActionContext.getActionMapping().getNamespace()+"/"+ServletActionContext.getActionMapping().getName()+"."+ServletActionContext.getActionMapping().getExtension();
		
		Map params=ServletActionContext.getRequest().getParameterMap();
		
		url=url+"?";
		if(params.size()>0)
		{
			String queryString="";
			for(Object key:params.keySet())
			{
				if(!key.equals("pageNo")&&!key.equals("currentNo")&&!key.equals("pageCount")&&!key.equals("r")&&!key.equals("uid")&&!key.equals("sid"))
				{
					String value=((String[])params.get(key))[0];
					
					if(key.equals("paramUrl")){
						try {
							value = java.net.URLEncoder.encode(value, "UTF-8");
						} catch (UnsupportedEncodingException e) {
							value = "";
						}
					}
					
					if(escapeAmp)
					{
						queryString+=key+"="+value+"&amp;";
					}
					else
					{
						queryString+=key+"="+value+"&";
					}
				}
			}
			url=url+queryString;
		}		
		
		StringBuffer strBuf = new StringBuffer();
		int start = 1;
		int end = 1;
		
		if(pageCount >=2)
		{
			if(pageNo != 1)
			{
				String url_first_page=addParameter(url, "pageNo", 1, escapeAmp);
				if(encode)
				{
					url_first_page = ServletActionContext.getResponse().encodeURL(url_first_page);
				}
				strBuf.append("<a href='").append(url_first_page).append("'  class='nextPage'>首页</a>");
			}
			if(pageNo == 1)
			{
				strBuf.append("<a  class='nextPage'>首页</a>");
			}
			if((pageNo != 1)&&(dataPageUtil.hasPrevPage()))
			{
				String url_prev_page=addParameter(url, "pageNo", (pageNo-1), escapeAmp);
				if(encode)
				{
					url_prev_page = ServletActionContext.getResponse().encodeURL(url_prev_page);
				}
				strBuf.append("<a href='").append(url_prev_page).append("'  class='nextPage'>上页</a>");
			}
			if (pageCount>=2) {
				if(pageNo==1){
					start =1;
				}else{
					start=pageNo-1;
				}
				if (pageCount!=pageNo) {
					end =pageNo+1;
				}else {
					end=pageNo;
				}
			}
			for (int i = start; i <=end ; i++) {
				String url_prev_page=addParameter(url, "pageNo", (i), escapeAmp);
				if (i==pageNo) {
					strBuf.append("<a href='").append(url_prev_page).append("' class='current'>"+i+"</a>");

				}else{
					strBuf.append("<a href='").append(url_prev_page).append("' class='pages'>"+i+"</a>");
				}
				
			}
			if((pageNo != pageCount)&&(dataPageUtil.hasNextPage()))
			{
				String url_next_page=addParameter(url, "pageNo", (pageNo+1), escapeAmp);
				
				if(encode)
				{
					url_next_page = ServletActionContext.getResponse().encodeURL(url_next_page);
				}
				strBuf.append("<a href='").append(url_next_page).append("'  class='nextPage'>下页</a>");
			}
			
			if(pageNo !=pageCount)
			{
				String url_last_page=addParameter(url, "pageNo", pageCount, escapeAmp);
				if(encode)
				{
					url_last_page = ServletActionContext.getResponse().encodeURL(url_last_page);
				}
				strBuf.append("<a href='").append(url_last_page).append("' class='nextPage'>末页</a>");
			}
			if(pageNo ==pageCount)
			{
				strBuf.append("<a class='nextPage'>末页</a>");
			}
			//String url_input_page=addParameter(url, "pageNo", pageCount, escapeAmp);
			strBuf.append("<a  class='total'>共"+dataPageUtil.getTotalRecord()+"条</a>");
		}
		
		return strBuf.toString();
	}
	
	
	public String addParameter(String url, String pageNoName, int pageNo, boolean escapeAmp)
	{
//		if(escapeAmp)
//		{
//			url.append("&amp");
//		}
//		else
//		{
//			url.append("&");
//		}
		
		return (url+pageNoName+"="+pageNo);
		
	}

	public void setName(String name) {
		this.name = name;
	}
	
//	public void setAction(String action) {
//		this.action = action;
//	}
//
//	public String getAction() {
//		return action;
//	}
	
	@StrutsTagAttribute(description="Whether to encode parameters", type="Boolean", defaultValue="true")
	public void setEncode(boolean encode) {
		this.encode = encode;
	}
	
	@StrutsTagAttribute(description="Specifies whether to escape ampersand (&amp;) to (&amp;amp;) or not", type="Boolean", defaultValue="true")
	public void setEscapeAmp(boolean escapeAmp) {
		this.escapeAmp = escapeAmp;
	}

}