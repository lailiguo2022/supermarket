package com.oracle.test;


import java.util.Date;


import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import com.oracle.dao.UserDao;
import com.oracle.dao.UserDaoImpl;
import com.oracle.po.User;
/**
 * �û���ɾ�Ĳ�Ĳ���
 * @author �Ž� 
 *
 */
public class UserDaoImplTest {
    UserDao udi;
	@Before
	public  void setUpBeforeClass() throws Exception {
		udi = new UserDaoImpl();
	}
    /**
     * 1.��Ӳ���
     */
	@Test
	public void testAddUser() {
		
		 Timestamp timestamp = new Timestamp(new Date().getTime());
 		 User user = new User(8, "12345678", "123", "��˼", "Ů", 21,timestamp,
					"13759722312", "�żҽ�");
 		 System.out.println(udi.addUser(user));
	}
	/**
     * 2.ɾ������
     */
	@Test
	public void testDeleteUserById() {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		
		System.out.println(timestamp);
		System.out.println(udi.deleteUserById(8));
	}
	/**
     * 3.�޸Ĳ���
     */
	@Test
	public void testUpdateUser() {
 		 User user = new User();
 		 user.setName("������");
 		 user.setUserID(2);
 		 user.setUserName("12345678");
 		 user.setPass("1288");
 		 System.out.println(udi.updateUser(user));
	}
	/**
     * 4.id��ȡ����
     */
	@Test
	public void testGetUserById() {
		 System.out.println(udi.getUserById(1));
	}
	/**
     * 5.�û�����ȡ����
     */
	@Test
	public void testGetUserByUserName() {
		 System.out.println(udi.getUserByUserName("zj"));
	}
	/**
     * 6.��ȡ�����û�����
     */
	@Test
	public void testGetAllUser() {
		System.out.println(udi.getAllUser());
	}
	 /**
     * 7.ͨ���û���Ż�ȡ����
     */
	@Test
	public void testGetOrderByUserId() {
		System.out.println(udi.getOrderByUserId(2));
	}

}
