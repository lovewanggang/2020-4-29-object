package com.nature.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
 
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class MyStrutsFilter extends StrutsPrepareAndExecuteFilter{
    
    private static Logger log = LoggerFactory.getLogger(MyStrutsFilter.class); 
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String url = request.getRequestURI();        
        //System.out.println(url); 
       // log.info(url); 
        if (url.contains("/ueditor/jsp/")) {            
       //     log.info("zidingyi");         
            chain.doFilter(req, res);        
        }else{ 
        //	log.info("System"); 
            //System.out.println("使用默认过滤器");            
            super.doFilter(req, res, chain);        
        }
    }
}