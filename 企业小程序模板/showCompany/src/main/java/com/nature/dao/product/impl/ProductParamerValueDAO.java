package com.nature.dao.product.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.product.IProductParamerValueDAO;
import com.nature.model.product.ProductParamerValue;

@Repository
public class ProductParamerValueDAO extends BaseDaoImpl<ProductParamerValue> implements IProductParamerValueDAO {

	@Override
	public List<ProductParamerValue> queryProductParamerByPID(Integer id) {
		String hql = "from ProductParamerValue where product.productId = "+id;
		return find(hql);
	}


}
