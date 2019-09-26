package cn.gpms.vo;

/**
 * Tutor entity. @author MyEclipse Persistence Tools
 */

public class Tutor implements java.io.Serializable {

	// Fields

	private String tutNo;
	private String tutName;
	private String sex;
	private String instituteName;
	private String position;
	private String updateTime;

	// Constructors

	/** default constructor */
	public Tutor() {
	}

	/** minimal constructor */
	public Tutor(String tutNo) {
		this.tutNo = tutNo;
	}

	/** full constructor */
	public Tutor(String tutNo, String tutName, String sex,
			String instituteName, String position, String updateTime) {
		this.tutNo = tutNo;
		this.tutName = tutName;
		this.sex = sex;
		this.instituteName = instituteName;
		this.position = position;
		this.updateTime = updateTime;
	}

	// Property accessors

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

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getInstituteName() {
		return this.instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}