package com.fiveone.edm.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.fiveone.edm.database.entity.EmailLogs;

import junit.framework.TestCase;

/**
 * 测试邮箱日志信息
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月29日 上午10:17:38
 * @version: 1.0
 * @since: JDK1.7
 */
public class EmailLogsTest extends TestCase {
	
	/*@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(EmailLogsTest.class); 
	
	@Before
    public void setup() {
		System.out.println("before Test");
    }
    
	*//**
	 * 测试根据id查询邮箱日志
	 *//*
	@Test
    public void testQueryById() {
		System.out.println("测试根据id查询邮箱日志...");
		IEmailLogsService emailLogsService = loadXml();
		EmailLogs el = emailLogsService.queryById(1);
		System.out.println(el.getEmailSender().getEmailSenderAddress());
		System.out.println(el.getEmail().getEmailAddress());
		System.out.println(el.getEmailState().getStateNo());
		System.out.println(el.getEmailState().getStateContent());
	}
	
	*//**
	 * 测试查询所有
	 *//*
	@Test
    public void testQueryAll() {
		System.out.println("测试查询所有...");
		IEmailLogsService emailLogsService = loadXml();
		List<EmailLogs> elList = emailLogsService.queryAll();
		for (EmailLogs emailLogs : elList) {
			System.out.println(emailLogs.getEmailSender().getEmailSenderAddress());
			System.out.println(emailLogs.getEmail().getEmailAddress());
			System.out.println(emailLogs.getEmailState().getStateNo());
			System.out.println(emailLogs.getEmailState().getStateContent());
			System.out.println("=====================");
		}
	}
	
	*//**
	 * 测试分页查询所有
	 *//*
	@Test
	public void testQueryByPage() {
		System.out.println("测试分页查询所有...");
		IEmailLogsService emailLogsService = loadXml();
		List<EmailLogs> elList = emailLogsService.queryAllByPage(0, 2);
		for (EmailLogs emailLogs : elList) {
			System.out.println(emailLogs.getEmailSender().getEmailSenderAddress());
			System.out.println(emailLogs.getEmail().getEmailAddress());
			System.out.println(emailLogs.getEmailState().getStateNo());
			System.out.println(emailLogs.getEmailState().getStateContent());
			System.out.println("=====================");
		}
	 }
	
	*//**
	 * 测试查询总数
	 *//*
	@Test
	public void testQueryTatolCount() {
		System.out.println("测试查询总数...");
		IEmailLogsService emailLogsService = loadXml();
		int count = emailLogsService.queryTotalCount();
		System.out.println(count);
	 }
	
	*//**
	 * 测试删除
	 *//*
	@Test
	public void testDelete() {
		System.out.println("测试删除...");
		IEmailLogsService emailLogsService = loadXml();
		int row = emailLogsService.deleteById(3);
		System.out.println(row);
	 }
    
	*//**
	 * 测试插入邮箱日志
	 *//*
	@Test
    public void testInsert() {
		System.out.println("测试插入邮箱日志...");
		IEmailLogsService emailLogsService = loadXml();
		EmailLogs el = new EmailLogs();
		el.setEmailId(2);
		el.setIsSend(1);
		el.setIsOpen(1);
		el.setIsRead(1);
		el.setMsgSend("已读");
		el.setMsgRead("已读");
		el.setSendDate(new Timestamp(System.currentTimeMillis()));
		el.setOpenDate(new Timestamp(System.currentTimeMillis()));
		el.setReadDate(new Timestamp(System.currentTimeMillis()));
		el.setServerId(2);
		el.setIp("192.168.1.17");
		el.setEmailSenderId(1);
		el.setArea("上海");
		el.setStateId(1);
		el.setCreateDate(new Timestamp(System.currentTimeMillis()));
		el.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		int row = emailLogsService.add(el);
		System.out.println(row);
		Assert.assertNotNull(true);  
    } 
	
	*//**
	 * 测试修改邮箱内容信息
	 *//*
	@Test
    public void testUpdate() {
		System.out.println("测试修改邮箱日志...");
		IEmailLogsService emailLogsService = loadXml();
		EmailLogs el = new EmailLogs();
		el.setId(2);
		el.setEmailId(2);
		el.setIsSend(1);
		el.setIsOpen(1);
		el.setIsRead(1);
		el.setMsgSend("未读");
		el.setMsgRead("未读");
		el.setReadDate(new Timestamp(System.currentTimeMillis()));
		el.setOpenDate(new Timestamp(System.currentTimeMillis()));
		el.setReadDate(new Timestamp(System.currentTimeMillis()));
		el.setServerId(2);
		el.setIp("192.168.1.17");
		el.setFromEmail("51jrq.vip@163.com");
		el.setArea("上海");
		el.setStateId(1);
		el.setCreateDate(new Timestamp(System.currentTimeMillis()));
		el.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		int row = emailLogsService.update(el);
		System.out.println(row);
		Assert.assertNotNull(true);  
    }

	  
	@SuppressWarnings("resource")
	private IEmailLogsService loadXml() {
		String paths[] = {"classpath:spring/applicationContext-dao.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		IEmailLogsService emailLogsService = (IEmailLogsService) ctx.getBean("emailLogsService");
		return emailLogsService;
	}*/
}
