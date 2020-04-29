package com.nature.dao.company;

import com.nature.dao.base.BaseDaoI;
import com.nature.model.module.Module_on_off;

public interface IModuleDAO extends BaseDaoI<Module_on_off> {

	/**
	 * @return
	 */
	Module_on_off getModuleByComId(Integer companyId);

}
