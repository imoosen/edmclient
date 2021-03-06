package com.fiveone.edm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.fiveone.edm.database.dao.IEmailLogsDao;
import com.fiveone.edm.database.entity.EmailLogs;
import com.fiveone.edm.service.IEmailLogsService;

/**
 * 邮箱日志服务层操作实现类
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月29日 上午10:34:21
 * @version: 1.0
 * @since: JDK1.7
 */
@Service
@Repository("emailLogsService")
public class EmailLogsServiceImpl implements IEmailLogsService {
	
	@Autowired
	private IEmailLogsDao emailLogsDao;

	/**
	 * 根据id查询邮箱日志
	 * @param id
	 * @return
	 */
	@Override
	public EmailLogs queryById(Integer id) {
		return emailLogsDao.queryById(id);
	}

	/**
	 * 查询所有
	 * @return
	 */
	@Override
	public List<EmailLogs> queryAll() {
		return emailLogsDao.queryAll();
	}

	/**
	 * 分页查询所有
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<EmailLogs> queryAllByPage(int startIndex, int pageSize) {
		return emailLogsDao.queryAllByPage(startIndex, pageSize);
	}

	/**
	 * 查询总数
	 * @return
	 */
	@Override
	public int queryTotalCount() {
		return emailLogsDao.queryTotalCount();
	}

	/**
	 * 统计某个邮件项目每分钟发送量
	 * @param table   邮箱日志表名
	 * @return
	 */
	@Override
	public int queryCountByMinute(String table) {
		return emailLogsDao.queryCountByMinute(table);
	}

	/**
	 * 统计某个邮件项目每小时发送量
	 * @param table	邮箱日志表名
	 * @return
	 */
	@Override
	public int queryCountByHour(String table) {
		return emailLogsDao.queryCountByHour(table);
	}

	/**
	 * 统计某个邮件项目每天发送量
	 * @param table	邮箱日志表名
	 * @return
	 */
	@Override
	public int queryCountByDay(String table) {
		return emailLogsDao.queryCountByDay(table);
	}

	/**
	 * 根据id删除
	 * @param id
	 */
	@Override
	public int deleteById(Integer id) {
		return emailLogsDao.deleteById(id);
	}
	
	/**
	 * 保存邮箱日志
	 * @param emailLogs
	 */
	@Override
	public int add(EmailLogs emailLogs) {
		return emailLogsDao.save(emailLogs);
	}

	/**
	 * 修改邮箱日志
	 * @param emailLogs
	 */
	@Override
	public int update(EmailLogs emailLogs) {
		return emailLogsDao.update(emailLogs);
	}
	
	/**
	 * 修改邮箱日志
	 * @param emailLogs
	 */
	@Override
	public int updateByEmailId(EmailLogs emailLogs,String no) {
		return emailLogsDao.updateByEmailId(emailLogs, no);
	}

}
