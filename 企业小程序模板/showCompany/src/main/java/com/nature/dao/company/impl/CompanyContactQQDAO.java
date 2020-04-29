package com.nature.dao.company.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.company.ICompanyContactQQDAO;
import com.nature.model.company.CompanyContactQQ;


/**
 * @description
 * @author len
 * @createDate 2018年4月18日
 */
@Repository
public class CompanyContactQQDAO extends BaseDaoImpl<CompanyContactQQ> implements ICompanyContactQQDAO {

	@Override
	public List<CompanyContactQQ> queryCompanyContactQQByComId(Integer companyId) {
		String hql = "from CompanyContactQQ where company.companyId = " + companyId;
		return find(hql);
	}

}
