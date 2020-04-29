package com.nature.dao.product.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.product.IProductClassDAO;
import com.nature.model.product.ProductClass;

/**
 * @description
 * @author len
 * @createDate 2018年4月19日
 */
@Repository
public class ProductClassDAO extends BaseDaoImpl<ProductClass> implements IProductClassDAO{

	@Override
	public List<ProductClass> findProductClassByBCid(Integer id) {
		String hql = "from ProductClass where productBigClass.productBigClassId = "+id+" and status = 1";
		return find(hql);
	}

}
