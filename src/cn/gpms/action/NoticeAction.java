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
 * ��ӹ���
 */
	public String addNotice(){
		String Eor=null;
		noticeList = noticeService.addNotice(notice);
		if(!noticeList.isEmpty()){
			ActionContext.getContext().put("message", "�����ɹ���");
			Eor = "listNoticeAll";
		}else{
			ActionContext.getContext().put("message", "����ʧ�ܣ�");
			Eor = "listNoticeAll";
		}
		notice = new Notice();
		return Eor;
	}

/**
 * ��ʾĳ�û����й���
 */
	public String listNoticeAll(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		noticeList = noticeService.findNoticeByPublisherId(user12.getUserid());
		return "listNoticeAll";
	}

/**
 * ��������
 */
	public String noticeDetails(){
		noticeList =noticeService.findNoticeByNoticeNo(notice.getNoticeNo());
		return "noticeDetails";
	}
/**
 * ǰ���޸�
 */
	public String toUpdateNotice(){
		noticeList =noticeService.findNoticeByNoticeNo(notice.getNoticeNo());
		return "toUpdateNotice";
	}
/**
 * �޸�
 */
	public String updateNotice(){
		noticeList = noticeService.updateNotice(notice);
		if(noticeList.isEmpty()){
			ActionContext.getContext().put("message", "�޸�ʧ�ܣ�");
		}else{
			ActionContext.getContext().put("message", "�޸ĳɹ���");
		}
		return "noticeDetails"; 
	}

/**
 * ɾ��
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
