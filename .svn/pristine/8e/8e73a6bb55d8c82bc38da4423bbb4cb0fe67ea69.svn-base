package com.fiveone.edm.controller;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fiveone.edm.common.controller.BasePageController;
import com.fiveone.edm.database.entity.Email;
import com.fiveone.edm.service.IEmailService;

/**
 * 邮箱信息控制层
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月29日 上午10:59:11
 * @version: 1.0
 * @since: JDK1.7
 */
@Controller
@RequestMapping("email")
@Scope("prototype")
public class EmailController extends BasePageController {
	
	private static Logger log = Logger.getLogger(EmailController.class);

	@Autowired
	private IEmailService emailService;
	
	private String info;
	
	@RequestMapping("add")
	public void addEmail() {
		Email email = new Email();
		int row = 0;
		String email_str = request.getParameter("email");
		String province = request.getParameter("provice");
		String city = request.getParameter("city");
		String source = request.getParameter("source");
		String addrs[]=email_str.split("@");
		if (addrs.length != 2) {
		  log.error("非法邮箱地址");
		} else {
			email.setEmailAddress(email_str);
			email.setEmailType(addrs[1]);
		}
		if(province != null) {
			email.setProvince(province);
		}
		if(city != null) {
			email.setCity(city);
		}
		if(source != null) {
			email.setSource(source);
		}
		email.setDataIsValid(1);
		email.setEmailState(1);
		email.setCreateDate(new Timestamp(System.currentTimeMillis()));
		row = emailService.add(email);
		if(row > 0) {
			info = "添加成功！";
		} else {
			info = "添加失败！";
		}
		//System.out.println(info);
	}
	
	/**
	 * 从文件中获取收件人邮箱地址
	 * @return
	 */
	/*private static Map<List<String>,List<String>> getEmailReadFile() {
		Map<List<String>,List<String>> map = new HashMap<List<String>,List<String>>();
		List<String> recipients = new ArrayList<String>();
		List<String> emailType = new ArrayList<String>();
		File file = new File("D:\\work\\emails.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String email = null;
		try {
			while ((email = reader.readLine()) != null) {
				recipients.add(email);
				String addrs[]=email.split("@");
				if (addrs.length != 2) {
				  System.out.println("非法邮箱地址");
				} else {
				  System.out.println(addrs[0]);//用户名
				  	System.out.println(addrs[1]);//域名				  
					emailType.add(addrs[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put(recipients, emailType);
		return map;
	}*/
	
	/**
	 * 从文件中获取收件人邮箱地址
	 * @return
	 */
	/*private static List<String> getRecipientsReadFile() {
		List<String> recipients = new ArrayList<String>();
		File file = new File("D:\\work\\emails.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String email = null;
		try {
			while ((email = reader.readLine()) != null) {
				recipients.add(email);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return recipients;
	}
	*/
	/**
	 * 获取收件人邮箱类型
	 * @return
	 */
	/*private static List<String> getEmailType() {
		List<String> emailType = new ArrayList<String>();
		File file = new File("D:\\work\\emails.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String email = null;
		try {
			while ((email = reader.readLine()) != null) {
				String addrs[]=email.split("@");
				if (addrs.length != 2) {
				  System.out.println("非法邮箱地址");
				} else {
				  System.out.println(addrs[0]);//用户名
				  System.out.println(addrs[1]);//域名
				  emailType.add(addrs[1]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return emailType;
	}*/

	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}
}
