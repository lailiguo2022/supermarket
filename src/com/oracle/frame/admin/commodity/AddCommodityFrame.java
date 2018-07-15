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
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class AddCommodityFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	CommodityServices comSer = new CommodityServicesImpl();
	ComTypeServices ctd = new ComTypeServicesImpl();
	JSpinner spinner;
	JComboBox comboBox;
	/**
	 * 管理员界面》商品管理按钮》添加商品界面
	 */
	public AddCommodityFrame() {
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
		setTitle("\u6DFB\u52A0\u5546\u54C1");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5546\u54C1\u540D\u79F0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(72, 54, 76, 40);
		contentPane.add(label);
		/**
		 * 管理员界面》商品管理按钮》添加商品界面》商品名称文本框
		 */
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(162, 54, 200, 40);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u4EF7\u683C");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(72, 122, 76, 40);
		contentPane.add(label_1);
		/**
		 * 管理员界面》商品管理按钮》添加商品界面》商品价格文本框
		 */
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(162, 122, 200, 40);
		contentPane.add(textField_1);
		
		JLabel label_2 = new JLabel("\u6570\u91CF");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_2.setBounds(72, 189, 76, 40);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u5546\u54C1\u79CD\u7C7B");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_3.setBounds(72, 257, 76, 40);
		contentPane.add(label_3);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		spinner.setBounds(162, 189, 60, 40);
		contentPane.add(spinner);
		/**
		 * 管理员界面》商品管理按钮》添加商品界面》商品种类下拉框
		 */
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
		
		comboBox.setBounds(162, 259, 100, 40);
		contentPane.add(comboBox);
		/**
		 * 管理员界面》商品管理按钮》添加商品界面》添加按钮
		 */
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "是否添加？", "提示", JOptionPane.YES_NO_OPTION);
				// 说明你点击的是“是”按钮
				if (choice == JOptionPane.YES_OPTION) {
					addCom();
					JOptionPane.showMessageDialog(null, "添加成功!");
					CommodityManagementFrame frame = new CommodityManagementFrame();
					frame.setVisible(true);
					AddCommodityFrame.this.dispose();
					}
				
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button.setBounds(122, 373, 80, 35);
		contentPane.add(button);
		/**
		 * 管理员界面》商品管理按钮》添加商品界面》返回按钮
		 */
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//关闭当前界面
				AddCommodityFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_1.setBounds(236, 373, 80, 35);
		contentPane.add(button_1);
		
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
	}
	/**
	 * 添加商品方法
	 */
	public void addCom() {
		//获取文本框中的值
		String comName = textField.getText().trim();
		double comPrice = Double.parseDouble(textField_1.getText().trim());
		int count = (Integer) spinner.getValue();
		String typeName = comboBox.getSelectedItem().toString();
		//创建商品对象，封装属性到对象
		Commodity com = new Commodity();
		com.setComName(comName);
		com.setComPrice(comPrice);
		com.setCount(count);
		/**
		 * 封装类型名称，但是comSer的addCommodity方法（实际为dao层CommodityServicesImpl的addCommodity方法）
		 * 传参的sql语句用的是getTypeID
		 * 所以要通过ctd中getTypeByName方法，得到getTypeID，用int接收。
		 * 再用setTypeID(tid)传id，即封装了id就是封装了类型名称
		 */
		//com.setTypeName(typeName);
		
		ComType cType = ctd.getTypeByName(typeName);
		int tid = cType.getTypeID();
		com.setTypeID(tid);
		//调用业务层add方法,判断是否添加成功
		comSer.addCommodity(com);
		
	}
}
