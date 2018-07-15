package com.oracle.frame.admin.commodity;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.oracle.frame.admin.AdminFrame;
import com.oracle.po.Commodity;
import com.oracle.services.CommodityServices;
import com.oracle.services.CommodityServicesImpl;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CommodityManagementFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	DefaultTableModel dtm = new DefaultTableModel();
	CommodityServices cServices = new CommodityServicesImpl();
	Commodity commodity;
	int id;
	int row;
	/**
	 * 管理员界面》商品管理界面
	 */
	public CommodityManagementFrame() {
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
		setTitle("\u5546\u54C1\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(450, 83));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.NORTH);
		/**
		 * 管理员界面》商品管理界面》添加商品按钮
		 */
		JButton button = new JButton("\u6DFB\u52A0\u5546\u54C1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开添加商品界面
				AddCommodityFrame frame = new AddCommodityFrame();
				frame.setVisible(true);
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button.setBounds(14, 49, 100, 31);
		panel.add(button);
		/**
		 * 管理员界面》商品管理界面》删除商品按钮
		 */
		JButton button_1 = new JButton("\u5220\u9664\u5546\u54C1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "是否删除？", "提示", JOptionPane.YES_NO_OPTION);
				// 说明你点击的是“是”按钮
				if (choice == JOptionPane.YES_OPTION) {
					deleteCom();
					JOptionPane.showMessageDialog(null, "删除成功!");
					fillTable();
					}
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_1.setBounds(128, 49, 100, 31);
		panel.add(button_1);
		/**
		 * 管理员界面》商品管理界面》修改商品按钮
		 */
		JButton button_2 = new JButton("\u4FEE\u6539\u5546\u54C1");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开修改商品界面
				UpdateCommodityFrame frame = new UpdateCommodityFrame(commodity);
				frame.setVisible(true);
				CommodityManagementFrame.this.dispose();
			}
		});
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_2.setBounds(128, 6, 100, 31);
		panel.add(button_2);
		/**
		 * 管理员界面》商品管理界面》查看商品按钮
		 */
		JButton button_3 = new JButton("\u67E5\u770B\u5546\u54C1");
		button_3.setFocusPainted(false);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillTable();
			}
		});
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_3.setBounds(14, 5, 100, 31);
		panel.add(button_3);
		/**
		 * 管理员界面》商品管理界面》返回按钮
		 */
		JButton button_4 = new JButton("\u8FD4\u56DE");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//点击返回打开主界面
				AdminFrame frame = new AdminFrame();
				frame.setVisible(true);
				//关闭当前界面
				CommodityManagementFrame.this.dispose();
			}
		});
		button_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_4.setBounds(593, 6, 70, 31);
		panel.add(button_4);
		/**
		 * 管理员界面》商品管理界面》搜索商品按钮
		 */
		JButton button_5 = new JButton("\u641C\u7D22");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				commoditiesFuzzySearch();
			}
		});
		button_5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_5.setBounds(294, 6, 70, 31);
		panel.add(button_5);
		/**
		 * 管理员界面》商品管理界面》搜索文本框
		 */
		textField = new JTextField();
		textField.setBounds(384, 7, 195, 30);
		panel.add(textField);
		textField.setColumns(10);
		/**
		 * 管理员界面》商品管理界面》添加种类按钮
		 */
		JButton button_6 = new JButton("\u5546\u54C1\u79CD\u7C7B");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开商品种类界面
				OrderTypeFrame frame = new OrderTypeFrame();
				frame.setVisible(true);
				//关闭当前界面
				CommodityManagementFrame.this.dispose();
			}
		});
		button_6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_6.setBounds(294, 49, 100, 31);
		panel.add(button_6);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		/**
		 * 管理员界面》商品管理界面》Jtable点击事件
		 */
		table = new JTable();
		table.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				id = (int) table.getValueAt(row, 0);
				commodity = cServices.getCommodityByComid(id);
			}
		});
		scrollPane.setViewportView(table);
		/**
		 * 加入表格字段名称
		 */
		table.setModel(dtm);
		// 添加列名
		dtm.addColumn("商品编号");
		dtm.addColumn("商品名称");
		dtm.addColumn("商品价格");
		dtm.addColumn("商品数量");
		dtm.addColumn("商品种类");
		// 调用添加数据方法
		this.fillTable();
		
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
		
	}
	
	
		/**
		 * 模糊查找商品方法
		 */
		public void commoditiesFuzzySearch(){
			String dity = textField.getText().trim();//获取文本框输入的内容
			//调用业务层商品检索方法
			List<Commodity> list = cServices.commoditiesFuzzySearch(dity);
			dtm.setRowCount(0);
			if(!dity.equals("")){
				if(list!=null&&list.size()>0){
					for (Commodity commodity : list) {
						Object[] obj = new Object[]{
								commodity.getComID(),
								commodity.getComName(),
								commodity.getComPrice(),
								commodity.getCount(),
								commodity.getTypeName()
								                    };
						dtm.addRow(obj);
					}
				}
			}
		}
		/**
		 * 删除商品方法
		 */
		public void deleteCom() {
			int comid = commodity.getComID();
			cServices.deleteCommodity(comid);
		}
		/**
		 * 查看商品方法
		 */
			public void fillTable() {
				dtm.setRowCount(0);
				
				List<Commodity> list = cServices.getAllCommodities();
				// 遍历集合
				if(list!=null&&list.size()>0){
					for (Commodity cmy : list) {
						// 将集合中属性的值取出，封装在数组中。
						Object[] com = {cmy.getComID(),cmy.getComName(),
								cmy.getComPrice(),cmy.getCount(),cmy.getTypeName()};
						// 用addRow方法添加数据。用for循环，有一条记录就添加一条用于显示。
						dtm.addRow(com);
					}
			}
		}
}
