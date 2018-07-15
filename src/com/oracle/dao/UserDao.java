package com.oracle.dao;

import java.util.List;

import com.oracle.po.Order;
import com.oracle.po.User;

/**
 * �û�����ɾ�Ĳ�
 * 
 * @author �Ž�
 *
 */
public interface UserDao {
	/**
	 * 1.����û�
	 * 
	 * @param user
	 * @return ���� 1��0, 1������ӳɹ� 0�������ʧ��
	 */
	public int addUser(User user);

	/**
	 * 2.ͨ��idɾ���û�
	 * 
	 * @param id
	 * @return ���� 1��0, 1����ɾ���ɹ� 0����ɾ��ʧ��
	 */
	public int deleteUserById(int id);

	/**
	 * 3.�޸��û�
	 * 
	 * @param user
	 * @return ���� 1��0, 1�����޸ĳɹ� 0�����޸�ʧ��
	 */
	public int updateUser(User user);

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
