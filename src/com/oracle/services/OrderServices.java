package com.oracle.services;

import java.util.List;

import com.oracle.po.Commodity;
import com.oracle.po.Order;

public interface OrderServices {
	/**
	 * 生成订单及明细
	 * @return成功返回true 失败返回false；
	 */
	boolean addOder(int userID,List<Commodity> list);
	
	/**
	 * 删除订单
	 * @return删除成功返回true 失败返回false
	 */
	boolean deleteOderById(int orderid);
	/**
	 * 修改订单
	 * @return 修改成功返回true 失败返回false；
	 */
	boolean UpdateOder(Order order);
	/**
	 * 通过订单编号查询订单明细
	 * @return
	 */
	Order searchOder_CommodityByOrderID(int id);
	/**
	 * 通过订单编号查询订单
	 * @return返回要检索的编号的订单
	 */
	Order searchOderById(int id);
	/**
	 * 通过订单生成日期查询订单
	 * @return返回要检索日期的订单
	 */
	Order searchOderByDate();
	/**
	 * 浏览所有订单
	 * @return
	 */
	List<Order> getAllOder();
	/**
	 * 生成订单编号
	 * @return
	 */
	int getMaxOrderID();
	
	/**
	 * 通过用户编号获取该用户最大订单编号
	 * @param userID 用户编号
	 * @return 该用户最大订单编号
	 */
	int getUserMaxOrderID(int userID);
}
