package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.ITsControlDAO;
import cn.gpms.vo.TsControl;

public class TsControlDAO extends Base2DAO implements ITsControlDAO {

	public void addTsControl(TsControl tsControl) {
		save(tsControl);
	}

	public void deleteTsControl(TsControl tsControl) {
		delete(tsControl);
	}

	public List findTsControlByTutNo(String tutNo) {
		String hql = "from TsControl where tutNo=?";
		return find(hql, tutNo);
	}

	public void updataTsControl(TsControl tsControl) {
		update(tsControl);
	}

}
