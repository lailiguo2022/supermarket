package com.oracle.frame.admin.order;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oracle.po.Order;
import com.oracle.services.OrderServices;
import com.oracle.services.OrderServicesImpl;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateOrderFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JTextField textField;
	private JLabel label_1;
	private Order order;//����ȫ�ֱ����������������β��Ա����¼�ʹ��
	//��������ҵ���Ķ���
	OrderServices os=new OrderServicesImpl();
	//
	OrderManagementFrame om;//����ȫ�ֱ���������������棩�����β��Ա����¼�ʹ��
	/**
	 * ����Ա���桷����������桷�޸Ķ�������
	 */
	public UpdateOrderFrame( Order orde,OrderManagementFrame omf) {
		
		order=orde;//������紫������
		om=omf;//������һ��ҳ�棨��
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//�رմ˽���ʱֻ�ص���ǰ����
		setFont(new Font("΢���ź�", Font.PLAIN, 20));
		setTitle("\u4FEE\u6539\u8BA2\u5355");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDi = new JLabel("\u8BA2\u5355\u72B6\u6001");
		lblDi.setHorizontalAlignment(SwingConstants.CENTER);
		lblDi.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		lblDi.setBounds(110, 115, 76, 40);
		contentPane.add(lblDi);
		/**
		 * ����Ա���桷����������桷�޸Ķ������桷������
		 */
		comboBox = new JComboBox();
		comboBox.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u672A\u4ED8\u6B3E", "\u5DF2\u4ED8\u6B3E", "\u5DF2\u53D1\u8D27"}));
		//��ԭ����״̬��ֵ��������
		comboBox.setSelectedItem(order.getOrderStuas());
		
		comboBox.setBounds(200, 117, 100, 40);
		contentPane.add(comboBox);
		
		JLabel label = new JLabel("\u8BA2\u5355\u603B\u4EF7");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label.setBounds(110, 197, 76, 40);
		contentPane.add(label);
		/**
		 * ����Ա���桷����������桷�޸Ķ������桷�޸Ķ����۸��ı���
		 */
		textField = new JTextField();
		textField.setBounds(200, 199, 76, 40);
		//��ԭ�����ܼ۸�ֵ���ı���
		textField.setText(Double.toString(order.getTotalPrice()));
		//���������Ϊδ����������޸��ܼ� ���򲻿��޸�
		if (comboBox.getSelectedItem().equals("δ����")) {
			textField.setEnabled(true);
			
		}else {
			textField.setEnabled(false);
		}
		
		contentPane.add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("\u5143");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_1.setBounds(274, 197, 39, 40);
		contentPane.add(label_1);
		/**
		 * ����Ա���桷����������桷�޸Ķ������桷�޸İ�ť
		 */
		JButton button = new JButton("\u786E\u8BA4\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ȡ�������ֵ���ı����ֵ   ����ֵ����order
				order.setOrderStuas((String)comboBox.getSelectedItem());
				order.setTotalPrice(Double.parseDouble(textField.getText()));
				//��ʾ�޸���Ϣ
				JOptionPane.showConfirmDialog(null, "�����޸�", "", JOptionPane.YES_NO_OPTION);
				//���ö���ҵ�����޸ķ���
				if (os.UpdateOder(order)) {
					om.dtm.setRowCount(0);
					om.addRowData();//���±�����
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					
					UpdateOrderFrame.this.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "�޸�ʧ��");
				}
			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button.setBounds(164, 334, 112, 40);
		contentPane.add(button);
		
		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
	}
}
