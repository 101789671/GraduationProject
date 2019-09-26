package cn.gpms.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.gpms.dao.IGpFileDAO;
import cn.gpms.service.IAnswergroupService;
import cn.gpms.service.IClassService;
import cn.gpms.service.IGpFileService;
import cn.gpms.service.IGpResultsService;
import cn.gpms.service.IGroupingService;
import cn.gpms.service.IStudentService;
import cn.gpms.service.ISubjectService;
import cn.gpms.service.ITsControlService;
import cn.gpms.service.ITutorService;
import cn.gpms.service.IUserService;
import cn.gpms.vo.Answergroup;
import cn.gpms.vo.GpFile;
import cn.gpms.vo.GpResults;
import cn.gpms.vo.Grouping;
import cn.gpms.vo.Student;
import cn.gpms.vo.Subject;
import cn.gpms.vo.TsControl;
import cn.gpms.vo.Tutor;
import cn.gpms.vo.User;
import cn.gpms.vo.Class;

public class GpFileService implements IGpFileService {
	
	protected IGpFileDAO gpFileDAO;
	protected IStudentService studentService;
	protected IUserService userService;
	protected ITutorService tutorService;
	protected IClassService classService;
	protected ISubjectService subjectService;
	protected ITsControlService tsControlService;
	protected IGroupingService groupingService;
	protected IAnswergroupService answergroupService;
	protected IGpResultsService gpResultsService;
	

/**
 * 上传文件
 */
	public File uploadFile(int i, InputStream in, String fileName, String dir,String suffix,String fileCatNum)
			throws FileNotFoundException, IOException {
		String path = null;
		// String dir = ServletActionContext.getRequest().getRealPath(UPLOADDIR);//获得项目根目录上传路径
		dir = ServletActionContext.getRequest().getRealPath(dir);//获得项目根目录上传路径 更改为相对路径时，取消注释即可
		// 或 String dir = "F://upload";//自定义上传路径
		File fileLocation = new File(dir);
		// 此处也可以在应用根目录手动建立目标上传目录
		if (fileLocation.exists()==false) {
			boolean isCreated = fileLocation.mkdir();
			if (isCreated==false) {
				// 目标上传目录创建失败,可做其他处理,例如抛出自定义异常等,一般应该不会出现这种情况。
				System.out.println("创建文件夹失败！");
			}
		}
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");// 获取当前系统时间
		String prefix = user12.getUserid()+df1.format(new Date());//创建文件前缀
		String fileNo = prefix+i;//创建文件编号
		fileName = fileNo+"_"+fileName;//创建虚文件名
		File uploadFile = new File(dir, fileName); // 创建新文件路径
		path = uploadFile.getPath();// 获取新文件路径
		OutputStream out = new FileOutputStream(uploadFile); // 在磁盘创建文件，此时文件内容为空
		byte[] buffer = new byte[1024 * 1024]; // 创建一个数字长度为1024*1024的byte数组
		int length;
		while ((length = in.read(buffer)) > 0) { // .read()从输入流（in）中读取一定数量的字节并将它们存储到缓冲区数组b。实际读取的字节数作为整数返回。如果B的长度为零，则不读取字节，返回0
			out.write(buffer, 0, length); // 给文件写入内容,.write(数据,
											// 数据偏移的开始位置,写入的字节数)
		}
		in.close();
		out.close();
		
		GpFile gpFile = new GpFile();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
		
		gpFile.setFileNo(fileNo);
		if("xls".equals(suffix.toLowerCase())||"xlsx".equals(suffix.toLowerCase())){
			gpFile.setFileType("表格");
		}else if ("doc".equals(suffix.toLowerCase())||"docx".equals(suffix.toLowerCase())) {
			gpFile.setFileType("文档");
		}else {
			gpFile.setFileType("压缩包");
		}
		if(fileCatNum!=null){
			if("TP".equals(fileCatNum)){
				gpFile.setFileCategory("开题报告");
			}else if ("PA".equals(fileCatNum)) {
				gpFile.setFileCategory("论文");
			}else {
				gpFile.setFileCategory("作品");
			}
			gpFile.setFileCatNum(fileCatNum);
		}
		gpFile.setFileName(fileName);
		gpFile.setFilePath(path);
		gpFile.setUploaderId(user12.getUserid());
		gpFile.setUploaderName(user12.getUserName());
		gpFile.setReviewState("待审阅");
		gpFile.setFileState("最新");
		gpFile.setUploadTime(df.format(new Date()));
		List<GpFile> gpFilelist = gpFileDAO.findGpFileBy1(user12.getUserid(), fileCatNum, "最新");
		if(!gpFilelist.isEmpty()){

				GpFile gpFile2 = gpFilelist.get(0);
				gpFile2.setFileState("历史");
				gpFileDAO.updateGpFile(gpFile2);
		}
		gpFileDAO.addGpFile(gpFile);
		return uploadFile;
	}

/**
 * 删除文件
 */
	public void deleteFile(File uploadFile) {
		if (uploadFile.exists()) {
			String path = uploadFile.getPath();
			String fileNoS = StringUtils.substringAfterLast(path, "\\");//截取最后一个\之后的字符串
			String fileNo = StringUtils.substringBefore(fileNoS, "_");//截取第一个下划线之前的字符串
			GpFile gpFile = new GpFile();
			gpFile.setFileNo(fileNo);
			gpFileDAO.deleteGpFile(gpFile);
			boolean Eor2 = uploadFile.delete();// 删除需要在流关闭之后后者文件未被打开时才可进行
		}
	}
	
/**
 * 批量下载文件打包
 */
	public String filePcakZip(String DOWBLOADDIR,String fileNos)throws IOException{
		String downPath = null;
		String[] strs = fileNos.split(", ");
		List<GpFile> gpFilelists = new ArrayList<GpFile>();
		List<File> filelist = new ArrayList<File>();
		for (int i = 0; i < strs.length; i++) {
			List<GpFile> gpFilelist = gpFileDAO.findGpFileByFileNo(strs[i]);
			if (!gpFilelist.isEmpty()) {
				GpFile gpFile = gpFilelist.get(0);
				File file = new File(gpFile.getFilePath());
				filelist.add(file);
				String fileName = StringUtils.substringAfterLast(gpFile.getFileName(), "_");//截取字符串 （给文件名去前缀）
				Student student = (Student) studentService.findStuBystuNo(gpFile.getUploaderId()).get(0);
				fileName = student.getClassName()+"-"+gpFile.getUploaderName()+"-"+fileName;//重新给文件命名
				gpFile.setFileName(fileName);
				gpFilelists.add(gpFile);
			}
		}
		if(!filelist.isEmpty()){
			@SuppressWarnings("deprecation")
			String dir = ServletActionContext.getRequest().getRealPath(DOWBLOADDIR);//获得项目根目录上传路径
			// 或 String dir = "F://upload";//自定义上传路径
			File fileLocation = new File(dir);
			// 此处也可以在应用根目录手动建立目标上传目录
			if (!fileLocation.exists()) {
				boolean isCreated = fileLocation.mkdir();
				if (!isCreated) {
					// 目标上传目录创建失败,可做其他处理,例如抛出自定义异常等,一般应该不会出现这种情况。
					System.out.println("创建文件夹失败！");
				}
			}
			downPath = dir+"\\_【批量下载】"+StringUtils.substringBefore(gpFilelists.get(0).getFileName(), ".")+".zip";
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(new File(downPath)));//创建一个空的压缩包
			for(int i=0;i<filelist.size();i++){
				File file = filelist.get(i);
				GpFile gpFile = gpFilelists.get(i);
				InputStream in = new FileInputStream(file);
				ZipEntry en = new ZipEntry(gpFile.getFileName());//写入文件名
				en.setSize(file.length());//设置文件大小
				out.putNextEntry(en);//创建Zip内文件
				int len = 0;
				byte[] buffer = new byte[1024];
				while (-1 != (len = in.read(buffer))) {
					out.write(buffer, 0, len);
				}
				

				in.close();
			}
			
			out.close();
		}
		
