package com.oracle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.po.Order;
import com.oracle.po.User;
import com.oracle.util.DBUtil;
 /**
  * �û�����ɾ�Ĳ�
  * @author �Ž� 
  *
  */
public class UserDaoImpl implements UserDao {
	/**
	 * 1.����û�
	 */
	@Override
	public int addUser(User user) {
		// ��ȡ����û���sequel���
		String sql = "INSERT INTO t_users "
				//�û��������룬�������Ա����䣬ע�����ڣ��绰����ַ
				+ "(USER,pass,NAME,sex,age,ergistration_date,telephonenumber,address)"
				+ "VALUES (?,?,?,?,?,?,?,?)";
		// ���ù�����ĸ��·���
		return DBUtil.executeUpdate(sql, user.getUserName(),
										 user.getPass(), 
										 user.getName(),
										 user.getSex(),
										 user.getAge(), 
										 user.getDate(), 
										 user.getTelephoneNumber(), 
										 user.getAddress());
	}

	/**
	 * 2.ͨ��idɾ���û�
	 */
	@Override
	public int deleteUserById(int id) {
		// ��ȡɾ���û���sequel���
		String sql = " DELETE FROM  t_users WHERE userid=?";
		// ���ù�����ĸ��·���
		return DBUtil.executeUpdate(sql, id);
	}

	/**
	 * 3.�޸��û�
	 */
	@Override
	public int updateUser(User user) {
		// ��ȡ�޸��û���sequel���
		String sql = " UPDATE t_users SET "
				// ���룬�������Ա����䣬�绰����ַ (id)
				+ "PASS=?,NAME=?,sex=?,age=?,telephonenumber=?,address=? WHERE userid=?";
		// ���ù�����ĸ��·���
		return DBUtil.executeUpdate(sql, user.getPass(),
										 user.getName(),
										 user.getSex(),
										 user.getAge(),
										 user.getTelephoneNumber(),
										 user.getAddress(),
										 user.getUserID());
	}

	/**
	 * 4.ͨ��id��ȡ�û�
	 */
	@Override
	public User getUserById(int id) {
		// ��ȡͨ��id�����û���sequel���
		String sql = " SELECT * FROM t_users WHERE userid=?";
		// ���ù�������������
		ResultSet rs = DBUtil.executeQuery(sql, id);
		// ����һ���û�����
		User user = null;
		// ��������
		try {
			if (rs.next()) {
				user = new User();
				user.setUserID(rs.getInt("userid"));
				user.setUserName(rs.getString("user"));
				user.setPass(rs.getString("pass"));
				user.setPower(rs.getInt("power"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getString("sex"));
				user.setAge(rs.getInt("age"));
				user.setDate(rs.getTimestamp("ergistration_date"));
				user.setTelephoneNumber(rs.getString("telephonenumber"));
				user.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * 5.ͨ���û�����ȡ�û�
	 */
	@Override
	public User getUserByUserName(String userName) {
		// ��ȡͨ���û��������û���sequel���
		String sql = " SELECT * FROM t_users WHERE user=?";
		// ���ù�������������
		ResultSet rs = DBUtil.executeQuery(sql, userName);
		// ����һ���û�����
		User user = null;
		// ��������
		try {
			if (rs.next()) {
				user = new User();
				user.setUserID(rs.getInt("userid"));
				user.setUserName(rs.getString("user"));
				user.setPass(rs.getString("pass"));
				user.setPower(rs.getInt("power"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getString("sex"));
				user.setAge(rs.getInt("age"));
				user.setDate(rs.getTimestamp("ergistration_date"));
				user.setTelephoneNumber(rs.getString("telephonenumber"));
				user.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * 6.��ȡ�����û�
	 */
	@Override
	public List<User> getAllUser() {

		// ��ȡͨ���û��������û���sequel���
		String sql = " SELECT * FROM t_users ";
		// ���ù�������������
		ResultSet rs = DBUtil.executeQuery(sql);
		// ����һ��List<order>����
		List<User> list = new ArrayList<User>();
		// ��������
		try {
			while (rs.next()) {
				User user = new User();
				user.setUserID(rs.getInt("userid"));
				user.setUserName(rs.getString("user"));
				user.setPass(rs.getString("pass"));
				user.setPower(rs.getInt("power"));
				user.setName(rs.getString("name"));
				user.setSex(rs.getString("sex"));
				user.setAge(rs.getInt("age"));
				user.setDate(rs.getTimestamp("ergistration_date"));
				user.setTelephoneNumber(rs.getString("telephonenumber"));
				user.setAddress(rs.getString("address"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 7.ͨ���û���Ż�ȡ���ж���
	 */
	@Override
	public List<Order> getOrderByUserId(int id){
		//��ȡ���Ҷ�����sequel���
		String sql = "SELECT * FROM t_order WHERE USERID=?";
		//��������
		ResultSet rs  = DBUtil.executeQuery(sql,id);
		//����һ����������
		List<Order> list = new ArrayList<Order>();
			try {
				while(rs.next()){
					Order order = new Order();
					order.setOrderID(rs.getInt("orderid"));
					order.setUserID(rs.getInt("userid"));
					order.setCreationTime(rs.getTimestamp("creation_time"));
					order.setCompletionTime(rs.getTimestamp("completion_time"));
					order.setOrderStuas(rs.getString("orderstaus"));
					order.setTotalPrice(rs.getDouble("totalprice"));
					list.add(order);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	return list;
	}

}
