package com.oracle.services;

import java.util.List;

import com.oracle.dao.CommodityDaoImpl;
import com.oracle.dao.UserDao;
import com.oracle.dao.UserDaoImpl;
import com.oracle.po.Commodity;
/**
 * ҵ���ʵ��
 * @author �Ž� 
 *
 */
public class CommodityServicesImpl implements CommodityServices {
	//������ƷDao������Ķ���
	CommodityDaoImpl cdi = new CommodityDaoImpl();
	/**
	 * �����Ʒ
	 */
	@Override
	public boolean addCommodity(Commodity com) {
		
		return cdi.addCommodity(com)!=0?true:false;
	}
    /**
     * ɾ����Ʒ
     */
	@Override
	public boolean deleteCommodity(int comid) {
		
		return cdi.deleteCommodity(comid)!=0?true:false;
	}
    /**
     * �޸���Ʒ
     */
	@Override
	public boolean update(Commodity com) {
		
		return cdi.update(com)!=0?true:false;
	}
    /**
     * ��ȡ������Ʒ
     */
	@Override
	public List<Commodity> getAllCommodities() {
		
		return cdi.getAllCommodities();
	}
    /**
     * ͨ����Ʒ��Ż�ȡ��Ʒ
     */
	@Override
	public Commodity getCommodityByComid(int id) {
		
		return cdi.getCommodityByComid(id);
	}
	/**
	 * ������Ʒ���ƺ���Ʒ���ͼ�����Ʒ��ʵ�ַ���
	 */
	@Override
	public List<Commodity> findCommodity(String comName, int comType) {
		
		return cdi.findCommodity(comName, comType);
	}
	 /**
     * ��Ʒģ������
     */
	@Override
	public List<Commodity> commoditiesFuzzySearch(String name) {
		
		return cdi.commoditiesFuzzySearch(name);
	}
	/**
	 * ������Ʒ�������ּ���
	 */
	@Override
	public List<Commodity> getCommodityByName(String name) {
		
		return cdi.getCommodityByName(name);
	}
	/**
	 * ������Ʒ����ģ������
	 */
	@Override
	public List<Commodity> getByCommodityName(String name) {
		
		return cdi.getByCommodityName(name);
	}
	
	
}
