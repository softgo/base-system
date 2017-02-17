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
import com.javabase.system.entity.SystemSourceRole;
import com.javabase.system.dao.SystemSourceRoleDao;

/**
 * SystemSourceRoleDaoImpl实现
 * 
 * @author 系统生成
 *
 */
@Service("systemSourceRoleDao")
public class SystemSourceRoleDaoImpl extends MultiCacheStrutsBaseDaoImpl<SystemSourceRole> implements SystemSourceRoleDao {
	
	public boolean deleteSystemSourceRoleByRoleId(Integer roleId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag);
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("roleId", roleId));
		List<SystemSourceRole> sourceRoles = factory.getCacheReadDataSession().queryAllListResult(SystemSourceRole.class, param);
		if (sourceRoles.size()>0) {
			for (SystemSourceRole sourceRole : sourceRoles) {
				deleteObjectByID(sourceRole.getId(), factoryTag);
			}
			return true;
		}else{
			return false;
		}
	}

	
	public SystemSourceRole saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemSourceRole object = CommonBeanUtils.transMap2BasePO(param, SystemSourceRole.class);
		return factory.getCacheWriteDataSession().saveObject(SystemSourceRole.class,object);
	}


	public SystemSourceRole saveObject(SystemSourceRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveObject(SystemSourceRole.class,object);
	}
	
	
	public boolean saveBatchObject(List<SystemSourceRole> objs,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveBatchObject(SystemSourceRole.class,objs);
	}
	
	
	public SystemSourceRole getObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultById(SystemSourceRole.class,"id", objId);
	}


	public SystemSourceRole getObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultByUUID(SystemSourceRole.class, uuid);
	}
	
	
	public int updateObjectByID(Map<String, Object> param, SystemSourceRole object ,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemSourceRole operateObject = new SystemSourceRole();
		operateObject.setId(object.getId());
		operateObject.setCreateTime(object.getCreateTime());
		operateObject = CommonBeanUtils.transMap2BasePO(param, operateObject);
		return factory.getCacheWriteDataSession().updateObjectByID(SystemSourceRole.class, operateObject);
	}


	public int updateObjectByUUID(Map<String, Object> param, SystemSourceRole object ,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemSourceRole operateObject = new SystemSourceRole();
		operateObject.setUuid(object.getUuid());
		operateObject.setCreateTime(object.getCreateTime());
		operateObject = CommonBeanUtils.transMap2BasePO(param, operateObject);
		return factory.getCacheWriteDataSession().updateObjectByUUID(SystemSourceRole.class, operateObject);
	}
	
	
	public int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写.
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(SystemSourceRole.class, params, where);
	}
	
	
	public int updateObjectByWhere(SystemSourceRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加 
		Param params = null; //ParamBuilder.getInstance().getParam(); //根据实际情况填写 //updateStr
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写. //whereStr
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(SystemSourceRole.class, params, where);
	}

	
	public int deleteObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("id",objId));
		return factory.getCacheWriteDataSession().logicDelete(SystemSourceRole.class, param);
	}


	public int deleteObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv(SystemSourceRole.FIELD_UUID,uuid));
		return factory.getCacheWriteDataSession().logicDelete(SystemSourceRole.class, param);
	}

	
	public int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		Param params = null; //ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("AAA","AAA")); //根据实际情况去填写比较好
		return factory.getCacheWriteDataSession().logicDelete(SystemSourceRole.class, params);
	}


	public int deleteObjectByWhere(SystemSourceRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		Param params = null; //ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("AAA","AAA")); //根据实际情况去填写比较好
		return factory.getCacheWriteDataSession().logicDelete(SystemSourceRole.class, params);
	}
	
	
	public List<SystemSourceRole> getObjects(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryAllListResult(SystemSourceRole.class,params);
	}
	
	
	public List<SystemSourceRole> getObjects(SystemSourceRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryAllListResult(SystemSourceRole.class,params);
	}
	
	
	public Pagination<SystemSourceRole> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(SystemSourceRole.class, params, pageNo, pageSize, factoryTag);
	}


	public Pagination<SystemSourceRole> paginationObjects(SystemSourceRole object, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return queryClassPagination(SystemSourceRole.class, params, pageNo, pageSize, factoryTag);
	}
	
	
	public SystemSourceRole findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(SystemSourceRole.class, params);
	}
	
	
	public SystemSourceRole findObjectByPros(SystemSourceRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().querySingleResultByParams(SystemSourceRole.class, params);
	}
	
	
	public List<SystemSourceRole> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(SystemSourceRole.class, params);
	}
	
	
	public List<SystemSourceRole> findObjectListByPros(SystemSourceRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryListResult(SystemSourceRole.class, params);
	}
	
	public int getObjectCount(String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().queryListResultCount(SystemSourceRole.class, ParamBuilder.getInstance().getParam());
	}
	
	
	public int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(SystemSourceRole.class, params);
	}


	public int getObjectCount(SystemSourceRole object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryListResultCount(SystemSourceRole.class, params);
	}


}
