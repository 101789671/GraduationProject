package cn.gpms.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cn.gpms.service.IAnswergroupService;
import cn.gpms.service.IClassService;
import cn.gpms.service.IGpFileService;
import cn.gpms.service.IGpResultsService;
import cn.gpms.service.INoticeService;
import cn.gpms.service.ISwitchService;
import cn.gpms.vo.Answergroup;
import cn.gpms.vo.Class;
import cn.gpms.vo.GpFile;
import cn.gpms.vo.GpResults;
import cn.gpms.vo.Notice;
import cn.gpms.vo.Student;
import cn.gpms.vo.Switch;
import cn.gpms.vo.TsControl;
import cn.gpms.vo.Tutor;
import cn.gpms.vo.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport{
	private GpFile gpFile;
	private Student student;
	private Answergroup answergroup;
	private GpResults gpResults;
	private Notice notice;
	
	private List<GpFile> gpFileList;
	private List<Switch> sySwitchList;
	private List<Switch> paSwitchList;
	private List<Switch> tpSwitchList;
	private List<Switch> woSwitchList;
	private List<Class> classList;
	private List<Class> classList1;
	private List<Answergroup> answergroupList;
	private List<Answergroup> JSanswergroupList;
	private List<GpResults> gpResultsList;
	private List<Notice> noticeList;
	
	protected IGpFileService gpFileService;
	protected ISwitchService switchService;
	protected IAnswergroupService answergroupService;
	protected IClassService classService;
	protected IGpResultsService gpResultsService;
	protected INoticeService noticeService;
	
	
	
	
/**	
 * 前往学生毕设资料报告（概况）
 */
	public String toStudentGPDatumSurvey() {
		String Eor = null;
		String fileCatNum = gpFile.getFileCatNum();
		gpFileList = gpFileService.findGpFileByFileCatNum(fileCatNum);
		if ("TP".equals(fileCatNum)) {
			Eor = "toStudentTPSurvey";
		} else if ("PA".equals(fileCatNum)) {
			Eor = "toStudentPASurvey";
		} else if ("WO".equals(fileCatNum)) {
			Eor = "toStudentWOSurvey";
		}
		return Eor;
	}
/**
 * 查询某个学生的更多开题报告
 */
	public String someStudentMoreGPDatum() {
		String Eor = null;
		String fileCatNum = gpFile.getFileCatNum();
		gpFileList = gpFileService.findGpFileByUploaderId(student.getStuNo(),
				fileCatNum);
		if ("TP".equals(fileCatNum)) {
			Eor = "studentMoreTP";
		} else if ("PA".equals(fileCatNum)) {
			Eor = "studentMorePA";
		} else if ("WO".equals(fileCatNum)) {
			Eor = "studentMoreWO";
		}
		return Eor;
	}
/**
 * 所有学生的所有毕设资料
 */
	public String studentAllGPDatum() {
		String Eor = null;
		String fileCatNum = gpFile.getFileCatNum();
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		gpFileList = gpFileService.findGpFileByFileCatNum(fileCatNum);
		if ("TP".equals(fileCatNum)) {
			Eor = "studentMoreTP";
		} else if ("PA".equals(fileCatNum)) {
			Eor = "studentMorePA";
		} else if ("WO".equals(fileCatNum)) {
			Eor = "studentMoreWO";
		}
		return Eor;
	}
	
/**
 * 前往系统开关
 */
	public String toSystemSwitch(){
		sySwitchList = switchService.findSwitchBySwitchNumber("SYS");
		tpSwitchList = switchService.findSwitchBySwitchNumber("TPS");
		paSwitchList = switchService.findSwitchBySwitchNumber("PAS");
		woSwitchList = switchService.findSwitchBySwitchNumber("WOS");
		return "toSystemSwitch";
	}
	
/**
 * 更改开关
 */
	public void updateSwitch() {
		String items = ServletActionContext.getRequest().getParameter("delitems");
		if (items != null) {
			String[] strs = items.split(",");
			if("SYS".equals(strs[0])){
				switchService.updateSwitch(strs[0], strs[1]);
				switchService.updateSwitch("TPS", strs[1]);
				switchService.updateSwitch("PAS", strs[1]);
				switchService.updateSwitch("WOS", strs[1]);
			}else if ("TPS".equals(strs[0])||"PAS".equals(strs[0])||"WOS".equals(strs[0])) {
				switchService.updateSwitch(strs[0], strs[1]);	
			}
		}
	}

/**
 * 前往答辩分组
 */
	public String toAddGrouping(){
		return "toAddGrouping";
	}
	
/**
 *前往组别信息
 */
	@SuppressWarnings("unchecked")
	public String toAnswerGroup(){
		if(answergroup!=null && answergroup.getReplyType()!=null){
			if(answergroup.getMajorName()!=null && !"".equals(answergroup.getMajorName())){
				answergroupList = answergroupService.findAnswerGroup3(answergroup.getReplyType(), answergroup.getMajorName());
			}else{
				answergroupList = answergroupService.findAnswerGroupByReplyType(answergroup.getReplyType());
			}
			
		}else{
			answergroupList = answergroupService.findAnswergroupAll();
		}
		classList = classService.disfindInsAndclassName();
		classList1 = classService.disfindMajorName();
		List<Answergroup> JSanswergrouplist = new ArrayList<Answergroup>();
		JSanswergrouplist.add(answergroup);
		JSanswergroupList = JSanswergrouplist;
		answergroup = new Answergroup();
		return "toAnswerGroup";
	}
	
/**
 * 前往成绩录
 */
	public String toResultsAdd(){
		return "toResultsAdd";
	}

/**
 * 前往成绩显示管理
 */
	@SuppressWarnings("unchecked")
	public String listGpResults(){
		gpResultsList = gpResultsService.findGpResultsAll();
		classList = classService.disfindInsAndclassName();
		classList1 = classService.disfindMajorName();
		return "listGpResults";
	}
	
/**
 * 前往修改成绩
 */
	public String toUpdateGpResults(){
		gpResultsList = gpResultsService.findGpResultsByResultsNo(gpResults.getResultsNo());
		if(!gpResultsList.isEmpty()){
			GpResults gpResults1 = gpResultsList.get(0);
			String AnReplyType = null;
			if("TPG".equals(gpResults1.getReType())){
				AnReplyType="TPR";
			}else if ("PAG".equals(gpResults1.getReType()) || "WOG".equals(gpResults1.getReType())) {
				AnReplyType="GRR";
			}
			answergroupList = answergroupService.findAnswerGroup3(AnReplyType, gpResults1.getMajorName());
		}
		gpResults = new GpResults();
		return "toUpdateGpResults";
	}
	
/**
 * 前往发布公告
 */
	public String toReleaseNotice(){
		return "toReleaseNotice";
	}
	

		
/**
 */
	public GpFile getGpFile() {
		return gpFile;
	}
	public void setGpFile(GpFile gpFile) {
		this.gpFile = gpFile;
	}
	public List<GpFile> getGpFileList() {
		return gpFileList;
	}
	public void setGpFileList(List<GpFile> gpFileList) {
		this.gpFileList = gpFileList;
	}
	public IGpFileService getGpFileService() {
		return gpFileService;
	}
	public void setGpFileService(IGpFileService gpFileService) {
		this.gpFileService = gpFileService;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public List<Switch> getSySwitchList() {
		return sySwitchList;
	}
	public void setSySwitchList(List<Switch> sySwitchList) {
		this.sySwitchList = sySwitchList;
	}
	public List<Switch> getPaSwitchList() {
		return paSwitchList;
	}
	public void setPaSwitchList(List<Switch> paSwitchList) {
		this.paSwitchList = paSwitchList;
	}
	public List<Switch> getTpSwitchList() {
		return tpSwitchList;
	}
	public void setTpSwitchList(List<Switch> tpSwitchList) {
		this.tpSwitchList = tpSwitchList;
	}
	public List<Switch> getWoSwitchList() {
		return woSwitchList;
	}
	public void setWoSwitchList(List<Switch> woSwitchList) {
		this.woSwitchList = woSwitchList;
	}
	public ISwitchService getSwitchService() {
		return switchService;
	}
	public void setSwitchService(ISwitchService switchService) {
		this.switchService = switchService;
	}
	
	public IAnswergroupService getAnswergroupService() {
		return answergroupService;
	}
	public void setAnswergroupService(IAnswergroupService answergroupService) {
		this.answergroupService = answergroupService;
	}
	public List<Answergroup> getAnswergroupList() {
		return answergroupList;
	}
	public void setAnswergroupList(List<Answergroup> answergroupList) {
		this.answergroupList = answergroupList;
	}
	public Answergroup getAnswergroup() {
		return answergroup;
	}
	public void setAnswergroup(Answergroup answergroup) {
		this.answergroup = answergroup;
	}
	public List<Class> getClassList() {
		return classList;
	}
	public void setClassList(List<Class> classList) {
		this.classList = classList;
	}
	public List<Class> getClassList1() {
		return classList1;
	}
	public void setClassList1(List<Class> classList1) {
		this.classList1 = classList1;
	}
	public IClassService getClassService() {
		return classService;
	}
	public void setClassService(IClassService classService) {
		this.classService = classService;
	}
	public IGpResultsService getGpResultsService() {
		return gpResultsService;
	}
	public void setGpResultsService(IGpResultsService gpResultsService) {
		this.gpResultsService = gpResultsService;
	}
	public List<GpResults> getGpResultsList() {
		return gpResultsList;
	}
	public void setGpResultsList(List<GpResults> gpResultsList) {
		this.gpResultsList = gpResultsList;
	}
	public GpResults getGpResults() {
		return gpResults;
	}
	public void setGpResults(GpResults gpResults) {
		this.gpResults = gpResults;
	}
	public List<Answergroup> getJSanswergroupList() {
		return JSanswergroupList;
	}
	public void setJSanswergroupList(List<Answergroup> jSanswergroupList) {
		JSanswergroupList = jSanswergroupList;
	}
	public INoticeService getNoticeService() {
		return noticeService;
	}
	public void setNoticeService(INoticeService noticeService) {
		this.noticeService = noticeService;
	}
	public Notice getNotice() {
		return notice;
	}
	public void setNotice(Notice notice) {
		this.notice = notice;
	}
	public List<Notice> getNoticeList() {
		return noticeList;
	}
	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}
	

}
