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
 * 添加导师
 * @return Tutor
 */
	public void addTutor(Tutor tutor) {
		save(tutor);
	}
/**
 * 查询导师
 */
	public List findTuAll() {	
		String hql = "from Tutor";
		return findAll(hql);
	}
	
/**
 * 单个删除导师
 */
	public void delTutor(Tutor tutor){
		delete(tutor);
	}
	
/**
 * 修改
 */
	public void updateTutor(Tutor tutor){
		update(tutor);
	}
	
/**
 * 根据tutNo查询导师
 * 返List
 */
public List findTutorByNo(String tutNo) {
	String hql = "from Tutor where tutNo=?";
	return find(hql, tutNo);
}


/**
 * 根据学院查询导师
 */
public List findTuByIns(String instituteName){
	String hql = "from Tutor where instituteName=?";
	return find(hql, instituteName);
	
}

/**
 * 根据tutName查询 
 * @return Tutor
 */
public List findTuByName(String tutName) {
	String hql = "from Tutor where tutName=?";
	return find(hql, tutName);
}



}