		return StringUtils.substringAfterLast(downPath, "\\");
	}
	

/**
 * 读取xlsx表格,导入学生
 */
	public List<Student> addStudentXSSF(String fileURL) throws Exception {
		XSSFSheet sheet = Xsheet(fileURL);
		int LrowNum = sheet.getLastRowNum();
		List<Student> studentlist = new ArrayList<Student>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
		String time = df.format(new Date()); 
		for (int i = 1; i <= LrowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			int LcellNum = row.getLastCellNum();
			for (int j = 0; j < LcellNum; j++) {
				row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
			}
			String stuNo = row.getCell(0).getStringCellValue();
			List stu = studentService.findStuBystuNo(stuNo);
			if (stu.isEmpty()) {
				Student student = new Student();
				student.setStuNo(stuNo);
				student.setStuName(row.getCell(1).getStringCellValue());
				student.setSex(row.getCell(2).getStringCellValue());
				student.setClassNo(row.getCell(3).getStringCellValue());
				student.setClassName(row.getCell(4).getStringCellValue());
				student.setMajorNo(row.getCell(5).getStringCellValue());
				student.setMajorName(row.getCell(6).getStringCellValue());
				student.setInstituteNo(row.getCell(7).getStringCellValue());
				student.setInstituteName(row.getCell(8).getStringCellValue());
				student.setUpdateTime(time);
				studentService.addstudent(student);
				User user = new User();
				user.setUserid(stuNo);
				user.setUserName(row.getCell(1).getStringCellValue());
				user.setRole("student");
				user.setUpdateTime(time);
				userService.addUser(user);
				studentlist.add(student);
			}
		}
		return studentlist;

	}

/**
 * 读取xls表格,导入学生
 */
	public List<Student> addStudentHSSF(String fileURL) throws Exception {
		HSSFSheet sheet = Hsheet(fileURL);
		int LrowNum = sheet.getLastRowNum();
		List<Student> studentlist = new ArrayList<Student>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
		String time = df.format(new Date()); 
		for (int i = 1; i <= LrowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			int LcellNum = row.getLastCellNum();
			for (int j = 0; j < LcellNum; j++) {
				row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
			}
			String stuNo = row.getCell(0).getStringCellValue();
			List stu = studentService.findStuBystuNo(stuNo);
			if (stu.isEmpty()) {
				Student student = new Student();
				student.setStuNo(stuNo);
				student.setStuName(row.getCell(1).getStringCellValue());
				student.setSex(row.getCell(2).getStringCellValue());
				student.setClassNo(row.getCell(3).getStringCellValue());
				student.setClassName(row.getCell(4).getStringCellValue());
				student.setMajorNo(row.getCell(5).getStringCellValue());
				student.setMajorName(row.getCell(6).getStringCellValue());
				student.setInstituteNo(row.getCell(7).getStringCellValue());
				student.setInstituteName(row.getCell(8).getStringCellValue());
				student.setUpdateTime(time);
				studentService.addstudent(student);
				User user = new User();
				user.setUserid(stuNo);
				user.setUserName(row.getCell(1).getStringCellValue());
				user.setRole("student");
				user.setUpdateTime(time);
				userService.addUser(user);
				studentlist.add(student);
			}
		}
		return studentlist;
	}

