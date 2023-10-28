package front_gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class BookList extends JFrame implements ActionListener, TableCellRenderer {

	JLabel titleLabel, authorLabel, publisherLabel; // 제목, 저자, 출판사
	JTextField titleText, authorText, publisherText;
	JButton inputsearchbtn, homebtn;
	JPanel panel; // JLabel, JTextArea 부착
	String rentable;

	// JTable *******테이블 도서번호를 넣어야할지 고민해봐야함/ 도서코드로 해서 나중에 입력가능하게? 아닌데**********
	Object ob[][] = new Object[0][7];
	DefaultTableModel model; // 데이터 저장부분
	JTable booktable;
	JScrollPane scrollPane;
	String str[] = { "도서번호", "도서명", "저자", "출판사", "출판년도", "대출가능여부", "대출/예약" }; // 컬럼명

	// DB연동
	Connection con = null;
	PreparedStatement stmt = null; // sql구문 실행
	ResultSet result = null; // select구문 사용시 필요

	// DB연동 시 필요
	String url = "jdbc:mysql://localhost/bookdb_schema?serverTimezone=Asia/Seoul"; // dbstudy스키마
	String user = "root";
	String passwd = "0000"; // MySQL에 저장한 root 계정의 비밀번호를 적어주면 된다.

	public BookList() {
		super("도서목록");

		setSize(800, 500);
		setVisible(false);
		setResizable(false); // 사이즈조정 불가능
		setLocationRelativeTo(null); // 창위치 가운데
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		///////////////////////////////////////////////
		// StringLabel
		// searchbtn, homebtn

		JLabel StringLabel = new JLabel("도서목록");
		StringLabel.setFont(new Font("굴림", Font.BOLD, 30));
		StringLabel.setBounds(39, 32, 428, 50);
		getContentPane().add(StringLabel);

		JButton searchbtn = new JButton("검색");
		searchbtn.setFont(new Font("굴림", Font.PLAIN, 15));
		searchbtn.setBounds(462, 32, 135, 40);
		getContentPane().add(searchbtn);

		JButton homebtn = new JButton("HOME");
		homebtn.setFont(new Font("굴림", Font.PLAIN, 15));
		homebtn.setBounds(609, 32, 135, 40);
		getContentPane().add(homebtn);

		homebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UserMain();
			}
		});

		/////////////////////////////////////////////////////////
		/* 도서명,저자,출판사 입력 - 검색버튼 */
