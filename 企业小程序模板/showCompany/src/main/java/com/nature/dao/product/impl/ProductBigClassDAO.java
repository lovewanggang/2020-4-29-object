package com.nature.dao.product.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.product.IProductBigClassDAO;
import com.nature.model.product.ProductBigClass;

/**
 * @description
 * @author len
 */
@Repository
public class ProductBigClassDAO extends BaseDaoImpl<ProductBigClass> implements IProductBigClassDAO{

	@Override
	public List<ProductBigClass> queryBigClassByComId(Integer companyId) {
		String hql = "from ProductBigClass where company.companyId = "+companyId+" and status = 1";
		List<ProductBigClass> productBigClasses;
		try {
			productBigClasses = find(hql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return productBigClasses;
	}

	@Override
	public List<ProductBigClass> queryProClass(Integer companyId) {
		String hql = "from ProductBigClass where company.companyId = "+companyId;
		return find(hql);
	}
	
	
	
}
