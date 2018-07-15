package com.oracle.frame;

import java.awt.EventQueue;
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

import com.oracle.frame.admin.AdminFrame;
import com.oracle.frame.user.UserFrame;
import com.oracle.po.User;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends JFrame {
	// �����û�ҵ���Ķ���
	UserService service = new UserServiceImpl();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		LoginFrame frame = new LoginFrame();
		frame.setVisible(true);
	}

	/**
	 * ��¼����
	 */
	public LoginFrame() {
		setFont(new Font("΢���ź�", Font.PLAIN, 20));
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 525, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/**
		 * ��¼���桷��¼��ť
		 */
		JButton btnNewButton = new JButton("\u767B\u5F55");
		// �¼�������(�ӿ�)
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();// ���õ�¼����
			}
		});
		btnNewButton.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		btnNewButton.setBounds(80, 232, 80, 35);
		contentPane.add(btnNewButton);
		/**
		 * ��¼���桷�����ť
		 */
		JButton button = new JButton("\u6E05\u9664");
		// �¼�������(�ӿ�)
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����ı���ԭ��������
				textField.setText("");
				textField_1.setText("");
			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button.setBounds(213, 232, 80, 35);
		contentPane.add(button);
		/**
		 * ��¼���桷ע�ᰴť
		 */
		JButton button_1 = new JButton("\u6CE8\u518C");
		// �¼�������(�ӿ�)
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ����ע�����
				RegistFrame frame = new RegistFrame();
				frame.setVisible(true);
				// �رյ�ǰ����
				LoginFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_1.setBounds(346, 232, 80, 35);
		contentPane.add(button_1);
		/**
		 * ��¼���桷�û����ı���
		 */
		textField = new JTextField();
		textField.setForeground(Color.BLACK);
		textField.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		textField.setBounds(183, 59, 200, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		lblNewLabel.setBounds(105, 57, 64, 40);
		contentPane.add(lblNewLabel);
		/**
		 * ��¼���桷�����ı���
		 */
		textField_1 = new JTextField();
		textField_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		textField_1.setForeground(Color.BLACK);
		textField_1.setColumns(10);
		textField_1.setBounds(183, 128, 200, 40);
		contentPane.add(textField_1);

		JLabel label = new JLabel("\u5BC6    \u7801");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		label.setBounds(105, 128, 64, 38);
		contentPane.add(label);

		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
	}

	/**
	 * ��¼�����ж�
	 */
	public void login() {
		// ��ȡ������ÿ��������ֵ
		String userName = textField.getText().trim();// �û����ı���
		String pass = textField_1.getText().trim();// �����ı���
		// System.out.println(userName+pass);
		// ����ҵ����ͨ���û�����ȡ�û��ķ��� ����һ���û�
		User user = service.getUserByUserName(userName);
		if (user != null) {
			if (user.getPass().equalsIgnoreCase(pass)) {
				if (user.getPower() == 1) {
					// �������Ա����
					AdminFrame frame = new AdminFrame();
					frame.setVisible(true);
					// �رյ�ǰ����
					LoginFrame.this.dispose();
				} else {
					// �����û�����
					UserFrame frame = new UserFrame(user);
					frame.setVisible(true);
					// �رյ�ǰ����
					LoginFrame.this.dispose();
				}
			}
		} else {
			javax.swing.JOptionPane.showMessageDialog(null, "��¼ʧ��");
		}
	}
}
