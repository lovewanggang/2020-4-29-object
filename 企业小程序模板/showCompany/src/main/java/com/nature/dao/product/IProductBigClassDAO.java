package com.nature.dao.product;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.product.ProductBigClass;

/**
 * @description
 * @author len
 */
public interface IProductBigClassDAO extends BaseDaoI<ProductBigClass>{

	/**
	 * @param companyId
	 * @return
	 */
	List<ProductBigClass> queryBigClassByComId(Integer companyId);

	/**
	 * @param companyId
	 * @return
	 */
	List<ProductBigClass> queryProClass(Integer companyId);

	
	
}
