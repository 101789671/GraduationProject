package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.IGpFileDAO;
import cn.gpms.vo.GpFile;

public class GpFileDAO extends Base2DAO implements IGpFileDAO {

/**
 * 添加文件信息
 */
	public void addGpFile(GpFile gpFile) {
		save(gpFile);
	}
/**
 * 删除文件信息
 */
	public void deleteGpFile(GpFile gpFile){
		delete(gpFile);
	}
	
/**
 * 更新文件信息
 */
	public void updateGpFile(GpFile gpFile){
		update(gpFile);
	}

/**
 * 根据上传人ID、文件类别编号查询
 */
	public List findGpFileByUploaderId(String uploaderId,String fileCatNum) {
		String hql = "from GpFile where uploaderId=? and fileCatNum=?";
		Object[] values = {uploaderId,fileCatNum};
		return find(hql, values);
	}

/**
 * 根据上传人ID、文件类别编号、文件状态 查询
 */
	public List findGpFileBy1(String uploaderId,String fileCatNum,String fileState){
		String hql = "from GpFile where uploaderId=? and fileCatNum=? and fileState=?";
		Object[] values = {uploaderId,fileCatNum,fileState};
		return find(hql, values);
	}
	
/**
 * 根据文件编号查询
 */
	public List findGpFileByFileNo(String fileNo) {
		String hql = "from GpFile where fileNo=?";
		return find(hql, fileNo);
	}
	
/**
 * 根据文件类别编号查询
 */
	public List findGpFileByFileCatNum(String fileCatNum) {
		String Hql = "from GpFile where fileCatNum=?";
		Object value = fileCatNum;
		return find(Hql, value);
	}

/**
 * 根据上传者ID、审阅状态、文件类型编号 查询
 */
	public List findGpFile1(String uploaderId, String reviewState,String fileCatNum) {
		String hql = "from GpFile where uploaderId=? and reviewState=? and fileCatNum=?";
		Object[] values = { uploaderId, reviewState, fileCatNum };
		return find(hql, values);
	}

}
