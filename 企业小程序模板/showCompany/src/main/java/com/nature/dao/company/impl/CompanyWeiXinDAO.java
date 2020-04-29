package com.nature.dao.company.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.company.ICompanyWeiXinDAO;
import com.nature.model.company.CompanyWeiXin;

@Repository
public class CompanyWeiXinDAO extends BaseDaoImpl<CompanyWeiXin> implements ICompanyWeiXinDAO {

	@Override
	public List<CompanyWeiXin> queryComWeixinByComId(Integer companyId) {
		String hql = "from CompanyWeiXin where company.companyId = "+companyId;
		
		return find(hql);
	}

}
