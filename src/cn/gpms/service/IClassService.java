package cn.gpms.service;

import java.util.List;

import cn.gpms.form.ClassForm;
import cn.gpms.vo.Class;

public interface IClassService {
	public void addclass(Class class1);

	// 根据班级名 、学院查询班级
	public List findClassByName(String classtuName, String instituteName);

	// 查询所有
	public List findclassAll();

	// 根据班级号查询
	public List findclass(String classNo);

	// 去重查询 instituteName
	public List disfindclassAll();
	
	//去重查询 majorName
	public List disfindMajorName();
	
	//去重查询instituteName、className
	public List disfindInsAndclassName();
	
	//去重查询instituteName、className、majorName
	public List disfindInsClaNameMa();

	// 修改班级
	public void updataclass(Class class1);

	// 删除班级
	public void deleteclass(Class class1);

}
