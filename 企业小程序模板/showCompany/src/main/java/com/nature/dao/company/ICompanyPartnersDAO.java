package com.nature.dao.company;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.company.CompanyPartners;

public interface ICompanyPartnersDAO extends BaseDaoI<CompanyPartners> {

	/**
	 * 根据companyId获取List<CompanyPartners>
	 * @param companyId
	 * @return CompanyPartners
	 */
	public List<CompanyPartners> queryCompanyPartnersByComId(Integer companyId);
	
}
