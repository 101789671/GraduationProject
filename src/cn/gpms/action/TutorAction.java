package cn.gpms.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.struts2.ServletActionContext;

import cn.gpms.form.StudentApply;
import cn.gpms.form.TutorForm;
import cn.gpms.service.IAnswergroupService;
import cn.gpms.service.IApplyService;
import cn.gpms.service.IClassService;
import cn.gpms.service.IGpFileService;
import cn.gpms.service.IGpResultsService;
import cn.gpms.service.INodeService;
import cn.gpms.service.IProgressService;
import cn.gpms.service.IStudentService;
import cn.gpms.service.ISubjectService;
import cn.gpms.service.ITsControlService;
import cn.gpms.service.ITutorService;
import cn.gpms.service.IUserService;
import cn.gpms.vo.Answergroup;
import cn.gpms.vo.Apply;
import cn.gpms.vo.Class;
import cn.gpms.vo.GpFile;
import cn.gpms.vo.GpResults;
import cn.gpms.vo.Node;
import cn.gpms.vo.Progress;
import cn.gpms.vo.Student;
import cn.gpms.vo.Subject;
import cn.gpms.vo.TsControl;
import cn.gpms.vo.Tutor;
import cn.gpms.vo.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TutorAction extends ActionSupport{
	
	protected IClassService classService;
	protected ITutorService tutorService;
	protected IUserService userService;
	protected IApplyService applyService;
	protected IStudentService studentService;
	protected ITsControlService tsControlService;
	protected ISubjectService subjectService;
	protected IGpFileService gpFileService;
	protected IGpResultsService gpResultsService;
	protected IAnswergroupService answergroupService;
	protected IProgressService progressService;
	protected INodeService nodeService;
	
	private String seat;
	private TutorForm tutorForm = new TutorForm();
	private Tutor tutor;
	private Apply apply;
	private Student student;
	private GpFile gpFile;
	private GpFile gpFileC1;
	private GpResults gpResults;
	private GpResults gpResults1;
	private Answergroup answergroup;
	private Answergroup answergroup1;
	
	private List<Class> classlist;
	private List<Class> classList;
	private List<Class> classList1;
	private List<Tutor> tutorList;
	private List<Apply> applyList;
	private List<Student> studentLsit;
	private List<Student> studentList;
	private List<StudentApply> studentapplyList;
	private List<TsControl> tsControlList;
	private List<Subject> subjectList;
	private List<GpFile> gpFileList;
	private List<GpResults> gpResultsList;
	private List<GpResults> JSgpResultsList;
	private List<Answergroup> answergroupList;
	private List<Answergroup> JSanswergroupList;
	private List<Progress> progressList;
	private List<Node> nodeList;
	
	
/**
 * ��ӵ�ʦ
 */
	public String addTutro(){
		String userRole = ServletActionContext.getRequest().getParameter("role");
		
		List<Tutor> tutors = new ArrayList<Tutor>(20);
		
		List<String> tutNolist = new ArrayList<String>();
		List<String> tutNamelist = new ArrayList<String>();
		List<String> sexlist = new ArrayList<String>();
		List<String> inslist = new ArrayList<String>();
		List<String> positionlist = new ArrayList<String>();
		//�ָ���
		StringTokenizer tutNos = new StringTokenizer(tutorForm.getTutNo(), ", ");
		while (tutNos.hasMoreTokens()) {
			tutNolist.add(tutNos.nextToken());
		}
		//�ָ�����
		StringTokenizer tutNames = new StringTokenizer(tutorForm.getTutName(), ", ");
		while (tutNames.hasMoreTokens()) {
			tutNamelist.add(tutNames.nextToken());
		}
		//�ָ��Ա�
		StringTokenizer sexs = new StringTokenizer(tutorForm.getSex(), ", ");
		while (sexs.hasMoreTokens()) {
			sexlist.add(sexs.nextToken());
		}
		//�ָ�ѧԺ
		StringTokenizer inss = new StringTokenizer(tutorForm.getInstituteName(), ", ");
		while (inss.hasMoreTokens()) {
			inslist.add(inss.nextToken());
		}
		//�ָ�ְ��
		StringTokenizer positions = new StringTokenizer(tutorForm.getPosition(), ", ");
		while (positions.hasMoreTokens()) {
			positionlist.add(positions.nextToken());
		}

		int a = 0, b = 0;
		for (int i = 0, j = 0; i < tutNolist.size(); i++) {
			List userlist = userService.findUserById(tutNolist.get(i));
			List tutlist = tutorService.findTuByIns(tutNolist.get(i));
			if (userlist.isEmpty() && tutlist.isEmpty()) {
				String tutNo =tutNolist.get(i) ;
				String tutName = tutNamelist.get(i);	
				User user = new User();
				user.setUserid(tutNo);
				user.setUserName(tutName);
				user.setRole(userRole);
				userService.addUser(user);// ��User�����
				Tutor tutor = new Tutor();
				tutor.setTutNo(tutNo);
				tutor.setTutName(tutName);
				tutor.setSex(sexlist.get(i));
				tutor.setInstituteName(inslist.get(i));
				tutor.setPosition(positionlist.get(i));
				tutorService.addTutor(tutor);	
				TsControl tsControl = new TsControl();
				tsControl.setTutNo(tutNo);
				tsControl.setTutName(tutName);
				tsControl.setPermissions("T");
				tsControlService.addTsControl(tsControl);
				a = a + 1;
				tutors.add(j, tutor);
				j++;
			} else {
				b = b + 1;
			}
		}
		ActionContext.getContext().put("message","�ɹ�����  " + a + "  �����ݣ�δ�ɹ�  " + b + "  ����");
		tutorList = tutors;
		return "tutorlist";
	}
	
/**
 * ��ѯ���е�ʦ
 */
	@SuppressWarnings("unchecked")
	public String findTuAll(){
		tutorList = tutorService.findTuAll();
		return "tutorlist";
	}
	
/**	
 * ����ɾ����ʦ
 */
	public String delTuOne(){
		if(tutor.getTutNo()!=null){
		tutorService.delTutor(tutor);
		User user = new User();
		user.setUserid(tutor.getTutNo());
		userService.delUser(user);
		TsControl tsControl = new TsControl();
		tsControl.setTutNo(tutor.getTutNo());
		tsControlService.deleteTsControl(tsControl);
		}else{
			ActionContext.getContext().put("message","ɾ��ʧ�ܣ�");
		}
		tutorList = tutorService.findTuAll();
		return "tutorlist";
	}

/**
 * ����ɾ����ʦ
 */
	public String delTuBatch(){
		String items = ServletActionContext.getRequest().getParameter("delitems"); 
		String Ero = "false";
		if (items != null) {
			String[] strs = items.split(",");
			for(int i=0;i<strs.length;i++){
				Tutor tutor = new Tutor();
				tutor.setTutNo(strs[i]);
			    tutorService.delTutor(tutor);
				User user = new User();
				user.setUserid(strs[i]);
				userService.delUser(user);
				TsControl tsControl = new TsControl();
				tsControl.setTutNo(strs[i]);
				tsControlService.deleteTsControl(tsControl);
				}
		}
		if (Ero == "flase") {
			ActionContext.getContext().put("message", "����ɾ��ʧ�ܣ�");
		} else {
			ActionContext.getContext().put("message", "����ɾ���ɹ���");
		}
		return "tutorlist";
	}
	
/**
 * ����ѡ������
 */
	public String handleCT(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		Apply apply1 = (Apply) applyService.findApplyByApID(apply.getApId()).get(0);
		applyService.updtateApType(apply.getApId(), apply.getApState());//�޸ĵ�ǰ����״̬
		if("agree".equals(apply.getApState())){
		applyService.updateApType2(apply1.getApplicantId(), "CT","��ʧЧ", "�����");//�޸���������״̬
		}
		studentService.updateStuTut(apply1.getApplicantId(), user12.getUserid(), user12.getUserName());//����ѧ����ʦ��Ϣ
		examine_children(user12,"ѡ��","�����");
		return "exaCT";
	}
	
/**
 * ����ѡ������
 */
	public String handleCS(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		String Eor = studentService.updateStuSub(user12, apply);
		if(Eor=="success"){
		examine_children(user12,"ѡ��","�����");}
		else{
			ActionContext.getContext().put("message","���봦��ʧ�ܣ�");
		}
		return "exaCS";
	}

/**
 * �Ƴ�����ѧ��
 */
	public String removeMystu(){
		String stuNo = student.getStuNo();
		if(stuNo!=null){
			studentService.updateStuTut(stuNo, "", "");
		}else{
			ActionContext.getContext().put("message","�Ƴ�ʧ�ܣ�");
		}
		findMystu();
		return "studentlist";
	}

/**
 * �޸�ѧ����Ŀ
 */
	public String updateStuSubT(){
		String Eor = null;
		if (!"".equals(student.getStuNo()) && !"".equals(student.getSubject())) {
			studentService.updateStuSubT(student);
			findMystu();
			Eor = "studentlist";
		} else {
			ActionContext.getContext().put("message", "����ʧ�ܴ�������Ϊ�գ�");
			toUpdateStudentSub();
			Eor = "toUpdateStudentSub";
		}
		return Eor;
	}

/**
 * �鿴ĳ��ʦ����ѡ�������¼
 */
	public String recordCT(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		examine_children(user12, "CT", null);
		return "recordCTlist";
	}
	
/**
 * �鿴ĳ��ʦ����ѡ�������¼
 */
	public String recordCS(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		examine_children(user12, "CS", null);
		return "recordCTlist";
	}
	
	
/**
 * ��ѯ������ѧ��
 */
	public String findMystu(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		studentLsit = studentService.findStuByTutNo(user12.getUserid());
		return "studentlist";
	}
	
	
/**
 * �޸ĵ�ʦ
 */
	public String updateTutor(){
		tutorService.updateTutor(tutorForm);
		tutorList = tutorService.findTuAll();
		return "tutorlist";
	}
	
	/**
	 * ǰ����ӵ�ʦ
	 * @return
	 */
	public String toAddTutor(){
		classlist = classService.findclassAll();
		return "toaddTutor";
	}
	
/**
 * ǰ���޸ĵ�ʦ
 * @return
 */
	public String toUpdateTu(){
		tutorList = tutorService.toUpdateTu(tutor.getTutNo());
		classlist = classService.findclassAll();
		return "toupdateTu";
	}
/**
 * ǰ�����ѡ������
 */
	public String examineCT(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		examine_children(user12,"CT","�����");
		return "exaCT";
	}
	
/**
 * ǰ�����ѡ��
 */
	public String examineCS(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		examine_children(user12,"CS","�����");
		return "exaCS";
	}
	
/**
 * ǰ���޸�ѧ����Ŀ
 */
	@SuppressWarnings("unchecked")
	public String toUpdateStudentSub(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		studentLsit = studentService.findStuBystuNo(student.getStuNo());
		subjectList = subjectService.findSubjectByTutNo(user12.getUserid());
		return "toUpdateStudentSub";
	}
	
	
	

	public void examine_children(User user,String typeNumber,String apState){
		
			
		Apply apply = new Apply();
		Student student = new Student();
		List<StudentApply> studentApplyList2 = new ArrayList<StudentApply>();
		List Alist = new ArrayList<Apply>();
		if(user!=null && typeNumber!=null && apState!=null){Alist = applyService.findApply2(user.getUserid(), typeNumber, apState);}
		if(user!=null && typeNumber!=null && apState==null){Alist = applyService.findApply6(user.getUserid(), typeNumber);}
		if(user!=null && typeNumber==null && apState==null){Alist = applyService.findApplyByRID(user.getUserid());}
		if(user==null && typeNumber!=null && apState!=null){Alist = applyService.findApply4(typeNumber, apState);}
		if(user==null && typeNumber!=null && apState==null){Alist = applyService.findApplyByNum(typeNumber);}

		for(int i=0;i<Alist.size();i++){
			StudentApply studentApply = new StudentApply();
			apply = (Apply) Alist.get(i);
			student = (Student) studentService.findStuBystuNo(apply.getApplicantId()).get(0);
			studentApply.setStuNo(student.getStuNo());
			studentApply.setStuName(student.getStuName());
			studentApply.setClassName(student.getClassName());
			studentApply.setInstituteName(student.getInstituteName());
			studentApply.setApId(apply.getApId());
			studentApply.setApContent(apply.getApContent());
			studentApply.setApState(apply.getApState());
			studentApply.setApTime(apply.getApTime());
			studentApply.setHandleTime(apply.getHandleTime());
			studentApply.setApType(apply.getApType());
			studentApplyList2.add(i, studentApply);
		}
		studentapplyList = studentApplyList2;
	}
	
/**
 * ǰ����Ŀ���
 */
	public String toAddSubject(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		tsControlList = tsControlService.findTsControlByTutNo(user12.getUserid());
		return "toaddSubject";
	}
	
/**	
 * ǰ��ѧ���������ϱ��棨�ſ���
 */
	public String toStudentGPDatumSurvey(){
		String Eor = null;
		String fileCatNum = gpFile.getFileCatNum();
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		gpFileList = gpFileService.toStudentGPDatumSurvey(user12,fileCatNum);
		if("TP".equals(fileCatNum)){
			Eor="toStudentTPSurvey";
		}else if ("PA".equals(fileCatNum)) {
			Eor="toStudentPASurvey";
		}else if("WO".equals(fileCatNum)){
			Eor="toStudentWOSurvey";
		}
		gpFileC1 = gpFile;
		return Eor;
	}
/**
 * ��ѯĳ��ѧ���ĸ��࿪�ⱨ��
 */
	public String someStudentMoreGPDatum(){
		String Eor = null;
		String fileCatNum = gpFile.getFileCatNum();
		gpFileList = gpFileService.findGpFileByUploaderId(gpFile.getUploaderId(),fileCatNum);
		if("TP".equals(fileCatNum)){
			Eor="studentMoreTP";
		}else if ("PA".equals(fileCatNum)) {
			Eor="studentMorePA";
		}else if("WO".equals(fileCatNum)){
			Eor="studentMoreWO";
		}
		gpFileC1 = gpFile;
		return Eor;
	}
/**
 * ��ѯ��ʦ����������ѧ�������б�������
 */
	public String studentAllGPDatum(){
		String Eor = null;
		String fileCatNum = gpFile.getFileCatNum();
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		gpFileList = gpFileService.findGpFileByTutNo(user12.getUserid(), fileCatNum);
		if("TP".equals(fileCatNum)){
			Eor="studentMoreTP";
		}else if ("PA".equals(fileCatNum)) {
			Eor="studentMorePA";
		}else if("WO".equals(fileCatNum)){
			Eor="studentMoreWO";
		}
		gpFileC1 = gpFile;
		return Eor;
	}

/**
 * ��ѯѧ���ɼ�
 */
	public String myStudentGpResults(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		if (gpResults != null && gpResults.getReType() != null) {
			if( gpResults.getClassName() == null&& gpResults.getStuNo() == null){
				//��ѯ��ʦ����������ѧ����ĳ���ͳɼ�
				gpResultsList = gpResultsService.findGpResults4(gpResults.getReType(), user12.getUserid());
				
			}else if (gpResults.getClassName() != null&& gpResults.getStuNo() == null) {
				//���ݳɼ����͡��༶��ѯ
				gpResultsList = gpResultsService.findGpResults5(gpResults.getReType(), user12.getUserid(), gpResults.getClassName());
				
			}else if (gpResults.getClassName() == null&& gpResults.getStuNo() != null) {
				//���ݳɼ����͡�ѧ�Ų�ѯ
				gpResultsList = gpResultsService.findGpResults3(gpResults.getReType(), gpResults.getStuNo());
			}
		} else {
			//��ѯ��ʦ����ѧ���������͵ĳɼ�
			gpResultsList = gpResultsService.findGpResultsByTutNo(user12.getUserid());
		}
		classList = classService.disfindInsAndclassName();
		classList1 = classService.disfindMajorName();
		this.gpResults1= gpResults;
		List<GpResults> JSGpResultlist = new ArrayList<GpResults>();
		JSGpResultlist.add(gpResults1);
		JSgpResultsList = JSGpResultlist;
		gpResults = new GpResults();
		return "myStudentGpResults";
	}

/**
 * ����ɼ�
 */
	@SuppressWarnings("unchecked")
	public String listGpResults(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		if (gpResults != null && gpResults.getReType() != null) {
			if(gpResults.getMajorName() == null&& gpResults.getClassName() == null&& gpResults.getStuNo() == null){
				gpResultsList = gpResultsService.findGpResults4(gpResults.getReType(), user12.getUserid());
			}else if (gpResults.getMajorName() == null&& gpResults.getClassName() == null&& gpResults.getStuNo() == null) {
				gpResultsList = gpResultsService.findGpResultsByReType(gpResults.getReType());
			} else if (gpResults.getMajorName() == null&& gpResults.getClassName() != null&& gpResults.getStuNo() == null) {
				gpResultsList = gpResultsService.findGpResults1(gpResults.getReType(), gpResults.getClassName());
			} else if (gpResults.getMajorName() != null&& gpResults.getClassName() == null&& gpResults.getStuNo() == null) {
				gpResultsList = gpResultsService.findGpResults2(gpResults.getReType(), gpResults.getMajorName());
			} else if (gpResults.getMajorName() == null&& gpResults.getClassName() == null&& gpResults.getStuNo() != null) {
				gpResultsList = gpResultsService.findGpResults3(gpResults.getReType(), gpResults.getStuNo());
			}
		} else {
			gpResultsList = gpResultsService.findGpResultsAll();
		}
		classList = classService.disfindInsAndclassName();
		classList1 = classService.disfindMajorName();
		this.gpResults1= gpResults;
		List<GpResults> JSGpResultlist = new ArrayList<GpResults>();
		JSGpResultlist.add(gpResults1);
		JSgpResultsList = JSGpResultlist;
		gpResults = new GpResults();
		return "listGpResults";
	}
/**
 * ��ѯ�ҵĴ����
 */
	public String myAnserGroup(){
		List<Answergroup> answergrouplist = new ArrayList<Answergroup>();
		List<Answergroup> answergrouplist1 = new ArrayList<Answergroup>();
		answergrouplist = answergroupService .findAnswergroupAll();
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		for(int i=0;i<answergrouplist.size();i++){
			Answergroup answergroup = answergrouplist.get(i);
			if(user12.getUserid().equals(answergroup.getChargehandId())){
				answergrouplist1.add(answergroup);
			}else if (user12.getUserid().equals(answergroup.getMember1id())) {
				answergrouplist1.add(answergroup);
			}else if (user12.getUserid().equals(answergroup.getMember2id())) {
				answergrouplist1.add(answergroup);
			}else if (user12.getUserid().equals(answergroup.getMember3id())) {
				
			}
			
		}
		answergroupList = answergrouplist1;
		return "myAnserGroup";
	}

/**
 * ��ѯ
 */
	@SuppressWarnings("unchecked")
	public String listAnserGroup(){
		if(answergroup!=null && answergroup.getReplyType()!=null){
			if(answergroup.getMajorName()!=null && !"".equals(answergroup.getMajorName())){
				answergroupList = answergroupService.findAnswerGroup3(answergroup.getReplyType(), answergroup.getMajorName());
			}else{
				answergroupList = answergroupService.findAnswerGroupByReplyType(answergroup.getReplyType());
			}
			
		}else{
			answergroupList = answergroupService.findAnswergroupAll();
		}
		classList = classService.disfindInsAndclassName();
		classList1 = classService.disfindMajorName();
		List<Answergroup> JSanswergrouplist = new ArrayList<Answergroup>();
		JSanswergrouplist.add(answergroup);
		JSanswergroupList = JSanswergrouplist;
		answergroup1 = answergroup;
		answergroup = new Answergroup();
		return "listAnswerGroup";
		}
	
/**
 * ���ı�������
 */
	public String reviewFile(){
		String Eor = gpFileService.reviewFile(gpFile);
		if("error".equals(Eor)){
			ActionContext.getContext().put("message", "����ʧ�ܣ�");
		}
		gpFile = gpFileC1;
		if(gpFile!=null && gpFile.getUploaderId()!=null && gpFile.getFileCatNum()!=null){
			Eor = someStudentMoreGPDatum();
		}else if (gpFile!=null && gpFile.getUploaderId()==null && gpFile.getFileCatNum()!=null && "1".equals(seat)) {
			Eor = toStudentGPDatumSurvey();
		}else {
			Eor = studentAllGPDatum();
		}
		gpFile = new GpFile();
		return Eor;
	}
	
/**
 * ǰ����������
 */
	public String toReleaseNotice(){
		return "toReleaseNotice";
	}

/**
 * ǰ��ѧ������
 */
	@SuppressWarnings("unchecked")
	public String toStudentPregress(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		studentLsit = studentService.findStuByTutNo(user12.getUserid());
		return "toStudentPregress";
	}
	
/**
 * ��ѯѧ������
 */
	@SuppressWarnings("unchecked")
	public String studentProgress(){
		
		studentList = studentService.findStuBystuNo(student.getStuNo());
		gpFileList = gpFileService.findGpFile1(student.getStuNo(), "ͬ��", "TP");
		gpResultsList = gpResultsService.findGpResults3("TPG", student.getStuNo());
		
		List<GpFile> gpFilelist = gpFileService.findGpFile1(student.getStuNo(), "ͬ��", "PA");
		gpFileList.addAll(gpFilelist);
		gpFilelist = gpFileService.findGpFile1(student.getStuNo(), "ͬ��", "WO");
		gpFileList.addAll(gpFilelist);
		
		List<GpResults> gpResultlist = gpResultsService.findGpResults3("PAG", student.getStuNo());
		gpResultsList.addAll(gpResultlist);
		gpResultlist = gpResultsService.findGpResults3("WOG", student.getStuNo());
		gpResultsList.addAll(gpResultlist);
		
		progressList = progressService.findProgressByStuNo(student.getStuNo());
		nodeList = nodeService.findNodeByStuNo(student.getStuNo());
		return "toStudentPregress";
	}

/**
 * ��������
 */
	@SuppressWarnings("unchecked")
	public String personalData(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		tutorList = tutorService.findTutorByNo(user12.getUserid());
		return "personalData";
	}
	
	
/**
 */
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

	public TutorForm getTutorForm() {
		return tutorForm;
	}

	public void setTutorForm(TutorForm tutorForm) {
		this.tutorForm = tutorForm;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
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

	public List<StudentApply> getStudentapplyList() {
		return studentapplyList;
	}

	public void setStudentapplyList(List<StudentApply> studentapplyList) {
		this.studentapplyList = studentapplyList;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public List<Student> getStudentLsit() {
		return studentLsit;
	}

	public void setStudentLsit(List<Student> studentLsit) {
		this.studentLsit = studentLsit;
	}

	public List<TsControl> getTsControlList() {
		return tsControlList;
	}

	public void setTsControlList(List<TsControl> tsControlList) {
		this.tsControlList = tsControlList;
	}

	public ITsControlService getTsControlService() {
		return tsControlService;
	}

	public void setTsControlService(ITsControlService tsControlService) {
		this.tsControlService = tsControlService;
	}

	public Apply getApply() {
		return apply;
	}

	public void setApply(Apply apply) {
		this.apply = apply;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public ISubjectService getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubjectService subjectService) {
		this.subjectService = subjectService;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<GpFile> getGpFileList() {
		return gpFileList;
	}

	public void setGpFileList(List<GpFile> gpFileList) {
		this.gpFileList = gpFileList;
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

	public GpResults getGpResults() {
		return gpResults;
	}

	public void setGpResults(GpResults gpResults) {
		this.gpResults = gpResults;
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

	public IGpResultsService getGpResultsService() {
		return gpResultsService;
	}

	public void setGpResultsService(IGpResultsService gpResultsService) {
		this.gpResultsService = gpResultsService;
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

	public GpResults getGpResults1() {
		return gpResults1;
	}

	public void setGpResults1(GpResults gpResults1) {
		this.gpResults1 = gpResults1;
	}

	public IAnswergroupService getAnswergroupService() {
		return answergroupService;
	}

	public void setAnswergroupService(IAnswergroupService answergroupService) {
		this.answergroupService = answergroupService;
	}

	public List<Answergroup> getAnswergroupList() {
		return answergroupList;
	}

	public void setAnswergroupList(List<Answergroup> answergroupList) {
		this.answergroupList = answergroupList;
	}

	public Answergroup getAnswergroup() {
		return answergroup;
	}

	public void setAnswergroup(Answergroup answergroup) {
		this.answergroup = answergroup;
	}

	public Answergroup getAnswergroup1() {
		return answergroup1;
	}

	public void setAnswergroup1(Answergroup answergroup1) {
		this.answergroup1 = answergroup1;
	}

	public List<Answergroup> getJSanswergroupList() {
		return JSanswergroupList;
	}

	public void setJSanswergroupList(List<Answergroup> jSanswergroupList) {
		JSanswergroupList = jSanswergroupList;
	}

	public GpFile getGpFileC1() {
		return gpFileC1;
	}

	public void setGpFileC1(GpFile gpFileC1) {
		this.gpFileC1 = gpFileC1;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public IProgressService getProgressService() {
		return progressService;
	}

	public void setProgressService(IProgressService progressService) {
		this.progressService = progressService;
	}

	public INodeService getNodeService() {
		return nodeService;
	}

	public void setNodeService(INodeService nodeService) {
		this.nodeService = nodeService;
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


}
