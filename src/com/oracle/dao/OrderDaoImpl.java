package com.oracle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.oracle.po.Commodity;
import com.oracle.po.Order;
import com.oracle.util.DBUtil;

//import sun.security.pkcs11.Secmod.DbMode;

public class OrderDaoImpl implements OrderDao {
	CommodityDao cod=new CommodityDaoImpl();//实例化商品dao
		/**
		 * 添加订单方法
		 */
	@Override
	public boolean addOder(int userID,List<Commodity> list) {
		int orderId=getMaxOrderID();//用户编号通过自动生成
		//sql添加进订单添加语句
	String sql="insert into t_order values(?,?,?,?,?,?)";
	//sql添加进订单明细添加语句
	String sql2="insert into order_commodity values(null,?,?,?,?)";
	int data=0;//
	int	data2=0;
	double totalPrice = 0;
		if (list!=null&&list.size()>0) {
			for (Commodity com : list) {
				totalPrice+=com.getEntryPrice();//计算当前条目价格
				
			}
			//借助工具类的更新方法给data的“？”分别赋值
			 data=DBUtil.executeUpdate(sql, 
					orderId,//订单编号通过自动生成
					userID,//获取当前用户的id
					new Timestamp(new Date().getTime()),//获取当前时间
					null,//订单结束时间
					"未付款",//订单状态
					totalPrice);//订单总价
			 //将商品集合中的东西添加进明细表并给商品库存更新
				for (Commodity com : list) {
					//借助工具类的更新方法给data2的“？”分别赋值
					data2=DBUtil.executeUpdate(sql2,orderId,com.getComID(),com.getBuyNumber(),com.getEntryPrice());
				}
		}
		if (data==1&&data2!=0) {
			return true;
		}
		return false;
	}
	/**
	 * 通过订单编号查找订单明细
	 */
	@Override
	public Order searchOder_CommodityByOrderID(int id) {
		Order order=searchOderById(id);//先获取本id的订单
		String sql="SELECT comid,buynumber,entryprice FROM order_commodity WHERE"
				+ " orderid=? ";
		List<Commodity> list=new ArrayList<Commodity>();//创建集合
		ResultSet rs=DBUtil.executeQuery(sql, id);//调用工具类查询方法
		try {
			while (rs.next()) {
				int cid=rs.getInt(1);
				Commodity com=cod.getCommodityByComid(cid);//通过编号搜索商品
				com.setBuyNumber(rs.getInt("buynumber"));//给此商品的购买次数赋值
				//com.setEntryPrice(rs.getInt("entryprice"));//给此商品的金额赋值
				list.add(com);//将此商品添加进集合
			}
			order.setCommoditys(list);//将此集合设置为本订单的集合；
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	
	/**
	 * 删除订单方法
	 */
	@Override
	public boolean deleteOderById(int orderid) {
		//sql删除语句
		String sql="delete from t_order where orderid=?";
		int data=DBUtil.executeUpdate(sql, orderid);//借助工具类更新方法
		if (data!=0) {
			return true;//删除成功返回true
		}
		return false;//删除失败
	}
	
	
	/**
	 * 
	 */
	@Override
	public boolean UpdateOder(Order order) {
		String sql="UPDATE t_order SET orderstaus=?"
				+ ",totalprice=? WHERE orderid=?";
		int data=DBUtil.executeUpdate(sql,
				order.getOrderStuas(),
				order.getTotalPrice(),//调用工具更新方法
		        order.getOrderID());  //调用工具更新方法
		if (data!=0) {
			return true;
		}
		return false;
	}
	/**
	 * 通过编号检索订单号
	 */
	@Override
	public Order searchOderById(int id) {
		String sql="select *from t_order where orderid=?";
		ResultSet rs=DBUtil.executeQuery(sql, id);//调用辅助类查询方法
		Order order=new Order();
		try {
			if (rs.next()) {
				order.setOrderID(rs.getInt(1));
				order.setUserID(rs.getInt(2));
				order.setCreationTime(rs.getTimestamp(3));
				order.setCompletionTime(rs.getTimestamp(4));
				order.setOrderStuas(rs.getString(5));
				order.setTotalPrice(rs.getDouble(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	
	@Override
	public Order searchOderByDate() {
		
		return null;
	}
	/**
	 * 获取数据库的所有订单：
	 */
	@Override
	public List<Order> getAllOder() {
		String sql="select *from t_order";
		//创建集合
		List<Order> list=new ArrayList<Order>();
		//调用辅助类查询方法
		ResultSet rs=DBUtil.executeQuery(sql);//
		try {
			while (rs.next()) {
				//创建订单对象
				Order order=new Order();
				//给订单编号赋值
				order.setOrderID(rs.getInt(1));
				//给用户号赋值
				order.setUserID(rs.getInt(2));
				//给订单生成日期赋值
				order.setCreationTime(rs.getTimestamp(3));
				//给订单结束日期赋值
				order.setCompletionTime(rs.getTimestamp(4));
				//订单状态赋值
				order.setOrderStuas(rs.getString(5));
				//给订单总价赋值
				order.setTotalPrice(rs.getDouble(6));
				//将订单添加进订单集合
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 生成订单编号
	 */
	@Override
	public int getMaxOrderID() {
		String sql="select max(orderid) from t_order";
		//工具类查询方法
		ResultSet rs=DBUtil.executeQuery(sql);
		try {
			if (rs.next()) {
				return rs.getInt(1)+1;//获取最大编号并加一之后 返回；
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int getUserMaxOrderID(int userID) {
		String sql="select max(orderid) from t_order where userid= ? ";
		ResultSet rs=DBUtil.executeQuery(sql,userID);
		try {
			if (rs.next()) {
				return rs.getInt(1);//获取最大编号之后返回；
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
}
