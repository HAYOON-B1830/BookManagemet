package pet_book;

import javax.swing.*;
import java.awt.*;

public class Home extends JFrame {
    public Home() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/images/icon.png")));
        setTitle("메인 화면");
        setSize(700, 500);

        

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null); 
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("홈화면 (미구현)");
        lblNewLabel.setFont(new Font("돋움", Font.PLAIN, 88));
        lblNewLabel.setBounds(38, 174, 732, 124);
        getContentPane().add(lblNewLabel);
    }

    // 다른 클래스에서 Home 화면을 띄우기 위한 메서드
    public static void createAndShowGUI() {
        SwingUtilities.invokeLater(() -> {
            Home homeFrame = new Home();
            homeFrame.setVisible(true);
            
        });
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}
