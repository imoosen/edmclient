package com.fiveone.edm.service;

import java.util.List;

import com.fiveone.edm.database.entity.EmailSendPolicy;

/**
 * 邮箱策略服务层
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月12日 下午3:09:02
 * @version: 1.0
 * @since: JDK1.7
 */
public interface IEmailSendPolicyService {

	/**
	 *  根据主键或邮箱类型或客户端id查找
	 * @param entity
	 * @return
	 */
	public List<?> queryPolicyByid_mailtype_clientid(EmailSendPolicy esp);
	
	/**
	 * 根据主键删除数据
	 * @param entity
	 */
	public int deleteById(EmailSendPolicy esp);
	
	/**
	 * 根据主键修改数据 
	 * @param entity
	 */
	public int updateMailSendPolicyById(EmailSendPolicy esp);
	
	/**
	 * 插入数据
	 * @param entity
	 */
	public int saveMailSendPolicy(EmailSendPolicy esp);
	
	/**
	 * 统计邮箱计划
	 * @return
	 */
	public int queryCountPlan();
}
