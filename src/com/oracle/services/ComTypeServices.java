package com.oracle.services;

import java.util.List;

import com.oracle.po.ComType;

public interface ComTypeServices {
	/**
	 * 获取所有商品类型集合的方法
	 * @return 包含所有商品类型的集合
	 */
	List<ComType> getAllTypes();
	
	/**
	 * 通过类型编号查找类型的方法
	 * @param typeID 类型编号
	 * @return 查找到的类型对象
	 */
	ComType getTypeByID(int typeID);
	
	/**
	 * 通过类型名称查找类型的方法
	 * @param typeName 类型名称
	 * @return 查找到的类型对象
	 */
	ComType getTypeByName(String typeName);
	
	/**
	 * 新增类型的方法
	 * @param comType 传一个类型对象
	 * @return 如果添加成功返回1，不成功返回0
	 */
	boolean addNewType(ComType comType);
	
	/**
	 * 通过类型编号删除类型的方法
	 * @param typeID 类型编号
	 * @return 删除成功返回1，删除失败返回0
	 */
	boolean deleteTypeByID(int typeID);
	
	/**
	 * 通过编号修改类型的方法，
	 * @param comType 传回要修改的类型
	 * @return 修改成功返回1，修改失败返回0
	 */
	boolean updateType(ComType comType);
}
