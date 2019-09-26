package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.GpFile;

public interface IGpFileDAO {
	//添加文件信息
	public void addGpFile(GpFile gpFile);
	//删除文件信息
	public void deleteGpFile(GpFile gpFile);
	
	//更新文件信息
	public void updateGpFile(GpFile gpFile);

	//根据上传人ID、文件类别编号查询
	public List<GpFile> findGpFileByUploaderId(String uploaderId,String fileCatNum);
	
	//根据上传人ID、文件类别编号、文件状态查询
	public List<GpFile> findGpFileBy1(String uploaderId,String fileCatNum,String fileState);
	
	//根据文件编号查询
	public List<GpFile> findGpFileByFileNo(String fileNo);
	
	//根据文件类别编号查询
	public List<GpFile> findGpFileByFileCatNum(String fileCatNum);
	
	//根据上传者ID、审阅状态、文件类型编号 查询
	public List<GpFile> findGpFile1(String uploaderId,String reviewState,String fileCatNum);
	
}
