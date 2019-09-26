package cn.gpms.service;

import java.util.List;

import cn.gpms.form.StudentForm;
import cn.gpms.vo.Apply;
import cn.gpms.vo.Class;
import cn.gpms.vo.Student;
import cn.gpms.vo.User;

public interface IStudentService {
	// 添加学生
	public void addstudent(Student student);

	// 查询所有所有学生
	public List findStuAll();
	
	//查询已选导师的学生
	public List findStuByAlready();
	
	//查询未选导师的学生
	public List findStuByNoChooice();

	// 根据stuNo查学生，返回List
	public List findStuBystuNo(String stuNo);

	// 根据导师ID查询学生，返回List
	public List findStuByTutNo(String tutNo);
	
	//根据导师ID查询学生，返回int
	public int findStuByTutNoI(String tutNo);
	
	//根据专业查询学生
	public List<Student> findStuByMajorName(String majorName);

	// 删除学生
	public void deleteStu(Student student);

	// 修改学生信息（导师编号 导师名）
	public void updateStuTut(String stuNo, String tutNo, String tutName);
	
	// 修改学生信息apply - student（题目）
	public String updateStuSub(User user ,Apply apply);
	
	//修改学生题目 tutor - student
	public void updateStuSubT(Student student);

	// 修改学生信息（全部）
	public void updateStuAll(String classNo, String tutNo,StudentForm studentForm);
	
	
	

}
