package com.fiveone.edm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fiveone.edm.common.utils.Page;
import com.fiveone.edm.database.dao.IEmailStateDao;
import com.fiveone.edm.database.entity.EmailState;
import com.fiveone.edm.service.IEmailStateService;

/**
 * 邮箱状态服务层操作实现类
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月29日 上午10:26:34
 * @version: 1.0
 * @since: JDK1.7
 */
@Service
@Repository("emailStateService")
public class EmailStateServiceImpl implements IEmailStateService {
	
	@Autowired
	private IEmailStateDao emailStateDao;

	/**
	 * 根据id查询邮箱状态
	 * @param id
	 * @return
	 */
	@Override
	public EmailState queryById(Integer id) {
		return emailStateDao.queryById(id);
	}

	/**
	 * 根据邮箱状态编号查询
	 * @param emailType
	 * @return
	 */
	@Override
	public List<EmailState> queryByEmailStateNo(Integer stateNo) {
		return emailStateDao.queryByEmailStateNo(stateNo);
	}
	
	/**
	 * 根据邮箱状态编号和状态内容查询
	 * @param stateNo
	 * @param stateContent
	 * @return
	 */
	public EmailState queryByWhere(Integer stateNo, String stateContent) {
		return emailStateDao.queryByWhere(stateNo, stateContent);
	}

	/**
	 * 查询所有
	 * @return
	 */
	@Override
	public List<EmailState> queryAll() {
		return emailStateDao.queryAll();
	}

	/**
	 * 分页查询所有
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public Page queryAllByPage(Page page) {
		List<EmailState> esList =  emailStateDao.queryAllByPage(page.getStartIndex(), page.getPageSize());
		int rowCount = emailStateDao.queryTotalCount();
		page.setResult(esList, rowCount);
		return page;
	}
	
	/**
	 * 查询总数
	 * @return
	 */
	@Override
	public int queryTotalCount() {
		return emailStateDao.queryTotalCount();
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	@Override
	public int deleteById(Integer id) {
		return emailStateDao.deleteById(id);
	}

	/**
	 * 保存邮箱状态
	 * @param emailState
	 */
	@Override
	public int add(EmailState emailState) {
		return emailStateDao.save(emailState);
	}

	/**
	 * 修改邮箱状态
	 * @param emailState
	 */
	@Override
	public int update(EmailState emailState) {
		return emailStateDao.update(emailState);
	}

}
