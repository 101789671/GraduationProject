package cn.gpms.vo;

/**
 * GpResults entity. @author MyEclipse Persistence Tools
 */

public class GpResults implements java.io.Serializable {

	// Fields

	private String resultsNo;
	private String reType;
	private String reTypeName;
	private String stuNo;
	private String stuName;
	private String className;
	private String tutNo;
	private String tutName;
	private String majorName;
	private String groupingsNo;
	private String orders;
	private String subject;
	private String results;
	private String comments;

	// Constructors

	/** default constructor */
	public GpResults() {
	}

	/** minimal constructor */
	public GpResults(String resultsNo) {
		this.resultsNo = resultsNo;
	}

	/** full constructor */
	public GpResults(String resultsNo, String reType, String reTypeName,
			String stuNo, String stuName, String className, String tutNo,
			String tutName, String majorName, String groupingsNo,
			String orders, String subject, String results, String comments) {
		this.resultsNo = resultsNo;
		this.reType = reType;
		this.reTypeName = reTypeName;
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.className = className;
		this.tutNo = tutNo;
		this.tutName = tutName;
		this.majorName = majorName;
		this.groupingsNo = groupingsNo;
		this.orders = orders;
		this.subject = subject;
		this.results = results;
		this.comments = comments;
	}

	// Property accessors

	public String getResultsNo() {
		return this.resultsNo;
	}

	public void setResultsNo(String resultsNo) {
		this.resultsNo = resultsNo;
	}

	public String getReType() {
		return this.reType;
	}

	public void setReType(String reType) {
		this.reType = reType;
	}

	public String getReTypeName() {
		return this.reTypeName;
	}

	public void setReTypeName(String reTypeName) {
		this.reTypeName = reTypeName;
	}

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

	public String getClassName() {
		return this.className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getGroupingsNo() {
		return this.groupingsNo;
	}

	public void setGroupingsNo(String groupingsNo) {
		this.groupingsNo = groupingsNo;
	}

	public String getOrders() {
		return this.orders;
	}

	public void setOrders(String orders) {
		this.orders = orders;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getResults() {
		return this.results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}