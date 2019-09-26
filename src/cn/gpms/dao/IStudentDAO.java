package cn.gpms.dao;

import java.util.List;

import cn.gpms.form.StudentForm;
import cn.gpms.vo.Class;
import cn.gpms.vo.Student;

public interface IStudentDAO {
	//添加学生
	public void addstudent(Student student);
//	//根据stuNo查学生，返回Student
//	public Student findStuByid(String stuNo);
	
	
	//查询所有所有学生
	public List findStuAll() ;
	//根据stuNo查学生，返回List
	public List findStuBystuNo(String stuNo);
	//根据导师ID查询学生
	public List findStuByTutNo(String tutNo);
	//根据专业查询学生
	public List<Student> findStuByMajorName(String majorName);
	
	
	//删除学生
	public void deleteStu(Student student);


	//修改学生信息（导师编号 导师名）
	public void updateStu(Student student);
//	//修改学生信息（全部）
//	public void updateStuAll(String stuNo,String stuName,String sex,String classNo,String className,String instituteName,String tutNo,String tutName,String updateTime);

}
