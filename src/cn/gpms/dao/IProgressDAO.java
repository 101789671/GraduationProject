package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Progress;

public interface IProgressDAO {
	
	//��ӽ�����
	public void addProgress(Progress progress);
	
	//����ѧ�� ���׶β�ѯ
	public List<Progress> findProgresses1(String stuNo , String phase);
	
	//����ѧ�Ų�ѯ
	public List<Progress> findProgressByStuNo(String stuNo);

}
