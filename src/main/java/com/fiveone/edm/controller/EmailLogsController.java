package com.fiveone.edm.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fiveone.edm.common.controller.BasePageController;
import com.fiveone.edm.database.entity.Email;
import com.fiveone.edm.database.entity.EmailLogs;
import com.fiveone.edm.database.entity.EmailState;
import com.fiveone.edm.service.IEmailLogsService;
import com.fiveone.edm.service.IEmailService;
import com.fiveone.edm.service.IEmailStateService;
import com.fiveone.edm.service.IReceiveEmailService;

/**
 * 邮箱日志控制类&接收邮件控制类
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月12日 下午7:40:55
 * @version: 1.0
 * @since: JDK1.7
 */
@Controller
@Scope("prototype")
public class EmailLogsController extends BasePageController {
	
	private static Logger logger = Logger.getLogger(EmailLogsController.class); 

	@Autowired
	private IEmailLogsService emailLogsService;
	
	@Autowired
	private IEmailService emailService;
	
	@Autowired
	private IReceiveEmailService receiveEmailService;
	
	@Autowired
	private IEmailStateService emailStateService;
	
	/**
	 * 收取邮件信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/receive", method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	public String ReceiveEmail() {
		try {
			String no = request.getParameter("no");
			String host = request.getParameter("host");
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			EmailLogs emailLogs = new EmailLogs();
			List<Map<String,String>> list = receiveEmailService.receiveEmail(host, user, password);
			if(list != null) {
				for (Map<String, String> map : list) {
					String emailAddress = map.get("email");
					if (StringUtils.isBlank(emailAddress)) {
						logger.error("解析内容为null" + "邮箱不存在！");
						continue;	
					}
					List<Email> email = emailService.queryByEmailAddress(emailAddress,no);
					if(email == null) {
						logger.error(email + "邮箱不存在！");
						continue;				
					}
					String code = map.get("code");
					String msg = map.get("msg");
					if(StringUtils.isNotBlank(code)){
						List<EmailState> emailStates = emailStateService.queryByEmailStateNo(Integer.parseInt(code));
						int findFlag = 0;
						for(int i =0; i < emailStates.size(); i++){
							EmailState emailState = emailStates.get(i);
							String dbmsg = emailState.getStateContent().toLowerCase();
							msg = msg.toLowerCase();
							if(msg.indexOf(dbmsg) > -1){
								findFlag = 1;
								for (Email emails : email) {
									emailLogs.setEmailId(emails.getId());
									emailLogs.setMsgSend(msg);
									emailLogs.setStateId(emailState.getId());
									emailLogs.setUpdateDate(new Date());
									emailLogsService.updateByEmailId(emailLogs,no);
								}
								break;
							}
						}
						if(findFlag == 0){
							for (Email emails : email) {
								emailLogs.setEmailId(emails.getId());
								emailLogs.setMsgSend(msg);
								emailLogs.setStateId(Integer.parseInt(code));
								emailLogs.setUpdateDate(new Date());
								emailLogsService.updateByEmailId(emailLogs,no);
							}
						}
						logger.error("收取邮件 和 操作数据库执行完毕！。。。。。。。。。");
					}
				}   
			}
			return operationResult(true, "收取完成");
		} catch(Exception e) {
			logger.error("收取邮件信息：",e);
			e.printStackTrace();
			return operationResult(false, "执行出错");
		}		
	}
	
	/**
	 * 统计某个邮件项目每分钟邮件发送量
	 * @param table  邮箱日志表名
	 * @return
	 */
	@RequestMapping("/queryCountByMinute")
	public ModelAndView queryCountByMinute() {
		try {
			String table = request.getParameter("table");
			int countMinute = emailLogsService.queryCountByMinute(table);
			mav.setViewName("queryCountData");
			mav.addObject("countMinute", countMinute);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * 统计某个邮件项目每小时邮件发送量
	 * @param table	邮箱日志表名
	 * @return
	 */
	@RequestMapping("/queryCountByHour")
	public ModelAndView queryCountByHour() {
		try {
			String table = request.getParameter("table");
			int countHour = emailLogsService.queryCountByHour(table);
			mav.setViewName("queryCountData");
			mav.addObject("countHour", countHour);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * 统计某个邮件项目当天邮件发送量
	 * @param table	邮箱日志表名
	 * @return
	 */
	@RequestMapping("/queryCountByDay")
	public ModelAndView queryCountByDay(){
		try {
			String table = request.getParameter("table");
			int countDay = emailLogsService.queryCountByDay(table);
			mav.setViewName("queryCountData");
			mav.addObject("countDay", countDay);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}

}
