package com.nature.dao.company.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.company.ICompanyHonorsDAO;
import com.nature.model.company.CompanyHonors;


/**
 * @description
 * @author len
 * @createDate 2018年4月18日
 */
@Repository
public class CompanyHonorsDAO extends BaseDaoImpl<CompanyHonors> implements ICompanyHonorsDAO {

	@Override
	public List<CompanyHonors> queryCompanyHonorsByComId(Integer companyId) {
		String honorHql = "from CompanyHonors where company.companyId = " + companyId + "order by sequence";
		return find(honorHql);
	}

}
