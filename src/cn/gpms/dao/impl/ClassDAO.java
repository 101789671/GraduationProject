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
 * ��Ӱ༶
 */
	public void addclass(Class myclass) {
		save(myclass);
	}

/**
 * ���ݰ༶����ѧԺ��༶
 */
	public List findClassByName(String classtuName, String instituteName) {
		String hql = "from Class where classtuName=? and instituteName=?";
		Object[] values = { classtuName, instituteName };
		return find(hql, values);
	}

/**
 * ��ѯ���а༶
 */
	public List findclassAll() {
		String hql = "from Class ";
		return findAll(hql);
	}

/**
 * ��classNo��༶
 */
	public List findclass(String classNo) {
		String hql = "from Class where classNo=? ";
		return find(hql, classNo);
	}

/**
 * ȥ�ز�ѯ instituteName
 */
	public List disfindclassAll() {
		String hql = "select distinct instituteName from Class ";
		return findAll(hql);
	}

/**
 * ȥ�ز�ѯ instituteName, majorName,majorNo
 */
	public List disfindMajorName() {
		String hql = "select distinct instituteName,majorName,instituteNo,majorNo from Class ";
		return findAll(hql);
	}
	
/**
 * ȥ�ز�ѯinstituteName��className
 */
	public List disfindInsAndclassName(){
		String hql = "select distinct instituteName,classtuName from Class";
		return findAll(hql);
	}
	
/**
 * ȥ�ز�ѯinstituteName��className��majorName
 */
	public List disfindInsClaNameMa(){
		String hql = "select distinct instituteName,instituteNo,majorName,majorNo,classtuName,classNo from Class";
		return findAll(hql);
	}

/**
 * ɾ���༶
 */
	public void deleteclass(Class class1) {
		delete(class1);
	}

/**
 * �޸İ༶
 */
	public void updataclass(Class class1) {
		update(class1);
	}

}