package cn.gpms.vo;

/**
 * TsControl entity. @author MyEclipse Persistence Tools
 */

public class TsControl implements java.io.Serializable {

	// Fields

	private String tutNo;
	private String tutName;
	private String permissions;

	// Constructors

	/** default constructor */
	public TsControl() {
	}

	/** minimal constructor */
	public TsControl(String tutNo) {
		this.tutNo = tutNo;
	}

	/** full constructor */
	public TsControl(String tutNo, String tutName, String permissions) {
		this.tutNo = tutNo;
		this.tutName = tutName;
		this.permissions = permissions;
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

	public String getPermissions() {
		return this.permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

}