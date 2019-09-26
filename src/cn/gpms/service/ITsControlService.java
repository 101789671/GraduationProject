package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.TsControl;

public interface ITsControlService {
	//添加出题权限
	public void addTsControl(TsControl tsControl);
	//删除出题权限
	public void deleteTsControl(TsControl tsControl);
	//根据tutNo查询权限
	public List<TsControl> findTsControlByTutNo(String tutNo);
	//修改出题权限
	public void updataTsControl();

}
