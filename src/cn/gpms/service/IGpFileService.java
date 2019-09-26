package cn.gpms.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.gpms.vo.GpFile;
import cn.gpms.vo.GpResults;
import cn.gpms.vo.Grouping;
import cn.gpms.vo.Student;
import cn.gpms.vo.Subject;
import cn.gpms.vo.Tutor;
import cn.gpms.vo.Class;
import cn.gpms.vo.User;

public interface IGpFileService {
	// 上传文件
	public File uploadFile(int i, InputStream in, String fileName, String UPLOADDIR,String Suffix,String fileCategory)
			throws FileNotFoundException, IOException;

	// 删除文件
	public void deleteFile(File uploadFile);

	// 读取.xlsx表格，并添加student至数据库
	public List<Student> addStudentXSSF(String fileURL) throws Exception;

	// 读取.xls表格，并添加student至数据库
	public List<Student> addStudentHSSF(String fileURL) throws Exception;

	// 读取.xlsx表格，并添加tutor至数据库
	public List<Tutor> addTutorXSSF(String fileURL) throws Exception;

	// 读取.xls表格，并添加tutor至数据库
	public List<Tutor> addTutorHSSF(String fileURL) throws Exception;
	
	// 读取.xlsx表格，并添加class至数据库
	public List<Class> addClassXSSF(String fileURL) throws Exception;

	// 读取.xls表格，并添加class至数据库
	public List<Class> addClassHSSF(String fileURL) throws Exception;
	
	// 读取.xlsx表格，并添加subject至数据库
	public List<Subject> addSubjectXSSF(String fileURL) throws Exception;

	// 读取.xls表格，并添加subject至数据库
	public List<Subject> addSubjectHSSF(String fileURL) throws Exception;
	
	// 读取.xlsx表格，并添加Grouping至数据库
	public List<Grouping> addGroupingXSSF(String fileURL) throws Exception;

	// 读取.xls表格，并添加Grouping至数据库
	public List<Grouping> addGroupingHSSF(String fileURL) throws Exception;
	
	// 读取.xlsx表格，并添加GpResults至数据库
	public List<GpResults> addGpResultsXSSF(String fileURL) throws Exception;

	// 读取.xls表格，并添加GpResults至数据库
	public List<GpResults> addGpResultsHSSF(String fileURL) throws Exception;
	
	//根据上传人ID、文件类别编号查询
	public List<GpFile> findGpFileByUploaderId(String uploaderId,String fileCatNum);
	
	//根据文件类别编号查询
	public List<GpFile> findGpFileByFileCatNum(String fileCatNum);
	
	//根据文件编号查询
	public List<GpFile> findGpFileByFileNo(String fileNo);
	
	//前往学生某类型文件(概况)
	public List<GpFile> toStudentGPDatumSurvey(User user,String fileCatNum);
	
	//根据导师ID、学生上传的文件类型编号查询
	public List<GpFile> findGpFileByTutNo(String tutNo,String fileCatNum);
	
	//根据上传者ID、审阅状态、文件类型编号 查询
	public List<GpFile> findGpFile1(String uploaderId,String reviewState,String fileCatNum);
	
	//修改文件审阅状态
	public String reviewFile(GpFile gpFile);
	
	//批量下载文件打包
	public String filePcakZip( String DOWBLOADDIR,String items) throws IOException;
	




}
