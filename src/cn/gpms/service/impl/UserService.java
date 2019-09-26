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
 * ��¼
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

	// ����û�
	public void addUser(User user) {
		user.setPwd("123456");
		userDAO.addUser(user);
	}

	// ����userid���û�������User
	public List findUserById(String userid) {
		return userDAO.findUserById(userid);
	}

	// ɾ���û�
	public void delUser(User user) {
		userDAO.delUser(user);
	}
	
	//�����û�
	public void updateUser(String userid,String userName){
		User user = (User) userDAO.findUserById(userid).get(0);
		user.setUserName(userName);
		userDAO.updateUser(user);
	}
	
	//�޸�����
	@SuppressWarnings("unchecked")
	public void updatePwd( String oldPwd,User user){
		 Map session = ActionContext.getContext().getSession();
		 User user12 = (User) session.get("user");
		 user12 = (User) userDAO.findUserById(user12.getUserid()).get(0);
		if(oldPwd.equals(user12.getPwd())){
			user12.setPwd(user.getPwd());
			userDAO.updateUser(user12);
			ActionContext.getContext().put("message", "�޸ĳɹ���");
		}else{
			ActionContext.getContext().put("message", "ԭ���벻��ȷ��");
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
