package cn.gpms.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.ResponseBody;

import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONArray;

import cn.gpms.form.ClassForm;
import cn.gpms.form.StudentForm;
import cn.gpms.form.TuotrApply;
import cn.gpms.service.IApplyService;
import cn.gpms.service.IClassService;
import cn.gpms.service.IGpFileService;
import cn.gpms.service.IGpResultsService;
import cn.gpms.service.IGroupingService;
import cn.gpms.service.INodeService;
import cn.gpms.service.INoticeService;
import cn.gpms.service.IProgressService;
import cn.gpms.service.IStudentService;
import cn.gpms.service.ISubjectService;
import cn.gpms.service.ITsControlService;
import cn.gpms.service.ITutorService;
import cn.gpms.service.IUserService;
import cn.gpms.vo.Apply;
import cn.gpms.vo.Class;
import cn.gpms.vo.GpFile;
import cn.gpms.vo.GpResults;
import cn.gpms.vo.Grouping;
import cn.gpms.vo.Node;
import cn.gpms.vo.Notice;
import cn.gpms.vo.Progress;
import cn.gpms.vo.Student;
import cn.gpms.vo.Subject;
import cn.gpms.vo.TsControl;
import cn.gpms.vo.Tutor;
import cn.gpms.vo.User;
public class StudentAction {
	
	private Student student;
	private Apply apply;
	private Subject subject;
	private GpFile gpFile;
	private Grouping grouping;
	private GpResults gpResults;
	private GpResults gpResults1;
	private Progress progress;
	private Node node;
	private Notice notice;
	
	protected IStudentService studentService;
	protected IClassService classService;
	protected IUserService userService;
	protected ITutorService tutorService;
	protected IApplyService applyService;
	protected ITsControlService tsControlService;
	protected ISubjectService subjectService;
	protected IGpFileService gpFileService;
	protected IGroupingService groupingService;
	protected IGpResultsService gpResultsService;
	protected IProgressService progressService;
	protected INodeService nodeService;
	protected INoticeService noticeService;

	private List<Student> studentList;
	private List<Class> classlist;
	private List<Class> classList;
	private List<Class> classList1;
	private List<Tutor> tutorList;
	private List<Tutor> tutorList2;
	private List<Apply> applyList;
	private List<TuotrApply> tutorapplyList;
	private List<Subject> subjectList;
	private List<TsControl> tsControlList;
	private List<GpFile> gpFileList;
	private List<Grouping> groupingList;
	private List<Grouping> JSgroupingList;
	private List<GpResults> gpResultsList;
	private List<GpResults> JSgpResultsList;
	private List<Progress> progressList;
	private List<Node> nodeList;
	private List<Notice> noticeList;
	
