package cn.gpms.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.gpms.dao.IClassDAO;
import cn.gpms.form.ClassForm;
import cn.gpms.service.IClassService;
import cn.gpms.vo.Class;

public class ClassService implements IClassService{
	protected IClassDAO classDAO;

	public void addclass(Class class1) {
		classDAO.addclass(class1);
	}
	//根据班级名 、学院查询班级
	public List findClassByName(String classtuName,String instituteName) {
		return classDAO.findClassByName(classtuName,instituteName);
	}
	//查询所有班级
	public List findclassAll(){
		return classDAO.findclassAll();
	}
	//根据班级编号查询
	public List findclass(String classNo){
		return classDAO.findclass(classNo);
	}
	//去重查询 instituteName
	public List disfindclassAll(){
		return classDAO.disfindclassAll();
	}
	//去重查询instituteName、className、majorName
	@SuppressWarnings("unchecked")
	public List disfindInsClaNameMa(){
		List<Class> classlists = new ArrayList<Class>();
		List<Object> Oclasslist = classDAO.disfindInsClaNameMa();
		if (!Oclasslist.isEmpty()) {
			for (int i = 0; i < Oclasslist.size(); i++) {
				Object[] a = (Object[]) Oclasslist.get(i);
				Class class1 = new Class();
				class1.setInstituteName(a[0].toString());
				class1.setInstituteNo(a[1].toString());				
				class1.setMajorName(a[2].toString());
				class1.setMajorNo(a[3].toString());
				class1.setClasstuName(a[4].toString());
				class1.setClassNo(a[5].toString());
				classlists.add(class1);
			}
		}
		
		return classlists;
	}
	
	
	//去重查询 MajorName
	public List disfindMajorName(){
		List<Class> classlists = new ArrayList<Class>();
		List<Object> classlist = classDAO.disfindMajorName();
		if (!classlist.isEmpty()) {
			for (int i = 0; i < classlist.size(); i++) {
				Object[] a = (Object[]) classlist.get(i);
				Class class1 = new Class();
				class1.setInstituteName(a[0].toString());
				class1.setMajorName(a[1].toString());
				class1.setInstituteNo(a[2].toString());
				class1.setMajorNo(a[3].toString());
				classlists.add(class1);
			}
		}
		
//		return classelists;
		return classlists;
	}
	
	//去重查询instituteName、className
	public List disfindInsAndclassName(){
		List<Class> classlists = new ArrayList<Class>();
		List<Object> classlist = classDAO.disfindInsAndclassName();
		if (!classlist.isEmpty()) {
			for (int i = 0; i < classlist.size(); i++) {
				Object[] a = (Object[]) classlist.get(i);
				Class class1 = new Class();
				class1.setInstituteName(a[0].toString());
				class1.setClasstuName(a[1].toString());
				classlists.add(class1);
			}
		}
		
		return classlists;
	}
	
	public void updataclass(Class myclass){
		classDAO.updataclass(myclass);
	}
	//删除班级
	public void deleteclass(Class class1) {
	    classDAO.deleteclass(class1);
	}

	
	
	
	public IClassDAO getClassDAO() {
		return classDAO;
	}

	public void setClassDAO(IClassDAO classDAO) {
		this.classDAO = classDAO;
	}





}
