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
 * 添加学生
 */
	public void addstudent(Student student) {
		save(student);
	}

/**	
 * 根据stuNo查学生
 * @return Lsit
 */
public List findStuBystuNo(String stuNo){
    String hql = "from Student where stuNo=?";
    return find(hql, stuNo);
}

/**
 * 查询所有学生
 */
	public List findStuAll() {
		String hql = "from Student ";
		return findAll(hql);
	}
/**
 * 删除学生
 */
	public void deleteStu(Student student){
		delete(student);
	}
	
/**
 * 修改学生
 */
	public void updateStu(Student student) {	
		update(student);
	}		
/**
 * 根据导师ID查询学生
 */
public List findStuByTutNo(String tutNo) {
	String hql = "from Student where tutNo=?";
	return find(hql, tutNo);
}
	
/**
 * 根据专业查询学生
 */
	public List findStuByMajorName(String majorName) {
		String hql = "from Student where majorName=?";
		Object value = majorName;
		return find(hql, value);
	}

}



