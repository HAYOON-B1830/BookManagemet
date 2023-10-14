//테스트용 페이지 shop

//package pet_book;
//
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//import javax.swing.JTextPane;
//import javax.swing.JScrollBar;
//import javax.swing.JLabel;
//import java.awt.Font;
//import javax.swing.JRadioButton;
//import javax.swing.ImageIcon;
//import javax.swing.SwingConstants;
//import java.awt.Toolkit;
//
//public class ShopApp {
//
//	private JFrame frame;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ShopApp window = new ShopApp();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public ShopApp() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		//화질 저하 방지 코드
//		System.setProperty("sun.java2d.uiScale", "1.0");
//		
//		frame = new JFrame();
//		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ShopApp.class.getResource("/images/icon.png")));
//		frame.setTitle("상점");
//		frame.setBounds(100, 100, 450, 300);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//		
//		JPanel panel = new JPanel();
//		panel.setBounds(12, 47, 304, 194);
//		frame.getContentPane().add(panel);
//		panel.setLayout(null);
//		
//		JScrollBar scrollBar = new JScrollBar();
//		scrollBar.setBounds(287, 0, 17, 194);
//		panel.add(scrollBar);
//		
//		JPanel panel_2 = new JPanel();
//		panel_2.setBounds(12, 10, 125, 127);
//		panel.add(panel_2);
//		
//		JButton btnNewButton_3 = new JButton("100");
//		btnNewButton_3.setBounds(12, 147, 125, 37);
//		panel.add(btnNewButton_3);
//		
//		JPanel panel_2_1 = new JPanel();
//		panel_2_1.setBounds(149, 10, 125, 127);
//		panel.add(panel_2_1);
//		
//		JButton btnNewButton_3_1 = new JButton("100");
//		btnNewButton_3_1.setBounds(149, 147, 125, 37);
//		panel.add(btnNewButton_3_1);
//		
//		JButton btnNewButton = new JButton("Home");
//		btnNewButton.setFont(new Font("돋움", Font.PLAIN, 12));
//		btnNewButton.setBounds(333, 10, 91, 23);
//		frame.getContentPane().add(btnNewButton);
//		
//		JButton btnNewButton_1 = new JButton("포인트 이력");
//		btnNewButton_1.setFont(new Font("돋움", Font.PLAIN, 12));
//		btnNewButton_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		btnNewButton_1.setBounds(218, 10, 101, 23);
//		frame.getContentPane().add(btnNewButton_1);
//		
//		JPanel panel_1 = new JPanel();
//		panel_1.setBounds(328, 47, 96, 194);
//		frame.getContentPane().add(panel_1);
//		panel_1.setLayout(null);
//		
//		JLabel lblNewLabel_1 = new JLabel("홍길동님의");
//		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1.setFont(new Font("돋움", Font.PLAIN, 12));
//		lblNewLabel_1.setBounds(0, 110, 96, 29);
//		panel_1.add(lblNewLabel_1);
//		
//		JLabel lblNewLabel_2 = new JLabel("2023");
//		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_2.setBounds(0, 157, 96, 37);
//		panel_1.add(lblNewLabel_2);
//		
//		JButton btnNewButton_2 = new JButton("");
//		btnNewButton_2.setIcon(new ImageIcon(ShopApp.class.getResource("/images/dog03_r.png")));
//		btnNewButton_2.setBounds(12, 10, 67, 90);
//		panel_1.add(btnNewButton_2);
//		
//		JLabel lblNewLabel_1_1 = new JLabel("포인트");
//		lblNewLabel_1_1.setFont(new Font("돋움", Font.PLAIN, 12));
//		lblNewLabel_1_1.setBounds(28, 131, 39, 29);
//		panel_1.add(lblNewLabel_1_1);
//		
//		JLabel lblNewLabel = new JLabel("상점");
//		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
//		lblNewLabel.setBounds(12, 10, 134, 27);
//		frame.getContentPane().add(lblNewLabel);
//	}
//}
