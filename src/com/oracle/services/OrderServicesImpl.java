package com.oracle.services;

import java.util.List;

import com.oracle.dao.OrderDao;
import com.oracle.dao.OrderDaoImpl;
import com.oracle.po.Commodity;
import com.oracle.po.Order;

public class OrderServicesImpl implements OrderServices {
	
	OrderDao od=new OrderDaoImpl();
	/**
	 * 生成订单及明细方法
	 */
	@Override
	public boolean addOder(int userID, List<Commodity> list) {
		return od.addOder(userID, list);
	}
	/**
	 * 通过编号删除订单
	 */
	@Override
	public boolean deleteOderById(int orderid) {
		
		return od.deleteOderById(orderid);
	}
	/**
	 * 修改订单
	 */
	@Override
	public boolean UpdateOder(Order order) {
		
		return od.UpdateOder(order);
	}
	/**
	 * 通过订单id检索订单
	 */
	@Override
	public Order searchOderById(int id) {
		
		return od.searchOderById(id);
	}
	/**
	 * 
	 */
	@Override
	public Order searchOderByDate() {
		
		return null;
	}
	/**
	 * 获取所有订单
	 */
	@Override
	public List<Order> getAllOder() {
		
		return od.getAllOder();
	}
	/**
	 * 订单编号生成方法；
	 */
	@Override
	public int getMaxOrderID() {
		
		return od.getMaxOrderID();
	}
	/**
	 * 编号查看订单明细(返回此订单)
	 */
	@Override
	public Order searchOder_CommodityByOrderID(int id) {
		return od.searchOder_CommodityByOrderID(id);
	}
	
	@Override
	public int getUserMaxOrderID(int userID) {
		return od.getUserMaxOrderID(userID);
	}

}
