package com.oracle.services;

import java.util.List;

import com.oracle.dao.UserDao;
import com.oracle.dao.UserDaoImpl;
import com.oracle.po.Order;
import com.oracle.po.User;
/**
 * 业务类的实现
 * @author 张杰 
 *
 */
public class UserServiceImpl implements UserService {
	//创建用户UserDao层子类的对象
	UserDao udi = new UserDaoImpl();
    /**
     * 1.添加用户
     */
	@Override
	public boolean addUser(User user) {
		
		return udi.addUser(user)!=0? true:false;
	}
	/**
     * 2.删除用户
     */
	@Override
	public boolean deleteUserById(int id) {
		
		return udi.deleteUserById(id)!=0? true:false;
	}
	/**
     * 3.修改用户
     */
	@Override
	public boolean updateUser(User user) {
		
		return udi.updateUser(user)!=0?true:false;
	}
	/**
     * 4.通过id获取用户
     */
	@Override
	public User getUserById(int id) {
		
		return udi.getUserById(id);
	}
	/**
     * 5.通过用户名获取用户
     */
	@Override
	public User getUserByUserName(String userName) {
		
		return udi.getUserByUserName(userName);
	}
	/**
     * 6.获取所有用户
     */
	@Override
	public List<User> getAllUser() {
		
		return udi.getAllUser();
	}

	/**
	 * 7.通过用户编号获取所有订单
	 */
	@Override
	public List<Order> getOrderByUserId(int id){
		
		return udi.getOrderByUserId(id);
	}
}
