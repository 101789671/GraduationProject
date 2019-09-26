package cn.gpms.service;

import java.util.List;

import cn.gpms.form.StudentForm;
import cn.gpms.vo.Apply;
import cn.gpms.vo.Class;
import cn.gpms.vo.Student;
import cn.gpms.vo.User;

public interface IStudentService {
	// ���ѧ��
	public void addstudent(Student student);

	// ��ѯ��������ѧ��
	public List findStuAll();
	
	//��ѯ��ѡ��ʦ��ѧ��
	public List findStuByAlready();
	
	//��ѯδѡ��ʦ��ѧ��
	public List findStuByNoChooice();

	// ����stuNo��ѧ��������List
	public List findStuBystuNo(String stuNo);

	// ���ݵ�ʦID��ѯѧ��������List
	public List findStuByTutNo(String tutNo);
	
	//���ݵ�ʦID��ѯѧ��������int
	public int findStuByTutNoI(String tutNo);
	
	//����רҵ��ѯѧ��
	public List<Student> findStuByMajorName(String majorName);

	// ɾ��ѧ��
	public void deleteStu(Student student);

	// �޸�ѧ����Ϣ����ʦ��� ��ʦ����
	public void updateStuTut(String stuNo, String tutNo, String tutName);
	
	// �޸�ѧ����Ϣapply - student����Ŀ��
	public String updateStuSub(User user ,Apply apply);
	
	//�޸�ѧ����Ŀ tutor - student
	public void updateStuSubT(Student student);

	// �޸�ѧ����Ϣ��ȫ����
	public void updateStuAll(String classNo, String tutNo,StudentForm studentForm);
	
	
	

}
