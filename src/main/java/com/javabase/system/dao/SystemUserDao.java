package com.javabase.system.dao;

import com.javabase.system.entity.SystemUser;
import com.application.base.core.datasource.dao.StrutsBaseDao;
import com.application.base.core.exception.BusinessException;

/**
 * SystemUser实体
 * 
 * @author 系统生成
 *
 */
public interface SystemUserDao extends StrutsBaseDao<SystemUser>{
	
	//统计用户名和密码对应多少个用户.
	public int countUser(String userName,String userPass,String factoryTag) throws BusinessException;

}
