package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.IProgressDAO;
import cn.gpms.vo.Progress;

public class ProgressDAO extends Base2DAO implements IProgressDAO {
	
/**
 * 添加进度条
 */
	public void addProgress(Progress progress){
		save(progress);
	}

/**
 * 根据学号 、阶段查询
 */
	public List findProgresses1(String stuNo, String phase) {
		String hql = "from Progress where stuNo=? and phase=?";
		Object[] values = {stuNo,phase};
		return find(hql, values);
	}
	
/**
 * 根据学号查询
 */
	public List findProgressByStuNo(String stuNo){
		String hql = "from Progress where stuNo=?";
		return find(hql, stuNo);
	}

}
