package cn.gpms.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
public class Base2DAO extends HibernateDaoSupport{
	//��ѯ
	public List find(String queryString ,Object[] values){      //queryStringΪhql���    values[]��������ֶ�ֵ�������
		return getHibernateTemplate().find(queryString, values);
	}
	public List findAll(String queryString){
		return getHibernateTemplate().find(queryString);
	}
	public List find(String queryString ,Object value){      //queryStringΪhql���    values[]��������ֶ�ֵ�������
		return getHibernateTemplate().find(queryString, value);
	}
	//��
	public void save(Object entity){
		getHibernateTemplate().save(entity);
	}
	//ɾ
	public void delete(Object entity){
		getHibernateTemplate().delete(entity);
	}
	//��
	public void update(Object entity){
		getHibernateTemplate().update(entity);
	}
}
