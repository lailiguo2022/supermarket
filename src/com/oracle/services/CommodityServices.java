package com.oracle.services;

import java.util.List;

import com.oracle.po.Commodity;

/**
 * 商品业务层
 * 
 * @author 张杰
 *
 */
public interface CommodityServices {
	/**
	 * 添加商品
	 */
	public 	boolean addCommodity(Commodity com);
	/**
	 * 删除商品
	 */
	public boolean deleteCommodity(int comid);
	/**
	 * 修改商品
	 */
	public boolean update(Commodity com);
	/**
	 * 浏览所有商品
	 */
	public List<Commodity> getAllCommodities();
	/**
	 * 根据商品编号检索商品
	 */
	public Commodity getCommodityByComid(int id);
	/**
	 * 根据商品名称和商品类型检索商品的实现方法
	 */
	public List<Commodity> findCommodity(String comName, int comType);
	 /**
     * 根据商品和类型模糊检索
     * @return
     */
	public  List<Commodity> commoditiesFuzzySearch(String name);
	/**
	 * 根据种类编号名检索商品
	 */
	public List<Commodity> getCommodityByName(String name);
	/**
	 * 根据商品名模糊检索
	 */
	public List<Commodity> getByCommodityName(String name);
}
