package com.oracle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.po.Order;
import com.oracle.po.User;
import com.oracle.util.DBUtil;
 /**
  * 用户的增删改查
  * @author 张杰 
  *
  */
public class UserDaoImpl implements UserDao {
	/**
	 * 1.添加用户
	 */
	@Override
	public int addUser(User user) {
		// 获取添加用户的sequel语句
		String sql = "INSERT INTO t_users "
				//用户名，密码，姓名，性别，年龄，注册日期，电话，地址
				+ "(USER,pass,NAME,sex,age,ergistration_date,telephonenumber,address)"
				+ "VALUES (?,?,?,?,?,?,?,?)";
		// 调用工具类的更新方法
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
	 * 2.通过id删除用户
	 */
	@Override
	public int deleteUserById(int id) {
		// 获取删除用户的sequel语句
		String sql = " DELETE FROM  t_users WHERE userid=?";
		// 调用工具类的更新方法
		return DBUtil.executeUpdate(sql, id);
	}

	/**
	 * 3.修改用户
	 */
	@Override
	public int updateUser(User user) {
		// 获取修改用户的sequel语句
		String sql = " UPDATE t_users SET "
				// 密码，姓名，性别，年龄，电话，地址 (id)
				+ "PASS=?,NAME=?,sex=?,age=?,telephonenumber=?,address=? WHERE userid=?";
		// 调用工具类的更新方法
		return DBUtil.executeUpdate(sql, user.getPass(),
										 user.getName(),
										 user.getSex(),
										 user.getAge(),
										 user.getTelephoneNumber(),
										 user.getAddress(),
										 user.getUserID());
	}

	/**
	 * 4.通过id获取用户
	 */
	@Override
	public User getUserById(int id) {
		// 获取通过id查找用户的sequel语句
		String sql = " SELECT * FROM t_users WHERE userid=?";
		// 调用工具类的浏览方法
		ResultSet rs = DBUtil.executeQuery(sql, id);
		// 创建一个用户对象
		User user = null;
		// 处理结果集
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
	 * 5.通过用户名获取用户
	 */
	@Override
	public User getUserByUserName(String userName) {
		// 获取通过用户名查找用户的sequel语句
		String sql = " SELECT * FROM t_users WHERE user=?";
		// 调用工具类的浏览方法
		ResultSet rs = DBUtil.executeQuery(sql, userName);
		// 创建一个用户对象
		User user = null;
		// 处理结果集
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
	 * 6.获取所有用户
	 */
	@Override
	public List<User> getAllUser() {

		// 获取通过用户名查找用户的sequel语句
		String sql = " SELECT * FROM t_users ";
		// 调用工具类的浏览方法
		ResultSet rs = DBUtil.executeQuery(sql);
		// 创建一个List<order>集合
		List<User> list = new ArrayList<User>();
		// 处理结果集
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
	 * 7.通过用户编号获取所有订单
	 */
	@Override
	public List<Order> getOrderByUserId(int id){
		//获取查找订单的sequel语句
		String sql = "SELECT * FROM t_order WHERE USERID=?";
		//处理结果集
		ResultSet rs  = DBUtil.executeQuery(sql,id);
		//创建一个订单集合
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
