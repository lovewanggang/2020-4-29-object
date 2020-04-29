package com.nature.util.weixin.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nature.model.wxmini.AccessToken;
import com.nature.model.wxmini.JsapiTicket;
import com.nature.model.wxmini.SessionKey;
import com.nature.util.ConstantsUtil;
import com.nature.util.RandomUtil;
import com.nature.util.SignUtil;
import com.nature.util.TimeUtil;
import com.nature.util.weixin.pay.ClientPackageHandler;
import com.nature.util.weixin.pay.PackageHandler;



/**
 * 小程序通用接口工具类
 * 
 * @author hhy
 * @date 2018-2-5
 */
public class WxMiniUtil {
	private static Logger log = LoggerFactory.getLogger(WxMiniUtil.class);

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			System.out.println(buffer.toString());
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址 请求方式（POST）
	 * @param outputStr
	 *            提交的数据
	 * @return Map键值对数据
	 */
	public static Map<String, String> httpRequest(String requestUrl,
			String outputStr) {
		Map<String, String> map = new HashMap<String, String>();

		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("POST");

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			InputStream inputStream = httpUrlConn.getInputStream();

			map = parseXml(inputStream);
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return map;
	}

	
	
	//code 换取 session_key
	public final static String session_key_url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

	
	public static SessionKey getSessionKey(String code, String appid, String secret) {
		SessionKey sessionKey = null;
		System.out.println("=========getSessionKey中的============="+code);
		String requestUrl = session_key_url.replace("JSCODE",
				code).replace("SECRET", secret).replace("APPID", appid);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				sessionKey = new SessionKey();
				sessionKey.setOpenId(jsonObject.getString("openid"));
				sessionKey.setSession_key(jsonObject.getString("session_key"));
				
				
			} catch (JSONException e) {
				sessionKey = null;
				// 获取sessionKey失败
				log.error("获取sessionKey失败 errcode:{} errmsg:{}", jsonObject
						.getInt("errcode"), jsonObject.getString("errmsg"));
			}
		}
		return sessionKey;
	}

	
	// 获取access_token的接口地址（GET） 限2000（次/天）
		public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

		/**
		 * 获取access_token
		 * 
		 * @param appid
		 *            凭证
		 * @param appsecret
		 *            密钥
		 * @return
		 */
		public static AccessToken getAccessToken(String appid, String appsecret) {
			AccessToken accessToken = null;

			String requestUrl = access_token_url.replace("APPID", appid).replace(
					"APPSECRET", appsecret);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				System.out.println("--get token success----");
				try {
					accessToken = new AccessToken();
					accessToken.setToken(jsonObject.getString("access_token"));
					accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
				} catch (JSONException e) {
					accessToken = null;
					// 获取token失败
					log.error("获取token失败 errcode:{} errmsg:{}", jsonObject
							.getInt("errcode"), jsonObject.getString("errmsg"));
				}
			} else {
				System.out.println("--get token play is fail---");
			}
			return accessToken;
		}
		
		// 获取jsapi_ticket的接口地址GET
		public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

		public static JsapiTicket getJsapiTicket(String accessToken) {
			JsapiTicket jsapiTicket = null;
			String requestUrl = jsapi_ticket_url.replaceAll("ACCESS_TOKEN",
					accessToken);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				try {
					jsapiTicket = new JsapiTicket();
					jsapiTicket.setTicket(jsonObject.getString("ticket"));
					jsapiTicket.setExpires_in(jsonObject.getInt("expires_in"));
				} catch (JSONException e) {
					jsapiTicket = null;
					// 获取ticket失败
					log.error("获取ticket失败 errcode:{} errmsg:{}", jsonObject
							.getInt("errcode"), jsonObject.getString("errmsg"));
				}
			}
			return jsapiTicket;
		}
		
		/**
		 * 微信图片上传后台代码
		 * @param root 上传基础路径，自动生成文件夹
		 * @param Wxfile 微信端传过来的文件
		 * @param fileName 保存到服务器的文件名
		 * @throws Exception
		 */
		public static boolean uploadPicture(String root,File Wxfile,String fileName) throws Exception {
			 if (Wxfile == null) {
		        	System.out.println("null");
		            return false;
		        }
		        InputStream in = null;
		        OutputStream out = null;
		        try {
		            in = new FileInputStream(Wxfile);
		            String serverRealPath = root;
		            File f = new File(serverRealPath);
		            if(!f.exists())
		            {
		            	f.mkdir();
		            }
		            // 设置文件在服务器上的存储路径, 以便读取
		            File targetFile = new File(serverRealPath, fileName);
		            out = new FileOutputStream(targetFile);
		            byte[] buffer = new byte[1024 * 1024];
		            int length;
		            while ((length = in.read(buffer)) > 0) {
		                out.write(buffer, 0, length);
		            }
		            in.close();
		            out.close();
		        } catch (FileNotFoundException e1) {
		            e1.printStackTrace();
		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		        return true;
	    }
		
		// 统一下单
		public static String unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

		public static Map<String, String> getPrepayId(String xml_body) {
			Map<String, String> map = httpRequest(unifiedorder_url, xml_body);
			// log.info(map.toString());
			System.out.println("=====" + map.toString());
			return map;
		}

		/**
		 * 提起支付请求
		 * 
		 * @param request
		 * @param ticket
		 * @param body
		 * @param out_trade_no
		 * @param total
		 * @param trade_type
		 * @param notify_url
		 * @param openid
		 * @return
		 */
		public static Map<String, String> unifiedOrder(HttpServletRequest request,
				String ticket, String body, String out_trade_no, Double total,
				String trade_type, String notify_url, String openid) {
			String nonceStr = RandomUtil.getRandomStr();
			String timestamp = TimeUtil.getTimeStamp();
			// String ticket =
			// wxPlatformBLL.getWxPlatformByAppId(ConstantsUtil.PUBLIC_APP_ID).getJsapi_ticket();
			String signature = SignUtil.getSignature(ticket, nonceStr, timestamp,
					SignUtil.getUrl(request));

			DecimalFormat dFormat = new DecimalFormat("0");
			PackageHandler packageHandler = new PackageHandler();
			packageHandler.setParameter("appid", ConstantsUtil.PUBLIC_APP_ID);// 商户aooid
			packageHandler.setParameter("mch_id", ConstantsUtil.PUBLIC_MCH_ID);// 商户号
			packageHandler.setParameter("nonce_str", nonceStr);// 随机数

			if (body == null || body.isEmpty()) {
				packageHandler.setParameter("body", "支付测试");// 标题
			} else {
				packageHandler.setParameter("body", body);
			}

			packageHandler.setParameter("out_trade_no", out_trade_no);// 商户订单号
			packageHandler.setParameter("total_fee", dFormat.format(total * 100));// 支付金额
			packageHandler
					.setParameter("spbill_create_ip", "127.0.0.1");// 客户端ip地址
			packageHandler.setParameter("notify_url", notify_url);// 回调接口
			packageHandler.setParameter("trade_type", trade_type);
			if (trade_type.equals("JSAPI")) {
				packageHandler.setParameter("openid", openid);
			}
			String sign = packageHandler.createSHA1Sign(ConstantsUtil.KEY);

			packageHandler.setParameter("sign", sign);

			String xml_body = packageHandler.getXmlBody();
			Map<String, String> map = WxMiniUtil.getPrepayId(xml_body);
			String prepayId = map.get("prepay_id");
			System.out.println(xml_body);
			if (prepayId == null) {
				return map;
			}

			System.out.println(prepayId);
			String packageId = "prepay_id=" + prepayId;

			ClientPackageHandler clientHandler = new ClientPackageHandler();
			clientHandler.setParameter("appId", ConstantsUtil.PUBLIC_APP_ID);
			// clientHandler.setParameter("paySign", ConstantsUtil.PUBLIC_MCH_ID);
			clientHandler.setParameter("timeStamp", timestamp);
			clientHandler.setParameter("package", packageId);
			clientHandler.setParameter("nonceStr", nonceStr);
			clientHandler.setParameter("signType", "MD5");

			String paySign = clientHandler.createSHA1Sign(ConstantsUtil.KEY);
			clientHandler.setParameter("paySign", paySign);
			clientHandler.setParameter("packageId", packageId);
			clientHandler.setParameter("prepayId", prepayId);
			clientHandler.setParameter("signature", signature);
			return (Map<String, String>) clientHandler.getAllParameters();
		}

		/**
		 * 退款申请
		 * 
		 * @param xml_body
		 * @return
		 */
		public static String refund_url = "https://api.mch.weixin.qq.com/secapi/pay/refund";

		public static Map<String, String> refundFee(String xml_body) {
			return httpRequest(refund_url, xml_body);
		}

		/**
		 * 查询订单
		 */
		public static String order_query_url = "https://api.mch.weixin.qq.com/pay/orderquery";

		public static Map<String, String> orderQuery(String xml_body) {
			return httpRequest(order_query_url, xml_body);
		}
		
		//获取带参数无限个数小程序码接口
				public static String getWxminiQRCode_url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN";
		/**
		 * 
		 * @param path 小程序二维码保存路径  绝对路径
		 * @param accessToken
		 * @param scene //参数设置 格式  a=123&b=213
		 * @param page //小程序页面 格式 pages/index/index
		 */
		public void getminiqrQr(String path, String accessToken,String scene,String page) {
			 try
		        {
		            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=你的access_token");
		            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		            httpURLConnection.setRequestMethod("POST");// 提交模式
		            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
		            // conn.setReadTimeout(2000);//读取超时 单位毫秒
		            // 发送POST请求必须设置如下两行
		            httpURLConnection.setDoOutput(true);
		            httpURLConnection.setDoInput(true);
		            // 获取URLConnection对象对应的输出流
		            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
		            // 发送请求参数
		            JSONObject paramJson = new JSONObject();
		            paramJson.put("scene", scene);
		            paramJson.put("page", page);
		            paramJson.put("width", 430);
		            paramJson.put("auto_color", true);
		            /**
		             * line_color生效
		             * paramJson.put("auto_color", false);
		             * JSONObject lineColor = new JSONObject();
		             * lineColor.put("r", 0);
		             * lineColor.put("g", 0);
		             * lineColor.put("b", 0);
		             * paramJson.put("line_color", lineColor);
		             * */

		            printWriter.write(paramJson.toString());
		            // flush输出流的缓冲
		            printWriter.flush();
		            //开始获取数据
		            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
		            File file = new File(path);
		            if(file.exists())
		            {
		            	file.createNewFile();
		            }
		            OutputStream os = new FileOutputStream(file);
		            int len;
		            byte[] arr = new byte[1024];
		            while ((len = bis.read(arr)) != -1)
		            {
		                os.write(arr, 0, len);
		                os.flush();
		            }
		            os.close();
		        }
		        catch (Exception e)
		        {
		            e.printStackTrace();
		        }
		    }  
	    
		/**
		 * 发送模版消息
		 * @param outputStr
		 *            模版消息json String
		 * @param accessToken
		 * @return 消息id
		 */
		public static String push_msg_url = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";

		public static long pushMsgByTemplate(String outputStr, String accessToken) {
			long msgid = 0;
			String requestUrl = push_msg_url.replace("ACCESS_TOKEN", accessToken);
			JSONObject jsonObject = httpRequest(requestUrl, "POST", outputStr);
			if (null != jsonObject) {
				// log.info(jsonObject.toString());
				try {
					msgid = jsonObject.getLong("msgid");
				} catch (JSONException e) {
					log.error("发送Template失败 errcode:{} errmsg:{}", jsonObject
							.getInt("errcode"), jsonObject.getString("errmsg"));
				}

			}
			return msgid;
		}


	
	public static Map<String, String> parseXml(InputStream inputStream) throws Exception {  
        // 将解析结果存储在HashMap中  
        Map<String, String> map = new HashMap<String, String>();  
  
//        // 读取输入流  
//        SAXReader reader = new SAXReader();  
//        Document document = reader.read(inputStream);  
        StringBuffer content = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        String line = null;
        while((line = br.readLine()) != null ){
         content.append(line+"\n");
        }
        br.close();
        Document document = DocumentHelper.parseText(content.toString());
        // 得到xml根元素  
        Element root = document.getRootElement();  
        // 得到根元素的所有子节点  
        List<Element> elementList = root.elements();  
  
        // 遍历所有子节点  
        for (Element e : elementList)  
            map.put(e.getName(), e.getText());  
  
        // 释放资源  
        inputStream.close();  
        inputStream = null;  
  
        return map;  
    }  
	public static String toUtf8(String str) {
		String utf8Str = "";
		if (str != null) {
			try {
				utf8Str = new String(str.getBytes("UTF-8"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return utf8Str;
	}
	
	

}
