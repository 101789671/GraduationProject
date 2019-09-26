package cn.gpms.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;


import cn.gpms.dao.IProgressDAO;
import cn.gpms.service.IProgressService;
import cn.gpms.vo.Progress;
import cn.gpms.vo.User;

public class ProgressService implements IProgressService {
	protected IProgressDAO progressDAO;
	
	
/**
 * 根据学号查询
 */
	public List<Progress> findProgressByStuNo(String stuNo){
		return progressDAO.findProgressByStuNo(stuNo);
	}
	
/**
 * 添加进度条
 */
	public void addProgress(Progress progress) throws Exception{
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		String progressId =null;
		if("开题报告".equals(progress.getPhase())){
			progressId = "TPP";
		}else if("论文".equals(progress.getPhase())){
			progressId = "PAP";
		}else if("毕业设计".equals(progress.getPhase())){
			progressId = "WOP";
		}
		
		List<Progress> progresslist = progressDAO.findProgresses1(user12.getUserid(), progress.getPhase());
		//生成progressId

		if(!progresslist.isEmpty()){
			int a = progresslist.size();
			progressId = progressId + user12.getUserid() + a;	
		}else{
			progressId = progressId + user12.getUserid() + "0";	
		}
		
		//类型转换
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = simpleFormat.parse(progress.getStartTime());
		Date estimatedTime = simpleFormat.parse(progress.getEstimatedTime());
		//获取节点数（天数）
		long dateDiff = estimatedTime.getTime() - startTime.getTime();
		int nodeNumS = (int) (dateDiff/(1000 * 60 * 60 * 24))+1;
		progress.setNodeNumS(Integer.toString(nodeNumS));
		progress.setProgressId(progressId);
		progress.setStuNo(user12.getUserid());
		progressDAO.addProgress(progress);
	}
	

	
	

	public IProgressDAO getProgressDAO() {
		return progressDAO;
	}

	public void setProgressDAO(IProgressDAO progressDAO) {
		this.progressDAO = progressDAO;
	}

}
