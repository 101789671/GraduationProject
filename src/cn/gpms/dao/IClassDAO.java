package cn.gpms.dao;

import java.util.List;

import cn.gpms.form.ClassForm;
import cn.gpms.vo.Class;

public interface IClassDAO {
	// ��Ӱ༶
	public void addclass(Class myclass);

	// ���ݰ༶����༶
	public List findClassByName(String classtuName, String instituteName);

	public List findclassAll();

	// ���ݰ༶��Ų�ѯ
	public List findclass(String classNo);

	// ȥ�ز�ѯ instituteName
	public List disfindclassAll();

	// ȥ�ز�ѯ majorName
	public List disfindMajorName();

	//ȥ�ز�ѯinstituteName��className
	public List<Object> disfindInsAndclassName();
	
	//ȥ�ز�ѯinstituteName��className��majorName
	public List disfindInsClaNameMa();

	// ɾ���༶
	public void deleteclass(Class class1);

	// ���°༶
	public void updataclass(Class class1);

}
