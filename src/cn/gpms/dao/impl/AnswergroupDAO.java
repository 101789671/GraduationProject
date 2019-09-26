package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.IAnswergroupDAO;
import cn.gpms.vo.Answergroup;

public class AnswergroupDAO extends Base2DAO implements IAnswergroupDAO {

/**
 * ��ѯȫ��
 */
	public List findAnswergroupAll(){
		String hql = "from Answergroup";
		return findAll(hql);
	}
	

/**
 * �������Id��ѯ
 */
	public List findAnswergroupByGroupID(String groupId){
		String hql = "from Answergroup where groupId=? ";
		return find(hql, groupId);
	}
	
/**
 *  ����replyType��ѯ
 */	
	public List findAnswerGroupByReplyType(String replyType){
		String hql = "from Answergroup where replyType=?";
		return find(hql, replyType);
	}
	
/**
 * ���� replyType  רҵ�� ��ѯ 
 */
	public List findAnswerGroup3(String replyType ,String majorName){
		String hql = "from Answergroup where replyType=? and majorName=?";
		Object[] values = {replyType,majorName};
		return find(hql, values);
	}
		

/**	
 * ����רҵ����������ѯ
 */
	public List fingAnswergroup2(String majorName ,String groupName){
		String hql = "from Answergroup where majorName=? and groupName=?";
		Object[] values = {majorName,groupName};
		return find(hql, values);
	}
	
/**	
 * ���ݴ�����͡�רҵ����������ѯ
 */
	public List fingAnswergroup4(String replyType,String majorName ,String groupName){
		String hql = "from Answergroup where replyType=? and majorName=? and groupName=?";
		Object[] values = {replyType,majorName,groupName};
		return find(hql, values);	
		}
	
	
/**
 * ����ѧԺ�� ��רҵ����ѯ
 */
	public List findAnswergroup1(String replyType,String instituteName,String majorName) {
		String hql = "from Answergroup where replyType=? and instituteName=? and majorName=?";
		Object[] values = {replyType,instituteName,majorName};
		return find(hql, values);
	}

/**
 * ��Ӵ�����
 */
	public void addAnswergroup(Answergroup answergroup) {
		save(answergroup);
	}
	
/**
 * ����
 */
 	public void updateAnswergroup(Answergroup answergroup){
 		update(answergroup);
 	}
 	
 /**
  * ɾ��
  */public void deleteAnsergrou(Answergroup answergroup) {
	  delete(answergroup);
	}

}
