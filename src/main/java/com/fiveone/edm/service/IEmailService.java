package com.fiveone.edm.service;

import java.util.List;

import com.fiveone.edm.database.entity.Email;

/**
 * 邮箱类服务层
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月29日 上午10:01:09
 * @version: 1.0
 * @since: JDK1.7
 */
public interface IEmailService {

	/**
	 * 根据id查询邮箱
	 * @param id
	 * @return
	 */
	public Email queryById(Integer id);
	
	/**
	 * 根据邮箱类型查询
	 * @param emailType
	 * @return
	 */
	public List<Email> queryByEmailType(String emailType);
	
	/**
	 * 根据邮箱地址查询
	 * @param emailAddress
	 * @return
	 */
	public List<Email> queryByEmailAddress(String emailAddress,String no);

	/**
	 * 查询所有
	 * @return
	 */
	public List<Email> queryAll();
	
	/**
	 * 分页查询所有
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<Email> queryAllByPage(int startIndex,int pageSize);
	
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
	 * 保存邮箱
	 * @param email
	 */
	public int add(Email email);
	
	/**
	 * 修改邮箱
	 * @param email
	 */
	public int update(Email email);
	
}
