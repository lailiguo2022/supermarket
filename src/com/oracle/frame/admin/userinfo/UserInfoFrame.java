package com.oracle.frame.admin.userinfo;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.oracle.frame.admin.AdminFrame;
import com.oracle.po.User;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class UserInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	DefaultTableModel tableModel;// 模板
	private int row;//点击事件获得的某一行
	public int id;//用户的ID
	JPanel panel;//主面板
	
	
	UserService usi = new UserServiceImpl();//创建用户业务层的对象

	
	/**
	 * 管理员界面》用户信息管理界面
	 */
	public UserInfoFrame() {
		setBackground(SystemColor.activeCaption);
		setForeground(Color.ORANGE);
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
		setTitle("\u7528\u6237\u4FE1\u606F\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	    panel = new JPanel();
		panel.setPreferredSize( new Dimension( 450, 42));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.NORTH);
	
		table = new JTable();//将模板放在表格上
		scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);//将表格放在界面
		
		/**
		 * 鼠标点击事件
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 1. 获取 table中的某一行
				row = table.getSelectedRow();
				// 2.通过某一行获取第0位上的值
				id =  (int) table.getValueAt(row, 0);
				
			}
		});
		
		/**
		 * 用户信息管理》搜索按钮
		 */
		btnNewButton = new JButton("\u641C\u7D22");
		btnNewButton.setBackground(new Color(204, 153, 0));
		btnNewButton.setForeground(SystemColor.textHighlight);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBounds(513, 6, 70, 31);
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();//搜索方法
			}
		});
		/**
		 * 用户信息管理》搜索文本框
		 */
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
		/**
		 * 回车事件
		 * @param e
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			search();//搜索方法
		}
		});
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setBackground(Color.WHITE);
		textField.addMouseListener(new MouseAdapter() {
			/**
			 * 输入框鼠标点击事件
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
			}
//			/**
//			 * 输入框鼠标离开事件
//			 */
//			@Override
//			public void mouseExited(MouseEvent e) {
//				String str = textField.getText().trim();
//				if(str!=null){
//					for (User user: usi.getAllUser()) {
//						if(!user.getUserName().equals(str)){
//							JLabel lblNewLabel = new JLabel("该帐号不存在!");
//							lblNewLabel.setBounds(471, 15, 54, 15);
//							panel.add(lblNewLabel);
//						}
//					}
//				}
//				
//			}
			
		});
		textField.setText("\u8BF7\u8F93\u5165\u7528\u6237\u540D");
		textField.setBounds(383, 7, 120, 28);
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setColumns(10);
		/**
		 * 用户信息管理》修改按钮
		 */
		button = new JButton("\u4FEE\u6539\u7528\u6237");
		button.setBackground(new Color(204, 153, 0));
		button.setForeground(SystemColor.textHighlight);
		button.setFocusPainted(false);
		button.setBounds(125, 6, 100, 31);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id!=0){
					if(usi.getUserById(id).getPower()==1){
	    				JOptionPane.showMessageDialog(null, "您暂不能修改管理员!");
	    			}else{
	    				User user = usi.getUserById(id);//调用业务层通过id获取用户的方法 
						if(user!=null){
							//跳转至修改界面
							UserInfo_updateFrame frame = new  UserInfo_updateFrame(user,UserInfoFrame.this);
							frame.setVisible(true);
						}
	    			}
					
				}
				
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		/**
		 * 用户信息管理》删除按钮
		 */
		button_1 = new JButton("\u5220\u9664\u7528\u6237");
		button_1.setBackground(new Color(204, 153, 0));
		button_1.setForeground(SystemColor.textHighlight);
		button_1.setBounds(239, 6, 100, 31);
		button_1.setFocusPainted(false);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id!=0){ //点击事件获取的用户id
					if((usi.getUserById(id).getPower())==1){
						JOptionPane.showMessageDialog(null, "不能删除管理员！");
						id=0;
					}else{
						// 添加确认提示框，会返回一个整数
						int choice =JOptionPane.showConfirmDialog(null, "是否删除？","提示！", JOptionPane.YES_NO_OPTION);
						if((usi.getUserById(id).getPower())==1){
							JOptionPane.showMessageDialog(null, "删除成功！");
						}else{
							// 说明你点击的是“是”按钮
							if (choice == JOptionPane.YES_OPTION) {
								usi.deleteUserById(id);//调用业务层的删除用户方法
								JOptionPane.showMessageDialog(null, "删除成功!");
								tableModel=(DefaultTableModel) table.getModel();//得到表格上的数据模板
								tableModel.removeRow(row);//删除点击事件获得的该行
								id=0;
							} 
						}
					}
						
						
			   }
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		panel.setLayout(null);
		panel.add(btnNewButton);
		panel.add(textField);
		panel.add(button);
		panel.add(button_1);
		/**
		 * 用户信息管理》返回按钮
		 */
		button_2 = new JButton("\u8FD4\u56DE");
		button_2.setBackground(new Color(204, 153, 0));
		button_2.setForeground(SystemColor.textHighlight);
		button_2.setBounds(593, 6, 70, 31);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//返回上级界面
				AdminFrame frame = new AdminFrame();
				frame.setVisible(true);
				UserInfoFrame.this.dispose();//关闭本窗口
				
			}
		});
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_2.setFocusPainted(false);
		panel.add(button_2);
		
		
		/**
		 * 用户信息管理》Jtable双击事件
		 */
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane.setViewportView(table);
		panel.setPreferredSize( new Dimension( 450, 40));
		/**
		 * 用户信息管理》所有用户按钮
		 */
		button_3 =  new JButton("\u6240\u6709\u7528\u6237");
		button_3.setBackground(new Color(204, 153, 0));
		button_3.setForeground(SystemColor.textHighlight);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  update();//用户的数据更新载入
			}
		});
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_3.setFocusPainted(false);
		button_3.setBounds(14, 6, 100, 31);
		panel.add(button_3);
		
		
		
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
		// 设置表格模型
		tableModel = new DefaultTableModel();
		tableModel.addColumn("编号");
		tableModel.addColumn("用户名");
		tableModel.addColumn("身份");
		tableModel.addColumn("姓名");
		tableModel.addColumn("性别");
		tableModel.addColumn("年龄");
		tableModel.addColumn("注册时间");
		tableModel.addColumn("联系电话");
		tableModel.addColumn("地址");
		table.setModel(tableModel);//将模型添加到表格上
		
	}
    //数据更新
	public void update(){
		
		tableModel.setRowCount(0);//清空表格模型数据
		//调用业务层获取所有用户的方法并遍历集合
		for (User user : usi.getAllUser()) {
			String power = null;
			if(user.getPower()==1){
				power="管理员";
			}else{
				power="普通用户";
			}
		    //创建obj数组存储用户属性
			Object[] obj = new Object[] { user.getUserID(),
					                      user.getUserName(),
					                      power,//身份
					                      user.getName(),
					                      user.getSex(),
					                      user.getAge(),
					                      user.getDate(),
					                      user.getTelephoneNumber(),
					                      user.getAddress()
			                              };
			tableModel.addRow(obj);//将用户数组传给模板
		}
	}
	/**
	 * 搜索方法
	 */
	public void search(){
		String userName = textField.getText().trim();//获取输入框的值
		tableModel.setRowCount(0);
		if(!userName.equals("")){// 判断输入框不为空
			User us = usi.getUserByUserName(userName);//调用业务层用户名查找用户的方法
			if(us!=null){
				String power = null;
				if(us.getPower()==1){
					power="管理员";
				}else{
					power="普通用户";
				}
				//将用户的加入到数组
				Object[] obj = new Object[]{  us.getUserID(),
						                      us.getUserName(),
						                      power,//身份
						                      us.getName(),
						                      us.getSex(),
						                      us.getAge(),
						                      us.getDate(),
						                      us.getTelephoneNumber(),
						                      us.getAddress()
					                        };
				tableModel.addRow(obj);//将数组加入到模型中
			}
		}
	}
}
