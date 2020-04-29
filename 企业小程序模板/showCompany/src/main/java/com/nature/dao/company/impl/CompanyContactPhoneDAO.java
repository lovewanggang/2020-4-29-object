package com.nature.dao.company.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.company.ICompanyContactPhoneDAO;
import com.nature.model.company.CompanyContactPhone;


/**
 * @description
 * @author len
 * @createDate 2018年4月18日
 */
@Repository
public class CompanyContactPhoneDAO extends BaseDaoImpl<CompanyContactPhone> implements ICompanyContactPhoneDAO {

	@Override
	public List<CompanyContactPhone> queryCompanyContactPhoneByComId(Integer companyId) {
		String hql = "from CompanyContactPhone where company.companyId = "+companyId;
		return find(hql);
	}

}
