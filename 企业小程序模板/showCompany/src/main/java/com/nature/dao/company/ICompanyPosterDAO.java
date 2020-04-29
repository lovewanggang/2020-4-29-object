package com.nature.dao.company;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.company.CompanyPoster;

public interface ICompanyPosterDAO extends BaseDaoI<CompanyPoster>{

	/**
	 * 获取海报数量
	 * @param companyId
	 * @return
	 */
	int checkPosterNum(Integer companyId);

	/**
	 * @param companyId
	 * @return
	 */
	List<CompanyPoster> queryPosterByComId(Integer companyId);

}
