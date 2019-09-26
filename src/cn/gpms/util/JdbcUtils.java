package cn.gpms.util;

import java.sql.*;
import java.sql.DriverManager;

public class JdbcUtils {
	/**
	 * ���Conenction���͵����ݿ����Ӷ���
	 * @return
	 */
	public Connection getConn(){
		Connection conn=null;//�������Ӷ������conn
		try{
			//1:�������� 
			 Class.forName("com.mysql.jdbc.Driver");
			//2�������������ݿ��ͨѶЭ��URL���û�������
			 String url="jdbc:mysql://127.0.0.1:3306/gpmsdb";
			 String user="root";
			 String password="123456";
			//3��ͨ������������DriverManager�������
		    conn =DriverManager.getConnection(url, user, password);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * �ر���Դ
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public void close(Connection conn,Statement stmt,ResultSet rs){
		try{
		if(rs!=null){
			rs.close();
		}
		if(stmt!=null){
			stmt.close();
		}
		if(conn!=null){
			conn.close();
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void close(Connection conn,Statement stmt){
		close(conn, stmt, null);
		
	}
	
	public static void main(String[]args){
		try{
			//ʵ����JdbcUtils ����
			JdbcUtils jdbcUtils=new JdbcUtils();
			Connection conn=jdbcUtils.getConn();
			System.out.println("���Ӷ����Ƿ�ΪNULL��"+conn.getCatalog());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
