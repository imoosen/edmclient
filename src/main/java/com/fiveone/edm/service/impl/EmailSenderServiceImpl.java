package com.fiveone.edm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fiveone.edm.database.dao.IEmailSenderDao;
import com.fiveone.edm.database.entity.EmailSender;
import com.fiveone.edm.service.IEmailSenderService;

/**
 * 邮箱发件人信息服务层实现类
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月30日 下午1:05:18
 * @version: 1.0
 * @since: JDK1.7
 */
@Service
@Repository("emailSenderService")
public class EmailSenderServiceImpl implements IEmailSenderService {
	
	@Autowired
	private IEmailSenderDao emailSenderDao;

	/**
	 * 根据id查询邮箱发件人信息
	 * @param id
	 * @return
	 */
	@Override
	public EmailSender queryById(Integer id) {
		return emailSenderDao.queryById(id);
	}

	/**
	 * 查询所有
	 * @return
	 */
	@Override
	public List<EmailSender> queryAll() {
		return emailSenderDao.queryAll();
	}

	/**
	 * 分页查询所有
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<EmailSender> queryAllByPage(int startIndex, int pageSize) {
		return emailSenderDao.queryAllByPage(startIndex, pageSize);
	}
	
	/**
	 * 查询总数
	 * @return
	 */
	@Override
	public int queryTotalCount() {
		return emailSenderDao.queryTotalCount();
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	@Override
	public int deleteById(Integer id) {
		return emailSenderDao.deleteById(id);
	}

	/**
	 * 保存邮箱发件人信息
	 */
	@Override
	public int add(EmailSender emailSender) {
		return emailSenderDao.save(emailSender);
	}

	/**
	 * 修改邮箱发件人信息
	 */
	@Override
	public int update(EmailSender emailSender) {
		return emailSenderDao.update(emailSender);
	}

}
