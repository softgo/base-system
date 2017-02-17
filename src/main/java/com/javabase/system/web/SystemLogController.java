package com.javabase.system.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.base.core.common.BaseController;
import com.application.base.core.obj.Pagination;
import com.application.base.utils.common.StringDefaultValue;
import com.application.base.utils.page.PageView;
import com.javabase.system.entity.SystemLog;
import com.javabase.system.entity.SystemServerInfo;
import com.javabase.system.service.SystemLogService;

/**
 * SystemLogController实现
 * 
 * @author 系统生成
 *
 */

@Controller
@RequestMapping("/background/systemLog/")
public class SystemLogController extends BaseController {	
	
	@Autowired
	private SystemLogService systemLogService;

	@RequestMapping("loginLog")
	public String logins(Model model) {
		Map<String, Object> param = getRequestParams(request);
		param.put("logType", "1");
		Pagination<SystemLog> resultPage = systemLogService.paginationObjects(param, StringDefaultValue.intValue(param.get("pageNo")), StringDefaultValue.intValue(param.get("pageSize")));
		model.addAttribute("pageView", resultPage);
		return "/background/systemLog/loginLog";
	}
	
	@RequestMapping("systemLog")
	public String find(Model model) {
		Map<String, Object> param = getRequestParams(request);
		param.put("logType", "2");
		Pagination<SystemLog> resultPage = systemLogService.paginationObjects(param, StringDefaultValue.intValue(param.get("pageNo")), StringDefaultValue.intValue(param.get("pageSize")));
		model.addAttribute("pageView", resultPage);
		return "/background/systemLog/systemLog";
	}
	
}


