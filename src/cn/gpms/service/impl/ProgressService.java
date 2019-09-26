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
 * ����ѧ�Ų�ѯ
 */
	public List<Progress> findProgressByStuNo(String stuNo){
		return progressDAO.findProgressByStuNo(stuNo);
	}
	
/**
 * ��ӽ�����
 */
	public void addProgress(Progress progress) throws Exception{
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		String progressId =null;
		if("���ⱨ��".equals(progress.getPhase())){
			progressId = "TPP";
		}else if("����".equals(progress.getPhase())){
			progressId = "PAP";
		}else if("��ҵ���".equals(progress.getPhase())){
			progressId = "WOP";
		}
		
		List<Progress> progresslist = progressDAO.findProgresses1(user12.getUserid(), progress.getPhase());
		//����progressId

		if(!progresslist.isEmpty()){
			int a = progresslist.size();
			progressId = progressId + user12.getUserid() + a;	
		}else{
			progressId = progressId + user12.getUserid() + "0";	
		}
		
		//����ת��
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = simpleFormat.parse(progress.getStartTime());
		Date estimatedTime = simpleFormat.parse(progress.getEstimatedTime());
		//��ȡ�ڵ�����������
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
