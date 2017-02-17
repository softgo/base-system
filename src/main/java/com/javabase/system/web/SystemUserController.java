package com.javabase.system.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.base.core.obj.Pagination;
import com.application.base.utils.common.Constants;
import com.application.base.utils.common.StringDefaultValue;
import com.application.base.utils.common.UUIDProvider;
import com.application.base.core.common.BaseController;
import com.javabase.system.entity.SystemLog;
import com.javabase.system.entity.SystemRole;
import com.javabase.system.entity.SystemUser;
import com.javabase.system.entity.SystemUserRole;
import com.javabase.system.service.SystemLogService;
import com.javabase.system.service.SystemRoleService;
import com.javabase.system.service.SystemUserRoleService;
import com.javabase.system.service.SystemUserService;
import com.javabase.system.utils.CommonUtils;
import com.javabase.system.utils.MD5Util;

/**
 * SystemUserController实现
 * 
 * @author 系统生成
 *
 */

@Controller
@RequestMapping("/background/systemUser/")
public class SystemUserController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(SystemUserController.class.getName());
	
	// Servrice start
	@Autowired(required=false)
	private SystemUserService  systemUserService; 
	@Autowired
	private SystemRoleService systemRoleService;
	@Autowired
	private SystemUserRoleService systemUserRoleService;
	@Autowired
	private SystemLogService systemLogService;
	
	private SystemUser systemUser;

	@RequestMapping("find")
	public String find(Model model) {
		SystemUser tUser = (SystemUser) request.getSession().getAttribute("userSession");
		Map<String, Object> param = getRequestParams(request);
		String loginName = tUser.getUserName().trim();
		Pagination<SystemUser> resultPage = null;
		//admin.
		if (loginName.equalsIgnoreCase("admin")) {
			resultPage = systemUserService.paginationObjects(param, StringDefaultValue.intValue(param.get("pageNo")), StringDefaultValue.intValue(param.get("pageSize")));
		}else {
			//someone.
			param.put("id", tUser.getId());
			param.put("parentId", tUser.getParentId());
			
			resultPage = systemUserService.paginationObjects(param, StringDefaultValue.intValue(param.get("pageNo")), StringDefaultValue.intValue(param.get("pageSize")));
			List<SystemUser> tempUList = resultPage.getData();
			List<SystemUser> userList = new ArrayList<SystemUser>();
			for (SystemUser user : tempUList) {
				if (StringUtils.isNotBlank(user.getRoleName())) {
					if (user.getRoleName().equalsIgnoreCase(tUser.getRoleName())) {
						userList.add(user);
					}
				}
				if (user.getParentId() == tUser.getId()) {
					userList.add(user);
				}
			}
		}
		/**
		 * 多角色显示.
		 */
		for ( SystemUser user : resultPage.getData()) {
			user.setRoleName(getRoleName(user.getId()));
		}
		model.addAttribute("pageView", resultPage);
		return "/background/systemUser/list";
	}
	
	private String getRoleName(Integer userId){
		List<SystemRole> roleList = null;
		try {
			roleList = systemRoleService.findSystemRolesByUserId(userId);	
		}
		catch (Exception e) {
			return "";
		}
		if (roleList==null || roleList.size()==0) {
			return "";
		}
		StringBuffer buffer = new StringBuffer("");
		for (int i = 0; i < roleList.size(); i++) {
			if (i==roleList.size()-1) {
				buffer.append(roleList.get(i).getRoleName());
			}else{
				buffer.append(roleList.get(i).getRoleName()+",");
			}
		}
		return buffer.toString();
	}
	
	@RequestMapping("findByName")
	public void findByName(Model model, String objName ) {
		String data = null;
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userName", objName);
			SystemUser sysUsers = systemUserService.findObjectByPros(param);
			if (sysUsers.getId() > 0) {
				data = "({msg:'Y',content:'该用户名已经存在,请重新填入!'})";
			}else {
				data = "({msg:'N',content:'该用户名可以正常使用!'})";
			}
			//返回.
			ajaxJson(data);
		} catch (Exception e) {
			logger.info("查找出错!"+e.getLocalizedMessage());
		}
	}
	
	@RequestMapping("addOne")
	public String addOne(Model model, SystemUser sysUsers) {
		SystemUser tUser = (SystemUser) request.getSession().getAttribute("userSession");
		if (tUser!=null) {
			sysUsers.setParentId(tUser.getId());
		}
		sysUsers.setUuid(UUIDProvider.uuid());
		sysUsers.setIsDelete(Constants.DeleteStatus.ENABLED);
		sysUsers.setCreateTime(new Date());
		sysUsers.setUpdateTime(new Date());
		//密码加密.
		String password = sysUsers.getUserPass();
		password = MD5Util.encrypt(password);
		sysUsers.setUserPass(password);		
		systemUserService.saveObject(sysUsers);
		//记录日志
		noteOperator("添加");
		return "redirect:find.html";
	}
	
	/**
	 * 修改密码.
	 * @param model
	 * @return
	 */
	@RequestMapping("updatePassUI")
	public String updatePassUI(Model model,String userId ) {
		SystemUser user = systemUserService.getObjectByID(userId);
		model.addAttribute("user", user);
		return "/background/systemUser/updatePass";
	}

	/**
	 * 跑到新增界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI() {
		return "/background/systemUser/add";
	}

	/**
	 * 修改界面
	 * 
	 * @param model
	 * @param videoTypeIds
	 * @return
	 */
	@RequestMapping("findById")
	public String findById(Model model, String userId, int type) {
		SystemUser user = systemUserService.getObjectByID(Integer.parseInt(userId));
		user.setRoleName(getRoleName(user.getId()));
		String enPass = user.getUserPass();
		user.setUserPass(MD5Util.decrypt(enPass));
		model.addAttribute("user", user);
		Map<String, Object> param = new HashMap<String, Object>();
		List<SystemRole> roles=systemRoleService.getObjects(param);
		model.addAttribute("roles", roles);
		if (type == 1) {
			return "/background/systemUser/edit";
		} else {
			return "/background/systemUser/show";
		}
	}
	
	/**
	 * 给用户分配角色界面
	 * @return
	 */
	@RequestMapping("userRole")
	public String userRole(Model model,String userId){
		SystemUser user = systemUserService.getObjectByID(Integer.parseInt(userId));
		if (user!=null) {
			user.setRoleName(getRoleName(user.getId()));
		}
		SystemRole tRole = (SystemRole) request.getSession().getAttribute("roleSession");
		SystemUser tUser = (SystemUser) request.getSession().getAttribute("userSession");
		Map<String, Object> param = getRequestParams(request);
		String loginName = tUser.getUserName().trim();
		SystemRole roles = new SystemRole();
		Pagination<SystemRole> resultPage = null;
		//admin.
		if (loginName.equalsIgnoreCase("admin")) {
			resultPage = systemRoleService.paginationObjects(tRole, StringDefaultValue.intValue(param.get("pageNo")), StringDefaultValue.intValue(param.get("pageSize")));
		}else{
			//someone
			roles.setId(tRole.getId());
			roles.setParentId(tRole.getParentId());
			resultPage = systemRoleService.paginationObjects(tRole, StringDefaultValue.intValue(param.get("pageNo")), StringDefaultValue.intValue(param.get("pageSize")));
			List<SystemRole> tempList = resultPage.getData();
			ArrayList<SystemRole> roleList = new ArrayList<SystemRole>();
			for (SystemRole role : tempList) {
				if (tRole.getId()==role.getParentId()) {
					roleList.add(role);
				}
				if (tRole.getRoleName().equalsIgnoreCase(role.getRoleName())) {
					roleList.add(role);					
				}
			}
			resultPage.setRowCount(roleList.size());
		}
		
		model.addAttribute("user", user);
		model.addAttribute("pageView", resultPage);
		return "/background/systemUser/userRole";
	}
	
	/**
	 * 保存用户分配角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping("allocation")
	public String allocation(Model model,String userId,String roleId){
		String resultCode = "1000";
		try {
			SystemUserRole userRole = SystemUserRole.getInstance(SystemUserRole.class);
			userRole.setRoleId(Integer.parseInt(roleId));
			userRole.setUserId(Integer.parseInt(userId));
			systemUser = getSysUsers();
			userRole.setCreateUser(systemUser.getId());
			userRole.setUpdateUser(systemUser.getId());
			//记录日志
			noteOperator("分配角色");
			userRole = systemUserRoleService.saveObject(userRole);
		} catch (Exception e) {
			e.printStackTrace();
			resultCode="1001";
		}
		return resultCode;
	}	
	
	@ResponseBody
	@RequestMapping("deleteAll")
	public void deleteAll(Model model,String pks){
		List<String> primks= CommonUtils.removeSameItem(Arrays.asList(pks.split(",")));
		boolean result = false;
		try {
			for (String pk : primks) {
				systemUserService.deleteObjectByID(pk);
			}
			result = true;
		}
		catch (Exception e) {
			result = false;
		}
		String data = null;
		if (result ) {
			data = "({msg:'Y',content:'删除成功!'})";
		}else {
			data = "({msg:'N',content:'删除失败!'})";
		}
		//记录日志
		noteOperator("删除选择的删除项.");
		//返回.
		ajaxJson(data);
	}
	
	@ResponseBody
	@RequestMapping("deleteById")
	public void deleteById(Model model, String objId) {
		String data = null;
		try {
			int userCount = systemUserService.deleteObjectByID(objId);
			if (userCount >0  ) {
				data = "({msg:'Y',content:'删除成功!'})";
			}else {
				data = "({msg:'N',content:'删除失败!'})";
			}
			//记录日志
			noteOperator("删除");
			//返回.
			ajaxJson(data);
		} catch (Exception e) {
			logger.info("查找出错!"+e.getLocalizedMessage());
		}
	}
	
	@RequestMapping("updateById")
	public String updateById(Model model, SystemUser sysUsers) {
		systemUserService.updateObjectByID(sysUsers, sysUsers.getId());
		
		/*
		// 一个用户多个角色的情况没有处理.
		String roleName = sysUsers.getRoleName();
		SystemRole roles = new SystemRole();
		roles.setRoleName(roleName);
		roles = systemRoleService.findObjectByPros(roles);
		SystemUserRole sysUsersRoles = new SystemUserRole();
		sysUsersRoles.setUserId(sysUsers.getId());
		sysUsersRoles.setRoleId(roles.getId());
		systemUserRoleService.updateObjectByID(sysUsersRoles, sysUsersRoles.getId());
		*/
		//记录日志
		noteOperator("修改");
		
		return "redirect:find.html";
	}
	
	public void noteOperator(String tag) {
		SystemLog sysLogs = SystemLog.getInstance(SystemLog.class); 
		SystemUser tUser = getSysUsers();
		sysLogs.setUserId(tUser.getId());
		sysLogs.setUserName(tUser.getUserName());
		sysLogs.setModule("系统用户管理");
		sysLogs.setAction("系统用户管理——"+tag+"用户");
		sysLogs.setFromIp(CommonUtils.toIpAddr(request));
		systemLogService.saveObject(sysLogs);
	}
	
	public SystemUser getSysUsers(){
		if (systemUser==null) {
			systemUser = (SystemUser) request.getSession().getAttribute("userSession");
		}
		return systemUser;
	}
	
}


