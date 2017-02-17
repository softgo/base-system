package com.javabase.system.entity;

import com.application.base.core.obj.BaseBean;

/**
 * SystemUserRole实体
 * 
 * @author 系统生成
 *
 */
public class SystemUserRole extends BaseBean {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "SYSTEM_USER_ROLE";
	
	/**系统用户的id*/
	private int  userId = -1;
	/**系统用户的id 对应的静态变量值*/
	public static final String FIELD_USER_ID = "userId";
	/**系统角色的id*/
	private int  roleId = -1;
	/**系统角色的id 对应的静态变量值*/
	public static final String FIELD_ROLE_ID = "roleId";

	public SystemUserRole () {
		super();
	}
	
	public SystemUserRole (int userId ,int roleId ) {
		super();
		 this.userId = userId;
		 this.roleId = roleId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
