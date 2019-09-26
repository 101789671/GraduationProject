package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.IGpFileDAO;
import cn.gpms.vo.GpFile;

public class GpFileDAO extends Base2DAO implements IGpFileDAO {

/**
 * ����ļ���Ϣ
 */
	public void addGpFile(GpFile gpFile) {
		save(gpFile);
	}
/**
 * ɾ���ļ���Ϣ
 */
	public void deleteGpFile(GpFile gpFile){
		delete(gpFile);
	}
	
/**
 * �����ļ���Ϣ
 */
	public void updateGpFile(GpFile gpFile){
		update(gpFile);
	}

/**
 * �����ϴ���ID���ļ�����Ų�ѯ
 */
	public List findGpFileByUploaderId(String uploaderId,String fileCatNum) {
		String hql = "from GpFile where uploaderId=? and fileCatNum=?";
		Object[] values = {uploaderId,fileCatNum};
		return find(hql, values);
	}

/**
 * �����ϴ���ID���ļ�����š��ļ�״̬ ��ѯ
 */
	public List findGpFileBy1(String uploaderId,String fileCatNum,String fileState){
		String hql = "from GpFile where uploaderId=? and fileCatNum=? and fileState=?";
		Object[] values = {uploaderId,fileCatNum,fileState};
		return find(hql, values);
	}
	
/**
 * �����ļ���Ų�ѯ
 */
	public List findGpFileByFileNo(String fileNo) {
		String hql = "from GpFile where fileNo=?";
		return find(hql, fileNo);
	}
	
/**
 * �����ļ�����Ų�ѯ
 */
	public List findGpFileByFileCatNum(String fileCatNum) {
		String Hql = "from GpFile where fileCatNum=?";
		Object value = fileCatNum;
		return find(Hql, value);
	}

/**
 * �����ϴ���ID������״̬���ļ����ͱ�� ��ѯ
 */
	public List findGpFile1(String uploaderId, String reviewState,String fileCatNum) {
		String hql = "from GpFile where uploaderId=? and reviewState=? and fileCatNum=?";
		Object[] values = { uploaderId, reviewState, fileCatNum };
		return find(hql, values);
	}

}
