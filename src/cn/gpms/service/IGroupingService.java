package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.Grouping;

public interface IGroupingService {
	
	//���
	public void addGrouping(Grouping grouping);
	
	//��ѯ���з���
	public List<Grouping> findGroupingAll();
	
	//���ݱ��
	public List<Grouping> findGroupingByGroupNo(String groupNo);
	
	//����ѧ�Ų�ѯ
	public List<Grouping> findGroupingByStuNo(String stuNo);
	
	//���ݴ������ ��ѧ�Ų�ѯ
	public List<Grouping> findGroupingByStuNo(String replyType,String stuNo);
	
	//���ݴ������ ����
	public void answerGrouping(String replyType);
	
	//���ݴ�����Ͳ�ѯ
	public List<Grouping> findGroupingByReplyType(String replyType);
	
	//���ݴ�����͡��༶��ѯ
	public List<Grouping> findGrouping1(String replyType ,String className);
	
	//���ݴ�����͡�רҵ
	public List<Grouping> findGrouping2(String replyType ,String majorName);
	
	//�޸�
	public List<Grouping> updateGrouping(Grouping grouping);
	
	//ɾ��
	public void deleteGrouping(Grouping grouping);

}
