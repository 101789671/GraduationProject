package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Subject;

public interface ISubjectDAO {
	//�����Ŀ
	public void addSubject(Subject subject);
	//���ݱ�Ų�ѯ��Ŀ
	public List findSubjectByNo (String subNo);
	//���ݵ�ʦID��ѯ��Ŀ
	public List finSubjectByTutNo(String tutNo);
	//�޸���Ŀ
	public void updateSubject(Subject subject);
	//ɾ����Ŀ
	public void deleteSubject(Subject subject);

}
