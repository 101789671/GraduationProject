package cn.gpms.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.opensymphony.xwork2.ActionContext;

import cn.gpms.vo.User;

public class publicUtils {
	
	private static final int environment = 1;// ���� 1��windows 2:linux  
	private String fileString;//Դ�ļ���·��	
	private String outputPath = "F:/upload";// ���·�� ����������þ������Ĭ�ϵ�λ�� (��Դ�ļ�λ����ͬ)
	private String openoffice_home = "C:/Program Files (x86)/OpenOffice 4";//������OpenOffice�İ�װĿ¼
	private String pdf2swf_home = "C:/Program Files (x86)/SWFTools/pdf2swf.exe ";//pdf2swf�İ�װ·��
	private String fileName;
	private File officeFile;//office�ļ�
	private File pdfFile;//ת�ɵ�pdf�ļ�
	private File swfFile;//ת�ɵ�swf�ļ�

	
	public User getSessionUser(){
		Map session = ActionContext.getContext().getSession();
		User user12 = (User) session.get("user");
		return user12;
	}
	
	
	public publicUtils(String fileString) {  
		//this.swfFile = swfFile;
		ini(fileString);  
	}  
	
	/**
	 * �ڵ���ת�����ʱ�����·����OpenOffice�İ�װ·����pdf2swf��·�������úã���·����һ������ֱ�������������ø�����
	 * @param fileString
	 * @param outputPath
	 * @param OpenOffice_Home
	 * @param pdf2swf_home
	 */
	public publicUtils(String fileString,String outputPath,String openoffice_home,String pdf2swf_home){
		this.outputPath = outputPath;
		this.openoffice_home = openoffice_home;
		this.pdf2swf_home = pdf2swf_home;
		ini(fileString);
	}
	
	/** 
	* ��������file 
	*  
	* @param fileString 
	*/  
	public void setFile(String fileString) {  
		ini(fileString);  
	}  
	
	/** 
	* ��ʼ�� 
	*  
	* @param fileString 
	*/  
	private void ini(String fileString) {  
		this.fileString = fileString; 
		fileName = fileString.substring(0, fileString.lastIndexOf("."));  
		officeFile = new File(fileString); 
		pdfFile = new File(fileName + ".pdf");  
		setOutputPath(outputPath);
		
	}  
	
	/** 
	* תΪPDF 
	*  
	* @param file 
	*/  
	private void doc2pdf() throws Exception {  
		if (officeFile.exists()) {  
			if (!pdfFile.exists()) {  
				
				// ������ļ��ж�ȡ��URL��ַ���һ���ַ����� '\'�������'\'  
	            if (openoffice_home.charAt(openoffice_home.length() - 1) != '\\') {  
	            	openoffice_home += "\\";  
	            }  
	            
	            // ʹ��java����OpenOffice�ķ��� 
	            String command = openoffice_home  
	                    + "program\\soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\"";  
	            Process pro = Runtime.getRuntime().exec(command);
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);  
				try {  
					connection.connect();  
					DocumentConverter converter = new OpenOfficeDocumentConverter(  
					connection);  
					converter.convert(officeFile, pdfFile);  
					// close the connection  
					connection.disconnect();  
					System.out.println("****pdfת���ɹ���PDF�����" + pdfFile.getPath() + "****");  
				} catch (java.net.ConnectException e) {  
					e.printStackTrace();  
					System.out.println("****swfת�����쳣��openoffice����δ������****");  
					throw e;  
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {  
					e.printStackTrace();  
					System.out.println("****swfת�����쳣����ȡת���ļ�ʧ��****");  
					throw e;  
				} catch (Exception e) {  
					e.printStackTrace();  
					throw e;  
				}  
				} else {  
				System.out.println("****�Ѿ�ת��Ϊpdf������Ҫ�ٽ���ת��****");  
				}  
		} else {  
			System.out.println("****swfת�����쳣����Ҫת�����ĵ������ڣ��޷�ת��****");  
		}  
	}  
	
