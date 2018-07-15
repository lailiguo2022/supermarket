package com.oracle.services;

import java.util.List;

import com.oracle.dao.CommodityDaoImpl;
import com.oracle.dao.UserDao;
import com.oracle.dao.UserDaoImpl;
import com.oracle.po.Commodity;
/**
 * 业务层实现
 * @author 张杰 
 *
 */
public class CommodityServicesImpl implements CommodityServices {
	//创建商品Dao层子类的对象
	CommodityDaoImpl cdi = new CommodityDaoImpl();
	/**
	 * 添加商品
	 */
	@Override
	public boolean addCommodity(Commodity com) {
		
		return cdi.addCommodity(com)!=0?true:false;
	}
    /**
     * 删除商品
     */
	@Override
	public boolean deleteCommodity(int comid) {
		
		return cdi.deleteCommodity(comid)!=0?true:false;
	}
    /**
     * 修改商品
     */
	@Override
	public boolean update(Commodity com) {
		
		return cdi.update(com)!=0?true:false;
	}
    /**
     * 获取所有商品
     */
	@Override
	public List<Commodity> getAllCommodities() {
		
		return cdi.getAllCommodities();
	}
    /**
     * 通过商品编号获取商品
     */
	@Override
	public Commodity getCommodityByComid(int id) {
		
		return cdi.getCommodityByComid(id);
	}
	/**
	 * 根据商品名称和商品类型检索商品的实现方法
	 */
	@Override
	public List<Commodity> findCommodity(String comName, int comType) {
		
		return cdi.findCommodity(comName, comType);
	}
	 /**
     * 商品模糊检索
     */
	@Override
	public List<Commodity> commoditiesFuzzySearch(String name) {
		
		return cdi.commoditiesFuzzySearch(name);
	}
	/**
	 * 根据商品种类名字检索
	 */
	@Override
	public List<Commodity> getCommodityByName(String name) {
		
		return cdi.getCommodityByName(name);
	}
	/**
	 * 根据商品名称模糊检索
	 */
	@Override
	public List<Commodity> getByCommodityName(String name) {
		
		return cdi.getByCommodityName(name);
	}
	
	
}