//		라벨 titleLabel, authorLabel, publisherLabel 
//		텍스트필드 titleText, authorText, publisherText

		JLabel titleLabel = new JLabel("도서명");
		titleLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		titleLabel.setBounds(39, 92, 46, 40);
		getContentPane().add(titleLabel);

		titleText = new JTextField();
		titleText.setFont(new Font("굴림", Font.PLAIN, 15));
		titleText.setBounds(86, 92, 211, 40);
		getContentPane().add(titleText);
		titleText.setColumns(10);

		JLabel authorLabel = new JLabel("저자");
		authorLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		authorLabel.setBounds(309, 92, 31, 40);
		getContentPane().add(authorLabel);

		authorText = new JTextField();
		authorText.setFont(new Font("굴림", Font.PLAIN, 15));
		authorText.setBounds(342, 93, 125, 40);
		getContentPane().add(authorText);
		authorText.setColumns(10);

		JLabel publisherLabel = new JLabel("출판사");
		publisherLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		publisherLabel.setBounds(479, 92, 52, 40);
		getContentPane().add(publisherLabel);

		publisherText = new JTextField();
		publisherText.setFont(new Font("굴림", Font.PLAIN, 15));
		publisherText.setBounds(527, 93, 116, 40);
		getContentPane().add(publisherText);
		publisherText.setColumns(10);

		JButton inputsearchbtn = new JButton("검색");
		inputsearchbtn.setBounds(655, 92, 89, 40);
		getContentPane().add(inputsearchbtn);

		inputsearchbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connect();
				search();
			}
		});

		////////////////////////////////////////////////////////////////////
		/* 테이블 */
		// JTable가운데 배치
		model = new DefaultTableModel(ob, str);
		booktable = new JTable(model);
		scrollPane = new JScrollPane(booktable);
		getContentPane().add("Center", scrollPane);
		scrollPane.setBounds(39, 142, 705, 295);
		// getContentPane().add(scrollPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setVisible(true);

		// 버튼에 ActionListenr 등록
		inputsearchbtn.addActionListener(this);

		// 텍스트필드에 addActionLinstener 등록
		titleText.addActionListener(this);
		authorText.addActionListener(this);
		publisherText.addActionListener(this);

		// DB접속해 select문장 이용해 jtable에 보여주는 구문
		connect();
		select();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					if (result != null)
						result.close();
					if (stmt != null)
						stmt.close();
					if (con != null)
						con.close();

				} catch (Exception e2) {
					System.exit(0);
				}
			}

		});

		setVisible(true);

	}

	@Override // 텍스트필드에 번호,도서명,저자,출판사,출판년도 입력하면 DB에 저장된다.
	public void actionPerformed(ActionEvent e) {
//		ActionEvent 발생 시 실행되는 메소드 -> 버튼 클릭시, 입력 칸에 입력 후 엔터키를 쳤을 때
		Object o = e.getSource(); // 이벤트가 발생한 객체 리턴
		if (o == inputsearchbtn) {
			search();
		}
	}

	public void clear() {
		// 재입력을 위한 필드 초기화
		titleText.setText("");
		authorText.setText("");
		publisherText.setText("");
		titleText.requestFocus();
	}

	public void connect() {
		try {
			// 접속할 드라이버 메모리에 올리기
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 접속하기 위한 메서드(접속 url, 계정명, 계정암호)
			con = DriverManager.getConnection("jdbc:mysql://localhost/bookdb_schema?serverTimezone=Asia/Seoul", "root",
					"0000");
			System.out.println("접속 : " + con);
		} catch (Exception e) {
			System.out.println("DB접속 오류 : " + e);
		}
	}

	public void select() {
		// 기본정보 출력
		try {
			// 실행할 sql문장 작성
			String sql = "select * from books";
			stmt = con.prepareStatement(sql);
			result = stmt.executeQuery(sql); // select문장

			// books테이블에서 불러오기
			while (result.next()) {
//				String str[] = {"도서번호", "도서명", "저자", "출판사" ,"출판년도", "대출가능여부", "대출/예약"}; // 컬럼명
				String no = result.getString("BOOK_NO");
				String title = result.getString("BOOK_TITLE");
				String author = result.getString("AUTHOR");
				String publisher = result.getString("PUBLISHER");
				String year = result.getString("PUBLISHER_YEAR");

				// object[]를 생성저장 해 model에 추가->JTable에서 결과 확인
				Object data[] = { no, title, author, publisher, year };
				model.addRow(data);
				System.out.println(no + ", " + title + ", " + author + ", " + publisher + ", " + year);// 콘솔출력

				// 대출가능여부 표기, 대출/예약버튼
//
//				String rentsql = "select * from rent";
//				stmt = con.prepareStatement(rentsql);
//				result = stmt.executeQuery(rentsql);
//				if ( no == result.getString("RENT_BOOK_NUMBER")) {
//					String rentable = "대출중";
//				} else {
//					String rentable = "대출중";
//				}

			}

		} catch (Exception e) {
			System.out.println("select() 실행오류 : " + e);
		}
	}

	// *버튼 누르면 검색결과 나옴*//
	public void search() {
		try {
			// 실행할 sql문장 작성
			model.setNumRows(0);
			String searchStr = "select * from books WHERE BOOK_TITLE LIKE '%" + titleText.getText() + "%'";
			stmt = con.prepareStatement(searchStr);
			result = stmt.executeQuery(searchStr);

			// clear();

			// books테이블에서 불러오기
			while (result.next()) {
				String no = result.getString("BOOK_NO");
				String title = result.getString("BOOK_TITLE");
				String author = result.getString("AUTHOR");
				String publisher = result.getString("PUBLISHER");
				String year = result.getString("PUBLISHER_YEAR");

				// object[]를 생성저장 해 model에 추가->JTable에서 결과 확인
				Object data[] = { no, title, author, publisher, year };
				model.addRow(data);
				System.out.println(no + ", " + title + ", " + author + ", " + publisher + ", " + year);// 콘솔출력

			}
		} catch (Exception e) {
			System.out.println("search() 실행오류 : " + e);
		}
	}

	public static void main(String[] args) {
		new BookList();

	}
}
