package cn.gpms.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.star.lib.connections.pipe.pipeAcceptor;

import cn.gpms.service.IClassService;
import cn.gpms.service.IGpResultsService;
import cn.gpms.vo.Class;
import cn.gpms.vo.GpResults;

public class GpResultsAction extends ActionSupport{
	private GpResults gpResults;
	private GpResults gpResults1;
	
	private List<GpResults> gpResultsList;
	private List<GpResults> JSgpResultsList;
	private List<Class> classList;
	private List<Class> classList1;
	
	protected IGpResultsService gpResultsService;
	protected IClassService classService;
	
	
/**	
 * Ìí¼Ó³É¼¨
 */
	public String addGpReluts(){
		gpResultsList = gpResultsService.addGpResults(gpResults);	
		gpResults = new GpResults();
		return "listGpResults";
		
	}
	
	
/**
 * ²éÑ¯³É¼¨
 */
	@SuppressWarnings("unchecked")
	public String listGpResults(){
		if (gpResults != null && gpResults.getReType() != null) {
			if (gpResults.getMajorName() == null&& gpResults.getClassName() == null&& gpResults.getStuNo() == null) {
				gpResultsList = gpResultsService.findGpResultsByReType(gpResults.getReType());
			} else if (gpResults.getMajorName() == null&& gpResults.getClassName() != null&& gpResults.getStuNo() == null) {
				gpResultsList = gpResultsService.findGpResults1(gpResults.getReType(), gpResults.getClassName());
			} else if (gpResults.getMajorName() != null&& gpResults.getClassName() == null&& gpResults.getStuNo() == null) {
				gpResultsList = gpResultsService.findGpResults2(gpResults.getReType(), gpResults.getMajorName());
			} else if (gpResults.getMajorName() == null&& gpResults.getClassName() == null&& gpResults.getStuNo() != null) {
				gpResultsList = gpResultsService.findGpResults3(gpResults.getReType(), gpResults.getStuNo());
			}
		} else {
			gpResultsList = gpResultsService.findGpResultsAll();
		}

		classList = classService.disfindInsAndclassName();
		classList1 = classService.disfindMajorName();
		this.gpResults1= gpResults;
		List<GpResults> JSGpResultlist = new ArrayList<GpResults>();
		JSGpResultlist.add(gpResults1);
		JSgpResultsList = JSGpResultlist;
		gpResults = new GpResults();
		return "listGpResults";
	}
	
/**
 * ÐÞ¸Ä³É¼¨
 */
	public String updateGpResults(){
		GpResults gpResults1 = new GpResults();
		List<GpResults> gpResultlist = gpResultsService.findGpResultsByResultsNo(gpResults.getResultsNo());
		if(!gpResultlist.isEmpty()){
			gpResults1 = gpResultlist.get(0);
			gpResults1.setGroupingsNo(gpResults.getGroupingsNo());
			gpResults1.setOrders(gpResults.getOrders());
			gpResults1.setSubject(gpResults.getSubject());
			gpResults1.setResults(gpResults.getResults());
			gpResults1.setComments(gpResults.getComments());
			gpResultsService.updataGpResults(gpResults1);
			gpResultlist.set(0, gpResults1);
			gpResultsList = gpResultlist;
		}else{
			ActionContext.getContext().put("message","ÐÞ¸ÄÊ§°Ü£¡");
		}
		gpResults = new GpResults();
		return "listGpResults";
	}
	
/**
 * É¾³ý
 */
	public String deleteGpResults(){
		if(gpResults!=null && gpResults.getResultsNo()!=null){
			gpResultsService.deleteGpResults(gpResults);
		}
		else{
			ActionContext.getContext().put("message","É¾³ýÊ§°Ü£¡");
		}
		gpResults = gpResults1;
		listGpResults();
		return "listGpResults";
	}
	
/**
 * ÅúÁ¿É¾³ý
 */
	public String batchDelGpResults(){
		String items = ServletActionContext.getRequest().getParameter("delitems"); 
		String Ero = "false";
		if (items != null) {
			String[] strs = items.split(",");
			for(int i=0;i<strs.length;i++){
				GpResults gpResults1 = new GpResults(); 
				gpResults1.setResultsNo(strs[i]);
				gpResultsService.deleteGpResults(gpResults1);
				}
			ActionContext.getContext().put("message", "ÅúÁ¿É¾³ýÊ§°Ü£¡");
		} else {
			ActionContext.getContext().put("message", "ÅúÁ¿É¾³ý³É¹¦£¡");
		}
		gpResults = gpResults1;
		return "listGpResults";
	}
	
	
/**
 */
	public GpResults getGpResults() {
		return gpResults;
	}

	public void setGpResults(GpResults gpResults) {
		this.gpResults = gpResults;
	}

	public List<GpResults> getGpResultsList() {
		return gpResultsList;
	}

	public void setGpResultsList(List<GpResults> gpResultsList) {
		this.gpResultsList = gpResultsList;
	}

	public IGpResultsService getGpResultsService() {
		return gpResultsService;
	}

	public void setGpResultsService(IGpResultsService gpResultsService) {
		this.gpResultsService = gpResultsService;
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


	public GpResults getGpResults1() {
		return gpResults1;
	}


	public void setGpResults1(GpResults gpResults1) {
		this.gpResults1 = gpResults1;
	}


	public List<GpResults> getJSgpResultsList() {
		return JSgpResultsList;
	}


	public void setJSgpResultsList(List<GpResults> jSgpResultsList) {
		JSgpResultsList = jSgpResultsList;
	}

}
