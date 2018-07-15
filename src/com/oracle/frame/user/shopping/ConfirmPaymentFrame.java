package com.oracle.frame.user.shopping;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.oracle.frame.user.UserFrame;
import com.oracle.po.Order;
import com.oracle.services.OrderServicesImpl;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;

public class ConfirmPaymentFrame extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField_orderID;
	private JTextField textField_totalPrice;
	Order order;
	OrderServicesImpl service = new OrderServicesImpl();
	UserService usi=new UserServiceImpl();

	/**
	 * Create the frame.
	 */
	public ConfirmPaymentFrame(Order orde) {
		order=orde;
		setTitle("\u786E\u8BA4\u4ED8\u6B3E");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);
		// ��������ı���
		JLabel lblNewLabel = new JLabel("  \u8BA2\u5355\u7F16\u53F7");
		lblNewLabel.setBounds(146, 76, 81, 27);
		panel_1.add(lblNewLabel);
		// �ı��򲻿ɸ���
		textField_orderID = new JTextField();
		textField_orderID.setEditable(false);
		textField_orderID.setBounds(267, 76, 116, 27);
		panel_1.add(textField_orderID);
		textField_orderID.setColumns(10);
		// �����ܼ��ı���
		JLabel label = new JLabel("  \u8BA2\u5355\u603B\u4EF7");
		label.setBounds(146, 142, 81, 27);
		panel_1.add(label);
		// �ı��򲻿ɸ���
		textField_totalPrice = new JTextField();
		textField_totalPrice.setEditable(false);
		textField_totalPrice.setColumns(10);
		textField_totalPrice.setBounds(267, 142, 116, 27);
		panel_1.add(textField_totalPrice);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(450, 42));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.SOUTH);
		// ȷ�ϸ��ť
		JButton button_1 = new JButton("\u786E\u8BA4\u4ED8\u6B3E");
		// �¼�������(�ӿ�)
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmPayment();// ȷ�ϸ����
			}
		});
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_1.setBounds(444, 6, 100, 31);
		panel.add(button_1);
		// ȡ�����ť
		JButton button_2 = new JButton("\u53D6\u6D88");
		// �¼�������(�ӿ�)
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame frame = new UserFrame(usi.getUserById(order.getUserID()));
				frame.setVisible(true);
				// �رյ�ǰ����
				ConfirmPaymentFrame.this.dispose();
			}
		});
		button_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_2.setBounds(558, 6, 100, 31);
		panel.add(button_2);
		
		textField_orderID.setText(Integer.toString(order.getOrderID()));
		textField_totalPrice.setText(Double.toString(order.getTotalPrice()));;
	}

	/**
	 * ȷ�ϸ����
	 */
	public void confirmPayment() {
		order.setOrderStuas("�Ѹ���");
		service.UpdateOder(order);
		javax.swing.JOptionPane.showMessageDialog(null, "����ɹ�");
		UserFrame frame = new UserFrame(usi.getUserById(order.getUserID()));
		frame.setVisible(true);
		// �رյ�ǰ����
		ConfirmPaymentFrame.this.dispose();
	}
	
}
