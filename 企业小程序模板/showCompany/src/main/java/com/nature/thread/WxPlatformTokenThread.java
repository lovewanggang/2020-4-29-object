package com.nature.thread;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nature.interceptor.MyStrutsFilter;
import com.nature.model.company.CompanyAccount;
import com.nature.model.wxmini.AccessToken;
import com.nature.model.wxmini.JsapiTicket;
import com.nature.model.wxmini.WxPlatform;
import com.nature.service.WxPlatformBLL;
import com.nature.util.ConstantsUtil;
import com.nature.util.weixin.util.WxMiniUtil;

import sun.util.logging.resources.logging;


public class WxPlatformTokenThread {

	private static Logger log = LoggerFactory.getLogger(MyStrutsFilter.class); 
	private static String[] locations = { "classpath*:spring-hibernate.xml" };
	private static AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
			locations);
	private static WxPlatformBLL wxPlatformBLL = (WxPlatformBLL) ctx.getBean("wxPlatformBLLImpl");

	public static void getAndSaveToken() {
		log.info("====================WxPlatformTokenThread=========================");
		Calendar calendar = Calendar.getInstance();

		Date time = calendar.getTime(); // 得出执行任务的时间
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			private WxPlatform wxPlatform;
			private AccessToken accessToken = null;
			private JsapiTicket jsapiTicket = null;
			private List<WxPlatform> wxPlatforms = null;
			@Override
			public void run() {
				long nowtime = System.currentTimeMillis();
				wxPlatforms = wxPlatformBLL.listWxplPlatforms();
				for (int i = 0; i < wxPlatforms.size(); i++) {
					accessToken = WxMiniUtil.getAccessToken(wxPlatforms.get(i).getAppId(),wxPlatforms.get(i).getAppSecret());
					if (accessToken != null) {
//						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//						System.out.println("获取成功：PUBLIC_APP_ID"+ wxPlatforms.get(i).getAppId() + "-->>accessToken:" + accessToken.getToken());

						wxPlatforms.get(i).setAccess_token(accessToken.getToken());
						jsapiTicket = WxMiniUtil.getJsapiTicket(accessToken.getToken());

						wxPlatforms.get(i).setJsapi_ticket(jsapiTicket.getTicket());
//						System.out.println("获取成功：PUBLIC_APP_ID" + wxPlatforms.get(i).getAppId() + "-->>jsapiTicket:" + jsapiTicket.getTicket());

						wxPlatforms.get(i).setCreateTime(nowtime);
//						System.out.println("==========wxPlatform============"+wxPlatforms.get(i));
						wxPlatformBLL.updateWxPlatform(wxPlatforms.get(i));
					} else {
						System.out
								.println("------get fail---------- ：PUBLIC_APP_ID:"
										+ wxPlatforms.get(i).getAppId());
					}
//					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				}
				
				
			}
		}, time, 2 * 55 * 60 * 1000);
	}
}
