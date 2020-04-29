package com.nature.dao.company;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.company.CompanyHonors;

public interface ICompanyHonorsDAO extends BaseDaoI<CompanyHonors> {

	/**
	 * 根据companyId获取List<CompanyHonors>
	 * @param companyId
	 * @return CompanyHonors
	 */
	public List<CompanyHonors> queryCompanyHonorsByComId(Integer companyId);
}
