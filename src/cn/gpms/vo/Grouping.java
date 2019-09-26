package cn.gpms.vo;

/**
 * Grouping entity. @author MyEclipse Persistence Tools
 */

public class Grouping implements java.io.Serializable {

	// Fields

	private String groupNo;
	private String replyType;
	private String replyTypeName;
	private String stuNo;
	private String stuName;
	private String classNo;
	private String className;
	private String tutName;
	private String majorName;
	private String groupingsNo;
	private String orders;
	private String places;

	// Constructors

	/** default constructor */
	public Grouping() {
	}

	/** minimal constructor */
	public Grouping(String groupNo) {
		this.groupNo = groupNo;
	}

	/** full constructor */
	public Grouping(String groupNo, String replyType, String replyTypeName,
			String stuNo, String stuName, String classNo, String className,
			String tutName, String majorName, String groupingsNo,
			String orders, String places) {
		this.groupNo = groupNo;
		this.replyType = replyType;
		this.replyTypeName = replyTypeName;
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.classNo = classNo;
		this.className = className;
		this.tutName = tutName;
		this.majorName = majorName;
		this.groupingsNo = groupingsNo;
		this.orders = orders;
		this.places = places;
	}

	// Property accessors

	public String getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getReplyType() {
		return this.replyType;
	}

	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}

	public String getReplyTypeName() {
		return this.replyTypeName;
	}

	public void setReplyTypeName(String replyTypeName) {
		this.replyTypeName = replyTypeName;
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

	public String getPlaces() {
		return this.places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}

}