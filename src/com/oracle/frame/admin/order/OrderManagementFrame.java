package com.oracle.frame.admin.order;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.Messaging.SyncScopeHelper;

import com.oracle.dao.OrderDaoImpl;
import com.oracle.frame.admin.AdminFrame;
import com.oracle.po.Commodity;
import com.oracle.po.Order;
import com.oracle.services.OrderServices;
import com.oracle.services.OrderServicesImpl;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;

import java.awt.Color;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

public class OrderManagementFrame extends JFrame {
	OrderServices os=new OrderServicesImpl();//ʵ��������ҵ���
	UserService us=new UserServiceImpl();//ʵ�����û�ҵ���
	DefaultTableModel dtm=new DefaultTableModel();//�������ж����ı�ģ��
	DefaultTableModel dtm2=new DefaultTableModel();//�������������ģ��
	private JPanel contentPane;
	private JTable table;//���������ϱ�
	private JTable table_1;//��������ϸ���±�
	private JTextField textField;
	int oID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
					OrderManagementFrame frame = new OrderManagementFrame();
					frame.setVisible(true);
			
	}

	/**
	 * ����Ա���桷�����������
	 */
	public OrderManagementFrame() {
		 
		//ʵ�������ģ��
		setTitle("\u8BA2\u5355\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(250);//�����Ϸָ��ĸ߶�
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		/**
		 * ����Ա���桷����������桷���ж�����ť
		 */
		JButton btnNewButton = new JButton("\u6240\u6709\u8BA2\u5355");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 dtm.setRowCount(0);//��ձ�
			 addRowData();//���¼��ض�������
			}
		});
		/**
		 * ����Ա���桷����������桷�޸Ķ�����ť
		 */
		JButton btnNewButton_1 = new JButton("\u4FEE\u6539\u8BA2\u5355");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			Order selectedOrder=os.searchOderById(oID);//����id��õ�ǰ������
			oID=0;
			if (selectedOrder.getOrderID()==0) {
				JOptionPane.showMessageDialog(null, "����ѡȡҪ�޸ĵĶ���");
			}else {
				UpdateOrderFrame uof=new UpdateOrderFrame(selectedOrder,OrderManagementFrame.this);//���˶��������޸Ľ���
				uof.setVisible(true);
			}
			}
		});
		/**
		 * ����Ա���桷����������桷ɾ��������ť
		 */
		JButton btnNewButton_2 = new JButton("\u5220\u9664\u8BA2\u5355");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice=JOptionPane.showConfirmDialog(null,"�Ƿ�ɾ���˶���", "����",JOptionPane.YES_NO_OPTION);//�Ƿ�ɾ������ʾ��
				if (choice==JOptionPane.YES_OPTION) {//���ѡ��yes
					os.deleteOderById(oID);//ͨ��idɾ���˶���
				}
				/**
				 * ˢ�´�ҳ
				 */
				dtm.setRowCount(0);//ˢ�´�ҳ
				addRowData();//�������
			}
		});
		/**
		 * ����Ա���桷����������桷���ذ�ť
		 */
		JButton btnNewButton_3 = new JButton("\u8FD4\u56DE");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderManagementFrame.this.dispose();
				AdminFrame frame = new AdminFrame();
				frame.setVisible(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		/**
		 * ����Ա���桷����������桷�ı���
		 */
		textField = new JTextField();
		textField.setColumns(10);
		/**
		 * ����Ա���桷����������桷������ť
		 */
		JButton btnNewButton_4 = new JButton("\u641C\u7D22");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int textID=Integer.parseInt(textField.getText());//��ȡ�ı����ֵ
					
					Order selectedOrder=os.searchOderById(textID);//���ݴ�id��ȡ�˶���
					  if (selectedOrder!=null&&selectedOrder.getCreationTime()!=null) {
						   dtm.setRowCount(0);//��ձ�
						   dtm.addRow(new Object[]{
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
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(33)
					.addComponent(btnNewButton)
					.addGap(28)
					.addComponent(btnNewButton_1)
					.addGap(18)
					.addComponent(btnNewButton_2)
					.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
					.addComponent(btnNewButton_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_3)
					.addGap(21))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_3, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		//�����������ϱ� ��Ӷ���ģ��
		table = new JTable(dtm);
		//��������ϸ������ӱ�ͷ
		dtm2.addColumn("��Ʒ����");
		dtm2.addColumn("�������");
		dtm2.addColumn("����");
		dtm2.addColumn("���");
		/**
		 * ���������Ľ�����������ʾ�ڶ���������±�
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//��ն�����������
				dtm2.setRowCount(0);
				int row=table.getSelectedRow();//��ȡ��ǰ��
				oID=(int)table.getValueAt(row, 0);//�������
				
				int userID=(int)table.getValueAt(row, 1);//�û�����
				//ͨ��������Ų�ѯ����Ӧ�в˵���ϸ�Ķ���
			Order order=os.searchOder_CommodityByOrderID(oID);
			//ͨ���û���Ż�ȡ�û� ���û���
			String str2=us.getUserById(userID).getUserName();
			//��ȡ�˶�������Ʒ����
			List<Commodity> list=order.getCommoditys();	
			
			
			/**
			 * ��������ϸģ�壨�±����������
			 */
	for (Commodity co : list) {
		dtm2.addRow(new Object[]{
				co.getComName(),
				co.getBuyNumber(),
				co.getComPrice(),
				co.getEntryPrice()});
	}
			}
		});
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane.setRightComponent(scrollPane_1);
		/**
		 * ����������ϸ���±�
		 */
		table_1 = new JTable(dtm2);
		scrollPane_1.setViewportView(table_1);
		contentPane.setLayout(gl_contentPane);
		/**
		 * ����ģ���ͷ���ϱ�
		 */
		dtm.addColumn("�������");
		dtm.addColumn("�û����");
		dtm.addColumn("������������");
		dtm.addColumn("����״̬");
		dtm.addColumn("�������");
		this.addRowData();
		
		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
	}
	/**
	 * ��������ģ�壨�ϱ���������ݣ��������ݣ�
	 */
	public void addRowData() {
		List<Order> list=os.getAllOder();//��ѯ���ж����õ���������
		for (Order order : list) {
			dtm.addRow(new Object[]{
					order.getOrderID(),
					order.getUserID(),
					order.getCreationTime(),
					order.getOrderStuas(),
					order.getTotalPrice()});
		}
	}
}
