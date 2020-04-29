package com.nature.dao.base;

import java.util.List;

import com.nature.model.account.SystemUser;


public interface ISystemUserDAO extends BaseDaoI<SystemUser> {

	
	/*
	 * 根据openId获取该用户
	 */

	public List<SystemUser> getUserListByScore();

	public SystemUser getUserByOpenId(String openId);

}
