package com.javabase.system.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.base.core.common.BaseController;
import com.application.base.utils.common.VerifyImageUtil;
import com.application.base.utils.page.Common;
import com.javabase.system.entity.SystemLog;
import com.javabase.system.entity.SystemRole;
import com.javabase.system.entity.SystemSource;
import com.javabase.system.entity.SystemUser;
import com.javabase.system.service.SystemLogService;
import com.javabase.system.service.SystemRoleService;
import com.javabase.system.service.SystemSourceService;
import com.javabase.system.service.SystemUserService;
import com.javabase.system.utils.CommonUtils;
import com.javabase.system.utils.MD5Util;


/**
 * 进行管理后台框架界面的类.(到background文件夹下去)
 * 
 * @author admin
 * 
 * @version 1.0v
 */
@Controller
@RequestMapping("/background/")
public class BackgroundController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SystemRoleService systemRoleService;
	@Autowired
	private SystemSourceService systemSourceService;
	@Autowired
	private SystemLogService systemLogService;
	@Autowired
	private AuthenticationManager myAuthenticationManager;
	
	/**
	 * 验证码
	 * 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("code")
	public void loginIn(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//VerifyImageUtil.createVerify(request, response,"hzzm");
		VerifyImageUtil.createEasy(request, response, "zm");
	}
	
	/**
	 * @return
	 */
	@RequestMapping("loginOut")
	public String loginOut(Model model, HttpServletRequest request) {
		// 重新登录时销毁该用户的Session
		Object object = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if (object!=null ) {
			request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
		}
		SystemUser systemUser = (SystemUser) request.getSession().getAttribute("userSession");
		if (systemUser!=null) {
			request.getSession().removeAttribute("userSession");
		}
		SystemRole systemRole = (SystemRole) request.getSession().getAttribute("roleSession");
		if (systemRole!=null) {
			request.getSession().removeAttribute("roleSession");
		}
		// session 失效.
		request.getSession().invalidate();
		//登出验证设置..
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:index.html";
	}

	/**
	 * 转到登陆去
	 * @return
	 */
	@RequestMapping("login")
	public String login(Model model, HttpServletRequest request) {
		// 重新登录时销毁该用户的Session
		Object object = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if ( object != null) {
			request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
		}else {
			Cookie[] cookies = request.getCookies();
			for (int i = 0; cookies != null && i < cookies.length; i++) {
				if (cookies[i].getName().equals("loginName")) {
					request.setAttribute("error", "登录超时,请按F5手动刷新,再次登录!");
				}
			}
		}
		return "/background/framework/login";
	}

	/**
	 * 登陆操作
	 * @param username
	 * @param password
	 * @param autocode
	 * @param request
	 * @return
	 */
	@RequestMapping("loginCheck")
	public String loginCheck(String username, String password,String autocode,HttpServletRequest request) {
		try {
			if (!request.getMethod().equals("POST")) {
				request.setAttribute("error", "支持POST方法提交!");
			}
			if (Common.isEmpty(username) || Common.isEmpty(password)) {
				request.setAttribute("error", "用户名或密码不能为空!");
				return "/background/framework/login";
			}
			String sessionCode = (String) request.getSession().getAttribute("AutoCode"); 
			if (!sessionCode.equalsIgnoreCase(autocode)) {
				request.setAttribute("error", "验证码输入有误!");
				return "/background/framework/login";
			}
			
			//验证用户账号与密码是否正确
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(SystemUser.FIELD_USER_NAME, username);
			SystemUser user = systemUserService.findObjectByPros(param);
			password = MD5Util.encrypt(password);
			if (user == null || ! user.getUserPass().equals(password)) {
				request.setAttribute("error", "用户名或密码不正确!");
				return "/background/framework/login";
			}
			
			//认证机制...
			Authentication authentication = myAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
			//设置
			SecurityContext securityContext = SecurityContextHolder.getContext();
			securityContext.setAuthentication(authentication);
			
			HttpSession session = request.getSession(false);
			session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

			// 当验证都通过后，把用户信息放在session里
			request.getSession().setAttribute("userSession", user);
			//加到cookie中去.
			Cookie userCookie = new Cookie("loginName", username);
			userCookie.setMaxAge(3610);//一个小时.
			response.addCookie(userCookie);
			//常量设值.
			param = new HashMap<String, Object>();
			param.put(SystemRole.FIELD_ROLE_NAME, username);
			SystemRole systemRole = this.systemRoleService.findObjectByPros(param);
			if (systemRole != null) {
				request.getSession().setAttribute("roleSession", systemRole);
			}
			// 记录登录信息
			SystemLog systemLog = SystemLog.getInstance(SystemLog.class);
			systemLog.setUserId(user.getId());
			systemLog.setUserName(user.getUserName());
			systemLog.setLogType(1);
			systemLog.setFromIp(CommonUtils.toIpAddr(request));
			systemLog.setModule("background");
			systemLog.setAction("登陆日志添加");
			systemLogService.saveObject(systemLog);
		}
		catch (AuthenticationException ae) {
			request.setAttribute("error", "登录异常，请联系管理员！");
			logger.error("登陆异常:{}",ae);
			return "/background/framework/login";
		}

		return "redirect:index.html";
	}

	/**
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model) {
		return "/background/framework/main";
	}

	@RequestMapping("top")
	public String top(Model model) {
		return "/background/framework/top";
	}

	@RequestMapping("left")
	public String left(Model model, HttpServletRequest request) {
		try {
			SecurityContextImpl securityContextImpl = (SecurityContextImpl) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
			// 获得访问地址
			List<SystemSource> resources = systemSourceService.getSystemSourcesByUserName(securityContextImpl.getAuthentication().getName());
			model.addAttribute("resources", resources);
		}
		catch (Exception e) {
			// 重新登录时销毁该用户的Session
			e.printStackTrace();
			request.getSession().removeAttribute("SPRING_SECURITY_CONTEXT");
			return "/background/framework/login";
		}
		return "/background/framework/left";
	}

	@RequestMapping("tab")
	public String tab(Model model) {
		return "/background/framework/tab/tab";
	}

	@RequestMapping("center")
	public String center(Model model) {
		return "/background/framework/center";
	}

}
