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
	// �ϴ��ļ�
	public File uploadFile(int i, InputStream in, String fileName, String UPLOADDIR,String Suffix,String fileCategory)
			throws FileNotFoundException, IOException;

	// ɾ���ļ�
	public void deleteFile(File uploadFile);

	// ��ȡ.xlsx��񣬲����student�����ݿ�
	public List<Student> addStudentXSSF(String fileURL) throws Exception;

	// ��ȡ.xls��񣬲����student�����ݿ�
	public List<Student> addStudentHSSF(String fileURL) throws Exception;

	// ��ȡ.xlsx��񣬲����tutor�����ݿ�
	public List<Tutor> addTutorXSSF(String fileURL) throws Exception;

	// ��ȡ.xls��񣬲����tutor�����ݿ�
	public List<Tutor> addTutorHSSF(String fileURL) throws Exception;
	
	// ��ȡ.xlsx��񣬲����class�����ݿ�
	public List<Class> addClassXSSF(String fileURL) throws Exception;

	// ��ȡ.xls��񣬲����class�����ݿ�
	public List<Class> addClassHSSF(String fileURL) throws Exception;
	
	// ��ȡ.xlsx��񣬲����subject�����ݿ�
	public List<Subject> addSubjectXSSF(String fileURL) throws Exception;

	// ��ȡ.xls��񣬲����subject�����ݿ�
	public List<Subject> addSubjectHSSF(String fileURL) throws Exception;
	
	// ��ȡ.xlsx��񣬲����Grouping�����ݿ�
	public List<Grouping> addGroupingXSSF(String fileURL) throws Exception;

	// ��ȡ.xls��񣬲����Grouping�����ݿ�
	public List<Grouping> addGroupingHSSF(String fileURL) throws Exception;
	
	// ��ȡ.xlsx��񣬲����GpResults�����ݿ�
	public List<GpResults> addGpResultsXSSF(String fileURL) throws Exception;

	// ��ȡ.xls��񣬲����GpResults�����ݿ�
	public List<GpResults> addGpResultsHSSF(String fileURL) throws Exception;
	
	//�����ϴ���ID���ļ�����Ų�ѯ
	public List<GpFile> findGpFileByUploaderId(String uploaderId,String fileCatNum);
	
	//�����ļ�����Ų�ѯ
	public List<GpFile> findGpFileByFileCatNum(String fileCatNum);
	
	//�����ļ���Ų�ѯ
	public List<GpFile> findGpFileByFileNo(String fileNo);
	
	//ǰ��ѧ��ĳ�����ļ�(�ſ�)
	public List<GpFile> toStudentGPDatumSurvey(User user,String fileCatNum);
	
	//���ݵ�ʦID��ѧ���ϴ����ļ����ͱ�Ų�ѯ
	public List<GpFile> findGpFileByTutNo(String tutNo,String fileCatNum);
	
	//�����ϴ���ID������״̬���ļ����ͱ�� ��ѯ
	public List<GpFile> findGpFile1(String uploaderId,String reviewState,String fileCatNum);
	
	//�޸��ļ�����״̬
	public String reviewFile(GpFile gpFile);
	
	//���������ļ����
	public String filePcakZip( String DOWBLOADDIR,String items) throws IOException;
	




}
