package com.nature.util.weixin.pay;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.nature.util.MD5Util;


public class ClientPackageHandler {

private SortedMap<String, String> parameters = new TreeMap<String, String>();
	
	/**
	 * 获取参数值
	 * @param parameter 参数名称
	 * @return String 
	 */
	public String getParameter(String parameter) {
		String s = (String)this.parameters.get(parameter); 
		return (null == s) ? "" : s;
	}
	
	/**
	 * 设置参数值
	 * @param parameter 参数名称
	 * @param parameterValue 参数值
	 */
	public void setParameter(String parameter, String parameterValue) {
		String v = "";
		if(null != parameterValue) {
			v = parameterValue.trim();
		}
		this.parameters.put(parameter, v);
	}
	
	/**
	 * 返回所有的参数
	 * @return SortedMap
	 */
	public SortedMap getAllParameters() {
		return this.parameters;
	}
	
	public String getXmlBody() {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>\r\n");
		Set es = getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"appkey".equals(k)) {
				sb.append("<" + k + ">" + v + "</" + k + ">" + "\r\n");
			}
		}
		sb.append("</xml>");
		return sb.toString();
	}
	
	/**
	 * 创建签名SHA1
	 * 
	 * @param signParams
	 * @return
	 * @throws Exception
	 */
	public String createSHA1Sign(String key) {
		StringBuffer sb = new StringBuffer();
		Set es = getAllParameters().entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
		}
		
		sb.append("key="+key);
		String params = sb.toString();
		String appsign = MD5Util.getMD5String(params, "UTF-8").toUpperCase();
		
		return appsign;
	}
}
