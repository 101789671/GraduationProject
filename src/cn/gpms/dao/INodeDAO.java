package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Node;

public interface INodeDAO {
	
	//��ӽڵ�
	public void addNode(Node node);
	
	//�޸Ľڵ�
	public void updateNode(Node node);
	
	//����ѧ�Ų�ѯ
	public List<Node> findNodeByStuNo(String stuNo);
	
	//���ݽڵ�id��ѯ
	public List<Node> findNodeByNodeId(String nodeId);

}
