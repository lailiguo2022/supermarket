package com.oracle.frame.user.personInfo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oracle.frame.user.UserFrame;
import com.oracle.po.User;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonInfoFrame extends JFrame {

	private JPanel contentPane;			

	/**
	 * �û����桷�������Ľ���
	 */
	public PersonInfoFrame(final User user) {
		setTitle("\u4E2A\u4EBA\u4E2D\u5FC3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/**
		 * �û����桷�������Ľ��桷�޸ĸ�����Ϣ
		 */
		JButton button = new JButton("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePersonInfoFrame frame = new UpdatePersonInfoFrame(user);
				frame.setVisible(true);
				PersonInfoFrame.this.dispose();
			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button.setBounds(124, 35, 180, 50);
		contentPane.add(button);
		/**
		 * �û����桷�������Ľ��桷���˶�����ϸ
		 */
		JButton button_1 = new JButton("\u4E2A\u4EBA\u8BA2\u5355\u660E\u7EC6");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonOrderDetailFrame frame = new PersonOrderDetailFrame(user);
				PersonInfoFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_1.setBounds(124, 108, 180, 50);
		contentPane.add(button_1);
		/**
		 * �û����桷�������Ľ��桷����
		 */
		JButton button_2 = new JButton("\u8FD4\u56DE");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame frame = new UserFrame(user);
				frame.setVisible(true);
				PersonInfoFrame.this.dispose();
			}
		});
		button_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_2.setBounds(124, 176, 180, 50);
		contentPane.add(button_2);
		
		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
	}

}
