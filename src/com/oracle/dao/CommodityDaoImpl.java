package com.oracle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.po.Commodity;
import com.oracle.util.DBUtil;


/**
 * 商品管理 实现类
 * 
 * @author Administrator
 *
 */
public class CommodityDaoImpl implements CommodityDao {
	/**
	 * 添加商品的实现方法
	 */
	@Override
	public int addCommodity(Commodity com) {
		// sql语句，负责提供sql语句给数据库
		String insert = "insert into t_commodity (comid,comname,comprice,comcount,typeid)" 
						+ "values(?,?,?,?,?)";
		return DBUtil.executeUpdate(insert,
				// 获取商品对象的五个属性
				com.getComID(), 
				com.getComName(),
				com.getComPrice(), 
				com.getCount(), 
				com.getTypeID());
	}

	/**
	 * 删除商品的实现方法
	 */
	@Override
	public int deleteCommodity(int id) {
		// sql语句，负责提供sql语句给数据库
		String delete = "delete from t_commodity where comid=?";
		return DBUtil.executeUpdate(delete, id);
	}

	/**
	 * 修改商品的实现方法
	 */
	@Override
	public int update(Commodity com) {
		// sql语句，负责提供sql语句给数据库
		String update = "update t_commodity set comname=?,comprice=?,comcount=?,typeid=? where comid=?";
		return DBUtil.executeUpdate(update, com.getComName(), com.getComPrice(), com.getCount(), com.getTypeID(),
				com.getComID());
	}

	/**
	 * 浏览所有商品的实现方法
	 */
	@Override
	public List<Commodity> getAllCommodities() {
		// 创建集合commodities 用于接收数据
		List<Commodity> commodities = new ArrayList<Commodity>();
		// sql语句，负责提供sql语句给数据库
		String select = "SELECT  * FROM t_commodity a, t_comtype b WHERE A.typeid=B.typeid ";
		// 调用工具类返回结果集的方法
		ResultSet rs = DBUtil.executeQuery(select);
		try {
			// 处理结果集
			while (rs.next()) {
				Commodity com = new Commodity();// 创建商品对象，从结果集中获取商品属性相应的字段
				com.setComID(rs.getInt("comid"));
				com.setComName(rs.getString("comname"));
				com.setComPrice(rs.getDouble("comprice"));
				com.setCount(rs.getInt("comcount"));
				com.setTypeName(rs.getString("typename"));
				com.setTypeID(rs.getInt("typeid"));
				
				// 给集合 commodities 添加数据
				commodities.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commodities;
	}

	/**
	 * 根据商品编号检索商品
	 */
	@Override
	public Commodity getCommodityByComid(int id) {
		Commodity com=null;//声明一个变量
		//sql语句，负责提供sql语句给数据库
		String select="select *from t_commodity a,t_comtype b where a.typeid = b.typeid AND comid=?";
		ResultSet rs=DBUtil.executeQuery(select,id);//调用工具类的executeQuery()方法
		try {
			//处理结果集
			if (rs.next()) {
				com=new Commodity();
				com.setComID(rs.getInt("comid"));
				com.setComName(rs.getString("comname"));
				com.setComPrice(rs.getDouble("comprice"));
				com.setTypeID(rs.getInt("typeid"));
				com.setCount(rs.getInt("comcount"));
				com.setTypeName(rs.getString("typename"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return com;
	}
	/**
	 * 根据商品名称和种类检索商品
	 */
	@Override
	public List<Commodity> findCommodity(String comName,int comType){
		//获取搜索商品的sql语句
		String sql = "SELECT comid,a.typeid,comname,comprice,comcount,typename FROM  "
				+ "t_commodity a,t_comtype b WHERE a.typeid=b.typeid AND a.typeid= ? " 
				+" AND comname LIKE ?";
		     String str = "%"+comName+"%";
		//调用工具类的浏览方法
		 ResultSet rs  = DBUtil.executeQuery(sql, comType,str);
		 List<Commodity> list = new ArrayList<Commodity>();
		 if(comName!=null){
			 try {
					while(rs.next()){
						 Commodity com = new Commodity();
						 com.setComID(rs.getInt("comid"));
						 com.setTypeID(rs.getInt("typeid"));
						 com.setComName(rs.getString("comname"));
						 com.setComPrice(rs.getDouble("comprice"));
						 com.setCount(rs.getInt("comcount"));
						 com.setTypeName(rs.getString("typename"));
						 list.add(com);
					 }
				} catch (SQLException e) {
					e.printStackTrace();
				}
		 }
		 
		 return list;
	}
    /**
     * 商品模糊检索
     */
	@Override
	public List<Commodity> commoditiesFuzzySearch(String name) {
		List<Commodity> list = new ArrayList<Commodity>();//创建商品集合
		//获取模糊检索商品名称的sql语句
		String  Commodity = "SELECT * FROM t_commodity a , t_comtype b WHERE a.typeid=b.typeid"
				+ " HAVING typename LIKE ? OR comname LIKE ?";
		String names = "%"+name+"%";
		//调用工具类的浏览方法
		ResultSet rs = DBUtil.executeQuery(Commodity, names,names);
		//处理结果集
		if(name!=null){
			try {
				while(rs.next()){
					Commodity  com = new Commodity();//创建商品 赋值属性
					com.setComID(rs.getInt("comid"));
					com.setComName(rs.getString("comname"));
					com.setComPrice(rs.getDouble("comprice"));
					com.setCount(rs.getInt("comcount"));
					com.setTypeName(rs.getString("typename"));
					list.add(com);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	/**
     *根据商品种类名字检索
     */
	@Override
	public List<Commodity> getCommodityByName(String name) {
		//获取根据商品种类检索的sql语句
		String sql = "SELECT * FROM t_commodity a INNER  JOIN  t_comtype b ON  a.typeid=b.typeid AND  typename=?";
		//调用工具类的浏览方法
		 ResultSet rs  =  DBUtil.executeQuery(sql, name);
		 List<Commodity> li = new ArrayList<Commodity>();
		 
		 //处理结果集
		 try {
			while(rs.next()){
				Commodity com =	new Commodity();
				com.setComID(rs.getInt("comid"));
				com.setComName(rs.getString("comname"));
				com.setComPrice(rs.getDouble("comprice"));
				com.setCount(rs.getInt("comcount"));
				li.add(com);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
     /**
      * 根据商品名模糊检索
      */
	@Override
	public List<Commodity> getByCommodityName(String name) {
		//获取根据商品名称模糊检索的sql语句
		String sql = " SELECT * FROM t_commodity WHERE comname LIKE ?";
		String names = "%"+name+"%";
		List<Commodity> list = new ArrayList<Commodity>();//创建商品集合
		//调用工具类的浏览方法
		ResultSet rs = DBUtil.executeQuery(sql,names);
		//处理结果集
		if(!name.equals("")){
			try {
				while(rs.next()){
					Commodity  com = new Commodity();//创建商品 赋值属性
					com.setComID(rs.getInt("comid"));
					com.setComName(rs.getString("comname"));
					com.setComPrice(rs.getDouble("comprice"));
					com.setCount(rs.getInt("comcount"));
					list.add(com);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}

	
