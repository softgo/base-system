package com.javabase.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.base.core.constant.CommonResultConstant;
import com.application.base.core.exception.CommonException;
import com.application.base.core.obj.Pagination;
import com.application.base.core.utils.CommonBeanUtils;
import com.javabase.system.entity.SystemServerInfo;
import com.javabase.system.dao.SystemServerInfoDao;
import com.javabase.system.service.SystemServerInfoService;

/**
 * SystemServerInfoServiceImpl实现
 * 
 * @author 系统生成
 *
 */

@Service("systemServerInfoService")
public class SystemServerInfoServiceImpl implements SystemServerInfoService {

	@Autowired
	private SystemServerInfoDao systemServerInfoDao; //Dao 注入.

	public SystemServerInfo saveObject(Map<String, Object> param) {
		try {
			//添加的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			SystemServerInfo object = systemServerInfoDao.saveObject(param,factoryTag);
			if(object==null){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return object;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	public SystemServerInfo saveObject(SystemServerInfo object) {
		try {
			//添加的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			SystemServerInfo result = systemServerInfoDao.saveObject(object,factoryTag);
			if(result==null){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return result;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	public boolean saveBatchObject(List<SystemServerInfo> objs) {
		try {
			//批量添加处理,自己去装数据
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			boolean result = systemServerInfoDao.saveBatchObject(objs,factoryTag);
			if(!result){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return result;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}

	public SystemServerInfo getObjectByID(Object objId) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			SystemServerInfo object = systemServerInfoDao.getObjectByID(objId,factoryTag);
			if(object==null){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return object;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}

	public SystemServerInfo getObjectByUUID(String uuid) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			SystemServerInfo object = systemServerInfoDao.getObjectByUUID(uuid,factoryTag);
			if(object==null){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return object;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	public int updateObjectByID(Map<String, Object> param, Object objId) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			SystemServerInfo object = getObjectByID(objId);
			int count = systemServerInfoDao.updateObjectByID(param, object,factoryTag);
			if(count < 1 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	public int updateObjectByID(SystemServerInfo object, Object objId) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			SystemServerInfo existObj = getObjectByID(objId);
			Map<String, Object> param = CommonBeanUtils.transBean2Map(object);
			param = CommonBeanUtils.getValueMap(param);
			int count = systemServerInfoDao.updateObjectByID(param, existObj,factoryTag);
			if(count < 1 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	public int updateObjectByUUID(Map<String, Object> param, String uuid) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			SystemServerInfo object = getObjectByUUID(uuid);
			int count = systemServerInfoDao.updateObjectByUUID(param, object,factoryTag);
			if(count < 1 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	public int updateObjectByUUID(SystemServerInfo object, String uuid) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			SystemServerInfo existObj = getObjectByUUID(uuid);
			Map<String, Object> param = CommonBeanUtils.transBean2Map(object);
			param = CommonBeanUtils.getValueMap(param);
			int count = systemServerInfoDao.updateObjectByUUID(param, existObj,factoryTag);
			if(count < 1 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	public int updateObjectByWhere(Map<String, Object> param) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = systemServerInfoDao.updateObjectByWhere(param,factoryTag);
			if(count < 1 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	public int updateObjectByWhere(SystemServerInfo object) {
		try {
			//修改的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = systemServerInfoDao.updateObjectByWhere(object,factoryTag);
			if(count < 1 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	public int deleteObjectByID(Object objId) {
		try {
			//删除的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = systemServerInfoDao.deleteObjectByID(objId,factoryTag);
			if(count < 1 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	public int deleteObjectByUUID(String uuid) {
		try {
			//删除的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = systemServerInfoDao.deleteObjectByUUID(uuid,factoryTag);
			if(count < 1 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	public int deleteObjectByWhere(Map<String, Object> param) {
		try {
			//删除的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = systemServerInfoDao.deleteObjectByWhere(param,factoryTag);
			if(count < 1 ){
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}

	public int deleteObjectByWhere(SystemServerInfo object) {
		try {
			//删除的验证和业务逻辑自己添加处理
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = systemServerInfoDao.deleteObjectByWhere(object,factoryTag);
			if(count < 1 ){
				throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	public List<SystemServerInfo> getObjects(Map<String, Object> param) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			List<SystemServerInfo> objects = systemServerInfoDao.getObjects(param,factoryTag);
			if(objects==null || objects.size()==0 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return objects;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}

	public List<SystemServerInfo> getObjects(SystemServerInfo object) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			List<SystemServerInfo> objects = systemServerInfoDao.getObjects(object,factoryTag);
			if(objects==null || objects.size()==0 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return objects;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	public Pagination<SystemServerInfo> paginationObjects(Map<String, Object> param, int pageNo, int pageSize){
		try {
			//根据条件查询分页操作.
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			Pagination<SystemServerInfo> pagination = systemServerInfoDao.paginationObjects(param, pageNo, pageSize,factoryTag);
			if(pagination==null){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return pagination;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}

	
	public Pagination<SystemServerInfo> paginationObjects(SystemServerInfo object, int pageNo, int pageSize){
		try {
			//根据条件查询分页操作.
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			Pagination<SystemServerInfo> pagination = systemServerInfoDao.paginationObjects(object, pageNo, pageSize,factoryTag);
			if(pagination==null){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return pagination;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	
	public SystemServerInfo findObjectByPros(Map<String, Object> param) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			SystemServerInfo object = systemServerInfoDao.findObjectByPros(param,factoryTag);
			if(object==null){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return object;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	
	public SystemServerInfo findObjectByPros(SystemServerInfo object) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			SystemServerInfo result = systemServerInfoDao.findObjectByPros(object,factoryTag);
			if(result==null){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return result;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	
	public List<SystemServerInfo> findObjectListByPros(Map<String, Object> param) {
		try {
			//根据条件查询对象.
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			List<SystemServerInfo> objects = systemServerInfoDao.findObjectListByPros(param,factoryTag);
			if(objects==null || objects.size()==0 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return objects;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	
	public List<SystemServerInfo> findObjectListByPros(SystemServerInfo object) {
		try {
			//根据条件查询对象.
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			List<SystemServerInfo> objects = systemServerInfoDao.findObjectListByPros(object,factoryTag);
			if(objects==null || objects.size()==0 ){
				//异常信息由自己去配置文件中编写
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_IS_NULL);
			}
			return objects;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	
	public int getObjectCount() {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = systemServerInfoDao.getObjectCount(factoryTag);
			if(count < 1 ){
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_COUNT_FAILD);
			}
			return count;
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}

	
	public int getObjectCount(Map<String, Object> param) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = systemServerInfoDao.getObjectCount(param,factoryTag);
			if(count < 1){
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_COUNT_FAILD);
			}	
			return count;		
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
	
	
	public int getObjectCount(SystemServerInfo object) {
		try {
			String factoryTag = null; //根据实际情况,来设置访问的数据源,如果设置为null,将访问默认的数据源配置
			int count = systemServerInfoDao.getObjectCount(object,factoryTag);
			if(count < 1){
				throw new CommonException(CommonResultConstant.CommonResult.GET_RESULT_COUNT_FAILD);
			}	
			return count;		
		}
		catch (Exception e) {
			throw new CommonException(CommonResultConstant.CommonResult.OPERATE_ACTION_IS_FAILD);
		}
	}
}
