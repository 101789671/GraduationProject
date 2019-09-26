package cn.gpms.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.BaseDAO;
import cn.gpms.dao.ITutorDAO;
import cn.gpms.dao.IUserDAO;
import cn.gpms.vo.Tutor;

public class TutorDAO extends Base2DAO implements ITutorDAO{

/**	
 * ��ӵ�ʦ
 * @return Tutor
 */
	public void addTutor(Tutor tutor) {
		save(tutor);
	}
/**
 * ��ѯ��ʦ
 */
	public List findTuAll() {	
		String hql = "from Tutor";
		return findAll(hql);
	}
	
/**
 * ����ɾ����ʦ
 */
	public void delTutor(Tutor tutor){
		delete(tutor);
	}
	
/**
 * �޸�
 */
	public void updateTutor(Tutor tutor){
		update(tutor);
	}
	
/**
 * ����tutNo��ѯ��ʦ
 * ��List
 */
public List findTutorByNo(String tutNo) {
	String hql = "from Tutor where tutNo=?";
	return find(hql, tutNo);
}


/**
 * ����ѧԺ��ѯ��ʦ
 */
public List findTuByIns(String instituteName){
	String hql = "from Tutor where instituteName=?";
	return find(hql, instituteName);
	
}

/**
 * ����tutName��ѯ 
 * @return Tutor
 */
public List findTuByName(String tutName) {
	String hql = "from Tutor where tutName=?";
	return find(hql, tutName);
}



}
