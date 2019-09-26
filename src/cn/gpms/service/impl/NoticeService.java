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
 * 添加公告
 */
	public List<Notice> addNotice(Notice notice){
		List<Notice> noticelist = new ArrayList<Notice>();
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		if("admin".equals(notice.getPosition())){
			notice.setPublisherName("管理员");
		}else {
			notice.setPublisherName(user12.getUserName());
		}
		notice.setPublisherId(user12.getUserid());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 获取当前系统时间
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String noticeNo = user12.getUserid()+df1.format(new Date());
		notice.setNoticeNo(noticeNo);
		notice.setNoticeTime(df.format(new Date()));	
		noticeDAO.addNotice(notice);	
		noticelist.add(notice);
		return noticelist;	
	}
	
/**
 * 根据发布人id查询
 */
	public List<Notice> findNoticeByPublisherId(String publisherId){
		return noticeDAO.findNoticeByPublisherId(publisherId);
	}

/**
 * 根据发布人职称
 */
	public List<Notice> findNoticeByPosition(String position){
		return noticeDAO.findNoticeByPosition(position);
	}
	
/**
 * 根据公告编号查询
 */
	public List<Notice> findNoticeByNoticeNo(String noticeNo){
		return noticeDAO.findNoticeByNoticeNo(noticeNo);
	}
	
/**
 * 修改
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
	
	//删除
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
