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
 * 查询所有
 */
	public List<GpResults> findGpResultsAll(){
		return gpResultsDAO.findGpResultsAll();
	}
	
/**
 * 根据学号查询
 */
	public List<GpResults> findGpResultsByStuNo(String stuNo){
		return gpResultsDAO.findGpResultsByStuNo(stuNo);
	}
	
/**
 * 根据编号查询
 */
	public List<GpResults> findGpResultsByResultsNo(String resultsNo){
		return gpResultsDAO.findGpResultsByResultsNo(resultsNo);
	}
	
/**
 * 根据成绩类型编号查询
 */
	public List<GpResults> findGpResultsByReType(String reType){
		return gpResultsDAO.findGpResultsByReType(reType);
	}
	
/**
 * 根据成绩类类编号 、 班级名查询
 */
	public List<GpResults> findGpResults1(String reType, String className){
		return gpResultsDAO.findGpResults1(reType, className);
	}
	
/**
 * 根据成绩类类编号 、 专业名查询
 */
	public List<GpResults> findGpResults2(String reType, String majorName){
		return gpResultsDAO.findGpResults2(reType, majorName);
	}
	
/**
 * 根据成绩类类编号 、 学号查询
 */
	public List<GpResults> findGpResults3(String reType, String stuNo){
		return gpResultsDAO.findGpResults3(reType, stuNo);
	}
	
/**
 * 根据导师号查询
 */
	public List<GpResults> findGpResultsByTutNo(String tutNo){
		return gpResultsDAO.findGpResultsByTutNo(tutNo);
	}
	
/**
 * 根据成绩类类编号 、 导师号查询
 */
	public List<GpResults> findGpResults4(String reType,String tutNo){
		return gpResultsDAO.findGpResults4(reType, tutNo);
	}

/**
 * 根据成绩类类编号 、 导师号、 班级查询
 */
	public List<GpResults> findGpResults5(String reType,String tutNo,String className){
		return gpResultsDAO.findGpResults5(reType, tutNo, className);
	}
	
	
/**
 * 添加成绩
 */
	@SuppressWarnings("unchecked")
	public List<GpResults> addGpResults(GpResults gpResult){
		String reType = null;
		if(gpResult.getReTypeName().indexOf("开题")!=-1){
			reType = "TPG";
		}else if (gpResult.getReTypeName().indexOf("论文")!=-1) {
			reType = "PAG";
		}else if (gpResult.getReTypeName().indexOf("设计")!=-1 || gpResult.getReTypeName().indexOf("作品")!=-1) {
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
 * 修改
 */
	public void updataGpResults(GpResults gpResults){
		gpResultsDAO.updataGpResults(gpResults);
	}
	
/**
 * 删除
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
