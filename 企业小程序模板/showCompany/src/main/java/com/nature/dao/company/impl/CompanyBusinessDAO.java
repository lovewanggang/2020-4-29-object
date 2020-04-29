package com.nature.dao.company.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.company.ICompanyBusinessDAO;
import com.nature.model.company.CompanyBusiness;


/**
 * @description
 * @author len
 * @createDate 2018年4月18日
 */
@Repository
public class CompanyBusinessDAO extends BaseDaoImpl<CompanyBusiness> implements ICompanyBusinessDAO {

	@Override
	public CompanyBusiness getCompanyBusinessByComId(Integer companyId) {
		String comBusiHql = "from CompanyBusiness where company.companyId = " + companyId;
		List<CompanyBusiness> companyBusinesses = find(comBusiHql);
		if (companyBusinesses.size()>0) {
			return companyBusinesses.get(0);
		}else {
			return null;
		}
	}

	@Override
	public CompanyBusiness queryBusiness(Integer companyId) {
		String hql = "from CompanyBusiness where company.companyId = "+companyId;
		List<CompanyBusiness> businesses = find(hql);
		if (businesses.size() > 0) {
			return businesses.get(0);
		}
		return null;
	}

}
