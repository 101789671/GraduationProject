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
 * ����apID��ѯ������Ϣ
 */
	public List findApplyByApID(String apId) {
		return applyDAO.findApplyByApID(apId);
	}
	
/**
 * �����������͡�״̬��ѯ �������루�磺�������룩
 */
	public List findApply4(String typeNumber, String apState) {
		return applyDAO.findApply4(typeNumber, apState);
	}
	
/**
 * ����������ID���������ͱ�Ų�ѯ������Ϣ
 */
	public List findApply5(String applicantID, String typeNumber) {
		return applyDAO.findApply5(applicantID, typeNumber);
	}
	
/**
 * ���ݱ�������ID���������ͱ�Ų�ѯ������Ϣ
 */
	public List findApply6(String respondentId, String typeNumber) {
		return applyDAO.findApply6(respondentId, typeNumber);
	}

/**
 * ����apIDɾ������
 */
	public void deleteApply(Apply apply) {
		applyDAO.deleteApply(apply);
	}

/**
 * ���ݱ�������ID��ѯ����״̬������
 */
	public List findApplyByRID(String respondentId) {
		return applyDAO.findApplyByRID(respondentId);
	}
	
/**
 * �����������ͱ�� ��ѯ����
 */
	public List findApplyByNum(String typeNumber) {
		return applyDAO.findApplyByNum(typeNumber);
	}
	
/**
 * �������
 */
	public void addApply(User user ,Tutor tutor ,Apply apply) {
		SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMddHHmmss");// ��ȡ��ǰϵͳʱ��
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ��ȡ��ǰϵͳʱ��
		//����apply��� ��д����
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
		//д���������͡���������
		if ("CT".equals(apply.getTypeNumber())) {
			apply.setApType("ѡ��");
			apply.setApContent("�������뵼ʦ");
		} else if ("CD".equals(apply.getTypeNumber())) {
			apply.setApType("ѡ��");
			apply.setApContent("�������");
		} else {
			apply.setApType("ѡ��");
		}
		apply.setApplicant(user.getUserName());
		apply.setApplicantId(user.getUserid());
		if(tutor.getTutName()==null){
			apply.setRespondent("����Ա");
			apply.setRespondentId("--");
			apply.setRespondentRole("admin");
		}else{
		    apply.setRespondent(tutor.getTutName());
		}
		apply.setApState("�����");
		apply.setApTime(df.format(new Date()));
		applyDAO.addApply(apply);

	}

/**
 * ����������ID��ѯĳһ���ͱ�š�ĳһ״̬������
 */
	public List findApplyByAID(String applicantID, String typeNumber) {
		String apType = null;
		if ("CT".equals(typeNumber) || "CD".equals(typeNumber)) {
			apType = "ѡ��";
		} else if ("CS".equals(typeNumber)) {
			apType = "ѡ��";
		}
		return applyDAO.findApplyByAID(applicantID, apType);
	}

/**
 * ���������ˡ������롢 �������ͱ�Ų�ѯ��¼
 */
	public List findApply3(String applicantId, String respondentId,String typeNumber) {
		List applylist = new ArrayList<Apply>();
		String  apType = null;
		if ("CT".equals(typeNumber) || "CD".equals(typeNumber)) {
			apType = "ѡ��";
		} else if ("CS".equals(typeNumber)) {
			apType = "ѡ��";
		}
		if("CT".equals(typeNumber)){
			applylist = applyDAO.findApply3(applicantId, respondentId, apType);
		}else if("CD".equals(typeNumber)) {
			applylist = applyDAO.findApply3_1(applicantId, typeNumber);
		}
		return applylist;
	}


/**
 * ���ݱ�������ID��ѯĳһ���͡�ĳһ״̬������
 */
	public List findApply2(String respondentId, String apType,
			String apState) {
		if ("CT".equals(apType) || "CD".equals(apType)) {
			apType = "ѡ��";
		} else if ("CS".equals(apType)) {
			apType = "ѡ��";
		}
		return applyDAO.findApply2(respondentId, apType, apState);
	}
	
/**	
 * ���������ˡ��������ͱ�š��������ݲ�ѯ����
 */
	public List findApply1(String applicantId, String typeNumber,
			String apContent) {
		return applyDAO.findApply1(applicantId, typeNumber, apContent);
	}


