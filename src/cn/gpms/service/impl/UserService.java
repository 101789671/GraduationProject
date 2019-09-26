package cn.gpms.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.gpms.dao.IUserDAO;
import cn.gpms.service.IUserService;
import cn.gpms.vo.User;

public class UserService implements IUserService {
	private User user;
	
	private IUserDAO userDAO;
/**
 * 登录
 */
	@SuppressWarnings("unchecked")
	public int login(User user) {
		List userlist = userDAO.find(user);
		if (!userlist.isEmpty()) {
			User user2 = (User) userlist.get(0);
			if(user.getPwd().equals(user2.getPwd())){
				return 2;
			}else{
				return 1;
			}
		}else{
			return 0;
		}
	}
	public void logOut() {
		Map session = ActionContext.getContext().getSession();
		session.clear();
	}

	// 添加用户
	public void addUser(User user) {
		user.setPwd("123456");
		userDAO.addUser(user);
	}

	// 根据userid查用户，返回User
	public List findUserById(String userid) {
		return userDAO.findUserById(userid);
	}

	// 删除用户
	public void delUser(User user) {
		userDAO.delUser(user);
	}
	
	//更新用户
	public void updateUser(String userid,String userName){
		User user = (User) userDAO.findUserById(userid).get(0);
		user.setUserName(userName);
		userDAO.updateUser(user);
	}
	
	//修改密码
	@SuppressWarnings("unchecked")
	public void updatePwd( String oldPwd,User user){
		 Map session = ActionContext.getContext().getSession();
		 User user12 = (User) session.get("user");
		 user12 = (User) userDAO.findUserById(user12.getUserid()).get(0);
		if(oldPwd.equals(user12.getPwd())){
			user12.setPwd(user.getPwd());
			userDAO.updateUser(user12);
			ActionContext.getContext().put("message", "修改成功！");
		}else{
			ActionContext.getContext().put("message", "原密码不正确！");
		}
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
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


}
