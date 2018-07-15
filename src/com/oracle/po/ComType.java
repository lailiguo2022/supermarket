package com.oracle.po;

public class ComType {
	
	private int typeID;//商品类型编号
	private String typeName;//类型名称

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public ComType(int typeID, String typeName) {
		super();
		this.typeID = typeID;
		this.typeName = typeName;
	}

	public ComType() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "商品类型编号：" + typeID + ", 商品类型名称：" + typeName;
	}

}
