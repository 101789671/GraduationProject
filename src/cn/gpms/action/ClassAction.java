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
 * ��Ӱ༶
 * ���ݵ�һһ��Ӧ������js�����жϿ���
 */
	public String addclass() throws Exception {
		if(reclassList!=null){
		reclassList.clear();}
		String Eor = "toadd";
		String classNo = classForm.getClassNo();
		String className = classForm.getClasstuName();
		int a =0,b=0;
		List<Class> reclasslist = new ArrayList<Class>(20);
		// �ָ�༶���
		List<String> classNolist = new ArrayList<String>();
		StringTokenizer st1 = new StringTokenizer(classNo, ", ");
		while (st1.hasMoreTokens()) {
			classNolist.add(st1.nextToken());
		}
		// �ָ�༶��
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
				"�ɹ�����  " + a + "  �����ݣ�δ�ɹ�  " + b + "  ����");
		reclassList = reclasslist;
		return Eor;
	}

	/**
	 * ��ѯ���а༶ classlist
	 */
	public String findclassAll(){
		if(classlist!=null){
		classlist.clear();}
		classlist = classService.findclassAll();
		return "listclass";
	}
	
/**
 * ����ɾ��
 */
	public String delclass(){
		if(myclass.getClassNo()!=null){
		  classService.deleteclass(myclass);
		  ActionContext.getContext().put("message", "ɾ���ɹ���");
		}else{
			ActionContext.getContext().put("message", "ɾ��ʧ�ܣ�");
		}
		findclassAll();
		return "listclass";
	}
	
/**
 * ����ɾ��
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
			ActionContext.getContext().put("message", "����ɾ��ʧ�ܣ�");
		} else {
			ActionContext.getContext().put("message", "����ɾ���ɹ���");
		}
		findclassAll();
		return "listclass";
	}

/**
 * �޸İ༶
 */
	public String updataclass() {
		if (myclass.getClassNo() != null) {
			classService.updataclass(myclass);
			ActionContext.getContext().put("message", "��Ϣ�޸ĳɹ���");
		} else {
			ActionContext.getContext().put("message", "��Ϣ�޸�ʧ�ܣ�");
		}
		findclassAll();
		return "listclass";
	}
	
/**
 * ǰ��addclassҳ��
 */
	public String toaddclass(){
		return "toadd";
	}
/**
 * ǰ���޸�classҳ��
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