/**
 *读取xlsx表格,导入导师
 */
	public List<Tutor> addTutorXSSF(String fileURL) throws Exception {
		XSSFSheet sheet = Xsheet(fileURL);
		int LrowNum = sheet.getLastRowNum();
		List<Tutor> tutorlist = new ArrayList<Tutor>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
		String time = df.format(new Date()); 
		for (int i = 1; i <= LrowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			int LcellNum = row.getLastCellNum();
			for (int j = 0; j < LcellNum; j++) {
				row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
			}
			String tutNo = row.getCell(0).getStringCellValue();
			List tut = tutorService.findTutorByNo(tutNo);
			if (tut.isEmpty()) {
				String tutName = row.getCell(1).getStringCellValue();
				Tutor tutor = new Tutor();
				tutor.setTutNo(tutNo);
				tutor.setTutName(tutName);
				tutor.setSex(row.getCell(2).getStringCellValue());
				tutor.setPosition(row.getCell(3).getStringCellValue());
				tutor.setInstituteName(row.getCell(4).getStringCellValue());
				tutor.setUpdateTime(time);
				tutorService.addTutor(tutor);
				User user = new User();
				user.setUserid(tutNo);
				user.setUserName(tutName);
				user.setRole("tutor");
				user.setUpdateTime(time);
				userService.addUser(user);
				TsControl tsControl = new TsControl();
				tsControl.setTutNo(tutNo);
				tsControl.setTutName(tutName);
				tsControl.setPermissions("T");
				tsControlService.addTsControl(tsControl);
				tutorlist.add(tutor);
			
			}
		}
		return tutorlist;

	}

/**
 * 读取xls表格,导入导师
 */
	public List<Tutor> addTutorHSSF(String fileURL) throws Exception {
		HSSFSheet sheet = Hsheet(fileURL);
		int LrowNum = sheet.getLastRowNum();
		List<Tutor> tutorlist = new ArrayList<Tutor>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
		String time = df.format(new Date()); 
		for (int i = 1; i <= LrowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			int LcellNum = row.getLastCellNum();
			for (int j = 0; j < LcellNum; j++) {
				row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
			}
			String tutNo = row.getCell(0).getStringCellValue();
			List tut = tutorService.findTutorByNo(tutNo);
			if (tut.isEmpty()) {
				String tutName = row.getCell(1).getStringCellValue();
				Tutor tutor = new Tutor();
				tutor.setTutNo(tutNo);
				tutor.setTutName(tutName);
				tutor.setSex(row.getCell(2).getStringCellValue());
				tutor.setPosition(row.getCell(3).getStringCellValue());
				tutor.setInstituteName(row.getCell(4).getStringCellValue());
				tutor.setUpdateTime(time);
				tutorService.addTutor(tutor);
				User user = new User();
				user.setUserid(tutNo);
				user.setUserName(tutName);
				user.setRole("tutor");
				user.setUpdateTime(time);
				userService.addUser(user);
				TsControl tsControl = new TsControl();
				tsControl.setTutNo(tutNo);
				tsControl.setTutName(tutName);
				tsControl.setPermissions("T");
				tsControlService.addTsControl(tsControl);
				tutorlist.add(tutor);
			}
		}
		return tutorlist;
	}

/**
 * 读取xlsx表格,导入班级
 */
	public List<Class> addClassXSSF(String fileURL) throws Exception {
		XSSFSheet sheet = Xsheet(fileURL);
		int LrowNum = sheet.getLastRowNum();
		List<Class> classlist = new ArrayList<Class>();
		for (int i = 1; i <= LrowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			int LcellNum = row.getLastCellNum();
			for (int j = 0; j < LcellNum; j++) {
				row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
			}
			String classNo = row.getCell(0).getStringCellValue();
			List cla = classService.findclass(classNo);
			if (cla.isEmpty()) {
				Class class1 = new Class();
				class1.setClassNo(row.getCell(0).getStringCellValue());
				class1.setClasstuName(row.getCell(1).getStringCellValue());
				class1.setMajorNo(row.getCell(2).getStringCellValue());
				class1.setMajorName(row.getCell(3).getStringCellValue());
				class1.setInstituteNo(row.getCell(4).getStringCellValue());
				class1.setInstituteName(row.getCell(5).getStringCellValue());
				classService.addclass(class1);
				classlist.add(class1);
			}
		}
		return classlist;

	}

