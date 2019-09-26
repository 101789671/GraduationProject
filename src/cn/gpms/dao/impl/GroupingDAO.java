package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.IGroupingDAO;
import cn.gpms.vo.Grouping;

public class GroupingDAO extends Base2DAO implements IGroupingDAO {
	
/**
 * 查询所有
 */
	public List findGroupingAll(){
		String hql = "from Grouping";
		return findAll(hql);
	}
	
/**
 * 根据编号
 */
	public List findGroupingByGroupNo(String groupNo){
		String hql = "from Grouping where groupNo=?";
		return find(hql, groupNo);
	}
	
/**
 * 根据学号查询
 */
	public List findGroupingByStuNo(String stuNo){
		String hql = "from Grouping where stuNo=?";
		return find(hql, stuNo);
	}

/**
 * 根据答辩分组编号、学生学号
 */
	public List findGroupingBygsNo(String replyType, String stuNo) {
		String hql = "from Grouping where replyType=? and stuNo=?";
		Object[] values = {replyType,stuNo};
		return find(hql, values);
	}
	
/**
 * 根据答辩类型查询
 */
	public List findGroupingByReplyType(String replyType){
		String hql = "from Grouping where replyType=?";
		return find(hql, replyType);
	}

/**
 * 根据答辩类型、班级查询
 */
	public List findGrouping1(String replyType, String className) {
		String hql = "from Grouping where replyType=? and className=?";
		Object[] values = {replyType,className};
		return find(hql, values);
	}

/**
 * 根据答辩类型、专业查询
	*/
	public List findGrouping2(String replyType, String majorName) {
		String hql = "from Grouping where replyType=? and majorName=?";
		Object[] values = {replyType,majorName};
		return find(hql, values);
	}
	

/**
 * 添加答辩分组信息
 */
	public void addGrouping(Grouping grouping) {
		save(grouping);
	}
	
/**
 * 更新
 */
	public void updateGrouping(Grouping grouping){
		update(grouping);
	}
	
/**
 * 删除
 */
	public void deleteGrouping(Grouping grouping){
		delete(grouping);
	}

}
