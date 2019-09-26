package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.Answergroup;

public interface IAnswergroupService {
	
	//查询全部
	public List<Answergroup> findAnswergroupAll();
	
	//根据组别ID
	public List<Answergroup> findAnswergroupByGroupID(String groupId);
	
	//根据专业名、 组名查询
	public List<Answergroup> fingAnswergroup2(String majorName ,String groupName);
	
	//根据学院名 、专业名查询
 	public List<Answergroup> findAnswergroup1(String replyType, String instituteName,String majorName);
 	
 	//添加答辩组别
 	public void addAnswergroup(Answergroup answergroup);
 	
 	//根据replyType查询
 	public List<Answergroup> findAnswerGroupByReplyType(String replyType);
 	
 	//根据 replyType  专业名 查询 
 	public List<Answergroup> findAnswerGroup3(String replyType ,String majorName);
 	
 	//根据答辩类型、专业名、 组名查询
 		public List<Answergroup> fingAnswergroup4( String replyType ,String majorName ,String groupName);
 	
 	//更新
 	public void updateAnswergroup(Answergroup answergroup);
 	
 	//删除
 	public void deleteAnswergroup(String groupId);

}
