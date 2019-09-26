package cn.gpms.vo;

/**
 * Node entity. @author MyEclipse Persistence Tools
 */

public class Node implements java.io.Serializable {

	// Fields

	private String nodeId;
	private String progressId;
	private String stuNo;
	private String nodePositionNum;
	private String nodeDate;
	private String content;

	// Constructors

	/** default constructor */
	public Node() {
	}

	/** minimal constructor */
	public Node(String nodeId) {
		this.nodeId = nodeId;
	}

	/** full constructor */
	public Node(String nodeId, String progressId, String stuNo,
			String nodePositionNum, String nodeDate, String content) {
		this.nodeId = nodeId;
		this.progressId = progressId;
		this.stuNo = stuNo;
		this.nodePositionNum = nodePositionNum;
		this.nodeDate = nodeDate;
		this.content = content;
	}

	// Property accessors

	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getProgressId() {
		return this.progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	public String getStuNo() {
		return this.stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getNodePositionNum() {
		return this.nodePositionNum;
	}

	public void setNodePositionNum(String nodePositionNum) {
		this.nodePositionNum = nodePositionNum;
	}

	public String getNodeDate() {
		return this.nodeDate;
	}

	public void setNodeDate(String nodeDate) {
		this.nodeDate = nodeDate;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}