package cn.gpms.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import cn.gpms.dao.IStudentDAO;
import cn.gpms.form.StudentForm;
import cn.gpms.service.IApplyService;
import cn.gpms.service.IStudentService;
import cn.gpms.vo.Apply;
import cn.gpms.vo.Class;
import cn.gpms.vo.Student;
import cn.gpms.vo.User;

public class StudentService implements IStudentService{
	protected IStudentDAO studentDAO;
	protected IApplyService applyService;
	
	//添加学生
	public void addstudent(Student student) {
	 studentDAO.addstudent(student);
	}
	//查询所有所有学生
	public List findStuAll() {
		return studentDAO.findStuAll();
	}
	
	//查询已选导师的学生
	public List findStuByAlready(){
		@SuppressWarnings("unchecked")
		List<Student> studentlist1 = studentDAO.findStuAll();
		List<Student> studentlist2 = new ArrayList<Student>();
		for(int i=0;i<studentlist1.size();i++){
			Student student = studentlist1.get(i);
			if(student.getTutName()!=null&&!"".equals(student.getTutName())){
				studentlist2.add(student);
			}
		}
		return studentlist2;
	}
	
	//查询未选导师的学生
	public List findStuByNoChooice(){
		@SuppressWarnings("unchecked")
		List<Student> studentlist1 = studentDAO.findStuAll();
		List<Student> studentlist2 = new ArrayList<Student>();
		for(int i=0;i<studentlist1.size();i++){
			Student student = studentlist1.get(i);
			if(student.getTutName()==null||"".equals(student.getTutName())){
				studentlist2.add(student);
			}
		}
		return studentlist2;
	}
	
	//删除学生
	public void deleteStu(Student student){
		studentDAO.deleteStu(student);
		
	}
	//根据stuNo查学生，返回Student
	public List findStuBystuNo(String stuNo) {
		return studentDAO.findStuBystuNo(stuNo);
	}
	
	//根据专业查询学生
	public List<Student> findStuByMajorName(String majorName){
		return studentDAO.findStuByMajorName(majorName);
	}
	
	//修改学生信息（导师编号 导师名）
	public void updateStuTut(String stuNo,String tutNo,String tutName){
		if("".equals(tutNo)){
			tutNo=null;
		}
		if("".equals(tutName)){
			tutName=null;
		}
		Student student = (Student) studentDAO.findStuBystuNo(stuNo).get(0);
		student.setTutNo(tutNo);
		student.setTutName(tutName);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
		student.setUpdateTime(df.format(new Date()));
		studentDAO.updateStu(student);
	}
	
	// 修改学生信息（题目）
	public String updateStuSub(User user, Apply apply) {
		String Eor = null;
		if (apply.getApId() != null && !"".equals(apply.getApId())) {
			Apply apply1 = (Apply) applyService
					.findApplyByApID(apply.getApId()).get(0);
			applyService.updtateApType(apply.getApId(), apply.getApState());// 修改当前申请状态
			applyService.updateApType2(apply1.getApplicantId(), "CS", "已失效",
					"待审核");// 修改其他申请状态
			if ("agree".equals(apply.getApState())) {
				Student student = (Student) studentDAO.findStuBystuNo(
						apply1.getApplicantId()).get(0);
				student.setSubject(apply1.getApContent());
				studentDAO.updateStu(student);
			}

			Eor = "success";
		} else {
			Eor = "error";
		}
		return Eor;
	}
	
/**
 * 修改学神题目tutor - student
 */
	public void updateStuSubT(Student student){
		Student student1 = new Student();
		student1 = (Student) studentDAO.findStuBystuNo(student.getStuNo()).get(0);
		student1.setSubject(student.getSubject());
		studentDAO.updateStu(student1);
		
	}
	
	
	//根据导师ID查询学生
	public List findStuByTutNo(String tutNo){
		return studentDAO.findStuByTutNo(tutNo);
	}
	
	//根据导师ID查询学生
	public int findStuByTutNoI(String tutNo){
		return studentDAO.findStuByTutNo(tutNo).size();
	}
	
	//修改学生信息（全部）
	public void updateStuAll(String classNo, String tutNo,
			StudentForm studentForm) {
		if(classNo!=null &&studentForm!=null){
			if("".equals(tutNo)){
				tutNo=null;
			}
			String tutName = studentForm.getTutName();
			if("".equals(tutName)){
				tutName=null;
			}
			Student student = (Student) studentDAO.findStuBystuNo(studentForm.getStuNo()).get(0);
			student.setStuName(studentForm.getStuName());
			student.setSex(studentForm.getSex());
			student.setClassNo(classNo);
			student.setClassName(studentForm.getClassName());
			student.setInstituteName(studentForm.getInstituteName());
			student.setTutNo(tutNo);
			student.setTutName(tutName);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
			student.setUpdateTime(df.format(new Date()));
		    studentDAO.updateStu(student);
		ActionContext.getContext().put("message","修改成功");
		}else {
			ActionContext.getContext().put("message","修改失败");
		}
	}

	
	
	
	public IStudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(IStudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	public IApplyService getApplyService() {
		return applyService;
	}
	public void setApplyService(IApplyService applyService) {
		this.applyService = applyService;
	}



}
