package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Notice;

public interface INoticeDAO {
	
	//添加公告
	public void addNotice(Notice notice);
	
	//根据发布人id查询
	public List<Notice> findNoticeByPublisherId(String publisherId);
	
	//根据发布人职称
	public List<Notice> findNoticeByPosition(String position);
	
	//根据公告编号查询
	public List<Notice> findNoticeByNoticeNo(String noticeNo);
	
	//修改
	public void updateNotice(Notice notice);
	
	//删除
	public void deleteNotice(Notice notice);

}
