package com.oracle.frame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.oracle.po.User;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;

public class RegistFrame extends JFrame {
	// ����ҵ������
	UserService service = new UserServiceImpl();
	private JPanel contentPane;
	private JTextField textField;
	private JLabel label;
	private JLabel label_1;
	private JTextField textField_1;
	private JLabel label_2;
	private JTextField textField_2;
	private JLabel label_3;
	private JTextField textField_3;
	private JLabel label_4;
	private JTextField textField_4;
	private JButton btnNewButton;
	private JButton button;

	/**
	 * ע�����
	 */
	public RegistFrame() {
		setFont(new Font("΢���ź�", Font.PLAIN, 20));
		setTitle("\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/**
		 * ע����桷ע���û����ı���
		 */
		textField = new JTextField();
		textField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField.setBounds(159, 38, 200, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		label = new JLabel("\u7528\u6237\u540D");
		label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(69, 38, 76, 40);
		contentPane.add(label);

		label_1 = new JLabel("\u5BC6    \u7801");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_1.setBounds(69, 108, 76, 40);
		contentPane.add(label_1);
		/**
		 * ע����桷ע�������ı���
		 */
		textField_1 = new JTextField();
		textField_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(159, 108, 200, 40);
		contentPane.add(textField_1);

		label_2 = new JLabel("\u59D3    \u540D");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_2.setBounds(69, 176, 76, 40);
		contentPane.add(label_2);
		/**
		 * ע����桷ע�������ı���
		 */
		textField_2 = new JTextField();
		textField_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(159, 176, 200, 40);
		contentPane.add(textField_2);

		label_3 = new JLabel("\u7535    \u8BDD");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_3.setBounds(69, 240, 76, 40);
		contentPane.add(label_3);
		/**
		 * ע����桷ע��绰�ı���
		 */
		textField_3 = new JTextField();
		textField_3.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(159, 240, 200, 40);
		contentPane.add(textField_3);

		label_4 = new JLabel("\u5730    \u5740");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_4.setBounds(69, 307, 76, 40);
		contentPane.add(label_4);
		/**
		 * ע����桷ע���ַ�ı���
		 */
		textField_4 = new JTextField();
		textField_4.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_4.setColumns(10);
		textField_4.setBounds(159, 307, 200, 40);
		contentPane.add(textField_4);
		/**
		 * ע����桷�ύ��ť
		 */
		btnNewButton = new JButton("\u63D0\u4EA4");
		// �¼�������(�ӿ�)
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();// ����ע�᷽��
			}
		});
		btnNewButton.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		btnNewButton.setBounds(80, 390, 80, 35);
		contentPane.add(btnNewButton);
		/**
		 * ע����桷�����ť
		 */
		button = new JButton("\u6E05\u9664");
		// �����¼�(�ӿ�)
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����ı��������
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button.setBounds(179, 390, 80, 35);
		contentPane.add(button);
		/**
		 * ע����桷���ذ�ť
		 */
		JButton button_1 = new JButton("\u8FD4\u56DE");
		// �¼�������(�ӿ�)
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���ص�¼����
				LoginFrame frame = new LoginFrame();
				frame.setVisible(true);
				// �رյ�ǰ����
				RegistFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_1.setBounds(279, 390, 80, 35);
		contentPane.add(button_1);
		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
	}

	/**
	 * ע���жϷ���
	 */
	public void register() {
		// ��ȡ����������ֵ
		String userName = textField.getText().trim();// �û����ı���
		String pass = textField_1.getText().trim();// ע�������ı���
		String name = textField_2.getText().trim();// ע����������
		String telephoneNumber = textField_3.getText().trim();// ע��绰�ı���
		String address = textField_4.getText().trim();// ע���ַ�ı���
		// ����ҵ���ͨ���û�����ȡ�û��ķ��� ����һ���û�
		User user = new User();
		// �ж�������е��û����Ƿ����
		if (service.getUserByUserName(userName) == null) {
			user.setUserName(userName);
			user.setPass(pass);
			user.setName(name);
			user.setTelephoneNumber(telephoneNumber);
			user.setAddress(address);
			// �ж�����������е������Ƿ�Ϊ��
			if (!pass.equals("") && !name.equalsIgnoreCase("") && !telephoneNumber.equalsIgnoreCase("")) {
				if (!address.equalsIgnoreCase("")) {
					if (service.addUser(user)) {
						javax.swing.JOptionPane.showMessageDialog(null, "ע��ɹ�");
						// �����¼����
						LoginFrame frame = new LoginFrame();
						frame.setVisible(true);
						// �رյ�ǰ����
						RegistFrame.this.dispose();
					}
				} else {
					javax.swing.JOptionPane.showMessageDialog(null, "ÿ������򶼱��");
				}
			} else {
				javax.swing.JOptionPane.showMessageDialog(null, "ע��ʧ��");
			}
		}
	}
}
