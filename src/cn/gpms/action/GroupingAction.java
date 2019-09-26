package cn.gpms.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.gpms.service.IClassService;
import cn.gpms.service.IGroupingService;
import cn.gpms.vo.Class;
import cn.gpms.vo.GpResults;
import cn.gpms.vo.Grouping;
import cn.gpms.vo.Student;
import cn.gpms.vo.User;

public class GroupingAction {
	
	private Grouping grouping;
	
	private List<Grouping> groupingList;
	private List<Class> classList;
	private List<Class> classList1;
	private List<Class> classList2;
	private List<Grouping> groupingList1;
	private List<Grouping> JSgroupingList;
	
	protected IGroupingService groupingService;
	protected IClassService classService;
	
	
/**
 * 答辩分组
 */
	public String answerGrouping(){
		groupingService.answerGrouping(grouping.getReplyType());
		return "toAnswerGrouping";
	}
	
/**
 * 查询分组
 */
	@SuppressWarnings("unchecked")
	public String listGrouping() {
		
		if (grouping != null && grouping.getReplyType() != null) {
			if (grouping.getClassName() != null&& grouping.getMajorName() == null && grouping.getStuNo()==null) {
				groupingList = groupingService.findGrouping1(grouping.getReplyType(), grouping.getClassName());
			} else if ( grouping.getClassName() == null&& grouping.getMajorName() != null && grouping.getStuNo()==null) {
				groupingList = groupingService.findGrouping2(grouping.getReplyType(), grouping.getMajorName());
			} else if (grouping.getClassName() == null&& grouping.getMajorName() == null && grouping.getStuNo()==null) {
				groupingList = groupingService.findGroupingByReplyType(grouping.getReplyType());
			}else if (grouping.getClassName() == null&& grouping.getMajorName() == null && grouping.getStuNo()!=null) {
				groupingList = groupingService.findGroupingByStuNo(grouping.getReplyType(), grouping.getStuNo());
			}
		}else {
			groupingList = groupingService.findGroupingAll();
		}
		classList = classService.disfindInsAndclassName();
		classList1 = classService.disfindMajorName();
		List<Grouping> JSgroupinglist = new ArrayList<Grouping>();
		JSgroupinglist.add(grouping);
		JSgroupingList = JSgroupinglist;
		grouping = new Grouping();
		return "listGrouping";
	}
	
/**
 * 修改某学生分组信息
 */
	public String updateGrouping(){
		String Eor = null;
		if(!"".equals(grouping.getGroupNo()) && grouping.getGroupNo()!=null ){
		groupingList = groupingService.updateGrouping(grouping);
		Eor = "listGrouping";
		}else{
			ActionContext.getContext().put("message", "修改失败！");
			Eor="toUpdateGrouping";
		}
		return Eor;
	}
	

/**
 * 前往修改某学生分组信息
 */
	public String toUpdateGrouping(){
		groupingList = groupingService .findGroupingByGroupNo(grouping.getGroupNo());
		Grouping grouping = groupingList.get(0);
		groupingList1 = groupingService.findGrouping2(grouping.getReplyType(), grouping.getMajorName());
		return "toUpdateGrouping";
	}

/**
 * 删除
 */
	public String deleteGrouping(){
		if(!"".equals(grouping.getGroupNo())){
			groupingService.deleteGrouping(grouping);
		}else{
			ActionContext.getContext().put("message", "删除失败！");
		}
		return "listGrouping";
	}
	
/**
 * 批量删除
 */
	public String batchDelGrouping(){
		String items = ServletActionContext.getRequest().getParameter("delitems"); 
		String Ero = "false";
		if (items != null) {
			String[] strs = items.split(",");
			for(int i=0;i<strs.length;i++){
				Grouping grouping = new Grouping();
				grouping.setGroupNo(strs[i]);
				groupingService.deleteGrouping(grouping);
				}
			ActionContext.getContext().put("message", "批量删除成功！");
		} else {
			ActionContext.getContext().put("message", "批量删除失败！");
		}
		return "listGrouping";
	}
	
	
	
/**
 * 
 */
	public Grouping getGrouping() {
		return grouping;
	}

	public void setGrouping(Grouping grouping) {
		this.grouping = grouping;
	}

	public IGroupingService getGroupingService() {
		return groupingService;
	}

	public void setGroupingService(IGroupingService groupingService) {
		this.groupingService = groupingService;
	}

	public List<Grouping> getGroupingList() {
		return groupingList;
	}

	public void setGroupingList(List<Grouping> groupingList) {
		this.groupingList = groupingList;
	}

	public IClassService getClassService() {
		return classService;
	}

	public void setClassService(IClassService classService) {
		this.classService = classService;
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

	public List<Class> getClassList2() {
		return classList2;
	}

	public void setClassList2(List<Class> classList2) {
		this.classList2 = classList2;
	}

	public List<Grouping> getGroupingList1() {
		return groupingList1;
	}

	public void setGroupingList1(List<Grouping> groupingList1) {
		this.groupingList1 = groupingList1;
	}

	public List<Grouping> getJSgroupingList() {
		return JSgroupingList;
	}

	public void setJSgroupingList(List<Grouping> jSgroupingList) {
		JSgroupingList = jSgroupingList;
	}

}
