package test_package;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;

public class BookFrame extends Frame {

	Button btn00, btn01, btn1, btn2;
	TextField tf1, tf2, tf3, tf4;
	TextArea ta1, ta2;
	Checkbox ch1, ch2, ch3;
	Scrollbar scr1;
	Label label1;

	public BookFrame() {
		super("구성테스트");

		setSize(800, 500); // 가로800 세로500

		Toolkit tk = Toolkit.getDefaultToolkit();// 화면크기구하는
		Dimension screenSize = tk.getScreenSize();
		setLocation(screenSize.width / 2 - 400, screenSize.height / 2 - 250);// 모니터 정중앙에 화면출력
		this.setBackground(new Color(236, 255, 179)); // rgb값 or 색 직접지정

		setLayout(new BorderLayout()); // 화면크기조절해도 변동없음

		Label label1 = new Label("도서목록"); // 표시
		add(label1);

		Button btn00 = new Button("검색");
		add("East", btn00);
		Button btn01 = new Button("Home");
		add("East", btn01);

		// Panel panel = new Panel(new BorderLayout());
		// Button panelNorth = new Button("panel_North");
		// panel.add("North", btn00);
		// panel.add("North", btn01);
		// add("North", btn00);
		// add("North", btn01);

		setLayout(new FlowLayout()); // 화면크기조절 시 유동적으로 움직임 //메소드를 왼쪽에서 오른쪽 정렬
		btn1 = new Button("확인");
		btn1.setBackground(Color.green);
		btn1.setForeground(Color.darkGray);
		add(btn1);

		tf1 = new TextField("도서명", 20);
		add(tf1);
		tf2 = new TextField("작가명", 5);
		add(tf2);
		tf3 = new TextField("출판사", 10);
		add(tf3);
		/*
		 * 출판년도 제외함 tf4 = new TextField("출판년도"); add(tf4);
		 */

		btn2 = new Button("검색");
		btn2.setBackground(Color.green);
		btn2.setForeground(Color.darkGray);
		add(btn2);

		ch1 = new Checkbox();
		add(ch1);

		ta1 = new TextArea("도서목록 출력자리", 10, 30, TextArea.SCROLLBARS_BOTH);
		add(ta1);// 크기고정하고 스크롤바 넣을 자리

		Scrollbar scr1 = new Scrollbar();
		scr1.setBounds(500, 400, 30, 200);
		add(scr1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BookFrame f = new BookFrame();
		f.setVisible(true);

	}

}
