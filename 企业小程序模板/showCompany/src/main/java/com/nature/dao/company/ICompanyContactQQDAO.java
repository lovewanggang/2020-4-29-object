package com.nature.dao.company;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.company.CompanyContactQQ;

public interface ICompanyContactQQDAO extends BaseDaoI<CompanyContactQQ> {

	/**
	 * 根据companyId获取List<CompanyContactQQ>
	 * @param companyId
	 * @return CompanyContactQQ
	 */
	public List<CompanyContactQQ> queryCompanyContactQQByComId(Integer companyId);
}
