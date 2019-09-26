package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.ISwitchDAO;
import cn.gpms.vo.Switch;

public class SwitchDAO  extends Base2DAO implements ISwitchDAO {

	public List findSwitchBySwitchNumber(String switchNumber) {
		String hql = "from Switch where switchNumber=?";
		Object value = switchNumber;
		return find(hql, value);
	}

	public void updateSwitch(Switch switch1) {
		update(switch1);
	}

}
