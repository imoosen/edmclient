package com.fiveone.edm.service;

import java.sql.Timestamp;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fiveone.edm.database.entity.EmailProject;

/**
 * 测试邮箱项目信息
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月30日 下午1:42:24
 * @version: 1.0
 * @since: JDK1.7
 */
public class EmailProjectTest extends TestCase {
	
	/*@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(EmailProjectTest.class); 
	
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
		IEmailProjectService emailProjectSevice = loadXml();
		EmailProject emailProject = emailProjectSevice.queryById(2);
		System.out.println(emailProject.getProjectName());
		System.out.println(emailProject.getEmailSender().getEmailSenderAddress());
		System.out.println(emailProject.getEmailContent().getEmailTitle());
		System.out.println(emailProject.getEmailContent().getContent());
	}
	
	*//**
	 * 测试查询所有
	 *//*
	@Test
    public void testQueryAll() {
		System.out.println("测试查询所有...");
		IEmailProjectService emailProjectSevice = loadXml();
		List<EmailProject> epList = emailProjectSevice.queryAll();
		for (EmailProject emailProject : epList) {
			System.out.println(emailProject.getProjectName());
			System.out.println(emailProject.getEmailSender().getEmailSenderAddress());
			System.out.println(emailProject.getEmailContent().getEmailTitle());
			System.out.println(emailProject.getEmailContent().getContent());
			System.out.println("==========================");
		}
	}
	
	*//**
	 * 测试分页查询所有
	 *//*
	@Test
	public void testQueryByPage() {
		System.out.println("测试分页查询所有...");
		IEmailProjectService emailProjectSevice = loadXml();
		List<EmailProject> epList = emailProjectSevice.queryAllByPage(0, 2);
		for (EmailProject emailProject : epList) {
			System.out.println(emailProject.getProjectName());
			System.out.println(emailProject.getEmailSender().getEmailSenderAddress());
			System.out.println(emailProject.getEmailContent().getEmailTitle());
			System.out.println(emailProject.getEmailContent().getContent());
			System.out.println("==========================");
		}
	 }
	
	*//**
	 * 测试查询总数
	 *//*
	@Test
	public void testQueryTatolCount() {
		System.out.println("测试查询总数...");
		IEmailProjectService emailProjectSevice = loadXml();
		int count = emailProjectSevice.queryTotalCount();
		System.out.println(count);
	 }
	
	*//**
	 * 测试删除
	 *//*
	@Test
	public void testDelete() {
		System.out.println("测试删除...");
		IEmailProjectService emailProjectSevice = loadXml();
		int row = emailProjectSevice.deleteById(2);
		System.out.println(row);
	 }
    
	*//**
	 * 测试插入邮箱内容信息
	 *//*
	@Test
    public void testInsert() {
		System.out.println("测试插入邮箱内容信息...");
		IEmailProjectService emailProjectSevice = loadXml();
		EmailProject emailProject = new EmailProject();
		emailProject.setProjectName("邀张晓彤教授主讲招聘技巧培训");
		emailProject.setEmailSenderId(1);
		emailProject.setContentId(1);
		emailProject.setPlanSend(10000);
		emailProject.setSendNum(0);
		emailProject.setActiveFlag(1);
		emailProject.setCreateDate(new Timestamp(System.currentTimeMillis()));
		emailProject.setUpdateDate(null);
		emailProject.setProjectState(1);
		int row = emailProjectSevice.add(emailProject);
		System.out.println(row);
		Assert.assertNotNull(true);  
    } 
	
	*//**
	 * 测试修改邮箱内容信息
	 *//*
	@Test
    public void testUpdate() {
		System.out.println("测试修改邮箱内容信息...");
		IEmailProjectService emailProjectSevice = loadXml();
		EmailProject emailProject = new EmailProject();
		emailProject.setId(2);
		emailProject.setProjectName("邀张晓彤教授主讲招聘技巧培训");
		emailProject.setEmailSenderId(1);
		emailProject.setContentId(1);
		emailProject.setPlanSend(10000);
		emailProject.setSendNum(100);
		emailProject.setActiveFlag(1);
		emailProject.setCreateDate(null);
		emailProject.setUpdateDate(new Timestamp(System.currentTimeMillis()));
		emailProject.setProjectState(1);
		int row = emailProjectSevice.update(emailProject);
		System.out.println(row);
		Assert.assertNotNull(true);  
    }

	  
	@SuppressWarnings("resource")
	private IEmailProjectService loadXml() {
		String paths[] = {"classpath:spring/applicationContext-dao.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		IEmailProjectService emailProjectSevice = (IEmailProjectService) ctx.getBean("emailProjectService");
		return emailProjectSevice;
	}*/
}
