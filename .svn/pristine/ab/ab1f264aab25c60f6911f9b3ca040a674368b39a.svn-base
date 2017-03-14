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

import com.fiveone.edm.database.entity.Email;

/**
 * 测试邮箱信息
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月29日 上午10:18:04
 * @version: 1.0
 * @since: JDK1.7
 */
public class EmailTest extends TestCase {
	
	/*@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(EmailTest.class); 
	
	@Before
    public void setup() {
		System.out.println("before Test");
    }
    
	*//**
	 * 测试根据id查询
	 *//*
	@Test
    public void testQueryById() {
		System.out.println("测试根据id查询...");
		IEmailService emailService = loadXml();
		Email email = emailService.queryById(2);
		System.out.println(email.getEmailAddress());
		System.out.println(email.getCity());
	}
	
	*//**
	 * 测试根据邮箱类型查询
	 *//*
	@Test
    public void testQueryByEmailType() {
		System.out.println("测试根据邮箱类型查询...");
		IEmailService emailService = loadXml();
		List<Email> emailList = emailService.queryByEmailType("163.com");
		for (Email email : emailList) {
			System.out.println(email.getEmailAddress());
			System.out.println(email.getCity());
			System.out.println("===================");
		}
	}
	
	*//**
	 * 测试查询所有
	 *//*
	@Test
    public void testQueryAll() {
		System.out.println("测试查询所有...");
		IEmailService emailService = loadXml();
		List<Email> emailList = emailService.queryAll();
		for (Email email : emailList) {
			System.out.println(email.getEmailAddress());
			System.out.println(email.getEmailType());
			System.out.println("===================");
		}
	}
	
	*//**
	 * 测试分页查询所有
	 *//*
	@Test
	public void testQueryByPage() {
		System.out.println("测试分页查询所有...");
		IEmailService emailService = loadXml();
		List<Email> emailList = emailService.queryAllByPage(0, 2);
		for (Email email : emailList) {
			System.out.println(email.getEmailAddress());
			System.out.println(email.getEmailType());
			System.out.println("===================");
		}
	 }
	
	*//**
	 * 测试查询总数
	 *//*
	@Test
	public void testQueryTatolCount() {
		System.out.println("测试查询总数...");
		IEmailService emailService = loadXml();
		int count = emailService.queryTotalCount();
		System.out.println(count);
	 }
	
	*//**
	 * 测试删除
	 *//*
	@Test
	public void testDelete() {
		System.out.println("测试删除...");
		IEmailService emailService = loadXml();
		int row = emailService.deleteById(3);
		System.out.println(row);
		Assert.assertNotNull(true);
	 }
    
	*//**
	 * 测试插入邮箱信息
	 *//*
	@Test
    public void testInsert() {
		System.out.println("测试插入邮箱信息...");
		IEmailService emailService = loadXml();
    	Email email = new Email();
    	email.setEmailAddress("595971102@qq.com");
    	email.setEmailType("qq.com");
    	email.setCreateDate(new Timestamp(System.currentTimeMillis()));
    	email.setDataIsValid(1);
    	email.setCity("1");
    	email.setEmailIsSubscibe(1);
    	email.setEmailState(1);
    	email.setProvince("1");
    	email.setSource("1");
    	email.setUpdateDate(new Timestamp(System.currentTimeMillis()));
    	int row = emailService.add(email);
    	System.out.println(row);
    	Assert.assertNotNull(true);  
    } 
	
	*//**
	 * 测试修改邮箱信息
	 *//*
	@Test
    public void testUpdate() {
		System.out.println("测试修改邮箱信息...");
		IEmailService emailService = loadXml();
    	Email email = new Email();
    	email.setId(2);
    	email.setEmailAddress("374745367@163.com");
    	email.setEmailType("163.com");
    	email.setCreateDate(new Timestamp(System.currentTimeMillis()));
    	email.setDataIsValid(1);
    	email.setCity("北京");
    	email.setEmailIsSubscibe(1);
    	email.setEmailState(1);
    	email.setProvince("1");
    	email.setSource("1");
    	email.setUpdateDate(new Timestamp(System.currentTimeMillis()));
    	int row = emailService.update(email);
    	System.out.println(row);
    	Assert.assertNotNull(true);  
    }

	@SuppressWarnings("resource")
	private IEmailService loadXml() {
		String paths[] = {"classpath:spring/applicationContext-dao.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		IEmailService emailService = (IEmailService) ctx.getBean("emailService");
		return emailService;
	}
*/
}