/**
 *读取xls表格,导入班级
 */
	public List<Class> addClassHSSF(String fileURL) throws Exception {
		HSSFSheet sheet = Hsheet(fileURL);
		int LrowNum = sheet.getLastRowNum();
		List<Class> classlist = new ArrayList<Class>();
		for (int i = 1; i <= LrowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			int LcellNum = row.getLastCellNum();
			for (int j = 0; j < LcellNum; j++) {
				row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
			}
			String classNo = row.getCell(0).getStringCellValue();
			List cla = classService.findclass(classNo);
			if (cla.isEmpty()) {
				Class class1 = new Class();
				class1.setClassNo(row.getCell(0).getStringCellValue());
				class1.setClasstuName(row.getCell(1).getStringCellValue());
				class1.setMajorNo(row.getCell(2).getStringCellValue());
				class1.setMajorName(row.getCell(3).getStringCellValue());
				class1.setInstituteNo(row.getCell(4).getStringCellValue());
				class1.setInstituteName(row.getCell(5).getStringCellValue());
				classService.addclass(class1);
				classlist.add(class1);
			}
		}
		return classlist;
	}
	
/**
 * 读取xlsx表格,导入题目
 */
	public List<Subject> addSubjectXSSF(String fileURL) throws Exception {
		XSSFSheet sheet = Xsheet(fileURL);
		int LrowNum = sheet.getLastRowNum();
		List<Subject> subjectlist = new ArrayList<Subject>();
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");// 获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
		for (int i = 1; i <= LrowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			int LcellNum = row.getLastCellNum();
			for (int j = 0; j < LcellNum; j++) {
				row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
			}
			String tutNo = user12.getUserid();
			StringBuffer No = new StringBuffer();
			No.append(df1.format(new Date()));
			No.append(tutNo);
			No.append(i+1);
			String subNo = No.toString();
			Subject subject = new Subject();
			subject.setSubNo(subNo);
			subject.setTutNo(tutNo);
			subject.setSubjectName(row.getCell(0).getStringCellValue());
			subject.setTutName(user12.getUserName());
			subject.setUpdateTime(df.format(new Date()));
			subjectService.addSubject(subject);
			subjectlist.add(subject);
		}
		return subjectlist;

	}

/**
 * 读取xls表格,导入题目
 */
	public List<Subject> addSubjectHSSF(String fileURL) throws Exception {
		HSSFSheet sheet = Hsheet(fileURL);
		int LrowNum = sheet.getLastRowNum();
		List<Subject> subjectlist = new ArrayList<Subject>();
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");// 获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
		for (int i = 1; i <= LrowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			int LcellNum = row.getLastCellNum();
			for (int j = 0; j < LcellNum; j++) {
				row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
			}
			String tutNo = user12.getUserid();
			StringBuffer No = new StringBuffer();
			No.append(df1.format(new Date()));
			No.append(tutNo);
			No.append(i+1);
			String subNo = No.toString();
			Subject subject = new Subject();
			subject.setSubNo(subNo);
			subject.setTutNo(tutNo);
			subject.setSubjectName(row.getCell(0).getStringCellValue());
			subject.setTutName(user12.getUserName());
			subject.setUpdateTime(df.format(new Date()));
			subjectService.addSubject(subject);
			subjectlist.add(subject);
		}
		return subjectlist;
	}

