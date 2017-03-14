package com.fiveone.edm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fiveone.edm.email.ReceiveEmail;
import com.fiveone.edm.service.IReceiveEmailService;

/**
 * 收取邮箱信息服务层实现类
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月18日 下午1:38:17
 * @version: 1.0
 * @since: JDK1.7
 */
@Service
@Repository("receiveEmailService")
public class ReceiveEmailServiceImpl implements IReceiveEmailService {

	/**
	 * 收取邮件
	 * @param host		接收邮件的POP3服务器的IP(或主机地址)
	 * @param user		接收邮件的用户名
	 * @param password	接收邮件的密码
	 */
	@Override
	public List<Map<String,String>> receiveEmail(String host, String user, String password) {
		List<Map<String,String>> list = null;
		try {
			list = ReceiveEmail.receiveEmails(host, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
