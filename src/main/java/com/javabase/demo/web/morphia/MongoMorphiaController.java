package com.javabase.demo.web.morphia;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.base.core.common.BaseController;
import com.application.base.core.constant.Constants;
import com.application.base.core.utils.CommonBeanUtils;
import com.application.base.utils.common.JSONUtils;
import com.application.base.utils.page.PageView;
import com.javabase.demo.morphia.entity.MongoMorphia;
import com.javabase.demo.morphia.service.MongoMorphiaService;

/**
 * Controller
 * @author bruce
 */
@Controller
@RequestMapping("/background/mongoMorphia/")
public class  MongoMorphiaController extends BaseController {

	private final static Logger logger= LoggerFactory.getLogger(MongoMorphiaController.class.getName());
	
	// Servrice start
	@Autowired
	private MongoMorphiaService  mongoMorphiaService; 
    
	@RequestMapping("findObjPage")
	public String findObjPage(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params =  nullAbleValidation(request, "");
	    PageView pageView = findByPage(params.get(Constants.SQLConstants.PAGE_NO).toString(),params.get(Constants.SQLConstants.PAGE_SIZE).toString());  
        pageView = mongoMorphiaService.findObjPage(pageView, params);
		return "/background/mongoMorphia/list";
	}
	
	@RequestMapping("findObjById")
    public void findObjById(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params =  nullAbleValidation(request, "id");
		MongoMorphia mongoMorphia =mongoMorphiaService.findObjById(new ObjectId(params.get("id")+""));
		//返回.
		ajaxJson(response,JSONUtils.toJson(mongoMorphia));
    }
	
	@RequestMapping("findObjByProps")
	public void findObjByProps(HttpServletRequest request,HttpServletResponse response) {
		String data = null;
		try {
    		Map<String, Object> params =  nullAbleValidation(request, "");
            MongoMorphia object = mongoMorphiaService.findObjByProps(params);
            if (object != null) {
				data = "({msg:'Y',content:'按需求填写!'})";
			}else {
				data = "({msg:'N',content:'按需求填写!'})";
			}
			//返回.
			ajaxJson(response,data);
		} catch (Exception e) {
			logger.info("查找出错!"+e.getLocalizedMessage());
		}
	}
	
    @RequestMapping("addObjUI")
    public String addObjUI() {
        return "/background/mongoMorphia/add";
    }
	
	@RequestMapping("addObjOne")
	public String addObjOne(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params = nullAbleValidation(request, "");
		MongoMorphia mongoMorphia = CommonBeanUtils.transMap2Bean(params, MongoMorphia.class);
		mongoMorphiaService.addObjOne(mongoMorphia);
		return "redirect:find.html";
	}
	
	@RequestMapping("deleteObjById")
	public void deleteObjById(HttpServletRequest request,HttpServletResponse response) {
		String data = null;
        try {
        	Map<String, Object> params = nullAbleValidation(request, "id");
            int result = mongoMorphiaService.deleteByObjId(new ObjectId(params.get("id")+""));
            if (result > 0 ) {
                data = "({msg:'Y',content:'删除成功!'})";
            }else {
                data = "({msg:'N',content:'删除失败!'})";
            }
            //返回.
            ajaxJson(response,data);
        } catch (Exception e) {
            logger.info("查找出错!"+e.getLocalizedMessage());
        }
	}
	
	@RequestMapping("updateObjById")
	public String updateObjById(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> params = nullAbleValidation(request, "id");
        mongoMorphiaService.updateObjOne(new ObjectId(params.get("id")+""), params);
		return "redirect:find.html";
	}
	
}
