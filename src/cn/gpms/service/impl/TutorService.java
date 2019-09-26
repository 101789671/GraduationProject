package cn.gpms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.gpms.dao.ITutorDAO;
import cn.gpms.form.TutorForm;
import cn.gpms.service.IStudentService;
import cn.gpms.service.ITutorService;
import cn.gpms.service.IUserService;
import cn.gpms.vo.GpFile;
import cn.gpms.vo.Student;
import cn.gpms.vo.Tutor;
import cn.gpms.vo.User;

public class TutorService implements ITutorService {
	protected ITutorDAO tutorDAO;
	protected IStudentService studentService;
	protected IUserService userService;

	// 添加导师
	public void addTutor(Tutor tutor) {
		tutorDAO.addTutor(tutor);

	}

	// 查询所有导师
	public List findTuAll() {
		return tutorDAO.findTuAll();
	}

	// 单个删除导师
	public void delTutor(Tutor tutor) {
		 tutorDAO.delTutor(tutor);
	}

	// 根据tutNo查询导师，前往修改导师
	public List<Tutor> toUpdateTu(String tutNo) {
		return tutorDAO.findTutorByNo(tutNo);

	}
	//根据No查导师，返回List
	public List findTutorByNo(String tutNo) {
		return tutorDAO.findTutorByNo(tutNo);
	}
	//根据学院查询导师
	public List findTuByIns(String instituteName){
		return tutorDAO.findTuByIns(instituteName);
	}
	

	// 修改导师
	public void updateTutor(TutorForm tutorForm) {
		String Eor = "error";
		if (tutorForm.getTutNo() != null && tutorForm.getTutName() != null
				&& tutorForm.getSex() != null
				&& tutorForm.getPosition() != null
				&& tutorForm.getInstituteName() != null) {
			Tutor tutor = new Tutor();
			tutor.setTutNo(tutorForm.getTutNo());
			tutor.setTutName(tutorForm.getTutName());
			tutor.setSex(tutorForm.getSex());
			tutor.setInstituteName(tutorForm.getInstituteName());
			tutor.setPosition(tutorForm.getPosition());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
			tutor.setUpdateTime(df.format(new Date()));
			tutorDAO.updateTutor(tutor);
			//在student更新导师姓名
			List studentlist = studentService.findStuByTutNo(tutorForm.getTutNo());
			for(int i=0;i<studentlist.size();i++){
				Student student = (Student) studentlist.get(i);
				studentService.updateStuTut(student.getStuNo(), student.getTutNo(), tutorForm.getTutName());
			}
			//在User表更新导师名
			userService.updateUser(tutorForm.getTutNo(), tutorForm.getTutName());
			Eor="seccess";
		}
		if(Eor=="error"){
			ActionContext.getContext().put("message","修改失败！");
		}
	}
	
	//根据导师名查导师，返回Tutor
	public List findTuByName(String tutName){
		return tutorDAO.findTuByName(tutName);
	}

	
	
	
	public ITutorDAO getTutorDAO() {
		return tutorDAO;
	}

	public void setTutorDAO(ITutorDAO tutorDAO) {
		this.tutorDAO = tutorDAO;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}




}
