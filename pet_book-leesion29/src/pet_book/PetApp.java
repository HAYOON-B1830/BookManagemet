/*펫 선택 및 성장은 미구현, 포인트 및 사용자명 연동은 차후 수정이 필요함.*/

package pet_book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.ImageIcon;

public class PetApp {

	private JFrame frame;
	private JLabel Label_N2; 
	private JLabel Label_P2; 

	/**
	 * 앱을 구동
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetApp window = new PetApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 앱을 생성
	 */

	public PetApp() {
		initialize();
		LoadUserData();
	}
	
	/**
	 * sql 연동으로 유저 데이터를 불러옴
	 */
	private void LoadUserData() {
	    try {
	        // JDBC 드라이버 로드
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // 데이터베이스 연결 정보 설정
	        String url = "jdbc:mysql://localhost:3306/petbook_db";
	        String username = "root";
	        String password = "0000";

	        // 데이터베이스 연결
	        Connection connection = DriverManager.getConnection(url, username, password);

	        // SQL 쿼리 작성 및 실행
	        String query = "SELECT member_name, point, pet_type FROM member_table WHERE member_id = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, "test1234"); // 해당 유저 데이터를 불러옴 (수정 필요)

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            String memberName = resultSet.getString("member_name");
	            int point = resultSet.getInt("point");
//	            String petType = resultSet.getString("pet_type");

	            Label_N2.setText(memberName);
	            Label_P2.setText(String.valueOf(point));
//	            Label_PetType.setText(petType);
	        }

	        // 데이터베이스 연결 종료
	        connection.close();
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	    }
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//화질 저하 방지 코드
		System.setProperty("sun.java2d.uiScale", "1.0");
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PetApp.class.getResource("/images/icon.png")));
		frame.setTitle("펫 상태 확인");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 436, 263);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		

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

        
		btnNewButton.setBounds(333, 10, 91, 23);
		panel.add(btnNewButton);
		
		String name = "이름";
		
		JPanel panel_pet = new JPanel();
		panel_pet.setToolTipText("현재 펫 이미지입니다");
		panel_pet.setBounds(28, 22, 150, 150);
		panel.add(panel_pet);
		panel_pet.setLayout(null);
		
		JButton btn_pet = new JButton("");
		btn_pet.setBackground(new Color(0, 0, 0));
		btn_pet.setIcon(new ImageIcon(PetApp.class.getResource("/images/dog03_r.png")));
		btn_pet.setBounds(0, 0, 150, 150);
		panel_pet.add(btn_pet);
		
		JPanel panel_baby = new JPanel();
		panel_baby.setBounds(55, 182, 59, 57);
		panel.add(panel_baby);
		panel_baby.setLayout(null);
		
		JButton btn_pet1 = new JButton("");
		btn_pet1.setBackground(new Color(0, 0, 0));
		btn_pet1.setIcon(new ImageIcon(PetApp.class.getResource("/images/dog01_s.png")));
		btn_pet1.setBounds(0, 0, 59, 57);
		panel_baby.add(btn_pet1);
		
		JPanel panel_youth = new JPanel();
		panel_youth.setBounds(197, 182, 59, 57);
		panel.add(panel_youth);
		panel_youth.setLayout(null);
		
		JButton btn_pet2 = new JButton("");
		btn_pet2.setBackground(new Color(0, 0, 0));
		btn_pet2.setIcon(new ImageIcon(PetApp.class.getResource("/images/dog02_s.png")));
		btn_pet2.setBounds(0, 0, 59, 57);
		panel_youth.add(btn_pet2);
		
		JPanel panel_adult = new JPanel();
		panel_adult.setBounds(333, 182, 59, 57);
		panel.add(panel_adult);
		panel_adult.setLayout(null);
		
		JButton btn_pet3 = new JButton("");
		btn_pet3.setIcon(new ImageIcon(PetApp.class.getResource("/images/dog03_s.png")));
		btn_pet3.setFocusPainted(false);
		btn_pet3.setContentAreaFilled(false);
		btn_pet3.setBackground(Color.BLACK);
		btn_pet3.setBounds(0, 0, 59, 57);
		panel_adult.add(btn_pet3);
		

		JButton btn_arr3 = new JButton("");
		btn_arr3.setIcon(new ImageIcon(PetApp.class.getResource("/images/Arrow_r.png")));
		btn_arr3.setBounds(126, 196, 59, 32);
		panel.add(btn_arr3);
		
		JButton btn_arr2 = new JButton("");
		btn_arr2.setIcon(new ImageIcon(PetApp.class.getResource("/images/Arrow_r.png")));
		btn_arr2.setBounds(268, 196, 59, 32);
		panel.add(btn_arr2);

		btn_pet.setContentAreaFilled(false);
		btn_pet.setFocusPainted(false); 
		btn_pet1.setContentAreaFilled(false);
		btn_pet1.setFocusPainted(false); 
		btn_pet2.setContentAreaFilled(false);
		btn_pet2.setFocusPainted(false); 
		btn_arr3.setContentAreaFilled(false); 
		btn_arr3.setBorderPainted(false);
		btn_arr3.setFocusPainted(false); 
		btn_arr2.setContentAreaFilled(false);
		btn_arr2.setBorderPainted(false); 
		btn_arr2.setFocusPainted(false);
		
		JLabel Label_N = new JLabel("이름");
		Label_N.setFont(new Font("돋움", Font.PLAIN, 12));
		Label_N.setBounds(231, 57, 76, 32);
		panel.add(Label_N);
		
		JLabel Label_P = new JLabel("잔여 포인트");
		Label_P.setFont(new Font("돋움", Font.PLAIN, 12));
		Label_P.setBounds(231, 99, 76, 32);
		panel.add(Label_P);
		
		Label_N2 = new JLabel("홍길동"); 
		Label_N2.setFont(new Font("돋움", Font.PLAIN, 12));
		Label_N2.setBounds(318, 57, 91, 32);
		panel.add(Label_N2);

		Label_P2 = new JLabel("2023");
		Label_P2.setFont(new Font("돋움", Font.PLAIN, 12));
		Label_P2.setBounds(318, 99, 91, 32);
		panel.add(Label_P2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(231, 124, 178, 5);
		panel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(231, 82, 178, 5);
		panel.add(separator_1);
	}
}
