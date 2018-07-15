package com.oracle.frame.user.shopping;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.oracle.po.Commodity;
import com.oracle.po.Order;
import com.oracle.po.User;
import com.oracle.services.OrderServicesImpl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmOrderFrame extends JFrame {
	
	// �����û�����
	User user;
	// ����ҵ������
	OrderServicesImpl service = new OrderServicesImpl();
	// �������ģ��
	DefaultTableModel dtm;
	private JScrollPane scrollPane;
	private JTextField textField_name;
	private JTextField textField_address;
	private JTextField textField_telephonenumber;
	private JTextField textField_totalPrice;
	private JButton button_confirm;
	private JButton button_cancel;
	private JTable table_coms;
	List<Commodity> list;
	Order order1;

	/**
	 * �û����桷ѡ����Ʒ���ύ������ȷ�ϸ������
	 */
	public ConfirmOrderFrame(User use) {
		user=use;
		setFont(new Font("΢���ź�", Font.PLAIN, 20));
		setTitle("\u786E\u8BA4\u8BA2\u5355");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 600);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		// �û������ı���
		textField_name = new JTextField();
		textField_name.setEditable(false);
		textField_name.setBounds(111, 54, 107, 37);
		textField_name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(" \u7528\u6237\u59D3\u540D");
		lblNewLabel.setBounds(39, 55, 62, 35);
		// �ջ���ַ�ı���
		textField_address = new JTextField();
		textField_address.setEditable(false);
		textField_address.setBounds(111, 255, 107, 37);
		textField_address.setColumns(10);
		// �ջ���ַ��ǩ
		JLabel label = new JLabel(" \u6536\u8D27\u5730\u5740");
		label.setBounds(39, 256, 62, 35);
		contentPane.setLayout(null);
		contentPane.add(label);
		contentPane.add(textField_address);
		contentPane.add(lblNewLabel);
		contentPane.add(textField_name);
		// ��ϵ��ʽ��ǩ
		JLabel label_1 = new JLabel(" \u8054\u7CFB\u65B9\u5F0F");
		label_1.setBounds(39, 152, 62, 35);
		contentPane.add(label_1);
		// ��ϵ��ʽ�ı���
		textField_telephonenumber = new JTextField();
		textField_telephonenumber.setEditable(false);
		textField_telephonenumber.setColumns(10);
		textField_telephonenumber.setBounds(111, 151, 107, 37);
		contentPane.add(textField_telephonenumber);
		// �������
		scrollPane = new JScrollPane();
		scrollPane.setBounds(268, 55, 256, 335);
		contentPane.add(scrollPane);
		// �ܼ۱�ǩ
		JLabel label_2 = new JLabel("   \u603B\u4EF7");
		label_2.setBounds(39, 355, 62, 35);
		contentPane.add(label_2);
		// �ܼ��ı���
		textField_totalPrice = new JTextField();
		textField_totalPrice.setEditable(false);
		textField_totalPrice.setColumns(10);
		textField_totalPrice.setBounds(111, 354, 107, 37);
		contentPane.add(textField_totalPrice);
		// �ײ����
		JPanel panel = new JPanel();
		panel.setBounds(23, 450, 501, 54);
		contentPane.add(panel);
		panel.setLayout(null);
		// ȷ�ϰ�ť
		button_confirm = new JButton("\u786E\u8BA4\u8BA2\u5355");
		// �¼�������
		button_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmOrder();// ����ȷ�϶����ķ���
				// ����ȷ�ϸ������
				ConfirmPaymentFrame frame = new ConfirmPaymentFrame(order1);
				frame.setVisible(true);
				// �رյ�ǰ����
				ConfirmOrderFrame.this.dispose();
			}
		});
		button_confirm.setBounds(75, 10, 93, 34);
		panel.add(button_confirm);
		// ȡ����ť
		button_cancel = new JButton("\u53D6\u6D88\u8BA2\u5355");
		// �����¼�
		button_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ������Ʒ�������
				ShoppingFrame frame = new ShoppingFrame(user);
				frame.setVisible(true);
				// �رյ�ǰ����
				ConfirmOrderFrame.this.dispose();
			}
		});
		button_cancel.setBounds(304, 10, 93, 34);
		panel.add(button_cancel);
		
		// �������
		table_coms = new JTable();
		scrollPane.setViewportView(table_coms);
		//�������ģ�Ͳ������ģ�͸�ֵ
		dtm = new DefaultTableModel();
		dtm.addColumn("��Ʒ����");
		dtm.addColumn("��Ʒ����");
		dtm.addColumn("��������");
		dtm.addColumn("���");
		table_coms.setModel(dtm);// �������Ӹ������ģ��
		addRowdata();

		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);

	}

	/**
	 * �����ģ��������ݷ���
	 */
	private void addRowdata() {
		
		textField_name.setText(user.getName());
		textField_address.setText(user.getAddress());
		textField_telephonenumber.setText(user.getTelephoneNumber());
		
		double totalPrice = 0;
		//��ȡ�ϲ���洫����user�Ķ������ϣ�����ֻ����һ��������û�ж������
		List<Order> orders = user.getOrders();
		//�����������ϣ���ȡΨһ��������������Ʒ����
		for (Order order : orders) {
			list =order.getCommoditys();
			order1=order;
		}
		//������Ʒ���ϣ���ӽ����ģ����
		for (Commodity commodity : list) {
			dtm.addRow(new Object[]{
				commodity.getComName(),
				commodity.getComPrice(),
				commodity.getBuyNumber(),
				commodity.getEntryPrice()
			});
			totalPrice+=commodity.getEntryPrice();
		}
		textField_totalPrice.setText(Double.toString(totalPrice));
		order1.setTotalPrice(totalPrice);
	}

	/**
	 * ȷ�϶����ķ���
	 */
	public void confirmOrder() {
		if(service.addOder(user.getUserID(),list)){
			order1.setOrderID(service.getUserMaxOrderID(user.getUserID()));
		}else{
			javax.swing.JOptionPane.showMessageDialog(null, "��������ʧ��");
		}
	}
		
}
