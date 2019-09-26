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
	
	private static final int environment = 1;// 环境 1：windows 2:linux  
	private String fileString;//源文件的路径	
	private String outputPath = "F:/upload";// 输出路径 ，如果不设置就输出在默认的位置 (与源文件位置相同)
	private String openoffice_home = "C:/Program Files (x86)/OpenOffice 4";//这里是OpenOffice的安装目录
	private String pdf2swf_home = "C:/Program Files (x86)/SWFTools/pdf2swf.exe ";//pdf2swf的安装路径
	private String fileName;
	private File officeFile;//office文件
	private File pdfFile;//转成的pdf文件
	private File swfFile;//转成的swf文件

	
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
	 * 在调用转换类的时候将输出路径、OpenOffice的安装路径、pdf2swf的路径都设置好，若路径都一样，可直接在属性上设置更方便
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
	* 重新设置file 
	*  
	* @param fileString 
	*/  
	public void setFile(String fileString) {  
		ini(fileString);  
	}  
	
	/** 
	* 初始化 
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
	* 转为PDF 
	*  
	* @param file 
	*/  
	private void doc2pdf() throws Exception {  
		if (officeFile.exists()) {  
			if (!pdfFile.exists()) {  
				
				// 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'  
	            if (openoffice_home.charAt(openoffice_home.length() - 1) != '\\') {  
	            	openoffice_home += "\\";  
	            }  
	            
	            // 使用java启动OpenOffice的服务 
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
					System.out.println("****pdf转换成功，PDF输出：" + pdfFile.getPath() + "****");  
				} catch (java.net.ConnectException e) {  
					e.printStackTrace();  
					System.out.println("****swf转换器异常，openoffice服务未启动！****");  
					throw e;  
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {  
					e.printStackTrace();  
					System.out.println("****swf转换器异常，读取转换文件失败****");  
					throw e;  
				} catch (Exception e) {  
					e.printStackTrace();  
					throw e;  
				}  
				} else {  
				System.out.println("****已经转换为pdf，不需要再进行转化****");  
				}  
		} else {  
			System.out.println("****swf转换器异常，需要转换的文档不存在，无法转换****");  
		}  
	}  
	
	/** 
	* 转换成 swf 
	*/  
	private void pdf2swf() throws Exception {  
		Runtime r = Runtime.getRuntime();  
		if (!swfFile.exists()) {  
			if (pdfFile.exists()) {  
				if (environment == 1) {// windows环境处理  
					try { 
						//以下的参数：-G -s poly2bitmap非常重要，特别是碰到office文件中文件特别大或者说图表特别多的情况，可能无法转为swf文件，加上这可以防止，
						//具体参数请参考网上资料。由于这样会降低转换效率，所以最好在这里做个判断，若文件大于某个大小再加这两个参数，反之不用加，提高转换效率
						Process p = r.exec(pdf2swf_home + pdfFile.getPath() + " -o "+ swfFile.getPath() + " -T 9 -G -s poly2bitmap");  
						System.out.print(loadStream(p.getInputStream()));  
						System.err.print(loadStream(p.getErrorStream()));  
						System.out.print(loadStream(p.getInputStream()));  
						System.err.println("****swf转换成功，文件输出："  
						+ swfFile.getPath() + "****");  
						if (pdfFile.exists()) {  
							pdfFile.delete();  
						}  
					} catch (IOException e) {  
						e.printStackTrace();  
						throw e;  
					}  
				} else if (environment == 2) {// linux环境处理  
					try {  
						Process p = r.exec("pdf2swf " + pdfFile.getPath()  
						+ " -o " + swfFile.getPath() + " -T 9");  
						System.out.print(loadStream(p.getInputStream()));  
						System.err.print(loadStream(p.getErrorStream()));  
						System.err.println("****swf转换成功，文件输出："  
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
				System.out.println("****pdf不存在,无法转换****");  
			}  
		} else {  
			System.out.println("****swf已经存在不需要转换****");  
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
	* 转换主方法 
	*/  
	public boolean conver() {  
	
		if (swfFile.exists()) {  
			System.out.println("****swf转换器开始工作，该文件已经转换为swf****");  
			return true;  
		}  
		
		if (environment == 1) {  
			System.out.println("****swf转换器开始工作，当前设置运行环境windows****");  
		} else {  
			System.out.println("****swf转换器开始工作，当前设置运行环境linux****");  
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
	* 返回文件路径 
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
	* 设置输出路径 
	*/  
	public void setOutputPath(String outputPath) {  
		this.outputPath = outputPath;  
		if (!outputPath.equals("")) {  
			//获取文件真正的名字
			String realName = fileName.substring(fileName.lastIndexOf("/")+1,fileString.lastIndexOf("."));  
			//判断输出路径最后是否有"/"，如果没有的话，输出时要加上一个"/"
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
			DocConverter d = new DocConverter("C:/upload/Power_Point的使用技巧.ppt");  
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
