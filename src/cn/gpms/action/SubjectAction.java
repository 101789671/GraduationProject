package cn.gpms.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.gpms.service.ISubjectService;
import cn.gpms.vo.Subject;
import cn.gpms.vo.Tutor;
import cn.gpms.vo.User;

public class SubjectAction {
	
	private Subject subject;
	
	private List<Subject> subjectList;
	
	protected ISubjectService subjectService;

/**	
 * 添加题目
 */
	public String addSubject(){
		String Eor = "error";
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		if (!"".equals(subject.getSubjectName())) {
			subjectList = subjectService.addSubject(user12, subject);
			if (!subjectList.isEmpty()) {
				ActionContext.getContext().put("message", "添加成功！");
				Eor="subjectlist";
			} else {
				ActionContext.getContext().put("message", "添加失败！");
			}
		} else {
			ActionContext.getContext().put("message", "添加失败，内容为空！");
		}
		return Eor;
	}
	
/**
 * 显示题目（题目管理）
 */
	public String subjectList(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		subjectList = subjectService.findSubjectByTutNo(user12.getUserid());
		return "subjectlist";	
	}
	
/**
 * 修改题目
 */
	public String updateSubject(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		if(!"".equals(subject.getSubjectName())&&!"".equals(subject.getSubNo())){
		subjectService.updateSubject(user12, subject);
		
		ActionContext.getContext().put("message", "修改成功！");
		}else {
			ActionContext.getContext().put("message", "修改失败！");	
		}
		subjectList = subjectService.findSubjectByTutNo(user12.getUserid());
		return "subjectlist";
	}
	
/**
 * 删除题目
 */
	public String deleteSubject() {
		if(!"".equals(subject.getSubNo())){
			Map session = ActionContext.getContext().getSession();
			User user12 = (User) session.get("user");
			subject.setTutNo(user12.getUserid());
		subjectService.deleteSubject(subject);
		}else{
			ActionContext.getContext().put("message", "删除失败！");	
		}
		subjectList();
		return "subjectlist";
	}
	
/**
 * 批量删除题目
 */
	public void batchDelSubject() {
		String items = ServletActionContext.getRequest().getParameter("delitems");
		String Ero = "false";
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		if (items != null) {
			String[] strs = items.split(",");
			for (int i = 0; i < strs.length; i++) {
				Subject subject = new Subject();
				subject.setSubNo(strs[i]);
				subject.setTutNo(user12.getUserid());
				subjectService.deleteSubject(subject);
				Ero = "true";
			}
		}
		if (Ero == "flase") {
			ActionContext.getContext().put("message", "批量删除失败！");
		} else {
			ActionContext.getContext().put("message", "批量删除成功！");
		}
	}
	
/**
 * 前往修改题目
 */
	public String toUpdateSubject(){
		subjectList = subjectService.findSubjectByNo(subject.getSubNo());
		return "toUpdataSubject";
	}

	
	
	
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public ISubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubjectService subjectService) {
		this.subjectService = subjectService;
	}






	public List<Subject> getSubjectList() {
		return subjectList;
	}






	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

}
