package com.nature.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @description
 * @author len
 * @createDate 2017年8月11日上午10:36:13
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	static {
		try {
			Configuration cfg = new Configuration().configure();
			sessionFactory = cfg.buildSessionFactory();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	public static void closeSession(Session session){
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
}
