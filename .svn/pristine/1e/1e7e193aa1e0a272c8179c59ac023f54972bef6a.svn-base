package com.fiveone.edm.service;

import java.util.List;

import com.fiveone.edm.database.entity.EmailLogs;

/**
 * 邮箱日志服务层
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月29日 上午10:03:16
 * @version: 1.0
 * @since: JDK1.7
 */
public interface IEmailLogsService {

	/**
	 * 根据id查询邮箱日志
	 * @param id
	 * @return
	 */
	public EmailLogs queryById(Integer id);

	/**
	 * 查询所有
	 * @return
	 */
	public List<EmailLogs> queryAll();
	
	/**
	 * 分页查询所有
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<EmailLogs> queryAllByPage(int startIndex,int pageSize);
	
	/**
	 * 查询总数
	 * @return
	 */
	public int queryTotalCount();
	
	/**
	 * 统计某个邮件项目每分钟发送量
	 * @param table     邮箱日志表名
	 * @return
	 */
	public int queryCountByMinute(String table);
	
	/**
	 * 统计某个邮件项目每小时发送量
	 * @param table	邮箱日志表名
	 * @return
	 */
	public int queryCountByHour(String table);
	
	/**
	 * 统计某个邮件项目每天发送量
	 * @param table	邮箱日志表名
	 * @return
	 */
	public int queryCountByDay(String table);
	
	/**
	 * 根据id删除
	 * @param id
	 */
	public int deleteById(Integer id);
	
	/**
	 * 保存邮箱日志
	 * @param emailLogs
	 */
	public int add(EmailLogs emailLogs);
	
	/**
	 * 修改邮箱日志
	 * @param emailLogs
	 */
	public int update(EmailLogs emailLogs);
	
	/**
	 * 修改邮箱日志
	 * @param emailLogs
	 */
	public int updateByEmailId(EmailLogs emailLogs,String no);
	
}