/**
 * 读取xlsx表格,导入分组
 */
	@SuppressWarnings("unchecked")
	public List<Grouping> addGroupingXSSF(String fileURL) throws Exception {
		XSSFSheet sheet = Xsheet(fileURL);;
		List<Grouping> groupinglists = new ArrayList<Grouping>();
		int LrowNum = sheet.getLastRowNum();
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy");// 获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
		List<Class> classlist = classService.disfindInsClaNameMa();
		List<Answergroup> answergrouplist = new ArrayList<Answergroup>();
		for (int i = 1; i <= LrowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			int LcellNum = row.getLastCellNum();
			for (int j = 0; j < LcellNum; j++) {
				row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
			}
			//获取答辩类型
			String replyTypeName = row.getCell(0).getStringCellValue();
			String replyType = null;
			if(replyTypeName.indexOf("开题")!=-1){
				replyType = "TPR";
			}else if (replyTypeName.indexOf("毕业")!=-1) {
				replyType = "GRR";
			}
			if(replyTypeName!=null){
				String groupId = null;
				String instituteName= null;
				String majorName =null;
				int stuNumber = 0;
				//获取学生学号
				String stuNo = row.getCell(1).getStringCellValue();
				//生成编号
				String groupNo = replyType+stuNo;
				//查询是否存在该记录
				List<Grouping> groupinglist = groupingService.findGroupingByGroupNo(groupNo);
				if(groupinglist.isEmpty()){
					
					//获得组名
					String groupingsNo = row.getCell(5).getStringCellValue();
					String className = row.getCell(3).getStringCellValue();					
					//获得班级号
					String classNo = null;
					for(int j=0;j<classlist.size();j++){
						if(className.equals(classlist.get(j).getClasstuName())){
							Class class1 = classlist.get(j);
							classNo = class1.getClassNo();						
							String a = StringUtils.substringAfterLast(groupingsNo, "第");
							a =  StringUtils.substringBefore(a, "组");
							groupId = replyType+df1.format(new Date())+class1.getInstituteNo()+class1.getMajorNo()+ Integer.parseInt(a);
							boolean Tof=true;
							for(int k=0;k<answergrouplist.size();k++){
								if(groupId.equals(answergrouplist.get(k).getGroupId())){
									Tof=false;
								}
							}						
								instituteName = class1.getInstituteName();
								majorName = class1.getMajorName();
								Answergroup answergroup = new Answergroup();
								answergroup.setGroupName(groupingsNo);
								answergroup.setReplyType(replyType);
								answergroup.setInstituteName(instituteName);
								answergroup.setMajorName(majorName);
							if(Tof==true){
								answergroup.setGroupId(groupId);
								answergroup.setStuNumber("0");
								answergrouplist.add(answergroup);
							}
							for(int k=0;k<answergrouplist.size();k++){
								Answergroup answergroup2 = answergrouplist.get(k);
								if(instituteName.equals(answergroup2.getInstituteName()) && majorName.equals(answergroup2.getMajorName()) && groupingsNo.equals(answergroup2.getGroupName())){									
									int aa = Integer.parseInt(answergroup2.getStuNumber())+1;
									answergroup2.setStuNumber(Integer.toString(aa));
									answergrouplist.set(k, answergroup2);
								}
							}			
						}	
					}
					Grouping grouping = new Grouping();
					grouping.setGroupNo(groupNo);
					grouping.setReplyType(replyType);
					grouping.setReplyTypeName(replyTypeName);
					grouping.setStuNo(stuNo);
					grouping.setStuName(row.getCell(2).getStringCellValue());
					grouping.setClassNo(classNo);
					grouping.setClassName(className);
					grouping.setTutName(row.getCell(4).getStringCellValue());
					grouping.setMajorName(majorName);
					grouping.setGroupingsNo(groupingsNo);
					grouping.setOrders(row.getCell(6).getStringCellValue());
					grouping.setPlaces(row.getCell(7).getStringCellValue());
					groupingService.addGrouping(grouping);
					groupinglists.add(grouping);

					

				}				
			}
		}
		for(int k=0;k<answergrouplist.size();k++){
			Answergroup answergroup  = answergrouplist.get(k);
			List<Answergroup> answergrouplist2 = answergroupService.findAnswergroupByGroupID(answergroup.getGroupId());
			if(answergrouplist2.isEmpty()){
				answergroupService.addAnswergroup(answergroup);		
			}else{
				Answergroup answergroup2 = answergrouplist2.get(0);
				int stuNumber = Integer.parseInt(answergroup.getStuNumber())+Integer.parseInt(answergroup2.getStuNumber());
				answergroup2.setStuNumber(Integer.toString(stuNumber));
				answergroupService.updateAnswergroup(answergroup2);
				}
			
		}
		return groupinglists;
	}

/**
 * 读取xls表格,导入分组
 */
	@SuppressWarnings("unchecked")
	public List<Grouping> addGroupingHSSF(String fileURL) throws Exception {
		HSSFSheet sheet = Hsheet(fileURL);;
		List<Grouping> groupinglists = new ArrayList<Grouping>();
		int LrowNum = sheet.getLastRowNum();
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy");// 获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
		List<Class> classlist = classService.disfindInsClaNameMa();
		List<Answergroup> answergrouplist = new ArrayList<Answergroup>();
		for (int i = 1; i <= LrowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			int LcellNum = row.getLastCellNum();
			for (int j = 0; j < LcellNum; j++) {
				row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
			}
			//获取答辩类型
			String replyTypeName = row.getCell(0).getStringCellValue();
			String replyType = null;
			if(replyTypeName.indexOf("开题")!=-1){
				replyType = "TPR";
			}else if (replyTypeName.indexOf("毕业")!=-1) {
				replyType = "GRR";
			}
			if(replyTypeName!=null){
				String groupId = null;
				String instituteName= null;
				String majorName =null;
				int stuNumber = 0;
				//获取学生学号
				String stuNo = row.getCell(1).getStringCellValue();
				//生成编号
				String groupNo = replyType+stuNo;
				//查询是否存在该记录
				List<Grouping> groupinglist = groupingService.findGroupingByGroupNo(groupNo);
				if(groupinglist.isEmpty()){
					
					//获得组名
					String groupingsNo = row.getCell(5).getStringCellValue();
					String className = row.getCell(3).getStringCellValue();					
					//获得班级号
					String classNo = null;
					for(int j=0;j<classlist.size();j++){
						if(className.equals(classlist.get(j).getClasstuName())){
							Class class1 = classlist.get(j);
							classNo = class1.getClassNo();						
							String a = StringUtils.substringAfterLast(groupingsNo, "第");
							a =  StringUtils.substringBefore(a, "组");
							groupId = replyType+df1.format(new Date())+class1.getInstituteNo()+class1.getMajorNo()+ Integer.parseInt(a);
							boolean Tof=true;
							for(int k=0;k<answergrouplist.size();k++){
								if(groupId.equals(answergrouplist.get(k).getGroupId())){
									Tof=false;
								}
							}						
								instituteName = class1.getInstituteName();
								majorName = class1.getMajorName();
								Answergroup answergroup = new Answergroup();
								answergroup.setGroupName(groupingsNo);
								answergroup.setInstituteName(instituteName);
								answergroup.setReplyType(replyType);
								answergroup.setMajorName(majorName);
							if(Tof==true){
								answergroup.setGroupId(groupId);
								answergroup.setStuNumber("0");
								answergrouplist.add(answergroup);
							}
							for(int k=0;k<answergrouplist.size();k++){
								Answergroup answergroup2 = answergrouplist.get(k);
								if(instituteName.equals(answergroup2.getInstituteName()) && majorName.equals(answergroup2.getMajorName()) && groupingsNo.equals(answergroup2.getGroupName())){									
									int aa = Integer.parseInt(answergroup2.getStuNumber())+1;
									answergroup2.setStuNumber(Integer.toString(aa));
									answergrouplist.set(k, answergroup2);
								}
							}			
						}	
					}
					Grouping grouping = new Grouping();
					grouping.setGroupNo(groupNo);
					grouping.setReplyType(replyType);
					grouping.setReplyTypeName(replyTypeName);
					grouping.setStuNo(stuNo);
					grouping.setStuName(row.getCell(2).getStringCellValue());
					grouping.setClassNo(classNo);
					grouping.setClassName(className);
					grouping.setTutName(row.getCell(4).getStringCellValue());
					grouping.setMajorName(majorName);
					grouping.setGroupingsNo(groupingsNo);
					grouping.setOrders(row.getCell(6).getStringCellValue());
					grouping.setPlaces(row.getCell(7).getStringCellValue());
					groupingService.addGrouping(grouping);
					groupinglists.add(grouping);

					

				}				
			}
		}
		for(int k=0;k<answergrouplist.size();k++){
			Answergroup answergroup  = answergrouplist.get(k);
			List<Answergroup> answergrouplist2 = answergroupService.findAnswergroupByGroupID(answergroup.getGroupId());
			if(answergrouplist2.isEmpty()){
				answergroupService.addAnswergroup(answergroup);		
			}else{
				Answergroup answergroup2 = answergrouplist2.get(0);
				int stuNumber = Integer.parseInt(answergroup.getStuNumber())+Integer.parseInt(answergroup2.getStuNumber());
				answergroup2.setStuNumber(Integer.toString(stuNumber));
				answergroupService.updateAnswergroup(answergroup2);
				}
			
		}
		return groupinglists;
	}
	
