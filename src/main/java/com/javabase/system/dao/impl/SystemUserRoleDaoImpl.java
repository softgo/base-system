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
import com.javabase.system.entity.SystemSource;
import com.javabase.system.entity.SystemUserRole;
import com.javabase.system.dao.SystemUserRoleDao;

/**
 * SystemUserRoleDaoImpl实现
 * 
 * @author 系统生成
 *
 */
@Service("systemUserRoleDao")
public class SystemUserRoleDaoImpl extends MultiCacheStrutsBaseDaoImpl<SystemUserRole> implements SystemUserRoleDao {

	
	public boolean deleteSystemUserRoleByPorps(SystemUserRole userRole, String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag);
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("userId", userRole.getUserId())).add(ParamBuilder.nv("roleId", userRole.getRoleId()));
		int count= factory.getCacheWriteDataSession().logicDelete(SystemUserRole.class, param);
		if (count>0) {
			return true;
		}else{
			return false;
		}
	}
	
	public SystemUserRole saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemUserRole object = CommonBeanUtils.transMap2BasePO(param, SystemUserRole.class);
		return factory.getCacheWriteDataSession().saveObject(SystemUserRole.class,object);
	}


	public SystemUserRole saveObject(SystemUserRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveObject(SystemUserRole.class,object);
	}
	
	
	public boolean saveBatchObject(List<SystemUserRole> objs,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveBatchObject(SystemUserRole.class,objs);
	}
	
	
	public SystemUserRole getObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultById(SystemUserRole.class,"id", objId);
	}


	public SystemUserRole getObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultByUUID(SystemUserRole.class, uuid);
	}
	
	
	public int updateObjectByID(Map<String, Object> param, SystemUserRole object ,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemUserRole operateObject = new SystemUserRole();
		operateObject.setId(object.getId());
		operateObject.setCreateTime(object.getCreateTime());
		operateObject = CommonBeanUtils.transMap2BasePO(param, operateObject);
		return factory.getCacheWriteDataSession().updateObjectByID(SystemUserRole.class, operateObject);
	}


	public int updateObjectByUUID(Map<String, Object> param, SystemUserRole object ,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemUserRole operateObject = new SystemUserRole();
		operateObject.setUuid(object.getUuid());
		operateObject.setCreateTime(object.getCreateTime());
		operateObject = CommonBeanUtils.transMap2BasePO(param, operateObject);
		return factory.getCacheWriteDataSession().updateObjectByUUID(SystemUserRole.class, operateObject);
	}
	
	
	public int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写.
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(SystemUserRole.class, params, where);
	}
	
	
	public int updateObjectByWhere(SystemUserRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加 
		Param params = null; //ParamBuilder.getInstance().getParam(); //根据实际情况填写 //updateStr
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写. //whereStr
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(SystemUserRole.class, params, where);
	}

	
	public int deleteObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("id",objId));
		return factory.getCacheWriteDataSession().logicDelete(SystemUserRole.class, param);
	}


	public int deleteObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv(SystemUserRole.FIELD_UUID,uuid));
		return factory.getCacheWriteDataSession().logicDelete(SystemUserRole.class, param);
	}

	
	public int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		Param params = null; //ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("AAA","AAA")); //根据实际情况去填写比较好
		return factory.getCacheWriteDataSession().logicDelete(SystemUserRole.class, params);
	}


	public int deleteObjectByWhere(SystemUserRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		Param params = null; //ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("AAA","AAA")); //根据实际情况去填写比较好
		return factory.getCacheWriteDataSession().logicDelete(SystemUserRole.class, params);
	}
	
	
	public List<SystemUserRole> getObjects(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryAllListResult(SystemUserRole.class,params);
	}
	
	
	public List<SystemUserRole> getObjects(SystemUserRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryAllListResult(SystemUserRole.class,params);
	}
	
	
	public Pagination<SystemUserRole> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(SystemUserRole.class, params, pageNo, pageSize, factoryTag);
	}


	public Pagination<SystemUserRole> paginationObjects(SystemUserRole object, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return queryClassPagination(SystemUserRole.class, params, pageNo, pageSize, factoryTag);
	}
	
	
	public SystemUserRole findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(SystemUserRole.class, params);
	}
	
	
	public SystemUserRole findObjectByPros(SystemUserRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().querySingleResultByParams(SystemUserRole.class, params);
	}
	
	
	public List<SystemUserRole> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(SystemUserRole.class, params);
	}
	
	
	public List<SystemUserRole> findObjectListByPros(SystemUserRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryListResult(SystemUserRole.class, params);
	}
	
	public int getObjectCount(String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().queryListResultCount(SystemUserRole.class, ParamBuilder.getInstance().getParam());
	}
	
	
	public int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(SystemUserRole.class, params);
	}


	public int getObjectCount(SystemUserRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryListResultCount(SystemUserRole.class, params);
	}



}
