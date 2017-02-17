package com.javabase.system.web;

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
import com.javabase.system.entity.SystemSource;
import com.javabase.system.entity.SystemSourceRole;
import com.javabase.system.entity.SystemUser;
import com.javabase.system.service.SystemLogService;
import com.javabase.system.service.SystemSourceRoleService;
import com.javabase.system.service.SystemSourceService;
import com.javabase.system.utils.CommonUtils;

/**
 * SystemSourceController实现
 * 
 * @author 系统生成
 *
 */

@Controller
@RequestMapping("/background/systemSource/")
public class SystemSourceController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SystemSourceService systemSourceService;
	@Autowired
	private SystemSourceRoleService systemSourceRoleService;
	@Autowired
	private SystemLogService systemLogService;

	private SystemUser systemUser;

	@RequestMapping("find")
	public String find(Model model, SystemSource systemSource, String pageNow, String pageSize) {
		Map<String, Object> param = getRequestParams(request);
		Pagination<SystemSource> resultPage = systemSourceService.paginationObjects(param, StringDefaultValue.intValue(param.get("pageNo")), StringDefaultValue.intValue(param.get("pageSize")));
		model.addAttribute("pageView", resultPage);
		return "/background/systemSource/list";
	}

	@RequestMapping("findByName")
	public void findByName(Model model, String objName) {
		String data = null;
		try {
			SystemSource systemSource = new SystemSource();
			systemSource.setSourceName(objName);
			systemSource = systemSourceService.findObjectByPros(systemSource);
			if (systemSource != null) {
				data = "({msg:'Y',content:'该资源名字已经存在,请重新添加!'})";
			}
			else {
				data = "({msg:'N',content:'可以放心使用!'})";
			}
			// 返回.
			ajaxJson(data);
		}
		catch (Exception e) {
			logger.info("查找出错!" + e.getLocalizedMessage());
		}
	}

	@RequestMapping("findByProps")
	public void findByProps(Model model, SystemSource systemSource) {
		String data = null;
		try {
			SystemSource object = systemSourceService.findObjectByPros(systemSource);
			if (object != null) {
				data = "({msg:'Y',content:'按需求填写!'})";
			}
			else {
				data = "({msg:'N',content:'按需求填写!'})";
			}
			// 返回.
			ajaxJson(data);
		}
		catch (Exception e) {
			logger.info("查找出错!" + e.getLocalizedMessage());
		}
	}

	/**
	 * 某个角色拥有的权限
	 * 
	 * @return
	 */
	@RequestMapping("permissioRole")
	public String permissioRole(Model model, String roleId) {
		SystemRole tRole = (SystemRole) request.getSession().getAttribute("roleSession");
		SystemUser tUsers = (SystemUser) request.getSession().getAttribute("userSession"); // login
		
		List<SystemSource> resources = systemSourceService.getSystemSourcesByRoleId(Integer.parseInt(roleId));
		boolean tag = false; // 没有分配过权限.

		StringBuffer sb = new StringBuffer();
		sb.append("var data = [];");
		String userName = tUsers.getUserName().trim();

		// admin.
		if (userName.equalsIgnoreCase("admin")) {
			// judge it.
			if ( resources == null || resources.size() == 0) {
				resources = systemSourceService.getSystemSourcesByRoleId(tRole.getId());
				tag = true;
			}
			Map<String, Object> param = new HashMap<String, Object>();
			List<SystemSource> allRes = systemSourceService.getObjects(param);
			// loop.
			for (SystemSource father : allRes) {
				boolean flag = false;
				if (tag) {
					sb.append("data.push({ fid: '" + father.getId() + "', pfid: '" + father.getParentId()
							+ "', fname: '" + father.getSourceName() + "'});");
				}
				else {
					for (SystemSource child : resources) {// 用户拥有的权限
						if (child.getId() == father.getId()) {
							sb.append("data.push({ fid: '" + father.getId() + "', pfid: '" + father.getParentId()
									+ "', fname: '" + father.getSourceName() + "',ischecked: true});");
							flag = true;
						}
					}
					if (!flag) {
						sb.append("data.push({ fid: '" + father.getId() + "', pfid: '" + father.getParentId()
								+ "', fname: '" + father.getSourceName() + "'});");
					}
				}
			}
		}
		else {
			// someone
			List<SystemSource> allRes = systemSourceService.getSystemSourcesByRoleId((Integer) tRole.getId());
			if (resources == null || resources.size() == 0 ) {
				tag = true;
			}
			for (SystemSource father : allRes) {
				boolean flag = false;
				if (tag) {
					sb.append("data.push({ fid: '" + father.getId() + "', pfid: '" + father.getParentId()
							+ "', fname: '" + father.getSourceName() + "'});");
				}
				else {
					for (SystemSource child : resources) {// 用户拥有的权限
						if (child.getId() == father.getId()) {
							sb.append("data.push({ fid: '" + father.getId() + "', pfid: '" + father.getParentId()
									+ "', fname: '" + father.getSourceName() + "',ischecked: true});");
							flag = true;
						}
					}
					if (!flag) {
						sb.append("data.push({ fid: '" + father.getId() + "', pfid: '" + father.getParentId()
								+ "', fname: '" + father.getSourceName() + "'});");
					}
				}
			}
		}

		model.addAttribute("roleId", roleId);
		model.addAttribute("resources", sb);
		return "/background/systemSource/permissioUser";

	}

	@ResponseBody
	@RequestMapping("saveSystemSources")
	public String saveSystemSources(String roleId, String rescId) {
		String errorCode = "1000";
		List<String> list = CommonUtils.removeSameItem(Arrays.asList(rescId.split(",")));
		Map<String, Object> param = new HashMap<String, Object>();
		List<SystemSource> sysSources = systemSourceService.findObjectListByPros(param);
		for (SystemSource source : sysSources) {
			if (list.contains(source.getId() + "") && !list.contains(source.getParentId() + "")) {
				list.add(source.getParentId() + "");
			}
		}
		try {
			for (String str : list) {
				SystemSourceRole systemSourceRole = SystemSourceRole.getInstance(SystemSourceRole.class);
				systemSourceRole.setRoleId(Integer.parseInt(roleId));
				systemSourceRole.setSourceId(Integer.parseInt(str));
				systemSourceRoleService.saveObject(systemSourceRole);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			errorCode = "1001";
		}
		// 记录日志
		noteOperator("分配");
		return errorCode;
	}

	@RequestMapping("addOne")
	public String addOne(Model model, SystemSource systemSource) {
		systemSource.setUuid(UUIDProvider.uuid());
		systemSource.setIsDelete(Constants.DeleteStatus.ENABLED);
		systemSource.setCreateTime(new Date());
		systemSource.setUpdateTime(new Date());
		systemSourceService.saveObject(systemSource);
		// 记录日志
		noteOperator("添加");

		return "redirect:find.html";
	}

	@RequestMapping("findById")
	public String findById(Model model, String sourcesId, int type) {
		SystemSource systemSource = systemSourceService.getObjectByID(Integer.parseInt(sourcesId));
		model.addAttribute("resources", systemSource);
		Map<String, Object> param = new HashMap<String, Object>();
		List<SystemSource> resLists = systemSourceService.getObjects(param);
		model.addAttribute("resLists", resLists);
		if (type == 1) {
			return "/background/systemSource/edit";
		}
		else {
			return "/background/systemSource/show";
		}
	}

	@RequestMapping("findByKey")
	public void findByKey(Model model, String resKey) {
		SystemSource sources = new SystemSource();
		sources.setSourceKey(resKey);
		String data = null;
		try {
			SystemSource object = systemSourceService.findObjectByPros(sources);
			if (object != null) {
				data = "({msg:'Y',content:'该资源KEY已经存在,请重新添加!'})";
			}
			else {
				data = "({msg:'N',content:'可以放心使用该KEY!'})";
			}
			// 返回.
			ajaxJson(data);
		}
		catch (Exception e) {
			logger.info("查找出错!" + e.getLocalizedMessage());
		}
	}

	@RequestMapping("findByUrl")
	public void findByUrl(Model model, String resUrl) {
		SystemSource sources = new SystemSource();
		sources.setSourceUrl(resUrl);
		String data = null;
		try {
			SystemSource object = systemSourceService.findObjectByPros(sources);
			if (object != null) {
				data = "({msg:'Y',content:'该资源URL已经存在,请重新添加!'})";
			}
			else {
				data = "({msg:'N',content:'可以放心使用该URL!'})";
			}
			// 返回.
			ajaxJson(data);
		}
		catch (Exception e) {
			logger.info("查找出错!" + e.getLocalizedMessage());
		}
	}

	@RequestMapping("deleteAll")
	public void deleteAll(Model model, String pks) {
		List<String> primks = CommonUtils.removeSameItem(Arrays.asList(pks.split(",")));
		boolean result = false;
		try {
			for (String pk : primks) {
				systemSourceService.deleteObjectByID(pk);
			}
			result = true;
		}
		catch (Exception e) {
			result = false;
		}

		String data = null;
		if (result) {
			data = "({msg:'Y',content:'删除成功!'})";
		}
		else {
			data = "({msg:'N',content:'删除失败!'})";
		}
		// 记录日志
		noteOperator("删除选择的删除项.");
		// 返回.
		ajaxJson(data);
	}

	@RequestMapping("deleteById")
	public void deleteById(Model model, String objId) {
		int count = systemSourceService.deleteObjectByID(objId);
		// 记录日志
		String data = null;
		if (count > 0) {
			data = "({msg:'Y',content:'删除成功!'})";
		}
		else {
			data = "({msg:'N',content:'删除失败!'})";
		}
		// 记录日志
		noteOperator("删除选择的删除项.");
		// 返回.
		ajaxJson(data);
	}

	@RequestMapping("updateById")
	public String updateById(Model model, SystemSource systemSource) {
		systemSourceService.updateObjectByID(systemSource, systemSource.getId());
		noteOperator("修改");
		return "redirect:find.html";
	}

	/**
	 * 跑到新增界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model, SystemSource systemSource, String tag) {
		if (StringUtils.isNotBlank(tag)) {
			if (systemSource == null) {
				systemSource = new SystemSource();
			}
			systemSource.setParentId(Integer.parseInt("1010"));
			List<SystemSource> resLists = systemSourceService.findObjectListByPros(systemSource);
			model.addAttribute("tag", "forSelf");
			model.addAttribute("resLists", resLists);
			return "/background/systemSource/add";
		}
		else {
			model.addAttribute("tag", "new");
			return "/background/systemSource/add";
		}
	}

	/**
	 * 记录日志
	 * 
	 * @param tag:分配,添加,删除,修改
	 */
	public void noteOperator(String tag) {
		// 记录日志
		SystemLog sysLogs = SystemLog.getInstance(SystemLog.class);
		SystemUser tUser = getSysUsers();
		sysLogs.setUserId(tUser.getId());
		sysLogs.setUserName(tUser.getUserName());
		sysLogs.setModule("系统资源管理");
		sysLogs.setAction("系统资源管理——" + tag + "资源");
		sysLogs.setFromIp(CommonUtils.toIpAddr(request));
		systemLogService.saveObject(sysLogs);
	}

	public SystemUser getSysUsers() {
		if (systemUser == null) {
			systemUser = (SystemUser) request.getSession().getAttribute("userSession");
		}
		return systemUser;
	}

}
