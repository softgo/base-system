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
import com.javabase.system.entity.SystemRole;
import com.javabase.system.dao.SystemRoleDao;

/**
 * SystemRoleDaoImpl实现
 * 
 * @author 系统生成
 *
 */
@Service("systemRoleDao")
public class SystemRoleDaoImpl extends MultiCacheStrutsBaseDaoImpl<SystemRole> implements SystemRoleDao {
	
	public List<SystemRole> findSystemRolesByUserId(Integer userId, String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag);
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("userId", userId));
		return factory.getCacheReadDataSession().queryVOList(SystemRole.class, new Throwable(), param);
	}
	
	public SystemRole saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemRole object = CommonBeanUtils.transMap2BasePO(param, SystemRole.class);
		return factory.getCacheWriteDataSession().saveObject(SystemRole.class,object);
	}


	public SystemRole saveObject(SystemRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveObject(SystemRole.class,object);
	}
	
	
	public boolean saveBatchObject(List<SystemRole> objs,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveBatchObject(SystemRole.class,objs);
	}
	
	
	public SystemRole getObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultById(SystemRole.class,"id", objId);
	}


	public SystemRole getObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultByUUID(SystemRole.class, uuid);
	}
	
	
	public int updateObjectByID(Map<String, Object> param, SystemRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemRole operateObject = new SystemRole();
		operateObject.setId(object.getId());
		operateObject.setCreateTime(object.getCreateTime());
		operateObject = CommonBeanUtils.transMap2BasePO(param, operateObject);
		return factory.getCacheWriteDataSession().updateObjectByID(SystemRole.class, operateObject);
	}

	
	public int updateObjectByUUID(Map<String, Object> param, SystemRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemRole operateObject = new SystemRole();
		operateObject.setUuid(object.getUuid());
		operateObject.setCreateTime(object.getCreateTime());
		operateObject = CommonBeanUtils.transMap2BasePO(param, operateObject);
		return factory.getCacheWriteDataSession().updateObjectByUUID(SystemRole.class, operateObject);
	}
	
	
	public int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写.
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(SystemRole.class, params, where);
	}
	
	
	public int updateObjectByWhere(SystemRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加 
		Param params = null; //ParamBuilder.getInstance().getParam(); //根据实际情况填写 //updateStr
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写. //whereStr
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(SystemRole.class, params, where);
	}

	
	public int deleteObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("id",objId));
		return factory.getCacheWriteDataSession().logicDelete(SystemRole.class, param);
	}


	public int deleteObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv(SystemRole.FIELD_UUID,uuid));
		return factory.getCacheWriteDataSession().logicDelete(SystemRole.class, param);
	}

	
	public int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		Param params = null; //ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("AAA","AAA")); //根据实际情况去填写比较好
		return factory.getCacheWriteDataSession().logicDelete(SystemRole.class, params);
	}


	public int deleteObjectByWhere(SystemRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		Param params = null; //ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("AAA","AAA")); //根据实际情况去填写比较好
		return factory.getCacheWriteDataSession().logicDelete(SystemRole.class, params);
	}
	
	
	public List<SystemRole> getObjects(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryAllListResult(SystemRole.class,params);
	}
	
	
	public List<SystemRole> getObjects(SystemRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object)); //自己构建条件
		return factory.getCacheReadDataSession().queryAllListResult(SystemRole.class,params);
	}
	
	
	public Pagination<SystemRole> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(SystemRole.class, params, pageNo, pageSize, factoryTag);
	}


	public Pagination<SystemRole> paginationObjects(SystemRole object, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return queryClassPagination(SystemRole.class, params, pageNo, pageSize, factoryTag);
	}
	
	
	public SystemRole findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(SystemRole.class, params);
	}
	
	
	public SystemRole findObjectByPros(SystemRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().querySingleResultByParams(SystemRole.class, params);
	}
	
	
	public List<SystemRole> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(SystemRole.class, params);
	}
	
	
	public List<SystemRole> findObjectListByPros(SystemRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryListResult(SystemRole.class, params);
	}
	
	public int getObjectCount(String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().queryListResultCount(SystemRole.class, ParamBuilder.getInstance().getParam());
	}
	
	
	public int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(SystemRole.class, params);
	}


	public int getObjectCount(SystemRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryListResultCount(SystemRole.class, params);
	}

}
