package com.oracle.dao;

import java.util.List;

import com.oracle.po.Commodity;

/**
 * 商品管理
 * Dao
 * @author Administrator
 * 对商品 CRUD
 */
public interface CommodityDao {
	/**
	 * 添加商品
	 */
	public 	int addCommodity(Commodity com);
	/**
	 * 删除商品
	 */
	public int deleteCommodity(int comid);
	/**
	 * 修改商品
	 */
	public int update(Commodity com);
	/**
	 * 浏览所有商品
	 */
	public List<Commodity> getAllCommodities();
	/**
	 * 根据商品编号检索商品
	 */
	public Commodity getCommodityByComid(int id);
	/**
	 * 根据商品的名称和类型检索
	
	 */
	List<Commodity> findCommodity(String comName,int comType);
    /**
     * 根据商品和类型模糊检索
    
     */
	public  List<Commodity> commoditiesFuzzySearch(String name);
	/**
	 * 根据种类编号检索商品
	 */
	public List<Commodity> getCommodityByName(String name);
	/**
	 * 根据商品名模糊检索
	 */
	public List<Commodity> getByCommodityName(String name);
	
}