/**
 * 读取xlsx表格,导入成绩
 */
		@SuppressWarnings("unchecked")
		public List<GpResults> addGpResultsXSSF(String fileURL) throws Exception {
			XSSFSheet sheet = Xsheet(fileURL);
			int LrowNum = sheet.getLastRowNum();
			List<GpResults> gpResultlists = new ArrayList<GpResults>();
			for (int i = 1; i <= LrowNum; i++) {
				XSSFRow row = sheet.getRow(i);
				int LcellNum = row.getLastCellNum();
				for (int j = 0; j < LcellNum; j++) {
					row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
				}
				String reTypeName = row.getCell(0).getStringCellValue();
				String reType = null;
				if(reTypeName.indexOf("开题")!=-1){
					reType = "TPG";
				}else if (reTypeName.indexOf("论文")!=-1) {
					reType = "PAG";
				}else if (reTypeName.indexOf("设计")!=-1 || reTypeName.indexOf("作品")!=-1) {
					reType = "WOG";
				}
				String stuNo = row.getCell(1).getStringCellValue();
				//生成编号
				String resultsNo = reType + stuNo;
				 List<GpResults> gpResultlist = gpResultsService.findGpResults3(reType, stuNo);
				   if(!gpResultlist.isEmpty()){
					   GpResults gpResults1 = gpResultlist.get(gpResultlist.size()-1);
					   String gNo = gpResults1.getResultsNo();
					   gNo = StringUtils.substringAfter(gNo, reType+stuNo);
					   int a = Integer.parseInt(gNo);
					   String relastNo = Integer.toString(a+1);
					   resultsNo=resultsNo+relastNo;
				   }else {
					   resultsNo=resultsNo+1;
				}
				//判断数据是否已存在
				List<Student> studentlist = studentService.findStuBystuNo(stuNo);
				List<GpResults> gpResultlsit = gpResultsService.findGpResultsByResultsNo(resultsNo); 
				if(gpResultlsit.isEmpty() && !studentlist.isEmpty()){
					GpResults gpResults = new GpResults();
					Student student = studentlist.get(0);
					gpResults.setTutNo(student.getTutNo());
					gpResults.setResultsNo(resultsNo);
					gpResults.setStuNo(stuNo);			
					gpResults.setReType(reType);
					gpResults.setReTypeName(reTypeName);
					gpResults.setStuName(row.getCell(2).getStringCellValue());
					gpResults.setClassName(row.getCell(3).getStringCellValue());
					gpResults.setTutName(row.getCell(4).getStringCellValue());
					gpResults.setMajorName(row.getCell(5).getStringCellValue());
					gpResults.setGroupingsNo(row.getCell(6).getStringCellValue());
					gpResults.setOrders(row.getCell(7).getStringCellValue());
					gpResults.setSubject(row.getCell(8).getStringCellValue());
					gpResults.setResults(row.getCell(9).getStringCellValue());
					gpResults.setComments(row.getCell(10).getStringCellValue());
					gpResultsService.addGpResults(gpResults);
					gpResultlists.add(gpResults);
				}else if(!studentlist.isEmpty()){
					GpResults gpResults = gpResultlsit.get(0);
					Student student = studentlist.get(0);
					gpResults.setTutNo(student.getTutNo());
					gpResults.setGroupingsNo(row.getCell(6).getStringCellValue());
					gpResults.setOrders(row.getCell(7).getStringCellValue());
					gpResults.setSubject(row.getCell(8).getStringCellValue());
					gpResults.setResults(row.getCell(9).getStringCellValue());
					gpResults.setComments(row.getCell(10).getStringCellValue());
					gpResultsService.updataGpResults(gpResults);
					gpResultlists.add(gpResults);
				}
			}
			return gpResultlists;

		}

