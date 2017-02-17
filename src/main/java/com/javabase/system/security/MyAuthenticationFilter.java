package com.javabase.system.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.application.base.utils.common.StringDefaultValue;
import com.javabase.system.entity.SystemLog;
import com.javabase.system.entity.SystemUser;
import com.javabase.system.service.SystemLogService;
import com.javabase.system.service.SystemUserService;
import com.javabase.system.utils.CommonUtils;
import com.javabase.system.utils.MD5Util;

/**
 * 这个类主要是用户登录验证
 * 
 * admin
 * 
 * @version 1.0v
 */
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String USERNAME = "username";
	
	private static final String PASSWORD = "password";
	
	/**
	 * 登录成功后跳转的地址
	 */
	private String successUrl = "/background/index.html";
	/**
	 * 登录失败后跳转的地址
	 */
	private String errorUrl = "/background/login.html";
	
	@Autowired
	private SystemUserService systemUserService;
	
	@Autowired
	private SystemLogService systemLogService;
	
	/**
	 * 自定义表单参数的name属性，默认是 j_username 和 j_password
	 * 定义登录成功和失败的跳转地址
	 * @author amdin
	 */
	public void init() {
		logger.info(" ---------------  MyAuthenticationFilter init--------------- ");
		this.setUsernameParameter(USERNAME);
		this.setPasswordParameter(PASSWORD);
		// 验证成功，跳转的页面
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl(successUrl);
		this.setAuthenticationSuccessHandler(successHandler);

		// 验证失败，跳转的页面
		SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl(errorUrl);
		this.setAuthenticationFailureHandler(failureHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		logger.info(" ---------------  MyAuthenticationFilter attemptAuthentication--------------- ");
		
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}

		String username = request.getParameter(USERNAME);
		String password = request.getParameter(PASSWORD);
		logger.info(">>>>>>>>>>000<<<<<<<<<< username is " +username);
		if (StringDefaultValue.isEmpty(username) || StringDefaultValue.isEmpty(password)) {
			BadCredentialsException exception = new BadCredentialsException(
					"用户名或密码不能为空！");// 在界面输出自定义的信息！！
			throw exception;
		}

		// 验证用户账号与密码是否正确
		Map<String,Object> param = new HashMap<String, Object>();
		param.put(SystemUser.FIELD_USER_NAME, username);
		SystemUser systemUser = this.systemUserService.findObjectByPros(param);
		if (systemUser == null || ! systemUser.getUserPass().equals(MD5Util.encrypt(password))) {
			BadCredentialsException exception = new BadCredentialsException("用户名或密码不匹配！");// 在界面输出自定义的信息！！
			// request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			// exception);
			throw exception;
		}
		// 当验证都通过后，把用户信息放在session里
		request.getSession().setAttribute("userSession", systemUser);
		// 记录登录信息
		SystemLog systemLog = SystemLog.getInstance(SystemLog.class);
		systemLog.setUserId(systemUser.getId());
		systemLog.setUserName(systemUser.getUserName());
		systemLog.setLogType(2);
		systemLog.setFromIp(CommonUtils.toIpAddr(request));
		systemLog.setModule("background");
		systemLog.setAction("权限认证日志添加");
		systemLogService.saveObject(systemLog);
		
		// 实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
}
