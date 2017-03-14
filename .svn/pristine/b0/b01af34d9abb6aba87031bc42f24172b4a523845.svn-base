package com.fiveone.edm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fiveone.edm.database.dao.IEmailProjectDao;
import com.fiveone.edm.database.entity.EmailProject;
import com.fiveone.edm.service.IEmailProjectService;

/**
 * 邮箱项目信息服务层实现类
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月30日 下午1:00:55
 * @version: 1.0
 * @since: JDK1.7
 */
@Service
@Repository("emailProjectService")
public class EmailProjectServiceImpl implements IEmailProjectService {
	
	@Autowired
	private IEmailProjectDao emailProjectDao;

	/**
	 * 根据id查询邮箱项目信息
	 * @param id
	 * @return
	 */
	@Override
	public EmailProject queryById(Integer id) {
		return emailProjectDao.queryById(id);
	}

	/**
	 * 查询所有
	 * @return
	 */
	@Override
	public List<EmailProject> queryAll() {
		return emailProjectDao.queryAll();
	}

	/**
	 * 分页查询所有
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<EmailProject> queryAllByPage(int startIndex, int pageSize) {
		return emailProjectDao.queryAllByPage(startIndex, pageSize);
	}
	
	/**
	 * 查询总数
	 * @return
	 */
	@Override
	public int queryTotalCount() {
		return emailProjectDao.queryTotalCount();
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	@Override
	public int deleteById(Integer id) {
		return emailProjectDao.deleteById(id);
	}

	/**
	 * 保存邮箱项目信息
	 * @param emailProject
	 * @return
	 */
	@Override
	public int add(EmailProject emailProject) {
		return emailProjectDao.save(emailProject);
	}

	/**
	 * 修改邮箱项目信息
	 * @param emailProject
	 * @return
	 */
	@Override
	public int update(EmailProject emailProject) {
		return emailProjectDao.update(emailProject);
	}

}
