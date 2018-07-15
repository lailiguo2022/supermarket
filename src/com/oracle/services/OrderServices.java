package com.oracle.services;

import java.util.List;

import com.oracle.po.Commodity;
import com.oracle.po.Order;

public interface OrderServices {
	/**
	 * ���ɶ�������ϸ
	 * @return�ɹ�����true ʧ�ܷ���false��
	 */
	boolean addOder(int userID,List<Commodity> list);
	
	/**
	 * ɾ������
	 * @returnɾ���ɹ�����true ʧ�ܷ���false
	 */
	boolean deleteOderById(int orderid);
	/**
	 * �޸Ķ���
	 * @return �޸ĳɹ�����true ʧ�ܷ���false��
	 */
	boolean UpdateOder(Order order);
	/**
	 * ͨ��������Ų�ѯ������ϸ
	 * @return
	 */
	Order searchOder_CommodityByOrderID(int id);
	/**
	 * ͨ��������Ų�ѯ����
	 * @return����Ҫ�����ı�ŵĶ���
	 */
	Order searchOderById(int id);
	/**
	 * ͨ�������������ڲ�ѯ����
	 * @return����Ҫ�������ڵĶ���
	 */
	Order searchOderByDate();
	/**
	 * ������ж���
	 * @return
	 */
	List<Order> getAllOder();
	/**
	 * ���ɶ������
	 * @return
	 */
	int getMaxOrderID();
	
	/**
	 * ͨ���û���Ż�ȡ���û���󶩵����
	 * @param userID �û����
	 * @return ���û���󶩵����
	 */
	int getUserMaxOrderID(int userID);
}
