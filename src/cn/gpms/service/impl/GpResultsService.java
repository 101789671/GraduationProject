package cn.gpms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.gpms.dao.IGpResultsDAO;
import cn.gpms.service.IGpResultsService;
import cn.gpms.service.IStudentService;
import cn.gpms.vo.GpResults;
import cn.gpms.vo.Student;

public class GpResultsService implements IGpResultsService{
	protected IGpResultsDAO gpResultsDAO;
	protected IStudentService studentService;
	
	
/**
 * ��ѯ����
 */
	public List<GpResults> findGpResultsAll(){
		return gpResultsDAO.findGpResultsAll();
	}
	
/**
 * ����ѧ�Ų�ѯ
 */
	public List<GpResults> findGpResultsByStuNo(String stuNo){
		return gpResultsDAO.findGpResultsByStuNo(stuNo);
	}
	
/**
 * ���ݱ�Ų�ѯ
 */
	public List<GpResults> findGpResultsByResultsNo(String resultsNo){
		return gpResultsDAO.findGpResultsByResultsNo(resultsNo);
	}
	
/**
 * ���ݳɼ����ͱ�Ų�ѯ
 */
	public List<GpResults> findGpResultsByReType(String reType){
		return gpResultsDAO.findGpResultsByReType(reType);
	}
	
/**
 * ���ݳɼ������� �� �༶����ѯ
 */
	public List<GpResults> findGpResults1(String reType, String className){
		return gpResultsDAO.findGpResults1(reType, className);
	}
	
/**
 * ���ݳɼ������� �� רҵ����ѯ
 */
	public List<GpResults> findGpResults2(String reType, String majorName){
		return gpResultsDAO.findGpResults2(reType, majorName);
	}
	
/**
 * ���ݳɼ������� �� ѧ�Ų�ѯ
 */
	public List<GpResults> findGpResults3(String reType, String stuNo){
		return gpResultsDAO.findGpResults3(reType, stuNo);
	}
	
/**
 * ���ݵ�ʦ�Ų�ѯ
 */
	public List<GpResults> findGpResultsByTutNo(String tutNo){
		return gpResultsDAO.findGpResultsByTutNo(tutNo);
	}
	
/**
 * ���ݳɼ������� �� ��ʦ�Ų�ѯ
 */
	public List<GpResults> findGpResults4(String reType,String tutNo){
		return gpResultsDAO.findGpResults4(reType, tutNo);
	}

/**
 * ���ݳɼ������� �� ��ʦ�š� �༶��ѯ
 */
	public List<GpResults> findGpResults5(String reType,String tutNo,String className){
		return gpResultsDAO.findGpResults5(reType, tutNo, className);
	}
	
	
/**
 * ��ӳɼ�
 */
	@SuppressWarnings("unchecked")
	public List<GpResults> addGpResults(GpResults gpResult){
		String reType = null;
		if(gpResult.getReTypeName().indexOf("����")!=-1){
			reType = "TPG";
		}else if (gpResult.getReTypeName().indexOf("����")!=-1) {
			reType = "PAG";
		}else if (gpResult.getReTypeName().indexOf("���")!=-1 || gpResult.getReTypeName().indexOf("��Ʒ")!=-1) {
			reType = "WOG";
		}
		gpResult.setReType(reType);
		if(gpResult.getResultsNo()==null){
		   String resultsNo = reType+gpResult.getStuNo();
		   List<GpResults> gpResultlist = gpResultsDAO.findGpResults3(reType, gpResult.getStuNo());
		   List<Student> studentlist = studentService.findStuBystuNo(gpResult.getStuNo());
		   if(!gpResultlist.isEmpty() && !studentlist.isEmpty()){
			   GpResults gpResults1 = gpResultlist.get(gpResultlist.size()-1);
			   String gNo = gpResults1.getResultsNo();
			   gNo = StringUtils.substringAfter(gNo, reType+gpResult.getStuNo());
			   int a = Integer.parseInt(gNo);
			   String relastNo = Integer.toString(a+1);
			   gpResult.setResultsNo(resultsNo+relastNo);
			   Student student = studentlist.get(0);
			   gpResult.setTutNo(student.getTutNo());
		   }else {
			   gpResult.setResultsNo(resultsNo+1);
		}
		}
		
		gpResultsDAO.addGpResults(gpResult);
		List<GpResults> gpResultslist = new ArrayList<GpResults>();
		gpResultslist.add(gpResult);
		return gpResultslist;
	}
	
/**
 * �޸�
 */
	public void updataGpResults(GpResults gpResults){
		gpResultsDAO.updataGpResults(gpResults);
	}
	
/**
 * ɾ��
 */
	public void deleteGpResults(GpResults gpResults){
		gpResultsDAO.deleteGpResults(gpResults);
	}
	
	

	public IGpResultsDAO getGpResultsDAO() {
		return gpResultsDAO;
	}

	public void setGpResultsDAO(IGpResultsDAO gpResultsDAO) {
		this.gpResultsDAO = gpResultsDAO;
	}

	public IStudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}

}
