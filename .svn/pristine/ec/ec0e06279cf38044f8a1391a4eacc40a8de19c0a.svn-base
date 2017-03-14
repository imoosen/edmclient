package com.fiveone.edm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fiveone.edm.database.dao.IEmailContentDao;
import com.fiveone.edm.database.entity.EmailContent;
import com.fiveone.edm.service.IEmailContentService;

/**
 * 邮箱内容数据库操作实现类
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月27日下午4:31:22
 * @version: 1.0
 * @since: JDK1.7
 */
@Service
@Repository("emailContentService")
public class EmailContentImpl implements IEmailContentService {
	
	@Autowired
	private IEmailContentDao emailContentDao;

	/**
	 * 根据id查询邮箱内容
	 * @param id
	 * @return
	 */
	@Override
	public EmailContent queryById(Integer id) {
		return emailContentDao.queryById(id);
	}

	/**
	 * 查询所有
	 * @return
	 */
	@Override
	public List<EmailContent> queryAll() {
		return emailContentDao.queryAll();
	}

	/**
	 * 分页查询所有
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<EmailContent> queryAllByPage(int startIndex, int pageSize) {
		return emailContentDao.queryAllByPage(startIndex, pageSize);
	}
	
	/**
	 * 查询总数
	 * @return
	 */
	@Override
	public int queryTotalCount() {
		return emailContentDao.queryTotalCount();
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	@Override
	public int deleteById(Integer id) {
		return emailContentDao.deleteById(id);
	}

	/**
	 * 保存邮箱内容
	 * @param emailContent
	 */
	@Override
	public int add(EmailContent emailContent) {
		return emailContentDao.save(emailContent);
	}

	/**
	 * 修改邮箱内容
	 * @param emailContent
	 */
	@Override
	public int update(EmailContent emailContent) {
		return emailContentDao.update(emailContent);
	}

}
