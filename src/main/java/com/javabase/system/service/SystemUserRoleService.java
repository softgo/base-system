package com.javabase.system.service;

import com.javabase.system.entity.SystemUserRole;
import com.application.base.core.datasource.service.StrutsBaseService;
import com.application.base.core.exception.BusinessException;

/**
 * SystemUserRole实体
 * 
 * @author 系统生成
 *
 */
public interface SystemUserRoleService extends StrutsBaseService<SystemUserRole>{

	//通过用户id删除用户角色关系
	public boolean deleteSystemUserRoleByPorps(SystemUserRole userRole) throws BusinessException;
	
}
