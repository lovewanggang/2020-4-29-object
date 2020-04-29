package com.nature.dao.company;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.company.CompanyContactPhone;

public interface ICompanyContactPhoneDAO extends BaseDaoI<CompanyContactPhone> {

	/**
	 * 根据companyId获取List<CompanyContactPhone>
	 * @param companyId
	 * @return CompanyContactPhone
	 */
	public List<CompanyContactPhone> queryCompanyContactPhoneByComId(Integer companyId);
}
