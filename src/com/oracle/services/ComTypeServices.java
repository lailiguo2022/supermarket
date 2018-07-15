package com.oracle.services;

import java.util.List;

import com.oracle.po.ComType;

public interface ComTypeServices {
	/**
	 * ��ȡ������Ʒ���ͼ��ϵķ���
	 * @return ����������Ʒ���͵ļ���
	 */
	List<ComType> getAllTypes();
	
	/**
	 * ͨ�����ͱ�Ų������͵ķ���
	 * @param typeID ���ͱ��
	 * @return ���ҵ������Ͷ���
	 */
	ComType getTypeByID(int typeID);
	
	/**
	 * ͨ���������Ʋ������͵ķ���
	 * @param typeName ��������
	 * @return ���ҵ������Ͷ���
	 */
	ComType getTypeByName(String typeName);
	
	/**
	 * �������͵ķ���
	 * @param comType ��һ�����Ͷ���
	 * @return �����ӳɹ�����1�����ɹ�����0
	 */
	boolean addNewType(ComType comType);
	
	/**
	 * ͨ�����ͱ��ɾ�����͵ķ���
	 * @param typeID ���ͱ��
	 * @return ɾ���ɹ�����1��ɾ��ʧ�ܷ���0
	 */
	boolean deleteTypeByID(int typeID);
	
	/**
	 * ͨ������޸����͵ķ�����
	 * @param comType ����Ҫ�޸ĵ�����
	 * @return �޸ĳɹ�����1���޸�ʧ�ܷ���0
	 */
	boolean updateType(ComType comType);
}
