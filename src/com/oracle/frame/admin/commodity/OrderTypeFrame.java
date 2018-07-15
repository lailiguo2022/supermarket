package com.oracle.frame.admin.commodity;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import com.oracle.po.ComType;
import com.oracle.services.ComTypeServices;
import com.oracle.services.ComTypeServicesImpl;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderTypeFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JPanel panel_1;
	private JButton button_10;
	DefaultTableModel dtm = new DefaultTableModel();
	ComTypeServices cType = new ComTypeServicesImpl();
	ComType ct;
	/**
	 * ����Ա���桷��Ʒ������桷��Ʒ����
	 */
	public OrderTypeFrame() {
		setFont(new Font("΢���ź�", Font.PLAIN, 20));
		setTitle("\u5546\u54C1\u79CD\u7C7B");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(450, 45));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.NORTH);
		/**
		 * ����Ա���桷��Ʒ������桷��Ʒ���ࡷ�鿴����
		 */
		button = new JButton("\u67E5\u770B\u79CD\u7C7B");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fillTable();
			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button.setBounds(14, 7, 100, 31);
		panel.add(button);
		/**
		 * ����Ա���桷��Ʒ������桷��Ʒ���ࡷ�������
		 */
		button_1 = new JButton("\u6DFB\u52A0\u79CD\u7C7B");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTypeidFrame frame = new AddTypeidFrame();
				frame.setVisible(true);
				OrderTypeFrame.this.dispose();
			}
		});
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_1.setBounds(128, 7, 100, 31);
		panel.add(button_1);
		/**
		 * ����Ա���桷��Ʒ������桷��Ʒ���ࡷɾ������
		 */
		button_2 = new JButton("\u5220\u9664\u79CD\u7C7B");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "�Ƿ�ɾ����", "��ʾ", JOptionPane.YES_NO_OPTION);
				// ˵���������ǡ��ǡ���ť
				if (choice == JOptionPane.YES_OPTION) {
					delType();
					JOptionPane.showMessageDialog(null, "ɾ���ɹ�!");
					fillTable();
					}
				
			}
		});
		button_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_2.setBounds(244, 7, 100, 31);
		panel.add(button_2);
		/**
		 * ����Ա���桷��Ʒ������桷��Ʒ���ࡷ�޸�����
		 */
		button_3 = new JButton("\u4FEE\u6539\u79CD\u7C7B");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//���޸Ľ���
				UpdateTypeFrame frame = new UpdateTypeFrame(ct);
				frame.setVisible(true);
				OrderTypeFrame.this.dispose();
			}
		});
		button_3.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_3.setBounds(358, 7, 100, 31);
		panel.add(button_3);
		
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setPreferredSize(new Dimension(450, 42));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel_1, BorderLayout.SOUTH);
		/**
		 * ����Ա���桷��Ʒ������桷��Ʒ���ࡷ���ذ�ť
		 */
		button_10 = new JButton("\u8FD4\u56DE");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//����Ʒ�������
				CommodityManagementFrame frame = new CommodityManagementFrame();
				frame.setVisible(true);
				//�رյ�ǰ����
				OrderTypeFrame.this.dispose();
			}
		});
		button_10.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		button_10.setBounds(388, 6, 70, 31);
		panel_1.add(button_10);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		
		table = new JTable();
		/**
		 * ��Jtable���������¼�
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int id = (int) table.getValueAt(row, 0);
				ct = cType.getTypeByID(id);
			}
		});
		table.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		/**
		 * �������ֶ�����
		 */
		table.setModel(dtm);
		// �������
		dtm.addColumn("��Ʒ������");
		dtm.addColumn("��Ʒ��������");
		// ����������ݷ���
		fillTable();
				
		
		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
	}
/**
 * �鿴���෽��
 */
	private void fillTable() {
		dtm.setRowCount(0);
		List<ComType> list = cType.getAllTypes();
		
		// ��������
			for (ComType ctl : list) {
				// �����������Ե�ֵȡ������װ�������С�
				Object[] com = {ctl.getTypeID(),ctl.getTypeName() };
				// ��addRow����������ݡ���forѭ������һ����¼�����һ��������ʾ��
				dtm.addRow(com);
			}
	}
/**
 * ɾ�����෽��
 */
	public void delType() {
		int id = ct.getTypeID();
		cType.deleteTypeByID(id);
	}
}
