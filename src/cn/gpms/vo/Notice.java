package cn.gpms.vo;

/**
 * Notice entity. @author MyEclipse Persistence Tools
 */

public class Notice implements java.io.Serializable {

	// Fields

	private String noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String publisherName;
	private String publisherId;
	private String position;
	private String pubObject;
	private String noticeTime;

	// Constructors

	/** default constructor */
	public Notice() {
	}

	/** minimal constructor */
	public Notice(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	/** full constructor */
	public Notice(String noticeNo, String noticeTitle, String noticeContent,
			String publisherName, String publisherId, String position,
			String pubObject, String noticeTime) {
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.publisherName = publisherName;
		this.publisherId = publisherId;
		this.position = position;
		this.pubObject = pubObject;
		this.noticeTime = noticeTime;
	}

	// Property accessors

	public String getNoticeNo() {
		return this.noticeNo;
	}

	public void setNoticeNo(String noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return this.noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return this.noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getPublisherName() {
		return this.publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherId() {
		return this.publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPubObject() {
		return this.pubObject;
	}

	public void setPubObject(String pubObject) {
		this.pubObject = pubObject;
	}

	public String getNoticeTime() {
		return this.noticeTime;
	}

	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}

}