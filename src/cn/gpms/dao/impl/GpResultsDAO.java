package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.IGpResultsDAO;
import cn.gpms.vo.GpResults;

public class GpResultsDAO extends Base2DAO implements IGpResultsDAO {
	
	
/**
 * 查询所有
 */
	public List findGpResultsAll(){
		String hql = "from GpResults";
		return findAll(hql);
	}
	
/**
 * 根据学号查询
 */
	public List findGpResultsByStuNo(String stuNo){
		String hql = "from GpResults where stuNo=?";
		return find(hql, stuNo);
	}
	
/**
 * 根据编号查询
 */
	public List findGpResultsByResultsNo(String resultsNo){
		String hql = "from GpResults where resultsNo=?";
		return find(hql, resultsNo);
	}

/**
 * 根据成绩类型编号查询
 */
	public List findGpResultsByReType(String reType){
		String hql = "from GpResults where reType=?";
		return find(hql, reType);
	}
	
/**
 * 根据成绩类类编号 、 班级名查询
 */
	public List findGpResults1(String reType, String className){
		String hql = "from GpResults where reType=? and className=?";
		Object[] values = {reType,className};
		return find(hql, values);
	}
	
/**
 * 根据成绩类类编号 、 专业名查询
 */
	public List findGpResults2(String reType, String majorName){
		String hql = "from GpResults where reType=? and majorName=?";
		Object[] values = {reType,majorName};
		return find(hql, values);
	}

/**
 * 根据成绩类类编号 、 学号查询
 */
	public List findGpResults3(String reType, String stuNo){
		String hql = "from GpResults where reType=? and stuNo=?";
		Object[] values = {reType,stuNo};
		return find(hql, values);
	}
	
/**
 * 根据导师号查询
 */
	public List findGpResultsByTutNo(String tutNo){
		String hql = "from GpResults where tutNo=?";
		return find(hql, tutNo);
	}
		
/**
 * 根据成绩类类编号 、 导师号查询
 */
	public List findGpResults4(String reType,String tutNo){
		String hql = "from GpResults where reType=? and tutNo=?";
		Object[] values = {reType,tutNo};
		return find(hql, values);
	}
	
/**
 * 根据成绩类类编号 、 导师号、 班级查询
 */
	public List findGpResults5(String reType,String tutNo,String className){
		String hql = "from GpResults where reType=? and tutNo=? and className=?";
		Object[] values = {reType,tutNo ,className};
		return find(hql, values);
		
	}
	
/**
 * 添加成绩
 */
	public void addGpResults(GpResults gpResults){
		save(gpResults);
	}
	
/**
 * 修改
 */
	public void updataGpResults(GpResults gpResults){
		update(gpResults);
	}
	
/**
 * 删除
 */
	public void deleteGpResults(GpResults gpResults){
		delete(gpResults);
	}
	
	
	
}
