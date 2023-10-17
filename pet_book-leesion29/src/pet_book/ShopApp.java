/*구매 및 이력 미구현, 포인트 및 사용자명 연동은 차후 수정이 필요함.*/

package pet_book;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.JScrollPane;

public class ShopApp {

    private JFrame frame;
    private JLabel Label_N2;
    private JLabel Label_P2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShopApp window = new ShopApp();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ShopApp() {
        initialize();
        LoadUserData(); // 데이터 자동으로 가져오기
    }

    private void initialize() {
		//화질 저하 방지 코드
		System.setProperty("sun.java2d.uiScale", "1.0");
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ShopApp.class.getResource("/images/icon.png")));
		frame.setTitle("상점");
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 68, 490, 643);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(473, 0, 17, 406);
		panel.add(scrollBar);
		
        JButton btnNewButton = new JButton("Home");
        btnNewButton.setFont(new Font("돋움", Font.PLAIN, 12));
        btnNewButton.setToolTipText("누르면 홈으로 돌아갑니다");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 현재 창을 숨김
                frame.setVisible(false);
                // Home 화면을 띄우기 위한 코드
                Home.createAndShowGUI();
            }
        });

        
        btnNewButton.setBounds(667, 10, 91, 23);
        frame.getContentPane().add(btnNewButton);


		JButton btn_f1 = new JButton("\r\n");
		btn_f1.setIcon(new ImageIcon(ShopApp.class.getResource("/images/food01_r.png")));
		btn_f1.setBounds(23, 10, 179, 144);
		panel.add(btn_f1);
		
		JButton btn_price_1 = new JButton("100");
		btn_price_1.setBounds(67, 164, 91, 23);
		panel.add(btn_price_1);
		
		JButton btn_price_2 = new JButton("100");
		btn_price_2.setBounds(318, 164, 91, 23);
		panel.add(btn_price_2);
		
		JButton btn_f2 = new JButton("");
		btn_f2.setIcon(new ImageIcon(ShopApp.class.getResource("/images/food02_r.png")));
		btn_f2.setBounds(273, 10, 179, 144);
		panel.add(btn_f2);
		
		JButton btn_f3 = new JButton("");
		btn_f3.setIcon(new ImageIcon(ShopApp.class.getResource("/images/food03_r.png")));
		btn_f3.setBounds(23, 207, 179, 144);
		panel.add(btn_f3);
		
		JButton btn_f4 = new JButton("");
		btn_f4.setIcon(new ImageIcon(ShopApp.class.getResource("/images/food04_r.png")));
		btn_f4.setBounds(273, 207, 179, 144);
		panel.add(btn_f4);
		
		JButton btn_price_3 = new JButton("100");
		btn_price_3.setBounds(67, 361, 91, 23);
		panel.add(btn_price_3);
		
		JButton btn_price_4 = new JButton("100");
		btn_price_4.setBounds(318, 361, 91, 23);
		panel.add(btn_price_4);
		
		JButton btn_t2 = new JButton("");
		btn_t2.setIcon(new ImageIcon(ShopApp.class.getResource("/images/toy02_r.png")));
		btn_t2.setBounds(273, 405, 179, 144);
		panel.add(btn_t2);
		
		JButton btn_t1 = new JButton("");
		btn_t1.setIcon(new ImageIcon(ShopApp.class.getResource("/images/toy01_r.png")));
		btn_t1.setBounds(23, 405, 179, 144);
		panel.add(btn_t1);
		
		JButton btn_price_5 = new JButton("100");
		btn_price_5.setBounds(67, 559, 91, 23);
		panel.add(btn_price_5);
		
		JButton btn_price_6 = new JButton("100");
		btn_price_6.setBounds(318, 559, 91, 23);
		panel.add(btn_price_6);
		
		
		JButton btnNewButton_1 = new JButton("포인트 이력");
		btnNewButton_1.setFont(new Font("돋움", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(526, 10, 101, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(514, 47, 260, 406);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		Label_N2 = new JLabel("홍길동님의");
		Label_N2.setHorizontalAlignment(SwingConstants.CENTER);
		Label_N2.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		Label_N2.setBounds(0, 267, 260, 40);
		panel_1.add(Label_N2);
		
		Label_P2 = new JLabel("2023");
		Label_P2.setFont(new Font("돋움", Font.BOLD, 20));
		Label_P2.setHorizontalAlignment(SwingConstants.CENTER);
		Label_P2.setBounds(46, 350, 181, 58);
		panel_1.add(Label_P2);
		
		JButton btn_profile = new JButton("");
		btn_profile.setIcon(new ImageIcon(ShopApp.class.getResource("/images/dog03_r.png")));
		btn_profile.setBounds(12, 10, 236, 247);
		panel_1.add(btn_profile);
		
		btn_profile.setContentAreaFilled(false);
		btn_profile.setFocusPainted(false); 
		btn_f1.setContentAreaFilled(false);
		btn_f1.setFocusPainted(false); 
		btn_f2.setContentAreaFilled(false);
		btn_f2.setFocusPainted(false); 
		btn_f3.setContentAreaFilled(false);
		btn_f3.setFocusPainted(false); 
		btn_f4.setContentAreaFilled(false);
		btn_f4.setFocusPainted(false); 
		btn_t1.setContentAreaFilled(false);
		btn_t1.setFocusPainted(false); 
		btn_t2.setContentAreaFilled(false);
		btn_t2.setFocusPainted(false); 
		
		JLabel lblNewLabel_1_1 = new JLabel("포인트");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 27));
		lblNewLabel_1_1.setBounds(0, 304, 260, 47);
		panel_1.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel = new JLabel("상점");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 10, 134, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(12, 47, 490, 406); // scrollPane의 크기 설정
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 가로 스크롤 비활성화
		frame.getContentPane().add(scrollPane); // frame에 scrollPane 추가

		// panel 크기 조정
		panel.setPreferredSize(new Dimension(490, 673));
    }

		 private void LoadUserData() {
		        String jdbcUrl = "jdbc:mysql://localhost:3306/petbook_db";
		        String username = "root";
		        String password = "0000";
		        try {
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

		            String selectQuery = "SELECT member_name, point FROM member_table WHERE member_id = ?";
		            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

		            String memberIdToBeRetrieved = "test1234";
		            preparedStatement.setString(1, memberIdToBeRetrieved);

		            ResultSet resultSet = preparedStatement.executeQuery();

		            if (resultSet.next()) {
		                String retrievedName = resultSet.getString("member_name");
		                int retrievedPoint = resultSet.getInt("point");
		                Label_N2.setText(retrievedName + "님의");
		                Label_P2.setText(String.valueOf(retrievedPoint));
		            } else {
		                System.out.println(memberIdToBeRetrieved + "의 정보를 찾을 수 없습니다.");
		            }

		            resultSet.close();
		            preparedStatement.close();
		            connection.close();
		        } catch (ClassNotFoundException | SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}
