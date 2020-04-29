package com.nature.dao.company.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.company.ICompanyAccountDAO;
import com.nature.model.company.Company;
import com.nature.model.company.CompanyAccount;
import com.nature.model.company.CompanyActivities;

@Repository
public class CompanyAccountDAO extends BaseDaoImpl<CompanyAccount> implements ICompanyAccountDAO{

	@Override
	public Company getCompanyByAccountId(String companyAccountId) {
		return get(CompanyAccount.class,companyAccountId).getCompany();
	}

	@Override
	public List<CompanyAccount> queryComAccountByAccountAndPassword(String account, String password) {
		String hql = "from CompanyAccount where loginAccount = '"+account+"' and loginPassword = '"+password+"'";
		return find(hql);
	}

	@Override
	public CompanyAccount findComAccount(Integer companyId) {
		String hql = "from CompanyAccount where company.companyId = "+companyId;
		List<CompanyAccount> accounts = find(hql);
		return accounts.get(0);
	}

	
}
