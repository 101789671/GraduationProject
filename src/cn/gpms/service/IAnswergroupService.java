package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.Answergroup;

public interface IAnswergroupService {
	
	//��ѯȫ��
	public List<Answergroup> findAnswergroupAll();
	
	//�������ID
	public List<Answergroup> findAnswergroupByGroupID(String groupId);
	
	//����רҵ���� ������ѯ
	public List<Answergroup> fingAnswergroup2(String majorName ,String groupName);
	
	//����ѧԺ�� ��רҵ����ѯ
 	public List<Answergroup> findAnswergroup1(String replyType, String instituteName,String majorName);
 	
 	//��Ӵ�����
 	public void addAnswergroup(Answergroup answergroup);
 	
 	//����replyType��ѯ
 	public List<Answergroup> findAnswerGroupByReplyType(String replyType);
 	
 	//���� replyType  רҵ�� ��ѯ 
 	public List<Answergroup> findAnswerGroup3(String replyType ,String majorName);
 	
 	//���ݴ�����͡�רҵ���� ������ѯ
 		public List<Answergroup> fingAnswergroup4( String replyType ,String majorName ,String groupName);
 	
 	//����
 	public void updateAnswergroup(Answergroup answergroup);
 	
 	//ɾ��
 	public void deleteAnswergroup(String groupId);

}
