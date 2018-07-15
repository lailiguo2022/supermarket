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

public class AddTypeidFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	
					
	

	/**
	 * 管理员界面》商品管理按钮》添加种类界面
	 */
	public AddTypeidFrame() {
		setTitle("\u6DFB\u52A0\u5546\u54C1\u79CD\u7C7B");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5546\u54C1\u7C7B\u578B");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		label.setBounds(77, 64, 76, 40);
		contentPane.add(label);
		/**
		 * 管理员界面》商品管理按钮》添加种类界面》商品类型文本框
		 */
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(167, 64, 176, 40);
		contentPane.add(textField);
		/**
		 * 管理员界面》商品管理按钮》添加种类界面》添加按钮
		 */
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addType();
				OrderTypeFrame frame = new OrderTypeFrame();
				frame.setVisible(true);
				AddTypeidFrame.this.dispose();
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button.setBounds(130, 172, 80, 35);
		contentPane.add(button);
		/**
		 * 管理员界面》商品管理按钮》添加种类界面》返回按钮
		 */
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTypeidFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		button_1.setBounds(244, 172, 80, 35);
		contentPane.add(button_1);
		
		/**
		 * 窗体屏幕居中
		 */
		this.setLocationRelativeTo(null);
	}
/**
 * 添加种类方法
 */
	public void addType() {
		ComTypeServices ctd = new ComTypeServicesImpl();
		//获取文本框输入的值
		String typeName = textField.getText().trim();
		//封装对象
		ComType cType = new ComType();
		cType.setTypeName(typeName);
		//判断是否添加成功
		if (ctd.addNewType(cType)) {
			javax.swing.JOptionPane.showMessageDialog(null, "添加成功！");
		}else{
			javax.swing.JOptionPane.showMessageDialog(null, "添加失败，请重新输入！");
		}
		textField.setText("");
	}
}
