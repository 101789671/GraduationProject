package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.User;

public interface IUserService {
	//��¼
	public int login(User user);

	public void logOut();

	// ����û�
	public void addUser(User user);

	// ����userid���û�������User
	public List findUserById(String userid);

	// ɾ���û�
	public void delUser(User user);
	
	//�����û�
	public void updateUser(String userid,String userName);
	
	//�޸�����
	public void updatePwd(String oldPwd,User user);

}
