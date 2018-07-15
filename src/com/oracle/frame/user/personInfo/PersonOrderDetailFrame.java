package com.oracle.frame.user.personInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.oracle.frame.user.shopping.ConfirmPaymentFrame;
import com.oracle.po.Commodity;
import com.oracle.po.Order;
import com.oracle.po.User;
import com.oracle.services.OrderServices;
import com.oracle.services.OrderServicesImpl;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PersonOrderDetailFrame extends JFrame {

	User user;
	private JPanel contentPane;
	private JTextField textField_serch;
	JSplitPane splitPane;
	private JTable table_order;
	private JTable table_coms;
	private DefaultTableModel tableModel_order;// 模板
	private DefaultTableModel tableModel_coms;// 模板
	
	int orderID;
	
	OrderServices osi=new OrderServicesImpl();
	UserService us = new UserServiceImpl();

	/**
	 * 用户界面》个人中心界面》个人订单明细
	 */
	public PersonOrderDetailFrame(User use) {
		user= use;
		setTitle("\u4E2A\u4EBA\u8BA2\u5355\u660E\u7EC6");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(450, 42));
		contentPane.add(panel, BorderLayout.NORTH);
		/**
		 * 用户界面》个人中心界面》个人订单明细》个人订单按钮
		 */
		JButton button_order = new JButton("\u4E2A\u4EBA\u8BA2\u5355");
		button_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//引用本类方法，给表格模型添加订单列表
				addOrder();
			}
		});
		button_order.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_order.setBounds(14, 6, 100, 31);
		panel.add(button_order);
		/**
		 * 用户界面》个人中心界面》个人订单明细》付款按钮
		 */
		JButton button_pay = new JButton("\u4ED8\u6B3E");
		button_pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(orderID!=0){
					Order ord = osi.searchOder_CommodityByOrderID(orderID);
					if(ord.getOrderStuas().equals("未付款")){
						ConfirmPaymentFrame frame = new ConfirmPaymentFrame(ord);
						frame.setVisible(true);
						PersonOrderDetailFrame.this.dispose();
					}else{
						JOptionPane.showMessageDialog(null, "该订单已付款！");
					}
				}else{
					JOptionPane.showMessageDialog(null, "请选择一条订单");
				}
				
				
			}
		});
		button_pay.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_pay.setBounds(122, 6, 100, 31);
		panel.add(button_pay);
		
		/**
		 * 用户界面》个人中心界面》个人订单明细》删除按钮
		 */
		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(orderID!=0){
					
						int choice=JOptionPane.showConfirmDialog(null,"是否删除此订单", "警告",JOptionPane.YES_NO_OPTION);//是否删除的提示框
						if (choice==JOptionPane.YES_OPTION) {//如果选择yes
							osi.deleteOderById(orderID);//通过id删除此订单
						}
						/**
						 * 刷新此页
						 */
						addOrder();
				}else{
					JOptionPane.showMessageDialog(null, "请选择一条订单");
				}
			}
		});
		button_delete.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_delete.setBounds(232, 6, 100, 31);
		panel.add(button_delete);
		/**
		 * 用户界面》个人中心界面》个人订单明细》搜索按钮
		 */
		JButton button_serch = new JButton("\u641C\u7D22");
		button_serch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderSerch();
			}
		});
		button_serch.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_serch.setBounds(360, 6, 70, 31);
		panel.add(button_serch);
		/**
		 * 用户界面》个人中心界面》个人订单明细》返回按钮
		 */
		JButton button_return = new JButton("\u8FD4\u56DE");
		button_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonInfoFrame frame = new PersonInfoFrame(user);
				frame.setVisible(true);
				PersonOrderDetailFrame.this.dispose();
			}
		});
		button_return.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_return.setBounds(593, 6, 70, 31);
		panel.add(button_return);
		/**
		 * 用户界面》个人中心界面》个人订单明细》搜索文本框
		 */
		textField_serch = new JTextField();
		textField_serch.setColumns(10);
		textField_serch.setBounds(439, 7, 140, 30);
		panel.add(textField_serch);

		
		//分割窗体面板
		splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		splitPane.setDividerSize(0);
		
		table_order = new JTable();
		table_order.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table_order.getSelectedRow();
				orderID=(int) table_order.getValueAt(row, 0);
				//将商品详情放进右边表格框里
				orderDetails();
			}
		});
		scrollPane.setViewportView(table_order);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		
		table_coms = new JTable();
		scrollPane_1.setViewportView(table_coms);
		
		
		/**
		 * 窗体显示，分割窗比例，屏幕居中
		 */
		this.setVisible(true);
		this.splitPane.setDividerLocation(0.5);
		this.setLocationRelativeTo(null);
		
		//给表格模型添加表头
		tableModel_order = new DefaultTableModel();
		tableModel_order.addColumn("订单编号");
		tableModel_order.addColumn("用户编号");
		tableModel_order.addColumn("订单生成日期");
		tableModel_order.addColumn("订单状态");
		tableModel_order.addColumn("订单金额");
		table_order.setModel(tableModel_order);
		
		tableModel_coms = new DefaultTableModel();
		tableModel_coms.addColumn("商品名称");
		tableModel_coms.addColumn("购买个数");
		tableModel_coms.addColumn("单价");
		tableModel_coms.addColumn("金额");
		table_coms.setModel(tableModel_coms);
	}
	
	private void addOrder(){
		tableModel_order.setRowCount(0);
		List<Order> list = us.getOrderByUserId(user.getUserID());
		if(list!=null&&list.size()>0){
			for (Order order : list) {
				tableModel_order.addRow(new Object[]{
						order.getOrderID(),
						order.getUserID(),
						order.getCreationTime(),
						order.getOrderStuas(),
						order.getTotalPrice()
				});
			}
		}
	}
	
	private void orderDetails(){
		tableModel_coms.setRowCount(0);
		Order order = osi.searchOder_CommodityByOrderID(orderID);
		for ( Commodity com : order.getCommoditys()) {
			tableModel_coms.addRow(new Object[]{
				com.getComName(),
				com.getBuyNumber(),
				com.getComPrice(),
				com.getEntryPrice()
			});
		}
	}
	
	private void orderSerch(){
		try {
			int textID=Integer.parseInt(textField_serch.getText());//获取文本框的值
			
			Order selectedOrder=osi.searchOderById(textID);//根据此id获取此订单
			  if (selectedOrder!=null&&selectedOrder.getCreationTime()!=null) {
				  tableModel_order.setRowCount(0);//清空表
				  tableModel_order.addRow(new Object[]{
						   selectedOrder.getOrderID(),
						   selectedOrder.getUserID(),
						   selectedOrder.getCreationTime(),
						   selectedOrder.getOrderStuas(),
						   selectedOrder.getTotalPrice()});//将此order放入此表
			   }
			   else {
				JOptionPane.showMessageDialog(null, "对不起你要查找的编号不存在或编号格式不正确");
				
			   }
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "内容不能为空且必须为整数");
		}
	}
}
