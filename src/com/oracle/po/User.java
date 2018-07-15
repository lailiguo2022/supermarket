package com.oracle.po;

import java.sql.Timestamp;
import java.util.List;

/**
 * 用户实体类
 * 
 * @author Administrator
 *
 */
public class User {
	private int userID; // 用户编号，主键 自增，系统赋值
	private String userName; // 用户名 唯一约束
	private String pass;// 密码
	private int power; // 权限（管理员：1 用户：0 ）默认约束，默认为0
	private String name; // 姓名
	private String sex; // 性别
	private int age; // 年龄
	private Timestamp date; // 注册日期
	private String telephoneNumber; // 电话号码
	private String address;// 收货地址
	private List<Order> orders;//用户的历时订单集合
    //无参构造
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 有参构造
	 * 
	 * @param userid
	 * @param userName
	 * @param pass
	 * @param power
	 * @param name
	 * @param sex
	 * @param age
	 * @param date
	 * @param telephonenumber
	 * @param address
	 */
	public User(int userID, String userName, String pass, String name, String sex, int age, Timestamp date,
			String telephoneNumber, String address) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.pass = pass;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.date = date;
		this.telephoneNumber = telephoneNumber;
		this.address = address;
	}



	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "用户编号：" + userID + "用户名称：" + userName + "用户密码：" + pass + "权限：" + power + "姓名：" + name + "性别：" + sex
				+ "年龄：" + age + "注册日期：" + date + "电话号码：" + telephoneNumber + "收货地址：" + address+"\n";
	}

}
