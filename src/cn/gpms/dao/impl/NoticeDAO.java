package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.INoticeDAO;
import cn.gpms.vo.Notice;

public class NoticeDAO extends Base2DAO implements INoticeDAO {
	
	//添加公告
	public void addNotice(Notice notice){
		save(notice);
	}
	
/**
 * 根据发布人id查询
 */
	public List findNoticeByPublisherId(String publisherId){
		String hql = "from Notice where publisherId=?";
		return find(hql, publisherId);
	}
	
/**
 * 根据发布人职称
 */
	public List findNoticeByPosition(String position){
		String hql = "from Notice where position=?";
		return find(hql, position);
	}
	
/**
 * 根据公告编号查询
 */
	public List findNoticeByNoticeNo(String noticeNo){
		String hql = "from Notice where noticeNo=?";
		return find(hql, noticeNo);
	}

/**
 * 修改
 */
	public void updateNotice(Notice notice){
		update(notice);
	}
	
/**
 * 删除
 */
	public void deleteNotice(Notice notice){
		delete(notice);
	}
	
}
