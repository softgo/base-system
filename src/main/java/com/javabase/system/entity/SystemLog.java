package com.javabase.system.entity;

import com.application.base.core.obj.BaseBean;

/**
 * SystemLog实体
 * 
 * @author 系统生成
 *
 */
public class SystemLog extends BaseBean {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "SYSTEM_LOG";
	
	/**系统登陆用户id*/
	private int  userId = -1;
	/**系统登陆用户id 对应的静态变量值*/
	public static final String FIELD_USER_ID = "userId";
	/**系统登陆用户名*/
	private String  userName = null;
	/**系统登陆用户名 对应的静态变量值*/
	public static final String FIELD_USER_NAME = "userName";
	/**登陆ip*/
	private String  fromIp = null;
	/**登陆ip 对应的静态变量值*/
	public static final String FIELD_FROM_IP = "fromIp";
	/**日志状态;1:可见,0:不可见*/
	private int  status = -1;
	/**日志状态;1:可见,0:不可见 对应的静态变量值*/
	public static final String FIELD_STATUS = "status";
	/**日志类型;1:登陆,2:系统*/
	private int  logType = -1;
	/**日志类型;1:登陆,2:系统 对应的静态变量值*/
	public static final String FIELD_LOG_TYPE = "logType";
	/**操作哪个模块*/
	private String  module = null;
	/**操作哪个模块 对应的静态变量值*/
	public static final String FIELD_MODULE = "module";
	/**操作详述*/
	private String  action = null;
	/**操作详述 对应的静态变量值*/
	public static final String FIELD_ACTION = "action";

	public SystemLog () {
		super();
	}
	
	public SystemLog (int userId ,String userName ,String fromIp ,int status ,int logType ,String module ,String action ) {
		super();
		 this.userId = userId;
		 this.userName = userName;
		 this.fromIp = fromIp;
		 this.status = status;
		 this.logType = logType;
		 this.module = module;
		 this.action = action;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFromIp() {
		return fromIp;
	}
	public void setFromIp(String fromIp) {
		this.fromIp = fromIp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getLogType() {
		return logType;
	}
	public void setLogType(int logType) {
		this.logType = logType;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
