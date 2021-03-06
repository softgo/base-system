package com.javabase.system.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javabase.system.entity.SystemSource;
import com.javabase.system.entity.SystemUser;
import com.javabase.system.service.SystemSourceService;
import com.javabase.system.service.SystemUserService;


/**
 * User userdetail该类实现 UserDetails 接口，该类在验证成功后会被保存在当前回话的principal对象中
 * 
 * 获得对象的方式：
 * WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * 
 * 或在JSP中：
 * <sec:authentication property="principal.username"/>
 * 
 * 如果需要包括用户的其他属性，可以实现 UserDetails 接口中增加相应属性即可
 * 权限验证类
 * 
 * @author admin
 * 
 * @version 1.0v
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private SystemUserService systemUserService;
	
	@Autowired
	private SystemSourceService systemSourceService ;
	
	// 登录验证
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//取得用户的权限
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(SystemUser.FIELD_USER_NAME, username);
		SystemUser systemUser = systemUserService.findObjectByPros(param);
		if  (systemUser==null)  {
            throw new UsernameNotFoundException(username+" not exist!");
		}
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(systemUser);
		// 封装成spring security的user
		User userdetail = new User(
				systemUser.getUserName(), 
				systemUser.getUserPass(),
				true, 
				true, 
				true,
				true, 
				grantedAuths	//用户的权限
			);
		return userdetail;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(SystemUser systemUser) {
		List<SystemSource> resources = systemSourceService.getSystemSourcesByUserId(systemUser.getId());
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (SystemSource res : resources) {
			// TODO: 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
			authSet.add(new SimpleGrantedAuthority("ROLE_" + res.getSourceKey()));
		}
		return authSet;
	}
}