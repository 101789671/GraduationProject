package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.TsControl;

public interface ITsControlService {
	//���ӳ���Ȩ��
	public void addTsControl(TsControl tsControl);
	//ɾ������Ȩ��
	public void deleteTsControl(TsControl tsControl);
	//����tutNo��ѯȨ��
	public List<TsControl> findTsControlByTutNo(String tutNo);
	//�޸ĳ���Ȩ��
	public void updataTsControl();

}