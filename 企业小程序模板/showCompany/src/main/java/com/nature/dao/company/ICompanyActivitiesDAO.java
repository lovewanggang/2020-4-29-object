package com.nature.dao.company;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.company.CompanyActivities;

/**
 * @description
 * @author len
 */
public interface ICompanyActivitiesDAO extends BaseDaoI<CompanyActivities>{

	/**
	 * 根据companyId 获取 CompanyActivities对象的列表
	 * @param companyId
	 * @return CompanyActivities对象的列表
	 */
	List<CompanyActivities> queryComActivities(Integer companyId);
	
	
}
