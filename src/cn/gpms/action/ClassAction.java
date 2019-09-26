package cn.gpms.action;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.gpms.form.ClassForm;
import cn.gpms.service.IClassService;
import cn.gpms.vo.Class;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ClassAction extends ActionSupport {
	private ClassForm classForm = new ClassForm();
	private Class myclass;
	protected IClassService classService;
	
	private List<Class> classlist;
	private List<Class> reclassList;
	
	
/**	
 * 添加班级
 * 数据的一一对应性已由js进行判断控制
 */
	public String addclass() throws Exception {
		if(reclassList!=null){
		reclassList.clear();}
		String Eor = "toadd";
		String classNo = classForm.getClassNo();
		String className = classForm.getClasstuName();
		int a =0,b=0;
		List<Class> reclasslist = new ArrayList<Class>(20);
		// 分割班级编号
		List<String> classNolist = new ArrayList<String>();
		StringTokenizer st1 = new StringTokenizer(classNo, ", ");
		while (st1.hasMoreTokens()) {
			classNolist.add(st1.nextToken());
		}
		// 分割班级名
		List<String> classNamelist = new ArrayList<String>();
		StringTokenizer st2 = new StringTokenizer(className, ", ");
		while (st2.hasMoreTokens()) {
			classNamelist.add(st2.nextToken());
		}

		for (int i = 0, j = 0; i < classNamelist.size(); i++) {
			List adclasslist = classService.findclass(classNolist.get(i));
			if (adclasslist.isEmpty()) {
				Class class1 = new Class();
				class1.setClassNo(classNolist.get(i));
				class1.setClasstuName(classNamelist.get(i));
				class1.setInstituteName(classForm.getInstituteName());
				classService.addclass(class1);
				reclasslist.add(j, class1);
				a = a + 1;
				j++;
				Eor = "success";
			} else {
				b = b + 1;
			}
		}
		ActionContext.getContext().put("message",
				"成功插入  " + a + "  条数据，未成功  " + b + "  条。");
		reclassList = reclasslist;
		return Eor;
	}

	/**
	 * 查询所有班级 classlist
	 */
	public String findclassAll(){
		if(classlist!=null){
		classlist.clear();}
		classlist = classService.findclassAll();
		return "listclass";
	}
	
/**
 * 单个删除
 */
	public String delclass(){
		if(myclass.getClassNo()!=null){
		  classService.deleteclass(myclass);
		  ActionContext.getContext().put("message", "删除成功！");
		}else{
			ActionContext.getContext().put("message", "删除失败！");
		}
		findclassAll();
		return "listclass";
	}
	
/**
 * 批量删除
 */
	public String delclassBatch(){
		String items = ServletActionContext.getRequest().getParameter("delitems"); 
		String Ero = "false";
		if (items != null) {
			String[] strs = items.split(",");
			for(int i=0;i<strs.length;i++){
				Class class1 = new Class();
				class1.setClassNo(strs[i]);
				classService.deleteclass(class1);
				}
			ActionContext.getContext().put("message", "批量删除失败！");
		} else {
			ActionContext.getContext().put("message", "批量删除成功！");
		}
		findclassAll();
		return "listclass";
	}

/**
 * 修改班级
 */
	public String updataclass() {
		if (myclass.getClassNo() != null) {
			classService.updataclass(myclass);
			ActionContext.getContext().put("message", "信息修改成功！");
		} else {
			ActionContext.getContext().put("message", "信息修改失败！");
		}
		findclassAll();
		return "listclass";
	}
	
/**
 * 前往addclass页面
 */
	public String toaddclass(){
		return "toadd";
	}
/**
 * 前往修改class页面
 */
	public String toUpdataclass() {
		String classNo = ServletActionContext.getRequest().getParameter("classNo");
		if (classlist != null) {
			classlist.clear();
		}
		classlist = classService.findclass(classNo);
		return "toUpdataclass";
	}
	
	
	
	
	public ClassForm getClassForm() {
		return classForm;
	}


	public void setClassForm(ClassForm classForm) {
		this.classForm = classForm;
	}


	public IClassService getClassService() {
		return classService;
	}


	public void setClassService(IClassService classService) {
		this.classService = classService;
	}

	public List<Class> getClasslist() {
		return classlist;
	}

	public void setClasslist(List<Class> classlist) {
		this.classlist = classlist;
	}

	public Class getMyclass() {
		return myclass;
	}

	public void setMyclass(Class myclass) {
		this.myclass = myclass;
	}

	public List<Class> getReclassList() {
		return reclassList;
	}

	public void setReclassList(List<Class> reclassList) {
		this.reclassList = reclassList;
	}
	

}
