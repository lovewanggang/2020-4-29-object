package controller;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @description
 * @author len
 * @createDate 2018年4月25日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring.xml","classpath:spring-hibernate.xml"})
@WebAppConfiguration
public class CompanyControllerTest {

	@Autowired
	private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();   //构造MockMvc
    }    
	
	@Test 
	public void test1(){
		 try {
			 System.out.println("==========================123"+mockMvc);
			ResultActions mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("company/test.do","json"))
					.andDo(MockMvcResultHandlers.print())
			    .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("result").value("test"));
//			 int status = mvcResult.getResponse().getStatus();
//			  System.out.println("请求状态码：" + status);
//			  String result = mvcResult.getResponse().getContentAsString();
//			  System.out.println("接口返回结果：" + result);
//			  JSONObject resultObj = JSON.parseObject(result);
//			  // 判断接口返回json中success字段是否为true
//			  Assert.assertTrue(resultObj.getBooleanValue("success"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @Test 
    @Ignore
	public void test2(){
    	System.out.println("======================tests");
    	Assert.assertTrue(true);
    }

}
