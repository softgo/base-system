package com.javabase.system.entity;

import com.application.base.core.obj.BaseBean;

/**
 * SystemUser实体
 * 
 * @author 系统生成
 *
 */
public class SystemUser extends BaseBean {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "SYSTEM_USER";
	
	/**系统用户父级id*/
	private int  parentId = -1;
	/**系统用户父级id 对应的静态变量值*/
	public static final String FIELD_PARENT_ID = "parentId";
	/**系统用户的名字*/
	private String  userName = null;
	/**系统用户的名字 对应的静态变量值*/
	public static final String FIELD_USER_NAME = "userName";
	
	/**系统角色的名字*/
	private String  roleName = null;
	
	/**系统登陆密码*/
	private String  userPass = null;
	/**系统登陆密码 对应的静态变量值*/
	public static final String FIELD_USER_PASS = "userPass";
	/**用户的性别: 1:男,0:女*/
	private int  userSex = -1;
	/**用户的性别: 1:男,0:女 对应的静态变量值*/
	public static final String FIELD_USER_SEX = "userSex";
	/**住址*/
	private String  userAddress = null;
	/**住址 对应的静态变量值*/
	public static final String FIELD_USER_ADDRESS = "userAddress";
	/**手机号码*/
	private String  userPhone = null;
	/**手机号码 对应的静态变量值*/
	public static final String FIELD_USER_PHONE = "userPhone";
	/**邮箱地址*/
	private String  userMail = null;
	/**邮箱地址 对应的静态变量值*/
	public static final String FIELD_USER_MAIL = "userMail";
	/**联系qq*/
	private String  userQq = null;
	/**联系qq 对应的静态变量值*/
	public static final String FIELD_USER_QQ = "userQq";
	/**用户状态;1:启用,0:弃用*/
	private int  status = -1;
	/**用户状态;1:启用,0:弃用 对应的静态变量值*/
	public static final String FIELD_STATUS = "status";

	public SystemUser () {
		super();
	}
	
	public SystemUser (int parentId ,String userName ,String userPass ,int userSex ,String userAddress ,String userPhone ,String userMail ,String userQq ,int status ) {
		super();
		 this.parentId = parentId;
		 this.userName = userName;
		 this.userPass = userPass;
		 this.userSex = userSex;
		 this.userAddress = userAddress;
		 this.userPhone = userPhone;
		 this.userMail = userMail;
		 this.userQq = userQq;
		 this.status = status;
	}
	
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public int getUserSex() {
		return userSex;
	}
	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserQq() {
		return userQq;
	}
	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