	private StudentForm studentForm = new StudentForm();
	private ClassForm classForm = new ClassForm();
/**
 * ���ѧ��
 * @return
 */
	public String addstudent(){
		String userRole = ServletActionContext.getRequest().getParameter("role");
		
		List<Student> students = new ArrayList<Student>(20);//����һ�����ڽ���DAO��student��list
		
		List<String> stuNolist = new ArrayList<String>();//����һ�����ڽ���jsp������stuNo
		List<String> stuNamelist = new ArrayList<String>();//����һ�����ڽ���jsp������stuName
		List<String> sexlist = new ArrayList<String>();//����һ�����ڽ���jsp������sex
		List<String> inslist = new ArrayList<String>();//����һ�����ڽ���jsp������ѧԺ��
		List<String> claNamelist = new ArrayList<String>();//����һ�����ڽ���jsp������className
		//�ָ�ѧ��
		StringTokenizer stuNos = new StringTokenizer(studentForm.getStuNo(), ", ");
		while (stuNos.hasMoreTokens()) {
			stuNolist.add(stuNos.nextToken());
		}
		//�ָ�����
		StringTokenizer stuNames = new StringTokenizer(studentForm.getStuName(), ", ");
		while (stuNames.hasMoreTokens()) {
			stuNamelist.add(stuNames.nextToken());
		}
		//�ָ��Ա�
		StringTokenizer sexs = new StringTokenizer(studentForm.getSex(), ", ");
		while (sexs.hasMoreTokens()) {
			sexlist.add(sexs.nextToken());
		}
		// �ָ�ѧԺ
		StringTokenizer inss = new StringTokenizer(
				classForm.getInstituteName(), ", ");
		while (inss.hasMoreTokens()) {
			inslist.add(inss.nextToken());
		}
		// �ָ�༶
		StringTokenizer claNames = new StringTokenizer(
				classForm.getClasstuName(), ", ");
		while (claNames.hasMoreTokens()) {
			claNamelist.add(claNames.nextToken());
		}
		List<Class> classAlllist = classService.findclassAll();
		int a = 0, b = 0;
		for (int i = 0, j = 0; i < stuNolist.size(); i++) {
			Class myclass = new Class();
			for (int k = 0; k < classAlllist.size(); k++) {
				if (claNamelist.get(i).equals(classAlllist.get(k).getClasstuName())&& inslist.get(i).equals(classAlllist.get(k).getInstituteName())) {
					myclass = classAlllist.get(k);// ��ȡ�༶��Ϣ,�˴�Ŀ�ģ���ȡ�༶���
				}
			}
			List userlist = userService.findUserById(stuNolist.get(i));// ��ȡ�û���Ϣ �˴�Ŀ�ģ��ж����û��Ƿ��Ѵ���
			if (myclass.getClassNo() != null && userlist.isEmpty()) {
				User user = new User();
				user.setUserid(stuNolist.get(i));
				user.setUserName(stuNamelist.get(i));
				user.setRole(userRole);
				Student student = new Student();
				student.setStuNo(stuNolist.get(i));
				student.setStuName(stuNamelist.get(i));
				student.setSex(sexlist.get(i));
				student.setClassNo(myclass.getClassNo());
				student.setClassName(myclass.getClasstuName());
				student.setInstituteName(myclass.getInstituteName());
				studentService.addstudent(student);
				userService.addUser(user);
				if (student.getClassNo() == null || student.getStuNo() == null) {
					b = b + 1;
				} else {
					students.add(j, student);
					a = a + 1;
					j++;
				}
			}
		}
		ActionContext.getContext().put("message",
				"�ɹ�����  " + a + "  �����ݣ�δ�ɹ�  " + b + "  ����");
		studentList = students;
		return "relistStu";
	}

/**
 * �޸�ѧ����Ϣ
 */
	public String updateStu(){
		Class myclass = (Class) classService.findClassByName(studentForm.getClassName(),studentForm.getInstituteName()).get(0);
		List tutlist = tutorService.findTuByName(studentForm.getTutName());
		Tutor tutor = new Tutor();
		if(!tutlist.isEmpty()){
		tutor = (Tutor) tutlist.get(0);}
		studentService.updateStuAll(myclass.getClassNo(), tutor.getTutNo(), studentForm);
		studentList = studentService.findStuAll();
		return "listStu";
	}
/**	
 * ��ѯ����ѧ��
 */
	public String findStuAll(){
		if(studentList!=null){
			studentList.clear();
		}
		studentList = studentService.findStuAll();
		return "listStu";
	}
	
/**
 * ɾ������ѧ��
 */
	public String delStudent(){
		if(student.getStuNo()!=null){
			studentService.deleteStu(student);
			User user = new User();
			user.setUserid(student.getStuNo());
			userService.delUser(user);
			ActionContext.getContext().put("message", "ɾ���ɹ���");
			}else{
				ActionContext.getContext().put("message", "ɾ��ʧ�ܣ�");
			}
			findStuAll();
		return "listStu";
	}
	
/**
 * ɾ�����ѧ��
 */
	public String batchDelStu(){
		String items = ServletActionContext.getRequest().getParameter("delitems"); 
		String Ero = "false";
		if (items != null) {
			String[] strs = items.split(",");
			for(int i=0;i<strs.length;i++){
				Student student = new Student();
				student.setStuNo(strs[i]);
				studentService.deleteStu(student);
				User user = new User();
				user.setUserid(strs[i]);
				userService.delUser(user);
				}
			ActionContext.getContext().put("message", "����ɾ���ɹ���");
		} else {
			ActionContext.getContext().put("message", "����ɾ��ʧ�ܣ�");
		}
		
		return "listStu";
	}
	
/**
 * �ύѡ������
 */
	public String submitCT() {
		String Eor = null;
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		
		Student student = (Student) studentService.findStuBystuNo(user12.getUserid()).get(0);
		if ((student.getTutNo() == null && student.getTutName() == null)||("".equals(student.getTutNo())&&"".equals(student.getTutName()))) {
			List AList = applyService.findApply3(user12.getUserid(),apply.getRespondentId(), apply.getTypeNumber());
			if (AList.size() == 0) {
				List tutlist = tutorService.findTutorByNo(apply.getRespondentId());
				Tutor tutor = new Tutor();
				if(!tutlist.isEmpty()){
					tutor = (Tutor) tutlist.get(0);
				}
				applyService.addApply(user12,tutor,apply);
				findCt_children(user12.getUserid(),"CT");
				Eor = "CTlist";
			} else {
				Eor = "toChoiceTutor";
				ActionContext.getContext().put("message", "�Ѿ�������ͬ�����ˣ���Ҫ�ظ��ύ��");
			}
		} else {
			Eor = "toChoiceTutor";
			ActionContext.getContext().put("message", "�Ѵ��ڵ�ʦ��");
		}
		apply = new Apply();//�� apply Ϊ��
		return Eor;
	}
	
/**
 * �ύѡ������
 */
	public String submintCS(){
		String Eor = null;
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");		
		Student student = (Student) studentService.findStuBystuNo(user12.getUserid()).get(0);
		List AList = new ArrayList();
		Apply apply1 = new Apply();
		if(apply==null||apply.getApContent()==null){
			subject = subjectService.findSubjectByNo(subject.getSubNo()).get(0);
			AList= applyService.findApply1(user12.getUserid(), "CS", subject.getSubjectName());
			apply1.setApContent(subject.getSubjectName());
		}else{
			AList= applyService.findApply1(user12.getUserid(), "CS", apply.getApContent());
			apply1.setApContent(apply.getApContent());
		}
		if (student.getSubject() == null||"".equals(student.getSubject())) {					
			if (AList.size() == 0) {
				Tutor tutor = new Tutor();		
				tutor.setTutName(student.getTutName());
				apply1.setRespondentId(student.getTutNo());
				apply1.setRespondentRole("tutor");
				apply1.setTypeNumber("CS");
				applyService.addApply(user12,tutor,apply1);
				findCt_children(user12.getUserid(),"CS");
				Eor = "CSlist";
			} else {
				Eor = "toChoiceSubject";
				ActionContext.getContext().put("message", "�Ѿ�������ͬ�����ˣ���Ҫ�ظ��ύ��");
			}
		} else {
			Eor = "toChoiceSubject";
			ActionContext.getContext().put("message", "�Ѵ�����Ŀ�ˣ�");
		}
		return Eor;
	}
		
	

/**
 * ��ѯ��ʦ��Ϣ
 */
	public String findMytutor(){
		String Eor = null;
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		Student studuent = new Student();
		studuent = (Student) studentService.findStuBystuNo(user12.getUserid()).get(0);
		if(studuent.getTutNo()!=null && studuent.getTutName()!=null && !"".equals(studuent.getTutNo()) && !"".equals(studuent.getTutName())){
			List<Tutor> tutorlist = new ArrayList<Tutor>();
			Tutor tutor = (Tutor) tutorService.findTutorByNo(studuent.getTutNo()).get(0);
			tutorlist.add(0, tutor);
			tutorList = tutorlist;
			Eor = "mytutorlist";
		}else{
			ActionContext.getContext().put("message", "��δ���ڵ�ʦ������ѡ��ʦ��");
			tutorList = tutorService.findTuAll();
			Eor = "toChoiceTutor";
		}	
		return Eor;
	}

/**
 * ǰ���ҵ���Ŀ��Ϣ
 */
	@SuppressWarnings("unchecked")
	public String findMySubject(){
		String Eor = null;
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		Student student = new Student();
		studentList = studentService.findStuBystuNo(user12.getUserid());
		student = studentList.get(0);
		if (student.getTutName() != null && !"".equals(student.getTutName())) {
			if (student.getSubject() != null&& !"".equals(student.getSubject())) {
				Eor = "mysubjectlist";
			} else {
				ActionContext.getContext().put("message","��δ������Ŀ��Ϣ������ѡ����Ŀ�������ĵȴ���ʦ��ˣ�");
				toChoiceSubject();
				Eor = "toChoiceSubject";
			}
		}else{
			ActionContext.getContext().put("message", "��δ���ڵ�ʦ������ѡ��ʦ��");
			tutorList = tutorService.findTuAll();
			Eor = "toChoiceTutor";
		}
		return Eor;
	}
	
/**
 * ��ѯ����
 */
	public String findCT(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		findCt_children(user12.getUserid(),"CT");
		return "CTlist";
	}

/**
 * ��ѯѡ������
 */
	public String findCS(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		findCt_children(user12.getUserid(),"CS");
		return "CSlist";
	}

