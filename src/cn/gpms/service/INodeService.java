package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.Node;

public interface INodeService {
	
	//��ӽڵ�
	public void addOrUpdate(Node node);
	
	//����ѧ�Ų�ѯ
	public List<Node> findNodeByStuNo(String stuNo);

}
