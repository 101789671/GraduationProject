package cn.gpms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.*;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.BaseDAO;
import cn.gpms.dao.IClassDAO;
import cn.gpms.form.ClassForm;

import cn.gpms.vo.Class;

public class ClassDAO extends Base2DAO implements IClassDAO {
/**
 * 添加班级
 */
	public void addclass(Class myclass) {
		save(myclass);
	}

/**
 * 根据班级名、学院查班级
 */
	public List findClassByName(String classtuName, String instituteName) {
		String hql = "from Class where classtuName=? and instituteName=?";
		Object[] values = { classtuName, instituteName };
		return find(hql, values);
	}

/**
 * 查询所有班级
 */
	public List findclassAll() {
		String hql = "from Class ";
		return findAll(hql);
	}

/**
 * 按classNo查班级
 */
	public List findclass(String classNo) {
		String hql = "from Class where classNo=? ";
		return find(hql, classNo);
	}

/**
 * 去重查询 instituteName
 */
	public List disfindclassAll() {
		String hql = "select distinct instituteName from Class ";
		return findAll(hql);
	}

/**
 * 去重查询 instituteName, majorName,majorNo
 */
	public List disfindMajorName() {
		String hql = "select distinct instituteName,majorName,instituteNo,majorNo from Class ";
		return findAll(hql);
	}
	
/**
 * 去重查询instituteName、className
 */
	public List disfindInsAndclassName(){
		String hql = "select distinct instituteName,classtuName from Class";
		return findAll(hql);
	}
	
/**
 * 去重查询instituteName、className、majorName
 */
	public List disfindInsClaNameMa(){
		String hql = "select distinct instituteName,instituteNo,majorName,majorNo,classtuName,classNo from Class";
		return findAll(hql);
	}

/**
 * 删除班级
 */
	public void deleteclass(Class class1) {
		delete(class1);
	}

/**
 * 修改班级
 */
	public void updataclass(Class class1) {
		update(class1);
	}

}