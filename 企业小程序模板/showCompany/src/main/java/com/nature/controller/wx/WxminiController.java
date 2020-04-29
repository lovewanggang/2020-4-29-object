package com.nature.controller.wx;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.nature.action.BaseAction;
import com.nature.model.account.SystemUser;
import com.nature.model.company.CompanyAccount;
import com.nature.model.wxmini.SessionKey;
import com.nature.model.wxmini.WxPlatform;
import com.nature.service.CompanyService;
import com.nature.service.SystemUserBLL;
import com.nature.service.WxPlatformBLL;
import com.nature.util.AesCbcUtil;
import com.nature.util.ConstantsUtil;
import com.nature.util.DXController;
import com.nature.util.EncrypDES;
import com.nature.util.weixin.pay.PayCommonUtil;
import com.nature.util.weixin.pay.XMLUtil;
import com.nature.util.weixin.util.WxMiniUtil;

/**
 * 微信小程序控制层
 */
@ParentPackage("json-default")
@Namespace("/wx")
public class WxminiController extends BaseAction {

	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	/* Service层 */
	@Resource
	private SystemUserBLL systemUserBLL;
	@Resource
	private WxPlatformBLL wxPlatformBLL;
	@Resource
	private CompanyService companyService;
	
	private String encryptedData;
	private String iv;
	private String code;
	private String logs;
	private File Wxfile;
	private Map<String, Object> dataMap = new HashMap<String, Object>();
	private Map<String, String> payMap = new HashMap<String, String>();
	private String SCcode;
	//小程序后台客服配置的token
	private static String token = "xiaoshiniudao";
	
	
	/*****************************************************
	 * 小程序客服配置
	 *****************************************************/
	@Action(value="kefu", results= {
			@Result(name="success", type="json", params= {
					"root","echostr"
			})
	})
	public void kefu() throws IOException
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			String signature = request.getParameter("signature");
			String timestamp = request.getParameter("timestamp");
			String nonce= request.getParameter("nonce");
			String echostr=request.getParameter("echostr");
			PrintWriter out=response.getWriter();
			System.out.println(signature+ signature);
			System.out.println(timestamp+ timestamp);
			System.out.println(nonce+ nonce);
			System.out.println(echostr+ echostr);
			out.print(echostr);
			//验证请求确认成功原样返回echostr参数内容，则接入生效，成为开发者成功，否则
//			if(ValidationUtil.chechSignature(signature, timestamp, nonce))
//			{
//			out.print(echostr);
//			}
			//out.close();
			//return echostr;
		}

	
	/*****************************************************
	 * wx.login接口   接收code、companyAccountId
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 *****************************************************/
	@Action(value="login", results= {
			@Result(name="success", type="json", params= {
					"root","dataMap"
			})
	})
	public String login() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException
	{		 
		System.out.println("========login中的code========="+code);
		System.out.println("========login中的SCcode========="+SCcode);
		CompanyAccount account = companyService.getComAccountById(SCcode);
		System.out.println(account);
        SessionKey sessionkey  = WxMiniUtil.getSessionKey(code,account.getWxAppID(),account.getWxAppSecret());
		//033fIgDm048uMk1BuhDm0DRfDm0fIgD0
        System.out.println("======sessionkey========"+sessionkey);
		SystemUser user = systemUserBLL.getUserByOpenId(sessionkey.getOpenId());
		if(user!=null)
		{
			System.out.println("========user!=null=========");
			user.setSessionKey(sessionkey.getSession_key());
			user.setUpdateTimes(new Date());
			
		}
		else
		{
			System.out.println("========user=null=========");
			user = new SystemUser();
			user.setUserId(UUID.randomUUID().toString());
			user.setOpenId(sessionkey.getOpenId());
			user.setSessionKey(sessionkey.getSession_key());
			//user.setUnionid(sessionkey.getUnionid());
			user.setUpdateTimes(new Date());
			user.setCreateTimes(new Date());
			user.setCompanyAccountId(account.getCompanyAccountId());
			systemUserBLL.saveSystemUser(user);
		}
		
//		EncrypDES de1 = new EncrypDES();  
//        String msg =user.getOpenId()+"@@@!!!!@@@"+sessionkey.getSession_key();  
//        String encontent = de1.encode(msg);
//		//对logs进行一定的加密，UUIDD 作为key openId和sessionkey 作为value ，openId跟sessionkey以@@@!!!!@@@隔开
//		System.out.println("========login中的logs========="+encontent);
        dataMap.put("logs", user.getUserId());
		return SUCCESS;
	}
	
	/*****************************************************
	 * wx.getUserInfo接口   接收iv encryptedData 以及前端加密的logs
	 * 接收公司帐号ID SCcode
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 * 
	 *****************************************************/
	@Action(value="getUserInfo", results= {
			@Result(name="success", type="json", params= {
					"root","dataMap"
			})
	})
	public String getUserInfo() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException 
	{
		if(StringUtils.isNotEmpty(logs)&&logs.length()!=2)
		{
			SystemUser user = systemUserBLL.getSystemUser(logs);
			if(user!=null)
			{
				String result = null;
				
				try {
					result = AesCbcUtil.decrypt(encryptedData, user.getSessionKey(), iv, "UTF-8");
					 JSONObject json = JSONObject.fromObject(result);
					 	 user.setCompanyAccountId(SCcode);
						 user.setAvatarUrl(json.getString("avatarUrl"));
						 user.setCity(json.getString("city"));
						 user.setCountry(json.getString("country"));
						 user.setGender(json.getString("gender"));
						 user.setLanguage(json.getString("language"));
						 user.setNickName(URLEncoder.encode(json.getString("nickName"), "utf-8"));
						 user.setProvince(json.getString("province"));
						 user.setUpdateTimes(new Date());
						 systemUserBLL.updateSystemUser(user);
						 dataMap.put("user",user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
			else
			{
				dataMap.put("type", "login");
			}

		}
		else
		{
			dataMap.put("type", "login");
		}
       return SUCCESS;
	}
	/*****************************************************
	 * wx支付接口   需要接收logs
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidKeyException 
	 * 
	 *****************************************************/
	@Action(value="wxPay", results= {
			@Result(name="success", type="json", params= {
					"root","payMap"
			})
	})
	public String wxPay() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
		if(StringUtils.isNotEmpty(logs)&&logs.length()!=2)
		{
		EncrypDES de1 = new EncrypDES();  
        String data = de1.decode(logs);
		HttpServletRequest request = ServletActionContext.getRequest();
		WxPlatform wxPlatform = wxPlatformBLL.getWxPlatformByAppId(ConstantsUtil.PUBLIC_APP_ID);
		String body = "这是一个测试的支付页面" ;//页面标题
		String out_trade_no = Calendar.getInstance().getTimeInMillis()+"";//唯一的订单号，只能用一次
		double total = 0.01;//单位是元，现在是1.99元
		String notify_url = ConstantsUtil.TENPAY_NOTIFY_URL;//回调页面，需要在商户号上面同步设置
		String openid = data.split("@@@!!!!@@@")[0];
		String trade_type = "JSAPI";
		payMap = WxMiniUtil.unifiedOrder(request, wxPlatform.getJsapi_ticket(), body, out_trade_no, total, trade_type, notify_url, openid);
		
		}
		return SUCCESS;
	}
	
	/** 
	 * 微信支付回调方法 
	 * @throws Exception 
	 */ 
	@Action(value="toPay", results= {
			@Result(name="success", type="json", params= {
					"root","dataMap"
			})
	})
	public void toPay() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		System.out.println("支付回调方法开始！");  
		weixin_notify(request, response);  
		System.out.println("支付回调方法结束！"); 
	}
	/** 
	 * 微信支付回调方法 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */  
	   public void weixin_notify(HttpServletRequest request,HttpServletResponse response) throws Exception{  
	      
	    //读取参数  
	    InputStream inputStream ;  
	    StringBuffer sb = new StringBuffer();  
	    inputStream = request.getInputStream();  
	    String s ;  
	    BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
	    while ((s = in.readLine()) != null){  
	        sb.append(s);  
	    }  
	    in.close();  
	    inputStream.close();  
	  
	    //解析xml成map  
	    Map<String, String> m = new HashMap<String, String>();  
	    m = XMLUtil.doXMLParse(sb.toString());  
	      
	    //过滤空 设置 TreeMap  
	    SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();        
	    Iterator it = m.keySet().iterator();  
	    while (it.hasNext()) {  
	        String parameter = (String) it.next();  
	        String parameterValue = m.get(parameter);  
	          
	        String v = "";  
	        if(null != parameterValue) {  
	            v = parameterValue.trim();  
	        }  
	        packageParams.put(parameter, v);  
	    }  
	      
	    // 账号信息  
	       String key = ConstantsUtil.KEY; // key  
	       String out_trade_no = (String)packageParams.get("out_trade_no");  
	       //logger.info(packageParams);  
	    //判断签名是否正确  
	    if(PayCommonUtil.isTenpaySign("UTF-8", packageParams,key)) {  
	        //------------------------------  
	        //处理业务开始  
	        //------------------------------  
	        String resXml = "";  
	        if("SUCCESS".equals((String)packageParams.get("result_code"))){  
	            // 这里是支付成功  
	            //////////执行自己的业务逻辑////////////////  
	        	System.out.println("//////////执行自己的业务逻辑////////////////"+out_trade_no);
	        	 // 根据订单号实现逻辑              
	        	
	            String mch_id = (String)packageParams.get("mch_id");  
	            String openid = (String)packageParams.get("openid");  
	            String is_subscribe = (String)packageParams.get("is_subscribe");  
	              
	            String bank_type = (String)packageParams.get("bank_type");  
	            String total_fee = (String)packageParams.get("total_fee");  
	            String transaction_id = (String)packageParams.get("transaction_id");  
	              
	              
	            //成功回调后需要处理预生成订单的状态和一些支付信息  
	        } else {  
	            System.out.println("支付失败,错误信息：" + packageParams.get("err_code")+  
	                                "-----订单号：：："+out_trade_no+"*******支付失败时间：：：："  
	                    +new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));  
	           
	            String err_code = (String)packageParams.get("err_code");  
	      
	             resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
	                     + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
	        
	        }  
	        //------------------------------  
	        //处理业务完毕  
	        //------------------------------  
	        BufferedOutputStream out = new BufferedOutputStream(  
	                response.getOutputStream());  
	        out.write(resXml.getBytes());  
	        out.flush();  
	        out.close();  
	    } else{  
	        System.out.println("通知签名验证失败---时间::::"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));  
	    }  
	   }
	
	/*****************************************************
	 * wx.uploadFile上传图片文件接口   需要接收logs判断session
	 * 前台接收file  文件名为 Wxfile
	 * @throws Exception 
	 * 
	 *****************************************************/
	@Action(value="uploadFile", results= {
			@Result(name="success", type="json", params= {
					"root","dataMap"
			})
	})
	public String uploadFile() throws Exception 
	{
		if(StringUtils.isNotEmpty(logs)&&logs.length()!=2)
		{
		String serverRealPath = ServletActionContext
                .getServletContext().getRealPath(
                        "/");
		String rootPath = "/upload/";
		if(WxMiniUtil.uploadPicture(serverRealPath+rootPath, Wxfile, "123.jpg"))
		{
			dataMap.put("data","error" );
		}
		dataMap.put("data",hosturl+"/"+rootPath+"123.jpg" );
		}
		else
		{
			dataMap.put("data","login" );
		}
		return SUCCESS;
	}
	
		
	public SystemUserBLL getSystemUserBLL() {
		return systemUserBLL;
	}
	public void setSystemUserBLL(SystemUserBLL systemUserBLL) {
		this.systemUserBLL = systemUserBLL;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	public String getSCcode() {
		return SCcode;
	}


	public void setSCcode(String sCcode) {
		SCcode = sCcode;
	}


	public WxPlatformBLL getWxPlatformBLL() {
		return wxPlatformBLL;
	}


	public CompanyService getCompanyService() {
		return companyService;
	}


	public void setWxPlatformBLL(WxPlatformBLL wxPlatformBLL) {
		this.wxPlatformBLL = wxPlatformBLL;
	}


	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}


	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}


	public File getWxfile() {
		return Wxfile;
	}


	public void setWxfile(File wxfile) {
		Wxfile = wxfile;
	}


	public Map<String, String> getPayMap() {
		return payMap;
	}


	public void setPayMap(Map<String, String> payMap) {
		this.payMap = payMap;
	}
	
	
	
	
	
	
	
}


