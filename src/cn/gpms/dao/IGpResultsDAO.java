package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.GpResults;

public interface IGpResultsDAO {
	
	//添加成绩
	public void addGpResults(GpResults gpResults);
	
	//查询全部
	public List<GpResults> findGpResultsAll();
	
	//根据学号查询
	public List<GpResults> findGpResultsByStuNo(String stuNo);
	
	//根据编号查询
	public List<GpResults> findGpResultsByResultsNo(String resultsNo);
	
	//根据成绩类型编号查询
	public List<GpResults> findGpResultsByReType(String reType);
	
	//根据成绩类类编号 、 班级名查询
	public List<GpResults> findGpResults1(String reType, String className);
	
	//根据成绩类类编号 、 专业名查询
	public List<GpResults> findGpResults2(String reType, String majorName);
	
	//根据成绩类类编号 、 学号查询
	public List<GpResults> findGpResults3(String reType, String stuNo);
	
	//根据导师号查询
	public List<GpResults> findGpResultsByTutNo(String tutNo);
	
	//根据成绩类类编号 、 导师号查询
	public List<GpResults> findGpResults4(String reType,String tutNo);
	
	//根据成绩类类编号 、 导师号、 班级查询
	public List<GpResults> findGpResults5(String reType,String tutNo,String className);
	
	//修改
	public void updataGpResults(GpResults gpResults);
	
	//删除
	public void deleteGpResults(GpResults gpResults);

}
