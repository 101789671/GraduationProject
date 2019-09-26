package cn.gpms.action;

import java.util.List;
import java.util.Map;

import cn.gpms.service.INoticeService;
import cn.gpms.vo.Notice;
import cn.gpms.vo.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class NoticeAction extends ActionSupport{
	private Notice notice;
	
	protected INoticeService noticeService;
	
	private List<Notice> noticeList;
	
	
/**
 * 添加公告
 */
	public String addNotice(){
		String Eor=null;
		noticeList = noticeService.addNotice(notice);
		if(!noticeList.isEmpty()){
			ActionContext.getContext().put("message", "发布成功！");
			Eor = "listNoticeAll";
		}else{
			ActionContext.getContext().put("message", "发布失败！");
			Eor = "listNoticeAll";
		}
		notice = new Notice();
		return Eor;
	}

/**
 * 显示某用户所有公告
 */
	public String listNoticeAll(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		noticeList = noticeService.findNoticeByPublisherId(user12.getUserid());
		return "listNoticeAll";
	}

/**
 * 公告详情
 */
	public String noticeDetails(){
		noticeList =noticeService.findNoticeByNoticeNo(notice.getNoticeNo());
		return "noticeDetails";
	}
/**
 * 前往修改
 */
	public String toUpdateNotice(){
		noticeList =noticeService.findNoticeByNoticeNo(notice.getNoticeNo());
		return "toUpdateNotice";
	}
/**
 * 修改
 */
	public String updateNotice(){
		noticeList = noticeService.updateNotice(notice);
		if(noticeList.isEmpty()){
			ActionContext.getContext().put("message", "修改失败！");
		}else{
			ActionContext.getContext().put("message", "修改成功！");
		}
		return "noticeDetails"; 
	}

/**
 * 删除
 */
	public String deleteNotice(){
		noticeService.deleteNotice(notice);
		listNoticeAll();
		return "listNoticeAll";
	}
	

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public INoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public List<Notice> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}

}
