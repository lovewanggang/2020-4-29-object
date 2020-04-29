package com.nature.dao.company;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.company.CompanyJobInfo;

public interface ICompanyJobInfoDAO extends BaseDaoI<CompanyJobInfo>{

	/**
	 * 根据companyId获取List<CompanyJobInfo>
	 * @param companyId
	 * @return CompanyJobInfo
	 */
	public List<CompanyJobInfo> queryCompanyJobInfoByComId(Integer companyId);
}
