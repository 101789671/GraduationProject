package cn.gpms.vo;

/**
 * Subject entity. @author MyEclipse Persistence Tools
 */

public class Subject implements java.io.Serializable {

	// Fields

	private String subNo;
	private String tutNo;
	private String tutName;
	private String subjectName;
	private String updateTime;

	// Constructors

	/** default constructor */
	public Subject() {
	}

	/** minimal constructor */
	public Subject(String subNo, String tutNo) {
		this.subNo = subNo;
		this.tutNo = tutNo;
	}

	/** full constructor */
	public Subject(String subNo, String tutNo, String tutName,
			String subjectName, String updateTime) {
		this.subNo = subNo;
		this.tutNo = tutNo;
		this.tutName = tutName;
		this.subjectName = subjectName;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getSubNo() {
		return this.subNo;
	}

	public void setSubNo(String subNo) {
		this.subNo = subNo;
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

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}