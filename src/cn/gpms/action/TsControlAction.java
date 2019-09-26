package cn.gpms.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cn.gpms.service.ITsControlService;
import cn.gpms.vo.TsControl;
import cn.gpms.vo.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TsControlAction extends ActionSupport{
	
	private TsControl tsControl;
	
	protected ITsControlService tsControlService;
	
	
/**
 * 修改出题权限
 */
	public void updateTsControl(){
		tsControlService.updataTsControl();
	}
	
	
	
	public ITsControlService getTsControlService() {
		return tsControlService;
	}

	public void setTsControlService(ITsControlService tsControlService) {
		this.tsControlService = tsControlService;
	}

	public TsControl getTsControl() {
		return tsControl;
	}

	public void setTsControl(TsControl tsControl) {
		this.tsControl = tsControl;
	}

}
