package com.oracle.services;

import java.util.List;

import com.oracle.po.Commodity;

/**
 * ��Ʒҵ���
 * 
 * @author �Ž�
 *
 */
public interface CommodityServices {
	/**
	 * �����Ʒ
	 */
	public 	boolean addCommodity(Commodity com);
	/**
	 * ɾ����Ʒ
	 */
	public boolean deleteCommodity(int comid);
	/**
	 * �޸���Ʒ
	 */
	public boolean update(Commodity com);
	/**
	 * ���������Ʒ
	 */
	public List<Commodity> getAllCommodities();
	/**
	 * ������Ʒ��ż�����Ʒ
	 */
	public Commodity getCommodityByComid(int id);
	/**
	 * ������Ʒ���ƺ���Ʒ���ͼ�����Ʒ��ʵ�ַ���
	 */
	public List<Commodity> findCommodity(String comName, int comType);
	 /**
     * ������Ʒ������ģ������
     * @return
     */
	public  List<Commodity> commoditiesFuzzySearch(String name);
	/**
	 * ������������������Ʒ
	 */
	public List<Commodity> getCommodityByName(String name);
	/**
	 * ������Ʒ��ģ������
	 */
	public List<Commodity> getByCommodityName(String name);
}
