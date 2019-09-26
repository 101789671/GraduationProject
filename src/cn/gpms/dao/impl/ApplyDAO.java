package cn.gpms.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.BaseDAO;
import cn.gpms.dao.IApplyDAO;
import cn.gpms.vo.Apply;

public class ApplyDAO extends Base2DAO implements IApplyDAO {
/**
 * 添加申请
 * tutor
 */
	public void addApply( Apply apply) {
		save(apply);
	}

/**
 * 根据申请人编号查询申请人的某一类型的申请
 */
	public List findApplyByAID(String applicantID,String apType) {
		String hql = "from Apply where applicantId=? and apType=?";
		Object[] values={applicantID,apType};
		return find(hql, values);
	}
	
/**
 * 根据申请人、被申请、 申请类型查询记录
 */
	public List findApply3(String applicantId,String respondentId,String apType){
		String hql = "from Apply where applicantId=? and respondentId=? and apType=?";
		Object[] values={applicantId,respondentId,apType};
		return find(hql, values);
	}
	
/**
 * 根据申请人、 申请类型编号查询记录
 */
	public List findApply3_1(String applicantId, String typeNumber) {
		String hql = "from Apply where applicantId=? and typeNumber=?";
		Object[] values = {applicantId,typeNumber};
		return find(hql, values);
	}
	
/**
 * 根据被申请人ID查询所有状态的申请
 */
	public List findApplyByRID(String respondentId) {
		String hql = "from Apply where respondentId=?";
		return find(hql, respondentId);
	}

/**
 * 根据被申请人ID查询某一类型、某一状态的申请
 */
	public List findApply2(String respondentId, String apType,String apState) {
		String hql = "from Apply where respondentId=? and apType=? and apState=?";
		Object[] values = {respondentId,apType,apState};
		return find(hql, values);
	}
	
/**
 * 根据apID查询申请信息
 */
	public List findApplyByApID(String apId) {
		String hql = "from Apply where apId=?";
		return find(hql, apId);
	}

/**
 * 根据申请类型编号、状态 查询所有申请
 */
	public List findApply4(String typeNumber, String apState) {
		String hql = "from Apply where typeNumber=? and apState=? ";
		Object[] values = {typeNumber,apState};
		return find(hql, values);
	}

/**
 * 根据申请人ID、申请类型编号查询申请信息
 */
	public List findApply5(String applicantID, String typeNumber) {
		String hql = "from Apply where typeNumber=? and applicantId=? ";
		Object[] values = {typeNumber,applicantID};
		return find(hql, values);
	}

/**
 * 根据申请人ID、申请类型编号查询申请信息
 */
	public List findApply6(String respondentId, String typeNumber) {
		String hql = "from Apply where typeNumber=? and respondentId=? ";
		Object[] values = {typeNumber,respondentId};
		return find(hql, values);
	}

/**
 * 根据申请类型编号查询
 */
	public List findApplyByNum(String typeNumber) {
		String hql = "from Apply where typeNumber=?";
		return find(hql, typeNumber);
	}
	
/**
 * 根据申请人、申请类型编号、申请内容查询申请
 */
	public List findApply1(String applicantId,String typeNumber,String apContent){
		String hql = "from Apply where applicantId=? and typeNumber=? and apContent=?";
		Object[] values = {applicantId,typeNumber,apContent};
		return find(hql, values);
	}
	
		
/**
 * 删除申请
 */
	public void deleteApply(Apply apply) {
		delete(apply);
	}



/**
 * 修改申请
 * 
 */
	public void updtateApply(Apply apply) {
		update(apply);
	}



	}

