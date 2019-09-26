package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.Notice;

public interface INoticeService {
	
	//��ӹ���
	public List<Notice> addNotice(Notice notice);
	
	//���ݷ�����id��ѯ
	public List<Notice> findNoticeByPublisherId(String publisherId);
	
	//���ݷ�����ְ��
	public List<Notice> findNoticeByPosition(String position);
	
	//���ݹ����Ų�ѯ
	public List<Notice> findNoticeByNoticeNo(String noticeNo);

	//�޸�
	public List<Notice> updateNotice(Notice notice);
	
	//ɾ��
	public void deleteNotice(Notice notice);
}
