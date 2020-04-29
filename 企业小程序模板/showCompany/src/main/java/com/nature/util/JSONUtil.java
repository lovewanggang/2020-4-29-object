package com.nature.util;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * json转换工具类
 * @author panda
 *
 */
public class JSONUtil {
	 /**  
     * 实体类型的对象  
     */  
    public static final int OBJECT_TYPE_BEAN = 1;  
  
    /**  
     * 集合类型对象  
     */  
    public static final int OBJECT_TYPE_LIST = 2;  
	public JSONUtil(){
		
	}
	/**  
     * 方法名称：parseJsonObjectToBean  
     * 内容摘要：将一个JSON对象转换成指定类型的Bean  
     * @param json  任意实体，包括Json格式字符串  
     * @param clazz 需要转换的bean的Class  
     * @return  
     */  
    public static Object parseJsonObjectToBean(Object json, Class clazz)  
    {  
        Object bean = null;  
        try  
        {  
            JSONObject jsonObject = JSONObject.fromObject(json);  
  
            bean = JSONObject.toBean(jsonObject, clazz);  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
  
        return bean;  
    }  
	/**  
     * 方法名称：parseJsonArrayToBean  
     * 内容摘要：将一个JSON对象转换成指定类型的Bean集合  
     * @param json  任意实体，包括Json格式字符串  
     * @param clazz 需要转换的bean的Class  
     * @return  
     */  
    public static List<Object> parseJsonArrayToBean(Object json, Class clazz)  
    {  
        List<Object> list = new LinkedList<Object>();  
        try  
        {  
            JSONArray jsonArray = JSONArray.fromObject(json);  
  
            Object[] beans = jsonArray.toArray();  
  
            Object bean = null;  
            for(int i=0 ; i< beans.length; i++)  
            {  
                bean = parseJsonObjectToBean(beans[i], clazz);  
                list.add(bean);  
            }  
  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
  
        return list;  
    }  
    
    
    /**  
     * 方法名称：getJsonString  
     * 内容摘要：将对象转换为JSON字符串  
     * @param object  
     * @param objType  
     * @return  
     */  
    public static String getJsonString(Object object, int objType)  
    {  
        JSON json = null;  
        try  
        {  
            json = null;  
  
            if (objType == OBJECT_TYPE_BEAN)  
            {  
                json = JSONObject.fromObject(object);  
            }  
            else if (objType == OBJECT_TYPE_LIST)  
            {  
                json = JSONArray.fromObject(object);  
            }  
            else  
            {  
                return "待写入实体的对象类型不正确";  
            }  
        }  
        catch (Exception e)  
        {  
            return "转换JSON字符串出错";  
        }  
  
        return json.toString();  
    }  
  

	public static Object  jsonToObject(Object  jsonStr,Class clazz) {
		Object bean = null;  
		System.out.println("-----:"+jsonStr+"----:"+clazz);
		JSONObject jsonOrder = JSONObject.fromObject(jsonStr);
		bean=JSONObject.toBean(jsonOrder,clazz);
		return bean;
	}
	private static ObjectMapper objectMapper = new ObjectMapper();
	public static Map<String, Object> parseMap(String jsonStr) throws IOException {
		if(jsonStr==null||jsonStr.equals(""))
			return null;
		//
		JSONObject jsonOrder = JSONObject.fromObject(jsonStr);
		Map<String, Object> map = objectMapper.readValue(jsonStr, Map.class);
		return map;
	}
	public static void setObjectMapper(ObjectMapper objectMapper) {
		JSONUtil.objectMapper = objectMapper;
	}
	
}
