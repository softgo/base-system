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
import com.javabase.system.dao.SystemSourceDao;

/**
 * SystemSourceDaoImpl实现
 * 
 * @author 系统生成
 *
 */
@Service("systemSourceDao")
public class SystemSourceDaoImpl extends MultiCacheStrutsBaseDaoImpl<SystemSource> implements SystemSourceDao {
	
	public List<SystemSource> getSystemSourcesByUserId(Integer userId, String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag);
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("userId", userId));
		return factory.getCacheReadDataSession().queryVOList(SystemSource.class, new Throwable(), param);
	}


	public List<SystemSource> getSystemSourcesByRoleId(Integer roleId, String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag);
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("roleId", roleId));
		return factory.getCacheReadDataSession().queryVOList(SystemSource.class, new Throwable(), param);
	}


	public List<SystemSource> getSystemSourcesByUserName(String userName, String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag);
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("userName", userName));
		return factory.getCacheReadDataSession().queryVOList(SystemSource.class, new Throwable(), param);
	}

	public SystemSource saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemSource object = CommonBeanUtils.transMap2BasePO(param, SystemSource.class);
		return factory.getCacheWriteDataSession().saveObject(SystemSource.class,object);
	}

	public SystemSource saveObject(SystemSource object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveObject(SystemSource.class,object);
	}
	
	
	public boolean saveBatchObject(List<SystemSource> objs,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveBatchObject(SystemSource.class,objs);
	}
	
	
	public SystemSource getObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultById(SystemSource.class,"id", objId);
	}


	public SystemSource getObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultByUUID(SystemSource.class, uuid);
	}
	
	
	public int updateObjectByID(Map<String, Object> param, SystemSource object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemSource operateObject = new SystemSource();
		operateObject.setId(object.getId());
		operateObject.setCreateTime(object.getCreateTime());
		operateObject = CommonBeanUtils.transMap2BasePO(param, operateObject);
		return factory.getCacheWriteDataSession().updateObjectByID(SystemSource.class, operateObject);
	}


	public int updateObjectByUUID(Map<String, Object> param, SystemSource object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		SystemSource operateObject = new SystemSource();
		operateObject.setUuid(object.getUuid());
		operateObject.setCreateTime(object.getCreateTime());
		operateObject = CommonBeanUtils.transMap2BasePO(param, operateObject);
		return factory.getCacheWriteDataSession().updateObjectByUUID(SystemSource.class, operateObject);
	}
	
	
	public int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写.
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(SystemSource.class, params, where);
	}
	
	
	public int updateObjectByWhere(SystemSource object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加 
		Param params = null; //ParamBuilder.getInstance().getParam(); //根据实际情况填写 //updateStr
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写. //whereStr
        return factory.getCacheWriteDataSession().updateCustomColumnByWhere(SystemSource.class, params, where);
	}

	
	public int deleteObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("id",objId));
		return factory.getCacheWriteDataSession().logicDelete(SystemSource.class, param);
	}


	public int deleteObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param param = ParamBuilder.getInstance().getParam().add(ParamBuilder.nv(SystemSource.FIELD_UUID,uuid));
		return factory.getCacheWriteDataSession().logicDelete(SystemSource.class, param);
	}

	
	public int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		Param params = null; //ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("AAA","AAA")); //根据实际情况去填写比较好
		return factory.getCacheWriteDataSession().logicDelete(SystemSource.class, params);
	}


	public int deleteObjectByWhere(SystemSource object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		Param params = null; //ParamBuilder.getInstance().getParam().add(ParamBuilder.nv("AAA","AAA")); //根据实际情况去填写比较好
		return factory.getCacheWriteDataSession().logicDelete(SystemSource.class, params);
	}
	
	
	public List<SystemSource> getObjects(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryAllListResult(SystemSource.class,params);
	}
	
	
	public List<SystemSource> getObjects(SystemSource object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryAllListResult(SystemSource.class,params);
	}
	
	
	public Pagination<SystemSource> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(SystemSource.class, params, pageNo, pageSize, factoryTag);
	}


	public Pagination<SystemSource> paginationObjects(SystemSource object, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return queryClassPagination(SystemSource.class, params, pageNo, pageSize, factoryTag);
	}
	
	
	public SystemSource findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(SystemSource.class, params);
	}
	
	
	public SystemSource findObjectByPros(SystemSource object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().querySingleResultByParams(SystemSource.class, params);
	}
	
	
	public List<SystemSource> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(SystemSource.class, params);
	}
	
	
	public List<SystemSource> findObjectListByPros(SystemSource object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryListResult(SystemSource.class, params);
	}
	
	public int getObjectCount(String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().queryListResultCount(SystemSource.class, ParamBuilder.getInstance().getParam());
	}
	
	
	public int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(SystemSource.class, params);
	}


	public int getObjectCount(SystemSource object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));//自己构建条件
		return factory.getCacheReadDataSession().queryListResultCount(SystemSource.class, params);
	}

}
