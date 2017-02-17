package com.javabase.system.dao;

import com.javabase.system.entity.SystemSource;

import java.util.List;

import com.application.base.core.datasource.dao.StrutsBaseDao;
import com.application.base.core.exception.BusinessException;

/**
 * SystemSource实体
 * 
 * @author 系统生成
 *
 */
public interface SystemSourceDao extends StrutsBaseDao<SystemSource> {

	// 根据用户Id获取该用户的权限
	public List<SystemSource> getSystemSourcesByUserId(Integer userId,String factoryTag) throws BusinessException ;

	// 根据角色Id获取该角色的权限
	public List<SystemSource> getSystemSourcesByRoleId(Integer roleId,String factoryTag) throws BusinessException ;

	// 根据用户名获取该用户的权限
	public List<SystemSource> getSystemSourcesByUserName(String userName,String factoryTag) throws BusinessException ;
	
}
