package com.javabase.system.dao;

import com.javabase.system.entity.SystemRole;

import java.util.List;

import com.application.base.core.datasource.dao.StrutsBaseDao;
import com.application.base.core.exception.BusinessException;

/**
 * SystemRole实体
 * 
 * @author 系统生成
 *
 */
public interface SystemRoleDao extends StrutsBaseDao<SystemRole>{
	
	/**
	 * 通过用户id获取角色.
	 * @param userId
	 * @param factoryTag
	 * @return list
	 * @throws BusinessException
	 */
	public List<SystemRole> findSystemRolesByUserId(Integer userId,String factoryTag) throws BusinessException ;

}
