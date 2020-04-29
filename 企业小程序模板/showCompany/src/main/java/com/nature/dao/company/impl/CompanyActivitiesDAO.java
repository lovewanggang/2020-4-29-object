package com.nature.dao.company.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.company.ICompanyActivitiesDAO;
import com.nature.model.company.CompanyActivities;

/**
 * @description
 * @author len
 * @createDate 2018年4月27日
 */
@Repository
public class CompanyActivitiesDAO extends BaseDaoImpl<CompanyActivities> implements ICompanyActivitiesDAO{

	@Override
	public List<CompanyActivities> queryComActivities(Integer companyId) {
		String hql = "from CompanyActivities where company.companyId = "+companyId+" order by companyActivitiesId desc";
		return find(hql);
	}

}
