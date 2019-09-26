package cn.gpms.vo;

/**
 * Progress entity. @author MyEclipse Persistence Tools
 */

public class Progress implements java.io.Serializable {

	// Fields

	private String progressId;
	private String progressName;
	private String stuNo;
	private String phase;
	private String startTime;
	private String estimatedTime;
	private String actualTime;
	private String nodeNumS;

	// Constructors

	/** default constructor */
	public Progress() {
	}

	/** minimal constructor */
	public Progress(String progressId, String stuNo) {
		this.progressId = progressId;
		this.stuNo = stuNo;
	}

	/** full constructor */
	public Progress(String progressId, String progressName, String stuNo,
			String phase, String startTime, String estimatedTime,
			String actualTime, String nodeNumS) {
		this.progressId = progressId;
		this.progressName = progressName;
		this.stuNo = stuNo;
		this.phase = phase;
		this.startTime = startTime;
		this.estimatedTime = estimatedTime;
		this.actualTime = actualTime;
		this.nodeNumS = nodeNumS;
	}

	// Property accessors

	public String getProgressId() {
		return this.progressId;
	}

	public void setProgressId(String progressId) {
		this.progressId = progressId;
	}

	public String getProgressName() {
		return this.progressName;
	}

	public void setProgressName(String progressName) {
		this.progressName = progressName;
	}

	public String getStuNo() {
		return this.stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}

	public String getPhase() {
		return this.phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEstimatedTime() {
		return this.estimatedTime;
	}

	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public String getActualTime() {
		return this.actualTime;
	}

	public void setActualTime(String actualTime) {
		this.actualTime = actualTime;
	}

	public String getNodeNumS() {
		return this.nodeNumS;
	}

	public void setNodeNumS(String nodeNumS) {
		this.nodeNumS = nodeNumS;
	}

}