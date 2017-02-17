package com.javabase.system.entity;

import com.application.base.core.obj.BaseBean;

/**
 * SystemServerInfo实体
 * 
 * @author 系统生成
 *
 */
public class SystemServerInfo extends BaseBean {

	private static final long serialVersionUID = 1L;
	/**表名*/
	public static final String tableName = "SYSTEM_SERVER_INFO";
	
	/**cpu内存*/
	private String  cpuUsage = null;
	/**cpu内存 对应的静态变量值*/
	public static final String FIELD_CPU_USAGE = "cpuUsage";
	/**设置cpu内存*/
	private String  setCpuUsage = null;
	/**设置cpu内存 对应的静态变量值*/
	public static final String FIELD_SET_CPU_USAGE = "setCpuUsage";
	/**jvm内存*/
	private String  jvmUsage = null;
	/**jvm内存 对应的静态变量值*/
	public static final String FIELD_JVM_USAGE = "jvmUsage";
	/**设置jvm内存*/
	private String  setJvmUsage = null;
	/**设置jvm内存 对应的静态变量值*/
	public static final String FIELD_SET_JVM_USAGE = "setJvmUsage";
	/**内存使用*/
	private String  ramUsage = null;
	/**内存使用 对应的静态变量值*/
	public static final String FIELD_RAM_USAGE = "ramUsage";
	/**设置内存使用*/
	private String  setRamUsage = null;
	/**设置内存使用 对应的静态变量值*/
	public static final String FIELD_SET_RAM_USAGE = "setRamUsage";
	/**管理的email*/
	private String  managerEmail = null;
	/**管理的email 对应的静态变量值*/
	public static final String FIELD_MANAGER_EMAIL = "managerEmail";
	/**系统备注*/
	private String  mark = null;
	/**系统备注 对应的静态变量值*/
	public static final String FIELD_MARK = "mark";

	public SystemServerInfo () {
		super();
	}
	
	public SystemServerInfo (String cpuUsage ,String setCpuUsage ,String jvmUsage ,String setJvmUsage ,String ramUsage ,String setRamUsage ,String managerEmail ,String mark ) {
		super();
		 this.cpuUsage = cpuUsage;
		 this.setCpuUsage = setCpuUsage;
		 this.jvmUsage = jvmUsage;
		 this.setJvmUsage = setJvmUsage;
		 this.ramUsage = ramUsage;
		 this.setRamUsage = setRamUsage;
		 this.managerEmail = managerEmail;
		 this.mark = mark;
	}
	
	public String getCpuUsage() {
		return cpuUsage;
	}
	public void setCpuUsage(String cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	public String getSetCpuUsage() {
		return setCpuUsage;
	}
	public void setSetCpuUsage(String setCpuUsage) {
		this.setCpuUsage = setCpuUsage;
	}
	public String getJvmUsage() {
		return jvmUsage;
	}
	public void setJvmUsage(String jvmUsage) {
		this.jvmUsage = jvmUsage;
	}
	public String getSetJvmUsage() {
		return setJvmUsage;
	}
	public void setSetJvmUsage(String setJvmUsage) {
		this.setJvmUsage = setJvmUsage;
	}
	public String getRamUsage() {
		return ramUsage;
	}
	public void setRamUsage(String ramUsage) {
		this.ramUsage = ramUsage;
	}
	public String getSetRamUsage() {
		return setRamUsage;
	}
	public void setSetRamUsage(String setRamUsage) {
		this.setRamUsage = setRamUsage;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
}
