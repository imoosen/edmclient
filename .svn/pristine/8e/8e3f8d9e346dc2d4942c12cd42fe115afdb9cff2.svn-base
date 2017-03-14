package com.fiveone.edm.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 向邮件服务器提交认证信息
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月3日 下午5:55:45
 * @version: 1.0
 * @since: JDK1.7
 */
public class EmailAuthenticator extends Authenticator  {

	//用户名（登录邮箱）
    private String username;
    
    //密码
    private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	//默认
	public EmailAuthenticator() {
		super();
	}
    
	/**
	 * 邮件服务器登录验证
	 * @param username
	 * @param password
	 */
	public EmailAuthenticator(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	/**
	 * 邮件服务器密码验证
	 */
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
	    return new PasswordAuthentication(username, password);
    }
}
