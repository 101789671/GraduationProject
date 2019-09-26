package cn.gpms.util;

import java.sql.*;
import java.sql.DriverManager;

public class JdbcUtils {
	/**
	 * 获得Conenction类型的数据库连接对象
	 * @return
	 */
	public Connection getConn(){
		Connection conn=null;//定义连接对象变量conn
		try{
			//1:加载驱动 
			 Class.forName("com.mysql.jdbc.Driver");
			//2：定义连接数据库的通讯协议URL、用户、密码
			 String url="jdbc:mysql://127.0.0.1:3306/gpmsdb";
			 String user="root";
			 String password="123456";
			//3：通过驱动管理器DriverManager获得连接
		    conn =DriverManager.getConnection(url, user, password);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 关闭资源
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
			//实例化JdbcUtils 对象
			JdbcUtils jdbcUtils=new JdbcUtils();
			Connection conn=jdbcUtils.getConn();
			System.out.println("连接对象是否为NULL？"+conn.getCatalog());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
