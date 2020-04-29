package com.nature.dao.base;

import org.hibernate.Session;

import com.nature.model.wxmini.WxPlatform;

public interface IWxPlatformDAO extends BaseDaoI<WxPlatform> {

	public Session getSession();
}
