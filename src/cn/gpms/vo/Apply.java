package cn.gpms.vo;

/**
 * Apply entity. @author MyEclipse Persistence Tools
 */

public class Apply implements java.io.Serializable {

	// Fields

	private String apId;
	private String apType;
	private String typeNumber;
	private String apContent;
	private String applicant;
	private String applicantId;
	private String respondent;
	private String respondentId;
	private String respondentRole;
	private String apState;
	private String apTime;
	private String handleTime;

	// Constructors

	/** default constructor */
	public Apply() {
	}

	/** minimal constructor */
	public Apply(String apId) {
		this.apId = apId;
	}

	/** full constructor */
	public Apply(String apId, String apType, String typeNumber,
			String apContent, String applicant, String applicantId,
			String respondent, String respondentId, String respondentRole,
			String apState, String apTime, String handleTime) {
		this.apId = apId;
		this.apType = apType;
		this.typeNumber = typeNumber;
		this.apContent = apContent;
		this.applicant = applicant;
		this.applicantId = applicantId;
		this.respondent = respondent;
		this.respondentId = respondentId;
		this.respondentRole = respondentRole;
		this.apState = apState;
		this.apTime = apTime;
		this.handleTime = handleTime;
	}

	// Property accessors

	public String getApId() {
		return this.apId;
	}

	public void setApId(String apId) {
		this.apId = apId;
	}

	public String getApType() {
		return this.apType;
	}

	public void setApType(String apType) {
		this.apType = apType;
	}

	public String getTypeNumber() {
		return this.typeNumber;
	}

	public void setTypeNumber(String typeNumber) {
		this.typeNumber = typeNumber;
	}

	public String getApContent() {
		return this.apContent;
	}

	public void setApContent(String apContent) {
		this.apContent = apContent;
	}

	public String getApplicant() {
		return this.applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getApplicantId() {
		return this.applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public String getRespondent() {
		return this.respondent;
	}

	public void setRespondent(String respondent) {
		this.respondent = respondent;
	}

	public String getRespondentId() {
		return this.respondentId;
	}

	public void setRespondentId(String respondentId) {
		this.respondentId = respondentId;
	}

	public String getRespondentRole() {
		return this.respondentRole;
	}

	public void setRespondentRole(String respondentRole) {
		this.respondentRole = respondentRole;
	}

	public String getApState() {
		return this.apState;
	}

	public void setApState(String apState) {
		this.apState = apState;
	}

	public String getApTime() {
		return this.apTime;
	}

	public void setApTime(String apTime) {
		this.apTime = apTime;
	}

	public String getHandleTime() {
		return this.handleTime;
	}

	public void setHandleTime(String handleTime) {
		this.handleTime = handleTime;
	}

}