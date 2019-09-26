package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.IGroupingDAO;
import cn.gpms.vo.Grouping;

public class GroupingDAO extends Base2DAO implements IGroupingDAO {
	
/**
 * ��ѯ����
 */
	public List findGroupingAll(){
		String hql = "from Grouping";
		return findAll(hql);
	}
	
/**
 * ���ݱ��
 */
	public List findGroupingByGroupNo(String groupNo){
		String hql = "from Grouping where groupNo=?";
		return find(hql, groupNo);
	}
	
/**
 * ����ѧ�Ų�ѯ
 */
	public List findGroupingByStuNo(String stuNo){
		String hql = "from Grouping where stuNo=?";
		return find(hql, stuNo);
	}

/**
 * ���ݴ������š�ѧ��ѧ��
 */
	public List findGroupingBygsNo(String replyType, String stuNo) {
		String hql = "from Grouping where replyType=? and stuNo=?";
		Object[] values = {replyType,stuNo};
		return find(hql, values);
	}
	
/**
 * ���ݴ�����Ͳ�ѯ
 */
	public List findGroupingByReplyType(String replyType){
		String hql = "from Grouping where replyType=?";
		return find(hql, replyType);
	}

/**
 * ���ݴ�����͡��༶��ѯ
 */
	public List findGrouping1(String replyType, String className) {
		String hql = "from Grouping where replyType=? and className=?";
		Object[] values = {replyType,className};
		return find(hql, values);
	}

/**
 * ���ݴ�����͡�רҵ��ѯ
	*/
	public List findGrouping2(String replyType, String majorName) {
		String hql = "from Grouping where replyType=? and majorName=?";
		Object[] values = {replyType,majorName};
		return find(hql, values);
	}
	

/**
 * ��Ӵ�������Ϣ
 */
	public void addGrouping(Grouping grouping) {
		save(grouping);
	}
	
/**
 * ����
 */
	public void updateGrouping(Grouping grouping){
		update(grouping);
	}
	
/**
 * ɾ��
 */
	public void deleteGrouping(Grouping grouping){
		delete(grouping);
	}

}
