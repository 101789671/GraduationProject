package cn.gpms.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import cn.gpms.dao.INoticeDAO;
import cn.gpms.service.INoticeService;
import cn.gpms.vo.Notice;
import cn.gpms.vo.User;

public class NoticeService implements INoticeService {
	protected INoticeDAO noticeDAO;
	
/**
 * ��ӹ���
 */
	public List<Notice> addNotice(Notice notice){
		List<Notice> noticelist = new ArrayList<Notice>();
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		if("admin".equals(notice.getPosition())){
			notice.setPublisherName("����Ա");
		}else {
			notice.setPublisherName(user12.getUserName());
		}
		notice.setPublisherId(user12.getUserid());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// ��ȡ��ǰϵͳʱ��
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String noticeNo = user12.getUserid()+df1.format(new Date());
		notice.setNoticeNo(noticeNo);
		notice.setNoticeTime(df.format(new Date()));	
		noticeDAO.addNotice(notice);	
		noticelist.add(notice);
		return noticelist;	
	}
	
/**
 * ���ݷ�����id��ѯ
 */
	public List<Notice> findNoticeByPublisherId(String publisherId){
		return noticeDAO.findNoticeByPublisherId(publisherId);
	}

/**
 * ���ݷ�����ְ��
 */
	public List<Notice> findNoticeByPosition(String position){
		return noticeDAO.findNoticeByPosition(position);
	}
	
/**
 * ���ݹ����Ų�ѯ
 */
	public List<Notice> findNoticeByNoticeNo(String noticeNo){
		return noticeDAO.findNoticeByNoticeNo(noticeNo);
	}
	
/**
 * �޸�
 */
	public List<Notice> updateNotice(Notice notice){
		List<Notice> noticeList = new ArrayList<Notice>();
		List<Notice> noticelist = noticeDAO.findNoticeByNoticeNo(notice.getNoticeNo());
		if(!noticelist.isEmpty()){
			Notice notice1 = noticelist.get(0);
			notice1.setNoticeTitle(notice.getNoticeTitle());
			notice1.setNoticeContent(notice.getNoticeContent());
			notice1.setPubObject(notice.getPubObject());
			noticeDAO.updateNotice(notice1);
			noticeList.add(notice1);		
		}
		return noticeList;
	}
	
	//ɾ��
	public void deleteNotice(Notice notice){
		noticeDAO.deleteNotice(notice);
	}

public INoticeDAO getNoticeDAO() {
	return noticeDAO;
}

public void setNoticeDAO(INoticeDAO noticeDAO) {
	this.noticeDAO = noticeDAO;
}

}
