package com.nature.dao.company.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.company.ICompanyPosterDAO;
import com.nature.model.company.CompanyPoster;

@Repository
public class CompanyPosterDAO extends BaseDaoImpl<CompanyPoster> implements ICompanyPosterDAO{

	@Override
	public int checkPosterNum(Integer companyId) {
		String hql = "select count(*) from CompanyPoster where company.companyId = "+companyId;
		Query query = getCurrentSession().createQuery(hql);
		Long countL = (Long)query.uniqueResult();
		int count = countL.intValue();
		return count;
	}

	@Override
	public List<CompanyPoster> queryPosterByComId(Integer companyId) {
		String hql = "from CompanyPoster where company.companyId = "+companyId;
		return find(hql);
	}

}
