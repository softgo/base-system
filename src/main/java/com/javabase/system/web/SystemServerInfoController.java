package com.javabase.system.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.base.core.obj.Pagination;
import com.application.base.utils.common.JSONUtils;
import com.application.base.utils.common.StringDefaultValue;
import com.application.base.core.common.BaseController;
import com.javabase.system.entity.ServerStatus;
import com.javabase.system.entity.SystemLog;
import com.javabase.system.entity.SystemServerInfo;
import com.javabase.system.entity.SystemUser;
import com.javabase.system.service.SystemLogService;
import com.javabase.system.service.SystemServerInfoService;
import com.javabase.system.utils.CommonUtils;
import com.javabase.system.utils.PropertiesUtils;

/**
 * SystemServerInfoController实现
 * 
 * @author 系统生成
 *
 */

@Controller
@RequestMapping("/background/systemServerInfo/")
public class SystemServerInfoController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SystemServerInfoService systemServerInfoService;
	@Autowired
	private SystemLogService systemLogService;
	
	private SystemUser systemUser;

	@RequestMapping("find")
	public String find(Model model, SystemServerInfo sysServerInfo) {
		Map<String, Object> param = getRequestParams(request);
		Pagination<SystemServerInfo> resultPage = systemServerInfoService.paginationObjects(param, StringDefaultValue.intValue(param.get("pageNo")), StringDefaultValue.intValue(param.get("pageSize")));
		model.addAttribute("pageView", resultPage);
		return "/background/systemServerInfo/list";
	}
	
	@RequestMapping(value="show")
	public String show() {
		return "/background/systemServerInfo/show";
	}

	@RequestMapping(value="forecast")
	public String forecast() {
		return "/background/systemServerInfo/forecast";
	}

	/**
	 * 获取服务器基本信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="info")
	public void serverBaseInfo() throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		ServerStatus status = CommonUtils.getServerStatus();
		dataMap.put("data", status);
		//return dataMap;
		ajaxJson(response, JSONUtils.toJson(dataMap));
	}
	
	/**
	 * 预警监控信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/warnInfo")
	public void warnInfo(HttpServletRequest request) throws Exception {
		ServerStatus status = CommonUtils.getServerStatus();
		Map<String, Object> dataMap = new HashMap<String, Object>();

		String cpuUsage = status.getCpuUsage();
		long FreeMem = status.getFreeMem();
		long useMem = status.getUsedMem();
		long TotalMem = status.getTotalMem();
		String serverUsage = CommonUtils.fromUsage(useMem, TotalMem);
		dataMap.put("cpuUsage", cpuUsage);
		dataMap.put("FreeMem", FreeMem);
		dataMap.put("TotalMem", TotalMem);
		dataMap.put("serverUsage", serverUsage);
		long JvmFreeMem = status.getJvmFreeMem();
		long JvmTotalMem = status.getJvmTotalMem();
		String JvmUsage = CommonUtils.fromUsage(JvmTotalMem - JvmFreeMem, JvmTotalMem);
		dataMap.put("JvmFreeMem", JvmFreeMem);
		dataMap.put("JvmTotalMem", JvmTotalMem);
		dataMap.put("JvmUsage", JvmUsage);
		dataMap.put("cpu", PropertiesUtils.findPropertiesKey("cpu",PropertiesUtils.propsFilePath));
		dataMap.put("jvm", PropertiesUtils.findPropertiesKey("jvm",PropertiesUtils.propsFilePath));
		dataMap.put("ram", PropertiesUtils.findPropertiesKey("ram",PropertiesUtils.propsFilePath));
		dataMap.put("toEmail", PropertiesUtils.findPropertiesKey("toEmail",PropertiesUtils.propsFilePath));
		dataMap.put("diskInfos", status.getDiskInfos());
		//return dataMap;
		ajaxJson(response, JSONUtils.toJson(dataMap));
	}
	
	/**
	 * 修改配置　
	 * @param request
	 * @param nodeId
	 * @return
	 * @throws Exception
	 */
    @ResponseBody
	@RequestMapping("/modifySer")
    public void modifySer(HttpServletRequest request,String key,String value) throws Exception{
    	Map<String, Object> dataMap = new HashMap<String,Object>();
    	try {
		// 从输入流中读取属性列表（键和元素对）
    		PropertiesUtils.modifyProperties(key, value,PropertiesUtils.propsFilePath);
		} catch (Exception e) {
			dataMap.put("flag", false);
		}
    	dataMap.put("flag", true);
    	
    	//系统设置管理.
    	SystemLog systemLog = SystemLog.getInstance(SystemLog.class);
		SystemUser tUser = getSysUsers();
		systemLog.setUserId(tUser.getId());
		systemLog.setUserName(tUser.getUserName());
		systemLog.setModule("服务器管理");
		systemLog.setAction("服务器管理——修改服务配置");
		systemLog.setFromIp(CommonUtils.toIpAddr(request));
		systemLogService.saveObject(systemLog);
		//return dataMap;
		ajaxJson(response, JSONUtils.toJson(dataMap));
    }
	
	
	@RequestMapping("findByProps")
	public void findByProps(Model model, SystemServerInfo sysServerInfo) {
		String data = null;
		try {
			SystemServerInfo object = systemServerInfoService.findObjectByPros(sysServerInfo);
			if (object != null) {
				data = "({msg:'Y',content:'按需求填写!'})";
			}else {
				data = "({msg:'N',content:'按需求填写!'})";
			}
			//返回.
			ajaxJson(data);
		} catch (Exception e) {
			logger.info("查找出错!"+e.getLocalizedMessage());
		}
	}
	
	@RequestMapping("addOne")
	public String addOne(Model model, SystemServerInfo sysServerInfo) {
		systemServerInfoService.saveObject(sysServerInfo);
		return "redirect:find.html";
	}
	
	@RequestMapping("findById")
	public String findById(Model model, String objId, int type) {
    	 SystemServerInfo sysServerInfos =systemServerInfoService.getObjectByID(objId);
    	 model.addAttribute("systemServerInfo", sysServerInfos);
    	 return "/background/systemServerInfo/show";
	}
	
	@RequestMapping("deleteById")
	public String deleteById(Model model, String objId) {
		systemServerInfoService.deleteObjectByID(objId);
		return "redirect:find.html";
	}
	
	@RequestMapping("updateById")
	public String updateById(Model model, SystemServerInfo sysServerInfo) {
		systemServerInfoService.updateObjectByID(sysServerInfo,sysServerInfo.getId());
		return "redirect:find.html";
	}
	
	public SystemUser getSysUsers(){
		if (systemUser==null) {
			systemUser = (SystemUser) request.getSession().getAttribute("userSession");
		}
		return systemUser;
	}
}


