package com.oracle.frame.user.personInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.oracle.frame.user.shopping.ConfirmPaymentFrame;
import com.oracle.po.Commodity;
import com.oracle.po.Order;
import com.oracle.po.User;
import com.oracle.services.OrderServices;
import com.oracle.services.OrderServicesImpl;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PersonOrderDetailFrame extends JFrame {

	User user;
	private JPanel contentPane;
	private JTextField textField_serch;
	JSplitPane splitPane;
	private JTable table_order;
	private JTable table_coms;
	private DefaultTableModel tableModel_order;// ģ��
	private DefaultTableModel tableModel_coms;// ģ��
	
	int orderID;
	
	OrderServices osi=new OrderServicesImpl();
	UserService us = new UserServiceImpl();

	/**
	 * �û����桷�������Ľ��桷���˶�����ϸ
	 */
	public PersonOrderDetailFrame(User use) {
		user= use;
		setTitle("\u4E2A\u4EBA\u8BA2\u5355\u660E\u7EC6");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(450, 42));
		contentPane.add(panel, BorderLayout.NORTH);
		/**
		 * �û����桷�������Ľ��桷���˶�����ϸ�����˶�����ť
		 */
		JButton button_order = new JButton("\u4E2A\u4EBA\u8BA2\u5355");
		button_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//���ñ��෽���������ģ����Ӷ����б�
				addOrder();
			}
		});
		button_order.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_order.setBounds(14, 6, 100, 31);
		panel.add(button_order);
		/**
		 * �û����桷�������Ľ��桷���˶�����ϸ�����ť
		 */
		JButton button_pay = new JButton("\u4ED8\u6B3E");
		button_pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(orderID!=0){
					Order ord = osi.searchOder_CommodityByOrderID(orderID);
					if(ord.getOrderStuas().equals("δ����")){
						ConfirmPaymentFrame frame = new ConfirmPaymentFrame(ord);
						frame.setVisible(true);
						PersonOrderDetailFrame.this.dispose();
					}else{
						JOptionPane.showMessageDialog(null, "�ö����Ѹ��");
					}
				}else{
					JOptionPane.showMessageDialog(null, "��ѡ��һ������");
				}
				
				
			}
		});
		button_pay.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_pay.setBounds(122, 6, 100, 31);
		panel.add(button_pay);
		
		/**
		 * �û����桷�������Ľ��桷���˶�����ϸ��ɾ����ť
		 */
		JButton button_delete = new JButton("\u5220\u9664");
		button_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(orderID!=0){
					
						int choice=JOptionPane.showConfirmDialog(null,"�Ƿ�ɾ���˶���", "����",JOptionPane.YES_NO_OPTION);//�Ƿ�ɾ������ʾ��
						if (choice==JOptionPane.YES_OPTION) {//���ѡ��yes
							osi.deleteOderById(orderID);//ͨ��idɾ���˶���
						}
						/**
						 * ˢ�´�ҳ
						 */
						addOrder();
				}else{
					JOptionPane.showMessageDialog(null, "��ѡ��һ������");
				}
			}
		});
		button_delete.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_delete.setBounds(232, 6, 100, 31);
		panel.add(button_delete);
		/**
		 * �û����桷�������Ľ��桷���˶�����ϸ��������ť
		 */
		JButton button_serch = new JButton("\u641C\u7D22");
		button_serch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderSerch();
			}
		});
		button_serch.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_serch.setBounds(360, 6, 70, 31);
		panel.add(button_serch);
		/**
		 * �û����桷�������Ľ��桷���˶�����ϸ�����ذ�ť
		 */
		JButton button_return = new JButton("\u8FD4\u56DE");
		button_return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonInfoFrame frame = new PersonInfoFrame(user);
				frame.setVisible(true);
				PersonOrderDetailFrame.this.dispose();
			}
		});
		button_return.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_return.setBounds(593, 6, 70, 31);
		panel.add(button_return);
		/**
		 * �û����桷�������Ľ��桷���˶�����ϸ�������ı���
		 */
		textField_serch = new JTextField();
		textField_serch.setColumns(10);
		textField_serch.setBounds(439, 7, 140, 30);
		panel.add(textField_serch);

		
		//�ָ�����
		splitPane = new JSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		splitPane.setDividerSize(0);
		
		table_order = new JTable();
		table_order.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table_order.getSelectedRow();
				orderID=(int) table_order.getValueAt(row, 0);
				//����Ʒ����Ž��ұ߱�����
				orderDetails();
			}
		});
		scrollPane.setViewportView(table_order);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		
		table_coms = new JTable();
		scrollPane_1.setViewportView(table_coms);
		
		
		/**
		 * ������ʾ���ָ��������Ļ����
		 */
		this.setVisible(true);
		this.splitPane.setDividerLocation(0.5);
		this.setLocationRelativeTo(null);
		
		//�����ģ����ӱ�ͷ
		tableModel_order = new DefaultTableModel();
		tableModel_order.addColumn("�������");
		tableModel_order.addColumn("�û����");
		tableModel_order.addColumn("������������");
		tableModel_order.addColumn("����״̬");
		tableModel_order.addColumn("�������");
		table_order.setModel(tableModel_order);
		
		tableModel_coms = new DefaultTableModel();
		tableModel_coms.addColumn("��Ʒ����");
		tableModel_coms.addColumn("�������");
		tableModel_coms.addColumn("����");
		tableModel_coms.addColumn("���");
		table_coms.setModel(tableModel_coms);
	}
	
	private void addOrder(){
		tableModel_order.setRowCount(0);
		List<Order> list = us.getOrderByUserId(user.getUserID());
		if(list!=null&&list.size()>0){
			for (Order order : list) {
				tableModel_order.addRow(new Object[]{
						order.getOrderID(),
						order.getUserID(),
						order.getCreationTime(),
						order.getOrderStuas(),
						order.getTotalPrice()
				});
			}
		}
	}
	
	private void orderDetails(){
		tableModel_coms.setRowCount(0);
		Order order = osi.searchOder_CommodityByOrderID(orderID);
		for ( Commodity com : order.getCommoditys()) {
			tableModel_coms.addRow(new Object[]{
				com.getComName(),
				com.getBuyNumber(),
				com.getComPrice(),
				com.getEntryPrice()
			});
		}
	}
	
	private void orderSerch(){
		try {
			int textID=Integer.parseInt(textField_serch.getText());//��ȡ�ı����ֵ
			
			Order selectedOrder=osi.searchOderById(textID);//���ݴ�id��ȡ�˶���
			  if (selectedOrder!=null&&selectedOrder.getCreationTime()!=null) {
				  tableModel_order.setRowCount(0);//��ձ�
				  tableModel_order.addRow(new Object[]{
						   selectedOrder.getOrderID(),
						   selectedOrder.getUserID(),
						   selectedOrder.getCreationTime(),
						   selectedOrder.getOrderStuas(),
						   selectedOrder.getTotalPrice()});//����order����˱�
			   }
			   else {
				JOptionPane.showMessageDialog(null, "�Բ�����Ҫ���ҵı�Ų����ڻ��Ÿ�ʽ����ȷ");
				
			   }
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "���ݲ���Ϊ���ұ���Ϊ����");
		}
	}
}
