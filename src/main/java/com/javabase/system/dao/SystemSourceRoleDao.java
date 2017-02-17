package com.javabase.system.dao;

import com.javabase.system.entity.SystemSourceRole;
import com.application.base.core.datasource.dao.StrutsBaseDao;
import com.application.base.core.exception.BusinessException;

/**
 * SystemSourceRole实体
 * 
 * @author 系统生成
 *
 */
public interface SystemSourceRoleDao extends StrutsBaseDao<SystemSourceRole>{
	
	//通过角色id删除角色关系对应表
	public boolean deleteSystemSourceRoleByRoleId(Integer roleId,String factoryTag) throws BusinessException;
	
}
