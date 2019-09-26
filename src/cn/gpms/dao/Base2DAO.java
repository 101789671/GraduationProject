package cn.gpms.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
public class Base2DAO extends HibernateDaoSupport{
	//查询
	public List find(String queryString ,Object[] values){      //queryString为hql语句    values[]存放条件字段值（多个）
		return getHibernateTemplate().find(queryString, values);
	}
	public List findAll(String queryString){
		return getHibernateTemplate().find(queryString);
	}
	public List find(String queryString ,Object value){      //queryString为hql语句    values[]存放条件字段值（多个）
		return getHibernateTemplate().find(queryString, value);
	}
	//增
	public void save(Object entity){
		getHibernateTemplate().save(entity);
	}
	//删
	public void delete(Object entity){
		getHibernateTemplate().delete(entity);
	}
	//改
	public void update(Object entity){
		getHibernateTemplate().update(entity);
	}
}
