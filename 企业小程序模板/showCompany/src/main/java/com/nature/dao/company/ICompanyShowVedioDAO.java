package com.nature.dao.company;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.company.CompanyShowVedio;

/**
 * @description
 * @author len
 * @createDate 2018年5月3日
 */
public interface ICompanyShowVedioDAO extends BaseDaoI<CompanyShowVedio> {

	/**
	 * 根据companyId获取CompanyShowVedio
	 * @param companyId
	 * @return CompanyShowVedio
	 */
	CompanyShowVedio findVedio(Integer companyId);

}