/**
 * ����apID�޸�����״̬
 */
	public void updtateApType(String apId, String apState) {
		if ("agree".equals(apState)) {
			apState = "ͬ��";
		} else if ("disagree".equals(apState)) {
			apState = "��ͬ��";
		}
		Apply apply = (Apply) applyDAO.findApplyByApID(apId).get(0);
		apply.setApState(apState);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ��ȡ��ǰϵͳʱ��
		apply.setHandleTime( df.format(new Date()));
		applyDAO.updtateApply(apply);
	}
	
/**
 * ���ݱ�������ID��ĳһ���͡�ĳһ״̬�޸������״̬
 */
	public void updateApType2(String applicantId ,String typeNumber,String newapState,String oldapState){
		String apType = null;
		if ("CT".equals(typeNumber) || "CD".equals(typeNumber)) {
			apType = "ѡ��";
		} else if ("CS".equals(typeNumber)) {
			apType = "ѡ��";
		}	
		List applyAlist = applyDAO.findApplyByAID(applicantId, apType);
		for(int i=0;i<applyAlist.size();i++){
			Apply apply = new Apply();
			apply= (Apply) applyAlist.get(i);
			if(oldapState.equals(apply.getApState()) && apType.equals(apply.getApType())){
				apply.setApState(newapState);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// ��ȡ��ǰϵͳʱ��
				apply.setHandleTime(df.format(new Date()));
				applyDAO.updtateApply(apply);
			}
		}
	
	}

/**
 * ����-���(�Զ�)���䴦��
 * @return 
 */
	public List<Student> randomAllot(String stuNo) {
		int MaxNumber = 12;
		Random ra = new Random();
		// ��ȡѧ������ѧԺ
		Student student = (Student) studentService.findStuBystuNo(stuNo).get(0);
		String stuIns = student.getInstituteName();
		String stuInsback = stuIns;
		// ��ȡ���е�ʦ
		List<Tutor> tutAlllist = tutorService.findTuAll();
		// ��ȡ����ѧԺ
		List<String> InsAll = classService.disfindclassAll();
		// ����һ�����ڴ洢ĳһѧԺ�����е�ʦ�ļ���
		List<Tutor> oneInsTutlist = new ArrayList<Tutor>();
		//����һ�����ڷ��ص�ѧ������
		List<Student> studentlist = new ArrayList<Student>(); 
		if(student.getTutNo()==null||"".equals(student.getTutNo())){
		w1: while (InsAll.size() > 0) {
			// ��ѧ������ѧԺ�������ȡ�õ�ѧԺ �����е�ʦ����������������oneInsTutlist��
			for (int i = 0; i < tutAlllist.size(); i++) {
				if (stuIns.equals(tutAlllist.get(i).getInstituteName())) {
					oneInsTutlist.add(tutAlllist.get(i));
				}
			}
			w2: while (oneInsTutlist.size() > 0) {
				// �����ȡһ����ʦ
				int Tutint = ra.nextInt(oneInsTutlist.size() + 0);// ��ȡ�����
				Tutor tutor = oneInsTutlist.get(Tutint);
				// �жϵ�ʦ���������Ƿ����MaxNumber
				int TutNumber = -1;
				if (tutor != null) {
					TutNumber = studentService.findStuByTutNoI(tutor.getTutNo());
					if (TutNumber > -1 && TutNumber < MaxNumber) {
						// ִ�з���
						allotApplys(stuNo, tutor.getTutName());
						studentlist.add((Student) studentService.findStuBystuNo(stuNo).get(0));
						// �˳�w1ѭ��
						break w1;
					} else {
						// ���õ�ʦ��oneInsTutlist��ɾ��
						oneInsTutlist.remove(Tutint);
					}
				}
			}
			//��InsAll���Ƴ�ѧԺ
			InsAll.remove(stuIns);
			//�����ȡѧԺ
			int InsInt = ra.nextInt(InsAll.size() + 0);
			stuIns = InsAll.get(InsInt);
			//���oneInsTutlist
			oneInsTutlist.clear();
		}}
		return studentlist;
	}

	
	
	
/**
 * ���봦�� �� ����
 */
	public void allotApplys(String stuNo,String tutName){
		Apply apply = (Apply) findApply5(stuNo, "CD").get(0);//��ȡ��������������Ϣ
		updtateApType(apply.getApId(), "agree");//�޸ĵ�ǰ����״̬
		updateApType2(stuNo, "CT","��ʧЧ", "�����");//�޸���������״̬  //CT CDЧ����ͬ
		Tutor tutor =(Tutor) tutorService.findTuByName(tutName).get(0);//��ȡ��ʦ
		studentService.updateStuTut(stuNo, tutor.getTutNo(), tutName);//����ѧ����Ϣ
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
