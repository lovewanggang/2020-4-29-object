package com.nature.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDaoI<T> {

	public Serializable saveWxPlatform(T o);
	
	/**
	 * 保存
	 * @param o 实体
	 * @return
	 */
	public Serializable save(T o);

	/**
	 * 删除
	 * @param o 实体
	 */
	public void delete(T o);

	/**
	 * 更新
	 * @param o 实体
	 */
	public void update(T o);

	/**
	 * 保存或更新
	 * @param o 实体
	 */
	public void saveOrUpdate(T o);

	/**
	 * 获取单个实体
	 * @param hql HQL语句
	 * @return 实体
	 */
	public T get(String hql);

	/**
	 * 获取单个实体
	 * @param hql HQL语句
	 * @param params 条件参数
	 * @return 实体
	 */
	public T get(String hql, Map<String, Object> params);

	public List<Object> findBySql(String sql);
	
	/**
	 * 查询
	 * @param hql HQL语句
	 * @return 结果列表
	 */
	public List<T> find(String hql);

	/**
	 * 查询
	 * @param hql HQL语句
	 * @param params 条件参数
	 * @return 结果列表
	 */
	public List<T> find(String hql, Map<String, Object> params);

	/**
	 * 查询
	 * @param hql HQL语句
	 * @param page 页码
	 * @param rows 每页行数
	 * @return 结果列表
	 */
	public List<T> find(String hql, int page, int rows);

	/**
	 * 查询
	 * @param hql HQL语句
	 * @param params 条件参数
	 * @param page 页码
	 * @param rows 每页行数
	 * @return 结果列表
	 */
	public List<T> find(String hql, Map<String, Object> params, int page, int rows);

	/**
	 * 
	 * @param hql HQL语句
	 * @return
	 */
	public Long count(String hql);

	/**
	 * 
	 * @param hql HQL语句
	 * @param params 条件参数
	 * @return
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * 获取实体
	 * @param c 实体类
	 * @param id 标识
	 * @return 实体
	 */
	public T get(Class<T> c, Serializable id);

	/**
	 * 执行HQL语句
	 * @param hql HQL语句
	 * @return 受影响行数
	 */
	public int executeHql(String hql);

}