	/** 
	* ת���� swf 
	*/  
	private void pdf2swf() throws Exception {  
		Runtime r = Runtime.getRuntime();  
		if (!swfFile.exists()) {  
			if (pdfFile.exists()) {  
				if (environment == 1) {// windows��������  
					try { 
						//���µĲ�����-G -s poly2bitmap�ǳ���Ҫ���ر�������office�ļ����ļ��ر�����˵ͼ���ر�������������޷�תΪswf�ļ�����������Է�ֹ��
						//���������ο��������ϡ����������ή��ת��Ч�ʣ�������������������жϣ����ļ�����ĳ����С�ټ���������������֮���üӣ����ת��Ч��
						Process p = r.exec(pdf2swf_home + pdfFile.getPath() + " -o "+ swfFile.getPath() + " -T 9 -G -s poly2bitmap");  
						System.out.print(loadStream(p.getInputStream()));  
						System.err.print(loadStream(p.getErrorStream()));  
						System.out.print(loadStream(p.getInputStream()));  
						System.err.println("****swfת���ɹ����ļ������"  
						+ swfFile.getPath() + "****");  
						if (pdfFile.exists()) {  
							pdfFile.delete();  
						}  
					} catch (IOException e) {  
						e.printStackTrace();  
						throw e;  
					}  
				} else if (environment == 2) {// linux��������  
					try {  
						Process p = r.exec("pdf2swf " + pdfFile.getPath()  
						+ " -o " + swfFile.getPath() + " -T 9");  
						System.out.print(loadStream(p.getInputStream()));  
						System.err.print(loadStream(p.getErrorStream()));  
						System.err.println("****swfת���ɹ����ļ������"  
						+ swfFile.getPath() + "****");  
						if (pdfFile.exists()) {  
							pdfFile.delete();  
						}  
					} catch (Exception e) {  
						e.printStackTrace();  
						throw e;  
					}  
				}  
			} else {  
				System.out.println("****pdf������,�޷�ת��****");  
			}  
		} else {  
			System.out.println("****swf�Ѿ����ڲ���Ҫת��****");  
		}  
	}  
	
	static String loadStream(InputStream in) throws IOException {  
	
		int ptr = 0;  
		in = new BufferedInputStream(in);  
		StringBuffer buffer = new StringBuffer();  
		
		while ((ptr = in.read()) != -1) {  
			buffer.append((char) ptr);  
		}  
		
		return buffer.toString();  
	}  
	
	/** 
	* ת�������� 
	*/  
	public boolean conver() {  
	
		if (swfFile.exists()) {  
			System.out.println("****swfת������ʼ���������ļ��Ѿ�ת��Ϊswf****");  
			return true;  
		}  
		
		if (environment == 1) {  
			System.out.println("****swfת������ʼ��������ǰ�������л���windows****");  
		} else {  
			System.out.println("****swfת������ʼ��������ǰ�������л���linux****");  
		}  
		try {  
			doc2pdf();  
			pdf2swf();  
		} catch (Exception e) {  
			e.printStackTrace();  
			return false;  
		}  
		
		if (swfFile.exists()) {  
			return true;  
		} else {  
			return false;  
		}  
	}  
	
	/** 
	* �����ļ�·�� 
	*  
	* @param s 
	*/  
	public String getswfPath() {  
		if (swfFile.exists()) {  
			String tempString = swfFile.getPath();  
			tempString = tempString.replaceAll("\\\\", "/");  
			return tempString;  
		} else {  
			return "";  
		}  
	}  
	
	/** 
	* �������·�� 
	*/  
	public void setOutputPath(String outputPath) {  
		this.outputPath = outputPath;  
		if (!outputPath.equals("")) {  
			//��ȡ�ļ�����������
			String realName = fileName.substring(fileName.lastIndexOf("/")+1,fileString.lastIndexOf("."));  
			//�ж����·������Ƿ���"/"�����û�еĻ������ʱҪ����һ��"/"
			if (outputPath.charAt(outputPath.length()-1) == '/') {  
				swfFile = new File(outputPath + realName + ".swf");  
			} else {  
				swfFile = new File(outputPath + "/" + realName + ".swf");  
			}  
		} else {
			swfFile = new File(fileName + ".swf"); 
		}
	}  
		
		/*public static void main(String s[]) {  
			DocConverter d = new DocConverter("C:/upload/Power_Point��ʹ�ü���.ppt");  
			d.conver();  
		}  			*/									


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	public String getFileString() {
		return fileString;
	}


	public void setFileString(String fileString) {
		this.fileString = fileString;
	}


	public String getOutputPath() {
		return outputPath;
	}
	public String getOpenoffice_home() {
		return openoffice_home;
	}


	public void setOpenoffice_home(String openoffice_home) {
		this.openoffice_home = openoffice_home;
	}


	public String getPdf2swf_home() {
		return pdf2swf_home;
	}


	public void setPdf2swf_home(String pdf2swf_home) {
		this.pdf2swf_home = pdf2swf_home;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public File getOfficeFile() {
		return officeFile;
	}


	public void setOfficeFile(File officeFile) {
		this.officeFile = officeFile;
	}


	public File getPdfFile() {
		return pdfFile;
	}


	public void setPdfFile(File pdfFile) {
		this.pdfFile = pdfFile;
	}


	public File getSwfFile() {
		return swfFile;
	}


	public void setSwfFile(File swfFile) {
		this.swfFile = swfFile;
	}


	public static int getEnvironment() {
		return environment;
	} 

}
