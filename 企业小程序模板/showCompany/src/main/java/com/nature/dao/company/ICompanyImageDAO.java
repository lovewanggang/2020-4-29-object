package com.nature.dao.company;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.company.CompanyImage;

public interface ICompanyImageDAO extends BaseDaoI<CompanyImage>{

	
	/**
	 * 根据companyId获取公司形象图片列表
	 * @param companyId
	 * @return List（String）
	 */
	List<String> queryCompanyImage(Integer companyId);

	/**
	 * 获取公司形象图片数量
	 * @param companyId
	 * @return
	 */
	int checkComImageNum(Integer companyId);

	/**
	 * @param companyId
	 * @return
	 */
	List<CompanyImage> queryComImage(Integer companyId);
}
