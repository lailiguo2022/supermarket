package com.oracle.frame.user.personInfo;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oracle.po.User;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 修改个人用户信息界面，完成
 */
public class UpdatePersonInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_userName;
	private JTextField textField_pass;
	private JTextField textField_telephone;
	private JTextField textField_address;
	private JTextField textField_name;
	private JTextField textField_date;
	private JSpinner spinner_age;
	private JComboBox<String> comboBox_sex;
	private User user;//预留上层界面传进来的user对象
	
	UserService usi= new UserServiceImpl();
	
	/**
	 * 用户界面》个人中心界面》修改个人信息界面
	 */
	public UpdatePersonInfoFrame(User use) {
		user=usi.getUserById(use.getUserID());
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
		setTitle("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(14, 52, 76, 40);
		contentPane.add(label);
		/**
		 * 用户界面》个人中心界面》修改个人信息界面》用户名文本框
		 */
		textField_userName = new JTextField();
		textField_userName.setEnabled(false);
		textField_userName.setBounds(97, 51, 156, 41);
		contentPane.add(textField_userName);
		textField_userName.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BC6    \u7801");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(14, 108, 76, 40);
		contentPane.add(label_1);
		/**
		 * 用户界面》个人中心界面》修改个人信息界面》密码文本框
		 */
		textField_pass = new JTextField();
		textField_pass.setColumns(10);
		textField_pass.setBounds(97, 107, 156, 41);
		contentPane.add(textField_pass);
		
		JLabel label_2 = new JLabel("\u7535    \u8BDD");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_2.setBounds(202, 162, 76, 40);
		contentPane.add(label_2);
		/**
		 * 用户界面》个人中心界面》修改个人信息界面》电话文本框
		 */
		textField_telephone = new JTextField();
		textField_telephone.setColumns(10);
		textField_telephone.setBounds(277, 161, 133, 41);
		contentPane.add(textField_telephone);
		
		JLabel label_3 = new JLabel("\u5730    \u5740");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_3.setBounds(14, 215, 76, 40);
		contentPane.add(label_3);
		/**
		 * 用户界面》个人中心界面》修改个人信息界面》地址文本框
		 */
		textField_address = new JTextField();
		textField_address.setColumns(10);
		textField_address.setBounds(97, 217, 313, 41);
		contentPane.add(textField_address);
		
		JLabel label_4 = new JLabel("\u59D3    \u540D");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_4.setBounds(14, 162, 76, 40);
		contentPane.add(label_4);
		/**
		 * 用户界面》个人中心界面》修改个人信息界面》姓名文本框
		 */
		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setBounds(97, 161, 91, 41);
		contentPane.add(textField_name);
		
		JLabel label_5 = new JLabel("\u6CE8\u518C\u65E5\u671F");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_5.setBounds(14, 268, 76, 40);
		contentPane.add(label_5);
		/**
		 * 用户界面》个人中心界面》修改个人信息界面》注册日期文本框
		 */
		textField_date = new JTextField();
		textField_date.setColumns(10);
		textField_date.setBounds(97, 270, 313, 41);
		contentPane.add(textField_date);
		
		JLabel label_6 = new JLabel("\u6027    \u522B");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_6.setBounds(267, 52, 76, 40);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("\u5E74    \u9F84");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_7.setBounds(267, 108, 76, 40);
		contentPane.add(label_7);
		/**
		 * 用户界面》个人中心界面》修改个人信息界面》年龄框
		 */
		spinner_age = new JSpinner();
		spinner_age.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		spinner_age.setBounds(342, 108, 68, 40);
		contentPane.add(spinner_age);
		/**
		 * 用户界面》个人中心界面》修改个人信息界面》性别下拉框
		 */
		comboBox_sex = new JComboBox<String>();
		comboBox_sex.setModel(new DefaultComboBoxModel<String>(new String[] {"\u7537", "\u5973"}));
		comboBox_sex.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		comboBox_sex.setBounds(342, 52, 68, 40);
		contentPane.add(comboBox_sex);
		/**
		 * 用户界面》个人中心界面》修改个人信息界面》提交按钮
		 */
		JButton button_submit = new JButton("\u63D0\u4EA4");
		button_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//修改用户个人信息
				if(UpdatePersonInfoFrame.this.updateUser()){
					javax.swing.JOptionPane.showMessageDialog(null, "修改成功！");
					PersonInfoFrame frame = new PersonInfoFrame(user);
					frame.setVisible(true);
					UpdatePersonInfoFrame.this.dispose();
				}else{
					javax.swing.JOptionPane.showMessageDialog(null, "修改失败！");
				}
			}
		});
		button_submit.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_submit.setBounds(69, 371, 80, 35);
		contentPane.add(button_submit);
		/**
		 * 用户界面》个人中心界面》修改个人信息界面》清除按钮
		 */
		JButton button_clear = new JButton("\u6062\u590D");
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//恢复默认值
				UpdatePersonInfoFrame.this.addDefaultData();
			}
		});
		button_clear.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_clear.setBounds(176, 371, 80, 35);
		contentPane.add(button_clear);
		/**
		 * 用户界面》个人中心界面》修改个人信息界面》返回按钮
		 */
		JButton button_return = new JButton("\u8FD4\u56DE");
		button_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonInfoFrame frame = new PersonInfoFrame(user);
				frame.setVisible(true);
				UpdatePersonInfoFrame.this.dispose();
			}
		});
		button_return.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_return.setBounds(281, 371, 80, 35);
		contentPane.add(button_return);
		
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
		
		//添加默认数据
		this.addDefaultData();
	}
	
	/**
	 * 为修改用户信息界面的各个框体赋上层界面传进来的user对象的各个值
	 */
	private void addDefaultData(){
		textField_userName.setText(user.getUserName());
		textField_pass.setText(user.getPass());;
		textField_telephone.setText(user.getTelephoneNumber());
		textField_address.setText(user.getAddress());
		textField_name.setText(user.getName());
		textField_date.setText(user.getDate().toString());
		spinner_age.setValue(user.getAge());
		comboBox_sex.setSelectedItem(user.getSex());
	}
	
	private boolean updateUser(){
		User newuser = new User();
		newuser.setUserID(user.getUserID());
		newuser.setUserName(textField_userName.getText().trim());
		newuser.setPass(textField_pass.getText());
		newuser.setTelephoneNumber(textField_telephone.getText());
		newuser.setAddress(textField_address.getText());
		newuser.setName(textField_name.getText());
		//newuser.setDate(textField_date.getText());
		newuser.setAge((int)spinner_age.getValue());
		newuser.setSex(comboBox_sex.getSelectedItem().toString());
		UserService us = new UserServiceImpl();
		return us.updateUser(newuser);
		
	}
}
