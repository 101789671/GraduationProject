package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Switch;

public interface ISwitchDAO {
	
	// 根据开关编号查询
	public List<Switch> findSwitchBySwitchNumber(String switchNumber);

	// 修改开关状态
	public void updateSwitch(Switch switch1);

}
