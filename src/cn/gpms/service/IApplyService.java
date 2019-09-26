package cn.gpms.service;

import java.util.List;

import cn.gpms.form.StudentForm;
import cn.gpms.vo.Apply;
import cn.gpms.vo.Student;
import cn.gpms.vo.Tutor;
import cn.gpms.vo.User;

public interface IApplyService {

	// 根据apID查询申请信息
	public List findApplyByApID(String apId);

	// 根据申请人ID、申请类型编号查询申请信息
	public List findApply5(String applicantID, String typeNumber);
	
	// 根据被申请人ID、申请类型编号查询申请信息
	public List findApply6(String respondentId, String typeNumber);

	// 根据申请人编号查询某一类型的申请
	public List findApplyByAID(String applicantID, String typeNumber);

	// 根据申请人、被申请、 申请类型编号查询记录
	public List findApply3(String applicantId, String respondentId,String typeNumber);

	// 根据被申请人ID查询所有状态的申请
	public List findApplyByRID(String respondentId);

	// 根据被申请人ID查询某一类型、某一状态的申请
	public List findApply2(String respondentId, String apType, String apState);

	// 根据申请类型编号、状态查询 所有申请（如：分配申请）
	public List findApply4(String typeNumber, String apState);
	
	//根据申请人、申请类型编号、申请内容查询申请
	public List findApply1(String applicantId,String typeNumber,String apContent);
 
	// 根据申请类型编号 查询申请
	public List findApplyByNum(String typeNumber);

	// 删除
	public void deleteApply(Apply apply);

	// 添加申请
	public void addApply(User user, Tutor tutor, Apply apply);

	// 根据apID修改申请状态
	public void updtateApType(String apId, String apState);

	// 根据被申请人ID、某一类型、某一状态修改申请的状态
	public void updateApType2(String applicantId, String typeNumber,
			String newapState, String oldapState);

	public List<Student> randomAllot(String stuNo);

	// 申请处理 主 方法
	public void allotApplys(String stuNo, String tutName);
}
