package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.GpResults;

public interface IGpResultsDAO {
	
	//��ӳɼ�
	public void addGpResults(GpResults gpResults);
	
	//��ѯȫ��
	public List<GpResults> findGpResultsAll();
	
	//����ѧ�Ų�ѯ
	public List<GpResults> findGpResultsByStuNo(String stuNo);
	
	//���ݱ�Ų�ѯ
	public List<GpResults> findGpResultsByResultsNo(String resultsNo);
	
	//���ݳɼ����ͱ�Ų�ѯ
	public List<GpResults> findGpResultsByReType(String reType);
	
	//���ݳɼ������� �� �༶����ѯ
	public List<GpResults> findGpResults1(String reType, String className);
	
	//���ݳɼ������� �� רҵ����ѯ
	public List<GpResults> findGpResults2(String reType, String majorName);
	
	//���ݳɼ������� �� ѧ�Ų�ѯ
	public List<GpResults> findGpResults3(String reType, String stuNo);
	
	//���ݵ�ʦ�Ų�ѯ
	public List<GpResults> findGpResultsByTutNo(String tutNo);
	
	//���ݳɼ������� �� ��ʦ�Ų�ѯ
	public List<GpResults> findGpResults4(String reType,String tutNo);
	
	//���ݳɼ������� �� ��ʦ�š� �༶��ѯ
	public List<GpResults> findGpResults5(String reType,String tutNo,String className);
	
	//�޸�
	public void updataGpResults(GpResults gpResults);
	
	//ɾ��
	public void deleteGpResults(GpResults gpResults);

}
