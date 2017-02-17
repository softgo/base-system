package com.javabase.system.service;

import com.javabase.system.entity.SystemSourceRole;
import com.application.base.core.datasource.service.StrutsBaseService;
import com.application.base.core.exception.BusinessException;

/**
 * SystemSourceRole实体
 * 
 * @author 系统生成
 *
 */
public interface SystemSourceRoleService extends StrutsBaseService<SystemSourceRole>{
	
		//通过角色id删除角色关系对应表
		public boolean deleteSystemSourceRoleByRoleId(Integer roleId) throws BusinessException;
		
}
