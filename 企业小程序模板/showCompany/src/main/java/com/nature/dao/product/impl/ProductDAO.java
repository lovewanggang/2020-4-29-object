package com.nature.dao.product.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.product.IProductDAO;
import com.nature.model.product.Product;


/**
 * @description
 * @author len
 * @createDate 2018年4月19日
 */
@Repository
public class ProductDAO extends BaseDaoImpl<Product> implements IProductDAO {

	
	@Override
	public List<Product> queryHotProductByComId(Integer companyId) {
		String hql = "from Product where company.companyId = "+companyId+" and hot = 1";
		List<Product> products = find(hql);
		return products;
	}

	@Override
	public List<Product> queryProductById(Integer productClassId) {
		String hql = "from Product where productClass.productClassId = "+productClassId;
		List<Product> products;
		try {
			products = find(hql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return products;
	}

}
