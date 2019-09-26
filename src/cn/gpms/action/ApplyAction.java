package cn.gpms.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.ServletActionContext;

import cn.gpms.action.TutorAction;
import cn.gpms.form.StudentApply;
import cn.gpms.form.StudentForm;

import cn.gpms.service.IApplyService;
import cn.gpms.service.IClassService;
import cn.gpms.service.IStudentService;
import cn.gpms.service.ITutorService;
import cn.gpms.vo.Apply;
import cn.gpms.vo.Class;
import cn.gpms.vo.Student;
import cn.gpms.vo.Tutor;
import cn.gpms.vo.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ApplyAction extends ActionSupport{
	
	private StudentForm studentForm = new StudentForm();
	
	protected IApplyService applyService;
	protected ITutorService tutorService;
	protected IStudentService studentService;
	protected IClassService classService;
	
	public List<StudentApply> studentapplyList;
	public List<Student> studentList;
	private List<Class> classlist;
	private List<Tutor> tutorList;
	private List<Tutor> tutorList2;


/**
 * ����-���(�Զ�)���䴦��
 */
	@SuppressWarnings("unused")
	public String randomAllot() {
		List<Student> studentlist = applyService.randomAllot(studentForm.getStuNo());
		examine_children(null, "CD", "�����");
		if(studentlist.isEmpty()){
			ActionContext.getContext().put("message", "�Զ�����ʧ�ܣ�����ѧ���Ѵ��ڵ�ʦ��");
		}
		return "toAllotApply";
	}

/**
 * ����-���(�Զ�)���䴦��
 */
	public String batchRaAllot(){
		String items = ServletActionContext.getRequest().getParameter("delitems"); 
		String Ero = "false";
		if (items != null) {
			String[] strs = items.split(",");
			for(int i=0;i<strs.length;i++){
				studentList = applyService.randomAllot(strs[i]);
				}
			ActionContext.getContext().put("message", "��������ɹ���");
		} else {
			ActionContext.getContext().put("message", "��������ʧ�ܣ�");
		}
		return "toChoiceTut";
	}
	
	
/**
 * �˹����䴦��
 */
	public String allotApply(){
		if(!"".equals(studentForm.getTutName())&&studentForm.getTutName()!=null){
		applyService.allotApplys(studentForm.getStuNo(), studentForm.getTutName());
		examine_children(null, "CD", "�����");
		}else{
			ActionContext.getContext().put("message", "����ʧ�ܣ���δѡ��ʦ��");
		}
		return "toAllotApply";
	}

	
	
/**
 * ǰ���˹�����
 */
	@SuppressWarnings("unchecked")
	public String toAllotCT(){
		String stuNo = ServletActionContext.getRequest().getParameter("stuNo");
		int a[] = new int[10];
		studentList = studentService.findStuBystuNo(stuNo);
		tutorList = tutorService.findTuAll();
		classlist = classService.findclassAll();
		Student student = (Student) studentService.findStuBystuNo(stuNo).get(0);
		tutorList2 = tutorService.findTutorByNo(student.getTutNo());
		return "toAllotCT";
	}

/**
 * ǰ����������
 */
	public String toAllotApply(){
		examine_children(null, "CD", "�����");
		return "toAllotApply";
	}
/**
 * ��ѯCD�����¼
 */
	public String recordCD(){
		examine_children(null, "CD", null);
		return "recordCDlist";
	}
	
/**
 * ��ѯ����Ĺ��÷���
 * @param user ��ǰ��¼���û�
 * @param typeNumber ��������
 * @param apState ����״̬
 */
public void examine_children(User user,String typeNumber,String apState){
		Apply apply = new Apply();
		Student student = new Student();
		List<StudentApply> studentApplyList2 = new ArrayList<StudentApply>();
		List Alist = new ArrayList<Apply>();
		if(user!=null && typeNumber!=null && apState!=null){Alist = applyService.findApply2(user.getUserid(), typeNumber, apState);}
		if(user!=null && typeNumber!=null && apState==null){Alist = applyService.findApply6(user.getUserid(), typeNumber);}
		if(user!=null && typeNumber==null && apState==null){Alist = applyService.findApplyByRID(user.getUserid());}
		if(user==null && typeNumber!=null && apState!=null){Alist = applyService.findApply4(typeNumber, apState);}
		if(user==null && typeNumber!=null && apState==null){Alist = applyService.findApplyByNum(typeNumber);}
		for(int i=0;i<Alist.size();i++){
			StudentApply studentApply = new StudentApply();
			apply = (Apply) Alist.get(i);
			student = (Student) studentService.findStuBystuNo(apply.getApplicantId()).get(0);
			studentApply.setStuNo(student.getStuNo());
			studentApply.setStuName(student.getStuName());
			studentApply.setClassName(student.getClassName());
			studentApply.setInstituteName(student.getInstituteName());
			studentApply.setApId(apply.getApId());
			studentApply.setApContent(apply.getApContent());
			studentApply.setApState(apply.getApState());
			studentApply.setApTime(apply.getApTime());
			studentApply.setHandleTime(apply.getHandleTime());
			studentApply.setApType(apply.getApType());
			studentApplyList2.add(i, studentApply);
		}
		studentapplyList = studentApplyList2;
	}
	

	public IApplyService getApplyService() {
		return applyService;
	}

	public void setApplyService(IApplyService applyService) {
		this.applyService = applyService;
	}

	public ITutorService getTutorService() {
		return tutorService;
	}

	public void setTutorService(ITutorService tutorService) {
		this.tutorService = tutorService;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}


	public List<StudentApply> getStudentapplyList() {
		return studentapplyList;
	}


	public void setStudentapplyList(List<StudentApply> studentapplyList) {
		this.studentapplyList = studentapplyList;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public StudentForm getStudentForm() {
		return studentForm;
	}

	public void setStudentForm(StudentForm studentForm) {
		this.studentForm = studentForm;
	}

	public List<Class> getClasslist() {
		return classlist;
	}

	public void setClasslist(List<Class> classlist) {
		this.classlist = classlist;
	}

	public List<Tutor> getTutorList() {
		return tutorList;
	}

	public void setTutorList(List<Tutor> tutorList) {
		this.tutorList = tutorList;
	}

	public List<Tutor> getTutorList2() {
		return tutorList2;
	}

	public void setTutorList2(List<Tutor> tutorList2) {
		this.tutorList2 = tutorList2;
	}

	public IClassService getClassService() {
		return classService;
	}

	public void setClassService(IClassService classService) {
		this.classService = classService;
	}

}