	public void findCt_children(String applicantID,String typeNumber){
			
		List<Apply> Alist = new ArrayList<Apply>();	
		
		Apply apply = new Apply();
		Tutor tutor = new Tutor();
		
		
		Alist = applyService.findApplyByAID(applicantID,typeNumber);
		List<TuotrApply> tuotrApplieList2 = new ArrayList<TuotrApply>();
		for(int i=0;i<Alist.size();i++){
			TuotrApply tutorApply = new TuotrApply();
			apply = Alist.get(i);
			if(!"--".equals(apply.getRespondentId())){
			tutor = (Tutor) tutorService.findTutorByNo(apply.getRespondentId()).get(0);
			tutorApply.setTutName(tutor.getTutName());
			tutorApply.setPosition(tutor.getPosition());
			tutorApply.setInstituteName(tutor.getInstituteName());}
			else{
				tutorApply.setTutName("����Ա");
				tutorApply.setPosition("--");
				tutorApply.setInstituteName("--");
			}
			tutorApply.setApState(apply.getApState());
			tutorApply.setApTime(apply.getApTime());
			tutorApply.setApId(apply.getApId());
			tutorApply.setHandleTime(apply.getHandleTime());
			tutorApply.setApType(apply.getApType());
			tutorApply.setApContent(apply.getApContent());
			tuotrApplieList2.add(i, tutorApply);
		}
		tutorapplyList = tuotrApplieList2;
	}
/**
 * ��������
 */
	public String delApply() {
				List aplist = applyService.findApplyByApID(apply.getApId());
				if (!aplist.isEmpty()) {
					Apply apply = (Apply) aplist.get(0);
					if ("�����".equals(apply.getApState())) {
						applyService.deleteApply(apply);
					} else {
						ActionContext.getContext().put("message",
								"ֻ�ܳ��� ����� �����룡");
					}
				} else {
					ActionContext.getContext().put("message", "����ʧ��");
				}
		if("CT".equals(apply.getTypeNumber())){
		findCT();
		return "CTlist";
		}else {
			findCS();
			return "CSlist";
		}
	}
	
/**
 * ��������
 */
	@SuppressWarnings("unchecked")
	public String batchDelApply() {
		String items = ServletActionContext.getRequest().getParameter("delitems"); 
		List<Apply> aplist  = new ArrayList<Apply>();
		if (items != null) {
			String[] strs = items.split(",");
			for (int i = 0; i < strs.length; i++) {
				aplist = applyService.findApplyByApID(strs[i]);
				if (!aplist.isEmpty()) {
					Apply apply = (Apply) aplist.get(0);
					if ("�����".equals(apply.getApState())) {
						applyService.deleteApply(apply);
					} else {
						ActionContext.getContext().put("message",
								"ֻ�ܳ��� ����� �����룡");
					}
				} else {
					ActionContext.getContext().put("message", "����ʧ��");
				}
			}
		} else {
			ActionContext.getContext().put("message", "����ʧ�ܣ�");
		}
		if(!aplist.isEmpty() && "CT".equals(aplist.get(0).getTypeNumber())){
		findCT();
		return "CTlist";
		}else {
			findCS();
			return "CSlist";
		}
	}
	
	
/**
 * ǰ�����ѧ��
 */
	public String toaddStu(){
		classlist = classService.findclassAll();
		JSONArray classJson = new JSONArray();
		return "toaddStu";
	}
	
/**
 * ǰ���޸�ѧ��
 */
	public String toUpdateStu(){
		if (classlist != null) {
			classlist.clear();
		}
		studentList = studentService.findStuBystuNo(student.getStuNo());
		Student students = studentList.get(0);
		tutorList = tutorService.findTuAll();
		classlist = classService.findclassAll();
		tutorList2 = tutorService.findTutorByNo(students.getTutNo());
		return "toupdateStu";
	}

/**
 * ǰ����ʦѡ��
 * @return
 */
	public String toChoiceTutor(){
		tutorList = tutorService.findTuAll();
		return "toChoiceTutor";
	}
	
/**
 * ǰ��ѡ���ſ�
 */
	public String toChoiceTut(){
		studentList = studentService.findStuAll();
		return "toChoiceTut";
	}	
	
/**
 * ��ѡ
 */
	public String alreadyChoiceTut(){
		studentList = studentService.findStuByAlready();
		return "toChoiceTut";
	}
	
/**
 * δѡ
 */
	public String NoChoiceTut(){
		studentList = studentService.findStuByNoChooice();
		return "toChoiceTut";
	}

/**
 * ǰ��ѡ����Ŀ
 */
	public String toChoiceSubject() {
		String Eor = null;
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		Student student = (Student) studentService.findStuBystuNo(user12.getUserid()).get(0);
		String tutNo = student.getTutNo();
		if (tutNo != null && !"".equals(tutNo)) {
			subjectList = subjectService.findSubjectByTutNo(tutNo);
			tsControlList = tsControlService.findTsControlByTutNo(tutNo);
			Eor = "toChoiceSubject";
		} else {
			ActionContext.getContext().put("message", "��δ���ڵ�ʦ������ѡ��ʦ��");
			tutorList = tutorService.findTuAll();
			Eor = "toChoiceTutor";
		}
		return Eor;
	}
	
/**
 * ǰ���ҵı�ҵ����
 */
	public String toMyGPDtaum(){
		String Eor = null;
		String fileCatNum = gpFile.getFileCatNum();
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		gpFileList = gpFileService.findGpFileByUploaderId(user12.getUserid(),fileCatNum);
		if("TP".equals(fileCatNum)){
			Eor="toMyTp";
		}else if ("PA".equals(fileCatNum)) {
			Eor="toMyPaper";
		}else if("WO".equals(fileCatNum)){
			Eor="toMyWorks";
		}
		return Eor;
	}
	
/**
 * ������Ϣ
 */
	public String myAnswergroup(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		groupingList = groupingService.findGroupingByStuNo(user12.getUserid());
		grouping = new Grouping();
		return "myAnswergroup";
	}

/**
 * ���������Ϣ
 */
	@SuppressWarnings("unchecked")
	public String listGrouping() {
		
		if (grouping != null && grouping.getReplyType() != null) {
			if (grouping.getClassName() != null&& grouping.getMajorName() == null && grouping.getStuNo()==null) {
				groupingList = groupingService.findGrouping1(grouping.getReplyType(), grouping.getClassName());
			} else if ( grouping.getClassName() == null&& grouping.getMajorName() != null && grouping.getStuNo()==null) {
				groupingList = groupingService.findGrouping2(grouping.getReplyType(), grouping.getMajorName());
			} else if (grouping.getClassName() == null&& grouping.getMajorName() == null && grouping.getStuNo()==null) {
				groupingList = groupingService.findGroupingByReplyType(grouping.getReplyType());
			}else if (grouping.getClassName() == null&& grouping.getMajorName() == null && grouping.getStuNo()!=null) {
				groupingList = groupingService.findGroupingByStuNo(grouping.getReplyType(), grouping.getStuNo());
			}
		}else {
			groupingList = groupingService.findGroupingAll();
		}
		classList = classService.disfindInsAndclassName();
		classList1 = classService.disfindMajorName();
		List<Grouping> JSgroupinglist = new ArrayList<Grouping>();
		JSgroupinglist.add(grouping);
		JSgroupingList = JSgroupinglist;
		grouping = new Grouping();
		return "listGrouping";
	}
	
/**
 * �ɼ���Ϣ
 */
	public String myGpResults(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		gpResultsList = gpResultsService.findGpResultsByStuNo(user12.getUserid());
		return "myGpResults";
	}
/**
 * ���Ȳ�ѯ
 */
	@SuppressWarnings("unchecked")
	public String myProgres(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		studentList = studentService.findStuBystuNo(user12.getUserid());
		gpFileList = gpFileService.findGpFile1(user12.getUserid(), "ͬ��", "TP");
		gpResultsList = gpResultsService.findGpResults3("TPG", user12.getUserid());
		
		List<GpFile> gpFilelist = gpFileService.findGpFile1(user12.getUserid(), "ͬ��", "PA");
		gpFileList.addAll(gpFilelist);
		gpFilelist = gpFileService.findGpFile1(user12.getUserid(), "ͬ��", "WO");
		gpFileList.addAll(gpFilelist);
		
		List<GpResults> gpResultlist = gpResultsService.findGpResults3("PAG", user12.getUserid());
		gpResultsList.addAll(gpResultlist);
		gpResultlist = gpResultsService.findGpResults3("WOG", user12.getUserid());
		gpResultsList.addAll(gpResultlist);
		
		progressList = progressService.findProgressByStuNo(user12.getUserid());
		nodeList = nodeService.findNodeByStuNo(user12.getUserid());
		return "myProgres";
	}

/**
 * ��ӽ�����
 * @throws Exception 
 */
	public String addProgress() throws Exception{
		progressService.addProgress(progress);
		myProgres();
		return "myProgres";
	}

/**
 * ��ӽ��ȵĽڵ�
 */
	public String addOrUpdate(){
		nodeService.addOrUpdate(node);
		myProgres();
		return "myProgres";
	}
	
	
/**
 * �����ѯAll
 */
	@SuppressWarnings("unchecked")
	public String listNoticeAll(){
		if("tutor".equals(notice.getPosition())){
			Map session = ActionContext.getContext().getSession();
			User user12 = (User) session.get("user");
			List<Student> studentlist = studentService.findStuBystuNo(user12.getUserid());
			if(!studentlist.isEmpty()){
				Student student = studentlist.get(0);
				noticeList = noticeService.findNoticeByPublisherId(student.getTutNo());
			}
			
		}else if ("admin".equals(notice.getPosition())) {
			noticeList = noticeService.findNoticeByPosition("admin");
		}
		return "listNoticeAll";
	}
	
/**
 * ��������
 */
	@SuppressWarnings("unchecked")
	public String personalData(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		studentList = studentService.findStuBystuNo(user12.getUserid());
		return "personalData";
	}
	
/**
 */
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
	public List<Class> getClasslist() {
		return classlist;
	}
	public void setClasslist(List<Class> classlist) {
		this.classlist = classlist;
	}
	public StudentForm getStudentForm() {
		return studentForm;
	}
	public void setStudentForm(StudentForm studentForm) {
		this.studentForm = studentForm;
	}
	public ClassForm getClassForm() {
		return classForm;
	}
	public void setClassForm(ClassForm classForm) {
		this.classForm = classForm;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public List<Tutor> getTutorList() {
		return tutorList;
	}
	public void setTutorList(List<Tutor> tutorList) {
		this.tutorList = tutorList;
	}
	public ITutorService getTutorService() {
		return tutorService;
	}
	public void setTutorService(ITutorService tutorService) {
		this.tutorService = tutorService;
	}
	public IApplyService getApplyService() {
		return applyService;
	}
	public void setApplyService(IApplyService applyService) {
		this.applyService = applyService;
	}
	public List<Apply> getApplyList() {
		return applyList;
	}
	public void setApplyList(List<Apply> applyList) {
		this.applyList = applyList;
	}
	public List<TuotrApply> getTutorapplyList() {
		return tutorapplyList;
	}
	public void setTutorapplyList(List<TuotrApply> tutorapplyList) {
		this.tutorapplyList = tutorapplyList;
	}
	public List<Tutor> getTutorList2() {
		return tutorList2;
	}
	public void setTutorList2(List<Tutor> tutorList2) {
		this.tutorList2 = tutorList2;
	}

	public Apply getApply() {
		return apply;
	}

	public void setApply(Apply apply) {
		this.apply = apply;
	}

	public ITsControlService getTsControlService() {
		return tsControlService;
	}

	public void setTsControlService(ITsControlService tsControlService) {
		this.tsControlService = tsControlService;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<TsControl> getTsControlList() {
		return tsControlList;
	}

	public void setTsControlList(List<TsControl> tsControlList) {
		this.tsControlList = tsControlList;
	}

	public ISubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public IGpFileService getGpFileService() {
		return gpFileService;
	}

	public void setGpFileService(IGpFileService gpFileService) {
		this.gpFileService = gpFileService;
	}

	public GpFile getGpFile() {
		return gpFile;
	}

	public void setGpFile(GpFile gpFile) {
		this.gpFile = gpFile;
	}

	public List<GpFile> getGpFileList() {
		return gpFileList;
	}

	public void setGpFileList(List<GpFile> gpFileList) {
		this.gpFileList = gpFileList;
	}

	public IGroupingService getGroupingService() {
		return groupingService;
	}

	public void setGroupingService(IGroupingService groupingService) {
		this.groupingService = groupingService;
	}

	public List<Grouping> getGroupingList() {
		return groupingList;
	}

	public void setGroupingList(List<Grouping> groupingList) {
		this.groupingList = groupingList;
	}

	public Grouping getGrouping() {
		return grouping;
	}

	public void setGrouping(Grouping grouping) {
		this.grouping = grouping;
	}

	public List<Class> getClassList() {
		return classList;
	}

	public void setClassList(List<Class> classList) {
		this.classList = classList;
	}

	public List<Class> getClassList1() {
		return classList1;
	}

	public void setClassList1(List<Class> classList1) {
		this.classList1 = classList1;
	}

	public List<Grouping> getJSgroupingList() {
		return JSgroupingList;
	}

	public void setJSgroupingList(List<Grouping> jSgroupingList) {
		JSgroupingList = jSgroupingList;
	}

	public GpResults getGpResults() {
		return gpResults;
	}

	public void setGpResults(GpResults gpResults) {
		this.gpResults = gpResults;
	}

	public GpResults getGpResults1() {
		return gpResults1;
	}

	public void setGpResults1(GpResults gpResults1) {
		this.gpResults1 = gpResults1;
	}

	public IGpResultsService getGpResultsService() {
		return gpResultsService;
	}

	public void setGpResultsService(IGpResultsService gpResultsService) {
		this.gpResultsService = gpResultsService;
	}

	public List<GpResults> getGpResultsList() {
		return gpResultsList;
	}

	public void setGpResultsList(List<GpResults> gpResultsList) {
		this.gpResultsList = gpResultsList;
	}

	public List<GpResults> getJSgpResultsList() {
		return JSgpResultsList;
	}

	public void setJSgpResultsList(List<GpResults> jSgpResultsList) {
		JSgpResultsList = jSgpResultsList;
	}

	public IProgressService getProgressService() {
		return progressService;
	}

	public void setProgressService(IProgressService progressService) {
		this.progressService = progressService;
	}

	public Progress getProgress() {
		return progress;
	}

	public void setProgress(Progress progress) {
		this.progress = progress;
	}

	public List<Progress> getProgressList() {
		return progressList;
	}

	public void setProgressList(List<Progress> progressList) {
		this.progressList = progressList;
	}

	public List<Node> getNodeList() {
		return nodeList;
	}

	public void setNodeList(List<Node> nodeList) {
		this.nodeList = nodeList;
	}

	public INodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(INodeService nodeService) {
		this.nodeService = nodeService;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public INoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}

	public List<Notice> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}

}
