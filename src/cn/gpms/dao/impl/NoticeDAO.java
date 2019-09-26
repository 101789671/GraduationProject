package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.INoticeDAO;
import cn.gpms.vo.Notice;

public class NoticeDAO extends Base2DAO implements INoticeDAO {
	
	//��ӹ���
	public void addNotice(Notice notice){
		save(notice);
	}
	
/**
 * ���ݷ�����id��ѯ
 */
	public List findNoticeByPublisherId(String publisherId){
		String hql = "from Notice where publisherId=?";
		return find(hql, publisherId);
	}
	
/**
 * ���ݷ�����ְ��
 */
	public List findNoticeByPosition(String position){
		String hql = "from Notice where position=?";
		return find(hql, position);
	}
	
/**
 * ���ݹ����Ų�ѯ
 */
	public List findNoticeByNoticeNo(String noticeNo){
		String hql = "from Notice where noticeNo=?";
		return find(hql, noticeNo);
	}

/**
 * �޸�
 */
	public void updateNotice(Notice notice){
		update(notice);
	}
	
/**
 * ɾ��
 */
	public void deleteNotice(Notice notice){
		delete(notice);
	}
	
}
