package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.Progress;

public interface IProgressService {
	
	//��ӽ�����
	public void addProgress(Progress progress) throws Exception;
	
	//����ѧ�Ų�ѯ
	public List<Progress> findProgressByStuNo(String stuNo);
	
}
