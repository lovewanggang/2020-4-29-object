package com.nature.dao.company.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.company.ICompanyImageDAO;
import com.nature.model.company.CompanyImage;

@Repository
public class CompanyImageDAO extends BaseDaoImpl<CompanyImage> implements ICompanyImageDAO {

	@Override
	public List<String> queryCompanyImage(Integer companyId) {
		String hql = "select imgUrl from CompanyImage where company.companyId = "+companyId;
		Query q = this.getCurrentSession().createQuery(hql);
		List<String > images = q.list();
		return images;
	}

	@Override
	public int checkComImageNum(Integer companyId) {
		String hql = "select count(*) from CompanyImage where company.companyId = " + companyId;
		Long l =  (Long)this.getCurrentSession().createQuery(hql).uniqueResult();
		 return Integer.parseInt(l.toString());
	}

	@Override
	public List<CompanyImage> queryComImage(Integer companyId) {
		String hql = "from CompanyImage where company.companyId = "+companyId;
		return find(hql);
	}

}
