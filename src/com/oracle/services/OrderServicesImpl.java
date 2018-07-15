package com.oracle.services;

import java.util.List;

import com.oracle.dao.OrderDao;
import com.oracle.dao.OrderDaoImpl;
import com.oracle.po.Commodity;
import com.oracle.po.Order;

public class OrderServicesImpl implements OrderServices {
	
	OrderDao od=new OrderDaoImpl();
	/**
	 * ���ɶ�������ϸ����
	 */
	@Override
	public boolean addOder(int userID, List<Commodity> list) {
		return od.addOder(userID, list);
	}
	/**
	 * ͨ�����ɾ������
	 */
	@Override
	public boolean deleteOderById(int orderid) {
		
		return od.deleteOderById(orderid);
	}
	/**
	 * �޸Ķ���
	 */
	@Override
	public boolean UpdateOder(Order order) {
		
		return od.UpdateOder(order);
	}
	/**
	 * ͨ������id��������
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
	 * ��ȡ���ж���
	 */
	@Override
	public List<Order> getAllOder() {
		
		return od.getAllOder();
	}
	/**
	 * ����������ɷ�����
	 */
	@Override
	public int getMaxOrderID() {
		
		return od.getMaxOrderID();
	}
	/**
	 * ��Ų鿴������ϸ(���ش˶���)
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
