package cn.gpms.service;

import java.util.List;

import cn.gpms.form.StudentForm;
import cn.gpms.vo.Apply;
import cn.gpms.vo.Student;
import cn.gpms.vo.Tutor;
import cn.gpms.vo.User;

public interface IApplyService {

	// ����apID��ѯ������Ϣ
	public List findApplyByApID(String apId);

	// ����������ID���������ͱ�Ų�ѯ������Ϣ
	public List findApply5(String applicantID, String typeNumber);
	
	// ���ݱ�������ID���������ͱ�Ų�ѯ������Ϣ
	public List findApply6(String respondentId, String typeNumber);

	// ���������˱�Ų�ѯĳһ���͵�����
	public List findApplyByAID(String applicantID, String typeNumber);

	// ���������ˡ������롢 �������ͱ�Ų�ѯ��¼
	public List findApply3(String applicantId, String respondentId,String typeNumber);

	// ���ݱ�������ID��ѯ����״̬������
	public List findApplyByRID(String respondentId);

	// ���ݱ�������ID��ѯĳһ���͡�ĳһ״̬������
	public List findApply2(String respondentId, String apType, String apState);

	// �����������ͱ�š�״̬��ѯ �������루�磺�������룩
	public List findApply4(String typeNumber, String apState);
	
	//���������ˡ��������ͱ�š��������ݲ�ѯ����
	public List findApply1(String applicantId,String typeNumber,String apContent);
 
	// �����������ͱ�� ��ѯ����
	public List findApplyByNum(String typeNumber);

	// ɾ��
	public void deleteApply(Apply apply);

	// �������
	public void addApply(User user, Tutor tutor, Apply apply);

	// ����apID�޸�����״̬
	public void updtateApType(String apId, String apState);

	// ���ݱ�������ID��ĳһ���͡�ĳһ״̬�޸������״̬
	public void updateApType2(String applicantId, String typeNumber,
			String newapState, String oldapState);

	public List<Student> randomAllot(String stuNo);

	// ���봦�� �� ����
	public void allotApplys(String stuNo, String tutName);
}
