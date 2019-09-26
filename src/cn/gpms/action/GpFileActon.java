package cn.gpms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.sun.org.apache.commons.digester.rss.Item;
import com.sun.star.lib.connections.pipe.pipeAcceptor;

import cn.gpms.service.IGpFileService;
import cn.gpms.service.ISwitchService;
import cn.gpms.util.DocConverter;
import cn.gpms.vo.Class;
import cn.gpms.vo.GpFile;
import cn.gpms.vo.GpResults;
import cn.gpms.vo.Grouping;
import cn.gpms.vo.Student;
import cn.gpms.vo.Subject;
import cn.gpms.vo.Switch;
import cn.gpms.vo.Tutor;
import cn.gpms.vo.User;

public class GpFileActon {
	
	private GpFile gpFile;
	private Item netkejianyanshi;//�����ҵĶ���

	protected IGpFileService gpFileService;
	protected ISwitchService switchService;

	private List<Student> studentList;
	private List<Tutor> tutorList;
	private List<Class> reclassList;
	private List<Subject> subjectList;
	private List<GpFile> gpFileList;
	private List<Grouping> groupingList;
	private List<GpResults> gpResultsList;

	// �ϴ��ļ����·��
	private final static String UPLOADDIR = "/fileUpload";//���·��
	private final static String DOWBLOADDIR = "/fileDownload";//���·��
	// �ϴ��ļ�����
	private List<File> file;
	// �ϴ��ļ�������
	private List<String> fileFileName;
	// �ϴ��ļ��������ͼ���
	private List<String> fileContentType;

/**
 * ����ѧ��
 */
	public String importStudent() throws Exception {
		System.out.println(this.getFileFileName());
		for (int i = 0; i < file.size(); i++) {
			// ѭ���ϴ�ÿ���ļ�
			File uploadFile = new File(UPLOADDIR);
			// ����ļ���
			String fileName = this.getFileFileName().get(i);
			// ��ȡ�ļ���׺��
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			if ("xls".equals(suffix.toLowerCase())|| "xlsx".equals(suffix.toLowerCase())) {
				// ����ļ�������
				InputStream in = new FileInputStream(file.get(i));
				uploadFile = gpFileService.uploadFile(i, in, fileName,UPLOADDIR,suffix,null);
				// ��ȡ���ļ�·��
				String url = uploadFile.getPath();
				if ("xlsx".equals(suffix.toLowerCase())) {
					studentList = gpFileService.addStudentXSSF(url);
				} else {
					studentList = gpFileService.addStudentHSSF(url);
				}
				// ɾ���ļ�
				gpFileService.deleteFile(uploadFile);
				in.close();
				ActionContext.getContext().put("message", "����ɹ���");
			} else {
				ActionContext.getContext().put("message", "�ļ�����Excel�ļ�");
			}
		}
		gpFile = new GpFile();
		return "relistStu";
	}

/**
 * ����ѧ��
 */
	public String importTutor() throws Exception {
		System.out.println(this.getFileFileName());
		for (int i = 0; i < file.size(); i++) {
			File uploadFile = new File(UPLOADDIR);
			String fileName = this.getFileFileName().get(i);
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			if ("xls".equals(suffix.toLowerCase())|| "xlsx".equals(suffix.toLowerCase())) {
				InputStream in = new FileInputStream(file.get(i));
				uploadFile = gpFileService.uploadFile(i, in, fileName,UPLOADDIR,suffix,null);
				String url = uploadFile.getPath();
				if ("xlsx".equals(suffix.toLowerCase())) {
					tutorList = gpFileService.addTutorXSSF(url);
				} else {
					tutorList = gpFileService.addTutorHSSF(url);
				}
				gpFileService.deleteFile(uploadFile);
				in.close();
				ActionContext.getContext().put("message", "����ɹ���");
			} else {
				ActionContext.getContext().put("message", "�ļ�����Excel�ļ�");
			}
		}
		gpFile = new GpFile();
		return "tutorlist";
	}

/**
 * ����༶
 */
	public String importClass() throws Exception {
		System.out.println(this.getFileFileName());
		for (int i = 0; i < file.size(); i++) {
			File uploadFile = new File(UPLOADDIR);
			String fileName = this.getFileFileName().get(i);
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			if ("xls".equals(suffix.toLowerCase())|| "xlsx".equals(suffix.toLowerCase())) {
				InputStream in = new FileInputStream(file.get(i));
				uploadFile = gpFileService.uploadFile(i, in, fileName,UPLOADDIR,suffix,null);
				String url = uploadFile.getPath();
				if ("xlsx".equals(suffix.toLowerCase())) {
					reclassList = gpFileService.addClassXSSF(url);
				} else {
					reclassList = gpFileService.addClassHSSF(url);
				}
				gpFileService.deleteFile(uploadFile);
				ActionContext.getContext().put("message", "����ɹ���");
				in.close();
			} else {
				ActionContext.getContext().put("message", "�ļ�����Excel�ļ�");
			}
		}
		gpFile = new GpFile();
		return "reclassList";
	}
	
/**
 * ������Ŀ
 */
	public String importSubject() throws Exception {
		System.out.println(this.getFileFileName());
		for (int i = 0; i < file.size(); i++) {
			File uploadFile = new File(UPLOADDIR);
			String fileName = this.getFileFileName().get(i);
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			if ("xls".equals(suffix.toLowerCase())
					|| "xlsx".equals(suffix.toLowerCase())) {
				InputStream in = new FileInputStream(file.get(i));
				uploadFile = gpFileService.uploadFile(i, in, fileName,UPLOADDIR,suffix,null);
				String url = uploadFile.getPath();
				if ("xlsx".equals(suffix.toLowerCase())) {
					subjectList = gpFileService.addSubjectXSSF(url);
				} else {
					subjectList = gpFileService.addSubjectHSSF(url);
				}
				gpFileService.deleteFile(uploadFile);
				ActionContext.getContext().put("message", "����ɹ���");
				in.close();
			} else {
				ActionContext.getContext().put("message", "�ļ�����Excel�ļ�");
			}
		}
		gpFile = new GpFile();
		return "subjectlist";
	}
	
/**
 * �������
 */
	public String importGrouping() throws Exception {
		System.out.println(this.getFileFileName());
		for (int i = 0; i < file.size(); i++) {
			File uploadFile = new File(UPLOADDIR);
			String fileName = this.getFileFileName().get(i);
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			if ("xls".equals(suffix.toLowerCase())
					|| "xlsx".equals(suffix.toLowerCase())) {
				InputStream in = new FileInputStream(file.get(i));
				uploadFile = gpFileService.uploadFile(i, in, fileName,
						UPLOADDIR, suffix, null);
				String url = uploadFile.getPath();
				if ("xlsx".equals(suffix.toLowerCase())) {
					groupingList = gpFileService.addGroupingXSSF(url);
				} else {
					groupingList = gpFileService.addGroupingHSSF(url);
				}
				gpFileService.deleteFile(uploadFile);
				ActionContext.getContext().put("message", "����ɹ���");
				in.close();
			} else {
				ActionContext.getContext().put("message", "�ļ�����Excel�ļ�");
			}
		}
		gpFile = new GpFile();
		return "listGrouping";
	}
	
/**
 * ����ɼ�
 */
	public String importGpResults() throws Exception {
		System.out.println(this.getFileFileName());
		for (int i = 0; i < file.size(); i++) {
			File uploadFile = new File(UPLOADDIR);
			String fileName = this.getFileFileName().get(i);
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			if ("xls".equals(suffix.toLowerCase())
					|| "xlsx".equals(suffix.toLowerCase())) {
				InputStream in = new FileInputStream(file.get(i));
				uploadFile = gpFileService.uploadFile(i, in, fileName,
						UPLOADDIR, suffix, null);
				String url = uploadFile.getPath();
				if ("xlsx".equals(suffix.toLowerCase())) {
					gpResultsList = gpFileService.addGpResultsXSSF(url);
				} else {
					gpResultsList = gpFileService.addGpResultsHSSF(url);
				}
				gpFileService.deleteFile(uploadFile);
				ActionContext.getContext().put("message", "����ɹ���");
				in.close();
			} else {
				ActionContext.getContext().put("message", "�ļ�����Excel�ļ�");
			}
		}
		gpFile = new GpFile();
		return "listGpResults";
	}
	
	
/**
 * �ϴ��������ϣ����ⱨ�桢���ģ�
 */
	public String uploadFile() throws Exception {
		String Eor = null;
		String fileCatNum = gpFile.getFileCatNum();
		Switch switch1 = switchService.findSwitchBySwitchNumber(fileCatNum+"S").get(0);
		if("T".equals(switch1.getSwitchState())){
		System.out.println(this.getFileFileName());
		for (int i = 0; i < file.size(); i++) {
			File uploadFile = new File(UPLOADDIR);
			String fileName = this.getFileFileName().get(i);
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			if ("doc".equals(suffix.toLowerCase())|| "docx".equals(suffix.toLowerCase())) {
				InputStream in = new FileInputStream(file.get(i));
				uploadFile = gpFileService.uploadFile(i, in, fileName,UPLOADDIR,suffix,fileCatNum);
				in.close();
				Map session = ActionContext.getContext().getSession();
				User user12 = (User) session.get("user");
				gpFileList = gpFileService.findGpFileByUploaderId(user12.getUserid(), fileCatNum);
				ActionContext.getContext().put("message", "�ϴ��ɹ���");
			} else {
				ActionContext.getContext().put("message", "�ļ������ĵ��ļ�");
			}
		}
		gpFile = new GpFile();
		}else {
			ActionContext.getContext().put("message", "�ļ��ϴ������ѹرգ�");
		}
		if("TP".equals(fileCatNum)){
			Eor= "toMyTp";
		}else if ("PA".equals(fileCatNum)) {
			Eor= "toMyPaper";
		}
		
		return Eor;
	}
	
/**
 * �ϴ��������ϣ���Ʒ��
 */
	public String uploadWOFile() throws Exception {
		String fileCatNum = gpFile.getFileCatNum();
		Switch switch1 = switchService.findSwitchBySwitchNumber(fileCatNum+"S").get(0);
		if("T".equals(switch1.getSwitchState())){
		System.out.println(this.getFileFileName());
		for (int i = 0; i < file.size(); i++) {
			File uploadFile = new File(UPLOADDIR);
			String fileName = this.getFileFileName().get(i);
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			String toLowSu = suffix.toLowerCase();
			if ("doc".equals(toLowSu)|| "docx".equals(toLowSu)||"xls".equals(toLowSu)||"xlsx".equals(toLowSu)||"zip".equals(toLowSu)||"rar".equals(toLowSu)) {
				InputStream in = new FileInputStream(file.get(i));
				uploadFile = gpFileService.uploadFile(i, in, fileName,
						UPLOADDIR, suffix, fileCatNum);
				in.close();
				Map session = ActionContext.getContext().getSession();
				User user12 = (User) session.get("user");
				gpFileList = gpFileService.findGpFileByUploaderId(
						user12.getUserid(), fileCatNum);
				ActionContext.getContext().put("message", "�ϴ��ɹ���");
			} else {
				ActionContext.getContext().put("message", "��֧�ִ������ļ�");
			}
		}
		gpFile = new GpFile();
		}else {
			ActionContext.getContext().put("message", "�ļ��ϴ������ѹرգ�");
		}
		return "toMyWorks";
	}
	
	
	
/**
 * ɾ���ļ�
 */
	public String deleteGpFile(){
		List<GpFile> gpFilelist = gpFileService.findGpFileByFileNo(this.gpFile.getFileNo());
		String fileCatNum = null;
		String Eor = null;
		if (!gpFilelist.isEmpty()) {
			GpFile gpFile = gpFilelist.get(0);
			fileCatNum = gpFile.getFileCatNum();
			String filePath = gpFile.getFilePath();
			File uploadFile = new File(filePath);
			gpFileService.deleteFile(uploadFile);
		} else {
			ActionContext.getContext().put("message", "ɾ��ʧ�ܣ�");
		}
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		gpFileList = gpFileService.findGpFileByUploaderId(user12.getUserid(),fileCatNum);
		if("TP".equals(fileCatNum)){
			Eor= "toMyTp";
		}else if ("PA".equals(fileCatNum)) {
			Eor= "toMyPaper";
		}else if ("WO".equals(fileCatNum)) {
			Eor= "toMyWorks";
		}
		return Eor;
	}
	
/**
 * �ļ�����
 */
	//1.�����ύ��ҵ�񷽷�����struts.xml�����÷���stream��
    public String downloadFile() throws Exception{
        return "download";
    }
    //2.�����ļ����ķ���
    //getAttrInputStream�Զ��׼���getter����������attrInputStream��һ������
	public InputStream getAttrInputStream() throws IOException {
		InputStream inputStream = new InputStream() {public int read() throws IOException {return 0;}};
		String fileNo = gpFile.getFileNo();
		if (fileNo.contains(", ")) {
			String ZipName = gpFileService.filePcakZip(DOWBLOADDIR, fileNo);
			gpFile = new GpFile();
			gpFile.setFileName(ZipName);
			inputStream = ServletActionContext.getServletContext().getResourceAsStream(DOWBLOADDIR + "/" + gpFile.getFileName());
			File file1 = new File(ServletActionContext.getRequest().getRealPath(DOWBLOADDIR) + "/" + gpFile.getFileName());
			file1.delete();// ɾ����ʱѹ����
		} else {
			GpFile gpFile = gpFileService.findGpFileByFileNo(fileNo).get(0);
			this.gpFile.setFileName(gpFile.getFileName());
			inputStream = ServletActionContext.getServletContext().getResourceAsStream(UPLOADDIR + "/" + gpFile.getFileName());// ���·��  getResourceAsStream�������Ǿ���·��
		}
		return inputStream;
	}
    //3.������ʾ���ļ������������ʾ���ļ�����
    public String getDownFileName(){
        //��Ҫ�������ı���
    	String fileName = StringUtils.substringAfterLast(gpFile.getFileName(), "_");//��ȡ���һ��\֮����ַ��� 
        try {
        	
            fileName = URLEncoder.encode(fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
       }
        gpFile = new GpFile();
       return fileName;
    }
	
    
/**
 * ��������
 */
	public String onlineReading() throws IOException {
		GpFile gpFile1 = new GpFile();
		String fileNo = gpFile.getFileNo();
		
		if(fileNo!=null && !"".equals(fileNo)){
			gpFile1 = gpFileService.findGpFileByFileNo(this.gpFile.getFileNo()).get(0);
			String filePath = gpFile1.getFilePath().replace("\\", "/");//replace �ַ��滻
			DocConverter converter = new DocConverter(filePath);
			String swfPath = converter.conver();
			String fileName = StringUtils.substringAfterLast(swfPath, "\\");
			gpFile1.setFileName(fileName);
			gpFileList = new ArrayList<GpFile>();
			gpFileList.add(gpFile1);
		}else{
			ActionContext.getContext().put("message", "��ȡ�ļ�ʧ�ܣ�");
		}
		gpFile = new GpFile();
		return "myjsp2";
	}
	
	
	
	
	
	
	
	
	
	
/**
 * @return
 */
	
	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	public static String getUploaddir() {
		return UPLOADDIR;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<Tutor> getTutorList() {
		return tutorList;
	}

	public void setTutorList(List<Tutor> tutorList) {
		this.tutorList = tutorList;
	}

	public List<Class> getReclassList() {
		return reclassList;
	}

	public void setReclassList(List<Class> reclassList) {
		this.reclassList = reclassList;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public GpFile getGpFile() {
		return gpFile;
	}

	public void setGpFile(GpFile gpFile) {
		this.gpFile = gpFile;
	}

	public IGpFileService getGpFileService() {
		return gpFileService;
	}

	public void setGpFileService(IGpFileService gpFileService) {
		this.gpFileService = gpFileService;
	}

	public List<GpFile> getGpFileList() {
		return gpFileList;
	}

	public void setGpFileList(List<GpFile> gpFileList) {
		this.gpFileList = gpFileList;
	}

	public Item getNetkejianyanshi() {
		return netkejianyanshi;
	}

	public void setNetkejianyanshi(Item netkejianyanshi) {
		this.netkejianyanshi = netkejianyanshi;
	}

	public ISwitchService getSwitchService() {
		return switchService;
	}

	public void setSwitchService(ISwitchService switchService) {
		this.switchService = switchService;
	}

	public static String getDowbloaddir() {
		return DOWBLOADDIR;
	}

	public List<Grouping> getGroupingList() {
		return groupingList;
	}

	public void setGroupingList(List<Grouping> groupingList) {
		this.groupingList = groupingList;
	}

	public List<GpResults> getGpResultsList() {
		return gpResultsList;
	}

	public void setGpResultsList(List<GpResults> gpResultsList) {
		this.gpResultsList = gpResultsList;
	}

}
