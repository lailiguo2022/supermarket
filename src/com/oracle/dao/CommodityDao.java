package com.oracle.dao;

import java.util.List;

import com.oracle.po.Commodity;

/**
 * ��Ʒ����
 * Dao
 * @author Administrator
 * ����Ʒ CRUD
 */
public interface CommodityDao {
	/**
	 * �����Ʒ
	 */
	public 	int addCommodity(Commodity com);
	/**
	 * ɾ����Ʒ
	 */
	public int deleteCommodity(int comid);
	/**
	 * �޸���Ʒ
	 */
	public int update(Commodity com);
	/**
	 * ���������Ʒ
	 */
	public List<Commodity> getAllCommodities();
	/**
	 * ������Ʒ��ż�����Ʒ
	 */
	public Commodity getCommodityByComid(int id);
	/**
	 * ������Ʒ�����ƺ����ͼ���
	
	 */
	List<Commodity> findCommodity(String comName,int comType);
    /**
     * ������Ʒ������ģ������
    
     */
	public  List<Commodity> commoditiesFuzzySearch(String name);
	/**
	 * ���������ż�����Ʒ
	 */
	public List<Commodity> getCommodityByName(String name);
	/**
	 * ������Ʒ��ģ������
	 */
	public List<Commodity> getByCommodityName(String name);
	
}
