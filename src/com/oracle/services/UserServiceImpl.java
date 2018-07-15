package com.oracle.services;

import java.util.List;

import com.oracle.dao.UserDao;
import com.oracle.dao.UserDaoImpl;
import com.oracle.po.Order;
import com.oracle.po.User;
/**
 * ҵ�����ʵ��
 * @author �Ž� 
 *
 */
public class UserServiceImpl implements UserService {
	//�����û�UserDao������Ķ���
	UserDao udi = new UserDaoImpl();
    /**
     * 1.����û�
     */
	@Override
	public boolean addUser(User user) {
		
		return udi.addUser(user)!=0? true:false;
	}
	/**
     * 2.ɾ���û�
     */
	@Override
	public boolean deleteUserById(int id) {
		
		return udi.deleteUserById(id)!=0? true:false;
	}
	/**
     * 3.�޸��û�
     */
	@Override
	public boolean updateUser(User user) {
		
		return udi.updateUser(user)!=0?true:false;
	}
	/**
     * 4.ͨ��id��ȡ�û�
     */
	@Override
	public User getUserById(int id) {
		
		return udi.getUserById(id);
	}
	/**
     * 5.ͨ���û�����ȡ�û�
     */
	@Override
	public User getUserByUserName(String userName) {
		
		return udi.getUserByUserName(userName);
	}
	/**
     * 6.��ȡ�����û�
     */
	@Override
	public List<User> getAllUser() {
		
		return udi.getAllUser();
	}

	/**
	 * 7.ͨ���û���Ż�ȡ���ж���
	 */
	@Override
	public List<Order> getOrderByUserId(int id){
		
		return udi.getOrderByUserId(id);
	}
}
