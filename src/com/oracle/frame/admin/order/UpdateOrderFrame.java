package com.oracle.frame.admin.order;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oracle.po.Order;
import com.oracle.services.OrderServices;
import com.oracle.services.OrderServicesImpl;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateOrderFrame extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBox;
	private JTextField textField;
	private JLabel label_1;
	private Order order;//设置全局变量（订单）接收形参以便点击事件使用
	//创建订单业务层的对象
	OrderServices os=new OrderServicesImpl();
	//
	OrderManagementFrame om;//设置全局变量（订单管理界面）接收形参以便点击事件使用
	/**
	 * 管理员界面》订单管理界面》修改订单界面
	 */
	public UpdateOrderFrame( Order orde,OrderManagementFrame omf) {
		
		order=orde;//接收外界传来订单
		om=omf;//接收上一级页面（）
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//关闭此界面时只关掉当前界面
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
		setTitle("\u4FEE\u6539\u8BA2\u5355");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDi = new JLabel("\u8BA2\u5355\u72B6\u6001");
		lblDi.setHorizontalAlignment(SwingConstants.CENTER);
		lblDi.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		lblDi.setBounds(110, 115, 76, 40);
		contentPane.add(lblDi);
		/**
		 * 管理员界面》订单管理界面》修改订单界面》下拉框
		 */
		comboBox = new JComboBox();
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u672A\u4ED8\u6B3E", "\u5DF2\u4ED8\u6B3E", "\u5DF2\u53D1\u8D27"}));
		//将原订单状态赋值给下拉框
		comboBox.setSelectedItem(order.getOrderStuas());
		
		comboBox.setBounds(200, 117, 100, 40);
		contentPane.add(comboBox);
		
		JLabel label = new JLabel("\u8BA2\u5355\u603B\u4EF7");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(110, 197, 76, 40);
		contentPane.add(label);
		/**
		 * 管理员界面》订单管理界面》修改订单界面》修改订单价格文本框
		 */
		textField = new JTextField();
		textField.setBounds(200, 199, 76, 40);
		//将原订单总价赋值给文本框
		textField.setText(Double.toString(order.getTotalPrice()));
		//如果下拉框为未付款则可以修改总价 否则不可修改
		if (comboBox.getSelectedItem().equals("未付款")) {
			textField.setEnabled(true);
			
		}else {
			textField.setEnabled(false);
		}
		
		contentPane.add(textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("\u5143");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(274, 197, 39, 40);
		contentPane.add(label_1);
		/**
		 * 管理员界面》订单管理界面》修改订单界面》修改按钮
		 */
		JButton button = new JButton("\u786E\u8BA4\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取下拉框的值和文本框的值   并赋值给此order
				order.setOrderStuas((String)comboBox.getSelectedItem());
				order.setTotalPrice(Double.parseDouble(textField.getText()));
				//提示修改信息
				JOptionPane.showConfirmDialog(null, "谨慎修改", "", JOptionPane.YES_NO_OPTION);
				//调用订单业务层的修改方法
				if (os.UpdateOder(order)) {
					om.dtm.setRowCount(0);
					om.addRowData();//更新表数据
					JOptionPane.showMessageDialog(null, "修改成功");
					
					UpdateOrderFrame.this.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "修改失败");
				}
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button.setBounds(164, 334, 112, 40);
		contentPane.add(button);
		
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
	}
}
