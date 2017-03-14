package com.fiveone.edm.email;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * 使用javamail来发送电子邮件
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月3日 下午3:20:15
 * @version: 1.0
 * @since: JDK1.7
 */
public class SendEmail {
	
	private static final Logger log = Logger.getLogger(SendEmail.class);
	//邮件发送协议 
    private static final String MAIL_SEND_PROTOCOL = "smtp"; 

	//SMTP邮件服务器，发送邮件的服务器的IP(或主机地址)
	//private static String MAIL_SMTP_HOST = "smtp.163.com";

	//SMTP邮件服务器默认端口 
    private static final String MAIL_SMTP_PORT = "25"; 
    
    //是否需要身份验证 
    private static final String MAIL_SMTP_AUTH = "true"; 
    
    //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
    private static final String IS_ENABLED_DEBUG_MOD = "true"; 
    
    //邮件服务器登录验证
  	private transient EmailAuthenticator emailAuthenticator;
  	
  	//邮箱session
  	private transient Session session;
    
    //初始化连接邮件服务器的会话信息 
    private static Properties props = null; 

	/**
	 * 初始化邮件发送器
	 * 验证用户名和密码
	 * 创建实例对象session
	 * @param username
	 * @param password
	 */
	public SendEmail(final String username,final String password,String smtp) {
		props = new Properties(); 
        props.setProperty("mail.transport.protocol", MAIL_SEND_PROTOCOL); 
        props.setProperty("mail.smtp.host", smtp); 
        props.setProperty("mail.smtp.port", MAIL_SMTP_PORT); 
        props.setProperty("mail.smtp.auth", MAIL_SMTP_AUTH); 
        props.setProperty("mail.debug",IS_ENABLED_DEBUG_MOD); 
		
		log.error("OUT:"+username+"__"+password+"__域名为："+smtp);
		//验证用户名和密码
		emailAuthenticator = new EmailAuthenticator(username, password);
		//创建实例对象session
		session = Session.getDefaultInstance(props,emailAuthenticator);
		
	}
	
	/**
	 * 单个发邮件
	 * @param recipient
	 * @param subject
	 * @param content
	 * @throws Exception
	 */
	public void sendEmail(String recipient, String subject, Object content)throws Exception {
		//创建MimeMessage实例对象 
		final MimeMessage message = new MimeMessage(session);
		//设置发件人
		message.setFrom(new InternetAddress(emailAuthenticator.getUsername()));
		//设置收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
		//设置邮件主题
		message.setSubject(subject, "utf-8");
		//设置邮件内容
		message.setContent(content.toString(), "text/html;charset=utf-8");
		/*String [] type = {"base64","quoted-printable","8bit"};
		message.setHeader("Content-Transfer-Encoding", type[new Random().nextInt(3)%(3-1+1)]);*/
		//设置发送时间
		message.setSentDate(new Date());
		//保存并生成最终的邮件内容 
		message.saveChanges();
		//发送邮件
		Transport.send(message);
		//将内容置空，等待JVM自动回收
		content = null;
	}
	
	/**
	 * 群发邮件
	 * @param recipients
	 * @param subject
	 * @param content
	 * @throws Exception
	 */
	public void sendEmail(List<String> recipients, String subject, Object content)throws Exception {
		//创建MimeMessage实例对象 
		final MimeMessage message = new MimeMessage(session);
		//设置发件人
		message.setFrom(new InternetAddress(emailAuthenticator.getUsername()));
		//设置收件人们
		final int num = recipients.size();
		InternetAddress[] addresses = new InternetAddress[num];
		for (int i = 0; i < num; i++) {
			addresses[i] = new InternetAddress(recipients.get(i));
		}
		message.setRecipients(Message.RecipientType.TO, addresses);
		//设置邮件主题
		message.setSubject(subject, "utf-8");
		//设置邮件内容
		message.setContent(content.toString(),  "text/html;charset=utf-8");
		//设置发送时间
		message.setSentDate(new Date());
		//保存并生成最终的邮件内容 
		message.saveChanges();
		//发送邮件
		Transport.send(message);
	}
	
}
