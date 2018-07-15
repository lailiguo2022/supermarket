package com.oracle.services;

import java.util.List;

import com.oracle.po.Order;
import com.oracle.po.User;

/**
 * ҵ���
 * 
 * @author �Ž�
 *
 */
public interface UserService {
	/**
	 * 1.����û�
	 * 
	 * @param user
	 * @return ���� true �ɹ���false��ʧ��
	 */
	public boolean addUser(User user);

	/**
	 * 2.ͨ��idɾ���û�
	 * 
	 * @param id
	 * @return ���� true �ɹ���false��ʧ��
	 */
	public boolean deleteUserById(int id);

	/**
	 * 3.�޸��û�
	 * 
	 * @param user
	 * @return ���� true �ɹ���false��ʧ��
	 */
	public boolean updateUser(User user);

	/**
	 * 4.ͨ��id��ȡ�û�
	 * 
	 * @param id
	 * @return ����һ��User �� null
	 */
	public User getUserById(int id);

	/**
	 * 5.ͨ���û�����ȡ�û�
	 * 
	 * @param userName
	 * @return ����һ��User �� null
	 */
	public User getUserByUserName(String userName);

	/**
	 * 6.��ȡ���е��û�
	 * 
	 * @return ����һ��List����
	 */
	public List<User> getAllUser();
	
	/**
	 * 7.ͨ���û���Ż�ȡ����
	 * @param id
	 * @return  ����һ����������
	 */
	public List<Order> getOrderByUserId(int id);

}
