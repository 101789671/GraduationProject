package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.Switch;

public interface ISwitchService {

	//���ݿ��ر�Ų�ѯ
	public List<Switch> findSwitchBySwitchNumber(String switchNumber);
	
	//�޸Ŀ���״̬
	public void updateSwitch(String switchNumber, String switchState);
}
