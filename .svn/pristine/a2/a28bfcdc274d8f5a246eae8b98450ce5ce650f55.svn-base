package com.fiveone.edm.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiveone.edm.common.controller.BasePageController;
import com.fiveone.edm.database.entity.EmailState;
import com.fiveone.edm.service.IEmailStateService;

/**
 * 邮箱状态信息控制层
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月30日 下午1:54:33
 * @version: 1.0
 * @since: JDK1.7
 */
@Controller
@RequestMapping("/emailState")
@Scope("prototype")
public class EmailStateController extends BasePageController {
	
	private static Logger log = Logger.getLogger(EmailStateController.class);

	@Autowired
	private IEmailStateService emailStateService;
	
	private String info;
	
	@RequestMapping("/list")
	public ModelAndView queryAllEmailState() {
		try {
			List<EmailState> esList = emailStateService.queryAll();
			mav.setViewName("/queryEmailState");
			mav.addObject("esList", esList);
		} catch (Exception e) {
			log.error(e);
		}
		return mav;
	}
		
	@RequestMapping("/add")
	public ModelAndView addEmailState() {
		try {
			EmailState emailState = new EmailState();
			String stateNo = request.getParameter("stateNo");
			String stateContent = request.getParameter("stateContent");
			if(stateNo != null) {
				emailState.setStateNo(Integer.parseInt(stateNo));
			}
			if(stateContent != null) {
				emailState.setStateContent(stateContent);
			}
			int row = emailStateService.add(emailState);
			if(row > 0) {
				info = "添加成功！";
			} else {
				info = "添加失败！";
			}
			mav.setViewName("addEmailState");
			mav.addObject("info", info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}
	
}
