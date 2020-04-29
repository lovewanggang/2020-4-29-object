package com.nature.dao.product;

import java.util.List;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.product.ProductParamerValue;

public interface IProductParamerValueDAO extends BaseDaoI<ProductParamerValue> {

	List<ProductParamerValue> queryProductParamerByPID(Integer id);

}
