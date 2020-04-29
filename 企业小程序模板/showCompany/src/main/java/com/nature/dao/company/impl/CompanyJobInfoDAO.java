package com.nature.dao.company.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.company.ICompanyJobInfoDAO;
import com.nature.model.company.CompanyJobInfo;

/**
 * @description
 * @author len
 * @createDate 2018年4月18日
 */
@Repository
public class CompanyJobInfoDAO extends BaseDaoImpl<CompanyJobInfo> implements ICompanyJobInfoDAO {

	@Override
	public List<CompanyJobInfo> queryCompanyJobInfoByComId(Integer companyId) {
		String jobHql = "from CompanyJobInfo where company.companyId = " + companyId;
		return find(jobHql);
	}

}
