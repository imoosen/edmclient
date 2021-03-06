package com.fiveone.edm.service;

import java.util.List;

import com.fiveone.edm.database.entity.EmailProject;

/**
 * 发送邮件服务层
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月3日 下午6:59:56
 * @version: 1.0
 * @since: JDK1.7
 */
public interface ISendEmailService {

	/**
	 * 单个发邮件
	 * @param recipient
	 * @param emailProject
	 * @param stmp
	 * @param content
	 * @throws Exception
	 */
	public void sendEmail(String recipient, EmailProject emailProject,String stmp, String content,String subject) throws Exception ;
	
	/**
	 * 群发邮件
	 * @param recipients
	 * @param emailProject
	 * @throws Exception
	 */
	public void sendEmail(List<String> recipients, EmailProject emailProject) throws Exception;
}
