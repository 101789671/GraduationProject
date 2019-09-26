package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Node;

public interface INodeDAO {
	
	//添加节点
	public void addNode(Node node);
	
	//修改节点
	public void updateNode(Node node);
	
	//根据学号查询
	public List<Node> findNodeByStuNo(String stuNo);
	
	//根据节点id查询
	public List<Node> findNodeByNodeId(String nodeId);

}
