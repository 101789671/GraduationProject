package cn.gpms.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.gpms.dao.IApplyDAO;
import cn.gpms.form.StudentForm;
import cn.gpms.service.IApplyService;
import cn.gpms.service.IClassService;
import cn.gpms.service.IStudentService;
import cn.gpms.service.ITutorService;
import cn.gpms.vo.Apply;
import cn.gpms.vo.Student;
import cn.gpms.vo.Tutor;
import cn.gpms.vo.User;

public class ApplyService implements IApplyService {
	
	private StudentForm studentForm = new StudentForm();

	protected IApplyDAO applyDAO;
	
	protected ITutorService tutorService;
	protected IStudentService studentService;
	protected IClassService classService;
	
/**
 * 根据apID查询申请信息
 */
	public List findApplyByApID(String apId) {
		return applyDAO.findApplyByApID(apId);
	}
	
/**
 * 根据申请类型、状态查询 所有申请（如：分配申请）
 */
	public List findApply4(String typeNumber, String apState) {
		return applyDAO.findApply4(typeNumber, apState);
	}
	
/**
 * 根据申请人ID、申请类型编号查询申请信息
 */
	public List findApply5(String applicantID, String typeNumber) {
		return applyDAO.findApply5(applicantID, typeNumber);
	}
	
/**
 * 根据被申请人ID、申请类型编号查询申请信息
 */
	public List findApply6(String respondentId, String typeNumber) {
		return applyDAO.findApply6(respondentId, typeNumber);
	}

/**
 * 根据apID删除申请
 */
	public void deleteApply(Apply apply) {
		applyDAO.deleteApply(apply);
	}

/**
 * 根据被申请人ID查询所有状态的申请
 */
	public List findApplyByRID(String respondentId) {
		return applyDAO.findApplyByRID(respondentId);
	}
	
/**
 * 根据申请类型编号 查询申请
 */
	public List findApplyByNum(String typeNumber) {
		return applyDAO.findApplyByNum(typeNumber);
	}
	
/**
 * 添加申请
 */
	public void addApply(User user ,Tutor tutor ,Apply apply) {
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");// 获取当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
		//生成apply编号 ，写入编号
		StringBuffer No = new StringBuffer();
		if (apply.getRespondentRole() == "admin") {
			No.append("SA");
		} else {
			No.append("ST");
		}
		No.append(user.getUserid());
		if ("admin".equals(apply.getRespondentRole())) {
			No.append("10000");
			apply.setRespondent("admin") ;
		} else {
			No.append(apply.getRespondentId());
		}
		No.append(df1.format(new Date()));
		String apID = No.toString();
		apply.setApId(apID);
		//写入申请类型、申请内容
		if ("CT".equals(apply.getTypeNumber())) {
			apply.setApType("选导");
			apply.setApContent("自主申请导师");
		} else if ("CD".equals(apply.getTypeNumber())) {
			apply.setApType("选导");
			apply.setApContent("申请分配");
		} else {
			apply.setApType("选题");
		}
		apply.setApplicant(user.getUserName());
		apply.setApplicantId(user.getUserid());
		if(tutor.getTutName()==null){
			apply.setRespondent("管理员");
			apply.setRespondentId("--");
			apply.setRespondentRole("admin");
		}else{
		    apply.setRespondent(tutor.getTutName());
		}
		apply.setApState("待审核");
		apply.setApTime(df.format(new Date()));
		applyDAO.addApply(apply);

	}

/**
 * 根据申请人ID查询某一类型编号、某一状态的申请
 */
	public List findApplyByAID(String applicantID, String typeNumber) {
		String apType = null;
		if ("CT".equals(typeNumber) || "CD".equals(typeNumber)) {
			apType = "选导";
		} else if ("CS".equals(typeNumber)) {
			apType = "选题";
		}
		return applyDAO.findApplyByAID(applicantID, apType);
	}

/**
 * 根据申请人、被申请、 申请类型编号查询记录
 */
	public List findApply3(String applicantId, String respondentId,String typeNumber) {
		List applylist = new ArrayList<Apply>();
		String  apType = null;
		if ("CT".equals(typeNumber) || "CD".equals(typeNumber)) {
			apType = "选导";
		} else if ("CS".equals(typeNumber)) {
			apType = "选题";
		}
		if("CT".equals(typeNumber)){
			applylist = applyDAO.findApply3(applicantId, respondentId, apType);
		}else if("CD".equals(typeNumber)) {
			applylist = applyDAO.findApply3_1(applicantId, typeNumber);
		}
		return applylist;
	}


/**
 * 根据被申请人ID查询某一类型、某一状态的申请
 */
	public List findApply2(String respondentId, String apType,
			String apState) {
		if ("CT".equals(apType) || "CD".equals(apType)) {
			apType = "选导";
		} else if ("CS".equals(apType)) {
			apType = "选题";
		}
		return applyDAO.findApply2(respondentId, apType, apState);
	}
	
/**	
 * 根据申请人、申请类型编号、申请内容查询申请
 */
	public List findApply1(String applicantId, String typeNumber,
			String apContent) {
		return applyDAO.findApply1(applicantId, typeNumber, apContent);
	}


/**
 * 根据apID修改申请状态
 */
	public void updtateApType(String apId, String apState) {
		if ("agree".equals(apState)) {
			apState = "同意";
		} else if ("disagree".equals(apState)) {
			apState = "不同意";
		}
		Apply apply = (Apply) applyDAO.findApplyByApID(apId).get(0);
		apply.setApState(apState);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
		apply.setHandleTime( df.format(new Date()));
		applyDAO.updtateApply(apply);
	}
	
/**
 * 根据被申请人ID、某一类型、某一状态修改申请的状态
 */
	public void updateApType2(String applicantId ,String typeNumber,String newapState,String oldapState){
		String apType = null;
		if ("CT".equals(typeNumber) || "CD".equals(typeNumber)) {
			apType = "选导";
		} else if ("CS".equals(typeNumber)) {
			apType = "选题";
		}	
		List applyAlist = applyDAO.findApplyByAID(applicantId, apType);
		for(int i=0;i<applyAlist.size();i++){
			Apply apply = new Apply();
			apply= (Apply) applyAlist.get(i);
			if(oldapState.equals(apply.getApState()) && apType.equals(apply.getApType())){
				apply.setApState(newapState);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 获取当前系统时间
				apply.setHandleTime(df.format(new Date()));
				applyDAO.updtateApply(apply);
			}
		}
	
	}

/**
 * 单个-随机(自动)分配处理
 * @return 
 */
	public List<Student> randomAllot(String stuNo) {
		int MaxNumber = 12;
		Random ra = new Random();
		// 获取学生所在学院
		Student student = (Student) studentService.findStuBystuNo(stuNo).get(0);
		String stuIns = student.getInstituteName();
		String stuInsback = stuIns;
		// 获取所有导师
		List<Tutor> tutAlllist = tutorService.findTuAll();
		// 获取所有学院
		List<String> InsAll = classService.disfindclassAll();
		// 定义一个用于存储某一学院的所有导师的集合
		List<Tutor> oneInsTutlist = new ArrayList<Tutor>();
		//定义一个用于返回的学生集合
		List<Student> studentlist = new ArrayList<Student>(); 
		if(student.getTutNo()==null||"".equals(student.getTutNo())){
		w1: while (InsAll.size() > 0) {
			// 将学生所在学院或者随机取得的学院 的所有导师分离出来，并存放在oneInsTutlist中
			for (int i = 0; i < tutAlllist.size(); i++) {
				if (stuIns.equals(tutAlllist.get(i).getInstituteName())) {
					oneInsTutlist.add(tutAlllist.get(i));
				}
			}
			w2: while (oneInsTutlist.size() > 0) {
				// 随机获取一名导师
				int Tutint = ra.nextInt(oneInsTutlist.size() + 0);// 获取随机数
				Tutor tutor = oneInsTutlist.get(Tutint);
				// 判断导师所带人数是否大于MaxNumber
				int TutNumber = -1;
				if (tutor != null) {
					TutNumber = studentService.findStuByTutNoI(tutor.getTutNo());
					if (TutNumber > -1 && TutNumber < MaxNumber) {
						// 执行分配
						allotApplys(stuNo, tutor.getTutName());
						studentlist.add((Student) studentService.findStuBystuNo(stuNo).get(0));
						// 退出w1循环
						break w1;
					} else {
						// 将该导师从oneInsTutlist中删除
						oneInsTutlist.remove(Tutint);
					}
				}
			}
			//从InsAll中移除学院
			InsAll.remove(stuIns);
			//随机获取学院
			int InsInt = ra.nextInt(InsAll.size() + 0);
			stuIns = InsAll.get(InsInt);
			//清空oneInsTutlist
			oneInsTutlist.clear();
		}}
		return studentlist;
	}

	
	
	
/**
 * 申请处理 主 方法
 */
	public void allotApplys(String stuNo,String tutName){
		Apply apply = (Apply) findApply5(stuNo, "CD").get(0);//获取申请分配的申请信息
		updtateApType(apply.getApId(), "agree");//修改当前申请状态
		updateApType2(stuNo, "CT","已失效", "待审核");//修改其他申请状态  //CT CD效果相同
		Tutor tutor =(Tutor) tutorService.findTuByName(tutName).get(0);//获取导师
		studentService.updateStuTut(stuNo, tutor.getTutNo(), tutName);//更新学生信息
	}

	public IApplyDAO getApplyDAO() {
		return applyDAO;
	}

	public void setApplyDAO(IApplyDAO applyDAO) {
		this.applyDAO = applyDAO;
	}

	public ITutorService getTutorService() {
		return tutorService;
	}

	public void setTutorService(ITutorService tutorService) {
		this.tutorService = tutorService;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public IClassService getClassService() {
		return classService;
	}

	public void setClassService(IClassService classService) {
		this.classService = classService;
	}

	public StudentForm getStudentForm() {
		return studentForm;
	}

	public void setStudentForm(StudentForm studentForm) {
		this.studentForm = studentForm;
	}
}
