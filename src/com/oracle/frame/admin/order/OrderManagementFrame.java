package com.oracle.frame.admin.order;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.Messaging.SyncScopeHelper;

import com.oracle.dao.OrderDaoImpl;
import com.oracle.frame.admin.AdminFrame;
import com.oracle.po.Commodity;
import com.oracle.po.Order;
import com.oracle.services.OrderServices;
import com.oracle.services.OrderServicesImpl;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;

import java.awt.Color;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class OrderManagementFrame extends JFrame {
	OrderServices os=new OrderServicesImpl();//实例化订单业务层
	UserService us=new UserServiceImpl();//实例化用户业务层
	DefaultTableModel dtm=new DefaultTableModel();//创建所有订单的表模型
	DefaultTableModel dtm2=new DefaultTableModel();//创建订单详情表模型
	private JPanel contentPane;
	private JTable table;//（订单表）上表
	private JTable table_1;//（订单详细表）下表
	private JTextField textField;
	int oID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
					OrderManagementFrame frame = new OrderManagementFrame();
					frame.setVisible(true);
			
	}

	/**
	 * 管理员界面》订单管理界面
	 */
	public OrderManagementFrame() {
		 
		//实例化表格模板
		setTitle("\u8BA2\u5355\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(250);//设置上分割框的高度
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		/**
		 * 管理员界面》订单管理界面》所有订单按钮
		 */
		JButton btnNewButton = new JButton("\u6240\u6709\u8BA2\u5355");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 dtm.setRowCount(0);//清空表单
			 addRowData();//重新加载订单数据
			}
		});
		/**
		 * 管理员界面》订单管理界面》修改订单按钮
		 */
		JButton btnNewButton_1 = new JButton("\u4FEE\u6539\u8BA2\u5355");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			Order selectedOrder=os.searchOderById(oID);//将此id获得当前订单；
			oID=0;
			if (selectedOrder.getOrderID()==0) {
				JOptionPane.showMessageDialog(null, "请点击选取要修改的订单");
			}else {
				UpdateOrderFrame uof=new UpdateOrderFrame(selectedOrder,OrderManagementFrame.this);//将此订单传给修改界面
				uof.setVisible(true);
			}
			}
		});
		/**
		 * 管理员界面》订单管理界面》删除订单按钮
		 */
		JButton btnNewButton_2 = new JButton("\u5220\u9664\u8BA2\u5355");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice=JOptionPane.showConfirmDialog(null,"是否删除此订单", "警告",JOptionPane.YES_NO_OPTION);//是否删除的提示框
				if (choice==JOptionPane.YES_OPTION) {//如果选择yes
					os.deleteOderById(oID);//通过id删除此订单
				}
				/**
				 * 刷新此页
				 */
				dtm.setRowCount(0);//刷新此页
				addRowData();//添加数据
			}
		});
		/**
		 * 管理员界面》订单管理界面》返回按钮
		 */
		JButton btnNewButton_3 = new JButton("\u8FD4\u56DE");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderManagementFrame.this.dispose();
				AdminFrame frame = new AdminFrame();
				frame.setVisible(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		/**
		 * 管理员界面》订单管理界面》文本框
		 */
		textField = new JTextField();
		textField.setColumns(10);
		/**
		 * 管理员界面》订单管理界面》搜索按钮
		 */
		JButton btnNewButton_4 = new JButton("\u641C\u7D22");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int textID=Integer.parseInt(textField.getText());//获取文本框的值
					
					Order selectedOrder=os.searchOderById(textID);//根据此id获取此订单
					  if (selectedOrder!=null&&selectedOrder.getCreationTime()!=null) {
						   dtm.setRowCount(0);//清空表
						   dtm.addRow(new Object[]{
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
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(33)
					.addComponent(btnNewButton)
					.addGap(28)
					.addComponent(btnNewButton_1)
					.addGap(18)
					.addComponent(btnNewButton_2)
					.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
					.addComponent(btnNewButton_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_3)
					.addGap(21))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		//创建订单表（上表） 添加订单模板
		table = new JTable(dtm);
		//给订单明细名单添加表头
		dtm2.addColumn("商品名称");
		dtm2.addColumn("购买个数");
		dtm2.addColumn("单价");
		dtm2.addColumn("金额");
		/**
		 * 点击订单表的将订单详情显示在订单详情表（下表）
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//清空订单详情数据
				dtm2.setRowCount(0);
				int row=table.getSelectedRow();//获取当前行
				oID=(int)table.getValueAt(row, 0);//订单编号
				
				int userID=(int)table.getValueAt(row, 1);//用户姓名
				//通过订单编号查询到对应有菜单明细的订单
			Order order=os.searchOder_CommodityByOrderID(oID);
			//通过用户编号获取用户 的用户名
			String str2=us.getUserById(userID).getUserName();
			//获取此订单的商品集合
			List<Commodity> list=order.getCommoditys();	
			
			
			/**
			 * 给订单明细模板（下表）添加行数据
			 */
	for (Commodity co : list) {
		dtm2.addRow(new Object[]{
				co.getComName(),
				co.getBuyNumber(),
				co.getComPrice(),
				co.getEntryPrice()});
	}
			}
		});
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		/**
		 * 创建订单明细表（下表）
		 */
		table_1 = new JTable(dtm2);
		scrollPane_1.setViewportView(table_1);
		contentPane.setLayout(gl_contentPane);
		/**
		 * 创建模板表头（上表）
		 */
		dtm.addColumn("订单编号");
		dtm.addColumn("用户编号");
		dtm.addColumn("订单生成日期");
		dtm.addColumn("订单状态");
		dtm.addColumn("订单金额");
		this.addRowData();
		
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
	}
	/**
	 * 给订单表模板（上表）添加行数据（订单数据）
	 */
	public void addRowData() {
		List<Order> list=os.getAllOder();//查询所有订单得到订单集合
		for (Order order : list) {
			dtm.addRow(new Object[]{
					order.getOrderID(),
					order.getUserID(),
					order.getCreationTime(),
					order.getOrderStuas(),
					order.getTotalPrice()});
		}
	}
}
