package com.oracle.test;


import java.util.Date;


import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import com.oracle.dao.UserDao;
import com.oracle.dao.UserDaoImpl;
import com.oracle.po.User;
/**
 * 用户增删改查的测试
 * @author 张杰 
 *
 */
public class UserDaoImplTest {
    UserDao udi;
	@Before
	public  void setUpBeforeClass() throws Exception {
		udi = new UserDaoImpl();
	}
    /**
     * 1.添加测试
     */
	@Test
	public void testAddUser() {
		
		 Timestamp timestamp = new Timestamp(new Date().getTime());
 		 User user = new User(8, "12345678", "123", "张思", "女", 21,timestamp,
					"13759722312", "张家界");
 		 System.out.println(udi.addUser(user));
	}
	/**
     * 2.删除测试
     */
	@Test
	public void testDeleteUserById() {
		Timestamp timestamp = new Timestamp(new Date().getTime());
		
		System.out.println(timestamp);
		System.out.println(udi.deleteUserById(8));
	}
	/**
     * 3.修改测试
     */
	@Test
	public void testUpdateUser() {
 		 User user = new User();
 		 user.setName("撒旦法");
 		 user.setUserID(2);
 		 user.setUserName("12345678");
 		 user.setPass("1288");
 		 System.out.println(udi.updateUser(user));
	}
	/**
     * 4.id获取测试
     */
	@Test
	public void testGetUserById() {
		 System.out.println(udi.getUserById(1));
	}
	/**
     * 5.用户名获取测试
     */
	@Test
	public void testGetUserByUserName() {
		 System.out.println(udi.getUserByUserName("zj"));
	}
	/**
     * 6.获取所有用户测试
     */
	@Test
	public void testGetAllUser() {
		System.out.println(udi.getAllUser());
	}
	 /**
     * 7.通过用户编号获取订单
     */
	@Test
	public void testGetOrderByUserId() {
		System.out.println(udi.getOrderByUserId(2));
	}

}
