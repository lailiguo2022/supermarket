package com.oracle.frame.user.shopping;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.OBJ_ADAPTER;

import com.oracle.frame.user.UserFrame;
import com.oracle.po.ComType;
import com.oracle.po.Commodity;
import com.oracle.po.Order;
import com.oracle.po.User;
import com.oracle.services.ComTypeServices;
import com.oracle.services.ComTypeServicesImpl;
import com.oracle.services.CommodityServices;
import com.oracle.services.CommodityServicesImpl;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShoppingFrame extends JFrame {
    private static final String Douleger = null;
	//创建商品业务层的对象
	ComTypeServices cts = new ComTypeServicesImpl();
	CommodityServices  cdi = new CommodityServicesImpl();
	UserService usi=new UserServiceImpl();
	private JPanel contentPane;
	private JTextField textField;
	ComType comType;
	private JTable table;//选购表格
	private JTable table_1;//购物表格
	DefaultTableModel tableModel = new DefaultTableModel();// 选购模板
	DefaultTableModel tableModel_1 = new DefaultTableModel();// 购物模板
	private JComboBox comboBox;//下拉框
	private String str; // 下拉框的值
	private int row;//选购商品点击事件的行
	private int id;//选购商品点击事件的行的id
	private int row_1;//购物车点击事件的行
	private int id_1;//购物车击事件的行的id
	List<Commodity> comList ;//购物车的集合
    Object[] objects=null;//选购商品属性的数组
    int number=1;//购买物品的数量
    User user;//定义用户接受上层登录进来的用户
    List<Order>  orders = new  ArrayList<Order>();//购物车生成的订单集合

	/**
	 * 用户界面》选购商品
	 */
	public ShoppingFrame(User use) {
		user = use;
		setTitle("\u9009\u8D2D\u5546\u54C1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(450, 45));
		contentPane.add(panel, BorderLayout.NORTH);
		/**
		 * 用户界面》选购商品》搜索按钮
		 */
		JButton button_2 = new JButton("\u641C\u7D22");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText().trim();//获取搜索框的值
				//调用业务层模糊检索的方法
				
				 tableModel.setRowCount(0);//清空表格模型数据
				//调用业务层 遍历集合
				 for (Commodity commodity : cdi.commoditiesFuzzySearch(name)) {
					 Object[] obj =new Object[]{commodity.getComID(),
					                   commodity.getComName(),
					                   commodity.getComPrice(),
					                   commodity.getCount()};
					 tableModel.addRow(obj);//将用户数组传给模板
				 }
				
				
			}
		});
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_2.setBounds(884, 6, 70, 31);
		panel.add(button_2);
		/**
		 * 用户界面》选购商品》搜索文本框
		 */
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(963, 7, 181, 30);
		panel.add(textField);
		/**
		 * 用户界面》选购商品》下拉框
		 */
		comboBox = new JComboBox();
		comboBox.addItem("全部");
		comboBox.setSelectedItem("全部");//默认下拉框的值
		//下拉框事件监听
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
		    str = comboBox.getSelectedItem().toString();//获得下拉框的值
		    tableModel.setRowCount(0);//清空表格模型数据
		    if(str.equals("全部")){//判断监听的是否为全部
		    	 startDatas();//赋值全部商品
		    }
		   
		    //调用业务层 遍历集合
			 for (Commodity commodity : cdi.getCommodityByName(str)) {
				 Object[] obj =new Object[]{commodity.getComID(),
				                   commodity.getComName(),
				                   commodity.getComPrice(),
				                   commodity.getCount()};
				 tableModel.addRow(obj);//将用户数组传给模板
			 }
			}
			
		});
		//调用业务层 获取所有商品类型 方法 赋给数组listComType
		List<ComType> listComType = cts.getAllTypes();
		for (ComType comType : listComType) {
			comboBox.addItem(comType.getTypeName());
			}
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		comboBox.setBounds(14, 6, 85, 31);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setPreferredSize(new Dimension(450, 45));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		/**
		 * 用户界面》选购商品》提交订单按钮
		 */
		JButton button = new JButton("\u53BB\u7ED3\u7B97");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Order or =new Order();//创建一个订单
				or.setCommoditys(comList);//将商品集合赋给订单
				//user.setOrder(or);//将订单赋给用户
				orders.add(or);//将订单放进订单集合里面
				user.setOrders(orders);//将历时订单集合赋给用户
				
				if(user.getOrders()!=null){//判断订单有货的时候
					//跳转至订单界面
					ConfirmOrderFrame frame = new ConfirmOrderFrame(user);
					frame.setVisible(true);
					ShoppingFrame.this.dispose();//关闭本界面
				}
				
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button.setBounds(965, 6, 100, 31);
		panel_1.add(button);
		/**
		 * 用户界面》选购商品》返回按钮
		 */
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//跳转至用户界面
				UserFrame frame = new UserFrame(usi.getUserById(user.getUserID()));
				frame.setVisible(true);
				ShoppingFrame.this.dispose();//关闭本界面
				
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_1.setBounds(1075, 6, 70, 31);
		panel_1.add(button_1);
		/**
		 * 删除购物车商品按钮
		 */
		JButton button_3 = new JButton("\u5220\u9664");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获得table_1的模型
				tableModel_1 = (DefaultTableModel) table_1.getModel();
				tableModel_1.removeRow(row_1);//删除本行
				id_1=0;
			}
		});
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_3.setBounds(726, 6, 100, 31);
		panel_1.add(button_3);
		/**
		 * 清空购物车按钮
		 */
		JButton button_4 = new JButton("\u6E05\u7A7A\u8D2D\u7269\u8F66");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel_1.setRowCount(0);
				comList=new ArrayList<Commodity>();//购物车集合情况
			}
		});
		button_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_4.setBounds(836, 6, 119, 31);
		panel_1.add(button_4);
		/**
		 * 增加购车商品数量的按钮
		 */
		JButton button_5 = new JButton("\u6570\u91CF+");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int buyNumber=(int)table_1.getValueAt(row_1, 3);
				int count=comList.get(row_1).getCount();
				if ( buyNumber<count) {
					number= (int)table_1.getValueAt(row_1, 3)+1;//获得本行的商品购买数量
					//更新购买数量
					table_1.setValueAt(number, row_1, 3);
					//通过本行id在集合中的元素并更新购买数量
					comList.get(row_1).setBuyNumber(number);
					//更新集合此元素的购买数量
					double price= (double)table_1.getValueAt(row_1, 2);//获取单价
					//计算购买价格并更新表内容
					table_1.setValueAt(number*price, row_1, 4);
				}else {
					JOptionPane.showMessageDialog(null, "库存余量不足不能购买更多");
				}
			}
		});
		button_5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_5.setBounds(493, 6, 100, 31);
		panel_1.add(button_5);
		/**
		 * 减少购物车商品的数量按钮
		 */
		JButton button_6 = new JButton("\u6570\u91CF-");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 number= (int)table_1.getValueAt(row_1, 3);
				if (number>1) {
					number= (int)table_1.getValueAt(row_1, 3)-1;//获得本行的商品购买数量
					//更新购买数量
					table_1.setValueAt(number, row_1, 3);
					//通过本行id在集合中的元素并更新购买数量
					comList.get(row_1).setBuyNumber(number);
					//获取单价
					double price= (double)table_1.getValueAt(row_1, 2);
					//计算购买价格并更新表内容
					table_1.setValueAt(number*price, row_1, 4);
				}
			}
		});
		button_6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_6.setBounds(611, 6, 100, 31);
		panel_1.add(button_6);
		
		/**
		 * 添加到购物车按钮
		 */
		comList = new  ArrayList<Commodity>();//创建一个集合存放点击获得的商品
		JButton btnNewButton = new JButton("\u6DFB\u52A0\u8FDB\u8D2D\u7269\u8F66");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id!=0){
					//获取业务层通过id获取商品的方法
					Commodity com =  cdi.getCommodityByComid(id);//浏览商品的id
					//判断浏览商品的id是否与购物车的商品id_1相同
					 
					//comm.getComID()!=com.getComID()
					boolean falg=true;//定义第三方变量判断是否可以添加进购物车
					if (comList.size()==0) {//集合没有数据的情况
						if(com.getCount()==0){//商品数量为0的时候
							falg=false;//不可加入购物车
						}else{
							falg=true;//可加入购物车
						}
					}else{
						//遍历集合
						for (Commodity comm : comList) {
							//集合的商品id_1 和浏览的商品id相同或者商品的数量为0 不可加入购物车
							if (comm.getComID()==id||com.getCount()==0) {
								falg=false;
							}
						}
					}
					if(falg){// 变量为true
						 number=1;
						 com.setBuyNumber(number);//将购买的数量赋给该商品
						 comList.add(com);//将该商品添加进集合
						 //com.setEntryPrice(number*com.getComPrice());
						 objects =new Object[]{com.getComID(),
			                                   com.getComName(),
			                                   com.getComPrice(),
			                                   com.getBuyNumber(),
			                                   number*com.getComPrice()};
					 
			             tableModel_1.addRow(objects);//将用户数组传给模板
			             id=0;//取消点击获得的id
					}else{
							if(com.getCount()==0){//商品数量为0的时候
								JOptionPane.showMessageDialog(null, "该商品已售空!");
							}else{
								for (Commodity comm : comList) {
									if (comm.getComID()==id) {//判断购物车是否存在该商品
										JOptionPane.showMessageDialog(null, "购物车已存在该商品!");
									}
								}
								
							}
						
						}
				}
				
			}
		});
		btnNewButton.setBounds(122, 1, 161, 39);
		panel.add(btnNewButton);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		splitPane.setDividerSize(0);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane.setLeftComponent(splitPane_1);
		splitPane_1.setDividerLocation(120);
		splitPane_1.setDividerSize(0);
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setLeftComponent(panel_2);
		
		JLabel label = new JLabel("\u6240");
		label.setForeground(Color.BLUE);
		label.setToolTipText("");
		label.setFont(new Font("宋体", Font.BOLD, 25));
		
		JLabel label_1 = new JLabel("\u6709");
		label_1.setToolTipText("");
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("宋体", Font.BOLD, 25));
		
		JLabel label_2 = new JLabel("\u5546");
		label_2.setToolTipText("");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("宋体", Font.BOLD, 25));
		
		JLabel label_8 = new JLabel("\u54C1");
		label_8.setToolTipText("");
		label_8.setForeground(Color.BLUE);
		label_8.setFont(new Font("宋体", Font.BOLD, 25));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(55))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addComponent(label)
					.addGap(69)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(79)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
					.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(43))
		);
		panel_2.setLayout(gl_panel_2);
		
		
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane.setRightComponent(splitPane_2);
		splitPane_2.setDividerLocation(120);
		splitPane_2.setDividerSize(0);
		
		JPanel panel_3 = new JPanel();
		splitPane_2.setLeftComponent(panel_3);
		
		JLabel label_4 = new JLabel("\u6211");
		label_4.setForeground(Color.ORANGE);
		label_4.setToolTipText("");
		label_4.setFont(new Font("宋体", Font.BOLD, 25));
		
		JLabel label_3 = new JLabel("\u7684");
		label_3.setToolTipText("");
		label_3.setForeground(Color.ORANGE);
		label_3.setFont(new Font("宋体", Font.BOLD, 25));
		
		JLabel label_5 = new JLabel("\u8D2D");
		label_5.setToolTipText("");
		label_5.setForeground(Color.ORANGE);
		label_5.setFont(new Font("宋体", Font.BOLD, 25));
		
		JLabel label_6 = new JLabel("\u7269");
		label_6.setToolTipText("");
		label_6.setForeground(Color.ORANGE);
		label_6.setFont(new Font("宋体", Font.BOLD, 25));
		
		JLabel label_7 = new JLabel("\u8F66");
		label_7.setToolTipText("");
		label_7.setForeground(Color.ORANGE);
		label_7.setFont(new Font("宋体", Font.BOLD, 25));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGap(55))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(5)
					.addComponent(label_4)
					.addGap(18)
					.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane_1.setRightComponent(scrollPane);
		table = new JTable();
		

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_2.setRightComponent(scrollPane_1);
		table_1 = new JTable();
		/**
		 * 购物车点击事件
		 */
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row_1 = table_1.getSelectedRow();//获得本行
				 id_1  = (int)table_1.getValueAt(row_1, 0);//获得本行的商品id
			}
		});
		tableModel_1.addColumn("编号");
		tableModel_1.addColumn("商品名");
		tableModel_1.addColumn("单价");
		tableModel_1.addColumn("购买数量");
		tableModel_1.addColumn("总价");
		table_1.setModel(tableModel_1);//将购买模型添加到表格上
		scrollPane_1.setViewportView(table_1);
		
		/**
		 * 所有商品的点击事件
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			 row = table.getSelectedRow();//获得本行
			 id  = (int)table.getValueAt(row, 0);//获得本行的商品id
			 
			}
		});
		tableModel.addColumn("编号");
		tableModel.addColumn("商品名");
		tableModel.addColumn("价格");
		tableModel.addColumn("库存数量");
		table.setModel(tableModel);//将库存模型添加到表格上
		startDatas();//全部的商品
		scrollPane.setViewportView(table);
		
		
		splitPane.setDividerLocation(400);
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
	}
	/**
	 * 初始数据
	 */
	public void startDatas(){
		List<Commodity>  list =  cdi.getAllCommodities();
		for (Commodity commodity : list) {
			Object[] obj =new Object[]{commodity.getComID(),
				                       commodity.getComName(),
				                       commodity.getComPrice(),
				                       commodity.getCount()};
               tableModel.addRow(obj);//将用户数组传给模板
		}
		
	}
}
