package cn.gpms.service.impl;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import cn.gpms.dao.INodeDAO;
import cn.gpms.dao.impl.NodeDAO;
import cn.gpms.service.INodeService;
import cn.gpms.vo.Node;
import cn.gpms.vo.User;

public class NodeService implements INodeService {
	protected INodeDAO nodeDAO;
	
	
/**
 * 添加/修改节点
 */
	public void addOrUpdate(Node node){
		List<Node> nodelist = nodeDAO.findNodeByNodeId(node.getNodeId());
		if(nodelist.isEmpty()){
			String progressid = node.getNodeId().substring(0, node.getNodeId().length()-2);
			node.setProgressId(progressid);		
			
			String nodePositionNum = node.getNodeId().substring(node.getNodeId().length()- 2, node.getNodeId().length());
			node.setNodePositionNum(nodePositionNum);
			
			Map session = ActionContext.getContext().getSession();
			User user12 = (User) session.get("user");
			node.setStuNo(user12.getUserid());		
			nodeDAO.addNode(node);
		}else {
			nodeDAO.updateNode(node);
		}
		
	}

/**
 * 根据学号查询
 */
	public List<Node> findNodeByStuNo(String stuNo){
		return nodeDAO.findNodeByStuNo(stuNo);
	}
	
	

public INodeDAO getNodeDAO() {
	return nodeDAO;
}

public void setNodeDAO(INodeDAO nodeDAO) {
	this.nodeDAO = nodeDAO;
}

}
