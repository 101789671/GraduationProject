package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.Subject;
import cn.gpms.vo.User;

public interface ISubjectService {
	//�����Ŀ
	public List<Subject> addSubject(User user,Subject subject);
	//�����Ŀ
	public void addSubject(Subject subject);
	//�޸���Ŀ
	public void updateSubject(User user,Subject subject);
	//���ݱ�Ų�ѯ��Ŀ
	public List<Subject> findSubjectByNo (String subNo);
	//���ݵ�ʦID��ѯ��Ŀ
	public List<Subject> findSubjectByTutNo(String tutNo);
	//ɾ����Ŀ
	public void deleteSubject(Subject subject);

}
