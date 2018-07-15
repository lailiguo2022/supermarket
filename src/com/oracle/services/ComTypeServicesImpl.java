package com.oracle.services;

import java.util.List;

import com.oracle.dao.ComTypeDao;
import com.oracle.dao.ComTypeDaoImpl;
import com.oracle.po.ComType;

public class ComTypeServicesImpl implements ComTypeServices {
	ComTypeDao ctd =new ComTypeDaoImpl();
	
	@Override
	public List<ComType> getAllTypes() {
		
		return ctd.getAllTypes();
	}

	@Override
	public ComType getTypeByID(int typeID) {
		
		return ctd.getTypeByID(typeID);
	}

	@Override
	public ComType getTypeByName(String typeName) {
		
		return ctd.getTypeByName(typeName);
	}

	@Override
	public boolean addNewType(ComType comType) {
		
		return ctd.addNewType(comType)!=0?true:false;
	}

	@Override
	public boolean deleteTypeByID(int typeID) {
		
		return ctd.deleteTypeByID(typeID)!=0?true:false;
	}

	@Override
	public boolean updateType(ComType comType) {
		
		return ctd.updateType(comType)!=0?true:false;
	}

}
