package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.INodeDAO;
import cn.gpms.vo.Node;

public class NodeDAO extends Base2DAO implements INodeDAO {
	
/**
 * 添加节点
 */
	public void addNode(Node node){
		save(node);
	}
	
/**
 * 修改节点
 */
	public void updateNode(Node node){
		update(node);
	}
	
/**
 * 根据学号查询
 */
	public List findNodeByStuNo(String stuNo){
		String hql = "from Node where stuNo=?";
		return find(hql, stuNo);
	}
	

/**
 * 根据节点id查询
 */
	public List findNodeByNodeId(String nodeId){
		String hql = "from Node where nodeId=?";
		return find(hql, nodeId);
	}

}
