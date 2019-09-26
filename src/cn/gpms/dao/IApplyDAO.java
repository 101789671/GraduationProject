package cn.gpms.dao;

import java.util.List;

import cn.gpms.vo.Apply;

public interface IApplyDAO {

	// ����apID��ѯ������Ϣ
	public List findApplyByApID(String apId);

	// ����������ID���������ͱ�Ų�ѯ������Ϣ
	public List findApply5(String applicantID, String typeNumber);
	
	// ���ݱ�������ID���������ͱ�Ų�ѯ������Ϣ
	public List findApply6(String respondentId, String typeNumber);

	// ���������˱�Ų�ѯĳһ���͵�����
	public List findApplyByAID(String applicantID, String apType);

	// ���������ˡ������롢 �������Ͳ�ѯ��¼
	public List findApply3(String applicantId, String respondentId,
			String apType);

	// ���������ˡ������롢 �������Ͳ�ѯ��¼
	public List findApply3_1(String applicantId, String typeNumber);
	
	//���������ˡ��������ͱ�š��������ݲ�ѯ����
	public List findApply1(String applicantId,String typeNumber,String apContent);

	// ���ݱ�������ID��ѯ����״̬������
	public List findApplyByRID(String respondentId);

	// ���ݱ�������ID��ѯĳһ���͡�ĳһ״̬������
	public List findApply2(String respondentId, String apType, String apState);

	// �����������ͱ�š�״̬��ѯ �������루�磺�������룩
	public List findApply4(String typeNumber, String apState);

	// �����������ͱ�� ��ѯ����
	public List findApplyByNum(String typeNumber);

	// ����apID�޸�����״̬
	public void updtateApply(Apply apply);

	// �������
	public void addApply(Apply apply);

	// ɾ��
	public void deleteApply(Apply apply);

}
