package com.fiveone.edm.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fiveone.edm.common.controller.BasePageController;
import com.fiveone.edm.database.entity.EmailProject;
import com.fiveone.edm.service.IEmailProjectService;
import com.fiveone.edm.service.ISendEmailService;
import com.fiveone.edm.util.Contants;
import com.fiveone.edm.util.HtmlParserUtil;

/**
 * 发送邮件控制层
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月3日 下午7:16:45
 * @version: 1.0
 * @since: JDK1.7
 */
@Controller
@Scope("prototype")
public class SendEmailController extends BasePageController {
	
	private static Logger logger = Logger.getLogger(SendEmailController.class); 
	
	//发送邮件
	@Autowired
	private ISendEmailService sendEmailService;
	
	//邮件项目管理信息
	@Autowired
	private IEmailProjectService emailProjectService;
	
	private final static Map<String, EmailProject> PROJECT_POOL = new HashMap<String, EmailProject>();
	
	/**
	 * 单个发送邮件　　　
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="/send", method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String sendSingleEmail() {		
		try {
			String recipient = request.getParameter("email_address");//收件人邮箱地址
			String f_id = request.getParameter("f_id");//邮件地址数据库id
			String project_id = request.getParameter("pro_id");//项目id

			String subject = request.getParameter("subject");//邮件内容
			String content = request.getParameter("content");//邮件内容
			String STMP = request.getParameter("smtp");//邮件服务器域名
			String server_address = request.getParameter("server_address");
			String params = f_id + "#" + recipient + "#" +  project_id;

			String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
			if(!recipient.matches(regex)) {
				return operationResult(false, "邮箱地址格式错误");//邮箱地址格式错误
			} else {
				EmailProject emailProject = getProjectInfo(project_id);
				//替换后的邮件内容
				if (null==content) {
					 content = HtmlParserUtil.replaceAllLinkTag(emailProject.getEmailContent().getContent(), server_address, params);
				}
			    sendEmailService.sendEmail(recipient, emailProject, STMP,content,subject);
				return operationResult(true, "发送成功!");//发送成功
			}
		} catch (Exception e) {
			logger.error("请求发送邮件异常：" + e.getMessage(),e);
			return operationResult(false, "请求异常!");
		}
	}
	
	/**
	 * 克隆一个对象
	 * @param projectId
	 * @return
	 */
	private EmailProject getProjectInfo(String projectId) {
		if(PROJECT_POOL.containsKey(projectId)){
			return  PROJECT_POOL.get(projectId);
		}else{
			EmailProject emailProject = emailProjectService.queryById(Integer.parseInt(projectId));
			PROJECT_POOL.put(projectId,emailProject);
			return emailProject;
		}
	}	
	
	/**
	 * 缓存重新加载
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="/cacheReload", method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String cacheReload() {
		try {
			EmailProject emailProject = null;
			for(String key : PROJECT_POOL.keySet()) {
				emailProject = emailProjectService.queryById(Integer.parseInt(key));
				PROJECT_POOL.put(key, emailProject);
			}
			return operationResult(true, "刷新成功!");
		} catch(Exception e) {
			return operationResult(false, "刷新失败!");
		}		
	}

	/**
	 * 群发邮件
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="/sendMoreEmail", method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public ModelAndView sendMoreEmail() {
		/*String email = request.getParameter("email");*/
		//String ip = request.getParameter("ip");//由哪台服务器的哪个ip来绑定发送邮件
		String emailProjectId = request.getParameter("projectId");//项目id
		EmailProject emailProject = emailProjectService.queryById(Integer.parseInt(emailProjectId));
		//读取文件中的收件人邮箱地址
		List<String> recipients = getRecipientsReadFile();
		try {
			sendEmailService.sendEmail(recipients, emailProject);
			mav.addObject("code", Contants.API_SUCCESS);
			mav.addObject("msg", "发送成功！");
		} catch (Exception e) {
			logger.error(e);
			mav.addObject("code", Contants.API_ERROR);
			mav.addObject("msg", "请求出错！");
		}
		return mav;
	}
	
	/**
	 * 从文件中获取收件人邮箱地址
	 * @return
	 */
	private static List<String> getRecipientsReadFile() {
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
}
