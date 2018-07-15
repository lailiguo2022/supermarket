package com.oracle.frame.admin.userinfo;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oracle.po.User;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class UserInfo_updateFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel label_1;
	private JTextField textField_1;
	private JLabel label_2;
	private JTextField textField_2;
	private JLabel label_3;
	private JTextField textField_3;
	private JLabel label_4;
	private JTextField textField_4;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private User user;
    //创建用户业务层的对象
	UserService  usi = new UserServiceImpl();
	
	/**
	 * 用户信息管理》修改》修改界面
	 */
	public UserInfo_updateFrame(User use,final UserInfoFrame menu ) {
		user=use;
		setFont(new Font("微软雅黑", Font.ITALIC, 20));
		setTitle("\u4FEE\u6539\u7528\u6237\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭子窗口
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(63, 35, 76, 40);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(153, 35, 200, 40);
		contentPane.add(textField);
		
		label_1 = new JLabel("\u5BC6    \u7801");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(63, 103, 76, 40);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(153, 103, 200, 40);
		contentPane.add(textField_1);
		
		label_2 = new JLabel("\u59D3    \u540D");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_2.setBounds(63, 171, 76, 40);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(153, 171, 200, 40);
		contentPane.add(textField_2);
		
		label_3 = new JLabel("\u7535    \u8BDD");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_3.setBounds(63, 235, 76, 40);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(153, 235, 200, 40);
		contentPane.add(textField_3);
		
		label_4 = new JLabel("\u5730    \u5740");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_4.setBounds(63, 302, 76, 40);
		contentPane.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("微软雅黑", Font.PLAIN, 18));
//		textField_4.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusLost(FocusEvent e) {
//                if (textField_4.getText().isEmpty()){
//                   JOptionPane.showMessageDialog(null, "地址不能为空");
//                }
//            }
//        });
		textField_4.setColumns(10);
		textField_4.setBounds(153, 302, 200, 40);
		contentPane.add(textField_4);
		
		textField.setText(user.getUserName());//用户名框赋值 默认 不可修改
		textField_1.setText(user.getPass()); //密码框赋值
	    textField_2.setText(user.getName()); //姓名框赋值 默认
	    textField_3.setText(user.getTelephoneNumber()); //电话框赋值 默认
	    textField_4.setText(user.getAddress()); //地址框赋值 默认
		/**
		 * 用户信息管理》修改按钮》修改用户信息界面》提交按钮
		 */
		button = new JButton("\u63D0\u4EA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String pass = textField_1.getText().trim(); //获取密码输入框的值
			    String name = textField_2.getText().trim(); //获取姓名输入框的值
			    String telephonenumber = textField_3.getText().trim(); //获取电话输入框的值
			    String address= textField_4.getText().trim(); //获取地址输入框的值
			    //封装用户的新属性
			    user.setPass(pass);
    			user.setName(name);
    			user.setTelephoneNumber(telephonenumber);
    			user.setAddress(address);
    			//调用业务层修改用户的方法
    			// 添加确认提示框，会返回一个整数
				int choice = JOptionPane.showConfirmDialog(null, "是否修改？", "提示", JOptionPane.YES_NO_OPTION);
					// 说明你点击的是“是”按钮
					if (choice == JOptionPane.YES_OPTION) {
						if(user.getPass().equals("")){
							JOptionPane.showMessageDialog(null, "密码不能为空!");
						}else{
							usi.updateUser(user);
							JOptionPane.showMessageDialog(null, "修改成功!");
							UserInfo_updateFrame.this.dispose();//关闭本界面
							menu.update();//显示用户更新数据
							menu.id=0;//移除点击事件获得的id
						}
					} else
					// 说明你点击的是“否”按钮
					if (choice == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "修改失败!");
						UserInfo_updateFrame.this.dispose();//关闭本界面
						menu.id=0;//移除点击事件获得的id
					}
			
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button.setBounds(79, 384, 80, 35);
		contentPane.add(button);
		/**
		 * 用户信息管理》修改按钮》修改用户信息界面》清除按钮
		 */
		button_1 = new JButton("\u6E05\u9664");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  textField_1.setText(""); //给密码输入框赋值
				  textField_2.setText(""); //给姓名输入框赋值
				  textField_3.setText(""); //给电话输入框赋值
				  textField_4.setText(""); //给地址输入框赋值
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_1.setBounds(173, 384, 80, 35);
		contentPane.add(button_1);
		/**
		 * 用户信息管理》修改按钮》修改用户信息界面》返回按钮
		 */
		button_2 = new JButton("\u8FD4\u56DE");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.update();//返回所有用户界面
				UserInfo_updateFrame.this.dispose();//关闭本界面
			}
		});
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_2.setBounds(267, 384, 80, 35);
		contentPane.add(button_2);
		
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
	}
	
	
}
