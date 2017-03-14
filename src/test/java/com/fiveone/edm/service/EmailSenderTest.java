package com.fiveone.edm.service;

import junit.framework.TestCase;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fiveone.edm.database.entity.EmailSender;

/**
 * 测试邮箱发件人信息
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月30日 下午1:47:37
 * @version: 1.0
 * @since: JDK1.7
 */
public class EmailSenderTest extends TestCase {
	
	/*@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(EmailSenderTest.class); 
	
	@Before
    public void setup() {
		System.out.println("before Test");
    }
    
	*//**
	 * 测试根据id查询邮箱内容
	 *//*
	@Test
    public void testQueryById() {
		System.out.println("测试根据id查询...");
		IEmailSenderService emailSenderService = loadXml();
		EmailSender emailSender = emailSenderService.queryById(1);
		System.out.println(emailSender.getEmailSenderAddress());
		System.out.println(emailSender.getPassword());
	}
	
	*//**
	 * 测试查询所有
	 *//*
	@Test
    public void testQueryAll() {
		System.out.println("测试查询所有...");
		IEmailSenderService emailSenderService = loadXml();
		List<EmailSender> esList = emailSenderService.queryAll();
		for (EmailSender emailSender : esList) {
			System.out.println(emailSender.getEmailSenderAddress());
			System.out.println(emailSender.getPassword());
			System.out.println("=================");
		}
	}
	
	*//**
	 * 测试分页查询所有
	 *//*
	@Test
	public void testQueryByPage() {
		System.out.println("测试分页查询所有...");
		IEmailSenderService emailSenderService = loadXml();
		List<EmailSender> esList = emailSenderService.queryAllByPage(0, 2);
		for (EmailSender emailSender : esList) {
			System.out.println(emailSender.getEmailSenderAddress());
			System.out.println(emailSender.getPassword());
			System.out.println("=================");
		}
	 }
	
	*//**
	 * 测试查询总数
	 *//*
	@Test
	public void testQueryTatolCount() {
		System.out.println("测试查询总数...");
		IEmailSenderService emailSenderService = loadXml();
		int count = emailSenderService.queryTotalCount();
		System.out.println(count);
	 }
	
	*//**
	 * 测试删除
	 *//*
	@Test
	public void testDelete() {
		System.out.println("测试删除...");
		IEmailSenderService emailSenderService = loadXml();
		int row = emailSenderService.deleteById(2);
		System.out.println(row);
	 }
    
	*//**
	 * 测试插入邮箱内容信息
	 *//*
	@Test
    public void testInsert() {
		System.out.println("测试插入邮箱内容信息...");
		IEmailSenderService emailSenderService = loadXml();
		EmailSender emailSender = new EmailSender();
		emailSender.setEmailSenderAddress("webmaster@51jrq1.vip");
		emailSender.setPassword("123456");
		emailSender.setCreateDate(new Timestamp(System.currentTimeMillis()));
		emailSender.setUpdateDate(null);
		emailSender.setEmailSenderState(1);
		int row = emailSenderService.add(emailSender);
		System.out.println(row);
		Assert.assertNotNull(true);  
    } 
	
	*//**
	 * 测试修改邮箱内容信息
	 *//*
	@Test
    public void testUpdate() {
		System.out.println("测试修改邮箱内容信息...");
		IEmailSenderService emailSenderService = loadXml();
		EmailSender emailSender = new EmailSender();
		emailSender.setId(1);
		emailSender.setEmailSenderAddress("webmaster@51jrq1.vip");
		emailSender.setPassword("admin");
		emailSender.setCreateDate(null);
		emailSender.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		emailSender.setEmailSenderState(1);
		int row = emailSenderService.update(emailSender);
		System.out.println(row);
		Assert.assertNotNull(true);  
    }

	  
	@SuppressWarnings("resource")
	private IEmailSenderService loadXml() {
		String paths[] = {"classpath:spring/applicationContext-dao.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		IEmailSenderService emailSenderService = (IEmailSenderService) ctx.getBean("emailSenderService");
		return emailSenderService;
	}*/
}
