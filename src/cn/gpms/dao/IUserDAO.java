package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.User;

public interface IUserDAO {
	//�û���¼
	public List find(User user);

	// ����û�
	public void addUser(User user);

	// ����userid���û�������User
	public List findUserById(String userid);
	
	//�����û�
	public void updateUser(User user);

	// ɾ���û�
	public void delUser(User user);

}
