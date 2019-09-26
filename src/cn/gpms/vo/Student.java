package cn.gpms.vo;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private String stuNo;
	private String stuName;
	private String sex;
	private String classNo;
	private String className;
	private String majorNo;
	private String majorName;
	private String instituteNo;
	private String instituteName;
	private String tutNo;
	private String tutName;
	private String subject;
	private String updateTime;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(String stuNo) {
		this.stuNo = stuNo;
	}

	/** full constructor */
	public Student(String stuNo, String stuName, String sex, String classNo,
			String className, String majorNo, String majorName,
			String instituteNo, String instituteName, String tutNo,
			String tutName, String subject, String updateTime) {
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.sex = sex;
		this.classNo = classNo;
		this.className = className;
		this.majorNo = majorNo;
		this.majorName = majorName;
		this.instituteNo = instituteNo;
		this.instituteName = instituteName;
		this.tutNo = tutNo;
		this.tutName = tutName;
		this.subject = subject;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getStuNo() {
		return this.stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getStuName() {
		return this.stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getClassNo() {
		return this.classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMajorNo() {
		return this.majorNo;
	}

	public void setMajorNo(String majorNo) {
		this.majorNo = majorNo;
	}

	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getInstituteNo() {
		return this.instituteNo;
	}

	public void setInstituteNo(String instituteNo) {
		this.instituteNo = instituteNo;
	}

	public String getInstituteName() {
		return this.instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getTutNo() {
		return this.tutNo;
	}

	public void setTutNo(String tutNo) {
		this.tutNo = tutNo;
	}

	public String getTutName() {
		return this.tutName;
	}

	public void setTutName(String tutName) {
		this.tutName = tutName;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}