package com.nature.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;



public class QQSendEmailUtil {
//>>>>>>要先加好jar包>>>>>>
//<dependency>
//    <groupId>javax.mail</groupId>
//    <artifactId>javax.mail-api</artifactId>
//    <version>1.5.6</version>
//</dependency>
//<dependency>
//    <groupId>com.sun.mail</groupId>
//    <artifactId>javax.mail</artifactId>
//    <version>1.5.6</version>
//</dependency>
//>>>>>>>>>>>>>>>>>>>>>>>>>
	
	   // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
//    public static String myEmailAccount = "292996030@qq.com";
//    public static String myEmailPassword = "ijlyrxtoesxzcaji";
   // public static String myEmailAccount = "	admin@www.imagegere.cn";
	public static String myEmailAccount = "891846581@qq.com";
    public static String myEmailPassword = "syczizzxvdltbbda";
    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String myEmailSMTPHost = "smtp.qq.com";

    // 收件人邮箱（替换为自己知道的有效邮箱）
    public static String receiveMailAccount = "1822948363@qq.com";
    
	public static void main(String args[]) throws Exception {
		//System.out.println("hello world!");
		String text = "  您好，您在我们公司购买的'企业展示小程序'服务就要到期了，请联系客服继续购买服务，逾期后小程序将暂停服务。<br/>公司电话：13610012468";
		QQSendEmailUtil.sendMail("1822948363@qq.com", "企业展示小程序到期通知", text);
	}
	
	public static void sendMail(String userName, String title,String text) throws Exception{


	    
	        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
	        Properties props = new Properties();                    // 参数配置
	        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
	        props.setProperty("mail.host", myEmailSMTPHost);        // 发件人的邮箱的 SMTP 服务器地址
	        props.setProperty("mail.smtp.auth", "true");            // 请求认证，参数名称与具体实现有关
	        props.setProperty("mail.smtp.port", "465");
	        // QQ邮箱需要添加这一段
	        props.setProperty("mail.smtp.socketFactory.port", "465");
	        props.setProperty("mail.smtp.starttls.enable","true");
	        MailSSLSocketFactory sf = new MailSSLSocketFactory();
	        sf.setTrustAllHosts(true);
	        props.put("mail.smtp.ssl.enable", "true");
	        props.put("mail.smtp.ssl.socketFactory", sf);
	        // -->  QQ 邮箱 SSL 认证结束。

	        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
	        Session session = Session.getDefaultInstance(props);
	        
	        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

	        // 3. 创建一封邮件
	        MimeMessage message = createMimeMessage(session, myEmailAccount, userName,title,text);

	        // 4. 根据 Session 获取邮件传输对象
	        Transport transport = session.getTransport();

	        // 5. 使用 邮箱账号 和 密码 连接邮件服务器
	        //    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
	        transport.connect(myEmailSMTPHost, myEmailAccount, myEmailPassword);

	        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
	        transport.sendMessage(message, message.getAllRecipients());

	        // 7. 关闭连接
	        transport.close();
	}
	    
	    /**
	     * 创建一封只包含文本的简单邮件
	     *
	     * @param session 和服务器交互的会话
	     * @param sendMail 发件人邮箱
	     * @param receiveMail 收件人邮箱
	     * @return
	     * @throws Exception
	     */
	    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String title,String text) throws Exception {
	        // 1. 创建一封邮件
	        MimeMessage message = new MimeMessage(session);
	        
	        // 2. From: 发件人
	        message.setFrom(new InternetAddress(sendMail, "奈尘科技", "UTF-8"));

	        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
	        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, receiveMail, "UTF-8"));
	        
//	        message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("382891310@qq.com", "USER_EE", "UTF-8"));

	        // 4. Subject: 邮件主题
	        message.setSubject(title, "UTF-8");
	       

	        // 5. Content: 邮件正文（可以使用html标签）
	        message.setContent(text, "text/html;charset=UTF-8;");
	       
	        // 6. 设置发件时间
	        message.setSentDate(new Date());

	        // 7. 保存设置
	        message.saveChanges();

	        return message;
	    }
}
