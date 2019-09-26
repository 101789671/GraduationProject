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
	private Item netkejianyanshi;//所查找的对象

	protected IGpFileService gpFileService;
	protected ISwitchService switchService;

	private List<Student> studentList;
	private List<Tutor> tutorList;
	private List<Class> reclassList;
	private List<Subject> subjectList;
	private List<GpFile> gpFileList;
	private List<Grouping> groupingList;
	private List<GpResults> gpResultsList;

	// 上传文件存放路径
	private final static String UPLOADDIR = "/fileUpload";//相对路径
	private final static String DOWBLOADDIR = "/fileDownload";//相对路径
	// 上传文件集合
	private List<File> file;
	// 上传文件名集合
	private List<String> fileFileName;
	// 上传文件内容类型集合
	private List<String> fileContentType;

/**
 * 导入学生
 */
	public String importStudent() throws Exception {
		System.out.println(this.getFileFileName());
		for (int i = 0; i < file.size(); i++) {
			// 循环上传每个文件
			File uploadFile = new File(UPLOADDIR);
			// 获得文件名
			String fileName = this.getFileFileName().get(i);
			// 获取文件后缀名
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			if ("xls".equals(suffix.toLowerCase())|| "xlsx".equals(suffix.toLowerCase())) {
				// 获得文件输入流
				InputStream in = new FileInputStream(file.get(i));
				uploadFile = gpFileService.uploadFile(i, in, fileName,UPLOADDIR,suffix,null);
				// 获取新文件路径
				String url = uploadFile.getPath();
				if ("xlsx".equals(suffix.toLowerCase())) {
					studentList = gpFileService.addStudentXSSF(url);
				} else {
					studentList = gpFileService.addStudentHSSF(url);
				}
				// 删除文件
				gpFileService.deleteFile(uploadFile);
				in.close();
				ActionContext.getContext().put("message", "导入成功！");
			} else {
				ActionContext.getContext().put("message", "文件不是Excel文件");
			}
		}
		gpFile = new GpFile();
		return "relistStu";
	}

/**
 * 导入学生
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
				ActionContext.getContext().put("message", "导入成功！");
			} else {
				ActionContext.getContext().put("message", "文件不是Excel文件");
			}
		}
		gpFile = new GpFile();
		return "tutorlist";
	}

/**
 * 导入班级
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
				ActionContext.getContext().put("message", "导入成功！");
				in.close();
			} else {
				ActionContext.getContext().put("message", "文件不是Excel文件");
			}
		}
		gpFile = new GpFile();
		return "reclassList";
	}
	
/**
 * 导入题目
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
				ActionContext.getContext().put("message", "导入成功！");
				in.close();
			} else {
				ActionContext.getContext().put("message", "文件不是Excel文件");
			}
		}
		gpFile = new GpFile();
		return "subjectlist";
	}
	
/**
 * 导入分组
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
				ActionContext.getContext().put("message", "导入成功！");
				in.close();
			} else {
				ActionContext.getContext().put("message", "文件不是Excel文件");
			}
		}
		gpFile = new GpFile();
		return "listGrouping";
	}
	
/**
 * 导入成绩
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
				ActionContext.getContext().put("message", "导入成功！");
				in.close();
			} else {
				ActionContext.getContext().put("message", "文件不是Excel文件");
			}
		}
		gpFile = new GpFile();
		return "listGpResults";
	}
	
	
/**
 * 上传毕设资料（开题报告、论文）
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
				ActionContext.getContext().put("message", "上传成功！");
			} else {
				ActionContext.getContext().put("message", "文件不是文档文件");
			}
		}
		gpFile = new GpFile();
		}else {
			ActionContext.getContext().put("message", "文件上传功能已关闭！");
		}
		if("TP".equals(fileCatNum)){
			Eor= "toMyTp";
		}else if ("PA".equals(fileCatNum)) {
			Eor= "toMyPaper";
		}
		
		return Eor;
	}
	
/**
 * 上传毕设资料（作品）
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
				ActionContext.getContext().put("message", "上传成功！");
			} else {
				ActionContext.getContext().put("message", "不支持此类型文件");
			}
		}
		gpFile = new GpFile();
		}else {
			ActionContext.getContext().put("message", "文件上传功能已关闭！");
		}
		return "toMyWorks";
	}
	
	
	
/**
 * 删除文件
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
			ActionContext.getContext().put("message", "删除失败！");
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
 * 文件下载
 */
	//1.下载提交的业务方法（在struts.xml中配置返回stream）
    public String downloadFile() throws Exception{
        return "download";
    }
    //2.返回文件流的方法
    //getAttrInputStream显而易见是getter方法，所以attrInputStream是一个属性
	public InputStream getAttrInputStream() throws IOException {
		InputStream inputStream = new InputStream() {public int read() throws IOException {return 0;}};
		String fileNo = gpFile.getFileNo();
		if (fileNo.contains(", ")) {
			String ZipName = gpFileService.filePcakZip(DOWBLOADDIR, fileNo);
			gpFile = new GpFile();
			gpFile.setFileName(ZipName);
			inputStream = ServletActionContext.getServletContext().getResourceAsStream(DOWBLOADDIR + "/" + gpFile.getFileName());
			File file1 = new File(ServletActionContext.getRequest().getRealPath(DOWBLOADDIR) + "/" + gpFile.getFileName());
			file1.delete();// 删除临时压缩包
		} else {
			GpFile gpFile = gpFileService.findGpFileByFileNo(fileNo).get(0);
			this.gpFile.setFileName(gpFile.getFileName());
			inputStream = ServletActionContext.getServletContext().getResourceAsStream(UPLOADDIR + "/" + gpFile.getFileName());// 相对路径  getResourceAsStream不可用是绝对路径
		}
		return inputStream;
	}
    //3.下载显示的文件名（浏览器显示的文件名）
    public String getDownFileName(){
        //需要进行中文编码
    	String fileName = StringUtils.substringAfterLast(gpFile.getFileName(), "_");//截取最后一个\之后的字符串 
        try {
        	
            fileName = URLEncoder.encode(fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
       }
        gpFile = new GpFile();
       return fileName;
    }
	
    
/**
 * 在线阅览
 */
	public String onlineReading() throws IOException {
		GpFile gpFile1 = new GpFile();
		String fileNo = gpFile.getFileNo();
		
		if(fileNo!=null && !"".equals(fileNo)){
			gpFile1 = gpFileService.findGpFileByFileNo(this.gpFile.getFileNo()).get(0);
			String filePath = gpFile1.getFilePath().replace("\\", "/");//replace 字符替换
			DocConverter converter = new DocConverter(filePath);
			String swfPath = converter.conver();
			String fileName = StringUtils.substringAfterLast(swfPath, "\\");
			gpFile1.setFileName(fileName);
			gpFileList = new ArrayList<GpFile>();
			gpFileList.add(gpFile1);
		}else{
			ActionContext.getContext().put("message", "获取文件失败！");
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
