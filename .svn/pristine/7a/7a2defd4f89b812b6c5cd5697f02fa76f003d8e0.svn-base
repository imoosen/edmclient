package com.fiveone.edm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fiveone.edm.database.dao.IEmailDao;
import com.fiveone.edm.database.entity.Email;
import com.fiveone.edm.service.IEmailService;

/**
 * 邮箱服务层操作实现类
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月29日 上午10:10:09
 * @version: 1.0
 * @since: JDK1.7
 */
@Service
@Repository("emailService")
public class EmailServiceImpl implements IEmailService {
	
	/**
	 * 使用注解控制事务方法的优点： 
	 * 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
	 * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	@Autowired
	private IEmailDao emailDao;

	/**
	 * 根据id查询邮箱
	 * @param id
	 * @return
	 */
	@Override
	public Email queryById(Integer id) {
		return emailDao.queryById(id);
	}

	/**
	 * 根据邮箱类型查询
	 * @param emailType
	 * @return
	 */
	@Override
	public List<Email> queryByEmailType(String emailType) {
		return emailDao.queryByEmailType(emailType);
	}
	
	/**
	 * 根据邮箱地址查询
	 * @param emailAddress
	 * @return
	 */
	public List<Email> queryByEmailAddress(String emailAddress,String no) {
		return emailDao.queryByEmailAddress(emailAddress, no);
	}


	/**
	 * 查询所有
	 * @return
	 */
	@Override
	public List<Email> queryAll() {
		return emailDao.queryAll();
	}

	/**
	 * 分页查询所有
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<Email> queryAllByPage(int startIndex, int pageSize) {
		return emailDao.queryAllByPage(startIndex, pageSize);
	}

	/**
	 * 查询总数
	 * @return
	 */
	@Override
	public int queryTotalCount() {
		return emailDao.queryTotalCount();
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	@Override
	public int deleteById(Integer id) {
		return emailDao.deleteById(id);
	}

	/**
	 * 保存邮箱
	 * @param email
	 */
	@Override
	public int add(Email email) {
		return emailDao.save(email);
	}

	/**
	 * 修改邮箱
	 * @param email
	 */
	@Override
	public int update(Email email) {
		return emailDao.update(email);
	}

}
