package com.nature.util;

import java.util.List;

import org.hibernate.Session;

public interface IGenericDAO<T> {
	public T getById(String id);
	public T getById(Integer id);
	public boolean save(T t);
	public boolean update(T t);
	public boolean delete(T t);
	public List<T> findBySql(String sql);
	public List<T> findByHql(String hql);
	public List  findByHql2(String hql);
	public List findBySql2(String sql);
	public List findBySql(String sql, Class c);
	public boolean executeSql(String sql);
	public int executeUpdateSql(String sql);
	public boolean saveOrUpdate(T t);
	public Object getObjectBySql(String sql);
	public Object getObjectBySql(String sql,Class c );
	public List findMapBySql(String sql);
	
	public boolean storedProcedure(String[] data,String fx);
	public Session getHibernateSession();
}
