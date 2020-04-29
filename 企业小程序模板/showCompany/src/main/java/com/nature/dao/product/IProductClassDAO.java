package com.nature.dao.product;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.product.ProductClass;

/**
 * @description
 * @author len
 * @createDate 2018年4月19日
 */
public interface IProductClassDAO extends BaseDaoI<ProductClass> {

	/**
	 * @param id
	 * @return
	 */
	List<ProductClass> findProductClassByBCid(Integer id);

}
