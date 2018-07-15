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
	 * ����Ա���桷��Ʒ����ť������������
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
		label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label.setBounds(77, 64, 76, 40);
		contentPane.add(label);
		/**
		 * ����Ա���桷��Ʒ����ť�����������桷��Ʒ�����ı���
		 */
		textField = new JTextField();
		textField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(167, 64, 176, 40);
		contentPane.add(textField);
		/**
		 * ����Ա���桷��Ʒ����ť�����������桷��Ӱ�ť
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
		button.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button.setBounds(130, 172, 80, 35);
		contentPane.add(button);
		/**
		 * ����Ա���桷��Ʒ����ť�����������桷���ذ�ť
		 */
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTypeidFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_1.setBounds(244, 172, 80, 35);
		contentPane.add(button_1);
		
		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
	}
/**
 * ������෽��
 */
	public void addType() {
		ComTypeServices ctd = new ComTypeServicesImpl();
		//��ȡ�ı��������ֵ
		String typeName = textField.getText().trim();
		//��װ����
		ComType cType = new ComType();
		cType.setTypeName(typeName);
		//�ж��Ƿ���ӳɹ�
		if (ctd.addNewType(cType)) {
			javax.swing.JOptionPane.showMessageDialog(null, "��ӳɹ���");
		}else{
			javax.swing.JOptionPane.showMessageDialog(null, "���ʧ�ܣ����������룡");
		}
		textField.setText("");
	}
}
