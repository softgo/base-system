package com.javabase.system.dao;

import com.javabase.system.entity.SystemUserRole;
import com.application.base.core.datasource.dao.StrutsBaseDao;
import com.application.base.core.exception.BusinessException;

/**
 * SystemUserRole实体
 * 
 * @author 系统生成
 *
 */
public interface SystemUserRoleDao extends StrutsBaseDao<SystemUserRole>{
	
	//通过用户id,角色id 删除用户角色关系
	public boolean deleteSystemUserRoleByPorps(SystemUserRole userRole,String factoryTag) throws BusinessException;
	
}
