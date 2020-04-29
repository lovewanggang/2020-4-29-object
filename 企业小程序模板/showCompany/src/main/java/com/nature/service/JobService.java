package com.nature.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.nature.dao.company.ICompanyAccountDAO;
import com.nature.dao.company.ICompanyDAO;
import com.nature.dao.company.ICompanyJobInfoDAO;
import com.nature.model.company.Company;
import com.nature.model.company.CompanyJobInfo;

@Service
public class JobService {

	@Resource
	private ICompanyJobInfoDAO companyJobInfoDAO;
	@Resource
	private ICompanyAccountDAO companyAccountDAO;
	@Resource
	private ICompanyDAO companyDAO;

	public void detailJob(Integer id, String sCcode, Map<String, Object> dataMap) {
		CompanyJobInfo job = companyJobInfoDAO.get(CompanyJobInfo.class, id);
		System.out.println(sCcode);
		Company company = companyAccountDAO.getCompanyByAccountId(sCcode);
		List<CompanyJobInfo> companyJobInfo = companyJobInfoDAO.queryCompanyJobInfoByComId(company.getCompanyId());
		for (int i = 0; i < companyJobInfo.size(); i++) {
			if (companyJobInfo.get(i).getCompanyJobInfoId() == job.getCompanyJobInfoId()) {
				companyJobInfo.remove(i);
				i--;
			}
		}
		
		dataMap.put("job", job);
		dataMap.put("jobs", companyJobInfo);
	}

	public ICompanyJobInfoDAO getCompanyJobInfoDAO() {
		return companyJobInfoDAO;
	}

	public void setCompanyJobInfoDAO(ICompanyJobInfoDAO companyJobInfoDAO) {
		this.companyJobInfoDAO = companyJobInfoDAO;
	}

	public ICompanyAccountDAO getCompanyAccountDAO() {
		return companyAccountDAO;
	}

	public void setCompanyAccountDAO(ICompanyAccountDAO companyAccountDAO) {
		this.companyAccountDAO = companyAccountDAO;
	}

	public ICompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public void setCompanyDAO(ICompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}
	
	
	
	
	
}
