package com.fiveone.edm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fiveone.edm.database.dao.IEmailSendPolicyDao;
import com.fiveone.edm.database.entity.EmailSendPolicy;
import com.fiveone.edm.service.IEmailSendPolicyService;

/**
 * 邮箱策略服务层实现类
 * @company: 51jrq
 * @author: lhw
 * @time: 2017年1月12日 下午3:31:30
 * @version: 1.0
 * @since: JDK1.7
 */
@Service
@Repository("emailSendPolicyService")
public class EmailSendPolicyServiceImpl implements IEmailSendPolicyService {

	@Autowired
	private IEmailSendPolicyDao emailSendPolicyDao;

	/**
	 *  根据主键或邮箱类型或客户端id查找
	 * @param entity
	 * @return
	 */
	@Override
	public List<?> queryPolicyByid_mailtype_clientid(EmailSendPolicy esp) {
		return emailSendPolicyDao.selectPolicyByid_mailtype_clientid(esp);
	}

	/**
	 * 根据主键删除数据
	 * @param entity
	 */
	@Override
	public int deleteById(EmailSendPolicy esp) {
		return emailSendPolicyDao.deleteByPrimaryKey(esp);
	}

	/**
	 * 根据主键修改数据 
	 * @param entity
	 */
	@Override
	public int updateMailSendPolicyById(EmailSendPolicy esp) {
		return emailSendPolicyDao.updateMailSendPolicyById(esp);
	}

	/**
	 * 插入数据
	 * @param entity
	 */
	@Override
	public int saveMailSendPolicy(EmailSendPolicy esp) {
		return emailSendPolicyDao.insertMailSendPolicy(esp);
	}

	/**
	 * 统计邮箱计划
	 * @return
	 */
	@Override
	public int queryCountPlan() {
		return emailSendPolicyDao.queryCountPlan();
	}

}
