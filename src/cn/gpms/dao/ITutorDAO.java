package cn.gpms.dao;

import java.util.List;

import cn.gpms.form.TutorForm;
import cn.gpms.vo.Tutor;

public interface ITutorDAO {
	//添加导师
	public void addTutor(Tutor tutor);
	//根据导师名查导师，返回Tutor
	public List findTuByName(String tutName);
	
	
	//查询所有导师
	public List findTuAll();
	//根据tutNo查询导师,返回List
	public List findTutorByNo(String tutNo);
	//根据学院查询导师
	public List findTuByIns(String instituteName);
	
	
	//单个删除导师
	public void delTutor(Tutor tutor);
	//修改导师
	public void updateTutor(Tutor tutor);
	
	
	//根据No查导师，返货Tutor
	//public Tutor findTutorByNo(String tutNo);

	
}
