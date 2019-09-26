package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.IGpResultsDAO;
import cn.gpms.vo.GpResults;

public class GpResultsDAO extends Base2DAO implements IGpResultsDAO {
	
	
/**
 * ��ѯ����
 */
	public List findGpResultsAll(){
		String hql = "from GpResults";
		return findAll(hql);
	}
	
/**
 * ����ѧ�Ų�ѯ
 */
	public List findGpResultsByStuNo(String stuNo){
		String hql = "from GpResults where stuNo=?";
		return find(hql, stuNo);
	}
	
/**
 * ���ݱ�Ų�ѯ
 */
	public List findGpResultsByResultsNo(String resultsNo){
		String hql = "from GpResults where resultsNo=?";
		return find(hql, resultsNo);
	}

/**
 * ���ݳɼ����ͱ�Ų�ѯ
 */
	public List findGpResultsByReType(String reType){
		String hql = "from GpResults where reType=?";
		return find(hql, reType);
	}
	
/**
 * ���ݳɼ������� �� �༶����ѯ
 */
	public List findGpResults1(String reType, String className){
		String hql = "from GpResults where reType=? and className=?";
		Object[] values = {reType,className};
		return find(hql, values);
	}
	
/**
 * ���ݳɼ������� �� רҵ����ѯ
 */
	public List findGpResults2(String reType, String majorName){
		String hql = "from GpResults where reType=? and majorName=?";
		Object[] values = {reType,majorName};
		return find(hql, values);
	}

/**
 * ���ݳɼ������� �� ѧ�Ų�ѯ
 */
	public List findGpResults3(String reType, String stuNo){
		String hql = "from GpResults where reType=? and stuNo=?";
		Object[] values = {reType,stuNo};
		return find(hql, values);
	}
	
/**
 * ���ݵ�ʦ�Ų�ѯ
 */
	public List findGpResultsByTutNo(String tutNo){
		String hql = "from GpResults where tutNo=?";
		return find(hql, tutNo);
	}
		
/**
 * ���ݳɼ������� �� ��ʦ�Ų�ѯ
 */
	public List findGpResults4(String reType,String tutNo){
		String hql = "from GpResults where reType=? and tutNo=?";
		Object[] values = {reType,tutNo};
		return find(hql, values);
	}
	
/**
 * ���ݳɼ������� �� ��ʦ�š� �༶��ѯ
 */
	public List findGpResults5(String reType,String tutNo,String className){
		String hql = "from GpResults where reType=? and tutNo=? and className=?";
		Object[] values = {reType,tutNo ,className};
		return find(hql, values);
		
	}
	
/**
 * ��ӳɼ�
 */
	public void addGpResults(GpResults gpResults){
		save(gpResults);
	}
	
/**
 * �޸�
 */
	public void updataGpResults(GpResults gpResults){
		update(gpResults);
	}
	
/**
 * ɾ��
 */
	public void deleteGpResults(GpResults gpResults){
		delete(gpResults);
	}
	
	
	
}
