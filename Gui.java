package assignment3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Gui {

	private static void PricerGUI() {
		final LineBorder blackline = new LineBorder(Color.BLACK, 1);
		final JFrame frame = new JFrame("PRICER");
		frame.setSize(700, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JMenuBar menuBar = new JMenuBar();
		JMenu functions = new JMenu("Functions");
		JMenu about = new JMenu("About...");
		
		JMenuItem item1 = new JMenuItem("European Option");
		JMenuItem item2 = new JMenuItem("Volatility");
		JMenuItem item3 = new JMenuItem("Amerian Option");
		JMenuItem item4 = new JMenuItem("Geometric Asian Option");
		JMenuItem item5 = new JMenuItem("Arithmetic Asian Option");
		JMenuItem item6 = new JMenuItem("Geometric Basket Option");
		JMenuItem item7 = new JMenuItem("Arithmetic Basket Option");
		JMenuItem item8 = new JMenuItem("About");
		JMenuItem item9 = new JMenuItem("Help");
		
		functions.add(item1);
		functions.add(item2);
		functions.add(item3);
		functions.add(item4);
		functions.add(item5);
		functions.add(item6);
		functions.add(item7);
		about.add(item8);
		about.add(item9);
		
		menuBar.add(functions);
		menuBar.add(about);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(700, 450));
		JLabel label = new JLabel("Welcome to Pricer!");
		panel.add(label);
		
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(700, 450));
				
				JPanel panel1 = new JPanel();
				panel1.setPreferredSize(new Dimension(620, 250));
				panel1.setBorder(new TitledBorder(blackline, "European Option"));
				
				JLabel label1 = new JLabel("The spot price of asset");
				JLabel label2 = new JLabel("The volatility");
				JLabel label3 = new JLabel("Risk-free interest rate");
				JLabel label4 = new JLabel("Time to maturity");
				JLabel label5 = new JLabel("Strike price");
				final JTextField textField1 = new JTextField();
				final JTextField textField2 = new JTextField();
				final JTextField textField3 = new JTextField();
				final JTextField textField4 = new JTextField();
				final JTextField textField5 = new JTextField();
				
				final JRadioButton radioButton1 = new JRadioButton("Call",true);
				final JRadioButton radioButton2 = new JRadioButton("Put");
				ButtonGroup group = new ButtonGroup();
				group.add(radioButton1);
				group.add(radioButton2);
				JButton button = new JButton("Calculate");
				
				panel1.setLayout(new GridLayout(5, 3));
				panel1.add(label1);
				panel1.add(textField1);
				panel1.add(new JPanel());
				panel1.add(label2);
				panel1.add(textField2);
				panel1.add(new JPanel());
				panel1.add(label3);
				panel1.add(textField3);
				panel1.add(radioButton1);
				panel1.add(label4);
				panel1.add(textField4);
				panel1.add(radioButton2);
				panel1.add(label5);
				panel1.add(textField5);
				panel1.add(button);
				
				JPanel panel2 = new JPanel();
				panel2.setPreferredSize(new Dimension(620, 80));
				panel2.setBorder(new TitledBorder(blackline, "Result"));
				
				final JTextArea textArea = new JTextArea();
				textArea.setPreferredSize(new Dimension(550, 40));
				panel2.add(textArea);
				
				panel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));
				panel.add(panel1);	
				panel.add(panel2);
				
				button.addActionListener(new ActionListener() {		
					public void actionPerformed(ActionEvent e) {
						double assetprice = Double.valueOf(textField1.getText());
						double volatility = Double.valueOf(textField2.getText());
						double interest = Double.valueOf(textField3.getText());
						double time = Double.valueOf(textField4.getText());
						double strike = Double.valueOf(textField5.getText());
						Blackscholes blackscholes = new Blackscholes(assetprice, volatility, interest, time, strike);
						if(radioButton1.isSelected()){
							textArea.setText("Call option value:" + String.valueOf(blackscholes.calloption()));
						}
						if(radioButton2.isSelected()){
							textArea.setText("Put option value:" + String.valueOf(blackscholes.putoption()));
						}
					}
				});
				
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		});
		
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(700, 450));
				
				JPanel panel1 = new JPanel();
				panel1.setPreferredSize(new Dimension(620, 250));
				panel1.setBorder(new TitledBorder(blackline, "Volatility"));
				
				JLabel label1 = new JLabel("The spot price of asset");
				JLabel label2 = new JLabel("Risk-free interest rate");
				JLabel label3 = new JLabel("Repo rate");
				JLabel label4 = new JLabel("Time to maturity");
				JLabel label5 = new JLabel("Strike price");
				JLabel label6 = new JLabel("Option premium");
				final JTextField textField1 = new JTextField();
				final JTextField textField2 = new JTextField();
				final JTextField textField3 = new JTextField();
				final JTextField textField4 = new JTextField();
				final JTextField textField5 = new JTextField();
				final JTextField textField6 = new JTextField();
				
				final JRadioButton radioButton1 = new JRadioButton("Call",true);
				final JRadioButton radioButton2 = new JRadioButton("Put");
				ButtonGroup group = new ButtonGroup();
				group.add(radioButton1);
				group.add(radioButton2);
				JButton button = new JButton("Calculate");
				
				panel1.setLayout(new GridLayout(6, 3));
				panel1.add(label1);
				panel1.add(textField1);
				panel1.add(new JPanel());
				panel1.add(label2);
				panel1.add(textField2);
				panel1.add(new JPanel());
				panel1.add(label3);
				panel1.add(textField3);
				panel1.add(new JPanel());
				panel1.add(label4);
				panel1.add(textField4);
				panel1.add(radioButton1);
				panel1.add(label5);
				panel1.add(textField5);
				panel1.add(radioButton2);
				panel1.add(label6);
				panel1.add(textField6);
				panel1.add(button);
				
				JPanel panel2 = new JPanel();
				panel2.setPreferredSize(new Dimension(620, 80));
				panel2.setBorder(new TitledBorder(blackline, "Result"));
				
				final JTextArea textArea = new JTextArea();
				textArea.setPreferredSize(new Dimension(550, 40));
				panel2.add(textArea);
				
				panel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));
				panel.add(panel1);	
				panel.add(panel2);
				
				button.addActionListener(new ActionListener() {		
					public void actionPerformed(ActionEvent e) {
						double assetprice = Double.valueOf(textField1.getText());
						double interest = Double.valueOf(textField2.getText());
						double repo = Double.valueOf(textField3.getText());
						double time = Double.valueOf(textField4.getText());
						double strike = Double.valueOf(textField5.getText());
						double premium = Double.valueOf(textField6.getText());
						Volatility volatility = new Volatility(assetprice, interest, repo, time, strike, premium);
						if(radioButton1.isSelected()){
							textArea.setText("Call option volatility:" + String.valueOf(volatility.calloption()));
						}
						if(radioButton2.isSelected()){
							textArea.setText("Put option volatility:" + String.valueOf(volatility.putoption()));
						}
					}
				});
				
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		});
		
		item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(700, 450));
				
				JPanel panel1 = new JPanel();
				panel1.setPreferredSize(new Dimension(620, 250));
				panel1.setBorder(new TitledBorder(blackline, "Amerian Option"));
				
				JLabel label1 = new JLabel("The spot price of asset");
				JLabel label2 = new JLabel("The volatility");
				JLabel label3 = new JLabel("Risk-free interest rate");
				JLabel label4 = new JLabel("Time to maturity");
				JLabel label5 = new JLabel("Strike price");
				JLabel label6 = new JLabel("The number of steps");
				final JTextField textField1 = new JTextField();
				final JTextField textField2 = new JTextField();
				final JTextField textField3 = new JTextField();
				final JTextField textField4 = new JTextField();
				final JTextField textField5 = new JTextField();
				final JTextField textField6 = new JTextField();
				
				final JRadioButton radioButton1 = new JRadioButton("Call",true);
				final JRadioButton radioButton2 = new JRadioButton("Put");
				ButtonGroup group = new ButtonGroup();
				group.add(radioButton1);
				group.add(radioButton2);
				JButton button = new JButton("Calculate");
				
				panel1.setLayout(new GridLayout(6, 3));
				panel1.add(label1);
				panel1.add(textField1);
				panel1.add(new JPanel());
				panel1.add(label2);
				panel1.add(textField2);
				panel1.add(new JPanel());
				panel1.add(label3);
				panel1.add(textField3);
				panel1.add(new JPanel());
				panel1.add(label4);
				panel1.add(textField4);
				panel1.add(radioButton1);
				panel1.add(label5);
				panel1.add(textField5);
				panel1.add(radioButton2);
				panel1.add(label6);
				panel1.add(textField6);
				panel1.add(button);
				
				JPanel panel2 = new JPanel();
				panel2.setPreferredSize(new Dimension(620, 80));
				panel2.setBorder(new TitledBorder(blackline, "Result"));
				
				final JTextArea textArea = new JTextArea();
				textArea.setPreferredSize(new Dimension(550, 40));
				panel2.add(textArea);
				
				panel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));
				panel.add(panel1);	
				panel.add(panel2);
				
				button.addActionListener(new ActionListener() {		
					public void actionPerformed(ActionEvent e) {
						double assetprice = Double.valueOf(textField1.getText());
						double volatility = Double.valueOf(textField2.getText());
						double interest = Double.valueOf(textField3.getText());
						double time = Double.valueOf(textField4.getText());
						double strike = Double.valueOf(textField5.getText());
						int steps = Integer.valueOf(textField6.getText());
						BinomialTree binomialTree = new BinomialTree(assetprice, volatility, interest, time, strike, steps);
						BinomialTree binomialTree2 = new BinomialTree(assetprice, volatility, interest, time, strike, steps + 1);
						if(radioButton1.isSelected()){
							textArea.setText("Call option value:" + String.valueOf((binomialTree.americanCall() + binomialTree2.americanCall()) / 2));
						}
						if(radioButton2.isSelected()){
							textArea.setText("Put option value:" + String.valueOf((binomialTree.ameticanPut() + binomialTree2.ameticanPut()) / 2));
						}
					}
				});
				
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		});
		
		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(700, 450));
				
				JPanel panel1 = new JPanel();
				panel1.setPreferredSize(new Dimension(620, 250));
				panel1.setBorder(new TitledBorder(blackline, "Geometric Asian Option"));
				
				JLabel label1 = new JLabel("The spot price of asset");
				JLabel label2 = new JLabel("The volatility");
				JLabel label3 = new JLabel("Risk-free interest rate");
				JLabel label4 = new JLabel("Time to maturity");
				JLabel label5 = new JLabel("Strike price");
				JLabel label6 = new JLabel("Observation times");
				final JTextField textField1 = new JTextField();
				final JTextField textField2 = new JTextField();
				final JTextField textField3 = new JTextField();
				final JTextField textField4 = new JTextField();
				final JTextField textField5 = new JTextField();
				final JTextField textField6 = new JTextField();
				
				final JRadioButton radioButton1 = new JRadioButton("Call",true);
				final JRadioButton radioButton2 = new JRadioButton("Put");
				ButtonGroup group = new ButtonGroup();
				group.add(radioButton1);
				group.add(radioButton2);
				JButton button = new JButton("Calculate");
				
				panel1.setLayout(new GridLayout(6, 3));
				panel1.add(label1);
				panel1.add(textField1);
				panel1.add(new JPanel());
				panel1.add(label2);
				panel1.add(textField2);
				panel1.add(new JPanel());
				panel1.add(label3);
				panel1.add(textField3);
				panel1.add(new JPanel());
				panel1.add(label4);
				panel1.add(textField4);
				panel1.add(radioButton1);
				panel1.add(label5);
				panel1.add(textField5);
				panel1.add(radioButton2);
				panel1.add(label6);
				panel1.add(textField6);
				panel1.add(button);
				
				JPanel panel2 = new JPanel();
				panel2.setPreferredSize(new Dimension(620, 80));
				panel2.setBorder(new TitledBorder(blackline, "Result"));
				
				final JTextArea textArea = new JTextArea();
				textArea.setPreferredSize(new Dimension(550, 40));
				panel2.add(textArea);
				
				panel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));
				panel.add(panel1);	
				panel.add(panel2);
				
				button.addActionListener(new ActionListener() {		
					public void actionPerformed(ActionEvent e) {
						double assetprice = Double.valueOf(textField1.getText());
						double volatility = Double.valueOf(textField2.getText());
						double interest = Double.valueOf(textField3.getText());
						double time = Double.valueOf(textField4.getText());
						double strike = Double.valueOf(textField5.getText());
						int number = Integer.valueOf(textField6.getText());
						GeometricAsian geometricAsian = new GeometricAsian(assetprice, volatility, interest, time, strike, number);
						if(radioButton1.isSelected()){
							
							textArea.setText("Call option value:" + String.valueOf(geometricAsian.calloption()));
						}
						if(radioButton2.isSelected()){
							textArea.setText("Put option value:" + String.valueOf(geometricAsian.putoption()));
						}
					}
				});
				
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		});
		
		item5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(700, 450));
				
				JPanel panel1 = new JPanel();
				panel1.setPreferredSize(new Dimension(620, 250));
				panel1.setBorder(new TitledBorder(blackline, "Arithmetic Asian Option"));
				
				JLabel label1 = new JLabel("The spot price of asset");
				JLabel label2 = new JLabel("The volatility");
				JLabel label3 = new JLabel("Risk-free interest rate");
				JLabel label4 = new JLabel("Time to maturity");
				JLabel label5 = new JLabel("Strike price");
				JLabel label6 = new JLabel("Observation times");
				JLabel label7 = new JLabel("Nuber of paths");
				final JTextField textField1 = new JTextField();
				final JTextField textField2 = new JTextField();
				final JTextField textField3 = new JTextField();
				final JTextField textField4 = new JTextField();
				final JTextField textField5 = new JTextField();
				final JTextField textField6 = new JTextField();
				final JTextField textField7 = new JTextField();
				
				
				final JRadioButton radioButton1 = new JRadioButton("Call",true);
				final JRadioButton radioButton2 = new JRadioButton("Put");
				ButtonGroup group1 = new ButtonGroup();
				group1.add(radioButton1);
				group1.add(radioButton2);
				final JRadioButton radioButton3 = new JRadioButton("Geometric Asian option",true);
				final JRadioButton radioButton4 = new JRadioButton("No control variate");
				ButtonGroup group2 = new ButtonGroup();
				group2.add(radioButton3);
				group2.add(radioButton4);
				JButton button = new JButton("Calculate");
				
				panel1.setLayout(new GridLayout(7, 3));
				panel1.add(label1);
				panel1.add(textField1);
				panel1.add(new JPanel());
				panel1.add(label2);
				panel1.add(textField2);
				panel1.add(new JPanel());
				panel1.add(label3);
				panel1.add(textField3);
				panel1.add(radioButton1);
				panel1.add(label4);
				panel1.add(textField4);
				panel1.add(radioButton2);
				panel1.add(label5);
				panel1.add(textField5);
				panel1.add(radioButton3);
				panel1.add(label6);
				panel1.add(textField6);
				panel1.add(radioButton4);
				panel1.add(label7);
				panel1.add(textField7);
				panel1.add(button);
				
				JPanel panel2 = new JPanel();
				panel2.setPreferredSize(new Dimension(620, 80));
				panel2.setBorder(new TitledBorder(blackline, "Result"));
				
				final JTextArea textArea = new JTextArea();
				textArea.setPreferredSize(new Dimension(550, 40));
				panel2.add(textArea);
				
				panel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));
				panel.add(panel1);	
				panel.add(panel2);
				
				button.addActionListener(new ActionListener() {		
					public void actionPerformed(ActionEvent e) {
						double assetprice = Double.valueOf(textField1.getText());
						double volatility = Double.valueOf(textField2.getText());
						double interest = Double.valueOf(textField3.getText());
						double time = Double.valueOf(textField4.getText());
						double strike = Double.valueOf(textField5.getText());
						int number1 = Integer.valueOf(textField6.getText());
						int number2 = Integer.valueOf(textField7.getText());
						ArithmeticAsian arithmeticAsian = null;
						if(radioButton3.isSelected()){
							arithmeticAsian = new ArithmeticAsian(assetprice, volatility, interest, time, strike, number1, number2, true);
						}
						if(radioButton4.isSelected()){
							arithmeticAsian = new ArithmeticAsian(assetprice, volatility, interest, time, strike, number1, number2, false);
						}
						if(radioButton1.isSelected()){
							double[] interval = arithmeticAsian.calloption();
							textArea.setText("Call option value confidence interval:\n[" + String.valueOf(interval[0]) + "," + String.valueOf(interval[1]) + "]");
						}
						if(radioButton2.isSelected()){
							double[] interval = arithmeticAsian.putoption();
							textArea.setText("Put option value confidence interval:\n[" + String.valueOf(interval[0]) + "," + String.valueOf(interval[1]) + "]");
						}
					}
				});
				
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		});
		
		item6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(700, 450));
				
				JPanel panel1 = new JPanel();
				panel1.setPreferredSize(new Dimension(620, 250));
				panel1.setBorder(new TitledBorder(blackline, "Geometric Basket Option"));
				
				JLabel label1 = new JLabel("The spot price of asset1");
				JLabel label2 = new JLabel("The volatility1");
				JLabel label3 = new JLabel("The spot price of asset2");
				JLabel label4 = new JLabel("The volatility2");
				JLabel label5 = new JLabel("Risk-free interest rate");
				JLabel label6 = new JLabel("Time to maturity");
				JLabel label7 = new JLabel("Strike price");
				JLabel label8 = new JLabel("Correlation");
				final JTextField textField1 = new JTextField();
				final JTextField textField2 = new JTextField();
				final JTextField textField3 = new JTextField();
				final JTextField textField4 = new JTextField();
				final JTextField textField5 = new JTextField();
				final JTextField textField6 = new JTextField();
				final JTextField textField7 = new JTextField();
				final JTextField textField8 = new JTextField();
				
				final JRadioButton radioButton1 = new JRadioButton("Call",true);
				final JRadioButton radioButton2 = new JRadioButton("Put");
				ButtonGroup group = new ButtonGroup();
				group.add(radioButton1);
				group.add(radioButton2);
				JButton button = new JButton("Calculate");
				
				panel1.setLayout(new GridLayout(6, 4));
				panel1.add(label1);
				panel1.add(textField1);
				panel1.add(label2);
				panel1.add(textField2);
				panel1.add(label3);
				panel1.add(textField3);
				panel1.add(label4);
				panel1.add(textField4);
				panel1.add(label5);
				panel1.add(textField5);
				panel1.add(new JPanel());
				panel1.add(new JPanel());
				panel1.add(label6);
				panel1.add(textField6);
				panel1.add(new JPanel());
				panel1.add(new JPanel());
				panel1.add(label7);
				panel1.add(textField7);
				panel1.add(radioButton1);
				panel1.add(radioButton2);
				panel1.add(label8);
				panel1.add(textField8);
				panel1.add(new JPanel());
				panel1.add(button);
				
				JPanel panel2 = new JPanel();
				panel2.setPreferredSize(new Dimension(620, 80));
				panel2.setBorder(new TitledBorder(blackline, "Result"));
				
				final JTextArea textArea = new JTextArea();
				textArea.setPreferredSize(new Dimension(550, 40));
				panel2.add(textArea);
				
				panel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));
				panel.add(panel1);	
				panel.add(panel2);
				
				button.addActionListener(new ActionListener() {		
					public void actionPerformed(ActionEvent e) {
						double assetprice1 = Double.valueOf(textField1.getText());
						double volatility1 = Double.valueOf(textField2.getText());
						double assetprice2 = Double.valueOf(textField3.getText());
						double volatility2 = Double.valueOf(textField4.getText());
						double interest = Double.valueOf(textField5.getText());
						double time = Double.valueOf(textField6.getText());
						double strike = Double.valueOf(textField7.getText());
						double correlation = Double.valueOf(textField8.getText());
						GeometricBasket geometricBasket = new GeometricBasket(assetprice1, assetprice2, 
								volatility1, volatility2, interest, time, strike, correlation);
						if(radioButton1.isSelected()){
							textArea.setText("Call option value:" + String.valueOf(geometricBasket.calloption()));
						}
						if(radioButton2.isSelected()){
							textArea.setText("Put option value:" + String.valueOf(geometricBasket.putoption()));
						}
					}
				});
				
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		});
		
		item7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(700, 450));
				
				JPanel panel1 = new JPanel();
				panel1.setPreferredSize(new Dimension(620, 250));
				panel1.setBorder(new TitledBorder(blackline, "Arithmetic Basket Option"));
				
				JLabel label1 = new JLabel("The spot price of asset1");
				JLabel label2 = new JLabel("The volatility1");
				JLabel label3 = new JLabel("The spot price of asset2");
				JLabel label4 = new JLabel("The volatility2");
				JLabel label5 = new JLabel("Risk-free interest rate");
				JLabel label6 = new JLabel("Time to maturity");
				JLabel label7 = new JLabel("Strike price");
				JLabel label8 = new JLabel("Correlation");
				JLabel label9 = new JLabel("Nuber of paths");
				final JTextField textField1 = new JTextField();
				final JTextField textField2 = new JTextField();
				final JTextField textField3 = new JTextField();
				final JTextField textField4 = new JTextField();
				final JTextField textField5 = new JTextField();
				final JTextField textField6 = new JTextField();
				final JTextField textField7 = new JTextField();
				final JTextField textField8 = new JTextField();
				final JTextField textField9 = new JTextField();
				
				final JRadioButton radioButton1 = new JRadioButton("Call",true);
				final JRadioButton radioButton2 = new JRadioButton("Put");
				ButtonGroup group1 = new ButtonGroup();
				group1.add(radioButton1);
				group1.add(radioButton2);
				final JRadioButton radioButton3 = new JRadioButton("Geometric basket option",true);
				final JRadioButton radioButton4 = new JRadioButton("No control variate");
				ButtonGroup group2 = new ButtonGroup();
				group2.add(radioButton3);
				group2.add(radioButton4);
				JButton button = new JButton("Calculate");
				
				panel1.setLayout(new GridLayout(6, 4));
				panel1.add(label1);
				panel1.add(textField1);
				panel1.add(label2);
				panel1.add(textField2);
				panel1.add(label3);
				panel1.add(textField3);
				panel1.add(label4);
				panel1.add(textField4);
				panel1.add(label5);
				panel1.add(textField5);
				panel1.add(label6);
				panel1.add(textField6);
				panel1.add(label7);
				panel1.add(textField7);
				panel1.add(radioButton1);
				panel1.add(radioButton2);
				panel1.add(label8);
				panel1.add(textField8);
				panel1.add(radioButton3);
				panel1.add(radioButton4);
				panel1.add(label9);
				panel1.add(textField9);
				panel1.add(new JPanel());
				panel1.add(button);
				
				JPanel panel2 = new JPanel();
				panel2.setPreferredSize(new Dimension(620, 80));
				panel2.setBorder(new TitledBorder(blackline, "Result"));
				
				final JTextArea textArea = new JTextArea();
				textArea.setPreferredSize(new Dimension(550, 40));
				panel2.add(textArea);
				
				panel.setLayout(new FlowLayout(FlowLayout.LEFT, 40, 20));
				panel.add(panel1);	
				panel.add(panel2);
				
				button.addActionListener(new ActionListener() {		
					public void actionPerformed(ActionEvent e) {
						double assetprice1 = Double.valueOf(textField1.getText());
						double volatility1 = Double.valueOf(textField2.getText());
						double assetprice2 = Double.valueOf(textField3.getText());
						double volatility2 = Double.valueOf(textField4.getText());
						double interest = Double.valueOf(textField5.getText());
						double time = Double.valueOf(textField6.getText());
						double strike = Double.valueOf(textField7.getText());
						double correlation = Double.valueOf(textField8.getText());
						int  number = Integer.valueOf(textField9.getText());
						ArithmeticBasket arithmeticBasket = null;
						if(radioButton3.isSelected()){
							arithmeticBasket = new ArithmeticBasket(assetprice1, assetprice2, volatility1, volatility2,
									interest, time, strike, correlation, number, true);
						}
						if(radioButton4.isSelected()){
							arithmeticBasket = new ArithmeticBasket(assetprice1, assetprice2, volatility1, volatility2,
									interest, time, strike, correlation, number, false);
						}
						if(radioButton1.isSelected()){
							double[] interval = arithmeticBasket.calloption();
							textArea.setText("Call option value confidence interval:\n[" + String.valueOf(interval[0]) + "," + String.valueOf(interval[1]) + "]");
						}
						if(radioButton2.isSelected()){
							double[] interval = arithmeticBasket.putoption();
							textArea.setText("Put option value confidence interval:\n[" + String.valueOf(interval[0]) + "," + String.valueOf(interval[1]) + "]");
						}
					}
				});
				
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		});
		
		item8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(700, 450));
				JLabel label = new JLabel("Developed by ZhangXiaowei at HKU 2016.3.22");
				panel.add(label);
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		});
		
		item9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JPanel panel = new JPanel();
				panel.setPreferredSize(new Dimension(700, 450));
				JLabel label = new JLabel("Under Contruction T T");
				panel.add(label);
				frame.setContentPane(panel);
				frame.setVisible(true);
			}
		});
		frame.setContentPane(panel);
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PricerGUI();
		
		/*
		Blackscholes blackscholes = new Blackscholes(100, 0.2, 0.01, 0.5, 100);
		System.out.println(blackscholes.calloption());
		System.out.println(blackscholes.putoption());
		
		Volatility volatility = new Volatility(100, 0.01, 0, 0.5, 100, 5.876024233827607);
		System.out.println(volatility.calloption());
		System.out.println(volatility.putoption());
		
		BinomialTree binomialTree = new BinomialTree(100, 0.2, 0.01, 0.5, 100, 1000);
		System.out.println(binomialTree.europeanCall());
		System.out.println(binomialTree.europeanPut());
		
		GeometricAsian geometricAsian = new GeometricAsian(100, 0.3, 0.05, 3, 100, 50);
		System.out.println(geometricAsian.calloption());
		System.out.println(geometricAsian.putoption());
		
		ArithmeticAsian arithmeticAsian = new ArithmeticAsian(100, 0.3, 0.05, 3, 100, 50, 100000, true);
		double[] interval = arithmeticAsian.calloption();
		System.out.println(interval[0]);
		System.out.println(interval[1]);
		
		GeometricBasket geometricBasket = new GeometricBasket(100, 100, 0.3, 0.3, 0.05, 3, 100, 0.5);
		System.out.println(geometricBasket.calloption());
		System.out.println(geometricBasket.putoption());
		
		ArithmeticBasket arithmeticBasket = new ArithmeticBasket(100, 100, 0.3, 0.3, 0.05, 3, 100, 0.5, 100000, true);
		double[] interval1 = arithmeticBasket.putoption();
		System.out.println(interval1[0]);
		System.out.println(interval1[1]);
		*/
	}

}
