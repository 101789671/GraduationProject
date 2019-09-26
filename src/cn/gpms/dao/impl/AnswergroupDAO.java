package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.IAnswergroupDAO;
import cn.gpms.vo.Answergroup;

public class AnswergroupDAO extends Base2DAO implements IAnswergroupDAO {

/**
 * 查询全部
 */
	public List findAnswergroupAll(){
		String hql = "from Answergroup";
		return findAll(hql);
	}
	

/**
 * 根据组别Id查询
 */
	public List findAnswergroupByGroupID(String groupId){
		String hql = "from Answergroup where groupId=? ";
		return find(hql, groupId);
	}
	
/**
 *  根据replyType查询
 */	
	public List findAnswerGroupByReplyType(String replyType){
		String hql = "from Answergroup where replyType=?";
		return find(hql, replyType);
	}
	
/**
 * 根据 replyType  专业名 查询 
 */
	public List findAnswerGroup3(String replyType ,String majorName){
		String hql = "from Answergroup where replyType=? and majorName=?";
		Object[] values = {replyType,majorName};
		return find(hql, values);
	}
		

/**	
 * 根据专业名、组名查询
 */
	public List fingAnswergroup2(String majorName ,String groupName){
		String hql = "from Answergroup where majorName=? and groupName=?";
		Object[] values = {majorName,groupName};
		return find(hql, values);
	}
	
/**	
 * 根据答辩类型、专业名、组名查询
 */
	public List fingAnswergroup4(String replyType,String majorName ,String groupName){
		String hql = "from Answergroup where replyType=? and majorName=? and groupName=?";
		Object[] values = {replyType,majorName,groupName};
		return find(hql, values);	
		}
	
	
/**
 * 根据学院名 、专业名查询
 */
	public List findAnswergroup1(String replyType,String instituteName,String majorName) {
		String hql = "from Answergroup where replyType=? and instituteName=? and majorName=?";
		Object[] values = {replyType,instituteName,majorName};
		return find(hql, values);
	}

/**
 * 添加答辩组别
 */
	public void addAnswergroup(Answergroup answergroup) {
		save(answergroup);
	}
	
/**
 * 更新
 */
 	public void updateAnswergroup(Answergroup answergroup){
 		update(answergroup);
 	}
 	
 /**
  * 删除
  */public void deleteAnsergrou(Answergroup answergroup) {
	  delete(answergroup);
	}

}
