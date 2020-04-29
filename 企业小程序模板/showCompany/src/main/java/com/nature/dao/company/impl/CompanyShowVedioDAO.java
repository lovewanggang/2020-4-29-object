package com.nature.dao.company.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.company.ICompanyShowVedioDAO;
import com.nature.model.company.CompanyShowVedio;

/**
 * @description
 * @author len
 * @createDate 2018年5月3日
 */
@Repository
public class CompanyShowVedioDAO extends BaseDaoImpl<CompanyShowVedio> implements ICompanyShowVedioDAO{

	@Override
	public CompanyShowVedio findVedio(Integer companyId) {
		String hql = "from CompanyShowVedio where company.companyId = "+companyId; 
		System.out.println("==============1111111111111=========");
		List<CompanyShowVedio> companyShowVedios;
		try {
			companyShowVedios = find(hql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		System.out.println("=======================");
		if (companyShowVedios.size()>0) {
			return companyShowVedios.get(0);
		}else {
			return null;
		}
		
	}

}
