package cn.gpms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.gpms.dao.ITsControlDAO;
import cn.gpms.service.ITsControlService;
import cn.gpms.vo.TsControl;
import cn.gpms.vo.User;

public class TsControlService implements ITsControlService {
	protected ITsControlDAO tsControlDAO; 
	
/**
 * ��ӳ���Ȩ��
 */
	public void addTsControl(TsControl tsControl){
		tsControlDAO.addTsControl(tsControl);
	}
/**
 * ɾ������Ȩ��
 */
	public void deleteTsControl(TsControl tsControl){
		tsControlDAO.deleteTsControl(tsControl);
	}
/**
 * ����tutNo��ѯȨ��
 */
	public List<TsControl> findTsControlByTutNo(String tutNo){
		return tsControlDAO.findTsControlByTutNo(tutNo);
	}
/**
 * �޸ĳ���Ȩ��
 */
	public void updataTsControl(){
		TsControl tsControl = new TsControl();
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		String items = ServletActionContext.getRequest().getParameter("delitems"); 
		tsControl.setTutNo(user12.getUserid());
		tsControl.setTutName(user12.getUserName());
		tsControl.setPermissions(items);
		tsControlDAO.updataTsControl(tsControl);
	}


	
	
	
	
	public ITsControlDAO getTsControlDAO() {
		return tsControlDAO;
	}

	public void setTsControlDAO(ITsControlDAO tsControlDAO) {
		this.tsControlDAO = tsControlDAO;
	}
}

