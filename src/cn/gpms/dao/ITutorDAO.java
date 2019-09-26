package cn.gpms.dao;

import java.util.List;

import cn.gpms.form.TutorForm;
import cn.gpms.vo.Tutor;

public interface ITutorDAO {
	//��ӵ�ʦ
	public void addTutor(Tutor tutor);
	//���ݵ�ʦ���鵼ʦ������Tutor
	public List findTuByName(String tutName);
	
	
	//��ѯ���е�ʦ
	public List findTuAll();
	//����tutNo��ѯ��ʦ,����List
	public List findTutorByNo(String tutNo);
	//����ѧԺ��ѯ��ʦ
	public List findTuByIns(String instituteName);
	
	
	//����ɾ����ʦ
	public void delTutor(Tutor tutor);
	//�޸ĵ�ʦ
	public void updateTutor(Tutor tutor);
	
	
	//����No�鵼ʦ������Tutor
	//public Tutor findTutorByNo(String tutNo);

	
}
