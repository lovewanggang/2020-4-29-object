package com.nature.dao.base.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nature.dao.base.IWxPlatformDAO;
import com.nature.model.wxmini.WxPlatform;


@Repository
public class WxPlatformDAO extends BaseDaoImpl<WxPlatform> implements IWxPlatformDAO {

	@Transactional
	public Session getSession(){
		return getCurrentSession();
	}
}
