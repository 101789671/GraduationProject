package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.Progress;

public interface IProgressService {
	
	//添加进度条
	public void addProgress(Progress progress) throws Exception;
	
	//根据学号查询
	public List<Progress> findProgressByStuNo(String stuNo);
	
}
