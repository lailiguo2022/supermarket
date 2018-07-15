package com.oracle.po;

import java.sql.Timestamp;
import java.util.List;

/**
 * ����ʵ����
 * 
 * @author Administrator
 *
 */
public class Order {
	private int orderID;// ������ ����
	private Timestamp creationTime;// ����ʱ��
	private String orderStuas;// ״̬
	private Timestamp completionTime;// ����ʱ��
	private int userID;
	private double totalPrice;
	private List<Commodity> commoditys;//��Ʒ����
	/**
	 * �޲ι���
	 */
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * �вι���
	 * 
	 * @param orderID
	 * @param creationTime
	 * @param orderStuas
	 * @param completionTime
	 */
	public Order(int orderID,int userID,
			Timestamp creationTime, String orderStuas, Timestamp completionTime,double orderPrice) {
		super();
		this.orderID = orderID;
		this.creationTime = creationTime;
		this.orderStuas = orderStuas;
		this.completionTime = completionTime;
		this.userID = userID;
		this.totalPrice = orderPrice;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public String getOrderStuas() {
		return orderStuas;
	}

	public void setOrderStuas(String orderStuas) {
		this.orderStuas = orderStuas;
	}

	public Timestamp getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(Timestamp completionTime) {
		this.completionTime = completionTime;
	}

	@Override
	public String toString() {
		return "�����ţ�" + orderID +"�û���ţ�"+userID+ "����ʱ�䣺" 
	+ creationTime + "״̬��" + orderStuas + "����ʱ�䣺" + completionTime+"�����ܼ�"+totalPrice;
	}

	public int getUserID() {
		return userID;
	}
	/**
	 * �����û����
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * �����ܼ�
	 * @param userID
	 */
	public void setTotalPrice(double orderPrice) {
		this.totalPrice = orderPrice;
	}

	public List<Commodity> getCommoditys() {
		return commoditys;
	}

	public void setCommoditys(List<Commodity> commoditys) {
		this.commoditys = commoditys;
	}

}
