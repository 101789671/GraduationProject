package cn.gpms.service;

import java.util.List;

import cn.gpms.form.ClassForm;
import cn.gpms.vo.Class;

public interface IClassService {
	public void addclass(Class class1);

	// ���ݰ༶�� ��ѧԺ��ѯ�༶
	public List findClassByName(String classtuName, String instituteName);

	// ��ѯ����
	public List findclassAll();

	// ���ݰ༶�Ų�ѯ
	public List findclass(String classNo);

	// ȥ�ز�ѯ instituteName
	public List disfindclassAll();
	
	//ȥ�ز�ѯ majorName
	public List disfindMajorName();
	
	//ȥ�ز�ѯinstituteName��className
	public List disfindInsAndclassName();
	
	//ȥ�ز�ѯinstituteName��className��majorName
	public List disfindInsClaNameMa();

	// �޸İ༶
	public void updataclass(Class class1);

	// ɾ���༶
	public void deleteclass(Class class1);

}
