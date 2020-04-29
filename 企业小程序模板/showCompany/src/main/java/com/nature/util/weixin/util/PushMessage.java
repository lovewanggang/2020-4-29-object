package com.nature.util.weixin.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nature.model.wxmini.TemplateKeyNote;
import com.nature.model.wxmini.TemplateMessage;
import com.nature.service.WxPlatformBLL;
import com.nature.util.ConstantsUtil;





public class PushMessage {
	private static String[] locations = { "classpath*:applicationContext*.xml" };
	private static AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
			locations);
	private static WxPlatformBLL wxPlatformBLL = (WxPlatformBLL) ctx.getBean("wxPlatformBLLImpl");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		pushMsg(ConstantsUtil.KEY,null,"o2Yy35YSa-RdyWgvaR5xTaUZmdN0","wx2017123123");

	}
	
	
	
	public static void pushMsg(String Key,String pages,String openId,String formId){
		TemplateMessage message = new TemplateMessage();
		message.setPage(pages);
		message.setTemplate_id(Key);
		message.setTouser(openId);
		message.setFormId(formId);
        Map<String, TemplateKeyNote> map = new HashMap<String, TemplateKeyNote>();
		
		TemplateKeyNote keyNote = new TemplateKeyNote();

		keyNote = new TemplateKeyNote();
		keyNote.setColor("#173177");
		keyNote.setValue("咖啡");
		map.put("keyword1", keyNote);
		
		keyNote = new TemplateKeyNote();
		keyNote.setColor("#173177");
		keyNote.setValue("213215646");
		map.put("keyword2", keyNote);
		
		keyNote = new TemplateKeyNote();
		keyNote.setColor("#173177");
		keyNote.setValue("星巴克");
		map.put("keyword3", keyNote);
		
		keyNote = new TemplateKeyNote();
		keyNote.setColor("#173177");
		keyNote.setValue("2016-6-6");
		map.put("keyword4", keyNote);
	
		message.setData(map);
		String accessToken = wxPlatformBLL.getWxPlatform(ConstantsUtil.PUBLIC_APP_ID).getAccess_token();
		System.out.println(JSONObject.fromObject(message).toString()+":::::::::::::"+accessToken);
				WxMiniUtil.pushMsgByTemplate(JSONObject.fromObject(message).toString(), accessToken);
		System.out.println("push客服结束");
	}
}
