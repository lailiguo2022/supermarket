package com.oracle.frame.admin;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oracle.frame.LoginFrame;
import com.oracle.frame.admin.commodity.CommodityManagementFrame;
import com.oracle.frame.admin.order.OrderManagementFrame;
import com.oracle.frame.admin.userinfo.UserInfoFrame;
import com.oracle.po.User;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame {

	private JPanel contentPane;


	/**
	 * ����Ա����
	 */
	public AdminFrame() {
		
		setFont(new Font("΢���ź�", Font.PLAIN, 20));
		setTitle("\u7BA1\u7406\u5458\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/**
		 * ����Ա���桷�û���Ϣ����ť
		 */
		JButton btnNewButton = new JButton("\u7528\u6237\u4FE1\u606F\u7BA1\u7406");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInfoFrame frame = new UserInfoFrame();
				frame.setVisible(true);
				AdminFrame.this.dispose();
			}
		});
		btnNewButton.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		btnNewButton.setBounds(119, 51, 180, 50);
		contentPane.add(btnNewButton);
		/**
		 * ����Ա���桷��Ʒ����ť
		 */
		JButton button = new JButton("\u5546\u54C1\u7BA1\u7406");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommodityManagementFrame frame = new CommodityManagementFrame();
				frame.setVisible(true);
				AdminFrame.this.dispose();
			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button.setBounds(119, 124, 180, 50);
		contentPane.add(button);
		/**
		 * ����Ա���桷��������ť
		 */
		JButton button_1 = new JButton("\u8BA2\u5355\u7BA1\u7406");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderManagementFrame frame = new OrderManagementFrame();
				frame.setVisible(true);
				AdminFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_1.setBounds(119, 197, 180, 50);
		contentPane.add(button_1);
		/**
		 * ����Ա���桷����ͳ�ư�ť
		 */
		JButton button_2 = new JButton("\u9500\u552E\u7EDF\u8BA1");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ԥ����չ
			}
		});
		button_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_2.setBounds(119, 270, 180, 50);
		contentPane.add(button_2);
		/**
		 * ����Ա���桷���ذ�ť
		 */
		JButton button_3 = new JButton("\u8FD4\u56DE");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame frame = new LoginFrame();
				frame.setVisible(true);
				AdminFrame.this.dispose();
			}
		});
		button_3.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_3.setBounds(119, 344, 180, 50);
		contentPane.add(button_3);
		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
	}
}
