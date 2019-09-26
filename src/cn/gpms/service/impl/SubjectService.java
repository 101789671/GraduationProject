package cn.gpms.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import cn.gpms.dao.ISubjectDAO;
import cn.gpms.service.ISubjectService;
import cn.gpms.vo.Subject;
import cn.gpms.vo.User;

public class SubjectService implements ISubjectService {
	
	protected ISubjectDAO subjectDAO;
	
/**
 * �����Ŀ
 */
	public void addSubject(Subject subject) {
		subjectDAO.addSubject(subject);
	}

/**
 * �����Ŀ
 */
	public List<Subject> addSubject(User user, Subject subject) {
		List<Subject> subjectlist = new ArrayList<Subject>();
		
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");// ��ȡ��ǰϵͳʱ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ��ȡ��ǰϵͳʱ��
		
		//�����Ŀ����
		String subjectName = subject.getSubjectName();
		
		if (subjectName != null && user!=null) {
			//������Ŀ	
			String[] strs = subjectName.split(", ");
			for(int i=0;i<strs.length;i++){
				StringBuffer No = new StringBuffer();
				Subject subject1 = new Subject();
				String tutNo = user.getUserid();
				No.append(df1.format(new Date()));
				No.append(tutNo);
				No.append(i+1);
				String subNo = No.toString();
				subject1.setTutNo(tutNo);
				subject1.setTutName(user.getUserName());
				subject1.setSubNo(subNo);
				subject1.setSubjectName(strs[i]);
				subject1.setUpdateTime(df.format(new Date()));
				subjectDAO.addSubject(subject1);
				subjectlist.add(subject1);
				}
			
		}		
		return subjectlist;
	}
	
/**
 * ���ݱ�Ų�ѯ��Ŀ	
 */
	public List<Subject> findSubjectByNo (String subNo){
		return subjectDAO.findSubjectByNo(subNo);
	}
	
/**
 * ���ݵ�ʦID��ѯ��Ŀ
 */
	public List<Subject> findSubjectByTutNo(String tutNo){
		return subjectDAO.finSubjectByTutNo(tutNo);
	}
	
/**
 * ɾ����Ŀ
 */
	public void deleteSubject(Subject subject){
		subjectDAO.deleteSubject(subject);
	}
	
	
	
/**
 * �޸���Ŀ
 */
	public void updateSubject(User user, Subject subject) {
		subject.setTutNo(user.getUserid());
		subject.setTutName(user.getUserName());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ��ȡ��ǰϵͳʱ��
		subject.setUpdateTime(df.format(new Date()));
		subjectDAO.updateSubject(subject);
	}
	
	
	
	
	
	
	public ISubjectDAO getSubjectDAO() {
		return subjectDAO;
	}

	public void setSubjectDAO(ISubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}

}
