package cn.gpms.service;

import java.util.List;

import cn.gpms.vo.Node;

public interface INodeService {
	
	//添加节点
	public void addOrUpdate(Node node);
	
	//根据学号查询
	public List<Node> findNodeByStuNo(String stuNo);

}
