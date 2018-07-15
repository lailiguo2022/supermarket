package com.oracle.frame.admin.commodity;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.oracle.po.ComType;
import com.oracle.po.Commodity;
import com.oracle.services.ComTypeServices;
import com.oracle.services.ComTypeServicesImpl;
import com.oracle.services.CommodityServices;
import com.oracle.services.CommodityServicesImpl;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class UpdateCommodityFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	ComTypeServices ctd = new ComTypeServicesImpl();
	CommodityServices comSer = new CommodityServicesImpl();
	Commodity com;
	JComboBox comboBox;
	JSpinner spinner;
	
	/**
	 * ����Ա���桷��Ʒ����ť���޸���Ʒ����
	 */
	public UpdateCommodityFrame(Commodity coms) {
		com = coms;
		setFont(new Font("΢���ź�", Font.PLAIN, 20));
		setTitle("\u4FEE\u6539\u5546\u54C1\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u5546\u54C1\u540D\u79F0");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label.setBounds(74, 55, 76, 40);
		contentPane.add(label);
		/**
		 * ����Ա���桷��Ʒ����ť���޸���Ʒ���桷��Ʒ�����ı���
		 */
		textField = new JTextField();
		textField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBounds(164, 55, 200, 40);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("\u5546\u54C1\u4EF7\u683C");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_1.setBounds(74, 123, 76, 40);
		contentPane.add(label_1);
		/**
		 * ����Ա���桷��Ʒ����ť���޸���Ʒ���桷��Ʒ�۸��ı���
		 */
		textField_1 = new JTextField();
		textField_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(164, 123, 200, 40);
		contentPane.add(textField_1);
		
		JLabel label_2 = new JLabel("\u6570\u91CF");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_2.setBounds(74, 190, 76, 40);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u5546\u54C1\u79CD\u7C7B");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		label_3.setBounds(74, 258, 76, 40);
		contentPane.add(label_3);
		
		spinner = new JSpinner();
		spinner.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		spinner.setBounds(164, 190, 60, 40);
		contentPane.add(spinner);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		/**
		 * �������ϣ���ȡ�����������ֵ
		 */
		List<ComType> list = ctd.getAllTypes();
		// ��������
			for (ComType ctl : list) {
				
				comboBox.addItem(ctl.getTypeName());
			}
		comboBox.setBounds(164, 260, 100, 40);
		contentPane.add(comboBox);
		/**
		 * ����Ա���桷��Ʒ����ť���޸���Ʒ���桷�޸İ�ť
		 */
		JButton button = new JButton("\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "�Ƿ��޸ģ�", "��ʾ", JOptionPane.YES_NO_OPTION);
				// ˵���������ǡ��ǡ���ť
				if (choice == JOptionPane.YES_OPTION) {
					updateCom();
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�!");
					CommodityManagementFrame frame = new CommodityManagementFrame();
					frame.setVisible(true);
					UpdateCommodityFrame.this.dispose();
				    
					}
				
			}
		});
		button.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button.setBounds(124, 374, 80, 35);
		contentPane.add(button);
		// ���ı���ֵ,comtypeҪ�ڹ��췽�����������βΣ�
		//Ҫ����new���ı�����󣬲�Ȼ����ֿ�ָ��
			textField.setText(coms.getComName());
			textField_1.setText(String.valueOf(coms.getComPrice()));
			spinner.setValue(coms.getCount());
			comboBox.setSelectedItem(coms.getTypeName());		
						
		/**
		 * ����Ա���桷��Ʒ����ť���޸���Ʒ���桷���ذ�ť
		 */
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�رյ�ǰ����
				UpdateCommodityFrame.this.dispose();
				
			}
		});
		button_1.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		button_1.setBounds(238, 374, 80, 35);
		contentPane.add(button_1);
		
		/**
		 * ������Ļ����
		 */
		this.setLocationRelativeTo(null);
	}
	/**
	 * �޸���Ʒ��Ϣ����
	 */
	public void updateCom() {
		//��ȡ�ı����е�ֵ
		String comName = textField.getText().trim();
		double comPrice = Double.parseDouble(textField_1.getText().trim());
		int count = (Integer) spinner.getValue();
		String typeName = comboBox.getSelectedItem().toString();
		//������Ʒ���󣬷�װ���Ե�����
		Commodity commo=new Commodity();
		commo.setComID(com.getComID());
		commo.setComName(comName);
		commo.setComPrice(comPrice);
		commo.setCount(count);
		com.setTypeName(typeName);
		ComType cType = ctd.getTypeByName(typeName);
		int tid = cType.getTypeID();
		commo.setTypeID(tid);
		//����ҵ���update����,�ж��Ƿ���ӳɹ�
		comSer.update(commo);
	}

}
