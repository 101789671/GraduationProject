package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Grouping;

public interface IGroupingDAO {
	
	
	//查询所有分组
	public List<Grouping> findGroupingAll();
	
	//根据编号
	public List<Grouping> findGroupingByGroupNo(String groupNo);
	
	//根据学号查询
	public List<Grouping> findGroupingByStuNo(String stuNo);
	
	//根据答辩分组编号、学生学号
	public List<Grouping> findGroupingBygsNo(String replyType,String stuNo);
	
	//根据答辩类型查询
	public List<Grouping> findGroupingByReplyType(String replyType);
	
	//添加答辩分组
	public void addGrouping(Grouping grouping);
	
	//更新
	public void updateGrouping(Grouping grouping);
	
	//根据答辩类型、班级查询
	public List<Grouping> findGrouping1(String replyType ,String className);
	
	//根据答辩类型、专业
	public List<Grouping> findGrouping2(String replyType ,String majorName);
	
	//删除
	public void deleteGrouping(Grouping grouping);
	
	
	

}
