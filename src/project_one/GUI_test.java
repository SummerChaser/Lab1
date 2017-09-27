//修改记录8
package project_one;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import java.lang.Character;
import javax.swing.JScrollPane;
//功能二索引

public class GUI_test extends JFrame {

	private JPanel contentPane;
	private JTextField f1_adr;
	private JTextField f3_w1;
	private JTextField f3_w2;
	private JTextField f5_w1;
	private JTextField f5_w2;
	private JTextField f5_w;
	private JTextField f6_choose;
	// static JTextArea f2_output;

	static int[][] E, D, path;
	static String[] TxtWordArray;
	static int wordNum = 0;
	static boolean flag = true;
	static final int INFINITY = 10000;
	static HashMap<String, List<String>> map = new HashMap<String, List<String>>();
	static StringBuffer preStr = new StringBuffer(); // 初步处理字符串
	static StringBuffer newText = new StringBuffer();
	static StringBuffer pathWay = new StringBuffer();
	static StringBuffer randomPath = new StringBuffer();
	static List<String> wordList = new ArrayList<String>();
	static List<String> edgePairList = new ArrayList<String>();
	static Pattern p = Pattern.compile("[.,\"\\?!:' ]");
	static Graph graph;
	private static String filenameTemp;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_test frame = new GUI_test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_test() {
		setTitle("软件工程 - 实验一");

		CardLayout card = new CardLayout(0, 0);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 516);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(258, 11, 589, 472);
		panel.setBackground(new Color(238, 238, 238));

