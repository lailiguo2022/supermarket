package com.oracle.frame;

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

import com.oracle.po.User;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;

public class RegistFrame extends JFrame {
	// 创建业务层对象
	UserService service = new UserServiceImpl();
	private JPanel contentPane;
	private JTextField textField;
	private JLabel label;
	private JLabel label_1;
	private JTextField textField_1;
	private JLabel label_2;
	private JTextField textField_2;
	private JLabel label_3;
	private JTextField textField_3;
	private JLabel label_4;
	private JTextField textField_4;
	private JButton btnNewButton;
	private JButton button;

	/**
	 * 注册界面
	 */
	public RegistFrame() {
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
		setTitle("\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/**
		 * 注册界面》注册用户名文本框
		 */
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField.setBounds(159, 38, 200, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		label = new JLabel("\u7528\u6237\u540D");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(69, 38, 76, 40);
		contentPane.add(label);

		label_1 = new JLabel("\u5BC6    \u7801");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(69, 108, 76, 40);
		contentPane.add(label_1);
		/**
		 * 注册界面》注册密码文本框
		 */
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(159, 108, 200, 40);
		contentPane.add(textField_1);

		label_2 = new JLabel("\u59D3    \u540D");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_2.setBounds(69, 176, 76, 40);
		contentPane.add(label_2);
		/**
		 * 注册界面》注册姓名文本框
		 */
		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(159, 176, 200, 40);
		contentPane.add(textField_2);

		label_3 = new JLabel("\u7535    \u8BDD");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_3.setBounds(69, 240, 76, 40);
		contentPane.add(label_3);
		/**
		 * 注册界面》注册电话文本框
		 */
		textField_3 = new JTextField();
		textField_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(159, 240, 200, 40);
		contentPane.add(textField_3);

		label_4 = new JLabel("\u5730    \u5740");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_4.setBounds(69, 307, 76, 40);
		contentPane.add(label_4);
		/**
		 * 注册界面》注册地址文本框
		 */
		textField_4 = new JTextField();
		textField_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_4.setColumns(10);
		textField_4.setBounds(159, 307, 200, 40);
		contentPane.add(textField_4);
		/**
		 * 注册界面》提交按钮
		 */
		btnNewButton = new JButton("\u63D0\u4EA4");
		// 事件监听器(接口)
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();// 调用注册方法
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		btnNewButton.setBounds(80, 390, 80, 35);
		contentPane.add(btnNewButton);
		/**
		 * 注册界面》清除按钮
		 */
		button = new JButton("\u6E05\u9664");
		// 监听事件(接口)
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 清除文本框的内容
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button.setBounds(179, 390, 80, 35);
		contentPane.add(button);
		/**
		 * 注册界面》返回按钮
		 */
		JButton button_1 = new JButton("\u8FD4\u56DE");
		// 事件监听器(接口)
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 返回登录界面
				LoginFrame frame = new LoginFrame();
				frame.setVisible(true);
				// 关闭当前界面
				RegistFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_1.setBounds(279, 390, 80, 35);
		contentPane.add(button_1);
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
	}

	/**
	 * 注册判断方法
	 */
	public void register() {
		// 获取界面输入框的值
		String userName = textField.getText().trim();// 用户名文本框
		String pass = textField_1.getText().trim();// 注册密码文本框
		String name = textField_2.getText().trim();// 注册姓名本框
		String telephoneNumber = textField_3.getText().trim();// 注册电话文本框
		String address = textField_4.getText().trim();// 注册地址文本框
		// 访问业务层通过用户名获取用户的方法 返回一个用户
		User user = new User();
		// 判断输入框中的用户名是否存在
		if (service.getUserByUserName(userName) == null) {
			user.setUserName(userName);
			user.setPass(pass);
			user.setName(name);
			user.setTelephoneNumber(telephoneNumber);
			user.setAddress(address);
			// 判断所有输入框中的内容是否为空
			if (!pass.equals("") && !name.equalsIgnoreCase("") && !telephoneNumber.equalsIgnoreCase("")) {
				if (!address.equalsIgnoreCase("")) {
					if (service.addUser(user)) {
						javax.swing.JOptionPane.showMessageDialog(null, "注册成功");
						// 进入登录界面
						LoginFrame frame = new LoginFrame();
						frame.setVisible(true);
						// 关闭当前界面
						RegistFrame.this.dispose();
					}
				} else {
					javax.swing.JOptionPane.showMessageDialog(null, "每个输入框都必填！");
				}
			} else {
				javax.swing.JOptionPane.showMessageDialog(null, "注册失败");
			}
		}
	}
}
