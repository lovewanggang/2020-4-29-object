package com.nature.dao.base.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.ISystemUserDAO;
import com.nature.model.account.SystemUser;

@Repository
public class SystemUserDAO extends BaseDaoImpl<SystemUser> implements ISystemUserDAO {
	

	public SystemUser getUserByOpenId(String openId) {
		String sql = "from SystemUser where openid = '"+openId+"' ";
		List<SystemUser> list = find(sql);
		if(list.size()!=0)
			return list.get(0);
		else 
			return null;
	}
	
	public List<SystemUser> getUserListByScore() {
		String sql = "from SystemUser WHERE YEARWEEK(date_format(createTimes,'%Y-%m-%d')) = YEARWEEK(now()) order by score DESC";
		List<SystemUser> list = find(sql);
		if(list!=null)
			return list;
		else 
			return null;
	}


}
