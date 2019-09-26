package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.User;

public interface IUserDAO {
	//用户登录
	public List find(User user);

	// 添加用户
	public void addUser(User user);

	// 根据userid查用户，返回User
	public List findUserById(String userid);
	
	//更新用户
	public void updateUser(User user);

	// 删除用户
	public void delUser(User user);

}
