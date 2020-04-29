package com.nature.dao.company.impl;

import java.util.List;

import javax.persistence.Entity;

import org.springframework.stereotype.Repository;

import com.nature.dao.base.impl.BaseDaoImpl;
import com.nature.dao.company.IModuleDAO;
import com.nature.model.module.Module_on_off;

@Repository
public class ModuleDAO extends BaseDaoImpl<Module_on_off> implements IModuleDAO {

	@Override
	public Module_on_off getModuleByComId(Integer companyId) {
		String hql = "from Module_on_off where company.companyId = "+companyId;
		List<Module_on_off> module_on_offs = find(hql);
		if (module_on_offs.size()>0) {
			return module_on_offs.get(0);
		}else {
			return null;
		}
		
	}

}
