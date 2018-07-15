package com.oracle.dao;

import java.util.List;

import com.oracle.po.Order;
import com.oracle.po.User;

/**
 * 用户的增删改查
 * 
 * @author 张杰
 *
 */
public interface UserDao {
	/**
	 * 1.添加用户
	 * 
	 * @param user
	 * @return 返回 1或0, 1代表添加成功 0代表添加失败
	 */
	public int addUser(User user);

	/**
	 * 2.通过id删除用户
	 * 
	 * @param id
	 * @return 返回 1或0, 1代表删除成功 0代表删除失败
	 */
	public int deleteUserById(int id);

	/**
	 * 3.修改用户
	 * 
	 * @param user
	 * @return 返回 1或0, 1代表修改成功 0代表修改失败
	 */
	public int updateUser(User user);

	/**
	 * 4.通过id获取用户
	 * 
	 * @param id
	 * @return 返回一个User 或 null
	 */
	public User getUserById(int id);

	/**
	 * 5.通过用户名获取用户
	 * 
	 * @param userName
	 * @return 返回一个User 或 null
	 */
	public User getUserByUserName(String userName);

	/**
	 * 6.获取所有的用户
	 * 
	 * @return 返回一个List集合
	 */
	public List<User> getAllUser();
	/**
	 * 7.通过用户编号获取订单
	 * @param id
	 * @return  返回一个订单集合
	 */
	public List<Order> getOrderByUserId(int id);


}
