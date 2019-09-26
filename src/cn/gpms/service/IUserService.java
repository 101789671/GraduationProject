package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.User;

public interface IUserService {
	//登录
	public int login(User user);

	public void logOut();

	// 添加用户
	public void addUser(User user);

	// 根据userid查用户，返回User
	public List findUserById(String userid);

	// 删除用户
	public void delUser(User user);
	
	//更新用户
	public void updateUser(String userid,String userName);
	
	//修改密码
	public void updatePwd(String oldPwd,User user);

}
