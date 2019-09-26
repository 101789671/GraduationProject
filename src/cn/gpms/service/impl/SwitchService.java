package cn.gpms.service.impl;

import java.util.List;

import cn.gpms.dao.ISwitchDAO;
import cn.gpms.service.ISwitchService;
import cn.gpms.vo.Switch;

public class SwitchService implements ISwitchService {
	
	protected ISwitchDAO switchDAO;

	public List<Switch> findSwitchBySwitchNumber(String switchNumber) {
		return switchDAO.findSwitchBySwitchNumber(switchNumber);
	}

	public void updateSwitch(String switchNumber, String switchState) {
		Switch switch1 = switchDAO.findSwitchBySwitchNumber(switchNumber).get(0);
		switch1.setSwitchState(switchState);
		switchDAO.updateSwitch(switch1);
		
	}
	
	

	public ISwitchDAO getSwitchDAO() {
		return switchDAO;
	}

	public void setSwitchDAO(ISwitchDAO switchDAO) {
		this.switchDAO = switchDAO;
	}

}
