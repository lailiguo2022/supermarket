package com.oracle.frame.admin.commodity;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.oracle.po.ComType;
import com.oracle.services.ComTypeServices;
import com.oracle.services.ComTypeServicesImpl;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderTypeFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JPanel panel_1;
	private JButton button_10;
	DefaultTableModel dtm = new DefaultTableModel();
	ComTypeServices cType = new ComTypeServicesImpl();
	ComType ct;
	/**
	 * 管理员界面》商品管理界面》商品种类
	 */
	public OrderTypeFrame() {
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
		setTitle("\u5546\u54C1\u79CD\u7C7B");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(450, 45));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.NORTH);
		/**
		 * 管理员界面》商品管理界面》商品种类》查看种类
		 */
		button = new JButton("\u67E5\u770B\u79CD\u7C7B");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fillTable();
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button.setBounds(14, 7, 100, 31);
		panel.add(button);
		/**
		 * 管理员界面》商品管理界面》商品种类》添加种类
		 */
		button_1 = new JButton("\u6DFB\u52A0\u79CD\u7C7B");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTypeidFrame frame = new AddTypeidFrame();
				frame.setVisible(true);
				OrderTypeFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_1.setBounds(128, 7, 100, 31);
		panel.add(button_1);
		/**
		 * 管理员界面》商品管理界面》商品种类》删除种类
		 */
		button_2 = new JButton("\u5220\u9664\u79CD\u7C7B");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "是否删除？", "提示", JOptionPane.YES_NO_OPTION);
				// 说明你点击的是“是”按钮
				if (choice == JOptionPane.YES_OPTION) {
					delType();
					JOptionPane.showMessageDialog(null, "删除成功!");
					fillTable();
					}
				
			}
		});
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_2.setBounds(244, 7, 100, 31);
		panel.add(button_2);
		/**
		 * 管理员界面》商品管理界面》商品种类》修改种类
		 */
		button_3 = new JButton("\u4FEE\u6539\u79CD\u7C7B");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开修改界面
				UpdateTypeFrame frame = new UpdateTypeFrame(ct);
				frame.setVisible(true);
				OrderTypeFrame.this.dispose();
			}
		});
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_3.setBounds(358, 7, 100, 31);
		panel.add(button_3);
		
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setPreferredSize(new Dimension(450, 42));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		/**
		 * 管理员界面》商品管理界面》商品种类》返回按钮
		 */
		button_10 = new JButton("\u8FD4\u56DE");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//打开商品管理界面
				CommodityManagementFrame frame = new CommodityManagementFrame();
				frame.setVisible(true);
				//关闭当前界面
				OrderTypeFrame.this.dispose();
			}
		});
		button_10.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		button_10.setBounds(388, 6, 70, 31);
		panel_1.add(button_10);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		table = new JTable();
		/**
		 * 给Jtable添加鼠标点击事件
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int id = (int) table.getValueAt(row, 0);
				ct = cType.getTypeByID(id);
			}
		});
		table.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		/**
		 * 加入表格字段名称
		 */
		table.setModel(dtm);
		// 添加列名
		dtm.addColumn("商品种类编号");
		dtm.addColumn("商品种类名称");
		// 调用添加数据方法
		fillTable();
				
		
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
	}
/**
 * 查看种类方法
 */
	private void fillTable() {
		dtm.setRowCount(0);
		List<ComType> list = cType.getAllTypes();
		
		// 遍历集合
			for (ComType ctl : list) {
				// 将集合中属性的值取出，封装在数组中。
				Object[] com = {ctl.getTypeID(),ctl.getTypeName() };
				// 用addRow方法添加数据。用for循环，有一条记录就添加一条用于显示。
				dtm.addRow(com);
			}
	}
/**
 * 删除种类方法
 */
	public void delType() {
		int id = ct.getTypeID();
		cType.deleteTypeByID(id);
	}
}
