package com.oracle.po;

/**
 * 商品实体类
 * 
 * @author Administrator
 *
 */
public class Commodity {
	private int comID; // （商品编号）主键
	private String comName; // （商品名）
	private double comPrice; // （价格）
	private int typeID; // （种类表外键）
	private int count; // （商品库存）
	private int buyNumber=1;//购买数量
	private double entryPrice;//条目价格
	private String typeName;//类型名称

	/**
	 * 无参构造
	 */
	public Commodity() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 有参构造
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
		return "商品编号：" + comID + "商品类型："+typeName+"商品名称：" + comName + "商品价格：" + comPrice +  "商品个数：" + count+"\n";
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
