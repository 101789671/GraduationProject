package cn.gpms.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import cn.gpms.dao.IGroupingDAO;
import cn.gpms.service.IAnswergroupService;
import cn.gpms.service.IClassService;
import cn.gpms.service.IGroupingService;
import cn.gpms.service.IStudentService;
import cn.gpms.service.ITutorService;
import cn.gpms.vo.Answergroup;
import cn.gpms.vo.Class;
import cn.gpms.vo.Grouping;
import cn.gpms.vo.Student;
import cn.gpms.vo.Tutor;

public class GroupingService implements IGroupingService {

	private Answergroup answergroup;

	protected IGroupingDAO groupingDAO;
	protected IClassService classService;
	protected IStudentService studentService;
	protected IAnswergroupService answergroupService;
	protected ITutorService tutorService;
	
/**
 * ���
 */
	//���
	public void addGrouping(Grouping grouping){
		groupingDAO.addGrouping(grouping);
	}

	
/**
 * �Զ�����
 */
	@SuppressWarnings("unchecked")
	public void answerGrouping(String replyType) {
		Random ra = new Random();
		List<Student> studentlist = studentService.findStuAll();// ��ѯ����ѧ��
		List<Class> classlist = classService.disfindMajorName();// ȥ�ز�ѯ��ѧԺ��רҵ
		if (!classlist.isEmpty()) {
			String replyTypeName = null;
			if("TPR".equals(replyType)){
				 replyTypeName = "������";
			}else if ("PAR".equals(replyType)) {
				 replyTypeName = "��ҵ���";
			}
			for (int i = 0; i < classlist.size(); i++) {
				Class class1 = classlist.get(i);
				String instituteName = class1.getInstituteName();
				String majorName = class1.getMajorName();

				int anserNum = 0;// ����
				int peoNum = 0;// ÿ������
				// �����������majorNameרҵ��ѧ��
				List<Student> studentlist2 = studentService.findStuByMajorName(majorName);
				// �����������instituteNameѧԺ�ĵ�ʦ
				List<Tutor> tutorlist2 = tutorService.findTuByIns(instituteName);
				// ���ѧ������
				int stuNums = studentlist2.size();
				// �õ�����
				anserNum = tutorlist2.size() / 3;
				// �õ�ÿ������
				peoNum = stuNums / anserNum + 1;
				// ��ȡѧԺ=instituteName��רҵ=majorName�������Ϣ
				List<Answergroup> answergrouplist = answergroupService.findAnswergroup1(replyType,instituteName, majorName);
				// �ж��Ƿ������� �����Ϊ�գ��򴴽�������
				if (answergrouplist.isEmpty()) {
					// ����һ�����ڴ����һ��ѧ���ļ���
					List<Student> lastStudentlist = new ArrayList<Student>();
					SimpleDateFormat df = new SimpleDateFormat("yyyy");// ��ȡ��ǰϵͳʱ��
					Answergroup answergroup = new Answergroup();
					// �������ID
					String groupId =replyType+ df.format(new Date())+ class1.getInstituteNo() + class1.getMajorNo();
					answergroup.setReplyType(replyType);
					answergroup.setInstituteName(instituteName);
					answergroup.setMajorName(majorName);
					// ������һ�������
					int lastGroupNum = stuNums - ((anserNum - 1) * peoNum);
					boolean lastGroupFound = false;
					// �ж����һ�������Ƿ�ﵽ����һ�������
					if (lastGroupNum < (0.75 * peoNum)) {
						// ����ﲻ�����������ȡlastGroupNum��ѧ��,װ��lastStudentlist��
						for (int j = 0; j < lastGroupNum; j++) {
							int trandomt = ra.nextInt(studentlist2.size() + 0);
							Student student = studentlist2.get(trandomt);
							lastStudentlist.add(student);
							studentlist2.remove(trandomt);
						}
						anserNum = anserNum - 1;
						lastGroupFound = true;
					}
					F2_1: for (int j = 1; j <= anserNum; j++) {
						answergroup.setGroupId(groupId + j);
						// ���ɷ�����
						String groupName = "��" + j + "��";
						answergroup.setGroupName(groupName);
						int stuNumber = 0;
						// д���������Ϣ
						F3_1: for (int k = 1, l = 1; k <= peoNum; k++) {
							if (!studentlist2.isEmpty()) {
								// ��ȡ�����
								int trandomt = ra.nextInt(studentlist2.size() + 0);
								// ���ѧ��
								Student student = studentlist2.get(trandomt);
								String stuNo = student.getStuNo();
								// ��ȡѧ��ĳ���ͷ�����Ϣ
								List<Grouping> groupinglist = groupingDAO.findGroupingBygsNo(replyType, stuNo);
								// �жϸ�ѧ���Ƿ��Ѵ��ڸ����͵Ĵ�����
								if (groupinglist.isEmpty()) {
									k = l;
									Grouping grouping = new Grouping();
									// д���������Ϣ
									grouping.setGroupNo(replyType + stuNo);
									grouping.setReplyType(replyType);
									grouping.setReplyTypeName(replyTypeName);
									grouping.setStuNo(stuNo);
									grouping.setStuName(student.getStuName());
									grouping.setClassNo(student.getClassNo());
									grouping.setClassName(student.getClassName());
									grouping.setTutName(student.getTutName());
									grouping.setMajorName(student.getMajorName());
									grouping.setGroupingsNo(groupName);
									grouping.setOrders(Integer.toString(k));
									// grouping.setPlace("fuck");
									// ��Ӵ����������ݿ�
									groupingDAO.addGrouping(grouping);
									l++;
									stuNumber++;
								} else {
									k = l;
								}
								studentlist2.remove(trandomt);
							} else {
								if (stuNumber > 0) {
									answergroup.setStuNumber(String.valueOf(stuNumber));
									// ��Ӵ����������ݿ�
									answergroupService.addAnswergroup(answergroup);
								}
								break F2_1;

							}
						}
						answergroup.setStuNumber(String.valueOf(stuNumber));
						// ��Ӵ����������ݿ�
						answergroupService.addAnswergroup(answergroup);
					}
					// �޷��������һ��ʱ�Ĵ�����ʣ��ѧ���ֵ�������
					if (lastGroupFound && lastStudentlist.size() > 0) {
						// ��ȡ��������Ϣ
						List<Answergroup> answergroupslist2 = answergroupService.findAnswergroup1(replyType,instituteName, majorName);
						// ���ÿ��Ӧ�ֵ�������
						int allotGroupNum = lastStudentlist.size() / anserNum
								+ 1;
						for (int j = 0; j < anserNum; j++) {
							Answergroup answergroup2 = answergroupslist2.get(j);
							int stuNumber = Integer.parseInt(answergroup2.getStuNumber());
							int stuNumber2 = stuNumber;
							int orders = Integer.parseInt(answergroup2.getStuNumber());
							F3_2:for (int k = stuNumber, l = stuNumber; k < stuNumber2+allotGroupNum; k++) {
								if (!lastStudentlist.isEmpty()) {
									Student student = lastStudentlist.get(0);
									String stuNo = student.getStuNo();
									// ��ȡѧ��ĳ���ͷ�����Ϣ
									List<Grouping> groupinglist = groupingDAO.findGroupingBygsNo(replyType, stuNo);
									// �жϸ�ѧ���Ƿ��Ѵ��ڸ����͵Ĵ�����
									if (groupinglist.isEmpty()) {
										k = l;
										Grouping grouping = new Grouping();
										// д���������Ϣ
										grouping.setGroupNo(replyType + stuNo);
										grouping.setReplyType(replyType);
										grouping.setReplyTypeName(replyTypeName);
										grouping.setStuNo(stuNo);
										grouping.setStuName(student.getStuName());
										grouping.setClassNo(student.getClassNo());
										grouping.setClassName(student.getClassName());
										grouping.setTutName(student.getTutName());
										grouping.setMajorName(student.getMajorName());
										grouping.setGroupingsNo(answergroup2.getGroupName());
										String Sorder = Integer.toString(orders + k);
										grouping.setOrders(Sorder);
										// grouping.setPlace("fuck");
										// ��Ӵ����������ݿ�
										groupingDAO.addGrouping(grouping);								
										l++;
										stuNumber++;
									} else {
										k = l;
									}
									lastStudentlist.remove(0);
								}else{
									break F3_2;
								}
							}
							answergroup2.setStuNumber(Integer.toString(stuNumber));
							answergroupService.updateAnswergroup(answergroup2);
						}
					}

				} else {
					// �����������ʱ����������
					// �жϴ��ڵ�������Ƿ���ڵ�����Ҫ�������anserNum
					if (answergrouplist.size() >= anserNum) {
						for (int j = 0; j < anserNum; j++) {
							// ������
							Answergroup answergroup = answergrouplist.get(j);
							// ��������Ѵ���ѧ����
							int stuNumber = Integer.parseInt(answergroup.getStuNumber());
							// �ж����ѧ�����Ƿ����� �˴���������peoNum
							if (stuNumber < peoNum) {
								F3_2: for (int k = stuNumber + 1, l = stuNumber + 1; k <= peoNum; k++) {
									if (!studentlist2.isEmpty()) {
										// ��ȡ�����
										int trandomt = ra.nextInt(studentlist2.size() + 0);
										// ���ѧ��
										Student student = studentlist2.get(trandomt);
										String stuNo = student.getStuNo();
										// ��ȡѧ��ĳ���ͷ�����Ϣ
										List<Grouping> groupinglist = groupingDAO.findGroupingBygsNo(replyType, stuNo);
										// �жϸ�ѧ���Ƿ��Ѵ��ڸ����͵Ĵ�����
										if (groupinglist.isEmpty()) {
											k = l;
											Grouping grouping = new Grouping();
											// д���������Ϣ
											grouping.setGroupNo(replyType+ stuNo);
											grouping.setReplyType(replyType);
											grouping.setReplyTypeName(replyTypeName);
											grouping.setStuNo(stuNo);
											grouping.setStuName(student.getStuName());
											grouping.setClassNo(student.getClassNo());
											grouping.setClassName(student.getClassName());
											grouping.setTutName(student.getTutName());
											grouping.setMajorName(student.getMajorName());
											grouping.setGroupingsNo(answergroup.getGroupName());
											grouping.setOrders(String.valueOf(k));
											// ��Ӵ����������ݿ�
											groupingDAO.addGrouping(grouping);
											l++;
											stuNumber++;
										} else {
											k = l;
										}
										studentlist2.remove(trandomt);
									} else {
										break F3_2;
									}
								}
							if (stuNumber > 0) {
								answergroup.setStuNumber(String.valueOf(stuNumber));
								// ��Ӵ����������ݿ�
								answergroupService.updateAnswergroup(answergroup);
							}
							}
						}
					} else {
						// ���Ѵ��ڵ������С����Ҫ�����ʱ������ⴴ�����
						// �������---
						SimpleDateFormat df = new SimpleDateFormat("yyyy");// ��ȡ��ǰϵͳʱ��
						Answergroup answergroups = new Answergroup();
						// �������ID
						String groupId =replyType+ df.format(new Date())+ class1.getInstituteNo() + class1.getMajorNo();
						answergroups.setInstituteName(instituteName);
						answergroups.setMajorName(majorName);
						for (int j = answergrouplist.size() + 1; j <= anserNum; j++) {
							answergroups.setReplyType(replyType);
							answergroups.setGroupId(groupId + j);
							answergroups.setGroupName("��" + j + "��");
							answergroups.setStuNumber(String.valueOf(0));
							answergroupService.addAnswergroup(answergroups);
						}
						for (int j = 0; j < anserNum; j++) {
							// ������
							List<Answergroup> answergrouplist2 = answergroupService.findAnswergroup1(replyType,instituteName, majorName);
							Answergroup answergroup = answergrouplist2.get(j);
							// ��������Ѵ���ѧ����
							int stuNumber = Integer.parseInt(answergroup.getStuNumber());
							int stuNumber2 = stuNumber;
							// �ж����ѧ�����Ƿ����� �˴���������peoNum
							if (stuNumber < peoNum) {
								F3_2: for (int k = stuNumber + 1, l = stuNumber + 1; k <= peoNum; k++) {
									if (!studentlist2.isEmpty()) {
										// ��ȡ�����
										int trandomt = ra.nextInt(studentlist2.size() + 0);
										// ���ѧ��
										Student student = studentlist2.get(trandomt);
										String stuNo = student.getStuNo();
										// ��ȡѧ��ĳ���ͷ�����Ϣ
										List<Grouping> groupinglist = groupingDAO.findGroupingBygsNo(replyType, stuNo);
										// �жϸ�ѧ���Ƿ��Ѵ��ڸ����͵Ĵ�����
										if (groupinglist.isEmpty()) {
											k = l;
											Grouping grouping = new Grouping();
											// д���������Ϣ
											grouping.setGroupNo(replyType+ stuNo);
											grouping.setReplyType(replyType);
											grouping.setReplyTypeName(replyTypeName);
											grouping.setStuNo(stuNo);
											grouping.setStuName(student.getStuName());
											grouping.setClassNo(student.getClassNo());
											grouping.setClassName(student.getClassName());
											grouping.setTutName(student.getTutName());
											grouping.setMajorName(student.getMajorName());
											grouping.setGroupingsNo(answergroup.getGroupName());
											grouping.setOrders(String.valueOf(k));
											// ��Ӵ����������ݿ�
											groupingDAO.addGrouping(grouping);
											l++;
											stuNumber++;
										} else {
											l = k;
										}
										studentlist2.remove(trandomt);
									} else {
										break F3_2;
									}
								}
								if (stuNumber > 0 && stuNumber > stuNumber2) {
									answergroup.setStuNumber(String.valueOf(stuNumber));
									// ��Ӵ����������ݿ�
									answergroupService.updateAnswergroup(answergroup);
								}

							}

						}

					}
				}

			}
		}

	}
	
/**
 * ɾ��
 */
	@SuppressWarnings("unused")
	public void deleteGrouping(Grouping grouping){
		Grouping grouping2 = groupingDAO.findGroupingByGroupNo(grouping.getGroupNo()).get(0);
		String replyType = grouping2.getGroupNo().substring(0, 3);
		Answergroup answergroup = answergroupService.fingAnswergroup4(replyType,grouping2.getMajorName(), grouping2.getGroupingsNo()).get(0);
		groupingDAO.deleteGrouping(grouping);
		int stuNumber = Integer.parseInt(answergroup.getStuNumber())-1;
		answergroup.setStuNumber(Integer.toString(stuNumber));
		answergroupService.updateAnswergroup(answergroup);
		
	}
	
	
	
/**
 * �޸�
 */
	public List<Grouping> updateGrouping(Grouping grouping){
		Grouping grouping2 = groupingDAO.findGroupingByGroupNo(grouping.getGroupNo()).get(0);
		grouping2.setGroupingsNo(grouping.getGroupingsNo());
		grouping2.setOrders(grouping.getOrders());
		grouping2.setPlaces(grouping.getPlaces());
		groupingDAO.updateGrouping(grouping2);
		List<Grouping> groupinglsit = new ArrayList<Grouping>();
		groupinglsit.add(grouping2);
		return groupinglsit;
	}

	
	
/**
 * ��ѯ����
 */
	public List<Grouping> findGroupingAll(){
		return groupingDAO.findGroupingAll();
	}
	
/**
 * ���ݱ��
 */
	public List<Grouping> findGroupingByGroupNo(String groupNo){
		return groupingDAO.findGroupingByGroupNo(groupNo);
	}
	
/**
 * ����ѧ�Ų�ѯ
 */
	public List<Grouping> findGroupingByStuNo(String stuNo){
		return groupingDAO.findGroupingByStuNo(stuNo);
	}
	
/**
 * ���ݴ������ ��ѧ�Ų�ѯ
 */
	public List<Grouping> findGroupingByStuNo(String replyType,String stuNo){
		return groupingDAO.findGroupingBygsNo(replyType, stuNo);
	}

	
/**
 * ���ݴ�����Ͳ�ѯ
 */
	public List<Grouping> findGroupingByReplyType(String replyType){
		return groupingDAO.findGroupingByReplyType(replyType);
	}
	
/**
 * ���ݴ�����͡��༶��ѯ
 */
	public List<Grouping> findGrouping1(String replyType ,String className){
		return groupingDAO.findGrouping1(replyType, className);
	}
	
/**
 * ���ݴ�����͡�רҵ��ѯ
 */
	public List<Grouping> findGrouping2(String replyType ,String majorName){
		return groupingDAO.findGrouping2(replyType, majorName);
	}
	
	

/**
 */
	public IClassService getClassService() {
		return classService;
	}

	public void setClassService(IClassService classService) {
		this.classService = classService;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public IGroupingDAO getGroupingDAO() {
		return groupingDAO;
	}

	public void setGroupingDAO(IGroupingDAO groupingDAO) {
		this.groupingDAO = groupingDAO;
	}

	public Answergroup getAnswergroup() {
		return answergroup;
	}

	public void setAnswergroup(Answergroup answergroup) {
		this.answergroup = answergroup;
	}

	public IAnswergroupService getAnswergroupService() {
		return answergroupService;
	}

	public void setAnswergroupService(IAnswergroupService answergroupService) {
		this.answergroupService = answergroupService;
	}

	public ITutorService getTutorService() {
		return tutorService;
	}

	public void setTutorService(ITutorService tutorService) {
		this.tutorService = tutorService;
	}

}
