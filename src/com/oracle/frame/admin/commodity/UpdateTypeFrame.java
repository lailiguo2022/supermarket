package com.oracle.frame.admin.commodity;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.oracle.po.ComType;
import com.oracle.services.ComTypeServices;
import com.oracle.services.ComTypeServicesImpl;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateTypeFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	

	/**
	 * 修改种类界面
	 */
	public UpdateTypeFrame(ComType comType) {
		setTitle("\u4FEE\u6539\u79CD\u7C7B");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5546\u54C1\u7F16\u53F7");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(79, 51, 76, 40);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(169, 51, 176, 40);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(169, 106, 176, 40);
		contentPane.add(textField_1);
		/**
		 * 修改按钮
		 */
		JButton button = new JButton("\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateType();
				OrderTypeFrame frame = new OrderTypeFrame();
				frame.setVisible(true);
				UpdateTypeFrame.this.dispose();
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button.setBounds(130, 172, 80, 35);
		contentPane.add(button);
		// 给文本框赋值,comtype要在构造方法中申明（形参）
		//要先有new的文本框对象，不然会出现空指针
				textField.setText( String.valueOf(comType.getTypeID()));
				textField_1.setText(comType.getTypeName());
		/**
		 * 管理员界面》商品管理界面》商品种类》修改种类》返回按钮
		 */
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//关闭当前界面
				UpdateTypeFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_1.setBounds(244, 172, 80, 35);
		contentPane.add(button_1);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u7C7B\u578B");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label_1.setBounds(79, 106, 76, 40);
		contentPane.add(label_1);
		
		
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
	}
	public void updateType() {
		//获取文本框中值
		int comid = Integer.parseInt(textField.getText().trim());
		String comName = textField_1.getText().trim();
		//封装对象
		ComType cType = new ComType();
		cType.setTypeID(comid);
		cType.setTypeName(comName);
		//访问业务层修改方法
		//判断是否修改成功
		ComTypeServices cts = new ComTypeServicesImpl();
		if (cts.updateType(cType)) {
			javax.swing.JOptionPane.showMessageDialog(null, "修改成功！");
		}else {
			javax.swing.JOptionPane.showMessageDialog(null, "修改失败，请重新选择！");
		}
		//关闭当前界面
		UpdateTypeFrame.this.dispose();
	}
}
