package com.nature.dao.company;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.company.Company;
import com.nature.model.company.CompanyAccount;
import com.nature.model.company.CompanyActivities;

public interface ICompanyAccountDAO extends BaseDaoI<CompanyAccount>{

	/**
	 * 根据companyAccountId 获取Company对象
	 * @param companyAccountId
	 * @return Company
	 */
	Company getCompanyByAccountId(String companyAccountId);

	
	/**
	 * 
	 * @return
	 */
	List<CompanyAccount> queryComAccountByAccountAndPassword(String account, String password);


	/**
	 * @param companyId
	 * @return
	 */
	CompanyAccount findComAccount(Integer companyId);
	
}
