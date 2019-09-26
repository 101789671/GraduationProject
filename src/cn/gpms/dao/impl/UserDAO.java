package cn.gpms.dao.impl;

import org.hibernate.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.opensymphony.xwork2.ActionContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.BaseDAO;
import cn.gpms.dao.IUserDAO;
import cn.gpms.vo.Class;
import cn.gpms.vo.User;

public class UserDAO extends Base2DAO implements IUserDAO {
	/**
	 * 用户登录
	 */
	public List find(User user) {
		String hql = "from User user where user.userid=? and user.role=?";
		Object[] values = {user.getUserid(),user.getRole()};
		return find(hql, values);
	}

	/**
	 * 根据userid查用户
	 */
	public List findUserById(String userid) {	
		String hql = "from User user where user.userid=?";	
		return find(hql, userid);
	}

	/**
	 * 用户添加
	 */
	public void addUser(User user) {
		save(user);
	}

	/**
	 * 删除用户
	 */
	public void delUser(User user) {
		delete(user);
	}
	/**
	 * 更新用户
	 */
	public void updateUser(User user){
		update(user);
	}

}


