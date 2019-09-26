package cn.gpms.dao.impl;

import java.util.List;

import cn.gpms.dao.Base2DAO;
import cn.gpms.dao.INodeDAO;
import cn.gpms.vo.Node;

public class NodeDAO extends Base2DAO implements INodeDAO {
	
/**
 * ��ӽڵ�
 */
	public void addNode(Node node){
		save(node);
	}
	
/**
 * �޸Ľڵ�
 */
	public void updateNode(Node node){
		update(node);
	}
	
/**
 * ����ѧ�Ų�ѯ
 */
	public List findNodeByStuNo(String stuNo){
		String hql = "from Node where stuNo=?";
		return find(hql, stuNo);
	}
	

/**
 * ���ݽڵ�id��ѯ
 */
	public List findNodeByNodeId(String nodeId){
		String hql = "from Node where nodeId=?";
		return find(hql, nodeId);
	}

}
