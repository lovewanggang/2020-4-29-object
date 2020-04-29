package com.nature.dao.company.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.company.ICompanyPartnersDAO;
import com.nature.model.company.CompanyPartners;


/**
 * @description
 * @author len
 * @createDate 2018年4月18日
 */
@Repository
public class CompanyPartnersDAO extends BaseDaoImpl<CompanyPartners> implements ICompanyPartnersDAO {

	@Override
	public List<CompanyPartners> queryCompanyPartnersByComId(Integer companyId) {
		String partnerHql = "from CompanyPartners where company.companyId = " + companyId;
		return find(partnerHql);
	}

}
