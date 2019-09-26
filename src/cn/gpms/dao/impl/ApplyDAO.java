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
 * �������
 * tutor
 */
	public void addApply( Apply apply) {
		save(apply);
	}

/**
 * ���������˱�Ų�ѯ�����˵�ĳһ���͵�����
 */
	public List findApplyByAID(String applicantID,String apType) {
		String hql = "from Apply where applicantId=? and apType=?";
		Object[] values={applicantID,apType};
		return find(hql, values);
	}
	
/**
 * ���������ˡ������롢 �������Ͳ�ѯ��¼
 */
	public List findApply3(String applicantId,String respondentId,String apType){
		String hql = "from Apply where applicantId=? and respondentId=? and apType=?";
		Object[] values={applicantId,respondentId,apType};
		return find(hql, values);
	}
	
/**
 * ���������ˡ� �������ͱ�Ų�ѯ��¼
 */
	public List findApply3_1(String applicantId, String typeNumber) {
		String hql = "from Apply where applicantId=? and typeNumber=?";
		Object[] values = {applicantId,typeNumber};
		return find(hql, values);
	}
	
/**
 * ���ݱ�������ID��ѯ����״̬������
 */
	public List findApplyByRID(String respondentId) {
		String hql = "from Apply where respondentId=?";
		return find(hql, respondentId);
	}

/**
 * ���ݱ�������ID��ѯĳһ���͡�ĳһ״̬������
 */
	public List findApply2(String respondentId, String apType,String apState) {
		String hql = "from Apply where respondentId=? and apType=? and apState=?";
		Object[] values = {respondentId,apType,apState};
		return find(hql, values);
	}
	
/**
 * ����apID��ѯ������Ϣ
 */
	public List findApplyByApID(String apId) {
		String hql = "from Apply where apId=?";
		return find(hql, apId);
	}

/**
 * �����������ͱ�š�״̬ ��ѯ��������
 */
	public List findApply4(String typeNumber, String apState) {
		String hql = "from Apply where typeNumber=? and apState=? ";
		Object[] values = {typeNumber,apState};
		return find(hql, values);
	}

/**
 * ����������ID���������ͱ�Ų�ѯ������Ϣ
 */
	public List findApply5(String applicantID, String typeNumber) {
		String hql = "from Apply where typeNumber=? and applicantId=? ";
		Object[] values = {typeNumber,applicantID};
		return find(hql, values);
	}

/**
 * ����������ID���������ͱ�Ų�ѯ������Ϣ
 */
	public List findApply6(String respondentId, String typeNumber) {
		String hql = "from Apply where typeNumber=? and respondentId=? ";
		Object[] values = {typeNumber,respondentId};
		return find(hql, values);
	}

/**
 * �����������ͱ�Ų�ѯ
 */
	public List findApplyByNum(String typeNumber) {
		String hql = "from Apply where typeNumber=?";
		return find(hql, typeNumber);
	}
	
/**
 * ���������ˡ��������ͱ�š��������ݲ�ѯ����
 */
	public List findApply1(String applicantId,String typeNumber,String apContent){
		String hql = "from Apply where applicantId=? and typeNumber=? and apContent=?";
		Object[] values = {applicantId,typeNumber,apContent};
		return find(hql, values);
	}
	
		
/**
 * ɾ������
 */
	public void deleteApply(Apply apply) {
		delete(apply);
	}



/**
 * �޸�����
 * 
 */
	public void updtateApply(Apply apply) {
		update(apply);
	}



	}

