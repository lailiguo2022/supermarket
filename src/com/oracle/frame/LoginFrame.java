package com.oracle.frame;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.oracle.frame.admin.AdminFrame;
import com.oracle.frame.user.UserFrame;
import com.oracle.po.User;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {
	// 创建用户业务层的对象
	UserService service = new UserServiceImpl();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		LoginFrame frame = new LoginFrame();
		frame.setVisible(true);
	}

	/**
	 * 登录界面
	 */
	public LoginFrame() {
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 525, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/**
		 * 登录界面》登录按钮
		 */
		JButton btnNewButton = new JButton("\u767B\u5F55");
		// 事件监听器(接口)
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();// 调用登录方法
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnNewButton.setBounds(80, 232, 80, 35);
		contentPane.add(btnNewButton);
		/**
		 * 登录界面》清除按钮
		 */
		JButton button = new JButton("\u6E05\u9664");
		// 事件监听器(接口)
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 清除文本框原来的内容
				textField.setText("");
				textField_1.setText("");
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button.setBounds(213, 232, 80, 35);
		contentPane.add(button);
		/**
		 * 登录界面》注册按钮
		 */
		JButton button_1 = new JButton("\u6CE8\u518C");
		// 事件监听器(接口)
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 进入注册界面
				RegistFrame frame = new RegistFrame();
				frame.setVisible(true);
				// 关闭当前界面
				LoginFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_1.setBounds(346, 232, 80, 35);
		contentPane.add(button_1);
		/**
		 * 登录界面》用户名文本框
		 */
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField.setBounds(183, 59, 200, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblNewLabel.setBounds(105, 57, 64, 40);
		contentPane.add(lblNewLabel);
		/**
		 * 登录界面》密码文本框
		 */
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField_1.setForeground(Color.BLACK);
		textField_1.setColumns(10);
		textField_1.setBounds(183, 128, 200, 40);
		contentPane.add(textField_1);

		JLabel label = new JLabel("\u5BC6    \u7801");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label.setBounds(105, 128, 64, 38);
		contentPane.add(label);

		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
	}

	/**
	 * 登录方法判断
	 */
	public void login() {
		// 获取界面上每个输入框的值
		String userName = textField.getText().trim();// 用户名文本框
		String pass = textField_1.getText().trim();// 密码文本框
		// System.out.println(userName+pass);
		// 访问业务层的通过用户名获取用户的方法 返回一个用户
		User user = service.getUserByUserName(userName);
		if (user != null) {
			if (user.getPass().equalsIgnoreCase(pass)) {
				if (user.getPower() == 1) {
					// 进入管理员界面
					AdminFrame frame = new AdminFrame();
					frame.setVisible(true);
					// 关闭当前界面
					LoginFrame.this.dispose();
				} else {
					// 进入用户界面
					UserFrame frame = new UserFrame(user);
					frame.setVisible(true);
					// 关闭当前界面
					LoginFrame.this.dispose();
				}
			}
		} else {
			javax.swing.JOptionPane.showMessageDialog(null, "登录失败");
		}
	}
}
