package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Grouping;

public interface IGroupingDAO {
	
	
	//��ѯ���з���
	public List<Grouping> findGroupingAll();
	
	//���ݱ��
	public List<Grouping> findGroupingByGroupNo(String groupNo);
	
	//����ѧ�Ų�ѯ
	public List<Grouping> findGroupingByStuNo(String stuNo);
	
	//���ݴ������š�ѧ��ѧ��
	public List<Grouping> findGroupingBygsNo(String replyType,String stuNo);
	
	//���ݴ�����Ͳ�ѯ
	public List<Grouping> findGroupingByReplyType(String replyType);
	
	//��Ӵ�����
	public void addGrouping(Grouping grouping);
	
	//����
	public void updateGrouping(Grouping grouping);
	
	//���ݴ�����͡��༶��ѯ
	public List<Grouping> findGrouping1(String replyType ,String className);
	
	//���ݴ�����͡�רҵ
	public List<Grouping> findGrouping2(String replyType ,String majorName);
	
	//ɾ��
	public void deleteGrouping(Grouping grouping);
	
	
	

}
