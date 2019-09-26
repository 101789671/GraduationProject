package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.Grouping;

public interface IGroupingService {
	
	//添加
	public void addGrouping(Grouping grouping);
	
	//查询所有分组
	public List<Grouping> findGroupingAll();
	
	//根据编号
	public List<Grouping> findGroupingByGroupNo(String groupNo);
	
	//根据学号查询
	public List<Grouping> findGroupingByStuNo(String stuNo);
	
	//根据答辩类型 、学号查询
	public List<Grouping> findGroupingByStuNo(String replyType,String stuNo);
	
	//根据答辩类型 分组
	public void answerGrouping(String replyType);
	
	//根据答辩类型查询
	public List<Grouping> findGroupingByReplyType(String replyType);
	
	//根据答辩类型、班级查询
	public List<Grouping> findGrouping1(String replyType ,String className);
	
	//根据答辩类型、专业
	public List<Grouping> findGrouping2(String replyType ,String majorName);
	
	//修改
	public List<Grouping> updateGrouping(Grouping grouping);
	
	//删除
	public void deleteGrouping(Grouping grouping);

}
