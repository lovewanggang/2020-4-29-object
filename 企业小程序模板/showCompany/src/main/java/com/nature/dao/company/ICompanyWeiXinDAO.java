package com.nature.dao.company;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.company.CompanyWeiXin;

public interface ICompanyWeiXinDAO extends BaseDaoI<CompanyWeiXin> {

	/**
	 * @param companyId
	 * @return
	 */
	List<CompanyWeiXin> queryComWeixinByComId(Integer companyId);

}
