package com.nature.dao.product;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.product.Product;

/**
 * @description
 * @author len
 * @createDate 2018年4月19日
 */
public interface IProductDAO extends BaseDaoI<Product> {

	/**
	 * 根据公司ID查询该公司的人气产品
	 * @param companyId
	 * @return
	 */
	List<Product> queryHotProductByComId(Integer companyId);

	List<Product> queryProductById(Integer productClassId);

}
