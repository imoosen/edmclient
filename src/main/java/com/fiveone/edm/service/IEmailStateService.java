package com.fiveone.edm.service;

import java.util.List;

import com.fiveone.edm.common.utils.Page;
import com.fiveone.edm.database.entity.EmailState;

/**
 * 邮箱状态服务层
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月29日 上午10:04:09
 * @version: 1.0
 * @since: JDK1.7
 */
public interface IEmailStateService {

	/**
	 * 根据id查询邮箱状态
	 * @param id
	 * @return
	 */
	public EmailState queryById(Integer id);
	
	/**
	 * 根据邮箱状态编号查询
	 * @param emailType
	 * @return
	 */
	public List<EmailState> queryByEmailStateNo(Integer stateNo);
	
	/**
	 * 根据邮箱状态编号和状态内容查询
	 * @param stateNo
	 * @param stateContent
	 * @return
	 */
	public EmailState queryByWhere(Integer stateNo, String stateContent);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<EmailState> queryAll();
	
	/**
	 * 分页查询所有
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public Page queryAllByPage(Page page);
	
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
	 * 保存邮箱状态
	 * @param emailState
	 */
	public int add(EmailState emailState);
	
	/**
	 * 修改邮箱状态
	 * @param emailState
	 */
	public int update(EmailState emailState);
	
}
