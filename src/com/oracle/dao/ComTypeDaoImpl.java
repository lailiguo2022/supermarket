package com.oracle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.oracle.po.ComType;
import com.oracle.util.DBUtil;

public class ComTypeDaoImpl implements ComTypeDao {

	@Override
	public List<ComType> getAllTypes() {
		String sql= "select * from t_comtype";
		ResultSet rs = DBUtil.executeQuery(sql);
		List<ComType> list = new ArrayList<ComType>();
		try {
			while(rs.next()){
				ComType ct = new ComType();
				ct.setTypeID(rs.getInt("typeid"));
				ct.setTypeName(rs.getString("typename"));
				list.add(ct);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ComType getTypeByID(int typeID) {
		String sql= "select * from t_comtype where typeid = ? ";
		ResultSet rs = DBUtil.executeQuery(sql, typeID);
		ComType ct=new ComType();
		try {
			if(rs.next()){
				ct.setTypeID(rs.getInt("typeid"));
				ct.setTypeName(rs.getString("typename"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}

	@Override
	public int addNewType(ComType comType) {
		String sql = "insert into t_comtype (typename) values (?)";
		return DBUtil.executeUpdate(sql,comType.getTypeName());
	}

	@Override
	public int deleteTypeByID(int typeID) {
		String sql = "delete from t_comtype where typeid = ? ";
		return DBUtil.executeUpdate(sql, typeID);
	}

	@Override
	public int updateType(ComType comType) {
		String sql = "update t_comtype set typename = ? where typeid = ? ";
		return DBUtil.executeUpdate(sql, comType.getTypeName(),comType.getTypeID());
	}

	@Override
	public ComType getTypeByName(String typeName) {
		String sql= "select * from t_comtype where typename = ? ";
		ResultSet rs = DBUtil.executeQuery(sql, typeName);
		ComType ct=new ComType();
		try {
			if(rs.next()){
				ct.setTypeID(rs.getInt("typeid"));
				ct.setTypeName(rs.getString("typename"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
		
	}

}
