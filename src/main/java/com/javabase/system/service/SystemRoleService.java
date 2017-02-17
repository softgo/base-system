package com.javabase.system.service;

import com.javabase.system.entity.SystemRole;

import java.util.List;

import com.application.base.core.datasource.service.StrutsBaseService;
import com.application.base.core.exception.BusinessException;

/**
 * SystemRole实体
 * 
 * @author 系统生成
 *
 */
public interface SystemRoleService extends StrutsBaseService<SystemRole>{

	/**
	 * 通过用户id获取角色.
	 * @param userId
	 * @return list
	 * @throws BusinessException
	 */
	public List<SystemRole> findSystemRolesByUserId(Integer userId) throws BusinessException ;

}
