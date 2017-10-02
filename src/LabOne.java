import java.awt.Container;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LabOne{
	
	static JFrame frame = new JFrame("Lab1");
	static JTabbedPane tabPane = new JTabbedPane();//选项卡布局
	static Container container = new Container();
	static JLabel label1 = new JLabel("文件目录");
	static JLabel label2 = new JLabel("选择文件");
	static JTextField text1 = new JTextField();
	static JTextField text2 = new JTextField();
	static JButton button1 = new JButton("...");
	static JButton button2 = new JButton("...");
	static JButton buttonSel = new JButton("确认选择");
	static JFileChooser jfc = new JFileChooser();
	static JButton button3 = new JButton("展示有向图");
	static JButton button4 = new JButton("查询巧桥接词");
	static JButton button5 = new JButton("根据bridge word生成新文本");
	static JButton button6 = new JButton("计算两个单词之间的最短路径");
	static JButton button7 = new JButton("随机游走");
	
	static File f = null;
	
	static String text;
	public static String DotStr;
	public static StringManager sm;
	
	public static void main(String[] args) {
		

		init();
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jfc.setFileSelectionMode(1);
				int state = jfc.showOpenDialog(null);
				if(state == 1) {
					return;
				}else {
					f = jfc.getSelectedFile();
					text1.setText(f.getAbsolutePath());
				}
			}
		});
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jfc.setFileSelectionMode(0);
				int state = jfc.showOpenDialog(null);
				if(state==1) {
					return;
				}else {
					f = jfc.getSelectedFile();
					text2.setText(f.getAbsolutePath());
				}
				
			}
		});
		
		buttonSel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				FileManager.deleteFile("dotsource.dot");
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "成功","提示",2);
				f = jfc.getSelectedFile();
				if(f!=null) {
					sm = new StringManager();
					text = FileManager.ReadFile(f);
					text = sm.StringFormat(text);
					DotStr = sm.CreateDotFormat(text);
					System.out.println(text);
					System.out.println(DotStr);
					
				}
			}
			
		});
		button3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GraphManager.showDirectedGraph(DotStr, "DotGraph");
				try {
					GraphManager.showImage(f.getParent()+"\\DotGraph.jpg");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
		});
		
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFrame frameQuery = new JFrame();
				
				
				JTabbedPane paneQuery = new JTabbedPane();
				frameQuery.setContentPane(paneQuery);
				Container conQuery = new Container();
				
				JLabel labelWord1 = new JLabel("单词1");
				JLabel labelWord2 = new JLabel("单词2");
				JTextField word1 = new JTextField();
				JTextField word2 = new JTextField();
				final JTextField queryResult = new JTextField();
				JButton buttonConfirm = new JButton("确定");
				
				
				labelWord1.setBounds(10,10,70,20);
				labelWord2.setBounds(10,40,70,20);
				word1.setBounds(70,10,150,20);
				word2.setBounds(70,40,150,20);
				queryResult.setBounds(60,80,150,20);
				buttonConfirm.setBounds(60, 110, 50, 20);
				
				conQuery.add(labelWord1);
				conQuery.add(labelWord2);
				conQuery.add(word1);
				conQuery.add(word2);
				conQuery.add(queryResult);
				conQuery.add(buttonConfirm);
				
				
				paneQuery.add("查询桥接词",conQuery);
				
				frameQuery.setVisible(true);
				frameQuery.setSize(300, 400);
				
				buttonConfirm.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String wd1 = word1.getText().toString();
						String wd2 = word2.getText().toString();
						//System.out.println(wd1+" "+wd2);
						String result = sm.queryBridgeWords(wd1, wd2);
						if(result==null) queryResult.setText("没有桥接词");
						queryResult.setText(result);
					}
					
				});
				
			}
			
		});
		
		button5.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				
						
				JFrame frameGenerate = new JFrame("生成新文本");
				JTabbedPane paneGenerate = new JTabbedPane();
				Container container = new Container();
				JTextArea inputText = new JTextArea(8,20);
				JTextArea outputText = new JTextArea(8,20);
				
				frameGenerate.setVisible(true);
				frameGenerate.setSize(300, 400);
				frameGenerate.setContentPane(paneGenerate);
				
				JButton button1 = new JButton("确定");
				
				JLabel label1 = new JLabel();
				JLabel label2 = new JLabel();
				JScrollPane scrollPane1 = new JScrollPane(inputText);
				JScrollPane scrollPane2 = new JScrollPane(outputText);

				inputText.setLineWrap(true);
				outputText.setLineWrap(true);
				inputText.setWrapStyleWord(true);
				outputText.setWrapStyleWord(true);
				double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
				double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
				frame.setLocation(new Point((int)(lx/2)-150, (int)(ly/2)-150));
				//container.add(inputText);
				//container.add(outputText);
				
				label1.setBounds(10,10,80,20);
				label1.setText("输入文本");
				label2.setBounds(10, 150, 80, 20);
				label2.setText("输出文本");
				inputText.setBounds(80, 10, 180, 170);
				outputText.setBounds(80, 150, 180, 170);
				scrollPane1.setBounds(80, 10, 180, 100);
				scrollPane2.setBounds(80, 150, 180, 100);
				button1.setBounds(80, 300, 80, 20);
				
				container.add(button1);
				container.add(label1);
				container.add(label2);
				container.add(scrollPane1);
				container.add(scrollPane2);
				
				paneGenerate.add("输出新文本", container);
				
				button1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String input = inputText.getText().toString();
						System.out.println(input);
						String output = sm.generateNewText(input);
						outputText.setText(output);								
					}							
				});

				
			}
			
		});
		
		button6.addActionListener(new ActionListener() {

			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFrame frameQuery = new JFrame();
				
				
				JTabbedPane paneQuery = new JTabbedPane();
				frameQuery.setContentPane(paneQuery);
				Container conQuery = new Container();
				
				JLabel labelWord1 = new JLabel("单词1");
				JLabel labelWord2 = new JLabel("单词2");
				JTextField word1 = new JTextField();
				JTextField word2 = new JTextField();
				final JTextField queryResult = new JTextField();
				JButton buttonConfirm = new JButton("确定");
				
				
				labelWord1.setBounds(10,10,70,20);
				labelWord2.setBounds(10,40,70,20);
				word1.setBounds(70,10,150,20);
				word2.setBounds(70,40,150,20);
				queryResult.setBounds(60,80,150,20);
				buttonConfirm.setBounds(60, 110, 50, 20);
				
				conQuery.add(labelWord1);
				conQuery.add(labelWord2);
				conQuery.add(word1);
				conQuery.add(word2);
				conQuery.add(queryResult);
				conQuery.add(buttonConfirm);
				
				
				paneQuery.add("计算两单词最短路径",conQuery);
				
				frameQuery.setVisible(true);
				frameQuery.setSize(300, 400);
				
				buttonConfirm.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String w1 =  word1.getText();
						String w2 = word2.getText();
						
						
						String path = sm.calcShortestPath(w1, w2);
						queryResult.setText(path);
						//System.out.println("最短路径是："+path);
						//System.out.println("graphstr 是："+StringManager.graphstr);
						
						GraphManager.showDirectedGraph(StringManager.graphstr, "newDotGraph");
						try {
							GraphManager.showImage(f.getParent()+"\\newDotGraph.jpg");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					
						
					}
					
				});
				
			}
			
		});

		button7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frameQuery = new JFrame();
				JTabbedPane paneQuery = new JTabbedPane();
				frameQuery.setContentPane(paneQuery);
				Container conResult = new Container();
				paneQuery.add("随机游走", conResult);
				
				JTextArea textArea = new JTextArea(8,20);
				textArea.setWrapStyleWord(true);
				textArea.setLineWrap(true);
				textArea.setBounds(80, 10, 180, 170);
				JScrollPane sp = new JScrollPane(textArea);
				conResult.add(sp);
				conResult.add(textArea);
				frameQuery.setContentPane(paneQuery);
				frameQuery.setVisible(true);
				frameQuery.setSize(300, 400);
				
				
				String result = sm.randomWalk();
				//System.out.println("随机游走的结果是："+result);
				textArea.setText(result);
				try {
					sm.TextToFile(result);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
	}
	
	
	private static void init() {

		
		jfc.setCurrentDirectory(new File("d://"));
		double lx = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double ly = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		frame.setLocation(new Point((int)(lx/2)-150, (int)(ly/2)-150));
		frame.setSize(400,500);
		frame.setContentPane(tabPane);
		
		label1.setBounds(10,10,70,20);
		text1.setBounds(75, 10, 120, 20);
		button1.setBounds(210, 10, 50, 20);
		label2.setBounds(10, 35, 70, 20);
		text2.setBounds(75, 10, 120, 20);
		button2.setBounds(210, 35, 50, 20);
		
		buttonSel.setBounds(30,60,180,20);
		button3.setBounds(30,90,180,20);
		button4.setBounds(30,120,180,20);
		button5.setBounds(30,150,180,20);
		button6.setBounds(30,180,180,20);
		button7.setBounds(30, 210, 180, 20);

		
		container.add(label1);
		container.add(text1);
		container.add(button1);
		container.add(label2);
		container.add(text2);
		container.add(button2);
		container.add(buttonSel);
		container.add(button3);
		container.add(button4);
		container.add(button5);
		container.add(button6);
		container.add(button7);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabPane.add("面板", container);
	}
}