/**
 * 读取xls表格,导入成绩
 */
		@SuppressWarnings("unchecked")
		public List<GpResults> addGpResultsHSSF(String fileURL) throws Exception {
			HSSFSheet sheet = Hsheet(fileURL);
			int LrowNum = sheet.getLastRowNum();
			List<GpResults> gpResultlists = new ArrayList<GpResults>();
			for (int i = 1; i <= LrowNum; i++) {
				HSSFRow row = sheet.getRow(i);
				int LcellNum = row.getLastCellNum();
				for (int j = 0; j < LcellNum; j++) {
					row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
				}
				String reTypeName = row.getCell(0).getStringCellValue();
				String reType = null;
				if(reTypeName.indexOf("开题")!=-1){
					reType = "TPG";
				}else if (reTypeName.indexOf("论文")!=-1) {
					reType = "PAG";
				}else if (reTypeName.indexOf("设计")!=-1 || reTypeName.indexOf("作品")!=-1) {
					reType = "WOG";
				}
				String stuNo = row.getCell(1).getStringCellValue();
				//生成编号
				String resultsNo = reType + stuNo;
				 List<GpResults> gpResultlist = gpResultsService.findGpResults3(reType, stuNo);
				   if(!gpResultlist.isEmpty()){
					   GpResults gpResults1 = gpResultlist.get(gpResultlist.size()-1);
					   String gNo = gpResults1.getResultsNo();
					   gNo = StringUtils.substringAfter(gNo, reType+stuNo);
					   int a = Integer.parseInt(gNo);
					   String relastNo = Integer.toString(a+1);
					   resultsNo=resultsNo+relastNo;
				   }else {
					   resultsNo=resultsNo+1;
				}
				 //判断数据是否已存在
					List<Student> studentlist = studentService.findStuBystuNo(stuNo);
					List<GpResults> gpResultlsit = gpResultsService.findGpResultsByResultsNo(resultsNo); 
					if(gpResultlsit.isEmpty() && !studentlist.isEmpty()){
						GpResults gpResults = new GpResults();
						Student student = studentlist.get(0);
						gpResults.setTutNo(student.getTutNo());
						gpResults.setResultsNo(resultsNo);
						gpResults.setStuNo(stuNo);			
						gpResults.setReType(reType);
						gpResults.setReTypeName(reTypeName);
						gpResults.setStuName(row.getCell(2).getStringCellValue());
						gpResults.setClassName(row.getCell(3).getStringCellValue());
						gpResults.setTutName(row.getCell(4).getStringCellValue());
						gpResults.setMajorName(row.getCell(5).getStringCellValue());
						gpResults.setGroupingsNo(row.getCell(6).getStringCellValue());
						gpResults.setOrders(row.getCell(7).getStringCellValue());
						gpResults.setSubject(row.getCell(8).getStringCellValue());
						gpResults.setResults(row.getCell(9).getStringCellValue());
						gpResults.setComments(row.getCell(10).getStringCellValue());
						gpResultsService.addGpResults(gpResults);
						gpResultlists.add(gpResults);
					}else if(!studentlist.isEmpty()){
						GpResults gpResults = gpResultlsit.get(0);
						Student student = studentlist.get(0);
						gpResults.setTutNo(student.getTutNo());
						gpResults.setGroupingsNo(row.getCell(6).getStringCellValue());
						gpResults.setOrders(row.getCell(7).getStringCellValue());
						gpResults.setSubject(row.getCell(8).getStringCellValue());
						gpResults.setResults(row.getCell(9).getStringCellValue());
						gpResults.setComments(row.getCell(10).getStringCellValue());
						gpResultsService.updataGpResults(gpResults);
						gpResultlists.add(gpResults);
					}
			}
			return gpResultlists;

		}

	
	
/**
 * 根据上传人ID、文件类别编号查询
 */
	public List<GpFile> findGpFileByUploaderId(String uploaderId,String fileCatNum) {
		List<GpFile> gpFilelists = new ArrayList<GpFile>();
		List<GpFile> gpFilelist = gpFileDAO.findGpFileByUploaderId(uploaderId,fileCatNum);
		//文件名去前缀处理
		for(int i=0;i<gpFilelist.size();i++){
			GpFile gpFile = gpFilelist.get(i);
			String fileName = StringUtils.substringAfterLast(gpFile.getFileName(), "_");//截取最后一个下划线之后的字符串
			gpFile.setFileName(fileName);
			gpFilelists.add(gpFile);
		}	
		return gpFilelists;
	}
	
	
/**
 * 根据文件编号查询
 */
	public List<GpFile> findGpFileByFileNo(String fileNo){
		return gpFileDAO.findGpFileByFileNo(fileNo);
	}
	
