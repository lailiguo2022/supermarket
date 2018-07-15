package com.oracle.po;

/**
 * ��Ʒʵ����
 * 
 * @author Administrator
 *
 */
public class Commodity {
	private int comID; // ����Ʒ��ţ�����
	private String comName; // ����Ʒ����
	private double comPrice; // ���۸�
	private int typeID; // ������������
	private int count; // ����Ʒ��棩
	private int buyNumber=1;//��������
	private double entryPrice;//��Ŀ�۸�
	private String typeName;//��������

	/**
	 * �޲ι���
	 */
	public Commodity() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * �вι���
	 * 
	 * @param comid
	 * @param comname
	 * @param comprice
	 * @param typeid
	 * @param count
	 */
	public Commodity(int comID, String comName, double comPrice, int typeID, int count) {
		super();
		this.comID = comID;
		this.comName = comName;
		this.comPrice = comPrice;
		this.typeID = typeID;
		this.count = count;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(int buyNumber) {
		this.buyNumber = buyNumber;
	}

	public double getEntryPrice() {
		return entryPrice = buyNumber * comPrice;
	}


	public int getComID() {
		return comID;
	}

	public void setComID(int comID) {
		this.comID = comID;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public double getComPrice() {
		return comPrice;
	}

	public void setComPrice(double comPrice) {
		this.comPrice = comPrice;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "��Ʒ��ţ�" + comID + "��Ʒ���ͣ�"+typeName+"��Ʒ���ƣ�" + comName + "��Ʒ�۸�" + comPrice +  "��Ʒ������" + count+"\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + comID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commodity other = (Commodity) obj;
		if (comID != other.comID)
			return false;
		return true;
	}
	
	

}
