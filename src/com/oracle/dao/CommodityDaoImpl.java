package com.oracle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.po.Commodity;
import com.oracle.util.DBUtil;


/**
 * ��Ʒ���� ʵ����
 * 
 * @author Administrator
 *
 */
public class CommodityDaoImpl implements CommodityDao {
	/**
	 * �����Ʒ��ʵ�ַ���
	 */
	@Override
	public int addCommodity(Commodity com) {
		// sql��䣬�����ṩsql�������ݿ�
		String insert = "insert into t_commodity (comid,comname,comprice,comcount,typeid)" 
						+ "values(?,?,?,?,?)";
		return DBUtil.executeUpdate(insert,
				// ��ȡ��Ʒ������������
				com.getComID(), 
				com.getComName(),
				com.getComPrice(), 
				com.getCount(), 
				com.getTypeID());
	}

	/**
	 * ɾ����Ʒ��ʵ�ַ���
	 */
	@Override
	public int deleteCommodity(int id) {
		// sql��䣬�����ṩsql�������ݿ�
		String delete = "delete from t_commodity where comid=?";
		return DBUtil.executeUpdate(delete, id);
	}

	/**
	 * �޸���Ʒ��ʵ�ַ���
	 */
	@Override
	public int update(Commodity com) {
		// sql��䣬�����ṩsql�������ݿ�
		String update = "update t_commodity set comname=?,comprice=?,comcount=?,typeid=? where comid=?";
		return DBUtil.executeUpdate(update, com.getComName(), com.getComPrice(), com.getCount(), com.getTypeID(),
				com.getComID());
	}

	/**
	 * ���������Ʒ��ʵ�ַ���
	 */
	@Override
	public List<Commodity> getAllCommodities() {
		// ��������commodities ���ڽ�������
		List<Commodity> commodities = new ArrayList<Commodity>();
		// sql��䣬�����ṩsql�������ݿ�
		String select = "SELECT  * FROM t_commodity a, t_comtype b WHERE A.typeid=B.typeid ";
		// ���ù����෵�ؽ�����ķ���
		ResultSet rs = DBUtil.executeQuery(select);
		try {
			// ��������
			while (rs.next()) {
				Commodity com = new Commodity();// ������Ʒ���󣬴ӽ�����л�ȡ��Ʒ������Ӧ���ֶ�
				com.setComID(rs.getInt("comid"));
				com.setComName(rs.getString("comname"));
				com.setComPrice(rs.getDouble("comprice"));
				com.setCount(rs.getInt("comcount"));
				com.setTypeName(rs.getString("typename"));
				com.setTypeID(rs.getInt("typeid"));
				
				// ������ commodities �������
				commodities.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commodities;
	}

	/**
	 * ������Ʒ��ż�����Ʒ
	 */
	@Override
	public Commodity getCommodityByComid(int id) {
		Commodity com=null;//����һ������
		//sql��䣬�����ṩsql�������ݿ�
		String select="select *from t_commodity a,t_comtype b where a.typeid = b.typeid AND comid=?";
		ResultSet rs=DBUtil.executeQuery(select,id);//���ù������executeQuery()����
		try {
			//��������
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
	 * ������Ʒ���ƺ����������Ʒ
	 */
	@Override
	public List<Commodity> findCommodity(String comName,int comType){
		//��ȡ������Ʒ��sql���
		String sql = "SELECT comid,a.typeid,comname,comprice,comcount,typename FROM  "
				+ "t_commodity a,t_comtype b WHERE a.typeid=b.typeid AND a.typeid= ? " 
				+" AND comname LIKE ?";
		     String str = "%"+comName+"%";
		//���ù�������������
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
     * ��Ʒģ������
     */
	@Override
	public List<Commodity> commoditiesFuzzySearch(String name) {
		List<Commodity> list = new ArrayList<Commodity>();//������Ʒ����
		//��ȡģ��������Ʒ���Ƶ�sql���
		String  Commodity = "SELECT * FROM t_commodity a , t_comtype b WHERE a.typeid=b.typeid"
				+ " HAVING typename LIKE ? OR comname LIKE ?";
		String names = "%"+name+"%";
		//���ù�������������
		ResultSet rs = DBUtil.executeQuery(Commodity, names,names);
		//��������
		if(name!=null){
			try {
				while(rs.next()){
					Commodity  com = new Commodity();//������Ʒ ��ֵ����
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
     *������Ʒ�������ּ���
     */
	@Override
	public List<Commodity> getCommodityByName(String name) {
		//��ȡ������Ʒ���������sql���
		String sql = "SELECT * FROM t_commodity a INNER  JOIN  t_comtype b ON  a.typeid=b.typeid AND  typename=?";
		//���ù�������������
		 ResultSet rs  =  DBUtil.executeQuery(sql, name);
		 List<Commodity> li = new ArrayList<Commodity>();
		 
		 //��������
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
      * ������Ʒ��ģ������
      */
	@Override
	public List<Commodity> getByCommodityName(String name) {
		//��ȡ������Ʒ����ģ��������sql���
		String sql = " SELECT * FROM t_commodity WHERE comname LIKE ?";
		String names = "%"+name+"%";
		List<Commodity> list = new ArrayList<Commodity>();//������Ʒ����
		//���ù�������������
		ResultSet rs = DBUtil.executeQuery(sql,names);
		//��������
		if(!name.equals("")){
			try {
				while(rs.next()){
					Commodity  com = new Commodity();//������Ʒ ��ֵ����
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

	
