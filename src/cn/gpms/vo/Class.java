package cn.gpms.vo;

/**
 * Class entity. @author MyEclipse Persistence Tools
 */

public class Class implements java.io.Serializable {

	// Fields

	private String classNo;
	private String instituteName;
	private String classtuName;
	private String instituteNo;
	private String majorNo;
	private String majorName;

	// Constructors

	/** default constructor */
	public Class() {
	}

	/** minimal constructor */
	public Class(String classNo) {
		this.classNo = classNo;
	}

	/** full constructor */
	public Class(String classNo, String instituteName, String classtuName,
			String instituteNo, String majorNo, String majorName) {
		this.classNo = classNo;
		this.instituteName = instituteName;
		this.classtuName = classtuName;
		this.instituteNo = instituteNo;
		this.majorNo = majorNo;
		this.majorName = majorName;
	}

	// Property accessors

	public String getClassNo() {
		return this.classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public String getInstituteName() {
		return this.instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getClasstuName() {
		return this.classtuName;
	}

	public void setClasstuName(String classtuName) {
		this.classtuName = classtuName;
	}

	public String getInstituteNo() {
		return this.instituteNo;
	}

	public void setInstituteNo(String instituteNo) {
		this.instituteNo = instituteNo;
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

}