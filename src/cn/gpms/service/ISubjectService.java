package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.Subject;
import cn.gpms.vo.User;

public interface ISubjectService {
	//添加题目
	public List<Subject> addSubject(User user,Subject subject);
	//添加题目
	public void addSubject(Subject subject);
	//修改题目
	public void updateSubject(User user,Subject subject);
	//根据编号查询题目
	public List<Subject> findSubjectByNo (String subNo);
	//根据导师ID查询题目
	public List<Subject> findSubjectByTutNo(String tutNo);
	//删除题目
	public void deleteSubject(Subject subject);

}
