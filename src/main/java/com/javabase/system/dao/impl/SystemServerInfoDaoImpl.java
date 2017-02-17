package com.javabase.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.application.base.core.apisupport.impl.MultiCacheStrutsBaseDaoImpl;
import com.application.base.core.datasource.param.CustomSQL;
import com.application.base.core.datasource.param.ESQLOperator;
import com.application.base.core.datasource.param.Param;
import com.application.base.core.datasource.param.ParamBuilder;
import com.application.base.core.datasource.param.SQLCreator;
import com.application.base.core.exception.BusinessException;
import com.application.base.core.obj.Pagination;
import com.application.base.core.utils.CommonBeanUtils;
import com.javabase.system.entity.SystemServerInfo;
import com.javabase.system.dao.SystemServerInfoDao;

/**
 * SystemServerInfoDaoImpl实现
 * 
 * @author 系统生成
 *
 */
@Service("systemServerInfoDao")
public class SystemServerInfoDaoImpl extends MultiCacheStrutsBaseDaoImpl<SystemServerInfo> implements SystemServerInfoDao {
	
	
	public SystemServerInfo saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemServerInfo object = CommonBeanUtils.transMap2BasePO(param, SystemServerInfo.class);
		return factory.getCacheWriteDataSession().saveObject(SystemServerInfo.class,object);
	}


	public SystemServerInfo saveObject(SystemServerInfo object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveObject(SystemServerInfo.class,object);
	}
	
	
	public boolean saveBatchObject(List<SystemServerInfo> objs,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveBatchObject(SystemServerInfo.class,objs);
	}
	
	
	public SystemServerInfo getObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultById(SystemServerInfo.class,"id", objId);
	}


	public SystemServerInfo getObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultByUUID(SystemServerInfo.class, uuid);
	}
	
	
	public int updateObjectByID(Map<String, Object> param, SystemServerInfo object ,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemServerInfo operateObject = new SystemServerInfo();
		operateObject.setId(object.getId());
		operateObject.setCreateTime(object.getCreateTime());
		operateObject = CommonBeanUtils.transMap2BasePO(param, operateObject);
		return factory.getCacheWriteDataSession().updateObjectByID(SystemServerInfo.class, operateObject);
	}


	public int updateObjectByUUID(Map<String, Object> param, SystemServerInfo object ,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemServerInfo operateObject = new SystemServerInfo();
		operateObject.setUuid(object.getUuid());
		operateObject.setCreateTime(object.getCreateTime());
		operateObject = CommonBeanUtils.transMap2BasePO(param, operateObject);
		return factory.getCacheWriteDataSession().updateObjectByUUID(SystemServerInfo.class, operateObject);
	}
	
	
	public int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写.
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(SystemServerInfo.class, params, where);
	}
	
	
	public int updateObjectByWhere(SystemServerInfo object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加 
		Param params = null; //ParamBuilder.getInstance().getParam(); //根据实际情况填写 //updateStr
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写. //whereStr
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(SystemServerInfo.class, params, where);
	}

	
	public int deleteObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("id",objId));
		return factory.getCacheWriteDataSession().logicDelete(SystemServerInfo.class, param);
	}


	public int deleteObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv(SystemServerInfo.FIELD_UUID,uuid));
		return factory.getCacheWriteDataSession().logicDelete(SystemServerInfo.class, param);
	}

	
	public int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		Param params = null; //ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("AAA","AAA")); //根据实际情况去填写比较好
		return factory.getCacheWriteDataSession().logicDelete(SystemServerInfo.class, params);
	}


	public int deleteObjectByWhere(SystemServerInfo object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		Param params = null; //ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("AAA","AAA")); //根据实际情况去填写比较好
		return factory.getCacheWriteDataSession().logicDelete(SystemServerInfo.class, params);
	}
	
	
	public List<SystemServerInfo> getObjects(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryAllListResult(SystemServerInfo.class,params);
	}
	
	
	public List<SystemServerInfo> getObjects(SystemServerInfo object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryAllListResult(SystemServerInfo.class,params);
	}
	
	
	public Pagination<SystemServerInfo> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(SystemServerInfo.class, params, pageNo, pageSize, factoryTag);
	}


	public Pagination<SystemServerInfo> paginationObjects(SystemServerInfo object, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return queryClassPagination(SystemServerInfo.class, params, pageNo, pageSize, factoryTag);
	}
	
	
	public SystemServerInfo findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(SystemServerInfo.class, params);
	}
	
	
	public SystemServerInfo findObjectByPros(SystemServerInfo object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().querySingleResultByParams(SystemServerInfo.class, params);
	}
	
	
	public List<SystemServerInfo> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(SystemServerInfo.class, params);
	}
	
	
	public List<SystemServerInfo> findObjectListByPros(SystemServerInfo object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryListResult(SystemServerInfo.class, params);
	}
	
	public int getObjectCount(String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().queryListResultCount(SystemServerInfo.class, ParamBuilder.getInstance().getParam());
	}
	
	
	public int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(SystemServerInfo.class, params);
	}


	public int getObjectCount(SystemServerInfo object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryListResultCount(SystemServerInfo.class, params);
	}

}
