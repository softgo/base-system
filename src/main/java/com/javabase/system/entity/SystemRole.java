package com.javabase.system.entity;

import com.application.base.core.obj.BaseBean;

/**
 * SystemRole实体
 * 
 * @author 系统生成
 *
 */
public class SystemRole extends BaseBean {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "SYSTEM_ROLE";
	
	/**角色父级id*/
	private int  parentId = -1;
	/**角色父级id 对应的静态变量值*/
	public static final String FIELD_PARENT_ID = "parentId";
	/**角色名字*/
	private String  roleName = null;
	/**角色名字 对应的静态变量值*/
	public static final String FIELD_ROLE_NAME = "roleName";
	/**角色key*/
	private String  roleKey = null;
	/**角色key 对应的静态变量值*/
	public static final String FIELD_ROLE_KEY = "roleKey";
	/**角色介绍*/
	private String  description = null;
	/**角色介绍 对应的静态变量值*/
	public static final String FIELD_DESCRIPTION = "description";
	/**是否启用*/
	private int status = -1;
	/**角色父级id 对应的静态变量值*/
	public static final String FIELD_STATUS = "status";
	
	public SystemRole () {
		super();
	}
	
	public SystemRole (int parentId ,String roleName ,String roleKey ,String description,int status ) {
		super();
		 this.parentId = parentId;
		 this.roleName = roleName;
		 this.roleKey = roleKey;
		 this.description = description;
		 this.status = status;
	}
	
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleKey() {
		return roleKey;
	}
	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
