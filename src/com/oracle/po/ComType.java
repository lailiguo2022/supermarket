package com.oracle.po;

public class ComType {
	
	private int typeID;//��Ʒ���ͱ��
	private String typeName;//��������

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
		return "��Ʒ���ͱ�ţ�" + typeID + ", ��Ʒ�������ƣ�" + typeName;
	}

}
