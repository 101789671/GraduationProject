package cn.gpms.action;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.gpms.service.IGpFileService;
import cn.gpms.vo.GpFile;


/**
 * �ļ�����
 * 1.��ʾ����Ҫ�����ļ����б�
 * 2.�ļ�����
 */
public class DownAction extends ActionSupport{
	private GpFile gpFile;
	private static  String SUCCESS = "a.docx";
	protected IGpFileService gpFileService;
	
    /**
     * 2.�ļ�����
     */
    //2.2.�����ύ��ҵ�񷽷�����struts.xml�����÷���stream��
    public String down() throws Exception{
        return "download";
    }
    //2.3.�����ļ����ķ���
    //getAttrInputStream�Զ��׼���getter����������attrInputStream��һ������
    public InputStream getAttrInputStream(){
    	GpFile gpFile = gpFileService.findGpFileByFileNo(this.gpFile.getFileNo()).get(0);
    	this.gpFile.setFileName(gpFile.getFileName());
    	
    	File fileLocation = new File(gpFile.getFilePath());
        InputStream l = ServletActionContext.getServletContext().getResourceAsStream("/upload/"+gpFile.getFileName());
		return l;
    }
    //2.4.������ʾ���ļ������������ʾ���ļ�����
    public String getDownFileName(){
        //��Ҫ�������ı���
    	String a = "��ҵ��ƹ���ϵͳ�������ʵ��-���ⱨ��.doc"; 
    	String fileName=null;
        try {
        	
            fileName = URLEncoder.encode(a,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
       }
    	
       return fileName;
    }
    
    
	public GpFile getGpFile() {
		return gpFile;
	}
	public void setGpFile(GpFile gpFile) {
		this.gpFile = gpFile;
	}
	public IGpFileService getGpFileService() {
		return gpFileService;
	}
	public void setGpFileService(IGpFileService gpFileService) {
		this.gpFileService = gpFileService;
	}
}