		JButton f1 = new JButton("功能一：生成有向图");
		f1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(panel, "功能一");
			}
		});
		f1.setBounds(11, 11, 235, 73);
		f1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton f2 = new JButton("功能二：展示有向图");
		f2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(panel, "功能二");
			}
		});
		f2.setBounds(11, 84, 235, 73);
		f2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton f3 = new JButton("功能三：查询桥接词");
		f3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(panel, "功能三");
			}
		});
		f3.setBounds(11, 169, 235, 73);
		f3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton f4 = new JButton("功能四：生成新文本");
		f4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(panel, "功能四");
			}
		});
		f4.setBounds(11, 248, 235, 73);
		f4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton f5 = new JButton("功能五：查询最短路径");
		f5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(panel, "功能五");
			}
		});
		f5.setBounds(11, 327, 235, 73);
		f5.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton f6 = new JButton(" 功能六: 随机游走    ");
		f6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(panel, "功能六");
			}
		});
		f6.setBounds(11, 406, 235, 73);
		f6.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		contentPane.setLayout(null);
		contentPane.add(f5);
		contentPane.add(f1);
		contentPane.add(f2);
		contentPane.add(f3);
		contentPane.add(f4);
		contentPane.add(f6);
		contentPane.add(panel);

		panel.setLayout(card);

		JPanel p1 = new JPanel();
		panel.add(p1, "功能一");

		f1_adr = new JTextField();
		f1_adr.setText("/Users/summerchaser/Desktop/111.txt");
		f1_adr.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("文本处理结果 ：");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton btnNewButton = new JButton("确定");

		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton button = new JButton("使用默认地址");

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 使用默认地址的操作 把默认地址串set到文本框
			}
		});
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 13));

		JLabel lblNewLabel_1 = new JLabel("请输入文件地址 ：");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_p1 = new GroupLayout(p1);
		gl_p1.setHorizontalGroup(gl_p1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_p1.createSequentialGroup().addGap(32).addGroup(gl_p1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 512, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addGroup(gl_p1.createSequentialGroup()
								.addGroup(gl_p1.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_p1.createSequentialGroup().addComponent(lblNewLabel_1)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(button))
										.addComponent(f1_adr, GroupLayout.PREFERRED_SIZE, 368,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18).addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 96,
										GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(45, Short.MAX_VALUE)));
		gl_p1.setVerticalGroup(gl_p1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_p1.createSequentialGroup().addGap(30)
						.addGroup(gl_p1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_p1.createParallelGroup(Alignment.BASELINE)
								.addComponent(f1_adr, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addGap(26).addComponent(lblNewLabel_2).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 264, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(35, Short.MAX_VALUE)));

		JTextArea f1_out = new JTextArea();
		scrollPane.setViewportView(f1_out);

		p1.setLayout(gl_p1);

		JPanel p2 = new JPanel();
		panel.add(p2, "功能二");

		JLabel lblNewLabel = new JLabel("打印结果 ：");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton f2_showGraph = new JButton("生成并展示有向图");

		f2_showGraph.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_p2 = new GroupLayout(p2);
		gl_p2.setHorizontalGroup(gl_p2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_p2.createSequentialGroup().addGap(39)
						.addGroup(gl_p2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_p2.createSequentialGroup()
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 537,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(13, Short.MAX_VALUE))
								.addGroup(gl_p2.createSequentialGroup().addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED, 282, Short.MAX_VALUE)
										.addComponent(f2_showGraph).addGap(24)))));
		gl_p2.setVerticalGroup(gl_p2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_p2.createSequentialGroup().addGap(36)
						.addGroup(gl_p2.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel)
								.addComponent(f2_showGraph, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(39, Short.MAX_VALUE)));

		JTextArea f2_output = new JTextArea();
		scrollPane_1.setViewportView(f2_output);
		p2.setLayout(gl_p2);

		JPanel p3 = new JPanel();
		panel.add(p3, "功能三");

		JLabel label = new JLabel("请输入查询的单词 ：");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel label_3 = new JLabel("单词一 ：");
		label_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel label_4 = new JLabel("单词二 ：");
		label_4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		f3_w1 = new JTextField();
		f3_w1.setText("to");
		f3_w1.setColumns(10);

		f3_w2 = new JTextField();
		f3_w2.setText("strange");
		f3_w2.setColumns(10);

		JButton f3_query = new JButton("查询");

		f3_query.setFont(new Font("Lucida Grande", Font.PLAIN, 19));

		JLabel lblNewLabel_3 = new JLabel("查询结果为 ：");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_p3 = new GroupLayout(p3);
		gl_p3.setHorizontalGroup(gl_p3.createParallelGroup(Alignment.LEADING).addGroup(gl_p3.createSequentialGroup()
				.addGap(35)
				.addGroup(gl_p3.createParallelGroup(Alignment.LEADING, false).addComponent(label)
						.addGroup(gl_p3.createSequentialGroup()
								.addGroup(gl_p3.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel_3)
										.addGroup(gl_p3.createSequentialGroup().addGap(12)
												.addGroup(gl_p3.createParallelGroup(Alignment.LEADING)
														.addComponent(label_3).addComponent(label_4))))
								.addGap(18)
								.addGroup(gl_p3.createParallelGroup(Alignment.LEADING)
										.addComponent(f3_w1, GroupLayout.PREFERRED_SIZE, 192,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(f3_w2))))
				.addGap(50).addComponent(f3_query, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(89, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING,
						gl_p3.createSequentialGroup().addContainerGap(43, Short.MAX_VALUE)
								.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 512, GroupLayout.PREFERRED_SIZE)
								.addGap(34)));
		gl_p3.setVerticalGroup(gl_p3.createParallelGroup(Alignment.LEADING).addGroup(gl_p3.createSequentialGroup()
				.addGroup(gl_p3
						.createParallelGroup(Alignment.LEADING).addGroup(
								gl_p3.createSequentialGroup().addGap(37).addComponent(label).addGap(31)
										.addGroup(gl_p3.createParallelGroup(Alignment.BASELINE)
												.addComponent(f3_w1, GroupLayout.PREFERRED_SIZE, 36,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(label_3))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_p3.createParallelGroup(Alignment.BASELINE)
												.addComponent(f3_w2, GroupLayout.PREFERRED_SIZE, 36,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(label_4))
										.addGap(42).addComponent(lblNewLabel_3))
						.addGroup(gl_p3.createSequentialGroup().addGap(92).addComponent(f3_query,
								GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)))
				.addGap(18).addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(39, Short.MAX_VALUE)));

		JTextArea f3_out = new JTextArea();
		scrollPane_2.setViewportView(f3_out);
		p3.setLayout(gl_p3);

		JPanel p4 = new JPanel();
		panel.add(p4, "功能四");

		JLabel label_1 = new JLabel("请输入文本 ：");
		label_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

		JLabel lblNewLabel_4 = new JLabel("生成新文本为 ：");
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 16));

		JTextArea f4_in = new JTextArea();
		f4_in.setText("Seek to explore new and exciting synergies");

		JButton f4_create = new JButton("生成");

		f4_create.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_p4 = new GroupLayout(p4);
		gl_p4.setHorizontalGroup(gl_p4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_p4.createSequentialGroup().addContainerGap(32, Short.MAX_VALUE)
						.addGroup(gl_p4.createParallelGroup(Alignment.LEADING)
								.addComponent(f4_in, GroupLayout.PREFERRED_SIZE, 528, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_p4.createParallelGroup(Alignment.TRAILING, false)
										.addGroup(Alignment.LEADING, gl_p4.createSequentialGroup().addComponent(label_1)
												.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(f4_create).addGap(18))
										.addGroup(gl_p4.createSequentialGroup()
												.addGroup(gl_p4.createParallelGroup(Alignment.LEADING)
														.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 530,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblNewLabel_4))
												.addGap(27))))
						.addGap(6)));
		gl_p4.setVerticalGroup(gl_p4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_p4.createSequentialGroup().addContainerGap(38, Short.MAX_VALUE)
						.addGroup(gl_p4.createParallelGroup(Alignment.TRAILING).addComponent(label_1)
								.addComponent(f4_create, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(f4_in, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE).addGap(30)
						.addComponent(lblNewLabel_4).addGap(18)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addGap(36)));

		JTextArea f4_out = new JTextArea();
		scrollPane_3.setViewportView(f4_out);
		p4.setLayout(gl_p4);

		JPanel p5 = new JPanel();
		panel.add(p5, "功能五");

		JLabel lbll = new JLabel("单词一 ：");
		lbll.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		JLabel label_5 = new JLabel("请输入查询的单词 ：");
		label_5.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		JLabel label_6 = new JLabel("单词二 ：");
		label_6.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		f5_w1 = new JTextField();
		f5_w1.setText("seek");
		f5_w1.setColumns(10);

		f5_w2 = new JTextField();
		f5_w2.setText("explore");
		f5_w2.setColumns(10);

		JLabel label_7 = new JLabel("请输入单词 ： （可选功能）");
		label_7.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		JButton f5_1 = new JButton("查询两单词间最短路径");

		f5_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		f5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		f5_w = new JTextField();
		f5_w.setText("to");
		f5_w.setColumns(10);

		JButton f5_2 = new JButton("查询到其它所有单词最短路径");

		f5_2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		JLabel lblNewLabel_5 = new JLabel("查询结果：");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		JScrollPane scrollPane_4 = new JScrollPane();
		GroupLayout gl_p5 = new GroupLayout(p5);
		gl_p5.setHorizontalGroup(gl_p5.createParallelGroup(Alignment.LEADING).addGroup(gl_p5.createSequentialGroup()
				.addGap(20)
				.addGroup(gl_p5.createParallelGroup(Alignment.LEADING).addGroup(gl_p5
						.createParallelGroup(Alignment.LEADING, false)
						.addComponent(f5_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_p5.createSequentialGroup()
								.addGroup(gl_p5.createParallelGroup(Alignment.TRAILING).addComponent(lbll)
										.addComponent(label_6))
								.addGroup(gl_p5.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_p5.createSequentialGroup().addGap(16).addComponent(f5_w1,
												GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_p5.createSequentialGroup().addGap(18).addComponent(f5_w2))))
						.addComponent(f5_1, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
						.addComponent(
								f5_w, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_7))
				.addGroup(gl_p5.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_p5.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
								.addComponent(lblNewLabel_5).addGap(219))
						.addGroup(gl_p5.createSequentialGroup().addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()))));
		gl_p5.setVerticalGroup(gl_p5.createParallelGroup(Alignment.LEADING).addGroup(gl_p5.createSequentialGroup()
				.addGroup(gl_p5.createParallelGroup(Alignment.LEADING, false).addGroup(gl_p5.createSequentialGroup()
						.addGap(28).addComponent(label_5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGap(20)
						.addGroup(gl_p5.createParallelGroup(Alignment.BASELINE).addComponent(lbll)
								.addComponent(f5_w1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addGap(11)
						.addGroup(gl_p5.createParallelGroup(Alignment.BASELINE)
								.addComponent(f5_w2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_6))
						.addGap(18).addComponent(f5_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addGap(74).addComponent(label_7).addGap(18)
						.addComponent(f5_w, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(f5_2, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)).addGroup(
								gl_p5.createSequentialGroup().addGap(25).addComponent(lblNewLabel_5)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 351,
												GroupLayout.PREFERRED_SIZE)))
				.addContainerGap(53, Short.MAX_VALUE)));

		JTextArea f5_out = new JTextArea();
		scrollPane_4.setViewportView(f5_out);
		p5.setLayout(gl_p5);

		JPanel p6 = new JPanel();
		panel.add(p6, "功能六");

		JLabel lblSuijiyouyo = new JLabel("随机游走");
		lblSuijiyouyo.setFont(new Font("Lucida Grande", Font.PLAIN, 24));

		JButton f6_begin = new JButton("开始");

		f6_begin.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JLabel label_2 = new JLabel("系统选择了 ：");
		label_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		f6_choose = new JTextField();
		f6_choose.setEditable(false);
		f6_choose.setColumns(10);

		JLabel label_8 = new JLabel("输出路径  ： （结果以文本形式保存到桌面）");
		label_8.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton button_2 = new JButton("中断程序");
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
				// 获取结果文本 输出到桌面
			}
		});
		button_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JScrollPane scrollPane_5 = new JScrollPane();
		GroupLayout gl_p6 = new GroupLayout(p6);
		gl_p6.setHorizontalGroup(gl_p6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_p6.createSequentialGroup().addGroup(gl_p6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_p6.createSequentialGroup().addGap(201).addComponent(button_2,
								GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_p6.createSequentialGroup().addGap(25).addGroup(gl_p6
								.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_p6.createSequentialGroup().addGap(11)
										.addGroup(gl_p6.createParallelGroup(Alignment.LEADING)
												.addComponent(lblSuijiyouyo).addComponent(f6_begin,
														GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
										.addGap(98)
										.addGroup(gl_p6.createParallelGroup(Alignment.LEADING)
												.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 106,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(f6_choose, GroupLayout.PREFERRED_SIZE, 183,
														GroupLayout.PREFERRED_SIZE)))
								.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(28, Short.MAX_VALUE)));
		gl_p6.setVerticalGroup(gl_p6.createParallelGroup(Alignment.LEADING).addGroup(gl_p6.createSequentialGroup()
				.addGap(42)
				.addGroup(gl_p6.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_p6.createSequentialGroup().addComponent(lblSuijiyouyo).addGap(18))
						.addGroup(gl_p6.createSequentialGroup()
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
				.addGroup(gl_p6.createParallelGroup(Alignment.LEADING)
						.addComponent(f6_choose, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(f6_begin, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
				.addGap(37).addComponent(label_8, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
				.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE).addGap(20)
				.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE).addGap(14)));

		JTextArea f6_out = new JTextArea();
		scrollPane_5.setViewportView(f6_out);
		p6.setLayout(gl_p6);
		// =======================================================

		btnNewButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				InputStream fi;
				int c;
				f1_out.setText("");
				try {
					String adr = f1_adr.getText();
					fi = new FileInputStream(adr);
					try {
						while ((c = fi.read()) != -1) {
							Character m = new Character((char) c);
							if (Character.isLetter(m)) {
								preStr.append(m.toString());
							} else if (p.matcher(m.toString()).matches()) {
								preStr.append(" ");
							}
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				TxtWordArray = preStr.toString().toLowerCase().trim().split("\\s+");

				f1_out.append("The Text equal to : \n");

				for (String word : TxtWordArray) {
					// if wordlist no contain word , then add
					f1_out.append(word + " ");
					if (!wordList.contains(word)) {
						wordList.add(word);
						wordNum++; // recored word number
					}
				}

				f1_out.append("\nThe number of word is ：" + wordNum);

			}
		});

		// ============================================================

		f2_showGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 功能二索引
				E = new int[wordNum][wordNum]; // 建立边集
				f2_output.setText("");
				int preNum, curNum, i, j;
				String pre = "#";
				for (String word : TxtWordArray) {
					if (pre != "#") {
						preNum = wordList.indexOf(pre);
						curNum = wordList.indexOf(word);
						E[preNum][curNum]++;
						pre = word;
					} else {
						pre = word;
					}
				}
				f2_output.append("The graph matrix is ：\n");
				for (i = 0; i < wordNum; i++) {
					for (j = 0; j < wordNum; j++) {
						if (E[i][j] == 0) {
							E[i][j] = INFINITY;
							f2_output.append("0 ");
							// System.out.print("0 ");
						} else {
							f2_output.append(String.valueOf(E[i][j]) + " ");
							// System.out.print(String.valueOf(E[i][j]));

						}
					}
					f2_output.append("\n");
				}
				f2_output.append("\n");
				// System.out.print("\n");
				graph = new Graph(wordList, E, wordNum); // 创建图的实例
				f2_output.append("The graph presented is ：\n");
				// System.out.print("The graph presented is ：");
				for (i = 0; i < graph.wordNum; i++) {
					for (j = 0; j < graph.wordNum; j++) {
						if (E[i][j] != INFINITY) {
							String word1 = graph.wordList.get(i);
							String word2 = graph.wordList.get(j);
							String s = "Edge: " + word1 + " --> " + word2 + "   Weigth:  " + String.valueOf(E[i][j])
									+ "\n";
							f2_output.append(s);
							// System.out.print("s");
						}
					}
				}
				drawGraph(graph);
				// 生成有向图 的函数
				// 展示有向图 把生成的有向图打印出来 包括那些边 数据setText到下面的文本区域
			}

		});

		// =======================================

		f3_query.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 获取两个单词的内容，调用函数，把结果set到输出框
				f3_out.setText("");

				int i;
				for (i = 0; i < TxtWordArray.length - 2; i++) {
					String key = TxtWordArray[i] + "#" + TxtWordArray[i + 2];
					if (map.containsKey(key)) {
						map.get(key).add(TxtWordArray[i + 1]);
					} else {
						List<String> valueList = new ArrayList<String>();
						valueList.add(TxtWordArray[i + 1]);
						map.put(key, valueList);
					}
				}
				String word1 = f3_w1.getText();
				String word2 = f3_w2.getText();
				if (wordList.contains(word1) && wordList.contains(word2)) {
					String key = word1 + "#" + word2;
					if (!(wordList.contains(word1) && wordList.contains(word2))) {
						f3_out.setText("No word1 or word2 in the graph!");
					} else if (!map.containsKey(key)) {
						f3_out.setText("No bridge words from word1 to word2!");
					} else {
						StringBuffer result = new StringBuffer("The bridge from " + word1 + " to " + word2 + "  are: ");
						for (String bridge : map.get(key)) {
							result.append(bridge + " ");
						}
						f3_out.setText(result.toString());
					}
				} else {
					f3_out.setText("input error");
				}
			}
		});

		f4_create.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				f4_out.setText("");
				int i;
				String inputText = f4_in.getText();
				String[] TextWord = inputText.toLowerCase().trim().split("\\s+");
				StringBuffer newText = new StringBuffer();
				newText.append(TextWord[0] + " ");
				f4_out.append("New text created is ：\n");
				for (i = 0; i < TextWord.length - 1; i++) {
					String key = TextWord[i] + "#" + TextWord[i + 1];
					if (map.containsKey(key)) {
						String bridge = map.get(key).get(0);
						newText.append(bridge + " " + TextWord[i + 1] + " ");
					} else {
						newText.append(TextWord[i + 1] + " ");
					}
				}
				f4_out.append(newText.toString());
				// 生成新文本 get文本框的string set到结果框
			}
		});

		f5_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Floyd();
				String word1 = f5_w1.getText();
				String word2 = f5_w2.getText();
				f5_out.setText("");

				if (wordList.contains(word1) && wordList.contains(word2)) {
					f5_out.append("The path " + word1 + " to " + word2 + " is :\n");
					int start = wordList.indexOf(word1);
					int end = wordList.indexOf(word2);
					if (D[start][end] != INFINITY) {
						pathWay.append(word1 + " -> ");
						getPath(start, end);
						pathWay.append(word2);
						String py = pathWay.toString();
						pathWay.delete(0, pathWay.length());
						f5_out.append(py);
					} else {
						f5_out.append("no access");
					}
				} else {
					f5_out.append("input error");
				} // 查询两个单词最短路径 get word1 word2文本框的内容 set到输出款
			}
		});

		f5_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String word = f5_w.getText();
				f5_out.setText("");

				if (wordList.contains(word)) {
					f5_out.append("The path " + word + " to others is :\n");
					// f5_out.append("The path " + word1 + " to " + word2 + " is :");

					int s = wordList.indexOf(word);
					int i;
					for (i = 0; i < wordNum; i++) {
						pathWay.delete(0, pathWay.length());
						if (i != s) {
							String word1 = wordList.get(s);
							String word2 = wordList.get(i);
							f5_out.append((calcShortestPath(word1, word2)));
						}
						f5_out.append("\n");
					}

				} else {
					f5_out.setText("input error");
				} // 查询两个单词最短路径 get word1 word2文本框的内容 set到输出款

				// 查询最短路径
			}
		});

		f6_begin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int ranNum = (int) Math.round(Math.random() * (wordNum - 1));
				String ranWord = wordList.get(ranNum);
				randomPath.append(ranWord);
				f6_choose.setText(ranWord);
				// System.out.println("System choose -> " + ranWord + "\nRandom walk is ：");
				walkFrom(ranNum);
				String rp = randomPath.toString();
				randomPath.delete(0, randomPath.length());
				f6_out.setText(rp);
				// return rp;

				// 随机游走开始 把系统选择的 setText 结果输出到文本框
			}
		});
	} // 构造器

	protected static void getPath(int start, int end) {
		if (path[start][end] == -1) {
			return;
		} else {
			getPath(start, path[start][end]);
			pathWay.append(wordList.get(path[start][end]) + " -> ");
		}
	}

	protected static void Floyd() {
		int i, j;
		D = new int[wordNum][wordNum];
		path = new int[wordNum][wordNum];
		for (i = 0; i < wordNum; i++) {
			for (j = 0; j < wordNum; j++) {
				D[i][j] = E[i][j];
				path[i][j] = -1;
			}
		}
		for (int k = 0; k < wordNum; k++) {
			for (i = 0; i < wordNum; i++) {
				for (j = 0; j < wordNum; j++) {
					if (D[i][k] + D[k][j] < D[i][j]) {
						D[i][j] = D[i][k] + D[k][j];
						path[i][j] = k;
					}
				}
			}
		}
	}

	protected static String calcShortestPath(String word1, String word2) {
		if (wordList.contains(word1) && wordList.contains(word2)) {

			int start = wordList.indexOf(word1);
			int end = wordList.indexOf(word2);
			if (D[start][end] != INFINITY) {
				pathWay.append(word1 + " -> ");
				getPath(start, end);
				pathWay.append(word2);
				String py = pathWay.toString();
				pathWay.delete(0, pathWay.length());
				return py;
			} else {
				return "no access";
			}
		} else {
			return "input error";
		}
	}

	protected static boolean isEnd(int s) {
		for (int i = 0; i < wordNum; i++) {
			String edgePair = String.valueOf(s) + "#" + String.valueOf(i);
			if (E[s][i] != INFINITY && !edgePairList.contains(edgePair)) { // 如果存在边
				return false;
			}
		}
		return true; // 不存在边，即是尽头
	}

	protected static void walkFrom(int s) {
		for (int i = 0; i < wordNum; i++) {
			if (flag) {
				String edgePair = String.valueOf(s) + "#" + String.valueOf(i);
				if (E[s][i] != INFINITY && !edgePairList.contains(edgePair)) {
					edgePairList.add(edgePair);
					randomPath.append(" -> " + wordList.get(i));
					walkFrom(i);
				} else if (isEnd(s)) {
					flag = false;
					return;
				}
			}
		}
	}

	protected static void drawGraph(Graph graph) {
		boolean bool = false;
		// eclipse-workspace/test/
		filenameTemp = "/Users/summerchaser/" + "graph" + ".txt";
		File graph_file = new File(filenameTemp);
		try {
			if (!graph_file.exists()) {
				graph_file.createNewFile();
				bool = true;
				System.out.println("succeed!");
				FileWriter fileWritter = new FileWriter(graph_file.getName(), true);
				BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
				bufferWritter.write("digraph G{ \r\n");
				bufferWritter.close();
				System.out.println("Done");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < graph.wordNum; i++) {
			for (int j = 0; j < graph.wordNum; j++) {
				if (E[i][j] != INFINITY) {
					String word1 = graph.wordList.get(i);
					String word2 = graph.wordList.get(j);
					String filewrite = word1 + " -> " + word2 + " [label=" + E[i][j] + "];\r\n";
					try {
						FileWriter fileWritter = new FileWriter(graph_file.getName(), true);
						BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
						bufferWritter.write(filewrite);
						bufferWritter.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		try {
			FileWriter fileWritter = new FileWriter(graph_file.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write("}");
			bufferWritter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String command = "dot -Tpng graph.txt -o graph.png ";
			// String command= "cmd /c start cmd.exe /K dir";
			// Runtime.getRuntime().exec("cd /Users/summerchaser/eclipse-workspace/test");
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
