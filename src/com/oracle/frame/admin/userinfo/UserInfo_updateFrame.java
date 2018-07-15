package com.oracle.frame.admin.userinfo;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.oracle.po.User;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class UserInfo_updateFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel label_1;
	private JTextField textField_1;
	private JLabel label_2;
	private JTextField textField_2;
	private JLabel label_3;
	private JTextField textField_3;
	private JLabel label_4;
	private JTextField textField_4;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private User user;
    //�����û�ҵ���Ķ���
	UserService  usi = new UserServiceImpl();
	
	/**
	 * �û���Ϣ�����޸ġ��޸Ľ���
	 */
	public UserInfo_updateFrame(User use,final UserInfoFrame menu ) {
		user=use;
		setFont(new Font("΢���ź�", Font.ITALIC, 20));
		setTitle("\u4FEE\u6539\u7528\u6237\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//���ùر��Ӵ���
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label.setBounds(63, 35, 76, 40);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(153, 35, 200, 40);
		contentPane.add(textField);
		
		label_1 = new JLabel("\u5BC6    \u7801");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_1.setBounds(63, 103, 76, 40);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(153, 103, 200, 40);
		contentPane.add(textField_1);
		
		label_2 = new JLabel("\u59D3    \u540D");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_2.setBounds(63, 171, 76, 40);
		contentPane.add(label_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(153, 171, 200, 40);
		contentPane.add(textField_2);
		
		label_3 = new JLabel("\u7535    \u8BDD");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_3.setBounds(63, 235, 76, 40);
		contentPane.add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(153, 235, 200, 40);
		contentPane.add(textField_3);
		
		label_4 = new JLabel("\u5730    \u5740");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_4.setBounds(63, 302, 76, 40);
		contentPane.add(label_4);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("΢���ź�", Font.PLAIN, 18));
//		textField_4.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusLost(FocusEvent e) {
//                if (textField_4.getText().isEmpty()){
//                   JOptionPane.showMessageDialog(null, "��ַ����Ϊ��");
//                }
//            }
//        });
		textField_4.setColumns(10);
		textField_4.setBounds(153, 302, 200, 40);
		contentPane.add(textField_4);
		
		textField.setText(user.getUserName());//�û�����ֵ Ĭ�� �����޸�
		textField_1.setText(user.getPass()); //�����ֵ
	    textField_2.setText(user.getName()); //������ֵ Ĭ��
	    textField_3.setText(user.getTelephoneNumber()); //�绰��ֵ Ĭ��
	    textField_4.setText(user.getAddress()); //��ַ��ֵ Ĭ��
		/**
		 * �û���Ϣ�����޸İ�ť���޸��û���Ϣ���桷�ύ��ť
		 */
		button = new JButton("\u63D0\u4EA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String pass = textField_1.getText().trim(); //��ȡ����������ֵ
			    String name = textField_2.getText().trim(); //��ȡ����������ֵ
			    String telephonenumber = textField_3.getText().trim(); //��ȡ�绰������ֵ
			    String address= textField_4.getText().trim(); //��ȡ��ַ������ֵ
			    //��װ�û���������
			    user.setPass(pass);
    			user.setName(name);
    			user.setTelephoneNumber(telephonenumber);
    			user.setAddress(address);
    			//����ҵ����޸��û��ķ���
    			// ���ȷ����ʾ�򣬻᷵��һ������
				int choice = JOptionPane.showConfirmDialog(null, "�Ƿ��޸ģ�", "��ʾ", JOptionPane.YES_NO_OPTION);
					// ˵���������ǡ��ǡ���ť
					if (choice == JOptionPane.YES_OPTION) {
						if(user.getPass().equals("")){
							JOptionPane.showMessageDialog(null, "���벻��Ϊ��!");
						}else{
							usi.updateUser(user);
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�!");
							UserInfo_updateFrame.this.dispose();//�رձ�����
							menu.update();//��ʾ�û���������
							menu.id=0;//�Ƴ�����¼���õ�id
						}
					} else
					// ˵���������ǡ��񡱰�ť
					if (choice == JOptionPane.NO_OPTION) {
						JOptionPane.showMessageDialog(null, "�޸�ʧ��!");
						UserInfo_updateFrame.this.dispose();//�رձ�����
						menu.id=0;//�Ƴ�����¼���õ�id
					}
			
			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button.setBounds(79, 384, 80, 35);
		contentPane.add(button);
		/**
		 * �û���Ϣ�����޸İ�ť���޸��û���Ϣ���桷�����ť
		 */
		button_1 = new JButton("\u6E05\u9664");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  textField_1.setText(""); //�����������ֵ
				  textField_2.setText(""); //�����������ֵ
				  textField_3.setText(""); //���绰�����ֵ
				  textField_4.setText(""); //����ַ�����ֵ
			}
		});
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_1.setBounds(173, 384, 80, 35);
		contentPane.add(button_1);
		/**
		 * �û���Ϣ�����޸İ�ť���޸��û���Ϣ���桷���ذ�ť
		 */
		button_2 = new JButton("\u8FD4\u56DE");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.update();//���������û�����
				UserInfo_updateFrame.this.dispose();//�رձ�����
			}
		});
		button_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_2.setBounds(267, 384, 80, 35);
		contentPane.add(button_2);
		
		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
	}
	
	
}
