package com.nature.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.From;

import org.springframework.stereotype.Service;

import com.alibaba.druid.stat.TableStat.Name;
import com.nature.dao.company.ICompanyAccountDAO;
import com.nature.dao.company.ICompanyDAO;
import com.nature.dao.company.IModuleDAO;
import com.nature.dao.manage.IProductVisitDAO;
import com.nature.dao.product.IProductBigClassDAO;
import com.nature.dao.product.IProductClassDAO;
import com.nature.dao.product.IProductDAO;
import com.nature.dao.product.IProductParamDAO;
import com.nature.dao.product.IProductParamerValueDAO;
import com.nature.model.company.Company;
import com.nature.model.company.CompanyAccount;
import com.nature.model.manage.ProductVisit;
import com.nature.model.module.Module_on_off;
import com.nature.model.product.Product;
import com.nature.model.product.ProductBigClass;
import com.nature.model.product.ProductClass;
import com.nature.model.product.ProductParamer;
import com.nature.model.product.ProductParamerValue;

/**
 * @description
 * @author len
 * @createDate 2018年4月19日
 */
@Service
public class ProductService {

	@Resource
	private ICompanyDAO companyDAO;
	@Resource
	private ICompanyAccountDAO companyAccountDAO;
	@Resource
	private IProductClassDAO productClassDAO;
	@Resource
	private IProductDAO productDAO;
	@Resource
	private IProductBigClassDAO productBigClassDAO;
	@Resource
	private IProductParamDAO productParamDAO;
	@Resource
	private IProductParamerValueDAO productParamerValueDAO;
	@Resource
	private IModuleDAO moduleDAO;
	@Resource
	private IProductVisitDAO productVisitDAO;
	
	public void detailProduct(Integer id, Map<String, Object> dataMap) {
		Product product = productDAO.get(Product.class,id);
		//增加热度
		product.setProductHot(product.getProductHot() + 1);
		productDAO.update(product);
		List<ProductParamerValue> productParamers = productParamerValueDAO.queryProductParamerByPID(id);
		List<Product> products = productDAO.queryProductById(product.getProductClass().getProductClassId());
		dataMap.put("product", product);
		dataMap.put("products", products);
		dataMap.put("productParamers", productParamers);
		System.out.println(product);
		System.out.println(products);
		System.out.println(productParamers);
	}
	public void findOneProduct(Integer id, Map<String, Object> dataMap) {
		Product product = productDAO.get(Product.class,id);
		List<ProductParamerValue> productValues = productParamerValueDAO.queryProductParamerByPID(id);
		dataMap.put("product", product);
		dataMap.put("productValues", productValues);
	}

	/**
	 * @param id
	 * @param dataMap
	 */
	public void findProductClassByBCid(Integer id, Map<String, Object> dataMap) {
		List<ProductClass> productClasses = productClassDAO.findProductClassByBCid(id);
		System.out.println("==========findProductClassByBCid中的ID=============="+id);
		System.out.println(productClasses);
		dataMap.put("productClasses", productClasses);
	}
	/**
	 * 
	 * 获取大类列表
	 * 获取第一个大类的产品列表
	 * @param sCcode
	 * @param dataMap
	 */
	public void productHomePage(String sCcode, Map<String, Object> dataMap) {
		CompanyAccount companyAccount = companyAccountDAO.get(CompanyAccount.class, sCcode);
		Company company = companyAccount.getCompany();
		List<ProductBigClass> productBigClasses = productBigClassDAO.queryBigClassByComId(company.getCompanyId());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>获取大类列表>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(sCcode);
		System.out.println(companyAccount.getCompanyAccountId());
		System.out.println(company.getCompanyId());
		System.out.println(productBigClasses);
		System.out.println(productBigClasses.size());
		System.out.println(productBigClasses.get(0).getProductBigClassId());
		if (productBigClasses.size()>0) {
			List<ProductClass> productClasses = productBigClasses.get(0).getProductclasses();
			dataMap.put("productBigClasses", productBigClasses);
			dataMap.put("productClasses", productClasses);
		}else {
			dataMap.put("productBigClasses", null);
			dataMap.put("productClasses", null);
		}
	}
	
