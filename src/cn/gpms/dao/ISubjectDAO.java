package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Subject;

public interface ISubjectDAO {
	//添加题目
	public void addSubject(Subject subject);
	//根据编号查询题目
	public List findSubjectByNo (String subNo);
	//根据导师ID查询题目
	public List finSubjectByTutNo(String tutNo);
	//修改题目
	public void updateSubject(Subject subject);
	//删除题目
	public void deleteSubject(Subject subject);

}
