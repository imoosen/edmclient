package com.fiveone.edm.service.impl;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fiveone.edm.database.entity.EmailProject;
import com.fiveone.edm.email.SendEmail;
import com.fiveone.edm.service.ISendEmailService;

/**
 * 发送邮件服务层实现类
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月3日 下午7:04:07
 * @version: 1.0
 * @since: JDK1.7
 */
@Service
@Repository("sendEmailService")
public class SendEmailServiceImpl implements ISendEmailService {
	
	private static final Logger log = Logger.getLogger(SendEmailServiceImpl.class);
	
	/**
	 * 单个发邮件
	 * @param recipient
	 * @param emailProject
	 * @param stmp
	 * @param content
	 * @throws Exception
	 */
	@Override
	public void sendEmail(String recipient, EmailProject emailProject,String stmp, String content,String subject) throws Exception {
		/**
		 * 初始化 
		 * 通过邮箱项目来决定谁来发送   
		 * 验证用户名和密码
		 */
		SendEmail sendEmail = new SendEmail(emailProject.getEmailSender().getEmailSenderAddress(), emailProject.getEmailSender().getPassword(),stmp);
		//通过邮箱项目计划来决定发送什么主题和内容
//		String subject = emailProject.getEmailContent().getEmailTitle();
//		String[] str = subject.split(",");
//		if (str.length > 1) {
//			Random ran = new Random();
//			//随机去一个邮件标题
//			subject = str[ran.nextInt(str.length)];
//		}else {
//			subject = str[0];
//		}
		sendEmail.sendEmail(recipient, subject, content);
	}

	/**
	 * 群发邮件
	 * @param recipients
	 * @param emailProject
	 * @throws Exception
	 */
	@Override
	public void sendEmail(List<String> recipients, EmailProject emailProject) throws Exception {
		/**
		 * 初始化 
		 * 通过邮箱项目来决定谁来发送   
		 * 验证用户名和密码
		 */
		SendEmail sendEmail = new SendEmail(emailProject.getEmailSender().getEmailSenderAddress(), emailProject.getEmailSender().getPassword(),"");
		//通过邮箱项目计划来决定发送什么主题和内容
		String subject = emailProject.getEmailContent().getEmailTitle();
		String[] str = subject.split(",");
		if (str.length > 1) {
			Random ran = new Random();
			//随机去一个邮件标题
			subject = str[ran.nextInt(ran.nextInt(str.length))];
		}else {
			subject = str[0];
		}
		String content = emailProject.getEmailContent().getContent();
		sendEmail.sendEmail(recipients, subject, content);
	}
	
/*	public static void main(String[] args) {
		Random ran = new Random();
		for (int i = 0; i < 10; i++) {
			System.out.println(ran.nextInt(5));
		}
		
	}
	*/
}
