package cn.gpms.dao;

import java.util.List;

import cn.gpms.form.StudentForm;
import cn.gpms.vo.Class;
import cn.gpms.vo.Student;

public interface IStudentDAO {
	//���ѧ��
	public void addstudent(Student student);
//	//����stuNo��ѧ��������Student
//	public Student findStuByid(String stuNo);
	
	
	//��ѯ��������ѧ��
	public List findStuAll() ;
	//����stuNo��ѧ��������List
	public List findStuBystuNo(String stuNo);
	//���ݵ�ʦID��ѯѧ��
	public List findStuByTutNo(String tutNo);
	//����רҵ��ѯѧ��
	public List<Student> findStuByMajorName(String majorName);
	
	
	//ɾ��ѧ��
	public void deleteStu(Student student);


	//�޸�ѧ����Ϣ����ʦ��� ��ʦ����
	public void updateStu(Student student);
//	//�޸�ѧ����Ϣ��ȫ����
//	public void updateStuAll(String stuNo,String stuName,String sex,String classNo,String className,String instituteName,String tutNo,String tutName,String updateTime);

}
