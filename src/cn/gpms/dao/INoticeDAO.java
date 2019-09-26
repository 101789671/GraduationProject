package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Notice;

public interface INoticeDAO {
	
	//��ӹ���
	public void addNotice(Notice notice);
	
	//���ݷ�����id��ѯ
	public List<Notice> findNoticeByPublisherId(String publisherId);
	
	//���ݷ�����ְ��
	public List<Notice> findNoticeByPosition(String position);
	
	//���ݹ����Ų�ѯ
	public List<Notice> findNoticeByNoticeNo(String noticeNo);
	
	//�޸�
	public void updateNotice(Notice notice);
	
	//ɾ��
	public void deleteNotice(Notice notice);

}
