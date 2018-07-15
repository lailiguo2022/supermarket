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
	 * �޸��������
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
		label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label.setBounds(79, 51, 76, 40);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(169, 51, 176, 40);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(169, 106, 176, 40);
		contentPane.add(textField_1);
		/**
		 * �޸İ�ť
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
		button.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button.setBounds(130, 172, 80, 35);
		contentPane.add(button);
		// ���ı���ֵ,comtypeҪ�ڹ��췽�����������βΣ�
		//Ҫ����new���ı�����󣬲�Ȼ����ֿ�ָ��
				textField.setText( String.valueOf(comType.getTypeID()));
				textField_1.setText(comType.getTypeName());
		/**
		 * ����Ա���桷��Ʒ������桷��Ʒ���ࡷ�޸����ࡷ���ذ�ť
		 */
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�رյ�ǰ����
				UpdateTypeFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_1.setBounds(244, 172, 80, 35);
		contentPane.add(button_1);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u7C7B\u578B");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_1.setBounds(79, 106, 76, 40);
		contentPane.add(label_1);
		
		
		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
	}
	public void updateType() {
		//��ȡ�ı�����ֵ
		int comid = Integer.parseInt(textField.getText().trim());
		String comName = textField_1.getText().trim();
		//��װ����
		ComType cType = new ComType();
		cType.setTypeID(comid);
		cType.setTypeName(comName);
		//����ҵ����޸ķ���
		//�ж��Ƿ��޸ĳɹ�
		ComTypeServices cts = new ComTypeServicesImpl();
		if (cts.updateType(cType)) {
			javax.swing.JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
		}else {
			javax.swing.JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�������ѡ��");
		}
		//�رյ�ǰ����
		UpdateTypeFrame.this.dispose();
	}
}
