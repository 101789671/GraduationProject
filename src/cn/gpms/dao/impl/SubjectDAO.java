package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.ISubjectDAO;
import cn.gpms.vo.Subject;

public class SubjectDAO extends Base2DAO implements ISubjectDAO {

/**
 * �����Ŀ
 */
	public void addSubject(Subject subject) {
		save(subject);
	}
/**
 * ���ݱ�Ų�ѯ��Ŀ
 */
	public List findSubjectByNo(String subNo ){
		String hql = "from Subject where subNo=?";
		return find(hql, subNo);
	}

/**
 * ���ݵ�ʦID��ѯ��Ŀ
 */
	public List finSubjectByTutNo(String tutNo) {
		String hql = "from Subject where tutNo=?";
		return find(hql, tutNo);
	}
	

/**
 * �޸���Ŀ
 */
	public void updateSubject(Subject subject) {
		update(subject);
	}

/**
 * ɾ����Ŀ
 */
	public void deleteSubject(Subject subject){
		delete(subject);
		System.out.print("111");
	}
	


}
