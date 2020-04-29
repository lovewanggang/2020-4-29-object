package com.nature.dao.company;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.company.CompanyBusiness;

public interface ICompanyBusinessDAO extends BaseDaoI<CompanyBusiness> {

	/**
	 * 根据companyId获取Companybusiess
	 * @param companyId
	 * @return CompanyBusiness
	 */
	public CompanyBusiness getCompanyBusinessByComId(Integer companyId);

	/**
	 * @param companyId
	 * @return
	 */
	public CompanyBusiness queryBusiness(Integer companyId);
	
	
	
}
