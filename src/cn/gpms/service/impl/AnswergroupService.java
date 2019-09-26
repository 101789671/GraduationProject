package cn.gpms.service.impl;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import cn.gpms.dao.IAnswergroupDAO;
import cn.gpms.service.IAnswergroupService;
import cn.gpms.vo.Answergroup;

public class AnswergroupService implements IAnswergroupService {
	protected IAnswergroupDAO answergroupDAO;
	
	
/**
 * ��ѯȫ��
 */
	public List<Answergroup> findAnswergroupAll(){
		return answergroupDAO.findAnswergroupAll();
	}
	
	
/**	
 * �������ID
 */	
	public List<Answergroup> findAnswergroupByGroupID(String groupId){
		return answergroupDAO.findAnswergroupByGroupID(groupId);
	}

/**
 * ����replyType��ѯ
  */	
	public List<Answergroup> findAnswerGroupByReplyType(String replyType){
		return answergroupDAO.findAnswerGroupByReplyType(replyType);
	}
	
/**
 * ���� replyType  רҵ�� ��ѯ 
 */
 	public List<Answergroup> findAnswerGroup3(String replyType ,String majorName){
 		return answergroupDAO.findAnswerGroup3(replyType, majorName);
 	}

	
/**
 * ����רҵ�� ��������ѯ
 */
	public List<Answergroup> fingAnswergroup2(String majorName ,String groupName){
		return answergroupDAO.fingAnswergroup2(majorName, groupName);
	}
	
/**
 * ���ݴ�����͡�רҵ�� ��������ѯ
 */
	public List<Answergroup> fingAnswergroup4(String replyType,String majorName ,String groupName){
		return answergroupDAO.fingAnswergroup4(replyType,majorName, groupName);
	}
	
	
/**
 * ����ѧԺ�� ��רҵ����ѯ
 */
 	public List<Answergroup> findAnswergroup1(String replyType,String instituteName,String majorName){
 		return answergroupDAO.findAnswergroup1(replyType,instituteName, majorName);
 	}
 	
 /**
  * ��Ӵ�����
  */
	public void addAnswergroup(Answergroup answergroup) {
		answergroupDAO.addAnswergroup(answergroup);
	}
	
/**
 * ����
 */
 	public void updateAnswergroup(Answergroup answergroup){
 		answergroupDAO.updateAnswergroup(answergroup);
 	}
 	
/**
 * ɾ��
 */
 	public void deleteAnswergroup(String groupId){
 		
		List<Answergroup> answergrouplist = answergroupDAO.findAnswergroupByGroupID(groupId);
		if(!answergrouplist.isEmpty()){
		 Answergroup answergroup = answergrouplist.get(0);
		 int a = Integer.parseInt(answergroup.getStuNumber());
		 if(a<=0){
		 		answergroupDAO.deleteAnsergrou(answergroup);
		 		 ActionContext.getContext().put("message", "ɾ���ɹ���");
		 }else{
			 ActionContext.getContext().put("message", "ɾ��ʧ�ܣ��޷�ɾ���Դ���ѧ�������");
		 }
			
		}
 		
 		
 		
 	}
	
	
/**
 */
	public IAnswergroupDAO getAnswergroupDAO() {
		return answergroupDAO;
	}

	public void setAnswergroupDAO(IAnswergroupDAO answergroupDAO) {
		this.answergroupDAO = answergroupDAO;
	}

}
