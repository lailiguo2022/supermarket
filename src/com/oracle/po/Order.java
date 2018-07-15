package com.oracle.po;

import java.sql.Timestamp;
import java.util.List;

/**
 * 订单实体类
 * 
 * @author Administrator
 *
 */
public class Order {
	private int orderID;// 订单号 主键
	private Timestamp creationTime;// 创建时间
	private String orderStuas;// 状态
	private Timestamp completionTime;// 结束时间
	private int userID;
	private double totalPrice;
	private List<Commodity> commoditys;//商品集合
	/**
	 * 无参构造
	 */
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 有参构造
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
		return "订单号：" + orderID +"用户编号："+userID+ "创建时间：" 
	+ creationTime + "状态：" + orderStuas + "结束时间：" + completionTime+"定单总价"+totalPrice;
	}

	public int getUserID() {
		return userID;
	}
	/**
	 * 设置用户编号
	 * @param userID
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	/**
	 * 设置总价
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
