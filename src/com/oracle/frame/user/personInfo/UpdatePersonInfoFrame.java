package com.oracle.frame.user.personInfo;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oracle.po.User;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * �޸ĸ����û���Ϣ���棬���
 */
public class UpdatePersonInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_userName;
	private JTextField textField_pass;
	private JTextField textField_telephone;
	private JTextField textField_address;
	private JTextField textField_name;
	private JTextField textField_date;
	private JSpinner spinner_age;
	private JComboBox<String> comboBox_sex;
	private User user;//Ԥ���ϲ���洫������user����
	
	UserService usi= new UserServiceImpl();
	
	/**
	 * �û����桷�������Ľ��桷�޸ĸ�����Ϣ����
	 */
	public UpdatePersonInfoFrame(User use) {
		user=usi.getUserById(use.getUserID());
		setFont(new Font("΢���ź�", Font.PLAIN, 20));
		setTitle("\u4FEE\u6539\u4E2A\u4EBA\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label.setBounds(14, 52, 76, 40);
		contentPane.add(label);
		/**
		 * �û����桷�������Ľ��桷�޸ĸ�����Ϣ���桷�û����ı���
		 */
		textField_userName = new JTextField();
		textField_userName.setEnabled(false);
		textField_userName.setBounds(97, 51, 156, 41);
		contentPane.add(textField_userName);
		textField_userName.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BC6    \u7801");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_1.setBounds(14, 108, 76, 40);
		contentPane.add(label_1);
		/**
		 * �û����桷�������Ľ��桷�޸ĸ�����Ϣ���桷�����ı���
		 */
		textField_pass = new JTextField();
		textField_pass.setColumns(10);
		textField_pass.setBounds(97, 107, 156, 41);
		contentPane.add(textField_pass);
		
		JLabel label_2 = new JLabel("\u7535    \u8BDD");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_2.setBounds(202, 162, 76, 40);
		contentPane.add(label_2);
		/**
		 * �û����桷�������Ľ��桷�޸ĸ�����Ϣ���桷�绰�ı���
		 */
		textField_telephone = new JTextField();
		textField_telephone.setColumns(10);
		textField_telephone.setBounds(277, 161, 133, 41);
		contentPane.add(textField_telephone);
		
		JLabel label_3 = new JLabel("\u5730    \u5740");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_3.setBounds(14, 215, 76, 40);
		contentPane.add(label_3);
		/**
		 * �û����桷�������Ľ��桷�޸ĸ�����Ϣ���桷��ַ�ı���
		 */
		textField_address = new JTextField();
		textField_address.setColumns(10);
		textField_address.setBounds(97, 217, 313, 41);
		contentPane.add(textField_address);
		
		JLabel label_4 = new JLabel("\u59D3    \u540D");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_4.setBounds(14, 162, 76, 40);
		contentPane.add(label_4);
		/**
		 * �û����桷�������Ľ��桷�޸ĸ�����Ϣ���桷�����ı���
		 */
		textField_name = new JTextField();
		textField_name.setColumns(10);
		textField_name.setBounds(97, 161, 91, 41);
		contentPane.add(textField_name);
		
		JLabel label_5 = new JLabel("\u6CE8\u518C\u65E5\u671F");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_5.setBounds(14, 268, 76, 40);
		contentPane.add(label_5);
		/**
		 * �û����桷�������Ľ��桷�޸ĸ�����Ϣ���桷ע�������ı���
		 */
		textField_date = new JTextField();
		textField_date.setColumns(10);
		textField_date.setBounds(97, 270, 313, 41);
		contentPane.add(textField_date);
		
		JLabel label_6 = new JLabel("\u6027    \u522B");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_6.setBounds(267, 52, 76, 40);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("\u5E74    \u9F84");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_7.setBounds(267, 108, 76, 40);
		contentPane.add(label_7);
		/**
		 * �û����桷�������Ľ��桷�޸ĸ�����Ϣ���桷�����
		 */
		spinner_age = new JSpinner();
		spinner_age.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		spinner_age.setBounds(342, 108, 68, 40);
		contentPane.add(spinner_age);
		/**
		 * �û����桷�������Ľ��桷�޸ĸ�����Ϣ���桷�Ա�������
		 */
		comboBox_sex = new JComboBox<String>();
		comboBox_sex.setModel(new DefaultComboBoxModel<String>(new String[] {"\u7537", "\u5973"}));
		comboBox_sex.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		comboBox_sex.setBounds(342, 52, 68, 40);
		contentPane.add(comboBox_sex);
		/**
		 * �û����桷�������Ľ��桷�޸ĸ�����Ϣ���桷�ύ��ť
		 */
		JButton button_submit = new JButton("\u63D0\u4EA4");
		button_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�޸��û�������Ϣ
				if(UpdatePersonInfoFrame.this.updateUser()){
					javax.swing.JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
					PersonInfoFrame frame = new PersonInfoFrame(user);
					frame.setVisible(true);
					UpdatePersonInfoFrame.this.dispose();
				}else{
					javax.swing.JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
				}
			}
		});
		button_submit.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_submit.setBounds(69, 371, 80, 35);
		contentPane.add(button_submit);
		/**
		 * �û����桷�������Ľ��桷�޸ĸ�����Ϣ���桷�����ť
		 */
		JButton button_clear = new JButton("\u6062\u590D");
		button_clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�ָ�Ĭ��ֵ
				UpdatePersonInfoFrame.this.addDefaultData();
			}
		});
		button_clear.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_clear.setBounds(176, 371, 80, 35);
		contentPane.add(button_clear);
		/**
		 * �û����桷�������Ľ��桷�޸ĸ�����Ϣ���桷���ذ�ť
		 */
		JButton button_return = new JButton("\u8FD4\u56DE");
		button_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonInfoFrame frame = new PersonInfoFrame(user);
				frame.setVisible(true);
				UpdatePersonInfoFrame.this.dispose();
			}
		});
		button_return.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_return.setBounds(281, 371, 80, 35);
		contentPane.add(button_return);
		
		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
		
		//���Ĭ������
		this.addDefaultData();
	}
	
	/**
	 * Ϊ�޸��û���Ϣ����ĸ������帳�ϲ���洫������user����ĸ���ֵ
	 */
	private void addDefaultData(){
		textField_userName.setText(user.getUserName());
		textField_pass.setText(user.getPass());;
		textField_telephone.setText(user.getTelephoneNumber());
		textField_address.setText(user.getAddress());
		textField_name.setText(user.getName());
		textField_date.setText(user.getDate().toString());
		spinner_age.setValue(user.getAge());
		comboBox_sex.setSelectedItem(user.getSex());
	}
	
	private boolean updateUser(){
		User newuser = new User();
		newuser.setUserID(user.getUserID());
		newuser.setUserName(textField_userName.getText().trim());
		newuser.setPass(textField_pass.getText());
		newuser.setTelephoneNumber(textField_telephone.getText());
		newuser.setAddress(textField_address.getText());
		newuser.setName(textField_name.getText());
		//newuser.setDate(textField_date.getText());
		newuser.setAge((int)spinner_age.getValue());
		newuser.setSex(comboBox_sex.getSelectedItem().toString());
		UserService us = new UserServiceImpl();
		return us.updateUser(newuser);
		
	}
}
