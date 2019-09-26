package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.GpFile;

public interface IGpFileDAO {
	//����ļ���Ϣ
	public void addGpFile(GpFile gpFile);
	//ɾ���ļ���Ϣ
	public void deleteGpFile(GpFile gpFile);
	
	//�����ļ���Ϣ
	public void updateGpFile(GpFile gpFile);

	//�����ϴ���ID���ļ�����Ų�ѯ
	public List<GpFile> findGpFileByUploaderId(String uploaderId,String fileCatNum);
	
	//�����ϴ���ID���ļ�����š��ļ�״̬��ѯ
	public List<GpFile> findGpFileBy1(String uploaderId,String fileCatNum,String fileState);
	
	//�����ļ���Ų�ѯ
	public List<GpFile> findGpFileByFileNo(String fileNo);
	
	//�����ļ�����Ų�ѯ
	public List<GpFile> findGpFileByFileCatNum(String fileCatNum);
	
	//�����ϴ���ID������״̬���ļ����ͱ�� ��ѯ
	public List<GpFile> findGpFile1(String uploaderId,String reviewState,String fileCatNum);
	
}
