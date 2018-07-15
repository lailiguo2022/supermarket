package com.oracle.frame.user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oracle.frame.LoginFrame;
import com.oracle.frame.user.personInfo.PersonInfoFrame;
import com.oracle.frame.user.shopping.ShoppingFrame;
import com.oracle.po.User;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserFrame extends JFrame {

	private JPanel contentPane;
	User user;

	/**
	 * 用户界面
	 */
	public UserFrame(User use) {
		user=use;
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
		setTitle("\u7528\u6237\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/**
		 * 用户界面》个人信息按钮
		 */
		JButton btnNewButton = new JButton("\u4E2A\u4EBA\u4E2D\u5FC3");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonInfoFrame frame = new PersonInfoFrame(user);
				frame.setVisible(true);
				UserFrame.this.dispose();
			}
		});
		btnNewButton.setBounds(124, 35, 180, 50);
		contentPane.add(btnNewButton);
		/**
		 * 用户界面》选购商品按钮
		 */
		JButton button = new JButton("\u9009\u8D2D\u5546\u54C1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShoppingFrame frame = new ShoppingFrame(user);
				frame.setVisible(true);
				UserFrame.this.dispose();
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button.setBounds(124, 108, 180, 50);
		contentPane.add(button);
		/**
		 * 用户界面》返回按钮
		 */
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame frame = new LoginFrame();
				frame.setVisible(true);
				UserFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_1.setBounds(124, 176, 180, 50);
		contentPane.add(button_1);
		
		
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
	}

}
