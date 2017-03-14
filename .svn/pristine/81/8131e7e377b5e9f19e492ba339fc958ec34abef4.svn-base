package com.fiveone.edm.service;

import java.util.List;

import com.fiveone.edm.database.entity.EmailProject;

/**
 * 邮箱项目信息服务层
 * @company: 51jrq
 * @author: lhw
 * @time: 2016年12月30日 下午12:54:51
 * @version: 1.0
 * @since: JDK1.7
 */
public interface IEmailProjectService {

	/**
	 * 根据id查询邮箱项目信息
	 * @param id
	 * @return
	 */
	public EmailProject queryById(Integer id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<EmailProject> queryAll();
	
	/**
	 * 分页查询所有
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public List<EmailProject> queryAllByPage(int startIndex,int pageSize);
	
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
	 * 保存邮箱项目信息
	 * @param emailProject
	 * @return
	 */
	public int add(EmailProject emailProject);
	
	/**
	 * 修改邮箱项目信息
	 * @param emailProject
	 * @return
	 */
	public int update(EmailProject emailProject);
	
}
