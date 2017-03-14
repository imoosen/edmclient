package com.fiveone.edm.service;

import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fiveone.edm.database.entity.EmailState;

/**
 * 测试邮箱状态信息
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月28日下午5:18:30
 * @version: 1.0
 * @since: JDK1.7
 */
/*@Transactional  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  
@RunWith(value=SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:spring/applicationContext.xml"}) */
public class EmailStateTest extends TestCase {
	/*
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(EmailStateTest.class); 
	
	@Autowired 
	private IEmailStateDao emailStateDao;
	
	@Before
    public void setup() {
		System.out.println("before Test");
    }
    
	*//**
	 * 测试根据id查询邮箱状态
	 *//*
	@Test
    public void testQueryById() {
		System.out.println("测试根据id查询邮箱状态...");
		IEmailStateService emailStateService = loadXml();
		EmailState es = emailStateService.queryById(1);
		System.out.println(es.getStateNo());
		System.out.println(es.getStateContent());
	}
	
	*//**
	 * 测试查询所有
	 *//*
	@Test
    public void testQueryAll() {
		System.out.println("测试查询所有...");
		IEmailStateService emailStateService = loadXml();
		List<EmailState> esList = emailStateService.queryAll();
		for (EmailState emailState : esList) {
			System.out.println(emailState.getStateNo());
			System.out.println(emailState.getStateContent());
			System.out.println("=======================");
		}
	}
	
	*//**
	 * 测试分页查询所有
	 *//*
	@Test
	public void testQueryByPage() {
		System.out.println("测试分页查询所有...");
		IEmailStateService emailStateService = loadXml();
		List<EmailState> esList = emailStateService.queryAllByPage();
		for (EmailState emailState : esList) {
			System.out.println(emailState.getStateNo());
			System.out.println(emailState.getStateContent());
			System.out.println("=======================");
		}
	 }
	
	*//**
	 * 测试查询总数
	 *//*
	@Test
	public void testQueryTatolCount() {
		System.out.println("测试查询总数...");
		IEmailStateService emailStateService = loadXml();
		int count = emailStateService.queryTotalCount();
		System.out.println(count);
	 }
	
	*//**
	 * 测试删除
	 *//*
	@Test
	public void testDelete() {
		System.out.println("测试删除...");
		IEmailStateService emailStateService = loadXml();
		int row = emailStateService.deleteById(10);
		System.out.println(row);
	 }
    
	*//**
	 * 测试插入邮箱状态信息
	 *//*
	@Test
    public void testInsert() {
		System.out.println("测试插入邮箱状态信息...");
		IEmailStateService emailStateService = loadXml();
		EmailState es = new EmailState();
		es.setStateNo(452);
		es.setStateContent("Requested action not taken: insufficient system storage! 要求动作无法执行：系统空间不足。邮件保留在本地，可能会尝试重新投递。通常这种情况发生在邮箱限额满!");
		int row = emailStateService.add(es);
		System.out.println(row);
		Assert.assertNotNull(true);  
    } 
	
	*//**
	 * 测试修改邮箱状态信息
	 *//*
	@Test
    public void testUpdate() {
		System.out.println("测试修改邮箱状态信息...");
		IEmailStateService emailStateService = loadXml();
		EmailState es = new EmailState();
		es.setId(2);
		es.setStateNo(214);
		es.setStateContent("Help message! 显示系统帮助，通常用于显示非标准命令的帮助!");
		int row = emailStateService.update(es);
		System.out.println(row);
		Assert.assertNotNull(true);  
    }
	
	@SuppressWarnings("resource")
	private IEmailStateService loadXml() {
		String paths[] = {"classpath:spring/applicationContext-dao.xml"};
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		IEmailStateService emailStateService = (IEmailStateService) ctx.getBean("emailStateService");
		return emailStateService;
	}*/

}
