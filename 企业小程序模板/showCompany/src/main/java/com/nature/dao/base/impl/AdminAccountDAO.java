package com.nature.dao.base.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.IAdminAccountDAO;
import com.nature.model.account.AdminAccount;

@Repository
public class AdminAccountDAO extends BaseDaoImpl<AdminAccount> implements IAdminAccountDAO {

	@Override
	public List<AdminAccount> queryAdminAccount(String name, String password) {
		String hql = "from AdminAccount where loginName = '"+name+"' and password = '"+password+"'";
		return find(hql);
	}

}