	public String testSpringTest(String testStr){
		return testStr;
	}
	
	public Product getProduct(Integer id){
		Product product = productDAO.get(Product.class, id);
		product.setHot(product.getHot()+1);//增加热度
		productDAO.update(product);
		return product;
	}

	//推荐列表
	public List<Product> queryTuijianProduct(){
		String hql = "from product where tuijian  = 0";
		return productDAO.find(hql);
	}
	
	/**
	 * 搜索，分类或者商品编号
	 * @param dataMap 
	 * @param companyId
	 * @return
	 */
	public void searchProduct(String str, Map<String, Object> dataMap){
		String hql = "from Product where productNum like '%"+str+"%' or productClass.pcName like '%"+str+"%' or productClass.productBigClass.bigClassName like '%"+str+"%'";
		List<Product> products = productDAO.find(hql);
		System.out.println("=========products.size()=========="+products.size());
		dataMap.put("products",products);
		
	}
	
	/**
	 * 获取产品类别
	 * @param companyId
	 * @return
	 */
	public List<ProductClass> queryProductClassByCom(Integer companyId){
		String hql = "from ProductClass where company.companyId = "+companyId;
		return productClassDAO.find(hql);
	}
	
	public List<Product> queryProductByPCId(Integer productClassId){
		String hql = "from Product where productClass.productClassId = " + productClassId;
		return productDAO.find(hql);
	}
	//相关推荐
	public List<Product> recommend(Integer productId){
		Product product = productDAO.get(Product.class, productId);
		String hql = "from Product where productClass.productClassId = "+product.getProductClass().getProductClassId();
		List<Product> products = productDAO.find(hql);
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getProductId() == productId) {
				products.remove(i);
			}
		}
		return products;
	}

	
	/*********************************** setter and getter ****************************************/

	
	public ICompanyDAO getCompanyDAO() {
		return companyDAO;
	}

	public IProductBigClassDAO getProductBigClassDAO() {
		return productBigClassDAO;
	}

	public ICompanyAccountDAO getCompanyAccountDAO() {
		return companyAccountDAO;
	}

	public void setCompanyAccountDAO(ICompanyAccountDAO companyAccountDAO) {
		this.companyAccountDAO = companyAccountDAO;
	}

	public IProductParamerValueDAO getProductParamerValueDAO() {
		return productParamerValueDAO;
	}

	public void setProductParamerValueDAO(IProductParamerValueDAO productParamerValueDAO) {
		this.productParamerValueDAO = productParamerValueDAO;
	}

	public void setProductBigClassDAO(IProductBigClassDAO productBigClassDAO) {
		this.productBigClassDAO = productBigClassDAO;
	}

	public IProductClassDAO getProductClassDAO() {
		return productClassDAO;
	}

	public IProductParamDAO getProductParamDAO() {
		return productParamDAO;
	}

	public void setProductParamDAO(IProductParamDAO productParamDAO) {
		this.productParamDAO = productParamDAO;
	}

	public IProductDAO getProductDAO() {
		return productDAO;
	}

	public void setCompanyDAO(ICompanyDAO companyDAO) {
		this.companyDAO = companyDAO;
	}

	public void setProductClassDAO(IProductClassDAO productClassDAO) {
		this.productClassDAO = productClassDAO;
	}

	public void setProductDAO(IProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	/**
	 * @param sessionAccount
	 * @param bigClass
	 * @param str
	 * @param str2
	 */
	public void addBigClass(Integer companyId, ProductBigClass bigClass, String params, ProductClass productClass) {
		System.out.println(bigClass);
		System.out.println(productClass);
		Company company = companyDAO.get(Company.class, companyId);
		bigClass.setCompany(company);
		bigClass.setBigClassCreateTime(new Date());
		productBigClassDAO.save(bigClass);
		
		String[] pParams = params.split(",");
		ProductParamer param = null;
		for (int i = 0; i < pParams.length; i++) {
			param = new ProductParamer();
			param.setParamerName(pParams[i]);
			param.setProductBigClass(bigClass);
			param.setSequence(i);
			productParamDAO.save(param);
		}
		
		if (productClass != null) {
			if (productClass.getPcName() != null && productClass.getPcName().length() > 0 && !(productClass.getPcName().equals("null"))) {
				productClass.setCompany(company);
				productClass.setProductBigClass(bigClass);
				productClassDAO.save(productClass);
			}
		}
		
		
	}

	/**
	 * @param sessionAccount
	 * @return
	 */
	public List<ProductBigClass> queryProClass(Integer companyId) {
		List<ProductBigClass> bigClasses = productBigClassDAO.queryProClass(companyId);
		return bigClasses;
	}

	/**
	 * @param sessionAccount
	 * @param id
	 * @param productClass
	 */
	public void queryProClass(Integer companyId, Integer bigClassId, ProductClass productClass) {
		Company company = companyDAO.get(Company.class, companyId);
		ProductBigClass bigClass = productBigClassDAO.get(ProductBigClass.class, bigClassId);
		productClass.setCompany(company);
		productClass.setProductBigClass(bigClass);
		productClassDAO.save(productClass);
		
	}

	/**
	 * @param id
	 * @return
	 */
	public ProductBigClass findOneBigClass(Integer id) {
		ProductBigClass bigClass = productBigClassDAO.get(ProductBigClass.class, id);
		return bigClass;
	}

	public void editBigClass(ProductBigClass bigClass, String str) {
		ProductBigClass bc = productBigClassDAO.get(ProductBigClass.class, bigClass.getProductBigClassId());
		bc.setBigClassName(bigClass.getBigClassName());
		bc.setStatus(bigClass.getStatus());
		productBigClassDAO.update(bc);
		
		List<ProductParamer> productParamers = bc.getProductParamer();
		String[] params = str.split(",");
		
		System.out.println(str);
		System.out.println(productParamers);
		//添加新添加的
		if (params != null && params.length > 0) {
			ProductParamer pp = null;
			for (int i = 0; i < params.length; i++) {
				boolean isNew = true;
				for (int j = 0; j < productParamers.size(); j++) {
					if (params[i].equals(productParamers.get(j).getParamerName())) {
						isNew = false;
						productParamers.get(j).setSequence(i);
						productParamDAO.update(productParamers.get(j));
					}
				}
				if (isNew) {
					pp = new ProductParamer();
					pp.setProductBigClass(bc);
					pp.setParamerName(params[i]);
					pp.setSequence(i);
					productParamDAO.save(pp);
				}
				
			}
		}
		
		//刪除已經刪除的
		if(productParamers != null && productParamers.size() > 0){
			for (int i = 0; i < productParamers.size(); i++) {
				boolean isDel = true;
				for (int j = 0; j < params.length; j++) {
					if (productParamers.get(i).getParamerName().equals(params[j])) {
						isDel = false;
					}
				}
				if (isDel) {
					productParamDAO.delete(productParamers.get(i));
				}
			}
		}
		
	}

	public void updateBigClassStatus(Integer id, Integer status) {
		ProductBigClass bc = productBigClassDAO.get(ProductBigClass.class, id);
		bc.setStatus(status);
		productBigClassDAO.update(bc);
	}

	public void updateClassStatus(Integer id, Integer status) {
		ProductClass productClass = productClassDAO.get(ProductClass.class,id);
		productClass.setStatus(status);
		productClassDAO.update(productClass);
		
	}

	public void delBigClass(Integer bigClassId) {
		String valueHql = "from ProductParamerValue where productParamer.productBigClass.productBigClassId = "+bigClassId; 
		List<ProductParamerValue> values = productParamerValueDAO.find(valueHql);
		for (ProductParamerValue productParamerValue : values) {
			productParamerValueDAO.delete(productParamerValue);
		}
		String paramHql = "from ProductParamer where productBigClass.productBigClassId = "+bigClassId;
		List<ProductParamer> paramers = productParamDAO.find(paramHql);
		for (ProductParamer productParamer : paramers) {
			productParamDAO.delete(productParamer);
		}
		String productHql = "from Product where productClass.productBigClass.productBigClassId = "+bigClassId;
		List<Product> products = productDAO.find(productHql);
		String productVisHql = "from ProductVisit where product.productId =";
		List<ProductVisit> pvs = null;
		for (Product product : products) {
			pvs = productVisitDAO.find(productVisHql+product.getProductId());
			for (ProductVisit productVisit : pvs) {
				productVisit.setProduct(null);
				productVisitDAO.update(productVisit);
			}
			productDAO.delete(product);
		}
		String productClassHql = "from ProductClass where productBigClass.productBigClassId = "+bigClassId;
		List<ProductClass> productClasses = productClassDAO.find(productClassHql);
		for (ProductClass productClass : productClasses) {
			productClassDAO.delete(productClass);
		}
		productBigClassDAO.delete(productBigClassDAO.get(ProductBigClass.class, bigClassId));
	}
	
	public void delClass(Integer classId) {
		String valueHql = "from ProductParamerValue where product.productClass.productClassId = "+classId; 
		List<ProductParamerValue> values = productParamerValueDAO.find(valueHql);
		for (ProductParamerValue productParamerValue : values) {
			productParamerValueDAO.delete(productParamerValue);
		}
		String productHql = "from Product where productClass.productClassId = "+classId; 
		List<Product> products = productDAO.find(productHql);
		String productVisHql = "from ProductVisit where product.productId =";
		List<ProductVisit> pvs = null;
		for (Product product : products) {
			pvs = productVisitDAO.find(productVisHql+product.getProductId());
			for (ProductVisit productVisit : pvs) {
				productVisit.setProduct(null);
				productVisitDAO.update(productVisit);
			}
			productDAO.delete(product);
		}
		productClassDAO.delete(productClassDAO.get(ProductClass.class, classId));
		
	}

	public ProductClass findOneProductClass(Integer id) {
		return productClassDAO.get(ProductClass.class, id);
	}

	public void editProductClass(ProductClass productClass) {
		ProductClass pClass = productClassDAO.get(ProductClass.class, productClass.getProductClassId());
		pClass.setPcClassPic(productClass.getPcClassPic());
		pClass.setPcName(productClass.getPcName());
		productClassDAO.update(pClass);
	}

	public List<ProductBigClass> queryClassInfo(Integer companyId) {
		String hql = "from ProductBigClass where company.companyId = "+companyId;
		return productBigClassDAO.find(hql);
	}

	/**
	 * @param product
	 * @param id
	 * @param id2
	 * @param strs
	 * @param str2
	 */
	public void addProduct(Product product, Integer bigClassId, Integer classId, String paramStr, String valueStr) {
		ProductClass productClass = productClassDAO.get(ProductClass.class, classId);
		if (product.getProductPic1().length() < 20) {
			product.setProductPic1(null);
		}
		if (product.getProductPic2().length() < 20) {
			product.setProductPic2(null);
		}
		if (product.getProductPic3().length() < 20) {
			product.setProductPic3(null);
		}
		if (product.getProductPic4().length() < 20) {
			product.setProductPic4(null);
		}
		if (product.getProductPic5().length() < 20) {
			product.setProductPic5(null);
		}
		product.setCompany(productClass.getCompany());
		product.setProductClass(productClass);
		product.setCreateTime(new Date());
		product.setProductHot(0);
		productDAO.save(product);
		
		ProductBigClass bigClass = productBigClassDAO.get(ProductBigClass.class, bigClassId);
		String[] params = paramStr.split(",");
		String[] values = valueStr.split(",");
		ProductParamer productParamer = null;
		ProductParamerValue value = null;
		for (int i = 0; i < params.length; i++) {
			productParamer = productParamDAO.get(ProductParamer.class, Integer.parseInt(params[i]));
			value = new ProductParamerValue();
			value.setProduct(product);
			value.setProductParamer(productParamer);
			value.setProductValue(values[i]);
			productParamerValueDAO.save(value);
		}
		
		
		
	}

	/**
	 * @param sessionAccount
	 * @return
	 */
	public List<Product> listProduct(Integer companyId,Map<String, Object> dataMap) {
		String hql2 = "from Module_on_off where company.companyId = "+companyId;
		Module_on_off onoff = moduleDAO.find(hql2).get(0);
		if (onoff.isComProduct_on_off()) {
			dataMap.put("onoff", 1);
		}else {
			dataMap.put("onoff", 0);
		}
		String hql = "from Product where company.companyId = " + companyId + "order by hot desc,createTime desc";
		return productDAO.find(hql);
	}

	/**
	 * @param id
	 */
	public void delProduct(Integer id) {
		String hql = "from ProductParamerValue where product.productId ="+id;
		List<ProductParamerValue> values=productParamerValueDAO.find(hql);
		for (ProductParamerValue productParamerValue : values) {
			productParamerValueDAO.delete(productParamerValue);
		}
		productDAO.delete(productDAO.get(Product.class, id));
	}

	/**
	 * @param str
	 * @param i
	 */
	public void updateProduct(String str, int status) {
		String [] ids = str.split(",");
		Product product = null;
		for (int i = 0; i < ids.length; i++) {
			product = productDAO.get(Product.class, Integer.parseInt(ids[i]));
			product.setStatus(status);
			productDAO.update(product);
		}
	}

	/**
	 * @return
	 */
	public int queryHotProductCount(Integer companyId) {
		String hql = "from Product where company.companyId = "+companyId+" and hot = 1";
		return productDAO.find(hql).size();
	}
	
	public void updateProduct(Product product, Integer bigClassId, Integer classId, String paramStr, String valueStr) {
		System.out.println(">>>>>>>>>>product>>>>>>>>"+product);
		Product p = productDAO.get(Product.class, product.getProductId());
		
		if (product.getProductPic1().length() < 20) {
			p.setProductPic1(null);
		}else {
			p.setProductPic1(product.getProductPic1());
		}
		if (product.getProductPic2().length() < 20) {
			p.setProductPic2(null);
		}else {
			p.setProductPic2(product.getProductPic2());
		}
		if (product.getProductPic3().length() < 20) {
			p.setProductPic3(null);
		}else {
			p.setProductPic3(product.getProductPic3());
		}
		if (product.getProductPic4().length() < 20) {
			p.setProductPic4(null);
		}else {
			p.setProductPic4(product.getProductPic4());
		}
		if (product.getProductPic5().length() < 20) {
			p.setProductPic5(null);
		}else {
			p.setProductPic5(product.getProductPic5());
		}
		p.setProductDetail(product.getProductDetail());
		p.setProductNum(product.getProductNum());
		p.setHot(product.getHot());
		p.setStatus(product.getStatus());
		p.setShowProduct(product.getShowProduct());
		ProductClass productClass = productClassDAO.get(ProductClass.class, classId);
		p.setProductClass(productClass);
		productDAO.update(p);
		
		String[] values = valueStr.split(",");
		
		String paramHql = "from ProductParamer where productBigClass.productBigClassId = "+bigClassId;
		List<ProductParamer> params = productParamDAO.find(paramHql);
		String valueHql = null;
		ProductParamerValue value = null;
		for (int i=0;i<params.size();i++) {
			valueHql = "from ProductParamerValue where product.productId = "+product.getProductId()+" and productParamer.productId = "+params.get(i).getProductId();
			List<ProductParamerValue> values2 = productParamerValueDAO.find(valueHql);
			if (values2.size() > 0) {
				value = values2.get(0);
			}else{
				value = new ProductParamerValue();
				value.setProduct(p);
				value.setProductParamer(params.get(i));
			}
			//小类变没变
			if (p.getProductClass().getProductClassId() != classId) {
				
				//大类变没变
				if (p.getProductClass().getProductBigClass().getProductBigClassId() != bigClassId) {//大类变了，所以删除原来的value，然后重新new value
					productParamerValueDAO.delete(value);
					value = new ProductParamerValue();
					value.setProduct(p);
					value.setProductParamer(params.get(i));
					value.setProductValue(values[i]);
					productParamerValueDAO.save(value);
				}else {//大类没变，把原来的value值改了就行
					value.setProductValue(values[i]);
					productParamerValueDAO.saveOrUpdate(value);
				}
			}else {
				value.setProductValue(values[i]);
				productParamerValueDAO.saveOrUpdate(value);
			}
			
		}
		
	}
	
	
	
	
}
