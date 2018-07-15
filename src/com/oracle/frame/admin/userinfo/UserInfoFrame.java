package com.oracle.frame.admin.userinfo;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.oracle.frame.admin.AdminFrame;
import com.oracle.po.User;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class UserInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	DefaultTableModel tableModel;// ģ��
	private int row;//����¼���õ�ĳһ��
	public int id;//�û���ID
	JPanel panel;//�����
	
	
	UserService usi = new UserServiceImpl();//�����û�ҵ���Ķ���

	
	/**
	 * ����Ա���桷�û���Ϣ�������
	 */
	public UserInfoFrame() {
		setBackground(SystemColor.activeCaption);
		setForeground(Color.ORANGE);
		setFont(new Font("΢���ź�", Font.PLAIN, 20));
		setTitle("\u7528\u6237\u4FE1\u606F\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	    panel = new JPanel();
		panel.setPreferredSize( new Dimension( 450, 42));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.NORTH);
	
		table = new JTable();//��ģ����ڱ����
		scrollPane = new JScrollPane();
		scrollPane.setToolTipText("");
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(table);//�������ڽ���
		
		/**
		 * ������¼�
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 1. ��ȡ table�е�ĳһ��
				row = table.getSelectedRow();
				// 2.ͨ��ĳһ�л�ȡ��0λ�ϵ�ֵ
				id =  (int) table.getValueAt(row, 0);
				
			}
		});
		
		/**
		 * �û���Ϣ����������ť
		 */
		btnNewButton = new JButton("\u641C\u7D22");
		btnNewButton.setBackground(new Color(204, 153, 0));
		btnNewButton.setForeground(SystemColor.textHighlight);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBounds(513, 6, 70, 31);
		btnNewButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();//��������
			}
		});
		/**
		 * �û���Ϣ���������ı���
		 */
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
		/**
		 * �س��¼�
		 * @param e
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			search();//��������
		}
		});
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setBackground(Color.WHITE);
		textField.addMouseListener(new MouseAdapter() {
			/**
			 * �����������¼�
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
			}
//			/**
//			 * ���������뿪�¼�
//			 */
//			@Override
//			public void mouseExited(MouseEvent e) {
//				String str = textField.getText().trim();
//				if(str!=null){
//					for (User user: usi.getAllUser()) {
//						if(!user.getUserName().equals(str)){
//							JLabel lblNewLabel = new JLabel("���ʺŲ�����!");
//							lblNewLabel.setBounds(471, 15, 54, 15);
//							panel.add(lblNewLabel);
//						}
//					}
//				}
//				
//			}
			
		});
		textField.setText("\u8BF7\u8F93\u5165\u7528\u6237\u540D");
		textField.setBounds(383, 7, 120, 28);
		textField.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setColumns(10);
		/**
		 * �û���Ϣ�����޸İ�ť
		 */
		button = new JButton("\u4FEE\u6539\u7528\u6237");
		button.setBackground(new Color(204, 153, 0));
		button.setForeground(SystemColor.textHighlight);
		button.setFocusPainted(false);
		button.setBounds(125, 6, 100, 31);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id!=0){
					if(usi.getUserById(id).getPower()==1){
	    				JOptionPane.showMessageDialog(null, "���ݲ����޸Ĺ���Ա!");
	    			}else{
	    				User user = usi.getUserById(id);//����ҵ���ͨ��id��ȡ�û��ķ��� 
						if(user!=null){
							//��ת���޸Ľ���
							UserInfo_updateFrame frame = new  UserInfo_updateFrame(user,UserInfoFrame.this);
							frame.setVisible(true);
						}
	    			}
					
				}
				
			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		/**
		 * �û���Ϣ����ɾ����ť
		 */
		button_1 = new JButton("\u5220\u9664\u7528\u6237");
		button_1.setBackground(new Color(204, 153, 0));
		button_1.setForeground(SystemColor.textHighlight);
		button_1.setBounds(239, 6, 100, 31);
		button_1.setFocusPainted(false);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id!=0){ //����¼���ȡ���û�id
					if((usi.getUserById(id).getPower())==1){
						JOptionPane.showMessageDialog(null, "����ɾ������Ա��");
						id=0;
					}else{
						// ���ȷ����ʾ�򣬻᷵��һ������
						int choice =JOptionPane.showConfirmDialog(null, "�Ƿ�ɾ����","��ʾ��", JOptionPane.YES_NO_OPTION);
						if((usi.getUserById(id).getPower())==1){
							JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
						}else{
							// ˵���������ǡ��ǡ���ť
							if (choice == JOptionPane.YES_OPTION) {
								usi.deleteUserById(id);//����ҵ����ɾ���û�����
								JOptionPane.showMessageDialog(null, "ɾ���ɹ�!");
								tableModel=(DefaultTableModel) table.getModel();//�õ�����ϵ�����ģ��
								tableModel.removeRow(row);//ɾ������¼���õĸ���
								id=0;
							} 
						}
					}
						
						
			   }
			}
		});
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		panel.setLayout(null);
		panel.add(btnNewButton);
		panel.add(textField);
		panel.add(button);
		panel.add(button_1);
		/**
		 * �û���Ϣ�������ذ�ť
		 */
		button_2 = new JButton("\u8FD4\u56DE");
		button_2.setBackground(new Color(204, 153, 0));
		button_2.setForeground(SystemColor.textHighlight);
		button_2.setBounds(593, 6, 70, 31);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�����ϼ�����
				AdminFrame frame = new AdminFrame();
				frame.setVisible(true);
				UserInfoFrame.this.dispose();//�رձ�����
				
			}
		});
		button_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_2.setFocusPainted(false);
		panel.add(button_2);
		
		
		/**
		 * �û���Ϣ����Jtable˫���¼�
		 */
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane.setViewportView(table);
		panel.setPreferredSize( new Dimension( 450, 40));
		/**
		 * �û���Ϣ���������û���ť
		 */
		button_3 =  new JButton("\u6240\u6709\u7528\u6237");
		button_3.setBackground(new Color(204, 153, 0));
		button_3.setForeground(SystemColor.textHighlight);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  update();//�û������ݸ�������
			}
		});
		button_3.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_3.setFocusPainted(false);
		button_3.setBounds(14, 6, 100, 31);
		panel.add(button_3);
		
		
		
		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
		// ���ñ��ģ��
		tableModel = new DefaultTableModel();
		tableModel.addColumn("���");
		tableModel.addColumn("�û���");
		tableModel.addColumn("���");
		tableModel.addColumn("����");
		tableModel.addColumn("�Ա�");
		tableModel.addColumn("����");
		tableModel.addColumn("ע��ʱ��");
		tableModel.addColumn("��ϵ�绰");
		tableModel.addColumn("��ַ");
		table.setModel(tableModel);//��ģ����ӵ������
		
	}
    //���ݸ���
	public void update(){
		
		tableModel.setRowCount(0);//��ձ��ģ������
		//����ҵ����ȡ�����û��ķ�������������
		for (User user : usi.getAllUser()) {
			String power = null;
			if(user.getPower()==1){
				power="����Ա";
			}else{
				power="��ͨ�û�";
			}
		    //����obj����洢�û�����
			Object[] obj = new Object[] { user.getUserID(),
					                      user.getUserName(),
					                      power,//���
					                      user.getName(),
					                      user.getSex(),
					                      user.getAge(),
					                      user.getDate(),
					                      user.getTelephoneNumber(),
					                      user.getAddress()
			                              };
			tableModel.addRow(obj);//���û����鴫��ģ��
		}
	}
	/**
	 * ��������
	 */
	public void search(){
		String userName = textField.getText().trim();//��ȡ������ֵ
		tableModel.setRowCount(0);
		if(!userName.equals("")){// �ж������Ϊ��
			User us = usi.getUserByUserName(userName);//����ҵ����û��������û��ķ���
			if(us!=null){
				String power = null;
				if(us.getPower()==1){
					power="����Ա";
				}else{
					power="��ͨ�û�";
				}
				//���û��ļ��뵽����
				Object[] obj = new Object[]{  us.getUserID(),
						                      us.getUserName(),
						                      power,//���
						                      us.getName(),
						                      us.getSex(),
						                      us.getAge(),
						                      us.getDate(),
						                      us.getTelephoneNumber(),
						                      us.getAddress()
					                        };
				tableModel.addRow(obj);//��������뵽ģ����
			}
		}
	}
}
