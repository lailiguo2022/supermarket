package com.oracle.frame.admin.commodity;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.oracle.po.ComType;
import com.oracle.po.Commodity;
import com.oracle.services.ComTypeServices;
import com.oracle.services.ComTypeServicesImpl;
import com.oracle.services.CommodityServices;
import com.oracle.services.CommodityServicesImpl;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class UpdateCommodityFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	ComTypeServices ctd = new ComTypeServicesImpl();
	CommodityServices comSer = new CommodityServicesImpl();
	Commodity com;
	JComboBox comboBox;
	JSpinner spinner;
	
	/**
	 * 管理员界面》商品管理按钮》修改商品界面
	 */
	public UpdateCommodityFrame(Commodity coms) {
		com = coms;
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
		setTitle("\u4FEE\u6539\u5546\u54C1\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5546\u54C1\u540D\u79F0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(74, 55, 76, 40);
		contentPane.add(label);
		/**
		 * 管理员界面》商品管理按钮》修改商品界面》商品名称文本框
		 */
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(164, 55, 200, 40);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u4EF7\u683C");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(74, 123, 76, 40);
		contentPane.add(label_1);
		/**
		 * 管理员界面》商品管理按钮》修改商品界面》商品价格文本框
		 */
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(164, 123, 200, 40);
		contentPane.add(textField_1);
		
		JLabel label_2 = new JLabel("\u6570\u91CF");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_2.setBounds(74, 190, 76, 40);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u5546\u54C1\u79CD\u7C7B");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_3.setBounds(74, 258, 76, 40);
		contentPane.add(label_3);
		
		spinner = new JSpinner();
		spinner.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		spinner.setBounds(164, 190, 60, 40);
		contentPane.add(spinner);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		/**
		 * 便利集合，获取所有下拉框的值
		 */
		List<ComType> list = ctd.getAllTypes();
		// 遍历集合
			for (ComType ctl : list) {
				
				comboBox.addItem(ctl.getTypeName());
			}
		comboBox.setBounds(164, 260, 100, 40);
		contentPane.add(comboBox);
		/**
		 * 管理员界面》商品管理按钮》修改商品界面》修改按钮
		 */
		JButton button = new JButton("\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "是否修改？", "提示", JOptionPane.YES_NO_OPTION);
				// 说明你点击的是“是”按钮
				if (choice == JOptionPane.YES_OPTION) {
					updateCom();
					JOptionPane.showMessageDialog(null, "修改成功!");
					CommodityManagementFrame frame = new CommodityManagementFrame();
					frame.setVisible(true);
					UpdateCommodityFrame.this.dispose();
				    
					}
				
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button.setBounds(124, 374, 80, 35);
		contentPane.add(button);
		// 给文本框赋值,comtype要在构造方法中申明（形参）
		//要先有new的文本框对象，不然会出现空指针
			textField.setText(coms.getComName());
			textField_1.setText(String.valueOf(coms.getComPrice()));
			spinner.setValue(coms.getCount());
			comboBox.setSelectedItem(coms.getTypeName());		
						
		/**
		 * 管理员界面》商品管理按钮》修改商品界面》返回按钮
		 */
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//关闭当前窗口
				UpdateCommodityFrame.this.dispose();
				
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_1.setBounds(238, 374, 80, 35);
		contentPane.add(button_1);
		
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
	}
	/**
	 * 修改商品信息方法
	 */
	public void updateCom() {
		//获取文本框中的值
		String comName = textField.getText().trim();
		double comPrice = Double.parseDouble(textField_1.getText().trim());
		int count = (Integer) spinner.getValue();
		String typeName = comboBox.getSelectedItem().toString();
		//创建商品对象，封装属性到对象
		Commodity commo=new Commodity();
		commo.setComID(com.getComID());
		commo.setComName(comName);
		commo.setComPrice(comPrice);
		commo.setCount(count);
		com.setTypeName(typeName);
		ComType cType = ctd.getTypeByName(typeName);
		int tid = cType.getTypeID();
		commo.setTypeID(tid);
		//调用业务层update方法,判断是否添加成功
		comSer.update(commo);
	}

}
