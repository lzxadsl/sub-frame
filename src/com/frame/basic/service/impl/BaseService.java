package com.frame.basic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import com.frame.basic.dao.IBaseDao;
import com.frame.basic.model.PageData;
import com.frame.basic.service.IBaseService;

/**
 * 公共接口实现类
 * @author LiZhiXian
 * @version 1.0
 * @param <T,K> T 实体 K 主键类型
 * @date 2015-9-18 上午11:32:19
 */
public class BaseService<T,K> extends SqlSessionTemplate implements IBaseService<T,K>{

	private Class<? extends IBaseDao<T, K>> daoClass; 
	
	/**
	 * @param sqlSessionFactory
	 */
	public BaseService(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}
	
	/**
	 * 设置Mapper配置文件对应的DAO接口
	 */
	public void setDaoClass(Class<? extends IBaseDao<T, K>> daoClass){
		this.daoClass = daoClass;
	}

	/**
	 * 获取DAO接口
	 */
	public IBaseDao<T, K> getDao(){
		return this.getMapper(daoClass);
	}

	@Override
	public T save(T entity) {
		getDao().insert(entity);
		return entity;
	}

	@Override
	public int saveList(List<T> list) {
		return 0;
	}

	@Override
	public void delete(K id) {
		getDao().delete(id);
	}

	@Override
	public void deleteList(Map<String, Object> params) {
		if(params == null)params = new HashMap<String, Object>();
		getDao().deleteList(params);
	}

	@Override
	public T update(T entity) {
		getDao().update(entity);
		return entity;
	}

	@Override
	public T get(K id) {
		return getDao().get(id);
	}

	@Override
	public T get(Map<String, Object> params) {
		if(params == null)params = new HashMap<String, Object>();
		return getDao().get(params);
	}

	@Override
	public List<T> list() {
		Map<String, Object> params = new HashMap<String, Object>();
		return getDao().selectList(params);
	}

	@Override
	public List<T> list(Map<String, Object> params) {
		if(params == null)params = new HashMap<String, Object>();
		return getDao().selectList(params);
	}

	@Override
	public List<T> listPage(PageData pageData) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pageData", pageData);
		return getDao().selectPageList(params);
	}

	@Override
	public List<T> listPage(Map<String, Object> params,PageData pageData) {
		if(params == null)params = new HashMap<String, Object>();
		params.put("pageData", pageData);
		return getDao().selectPageList(params);
	}

	
}
