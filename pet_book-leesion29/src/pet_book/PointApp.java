package pet_book;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;

public class PointApp {

    private JFrame pframe;
    private JLabel Label_N2;
    private JLabel Label_P2;
    private JLabel DataLabel_1; 
    private LineBorder bb = new LineBorder(Color.black, 1, true);
    
    /* 앱 구동 */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PointApp window = new PointApp();
                    window.pframe.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* 앱 생성 */
    public PointApp() {
        initialize();
        LoadUserData();
        LoadPointHistory();
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
                int myPoint = resultSet.getInt("point");
                Label_N2.setText(retrievedName + "님의");
                Label_P2.setText(String.valueOf(myPoint));
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

    private void LoadPointHistory() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/petbook_db";
        String username = "root";
        String password = "0000";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // 기존의 모든 데이터를 가져오는 쿼리를 사용합니다.
            String selectQuery = "SELECT date, point FROM point_table ORDER BY date DESC";

            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            String pointHistoryText = "<html>";
            int count = 0;
            while (resultSet.next() && count < 8) {
                String date = resultSet.getString("date");
                int point = resultSet.getInt("point");
                pointHistoryText += date + ": -" + point + " 포인트<br>";
                DataLabel_1.setBorder(bb);
                count++;
            }
            pointHistoryText += "</html>";

            DataLabel_1.setText(pointHistoryText);

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /* GUI 구성 */
    private void initialize() {
        // 화질 저하 방지 코드
        System.setProperty("sun.java2d.uiScale", "1.0");

        pframe = new JFrame();
        pframe.setTitle("포인트 이력");
        pframe.setBounds(100, 100, 800, 500);
        pframe.setIconImage(Toolkit.getDefaultToolkit().getImage(ShopApp.class.getResource("/images/icon.png")));
        pframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pframe.setLocationRelativeTo(null);
        pframe.getContentPane().setLayout(null);
        pframe.setVisible(true);
        pframe.setResizable(false);

        JButton btnNewButton = new JButton("Home");
        btnNewButton.setFont(new Font("돋움", Font.PLAIN, 12));
        btnNewButton.setToolTipText("누르면 홈으로 돌아갑니다");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 현재 창을 숨김
                pframe.setVisible(false);
                // Home 화면을 띄우기 위한 코드
                Home.createAndShowGUI();
            }
        });

        btnNewButton.setBounds(667, 10, 91, 23);
        pframe.getContentPane().add(btnNewButton);

        JLabel lblNewLabel = new JLabel("포인트");
        lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        lblNewLabel.setBounds(12, 10, 134, 27);
        pframe.getContentPane().add(lblNewLabel);

        JPanel view_panel = new JPanel();
        view_panel.setBounds(12, 47, 490, 298);
        pframe.getContentPane().add(view_panel);
        view_panel.setLayout(null);

        DataLabel_1 = new JLabel("N"); // 수정된 부분
        DataLabel_1.setVerticalAlignment(SwingConstants.TOP);
        DataLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
        DataLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
        DataLabel_1.setBounds(0, 0, 490, 282);
        view_panel.add(DataLabel_1); // 수정된 부분
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(0, 404, 490, 2);
        view_panel.add(separator_1);

        JPanel profile_panel = new JPanel();
        profile_panel.setBounds(514, 47, 260, 406);
        pframe.getContentPane().add(profile_panel);
        profile_panel.setLayout(null);

        Label_N2 = new JLabel("홍길동님의");
        Label_N2.setHorizontalAlignment(SwingConstants.CENTER);
        Label_N2.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
        Label_N2.setBounds(0, 267, 260, 40);
        profile_panel.add(Label_N2);

        Label_P2 = new JLabel("2023");
        Label_P2.setFont(new Font("돋움", Font.BOLD, 20));
        Label_P2.setHorizontalAlignment(SwingConstants.CENTER);
        Label_P2.setBounds(46, 350, 181, 58);
        profile_panel.add(Label_P2);

        JButton btn_profile = new JButton("");
        btn_profile.setIcon(new ImageIcon(ShopApp.class.getResource("/images/rabbit03_r.png")));
        btn_profile.setBounds(12, 10, 236, 247);
        profile_panel.add(btn_profile);

        btn_profile.setContentAreaFilled(false);
        btn_profile.setFocusPainted(false);

        JLabel lblNewLabel_1_1 = new JLabel("포인트");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setFont(new Font("맑은 고딕", Font.PLAIN, 27));
        lblNewLabel_1_1.setBounds(0, 304, 260, 47);
        profile_panel.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1 = new JLabel("※ 포인트 이력은 최대 8건까지 볼 수 있습니다");
        lblNewLabel_1.setFont(new Font("돋움", Font.PLAIN, 17));
        lblNewLabel_1.setBounds(12, 355, 490, 25);
        pframe.getContentPane().add(lblNewLabel_1);
        
        

    }
}
