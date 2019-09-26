package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Apply;

public interface IApplyDAO {

	// 根据apID查询申请信息
	public List findApplyByApID(String apId);

	// 根据申请人ID、申请类型编号查询申请信息
	public List findApply5(String applicantID, String typeNumber);
	
	// 根据被申请人ID、申请类型编号查询申请信息
	public List findApply6(String respondentId, String typeNumber);

	// 根据申请人编号查询某一类型的申请
	public List findApplyByAID(String applicantID, String apType);

	// 根据申请人、被申请、 申请类型查询记录
	public List findApply3(String applicantId, String respondentId,
			String apType);

	// 根据申请人、被申请、 申请类型查询记录
	public List findApply3_1(String applicantId, String typeNumber);
	
	//根据申请人、申请类型编号、申请内容查询申请
	public List findApply1(String applicantId,String typeNumber,String apContent);

	// 根据被申请人ID查询所有状态的申请
	public List findApplyByRID(String respondentId);

	// 根据被申请人ID查询某一类型、某一状态的申请
	public List findApply2(String respondentId, String apType, String apState);

	// 根据申请类型编号、状态查询 所有申请（如：分配申请）
	public List findApply4(String typeNumber, String apState);

	// 根据申请类型编号 查询申请
	public List findApplyByNum(String typeNumber);

	// 根据apID修改申请状态
	public void updtateApply(Apply apply);

	// 添加申请
	public void addApply(Apply apply);

	// 删除
	public void deleteApply(Apply apply);

}
