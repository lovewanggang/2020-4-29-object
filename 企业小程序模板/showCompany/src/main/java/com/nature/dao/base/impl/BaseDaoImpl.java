package com.nature.dao.base.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nature.dao.base.BaseDaoI;



@Repository("baseDao")
@SuppressWarnings("all")
public class BaseDaoImpl<T> implements BaseDaoI<T> {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable save(T o) {
		Transaction tx = this.getCurrentSession().beginTransaction();
		Serializable s = this.getCurrentSession().save(o);
		this.getCurrentSession().flush();
		tx.commit();
		return s;
	}
	/**
	 * 业务需要的特别方法
	 */
	@Override
	public Serializable saveWxPlatform(T o) {
		Serializable s = this.getCurrentSession().save(o);
		return s;
	}

	@Override
	public List<Object> findBySql(String sql) {
		Session session = null;
		session = this.getCurrentSession();
		try {
			List<Object> list = session.createSQLQuery(sql).list();    
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null) {
				if (session.isOpen()) {
					session.close();
				}
			}
		}
	}
	
	@Override
	public T get(Class<T> c, Serializable id) {
		return (T) this.getCurrentSession().get(c, id);
	}

	@Override
	public T get(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public T get(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public void delete(T o) {
		this.getCurrentSession().delete(o);
		this.getCurrentSession().flush();
	}

	@Override
	public void update(T o) {
		this.getCurrentSession().update(o);
		this.getCurrentSession().flush();
	}

	@Override
	public void saveOrUpdate(T o) {
		this.getCurrentSession().saveOrUpdate(o);
		this.getCurrentSession().flush();
	}

	@Override
	public List<T> find(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public List<T> find(String hql, int page, int rows) {
		Query q = this.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public Long count(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public int executeHql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		int i = q.executeUpdate();
		this.getCurrentSession().flush();
		return i;
	}

}