package cn.gpms.service.impl;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import cn.gpms.dao.IAnswergroupDAO;
import cn.gpms.service.IAnswergroupService;
import cn.gpms.vo.Answergroup;

public class AnswergroupService implements IAnswergroupService {
	protected IAnswergroupDAO answergroupDAO;
	
	
/**
 * 查询全部
 */
	public List<Answergroup> findAnswergroupAll(){
		return answergroupDAO.findAnswergroupAll();
	}
	
	
/**	
 * 根据组别ID
 */	
	public List<Answergroup> findAnswergroupByGroupID(String groupId){
		return answergroupDAO.findAnswergroupByGroupID(groupId);
	}

/**
 * 根据replyType查询
  */	
	public List<Answergroup> findAnswerGroupByReplyType(String replyType){
		return answergroupDAO.findAnswerGroupByReplyType(replyType);
	}
	
/**
 * 根据 replyType  专业名 查询 
 */
 	public List<Answergroup> findAnswerGroup3(String replyType ,String majorName){
 		return answergroupDAO.findAnswerGroup3(replyType, majorName);
 	}

	
/**
 * 根据专业名 、组名查询
 */
	public List<Answergroup> fingAnswergroup2(String majorName ,String groupName){
		return answergroupDAO.fingAnswergroup2(majorName, groupName);
	}
	
/**
 * 根据答辩类型、专业名 、组名查询
 */
	public List<Answergroup> fingAnswergroup4(String replyType,String majorName ,String groupName){
		return answergroupDAO.fingAnswergroup4(replyType,majorName, groupName);
	}
	
	
/**
 * 根据学院名 、专业名查询
 */
 	public List<Answergroup> findAnswergroup1(String replyType,String instituteName,String majorName){
 		return answergroupDAO.findAnswergroup1(replyType,instituteName, majorName);
 	}
 	
 /**
  * 添加答辩组别
  */
	public void addAnswergroup(Answergroup answergroup) {
		answergroupDAO.addAnswergroup(answergroup);
	}
	
/**
 * 更新
 */
 	public void updateAnswergroup(Answergroup answergroup){
 		answergroupDAO.updateAnswergroup(answergroup);
 	}
 	
/**
 * 删除
 */
 	public void deleteAnswergroup(String groupId){
 		
		List<Answergroup> answergrouplist = answergroupDAO.findAnswergroupByGroupID(groupId);
		if(!answergrouplist.isEmpty()){
		 Answergroup answergroup = answergrouplist.get(0);
		 int a = Integer.parseInt(answergroup.getStuNumber());
		 if(a<=0){
		 		answergroupDAO.deleteAnsergrou(answergroup);
		 		 ActionContext.getContext().put("message", "删除成功！");
		 }else{
			 ActionContext.getContext().put("message", "删除失败！无法删除仍存在学生的组别。");
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
