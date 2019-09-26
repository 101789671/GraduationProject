package cn.gpms.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import cn.gpms.service.IStudentService;
import cn.gpms.service.ISwitchService;
import cn.gpms.service.IUserService;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import cn.gpms.vo.Student;
import cn.gpms.vo.Switch;
import cn.gpms.vo.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private User user;
	private String oldPwd;
	
	private List<User> userList;
	private List<Student> studentList;

	protected IUserService userService;
	protected ISwitchService switchService;
	protected IStudentService studentService;
	
/**
 * 登录
 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String login() throws Exception {
		String result = null;
		Switch switch1 = switchService.findSwitchBySwitchNumber("SYS").get(0);
		if ("T".equals(switch1.getSwitchState())||"admin".equals(user.getRole())) {
			String user_yzm = ServletActionContext.getRequest().getParameter("yzcode");
			String key_yzm = (String) getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
			if (user != null && key_yzm != null && user_yzm != null) {
				if (key_yzm.equals(user_yzm.toLowerCase())) {
					int state = userService.login(user);
					if (state == 0) {
						ActionContext.getContext().put("message", "用户不存在！");
						result = "error";
					} else if (state == 1) {
						ActionContext.getContext().put("message", "账号密码错误！");
						result = "error";
					} else if (state == 2) {
						user = (User) userService.findUserById(user.getUserid()).get(0);
						Map session = ActionContext.getContext().getSession();
						session.put("user", user);
						result = user.getRole();
						if("student".equals(result)){
							studentList = studentService.findStuBystuNo(user.getUserid());
						}
					}
				} else {
					ActionContext.getContext().put("message", "验证码错误！");
					result = "error";
				}
			} else {
				ActionContext.getContext().put("message", "请先登录！");
				result = "error";
			}
		} else {
			ActionContext.getContext().put("message", "系统暂未开放！");
			result = "error";
		}

		return result;

	}
	
	/**
	 * 用户退出
	 */
	public String logout() throws Exception {
		userService.logOut();
		System.out.println("用户退出");
		return "logout";
	}
	
/**
 * 前往密码修改
 */	public String toUpdatePwd(){
	 Map session = ActionContext.getContext().getSession();
	 User user12 = (User) session.get("user");
	 if(user12!=null){
		 List<User> userlist = new ArrayList<User>();
		 userlist.add(user12);
		 userList = userlist;
	 }
	return "toUpdatePwd";
	 
 }
 
/**
 * 修改密码
 */
   public String updatePwd(){
	userService.updatePwd(oldPwd,user);
	return "toUpdatePwd";
   }
   
	
	
	private HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public ISwitchService getSwitchService() {
		return switchService;
	}

	public void setSwitchService(ISwitchService switchService) {
		this.switchService = switchService;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

}
