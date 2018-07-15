package com.oracle.frame.user.shopping;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.OBJ_ADAPTER;

import com.oracle.frame.user.UserFrame;
import com.oracle.po.ComType;
import com.oracle.po.Commodity;
import com.oracle.po.Order;
import com.oracle.po.User;
import com.oracle.services.ComTypeServices;
import com.oracle.services.ComTypeServicesImpl;
import com.oracle.services.CommodityServices;
import com.oracle.services.CommodityServicesImpl;
import com.oracle.services.UserService;
import com.oracle.services.UserServiceImpl;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShoppingFrame extends JFrame {
    private static final String Douleger = null;
	//������Ʒҵ���Ķ���
	ComTypeServices cts = new ComTypeServicesImpl();
	CommodityServices  cdi = new CommodityServicesImpl();
	UserService usi=new UserServiceImpl();
	private JPanel contentPane;
	private JTextField textField;
	ComType comType;
	private JTable table;//ѡ�����
	private JTable table_1;//������
	DefaultTableModel tableModel = new DefaultTableModel();// ѡ��ģ��
	DefaultTableModel tableModel_1 = new DefaultTableModel();// ����ģ��
	private JComboBox comboBox;//������
	private String str; // �������ֵ
	private int row;//ѡ����Ʒ����¼�����
	private int id;//ѡ����Ʒ����¼����е�id
	private int row_1;//���ﳵ����¼�����
	private int id_1;//���ﳵ���¼����е�id
	List<Commodity> comList ;//���ﳵ�ļ���
    Object[] objects=null;//ѡ����Ʒ���Ե�����
    int number=1;//������Ʒ������
    User user;//�����û������ϲ��¼�������û�
    List<Order>  orders = new  ArrayList<Order>();//���ﳵ���ɵĶ�������

	/**
	 * �û����桷ѡ����Ʒ
	 */
	public ShoppingFrame(User use) {
		user = use;
		setTitle("\u9009\u8D2D\u5546\u54C1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(450, 45));
		contentPane.add(panel, BorderLayout.NORTH);
		/**
		 * �û����桷ѡ����Ʒ��������ť
		 */
		JButton button_2 = new JButton("\u641C\u7D22");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText().trim();//��ȡ�������ֵ
				//����ҵ���ģ�������ķ���
				
				 tableModel.setRowCount(0);//��ձ��ģ������
				//����ҵ��� ��������
				 for (Commodity commodity : cdi.commoditiesFuzzySearch(name)) {
					 Object[] obj =new Object[]{commodity.getComID(),
					                   commodity.getComName(),
					                   commodity.getComPrice(),
					                   commodity.getCount()};
					 tableModel.addRow(obj);//���û����鴫��ģ��
				 }
				
				
			}
		});
		button_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_2.setBounds(884, 6, 70, 31);
		panel.add(button_2);
		/**
		 * �û����桷ѡ����Ʒ�������ı���
		 */
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(963, 7, 181, 30);
		panel.add(textField);
		/**
		 * �û����桷ѡ����Ʒ��������
		 */
		comboBox = new JComboBox();
		comboBox.addItem("ȫ��");
		comboBox.setSelectedItem("ȫ��");//Ĭ���������ֵ
		//�������¼�����
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
		    str = comboBox.getSelectedItem().toString();//����������ֵ
		    tableModel.setRowCount(0);//��ձ��ģ������
		    if(str.equals("ȫ��")){//�жϼ������Ƿ�Ϊȫ��
		    	 startDatas();//��ֵȫ����Ʒ
		    }
		   
		    //����ҵ��� ��������
			 for (Commodity commodity : cdi.getCommodityByName(str)) {
				 Object[] obj =new Object[]{commodity.getComID(),
				                   commodity.getComName(),
				                   commodity.getComPrice(),
				                   commodity.getCount()};
				 tableModel.addRow(obj);//���û����鴫��ģ��
			 }
			}
			
		});
		//����ҵ��� ��ȡ������Ʒ���� ���� ��������listComType
		List<ComType> listComType = cts.getAllTypes();
		for (ComType comType : listComType) {
			comboBox.addItem(comType.getTypeName());
			}
		comboBox.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		comboBox.setBounds(14, 6, 85, 31);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setPreferredSize(new Dimension(450, 45));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		/**
		 * �û����桷ѡ����Ʒ���ύ������ť
		 */
		JButton button = new JButton("\u53BB\u7ED3\u7B97");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Order or =new Order();//����һ������
				or.setCommoditys(comList);//����Ʒ���ϸ�������
				//user.setOrder(or);//�����������û�
				orders.add(or);//�������Ž�������������
				user.setOrders(orders);//����ʱ�������ϸ����û�
				
				if(user.getOrders()!=null){//�ж϶����л���ʱ��
					//��ת����������
					ConfirmOrderFrame frame = new ConfirmOrderFrame(user);
					frame.setVisible(true);
					ShoppingFrame.this.dispose();//�رձ�����
				}
				
			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button.setBounds(965, 6, 100, 31);
		panel_1.add(button);
		/**
		 * �û����桷ѡ����Ʒ�����ذ�ť
		 */
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ת���û�����
				UserFrame frame = new UserFrame(usi.getUserById(user.getUserID()));
				frame.setVisible(true);
				ShoppingFrame.this.dispose();//�رձ�����
				
			}
		});
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_1.setBounds(1075, 6, 70, 31);
		panel_1.add(button_1);
		/**
		 * ɾ�����ﳵ��Ʒ��ť
		 */
		JButton button_3 = new JButton("\u5220\u9664");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//���table_1��ģ��
				tableModel_1 = (DefaultTableModel) table_1.getModel();
				tableModel_1.removeRow(row_1);//ɾ������
				id_1=0;
			}
		});
		button_3.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_3.setBounds(726, 6, 100, 31);
		panel_1.add(button_3);
		/**
		 * ��չ��ﳵ��ť
		 */
		JButton button_4 = new JButton("\u6E05\u7A7A\u8D2D\u7269\u8F66");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel_1.setRowCount(0);
				comList=new ArrayList<Commodity>();//���ﳵ�������
			}
		});
		button_4.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_4.setBounds(836, 6, 119, 31);
		panel_1.add(button_4);
		/**
		 * ���ӹ�����Ʒ�����İ�ť
		 */
		JButton button_5 = new JButton("\u6570\u91CF+");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int buyNumber=(int)table_1.getValueAt(row_1, 3);
				int count=comList.get(row_1).getCount();
				if ( buyNumber<count) {
					number= (int)table_1.getValueAt(row_1, 3)+1;//��ñ��е���Ʒ��������
					//���¹�������
					table_1.setValueAt(number, row_1, 3);
					//ͨ������id�ڼ����е�Ԫ�ز����¹�������
					comList.get(row_1).setBuyNumber(number);
					//���¼��ϴ�Ԫ�صĹ�������
					double price= (double)table_1.getValueAt(row_1, 2);//��ȡ����
					//���㹺��۸񲢸��±�����
					table_1.setValueAt(number*price, row_1, 4);
				}else {
					JOptionPane.showMessageDialog(null, "����������㲻�ܹ������");
				}
			}
		});
		button_5.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_5.setBounds(493, 6, 100, 31);
		panel_1.add(button_5);
		/**
		 * ���ٹ��ﳵ��Ʒ��������ť
		 */
		JButton button_6 = new JButton("\u6570\u91CF-");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 number= (int)table_1.getValueAt(row_1, 3);
				if (number>1) {
					number= (int)table_1.getValueAt(row_1, 3)-1;//��ñ��е���Ʒ��������
					//���¹�������
					table_1.setValueAt(number, row_1, 3);
					//ͨ������id�ڼ����е�Ԫ�ز����¹�������
					comList.get(row_1).setBuyNumber(number);
					//��ȡ����
					double price= (double)table_1.getValueAt(row_1, 2);
					//���㹺��۸񲢸��±�����
					table_1.setValueAt(number*price, row_1, 4);
				}
			}
		});
		button_6.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_6.setBounds(611, 6, 100, 31);
		panel_1.add(button_6);
		
		/**
		 * ��ӵ����ﳵ��ť
		 */
		comList = new  ArrayList<Commodity>();//����һ�����ϴ�ŵ����õ���Ʒ
		JButton btnNewButton = new JButton("\u6DFB\u52A0\u8FDB\u8D2D\u7269\u8F66");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id!=0){
					//��ȡҵ���ͨ��id��ȡ��Ʒ�ķ���
					Commodity com =  cdi.getCommodityByComid(id);//�����Ʒ��id
					//�ж������Ʒ��id�Ƿ��빺�ﳵ����Ʒid_1��ͬ
					 
					//comm.getComID()!=com.getComID()
					boolean falg=true;//��������������ж��Ƿ������ӽ����ﳵ
					if (comList.size()==0) {//����û�����ݵ����
						if(com.getCount()==0){//��Ʒ����Ϊ0��ʱ��
							falg=false;//���ɼ��빺�ﳵ
						}else{
							falg=true;//�ɼ��빺�ﳵ
						}
					}else{
						//��������
						for (Commodity comm : comList) {
							//���ϵ���Ʒid_1 ���������Ʒid��ͬ������Ʒ������Ϊ0 ���ɼ��빺�ﳵ
							if (comm.getComID()==id||com.getCount()==0) {
								falg=false;
							}
						}
					}
					if(falg){// ����Ϊtrue
						 number=1;
						 com.setBuyNumber(number);//�������������������Ʒ
						 comList.add(com);//������Ʒ��ӽ�����
						 //com.setEntryPrice(number*com.getComPrice());
						 objects =new Object[]{com.getComID(),
			                                   com.getComName(),
			                                   com.getComPrice(),
			                                   com.getBuyNumber(),
			                                   number*com.getComPrice()};
					 
			             tableModel_1.addRow(objects);//���û����鴫��ģ��
			             id=0;//ȡ�������õ�id
					}else{
							if(com.getCount()==0){//��Ʒ����Ϊ0��ʱ��
								JOptionPane.showMessageDialog(null, "����Ʒ���ۿ�!");
							}else{
								for (Commodity comm : comList) {
									if (comm.getComID()==id) {//�жϹ��ﳵ�Ƿ���ڸ���Ʒ
										JOptionPane.showMessageDialog(null, "���ﳵ�Ѵ��ڸ���Ʒ!");
									}
								}
								
							}
						
						}
				}
				
			}
		});
		btnNewButton.setBounds(122, 1, 161, 39);
		panel.add(btnNewButton);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);
		splitPane.setDividerSize(0);
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane.setLeftComponent(splitPane_1);
		splitPane_1.setDividerLocation(120);
		splitPane_1.setDividerSize(0);
		
		JPanel panel_2 = new JPanel();
		splitPane_1.setLeftComponent(panel_2);
		
		JLabel label = new JLabel("\u6240");
		label.setForeground(Color.BLUE);
		label.setToolTipText("");
		label.setFont(new Font("����", Font.BOLD, 25));
		
		JLabel label_1 = new JLabel("\u6709");
		label_1.setToolTipText("");
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("����", Font.BOLD, 25));
		
		JLabel label_2 = new JLabel("\u5546");
		label_2.setToolTipText("");
		label_2.setForeground(Color.BLUE);
		label_2.setFont(new Font("����", Font.BOLD, 25));
		
		JLabel label_8 = new JLabel("\u54C1");
		label_8.setToolTipText("");
		label_8.setForeground(Color.BLUE);
		label_8.setFont(new Font("����", Font.BOLD, 25));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(55))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addComponent(label)
					.addGap(69)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(79)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
					.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(43))
		);
		panel_2.setLayout(gl_panel_2);
		
		
		
		JSplitPane splitPane_2 = new JSplitPane();
		splitPane.setRightComponent(splitPane_2);
		splitPane_2.setDividerLocation(120);
		splitPane_2.setDividerSize(0);
		
		JPanel panel_3 = new JPanel();
		splitPane_2.setLeftComponent(panel_3);
		
		JLabel label_4 = new JLabel("\u6211");
		label_4.setForeground(Color.ORANGE);
		label_4.setToolTipText("");
		label_4.setFont(new Font("����", Font.BOLD, 25));
		
		JLabel label_3 = new JLabel("\u7684");
		label_3.setToolTipText("");
		label_3.setForeground(Color.ORANGE);
		label_3.setFont(new Font("����", Font.BOLD, 25));
		
		JLabel label_5 = new JLabel("\u8D2D");
		label_5.setToolTipText("");
		label_5.setForeground(Color.ORANGE);
		label_5.setFont(new Font("����", Font.BOLD, 25));
		
		JLabel label_6 = new JLabel("\u7269");
		label_6.setToolTipText("");
		label_6.setForeground(Color.ORANGE);
		label_6.setFont(new Font("����", Font.BOLD, 25));
		
		JLabel label_7 = new JLabel("\u8F66");
		label_7.setToolTipText("");
		label_7.setForeground(Color.ORANGE);
		label_7.setFont(new Font("����", Font.BOLD, 25));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4))
					.addGap(55))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(5)
					.addComponent(label_4)
					.addGap(18)
					.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane_1.setRightComponent(scrollPane);
		table = new JTable();
		

		JScrollPane scrollPane_1 = new JScrollPane();
		splitPane_2.setRightComponent(scrollPane_1);
		table_1 = new JTable();
		/**
		 * ���ﳵ����¼�
		 */
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row_1 = table_1.getSelectedRow();//��ñ���
				 id_1  = (int)table_1.getValueAt(row_1, 0);//��ñ��е���Ʒid
			}
		});
		tableModel_1.addColumn("���");
		tableModel_1.addColumn("��Ʒ��");
		tableModel_1.addColumn("����");
		tableModel_1.addColumn("��������");
		tableModel_1.addColumn("�ܼ�");
		table_1.setModel(tableModel_1);//������ģ����ӵ������
		scrollPane_1.setViewportView(table_1);
		
		/**
		 * ������Ʒ�ĵ���¼�
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			 row = table.getSelectedRow();//��ñ���
			 id  = (int)table.getValueAt(row, 0);//��ñ��е���Ʒid
			 
			}
		});
		tableModel.addColumn("���");
		tableModel.addColumn("��Ʒ��");
		tableModel.addColumn("�۸�");
		tableModel.addColumn("�������");
		table.setModel(tableModel);//�����ģ����ӵ������
		startDatas();//ȫ������Ʒ
		scrollPane.setViewportView(table);
		
		
		splitPane.setDividerLocation(400);
		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
	}
	/**
	 * ��ʼ����
	 */
	public void startDatas(){
		List<Commodity>  list =  cdi.getAllCommodities();
		for (Commodity commodity : list) {
			Object[] obj =new Object[]{commodity.getComID(),
				                       commodity.getComName(),
				                       commodity.getComPrice(),
				                       commodity.getCount()};
               tableModel.addRow(obj);//���û����鴫��ģ��
		}
		
	}
}
