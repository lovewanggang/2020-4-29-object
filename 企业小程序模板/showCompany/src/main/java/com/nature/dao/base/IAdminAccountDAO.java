package com.nature.dao.base;

import java.util.List;

import com.nature.model.account.AdminAccount;

public interface IAdminAccountDAO extends BaseDaoI<AdminAccount> {

	/**
	 * @param name
	 * @param password
	 * @return
	 */
	List<AdminAccount> queryAdminAccount(String name, String password);

}
