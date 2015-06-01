package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class TestJDBC {
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://168.7.2.167:3306/studentmgr";
		String username = "root";
		String password = "root";
		
		try{
			Class.forName(driverClassName);	// 加载JDBC Driver
			conn = DriverManager.getConnection(url, username, password);// 建立连接
			
			st = conn.createStatement();// 创建SQL
			String sql = "SELECT * FROM STUDENT";
			rs = st.executeQuery(sql);// 执行SQL
			
			while (rs.next()) {	// 处理结果
				System.out.println(rs.getString("name"));
			}
		} catch(Exception exp){
			exp.printStackTrace();
		} finally {
			
			try {
				if (rs != null) {
					rs.close();
				}
				
				if(st != null){
					st.close();
				}
				
				if(conn != null){
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	

}
