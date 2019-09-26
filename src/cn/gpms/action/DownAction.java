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
 * 文件下载
 * 1.显示所有要下载文件的列表
 * 2.文件下载
 */
public class DownAction extends ActionSupport{
	private GpFile gpFile;
	private static  String SUCCESS = "a.docx";
	protected IGpFileService gpFileService;
	
    /**
     * 2.文件下载
     */
    //2.2.下载提交的业务方法（在struts.xml中配置返回stream）
    public String down() throws Exception{
        return "download";
    }
    //2.3.返回文件流的方法
    //getAttrInputStream显而易见是getter方法，所以attrInputStream是一个属性
    public InputStream getAttrInputStream(){
    	GpFile gpFile = gpFileService.findGpFileByFileNo(this.gpFile.getFileNo()).get(0);
    	this.gpFile.setFileName(gpFile.getFileName());
    	
    	File fileLocation = new File(gpFile.getFilePath());
        InputStream l = ServletActionContext.getServletContext().getResourceAsStream("/upload/"+gpFile.getFileName());
		return l;
    }
    //2.4.下载显示的文件名（浏览器显示的文件名）
    public String getDownFileName(){
        //需要进行中文编码
    	String a = "毕业设计管理系统的设计与实现-开题报告.doc"; 
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