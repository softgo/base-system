package com.javabase.system.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.base.core.obj.Pagination;
import com.application.base.utils.common.Constants;
import com.application.base.utils.common.StringDefaultValue;
import com.application.base.utils.common.UUIDProvider;
import com.application.base.core.common.BaseController;
import com.javabase.system.entity.SystemLog;
import com.javabase.system.entity.SystemRole;
import com.javabase.system.entity.SystemSource;
import com.javabase.system.entity.SystemUser;
import com.javabase.system.service.SystemLogService;
import com.javabase.system.service.SystemRoleService;
import com.javabase.system.service.SystemSourceService;
import com.javabase.system.utils.CommonUtils;

/**
 * SystemRoleController实现
 * 
 * @author 系统生成
 *
 */

@Controller
@RequestMapping("/background/systemRole/")
public class SystemRoleController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(SystemRoleController.class.getName());

	@Autowired
	private SystemRoleService systemRoleService;
	@Autowired
	private SystemSourceService systemSourceService;
	@Autowired
	private SystemLogService systemLogService;
	
	private SystemUser systemUser;
	
	/**
	 * 跳到新增页面
	 * @return
	 */
	@RequestMapping(value="addUI")
	public String addUI(Model model){
		this.getRights(model);
		return "/background/systemRole/add";
	} 
	
	/**
	 * 权限树
	 * @return
	 */
	@RequestMapping(value="permissio")
	public String getRights(Model model){
		Map<String, Object> param = new HashMap<String, Object>();
		List<SystemSource> allRes = systemSourceService.getObjects(param);
		StringBuffer sb = new StringBuffer();
		sb.append("var data = [];");
		for (SystemSource r : allRes) {
				sb.append("data.push({ fid: '"
						+ r.getId() + "', pfid: '"
						+ r.getParentId()
						+ "', fname: '" + r.getSourceName()
						+ "'});");

			}
		model.addAttribute("resources", sb);
		return "/background/systemRole/permissioUser";
	}
	
	@RequestMapping("find")
	public String find(Model model, SystemRole systemRole, String pageNow,String pageSize ) {
		SystemRole tRole = (SystemRole) request.getSession().getAttribute("roleSession");
		SystemUser tUser = (SystemUser) request.getSession().getAttribute("userSession");
		Map<String, Object> param = getRequestParams(request);
		String loginName = tUser.getUserName().trim();
		Pagination<SystemRole> resultPage = null;
		if (loginName.equalsIgnoreCase("admin")) {
			resultPage = systemRoleService.paginationObjects(param, StringDefaultValue.intValue(param.get("pageNo")), StringDefaultValue.intValue(param.get("pageSize")));
		}else {
			systemRole.setParentId(tRole.getParentId());
			systemRole.setId(tRole.getId());
			resultPage = systemRoleService.paginationObjects(param, StringDefaultValue.intValue(param.get("pageNo")), StringDefaultValue.intValue(param.get("pageSize")));
			List<SystemRole> tempList  = resultPage.getData();
			List<SystemRole> roleList = new ArrayList<SystemRole>();
			for (SystemRole role : tempList) {
				if (tRole.getId()==role.getParentId()) {
					roleList.add(role);
				}
				if (tRole.getRoleName().equalsIgnoreCase(role.getRoleName())) {
					roleList.add(role);					
				}
			}
		}
		model.addAttribute("pageView", resultPage);
		model.addAttribute("loginRole", tRole);
		return "/background/systemRole/list";
	}
	
	@RequestMapping("findByName")
	public void findByName(Model model, String objName) {
		String data = null;
		try {
			SystemRole sysRole =  new SystemRole(); 
			sysRole.setRoleName(objName);
			sysRole = systemRoleService.findObjectByPros(sysRole);
			if (sysRole.getId() > 0 ) {
				data = "({msg:'Y',content:'该角色名已经存在,请重新输入!'})";
			}else {
				data = "({msg:'N',content:'可以放心使用!'})";
			}
			//返回.
			ajaxJson(data);
		} catch (Exception e) {
			logger.info("查找出错!"+e.getLocalizedMessage());
		}
	}
	
	@RequestMapping("findByProps")
	public void findByProps(Model model, String roleKey) {
		String data = null;
		SystemRole sysRoles = new SystemRole(); 
		sysRoles.setRoleKey(roleKey);
		try {
			SystemRole object = systemRoleService.findObjectByPros(sysRoles);
			if (object != null) {
				data = "({msg:'Y',content:'该KEY已经存在,请重新填写!'})";
			}else {
				data = "({msg:'N',content:'可以放心使用!'})";
			}
			//返回.
			ajaxJson(data);
		} catch (Exception e) {
			logger.info("查找出错!"+e.getLocalizedMessage());
		}
	}
	
	@RequestMapping("addOne")
	public String addOne(Model model, SystemRole sysRoles) {
		SystemRole trole = (SystemRole) request.getSession().getAttribute("roleSession");
		sysRoles.setUuid(UUIDProvider.uuid());
		sysRoles.setIsDelete(Constants.DeleteStatus.ENABLED);
		sysRoles.setCreateTime(new Date());
		sysRoles.setUpdateTime(new Date());
		SystemUser user = getSysUsers();
		if (user!=null) {
			sysRoles.setUpdateUser(user.getId());
			sysRoles.setCreateUser(user.getId());
		}
		sysRoles.setParentId(trole.getId());
		systemRoleService.saveObject(sysRoles);
		//记录日志
		noteOperator("添加");
		return "redirect:find.html";
	}
	
	@RequestMapping("findById")
	public String findById(Model model, String roleId, int type) {
		SystemRole sysRole = systemRoleService.getObjectByID(roleId);
    	model.addAttribute("role", sysRole);
    	if(type == 1){
  			return "/background/systemRole/edit";
  		}else{
  			return "/background/systemRole/show";
  		}
	}
	
	@RequestMapping("deleteAll")
	public void deleteAll(Model model,String pks){
		List<String> primks= CommonUtils.removeSameItem(Arrays.asList(pks.split(",")));
		boolean result = false ;
		for (String pk : primks) {
			systemRoleService.deleteObjectByID(pk);
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
	
	@RequestMapping("deleteById")
	public void deleteById(Model model, String objId) {
		String data = null;
		try {
			int count = systemRoleService.deleteObjectByID(objId);
			if (count >0 ) {
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
	public String updateById(Model model, SystemRole sysRole) {
		systemRoleService.updateObjectByID(sysRole, sysRole.getId());
		//记录日志
		noteOperator("修改");
		return "redirect:find.html";
	}
	
	/**
	 * 记录日志
	 * @param tag:添加;修改;删除...
	 */
	public void noteOperator(String tag){
		//记录日志
		SystemLog sysLogs = SystemLog.getInstance(SystemLog.class); 
		SystemUser tUser = getSysUsers();
		sysLogs.setUserId(tUser.getId());
		sysLogs.setUserName(tUser.getUserName());
		sysLogs.setModule("系统角色管理");
		sysLogs.setAction("系统角色管理——"+tag+"角色");
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


