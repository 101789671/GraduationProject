package cn.gpms.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.BaseDAO;
import cn.gpms.dao.IStudentDAO;
import cn.gpms.vo.Class;
import cn.gpms.vo.Student;

public class StudentDAO extends Base2DAO implements IStudentDAO {
/**
 * ���ѧ��
 */
	public void addstudent(Student student) {
		save(student);
	}

/**	
 * ����stuNo��ѧ��
 * @return Lsit
 */
public List findStuBystuNo(String stuNo){
    String hql = "from Student where stuNo=?";
    return find(hql, stuNo);
}

/**
 * ��ѯ����ѧ��
 */
	public List findStuAll() {
		String hql = "from Student ";
		return findAll(hql);
	}
/**
 * ɾ��ѧ��
 */
	public void deleteStu(Student student){
		delete(student);
	}
	
/**
 * �޸�ѧ��
 */
	public void updateStu(Student student) {	
		update(student);
	}		
/**
 * ���ݵ�ʦID��ѯѧ��
 */
public List findStuByTutNo(String tutNo) {
	String hql = "from Student where tutNo=?";
	return find(hql, tutNo);
}
	
/**
 * ����רҵ��ѯѧ��
 */
	public List findStuByMajorName(String majorName) {
		String hql = "from Student where majorName=?";
		Object value = majorName;
		return find(hql, value);
	}

}



