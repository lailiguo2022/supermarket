package com.oracle.po;

import java.sql.Timestamp;
import java.util.List;

/**
 * �û�ʵ����
 * 
 * @author Administrator
 *
 */
public class User {
	private int userID; // �û���ţ����� ������ϵͳ��ֵ
	private String userName; // �û��� ΨһԼ��
	private String pass;// ����
	private int power; // Ȩ�ޣ�����Ա��1 �û���0 ��Ĭ��Լ����Ĭ��Ϊ0
	private String name; // ����
	private String sex; // �Ա�
	private int age; // ����
	private Timestamp date; // ע������
	private String telephoneNumber; // �绰����
	private String address;// �ջ���ַ
	private List<Order> orders;//�û�����ʱ��������
    //�޲ι���
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * �вι���
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
		return "�û���ţ�" + userID + "�û����ƣ�" + userName + "�û����룺" + pass + "Ȩ�ޣ�" + power + "������" + name + "�Ա�" + sex
				+ "���䣺" + age + "ע�����ڣ�" + date + "�绰���룺" + telephoneNumber + "�ջ���ַ��" + address+"\n";
	}

}
