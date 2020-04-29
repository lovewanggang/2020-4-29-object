package com.nature.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheUtil {

    /**
     * 更新缓存时记录的时间
     */
    private volatile long time = 0L;
 
    /**
     * 正在更新缓存时的门闩，为 true 时表示当前没有更新缓存，为 true 时表示当前正在更新缓存
     */
    private volatile boolean updateGate = true;
 
    /**
     * 缓存容器
     */
    private static Map<String, Object> cacheMap = new ConcurrentHashMap<String, Object>();
    
    public static VeriCode getObjectBykey(String key){
    	VeriCode code = new VeriCode();
    	code = (VeriCode) cacheMap.get(key);
    	if(code==null)
    		return null;
    	if(isTimeout(code.getCreateTime())){
    		return code;
    	}
    	cacheMap.remove(key);
    	return null;
    }
    
    private static boolean isTimeout(long time){
    	return (System.currentTimeMillis()-time <= ConstantsUtil.CODE_TIMEOUT); 
    }
    
    public static void clearMap(){
    	VeriCode code;
    	for (String key : cacheMap.keySet()) {
    		code = (VeriCode) cacheMap.get(key);
    		if(!isTimeout(code.getCreateTime())){
    			cacheMap.remove(key);
        	}
		}
    }
    
    public static void setObject(VeriCode veriCode){
    	clearMap();
    	cacheMap.put(veriCode.getPhone(), veriCode);
    }
    
    public static VeriCode getObject(String key){
    	return (VeriCode) cacheMap.get(key);
    }
    
    public static void setObject(String key,Object object){
    	if(key==null)
    		return;
    	cacheMap.put(key, object);
    }
    
    public static Object getObject2(String key){
    	return cacheMap.get(key);
    }
   
}