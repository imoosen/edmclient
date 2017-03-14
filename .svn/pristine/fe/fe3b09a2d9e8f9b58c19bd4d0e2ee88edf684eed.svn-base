package com.fiveone.edm.controller;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiveone.edm.common.controller.BasePageController;
import com.fiveone.edm.database.entity.EmailContent;
import com.fiveone.edm.service.IEmailContentService;

/**
 * 邮件内容信息控制器
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月6日 下午8:34:30
 * @version: 1.0
 * @since: JDK1.7
 */
@Controller
@RequestMapping("/emailContent")
@Scope("prototype")
public class EmailContentController extends BasePageController {
	
	private static Logger log = Logger.getLogger(EmailContentController.class);

	@Autowired
	private IEmailContentService emailContentService;
	
	private String info;
	
	/**
	 * 添加邮件内容信息
	 * @return
	 */
	@RequestMapping("/add")
	public ModelAndView addEmailContent() {
		try {
			EmailContent emailContent = new EmailContent();
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			if(title != null) {
				emailContent.setEmailTitle(title);
			}
			if(content != null) {
				emailContent.setContent(content);
			}
			emailContent.setContentIsValid(1);
			emailContent.setCreateDate(new Timestamp(System.currentTimeMillis()));
			emailContent.setIsSend(1);
			int row = emailContentService.add(emailContent);
			if(row > 0) {
				info = "添加成功！";
			} else {
				info = "添加失败！";
			}
			mav.setViewName("addEmailContent");
			mav.addObject("info", info);
		} catch(Exception e) {
			log.error(e);
		}
		return mav;
	}
	
	/**
	 * 查询所有邮件内容信息
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView queryEmailContent() {
		List<EmailContent> ecList = emailContentService.queryAll();
		mav.setViewName("queryEmailContent");
		mav.addObject("ecList", ecList);
		return mav;
	}
	
	/**
	 * 根据id查询邮件内容信息
	 * @return
	 */
	@RequestMapping("/queryById")
	public ModelAndView queryEmailContentById() {
		String id = request.getParameter("id");
		EmailContent  emailContent = emailContentService.queryById(Integer.parseInt(id));
		mav.setViewName("updateEmailContent");
		mav.addObject("emailContent", emailContent);
		return mav;
	}
	
	/**
	 * 修改邮件内容信息
	 * @return
	 */
	@RequestMapping("/update")
	public ModelAndView updateEmailContent() {
		try {
			EmailContent emailContent = new EmailContent();
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String isSend = request.getParameter("isSend");
			String updateDate = request.getParameter("updateDate");
			String isValid = request.getParameter("isValid");
			if(id != null) {
				emailContent.setId(Integer.parseInt(id));
			}
			if(title != null) {
				emailContent.setEmailTitle(title);
			}
			if(content != null) {
				emailContent.setContent(content);
			}
			if(isSend != null) {
				emailContent.setIsSend(Integer.parseInt(isSend));
			}
			if(updateDate != null) {
				emailContent.setUpdateDate(Timestamp.valueOf(updateDate));
			}
			if(isValid != null) {
				emailContent.setContentIsValid(Integer.parseInt(isValid));
			}
			int row = emailContentService.update(emailContent);
			if(row > 0) {
				info = "修改成功！";
			} else {
				info = "修改失败！";
			}
		} catch(Exception e) {
			log.error(e);
		}
		return new ModelAndView("redirect:/emailContent/list.do");
	}
	
	/**
	 * 删除邮件内容
	 * @return
	 */
	@RequestMapping("/delete")
	public ModelAndView delete() {
		try {
			String id = request.getParameter("id");
			int row = emailContentService.deleteById(Integer.parseInt(id));
			if(row > 0) {
				info = "删除成功！";
			} else {
				info = "删除失败！";
			}
		} catch (Exception e) {
			log.error(e);
		}
		return new ModelAndView("forward:/emailContent/list.do");
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
