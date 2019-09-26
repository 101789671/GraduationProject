package cn.gpms.dao;

import java.util.List;

import cn.gpms.form.ClassForm;
import cn.gpms.vo.Class;

public interface IClassDAO {
	// 添加班级
	public void addclass(Class myclass);

	// 根据班级名查班级
	public List findClassByName(String classtuName, String instituteName);

	public List findclassAll();

	// 根据班级编号查询
	public List findclass(String classNo);

	// 去重查询 instituteName
	public List disfindclassAll();

	// 去重查询 majorName
	public List disfindMajorName();

	//去重查询instituteName、className
	public List<Object> disfindInsAndclassName();
	
	//去重查询instituteName、className、majorName
	public List disfindInsClaNameMa();

	// 删除班级
	public void deleteclass(Class class1);

	// 更新班级
	public void updataclass(Class class1);

}
