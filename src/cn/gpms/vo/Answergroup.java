package cn.gpms.vo;

/**
 * Answergroup entity. @author MyEclipse Persistence Tools
 */

public class Answergroup implements java.io.Serializable {

	// Fields

	private String groupId;
	private String replyType;
	private String instituteName;
	private String majorName;
	private String groupName;
	private String chargehandId;
	private String chargehandName;
	private String member1id;
	private String member1name;
	private String member2id;
	private String member2name;
	private String member3id;
	private String member3name;
	private String stuNumber;
	private String place;
	private String uploadTime;

	// Constructors

	/** default constructor */
	public Answergroup() {
	}

	/** minimal constructor */
	public Answergroup(String groupId, String replyType, String instituteName,
			String majorName, String groupName) {
		this.groupId = groupId;
		this.replyType = replyType;
		this.instituteName = instituteName;
		this.majorName = majorName;
		this.groupName = groupName;
	}

	/** full constructor */
	public Answergroup(String groupId, String replyType, String instituteName,
			String majorName, String groupName, String chargehandId,
			String chargehandName, String member1id, String member1name,
			String member2id, String member2name, String member3id,
			String member3name, String stuNumber, String place,
			String uploadTime) {
		this.groupId = groupId;
		this.replyType = replyType;
		this.instituteName = instituteName;
		this.majorName = majorName;
		this.groupName = groupName;
		this.chargehandId = chargehandId;
		this.chargehandName = chargehandName;
		this.member1id = member1id;
		this.member1name = member1name;
		this.member2id = member2id;
		this.member2name = member2name;
		this.member3id = member3id;
		this.member3name = member3name;
		this.stuNumber = stuNumber;
		this.place = place;
		this.uploadTime = uploadTime;
	}

	// Property accessors

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getReplyType() {
		return this.replyType;
	}

	public void setReplyType(String replyType) {
		this.replyType = replyType;
	}

	public String getInstituteName() {
		return this.instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getMajorName() {
		return this.majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getChargehandId() {
		return this.chargehandId;
	}

	public void setChargehandId(String chargehandId) {
		this.chargehandId = chargehandId;
	}

	public String getChargehandName() {
		return this.chargehandName;
	}

	public void setChargehandName(String chargehandName) {
		this.chargehandName = chargehandName;
	}

	public String getMember1id() {
		return this.member1id;
	}

	public void setMember1id(String member1id) {
		this.member1id = member1id;
	}

	public String getMember1name() {
		return this.member1name;
	}

	public void setMember1name(String member1name) {
		this.member1name = member1name;
	}

	public String getMember2id() {
		return this.member2id;
	}

	public void setMember2id(String member2id) {
		this.member2id = member2id;
	}

	public String getMember2name() {
		return this.member2name;
	}

	public void setMember2name(String member2name) {
		this.member2name = member2name;
	}

	public String getMember3id() {
		return this.member3id;
	}

	public void setMember3id(String member3id) {
		this.member3id = member3id;
	}

	public String getMember3name() {
		return this.member3name;
	}

	public void setMember3name(String member3name) {
		this.member3name = member3name;
	}

	public String getStuNumber() {
		return this.stuNumber;
	}

	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}

	public String getPlace() {
		return this.place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

}