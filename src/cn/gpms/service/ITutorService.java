package cn.gpms.service;

import java.util.List;

import cn.gpms.form.TutorForm;
import cn.gpms.vo.GpFile;
import cn.gpms.vo.Tutor;

public interface ITutorService {
	//��ӵ�ʦ
	public void addTutor(Tutor tutor);

	//���ݵ�ʦ���鵼ʦ������Tutor
	public List findTuByName(String tutName);
	
	
	//��ѯ���е�ʦ
	public List findTuAll();
	//ǰ���޸ĵ�ʦ
	public List<Tutor> toUpdateTu(String tutNo);
	//����No�鵼ʦ������List
	public List findTutorByNo(String tutNo);
	//����ѧԺ��ѯ��ʦ
	public List findTuByIns(String instituteName);
	
	
	//ɾ����ʦ
	public void delTutor(Tutor tutor);
	
	
	//�޸ĵ�ʦ
	public void updateTutor(TutorForm tutorForm);
	
	


}
