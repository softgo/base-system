package com.javabase.system.service;

import com.javabase.system.entity.SystemSource;

import java.util.List;

import com.application.base.core.datasource.service.StrutsBaseService;

/**
 * SystemSource实体
 * 
 * @author 系统生成
 *
 */
public interface SystemSourceService extends StrutsBaseService<SystemSource>{

	// 根据用户Id获取该用户的权限
	public List<SystemSource> getSystemSourcesByUserId(Integer userId);

	// 根据角色Id获取该角色的权限
	public List<SystemSource> getSystemSourcesByRoleId(Integer roleId);

	// 根据用户名获取该用户的权限
	public List<SystemSource> getSystemSourcesByUserName(String userName);

}