/**
 * 查询学生毕设的某类别的资料
 */
	@SuppressWarnings("unchecked")
	public List<GpFile> toStudentGPDatumSurvey(User user12,String fileCatNum) {
		List<Student> studentlist = studentService.findStuByTutNo(user12.getUserid());
		List<GpFile> gpFilelsits = new ArrayList<GpFile>();
		for(int i=0;i<studentlist.size();i++){
			Student student = studentlist.get(i);
			List<GpFile> gpFilelist = gpFileDAO.findGpFileByUploaderId(student.getStuNo(), fileCatNum);
			if(!gpFilelist.isEmpty()){
			GpFile gpFile = gpFilelist.get(gpFilelist.size()-1);
			String fileName = StringUtils.substringAfterLast(gpFile.getFileName(), "_");//截取最后一个下划线之后的字符串
			gpFile.setFileName(fileName);
			gpFilelsits.add(gpFile);
			}
		}
		return gpFilelsits;
	}
	
/**
 * 根据导师ID、学生上传的文件类型编号查询
 */
	@SuppressWarnings("unchecked")
	public List<GpFile> findGpFileByTutNo(String tutNo,String fileCatNum){
		List<Student> studentlist = studentService.findStuByTutNo(tutNo);
		List<GpFile> gpFilelsits = new ArrayList<GpFile>();
		for(int i=0;i<studentlist.size();i++){
			Student student = studentlist.get(i);
			List<GpFile> gpFilelist = gpFileDAO.findGpFileByUploaderId(student.getStuNo(), fileCatNum);
			if(!gpFilelist.isEmpty()){
				for(int j=0;j<gpFilelist.size();j++){
					GpFile gpFile = gpFilelist.get(j);
					String fileName = StringUtils.substringAfterLast(gpFile.getFileName(), "_");//截取最后一个下划线之后的字符串
					gpFile.setFileName(fileName);
					gpFilelsits.add(gpFile);
				}
			}
		}
		return gpFilelsits;	
	}
	
	
/**
 * 根据文件类别编号查询
 */
	public List<GpFile> findGpFileByFileCatNum(String fileCatNum) {
		List<GpFile> gpFilelist = gpFileDAO.findGpFileByFileCatNum(fileCatNum);
		List<GpFile> gpFilelsits = new ArrayList<GpFile>();
		if(!gpFilelist.isEmpty()){
			for(int j=0;j<gpFilelist.size();j++){
				GpFile gpFile = gpFilelist.get(j);
				String fileName = StringUtils.substringAfterLast(gpFile.getFileName(), "_");//截取最后一个下划线之后的字符串
				gpFile.setFileName(fileName);
				gpFilelsits.add(gpFile);
			}
		}
		return gpFilelsits;
	}
	
/**
 * 修改文件审阅状态
 */
	public String reviewFile(GpFile gpFile){
		String reviewState = null;
		if("agree".equals(gpFile.getReviewState())){
			reviewState ="同意";
		}else{
			reviewState ="不同意";
		}
		List<GpFile> gpFilelist = gpFileDAO.findGpFileByFileNo(gpFile.getFileNo());
		if(!gpFilelist.isEmpty()){
			gpFile = gpFilelist.get(0);	
			gpFile.setReviewState(reviewState);
			gpFileDAO.updateGpFile(gpFile);
			return "success";
		}else{
		return "error";
		}
		
	}
	
/**
 * 根据上传者ID、审阅状态、文件类型编号 查询
 */
	public List<GpFile> findGpFile1(String uploaderId,String reviewState,String fileCatNum){
		return gpFileDAO.findGpFile1(uploaderId, reviewState, fileCatNum);
	}	

	
	
/**
 * 
 * @param fileURL
 * @return
 * @throws Exception
 */
	public HSSFSheet Hsheet(String fileURL) throws Exception {
		FileInputStream inputStream = new FileInputStream(fileURL);
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = workbook.getSheetAt(0);
		inputStream.close();
		return sheet;
	}

	public XSSFSheet Xsheet(String fileURL) throws Exception {
		FileInputStream inputStream = new FileInputStream(fileURL);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);
		inputStream.close();
		return sheet;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ITutorService getTutorService() {
		return tutorService;
	}

	public void setTutorService(ITutorService tutorService) {
		this.tutorService = tutorService;
	}

	public IClassService getClassService() {
		return classService;
	}

	public void setClassService(IClassService classService) {
		this.classService = classService;
	}

	public ISubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public ITsControlService getTsControlService() {
		return tsControlService;
	}

	public void setTsControlService(ITsControlService tsControlService) {
		this.tsControlService = tsControlService;
	}

	public IGpFileDAO getGpFileDAO() {
		return gpFileDAO;
	}

	public void setGpFileDAO(IGpFileDAO gpFileDAO) {
		this.gpFileDAO = gpFileDAO;
	}

	public IGroupingService getGroupingService() {
		return groupingService;
	}

	public void setGroupingService(IGroupingService groupingService) {
		this.groupingService = groupingService;
	}

	public IAnswergroupService getAnswergroupService() {
		return answergroupService;
	}

	public void setAnswergroupService(IAnswergroupService answergroupService) {
		this.answergroupService = answergroupService;
	}

	public IGpResultsService getGpResultsService() {
		return gpResultsService;
	}

	public void setGpResultsService(IGpResultsService gpResultsService) {
		this.gpResultsService = gpResultsService;
	}

}
