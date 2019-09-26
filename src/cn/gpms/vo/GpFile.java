package cn.gpms.vo;

/**
 * GpFile entity. @author MyEclipse Persistence Tools
 */

public class GpFile implements java.io.Serializable {

	// Fields

	private String fileNo;
	private String fileType;
	private String fileCategory;
	private String fileCatNum;
	private String fileName;
	private String filePath;
	private String uploaderId;
	private String uploaderName;
	private String reviewState;
	private String fileState;
	private String uploadTime;

	// Constructors

	/** default constructor */
	public GpFile() {
	}

	/** minimal constructor */
	public GpFile(String fileNo) {
		this.fileNo = fileNo;
	}

	/** full constructor */
	public GpFile(String fileNo, String fileType, String fileCategory,
			String fileCatNum, String fileName, String filePath,
			String uploaderId, String uploaderName, String reviewState,
			String fileState, String uploadTime) {
		this.fileNo = fileNo;
		this.fileType = fileType;
		this.fileCategory = fileCategory;
		this.fileCatNum = fileCatNum;
		this.fileName = fileName;
		this.filePath = filePath;
		this.uploaderId = uploaderId;
		this.uploaderName = uploaderName;
		this.reviewState = reviewState;
		this.fileState = fileState;
		this.uploadTime = uploadTime;
	}

	// Property accessors

	public String getFileNo() {
		return this.fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getFileType() {
		return this.fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileCategory() {
		return this.fileCategory;
	}

	public void setFileCategory(String fileCategory) {
		this.fileCategory = fileCategory;
	}

	public String getFileCatNum() {
		return this.fileCatNum;
	}

	public void setFileCatNum(String fileCatNum) {
		this.fileCatNum = fileCatNum;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getUploaderId() {
		return this.uploaderId;
	}

	public void setUploaderId(String uploaderId) {
		this.uploaderId = uploaderId;
	}

	public String getUploaderName() {
		return this.uploaderName;
	}

	public void setUploaderName(String uploaderName) {
		this.uploaderName = uploaderName;
	}

	public String getReviewState() {
		return this.reviewState;
	}

	public void setReviewState(String reviewState) {
		this.reviewState = reviewState;
	}

	public String getFileState() {
		return this.fileState;
	}

	public void setFileState(String fileState) {
		this.fileState = fileState;
	}

	public String getUploadTime() {
		return this.uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

}