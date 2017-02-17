package com.javabase.system.security;


import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * 基础登陆
 * @author rocky
 */
public class BaseLoginUrlAuthenticationPoint extends LoginUrlAuthenticationEntryPoint{

	// 登陆地址
	private final static String staticLoginFormUrl = "/background/login.html";  

	public BaseLoginUrlAuthenticationPoint() {
		super(staticLoginFormUrl);
	}
	
}
