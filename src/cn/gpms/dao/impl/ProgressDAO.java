package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.IProgressDAO;
import cn.gpms.vo.Progress;

public class ProgressDAO extends Base2DAO implements IProgressDAO {
	
/**
 * ��ӽ�����
 */
	public void addProgress(Progress progress){
		save(progress);
	}

/**
 * ����ѧ�� ���׶β�ѯ
 */
	public List findProgresses1(String stuNo, String phase) {
		String hql = "from Progress where stuNo=? and phase=?";
		Object[] values = {stuNo,phase};
		return find(hql, values);
	}
	
/**
 * ����ѧ�Ų�ѯ
 */
	public List findProgressByStuNo(String stuNo){
		String hql = "from Progress where stuNo=?";
		return find(hql, stuNo);
	}

}
