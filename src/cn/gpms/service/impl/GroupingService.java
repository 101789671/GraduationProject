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
 * 添加
 */
	//添加
	public void addGrouping(Grouping grouping){
		groupingDAO.addGrouping(grouping);
	}

	
/**
 * 自动分组
 */
	@SuppressWarnings("unchecked")
	public void answerGrouping(String replyType) {
		Random ra = new Random();
		List<Student> studentlist = studentService.findStuAll();// 查询所有学生
		List<Class> classlist = classService.disfindMajorName();// 去重查询出学院、专业
		if (!classlist.isEmpty()) {
			String replyTypeName = null;
			if("TPR".equals(replyType)){
				 replyTypeName = "开题答辩";
			}else if ("PAR".equals(replyType)) {
				 replyTypeName = "毕业答辩";
			}
			for (int i = 0; i < classlist.size(); i++) {
				Class class1 = classlist.get(i);
				String instituteName = class1.getInstituteName();
				String majorName = class1.getMajorName();

				int anserNum = 0;// 组数
				int peoNum = 0;// 每组人数
				// 查出所有属于majorName专业的学生
				List<Student> studentlist2 = studentService.findStuByMajorName(majorName);
				// 查出所有属于instituteName学院的导师
				List<Tutor> tutorlist2 = tutorService.findTuByIns(instituteName);
				// 获得学生总数
				int stuNums = studentlist2.size();
				// 得到组数
				anserNum = tutorlist2.size() / 3;
				// 得到每组人数
				peoNum = stuNums / anserNum + 1;
				// 获取学院=instituteName，专业=majorName的组别信息
				List<Answergroup> answergrouplist = answergroupService.findAnswergroup1(replyType,instituteName, majorName);
				// 判断是否存在组别 ，如果为空，则创建答辩组别
				if (answergrouplist.isEmpty()) {
					// 定义一个用于存最后一组学生的集合
					List<Student> lastStudentlist = new ArrayList<Student>();
					SimpleDateFormat df = new SimpleDateFormat("yyyy");// 获取当前系统时间
					Answergroup answergroup = new Answergroup();
					// 生成组别ID
					String groupId =replyType+ df.format(new Date())+ class1.getInstituteNo() + class1.getMajorNo();
					answergroup.setReplyType(replyType);
					answergroup.setInstituteName(instituteName);
					answergroup.setMajorName(majorName);
					// 获得最后一组的人数
					int lastGroupNum = stuNums - ((anserNum - 1) * peoNum);
					boolean lastGroupFound = false;
					// 判断最后一组人数是否达到构成一组的条件
					if (lastGroupNum < (0.75 * peoNum)) {
						// 如果达不到，将随机抽取lastGroupNum个学生,装到lastStudentlist中
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
						// 生成分组名
						String groupName = "第" + j + "组";
						answergroup.setGroupName(groupName);
						int stuNumber = 0;
						// 写入答辩分组信息
						F3_1: for (int k = 1, l = 1; k <= peoNum; k++) {
							if (!studentlist2.isEmpty()) {
								// 获取随机数
								int trandomt = ra.nextInt(studentlist2.size() + 0);
								// 获得学生
								Student student = studentlist2.get(trandomt);
								String stuNo = student.getStuNo();
								// 获取学生某类型分组信息
								List<Grouping> groupinglist = groupingDAO.findGroupingBygsNo(replyType, stuNo);
								// 判断该学生是否已存在该类型的答辩分组
								if (groupinglist.isEmpty()) {
									k = l;
									Grouping grouping = new Grouping();
									// 写入答辩分组信息
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
									// 添加答辩分组至数据库
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
									// 添加答辩组别至数据库
									answergroupService.addAnswergroup(answergroup);
								}
								break F2_1;

							}
						}
						answergroup.setStuNumber(String.valueOf(stuNumber));
						// 添加答辩组别至数据库
						answergroupService.addAnswergroup(answergroup);
					}
					// 无法构成最后一组时的处理：将剩余学生分到其他组
					if (lastGroupFound && lastStudentlist.size() > 0) {
						// 获取答辩组别信息
						List<Answergroup> answergroupslist2 = answergroupService.findAnswergroup1(replyType,instituteName, majorName);
						// 获得每组应分到的人数
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
									// 获取学生某类型分组信息
									List<Grouping> groupinglist = groupingDAO.findGroupingBygsNo(replyType, stuNo);
									// 判断该学生是否已存在该类型的答辩分组
									if (groupinglist.isEmpty()) {
										k = l;
										Grouping grouping = new Grouping();
										// 写入答辩分组信息
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
										// 添加答辩分组至数据库
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
					// 当答辩组别存在时。。。。。
					// 判断存在的组别数是否大于等于需要的组别数anserNum
					if (answergrouplist.size() >= anserNum) {
						for (int j = 0; j < anserNum; j++) {
							// 获得组别
							Answergroup answergroup = answergrouplist.get(j);
							// 获得组别的已存在学生数
							int stuNumber = Integer.parseInt(answergroup.getStuNumber());
							// 判断组别学生数是否满额 此处满额数是peoNum
							if (stuNumber < peoNum) {
								F3_2: for (int k = stuNumber + 1, l = stuNumber + 1; k <= peoNum; k++) {
									if (!studentlist2.isEmpty()) {
										// 获取随机数
										int trandomt = ra.nextInt(studentlist2.size() + 0);
										// 获得学生
										Student student = studentlist2.get(trandomt);
										String stuNo = student.getStuNo();
										// 获取学生某类型分组信息
										List<Grouping> groupinglist = groupingDAO.findGroupingBygsNo(replyType, stuNo);
										// 判断该学生是否已存在该类型的答辩分组
										if (groupinglist.isEmpty()) {
											k = l;
											Grouping grouping = new Grouping();
											// 写入答辩分组信息
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
											// 添加答辩分组至数据库
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
								// 添加答辩组别至数据库
								answergroupService.updateAnswergroup(answergroup);
							}
							}
						}
					} else {
						// 当已存在的组别数小于需要的组别时，需额外创建组别
						// 创建组别---
						SimpleDateFormat df = new SimpleDateFormat("yyyy");// 获取当前系统时间
						Answergroup answergroups = new Answergroup();
						// 生成组别ID
						String groupId =replyType+ df.format(new Date())+ class1.getInstituteNo() + class1.getMajorNo();
						answergroups.setInstituteName(instituteName);
						answergroups.setMajorName(majorName);
						for (int j = answergrouplist.size() + 1; j <= anserNum; j++) {
							answergroups.setReplyType(replyType);
							answergroups.setGroupId(groupId + j);
							answergroups.setGroupName("第" + j + "组");
							answergroups.setStuNumber(String.valueOf(0));
							answergroupService.addAnswergroup(answergroups);
						}
						for (int j = 0; j < anserNum; j++) {
							// 获得组别
							List<Answergroup> answergrouplist2 = answergroupService.findAnswergroup1(replyType,instituteName, majorName);
							Answergroup answergroup = answergrouplist2.get(j);
							// 获得组别的已存在学生数
							int stuNumber = Integer.parseInt(answergroup.getStuNumber());
							int stuNumber2 = stuNumber;
							// 判断组别学生数是否满额 此处满额数是peoNum
							if (stuNumber < peoNum) {
								F3_2: for (int k = stuNumber + 1, l = stuNumber + 1; k <= peoNum; k++) {
									if (!studentlist2.isEmpty()) {
										// 获取随机数
										int trandomt = ra.nextInt(studentlist2.size() + 0);
										// 获得学生
										Student student = studentlist2.get(trandomt);
										String stuNo = student.getStuNo();
										// 获取学生某类型分组信息
										List<Grouping> groupinglist = groupingDAO.findGroupingBygsNo(replyType, stuNo);
										// 判断该学生是否已存在该类型的答辩分组
										if (groupinglist.isEmpty()) {
											k = l;
											Grouping grouping = new Grouping();
											// 写入答辩分组信息
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
											// 添加答辩分组至数据库
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
									// 添加答辩组别至数据库
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
 * 删除
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
 * 修改
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
 * 查询所有
 */
	public List<Grouping> findGroupingAll(){
		return groupingDAO.findGroupingAll();
	}
	
/**
 * 根据编号
 */
	public List<Grouping> findGroupingByGroupNo(String groupNo){
		return groupingDAO.findGroupingByGroupNo(groupNo);
	}
	
/**
 * 根据学号查询
 */
	public List<Grouping> findGroupingByStuNo(String stuNo){
		return groupingDAO.findGroupingByStuNo(stuNo);
	}
	
/**
 * 根据答辩类型 、学号查询
 */
	public List<Grouping> findGroupingByStuNo(String replyType,String stuNo){
		return groupingDAO.findGroupingBygsNo(replyType, stuNo);
	}

	
/**
 * 根据答辩类型查询
 */
	public List<Grouping> findGroupingByReplyType(String replyType){
		return groupingDAO.findGroupingByReplyType(replyType);
	}
	
/**
 * 根据答辩类型、班级查询
 */
	public List<Grouping> findGrouping1(String replyType ,String className){
		return groupingDAO.findGrouping1(replyType, className);
	}
	
/**
 * 根据答辩类型、专业查询
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
