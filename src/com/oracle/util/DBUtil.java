package com.oracle.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	static String driver="com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/db_supermarket";
	static String user = "root";
	static String pass = "mysql";
	
	static Connection con;

	/**
	 * 与数据库建立连接
	 * @return 返回一个连接对象
	 */
	public static Connection getConnection() {
		try {
			// 加载驱动
			Class.forName(driver);
			// 建立连接
			con = DriverManager.getConnection(url, user, pass);
			// 通过连接创建能提交预编译sql语句的PreparedStatement对象
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 建立更新数据库的方法，用于 insert  update delete
	 * @param sql sql语句
	 * @param obj 要修改的参数
	 * @return 更新后的结果，0或非0
	 */
	public static int executeUpdate(String sql,Object ... obj){
		try {
			con=getConnection();
			PreparedStatement prep = con.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				prep.setObject(i+1, obj[i]);
			}
			return prep.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 建立检索数据库的方法，用于select
	 * @param sql sql语句
	 * @param obj 要检索的条件参数
	 * @return 更新后的结果，0或非0
	 */
	public static ResultSet executeQuery(String sql,Object ... obj){
		try {
			con=getConnection();
			PreparedStatement prep = con.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				prep.setObject(i+1, obj[i]);
			}
			return prep.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

