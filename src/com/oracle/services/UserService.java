package com.oracle.services;

import java.util.List;

import com.oracle.po.Order;
import com.oracle.po.User;

/**
 * 业务层
 * 
 * @author 张杰
 *
 */
public interface UserService {
	/**
	 * 1.添加用户
	 * 
	 * @param user
	 * @return 返回 true 成功，false，失败
	 */
	public boolean addUser(User user);

	/**
	 * 2.通过id删除用户
	 * 
	 * @param id
	 * @return 返回 true 成功，false，失败
	 */
	public boolean deleteUserById(int id);

	/**
	 * 3.修改用户
	 * 
	 * @param user
	 * @return 返回 true 成功，false，失败
	 */
	public boolean updateUser(User user);

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
