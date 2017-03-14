package com.fiveone.edm.service;

import java.util.List;

import com.fiveone.edm.database.entity.EmailSender;

/**
 * 邮箱发件人服务层
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月30日 下午1:04:30
 * @version: 1.0
 * @since: JDK1.7
 */
public interface IEmailSenderService {

	/**
	 * 根据id查询邮箱发件人
	 * @param id
	 * @return
	 */
	public EmailSender queryById(Integer id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<EmailSender> queryAll();
	
	/**
	 * 分页查询所有
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<EmailSender> queryAllByPage(int startIndex,int pageSize);
	
	/**
	 * 查询总数
	 * @return
	 */
	public int queryTotalCount();
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public int deleteById(Integer id);
	
	/**
	 * 保存邮箱发件人信息
	 * @param emailSender
	 * @return
	 */
	public int add(EmailSender emailSender);
	
	/**
	 * 修改邮箱发件人信息
	 * @param emailSender
	 * @return
	 */
	public int update(EmailSender emailSender);
	
}
