package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Progress;

public interface IProgressDAO {
	
	//添加进度条
	public void addProgress(Progress progress);
	
	//根据学号 、阶段查询
	public List<Progress> findProgresses1(String stuNo , String phase);
	
	//根据学号查询
	public List<Progress> findProgressByStuNo(String stuNo);

}
