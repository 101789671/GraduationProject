package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Switch;

public interface ISwitchDAO {
	
	// ���ݿ��ر�Ų�ѯ
	public List<Switch> findSwitchBySwitchNumber(String switchNumber);

	// �޸Ŀ���״̬
	public void updateSwitch(Switch switch1);

}
