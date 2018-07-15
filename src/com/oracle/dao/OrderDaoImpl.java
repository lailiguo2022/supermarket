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
	CommodityDao cod=new CommodityDaoImpl();//ʵ������Ʒdao
		/**
		 * ��Ӷ�������
		 */
	@Override
	public boolean addOder(int userID,List<Commodity> list) {
		int orderId=getMaxOrderID();//�û����ͨ���Զ�����
		//sql��ӽ�����������
	String sql="insert into t_order values(?,?,?,?,?,?)";
	//sql��ӽ�������ϸ������
	String sql2="insert into order_commodity values(null,?,?,?,?)";
	int data=0;//
	int	data2=0;
	double totalPrice = 0;
		if (list!=null&&list.size()>0) {
			for (Commodity com : list) {
				totalPrice+=com.getEntryPrice();//���㵱ǰ��Ŀ�۸�
				
			}
			//����������ĸ��·�����data�ġ������ֱ�ֵ
			 data=DBUtil.executeUpdate(sql, 
					orderId,//�������ͨ���Զ�����
					userID,//��ȡ��ǰ�û���id
					new Timestamp(new Date().getTime()),//��ȡ��ǰʱ��
					null,//��������ʱ��
					"δ����",//����״̬
					totalPrice);//�����ܼ�
			 //����Ʒ�����еĶ�����ӽ���ϸ������Ʒ������
				for (Commodity com : list) {
					//����������ĸ��·�����data2�ġ������ֱ�ֵ
					data2=DBUtil.executeUpdate(sql2,orderId,com.getComID(),com.getBuyNumber(),com.getEntryPrice());
				}
		}
		if (data==1&&data2!=0) {
			return true;
		}
		return false;
	}
	/**
	 * ͨ��������Ų��Ҷ�����ϸ
	 */
	@Override
	public Order searchOder_CommodityByOrderID(int id) {
		Order order=searchOderById(id);//�Ȼ�ȡ��id�Ķ���
		String sql="SELECT comid,buynumber,entryprice FROM order_commodity WHERE"
				+ " orderid=? ";
		List<Commodity> list=new ArrayList<Commodity>();//��������
		ResultSet rs=DBUtil.executeQuery(sql, id);//���ù������ѯ����
		try {
			while (rs.next()) {
				int cid=rs.getInt(1);
				Commodity com=cod.getCommodityByComid(cid);//ͨ�����������Ʒ
				com.setBuyNumber(rs.getInt("buynumber"));//������Ʒ�Ĺ��������ֵ
				//com.setEntryPrice(rs.getInt("entryprice"));//������Ʒ�Ľ�ֵ
				list.add(com);//������Ʒ��ӽ�����
			}
			order.setCommoditys(list);//���˼�������Ϊ�������ļ��ϣ�
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}
	
	/**
	 * ɾ����������
	 */
	@Override
	public boolean deleteOderById(int orderid) {
		//sqlɾ�����
		String sql="delete from t_order where orderid=?";
		int data=DBUtil.executeUpdate(sql, orderid);//������������·���
		if (data!=0) {
			return true;//ɾ���ɹ�����true
		}
		return false;//ɾ��ʧ��
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
				order.getTotalPrice(),//���ù��߸��·���
		        order.getOrderID());  //���ù��߸��·���
		if (data!=0) {
			return true;
		}
		return false;
	}
	/**
	 * ͨ����ż���������
	 */
	@Override
	public Order searchOderById(int id) {
		String sql="select *from t_order where orderid=?";
		ResultSet rs=DBUtil.executeQuery(sql, id);//���ø������ѯ����
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
	 * ��ȡ���ݿ�����ж�����
	 */
	@Override
	public List<Order> getAllOder() {
		String sql="select *from t_order";
		//��������
		List<Order> list=new ArrayList<Order>();
		//���ø������ѯ����
		ResultSet rs=DBUtil.executeQuery(sql);//
		try {
			while (rs.next()) {
				//������������
				Order order=new Order();
				//��������Ÿ�ֵ
				order.setOrderID(rs.getInt(1));
				//���û��Ÿ�ֵ
				order.setUserID(rs.getInt(2));
				//�������������ڸ�ֵ
				order.setCreationTime(rs.getTimestamp(3));
				//�������������ڸ�ֵ
				order.setCompletionTime(rs.getTimestamp(4));
				//����״̬��ֵ
				order.setOrderStuas(rs.getString(5));
				//�������ܼ۸�ֵ
				order.setTotalPrice(rs.getDouble(6));
				//��������ӽ���������
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * ���ɶ������
	 */
	@Override
	public int getMaxOrderID() {
		String sql="select max(orderid) from t_order";
		//�������ѯ����
		ResultSet rs=DBUtil.executeQuery(sql);
		try {
			if (rs.next()) {
				return rs.getInt(1)+1;//��ȡ����Ų���һ֮�� ���أ�
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
				return rs.getInt(1);//��ȡ�����֮�󷵻أ�
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
}
