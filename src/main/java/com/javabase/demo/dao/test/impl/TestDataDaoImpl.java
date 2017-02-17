package com.javabase.demo.dao.test.impl;

import java.util.List;
import java.util.Map;
import java.util.Arrays;

import org.springframework.stereotype.Repository;

import com.application.base.core.apisupport.impl.MultiCacheStrutsBaseDaoImpl;
import com.application.base.core.apisupport.impl.MultiStrutsBaseDaoImpl;
import com.application.base.core.datasource.param.CustomSQL;
import com.application.base.core.datasource.param.ESQLOperator;
import com.application.base.core.datasource.param.Param;
import com.application.base.core.datasource.param.ParamBuilder;
import com.application.base.core.datasource.param.SQLCreator;
import com.application.base.core.exception.BusinessException;
import com.application.base.core.obj.Pagination;
import com.application.base.core.utils.CommonBeanUtils;


import com.javabase.demo.entity.test.TestData;
import com.javabase.demo.dao.test.TestDataDao;

/**
 * TestDataDaoImpl实现
 * 
 * @author 系统生成
 *
 */
@Repository("testDataDao")

public class TestDataDaoImpl extends MultiCacheStrutsBaseDaoImpl<TestData> implements TestDataDao {
	
	public TestData saveObject(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		TestData object = CommonBeanUtils.transMap2BasePO(param, TestData.class);
		return factory.getCacheWriteDataSession().saveObject(TestData.class,object);
	}


	public TestData saveObject(TestData object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveObject(TestData.class,object);
	}
	
	
	public boolean saveBatchObject(List<TestData> objs,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().saveBatchObject(TestData.class,objs);
	}
	
	
	public TestData getObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().querySingleResultById(TestData.class,"id", objId);
	}


	public TestData getObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		return null;
	}
	
	
	public int updateObjectByID(Map<String, Object> param, TestData object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		TestData operateObject = new TestData();
		operateObject.setId(object.getId());
		operateObject.setCreateTime(object.getCreateTime());
		operateObject = CommonBeanUtils.transMap2BasePO(param, operateObject);
		return factory.getCacheWriteDataSession().updateObjectByID(TestData.class, operateObject);
	}
	
	
	public int updateObjectByUUID(Map<String, Object> param, TestData object,String factoryTag) throws BusinessException {
		return 0;
	}
	
	
	public int updateObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写.
		return factory.getCacheWriteDataSession().updateCustomColumnByWhere(TestData.class, params, where);
	}
	
	
	public int updateObjectByWhere(TestData object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加 
		Param params = null; //ParamBuilder.getInstance().getParam(); //根据实际情况填写 //updateStr
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写. //whereStr
		return factory.getCacheWriteDataSession().updateCustomColumnByWhere(TestData.class, params, where);
	}

	
	public int deleteObjectByID(Object objId,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheWriteDataSession().physicalDelete(TestData.class,"id", objId);
	}


	public int deleteObjectByUUID(String uuid,String factoryTag) throws BusinessException {
		return 0;  //没有uuid,该方法就为不可用
	}

	
	public int deleteObjectByWhere(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写. //whereStr
		return factory.getCacheWriteDataSession().physicalWhereDelete(TestData.class, where);
	}


	public int deleteObjectByWhere(TestData object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己设置访问的数据库.
		//根据实际情况填写要查的列和对应的值.
		CustomSQL where = null; //SQLCreator.where().cloumn("AAA").operator(ESQLOperator.EQ).value("AAA"); //根据实际情况填写.
		return factory.getCacheWriteDataSession().physicalWhereDelete(TestData.class, where);
	}
	
	
	public List<TestData> getObjects(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryAllListResult(TestData.class,params);
	}
	
	
	public List<TestData> getObjects(TestData object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));
		return factory.getCacheReadDataSession().queryAllListResult(TestData.class,params);
	}
	
	
	public Pagination<TestData> paginationObjects(Map<String, Object> param, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return queryClassPagination(TestData.class, params, pageNo, pageSize, factoryTag);
	}


	public Pagination<TestData> paginationObjects(TestData object, int pageNo, int pageSize,String factoryTag)
			throws BusinessException {
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));
		return queryClassPagination(TestData.class, params, pageNo, pageSize, factoryTag);
	}
	
	
	public TestData findObjectByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().querySingleResultByParams(TestData.class, params);
	}
	
	
	public TestData findObjectByPros(TestData object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));
		return factory.getCacheReadDataSession().querySingleResultByParams(TestData.class, params);
	}
	
	
	public List<TestData> findObjectListByPros(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResult(TestData.class, params);
	}
	
	
	public List<TestData> findObjectListByPros(TestData object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));
		return factory.getCacheReadDataSession().queryListResult(TestData.class, params);
	}
	
	public int getObjectCount(String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		return factory.getCacheReadDataSession().queryListResultCount(TestData.class, ParamBuilder.getInstance().getParam());
	}
	
	
	public int getObjectCount(Map<String, Object> param,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(param);
		return factory.getCacheReadDataSession().queryListResultCount(TestData.class, params);
	}


	public int getObjectCount(TestData object,String factoryTag) throws BusinessException {
		factory.setFactoryTag(factoryTag); //根据实际情况自己添加
		Param params = ParamBuilder.getInstance().getParam().add(CommonBeanUtils.transBean2Map(object));
		return factory.getCacheReadDataSession().queryListResultCount(TestData.class, params);
	}

}
