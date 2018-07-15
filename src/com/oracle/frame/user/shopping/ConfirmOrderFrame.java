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
	
	// 创建用户对象
	User user;
	// 创建业务层对象
	OrderServicesImpl service = new OrderServicesImpl();
	// 创建表格模型
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
	 * 用户界面》选购商品》提交订单》确认付款界面
	 */
	public ConfirmOrderFrame(User use) {
		user=use;
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
		setTitle("\u786E\u8BA4\u8BA2\u5355");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 600);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		// 用户姓名文本框
		textField_name = new JTextField();
		textField_name.setEditable(false);
		textField_name.setBounds(111, 54, 107, 37);
		textField_name.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(" \u7528\u6237\u59D3\u540D");
		lblNewLabel.setBounds(39, 55, 62, 35);
		// 收货地址文本框
		textField_address = new JTextField();
		textField_address.setEditable(false);
		textField_address.setBounds(111, 255, 107, 37);
		textField_address.setColumns(10);
		// 收货地址标签
		JLabel label = new JLabel(" \u6536\u8D27\u5730\u5740");
		label.setBounds(39, 256, 62, 35);
		contentPane.setLayout(null);
		contentPane.add(label);
		contentPane.add(textField_address);
		contentPane.add(lblNewLabel);
		contentPane.add(textField_name);
		// 联系方式标签
		JLabel label_1 = new JLabel(" \u8054\u7CFB\u65B9\u5F0F");
		label_1.setBounds(39, 152, 62, 35);
		contentPane.add(label_1);
		// 联系方式文本框
		textField_telephonenumber = new JTextField();
		textField_telephonenumber.setEditable(false);
		textField_telephonenumber.setColumns(10);
		textField_telephonenumber.setBounds(111, 151, 107, 37);
		contentPane.add(textField_telephonenumber);
		// 滚动面板
		scrollPane = new JScrollPane();
		scrollPane.setBounds(268, 55, 256, 335);
		contentPane.add(scrollPane);
		// 总价标签
		JLabel label_2 = new JLabel("   \u603B\u4EF7");
		label_2.setBounds(39, 355, 62, 35);
		contentPane.add(label_2);
		// 总价文本框
		textField_totalPrice = new JTextField();
		textField_totalPrice.setEditable(false);
		textField_totalPrice.setColumns(10);
		textField_totalPrice.setBounds(111, 354, 107, 37);
		contentPane.add(textField_totalPrice);
		// 底部面板
		JPanel panel = new JPanel();
		panel.setBounds(23, 450, 501, 54);
		contentPane.add(panel);
		panel.setLayout(null);
		// 确认按钮
		button_confirm = new JButton("\u786E\u8BA4\u8BA2\u5355");
		// 事件监听器
		button_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmOrder();// 调用确认订单的方法
				// 进入确认付款界面
				ConfirmPaymentFrame frame = new ConfirmPaymentFrame(order1);
				frame.setVisible(true);
				// 关闭当前界面
				ConfirmOrderFrame.this.dispose();
			}
		});
		button_confirm.setBounds(75, 10, 93, 34);
		panel.add(button_confirm);
		// 取消按钮
		button_cancel = new JButton("\u53D6\u6D88\u8BA2\u5355");
		// 监听事件
		button_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 进入商品购物界面
				ShoppingFrame frame = new ShoppingFrame(user);
				frame.setVisible(true);
				// 关闭当前界面
				ConfirmOrderFrame.this.dispose();
			}
		});
		button_cancel.setBounds(304, 10, 93, 34);
		panel.add(button_cancel);
		
		// 创建表格
		table_coms = new JTable();
		scrollPane.setViewportView(table_coms);
		//创建表格模型并给表格模型赋值
		dtm = new DefaultTableModel();
		dtm.addColumn("商品名称");
		dtm.addColumn("商品单价");
		dtm.addColumn("购买数量");
		dtm.addColumn("金额");
		table_coms.setModel(dtm);// 给表格添加给定表格模型
		addRowdata();

		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);

	}

	/**
	 * 给表格模型添加数据方法
	 */
	private void addRowdata() {
		
		textField_name.setText(user.getName());
		textField_address.setText(user.getAddress());
		textField_telephonenumber.setText(user.getTelephoneNumber());
		
		double totalPrice = 0;
		//获取上层界面传来的user的订单集合，里面只包含一个订单，没有订单编号
		List<Order> orders = user.getOrders();
		//遍历订单集合，获取唯一订单，并点标出商品集合
		for (Order order : orders) {
			list =order.getCommoditys();
			order1=order;
		}
		//遍历商品集合，添加进表格模型里
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
	 * 确认订单的方法
	 */
	public void confirmOrder() {
		if(service.addOder(user.getUserID(),list)){
			order1.setOrderID(service.getUserMaxOrderID(user.getUserID()));
		}else{
			javax.swing.JOptionPane.showMessageDialog(null, "订单创建失败");
		}
	}
		
}
