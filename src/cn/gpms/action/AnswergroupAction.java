package cn.gpms.action;

import java.util.ArrayList;
import java.util.List;

import cn.gpms.service.IAnswergroupService;
import cn.gpms.service.IClassService;
import cn.gpms.vo.Answergroup;
import cn.gpms.vo.Class;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AnswergroupAction extends ActionSupport{
	
	private Answergroup answergroup;
	private Answergroup answergroup1;
	
	private List<Class> classList;
	private List<Class> classList1;
	private List<Answergroup> JSanswergroupList;
	private List<Answergroup> answergroupList;
	
	protected IAnswergroupService answergroupService;
	protected IClassService classService;

	public Answergroup getAnswergroup() {
		return answergroup;
	}
	
/**
 * É¾³ý
 */
	public String deleteAnswergrou(){
		if(answergroup.getGroupId()!=null && !"".equals(answergroup.getGroupId())){
			answergroupService.deleteAnswergroup(answergroup.getGroupId());
		}else {
			ActionContext.getContext().put("message", "É¾³ýÊ§°Ü£¡");
		}
		answergroup = answergroup1;
		listAnserGroup();
		return "toAnswerGroup";
	}
	
	
/**
 * ÐÞ¸Äanswergroup
 */
	public String updateAnswergroup(){
		List<Answergroup> answergrouplist = answergroupService.findAnswergroupByGroupID(answergroup.getGroupId());
		if(!answergrouplist.isEmpty()){
			Answergroup answergroup = answergrouplist.get(0);
			answergroup.setChargehandId(this.answergroup.getChargehandId());
			answergroup.setChargehandName(this.answergroup.getChargehandName());
			answergroup.setMember1id(this.answergroup.getMember1id());
			answergroup.setMember1name(this.answergroup.getMember1name());
			answergroup.setMember2id(this.answergroup.getMember2id());
			answergroup.setMember2name(this.answergroup.getMember2name());
			answergroup.setMember3id(this.answergroup.getMember3id());
			answergroup.setMember3name(this.answergroup.getMember3name());
			answergroup.setPlace(this.answergroup.getPlace());
			answergroupService.updateAnswergroup(answergroup);
			answergroupList.clear();
			answergroupList.add(answergroup);
		}else{
			ActionContext.getContext().put("message", "ÐÞ¸ÄÊ§°Ü£¡");
		}
		return "toAnswerGroup";
	}

/**
 * ²éÑ¯
 */
	public String listAnserGroup(){
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
		answergroup1 = answergroup;
		answergroup = new Answergroup();
		return "listAnswerGroup";
	}
	
/**
 * Ç°ÍùÐÞ¸Ä
 */
	public String toUpdateAnswergroup(){
		if(answergroup.getGroupId()!=null && !"".equals(answergroup.getGroupId())){
		answergroupList = answergroupService.findAnswergroupByGroupID(answergroup.getGroupId());
		}
		return "toUpdateAnswergroup";
	}
	
	
	
/**
 */
	public void setAnswergroup(Answergroup answergroup) {
		this.answergroup = answergroup;
	}

	public List<Answergroup> getAnswergroupList() {
		return answergroupList;
	}

	public void setAnswergroupList(List<Answergroup> answergroupList) {
		this.answergroupList = answergroupList;
	}

	public IAnswergroupService getAnswergroupService() {
		return answergroupService;
	}

	public void setAnswergroupService(IAnswergroupService answergroupService) {
		this.answergroupService = answergroupService;
	}

	public Answergroup getAnswergroup1() {
		return answergroup1;
	}

	public void setAnswergroup1(Answergroup answergroup1) {
		this.answergroup1 = answergroup1;
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

	public List<Answergroup> getJSanswergroupList() {
		return JSanswergroupList;
	}

	public void setJSanswergroupList(List<Answergroup> jSanswergroupList) {
		JSanswergroupList = jSanswergroupList;
	}

	public IClassService getClassService() {
		return classService;
	}

	public void setClassService(IClassService classService) {
		this.classService = classService;
	}

}
