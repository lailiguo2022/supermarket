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
	 * �����ݿ⽨������
	 * @return ����һ�����Ӷ���
	 */
	public static Connection getConnection() {
		try {
			// ��������
			Class.forName(driver);
			// ��������
			con = DriverManager.getConnection(url, user, pass);
			// ͨ�����Ӵ������ύԤ����sql����PreparedStatement����
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
	 * �����������ݿ�ķ��������� insert  update delete
	 * @param sql sql���
	 * @param obj Ҫ�޸ĵĲ���
	 * @return ���º�Ľ����0���0
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
	 * �����������ݿ�ķ���������select
	 * @param sql sql���
	 * @param obj Ҫ��������������
	 * @return ���º�Ľ����0���0
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

