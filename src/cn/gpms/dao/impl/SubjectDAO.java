package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.ISubjectDAO;
import cn.gpms.vo.Subject;

public class SubjectDAO extends Base2DAO implements ISubjectDAO {

/**
 * 添加题目
 */
	public void addSubject(Subject subject) {
		save(subject);
	}
/**
 * 根据编号查询题目
 */
	public List findSubjectByNo(String subNo ){
		String hql = "from Subject where subNo=?";
		return find(hql, subNo);
	}

/**
 * 根据导师ID查询题目
 */
	public List finSubjectByTutNo(String tutNo) {
		String hql = "from Subject where tutNo=?";
		return find(hql, tutNo);
	}
	

/**
 * 修改题目
 */
	public void updateSubject(Subject subject) {
		update(subject);
	}

/**
 * 删除题目
 */
	public void deleteSubject(Subject subject){
		delete(subject);
		System.out.print("111");
	}
	


}
