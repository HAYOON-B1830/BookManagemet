package front_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//JTable 연동 출력 확인
public class Database extends JFrame implements ActionListener {

	JLabel l1, l2, l3, l4, l5; // 번호, 제목, 저자, 출판사, 출판년도
	JTextField noText, titleText, authorText, publisherText, yearText;
	JPanel panel; // JLabel, JTextArea 부착

	// JTable
	Object ob[][] = new Object[0][5]; // 데이터 열 표시
	DefaultTableModel model; // 데이터 저장부분
	JTable table;
	JScrollPane js;
	String str[] = { "도서번호", "도서명", "저자", "출판사", "출판년도" }; // 컬럼명

	// DB연동
	Connection con = null;
	Statement stmt = null; // sql구문 실행
	ResultSet result = null; // select구문 사용시 필요

	// DB연동 시 필요
	String url = "jdbc:mysql://localhost/bookdb_schema?serverTimezone=Asia/Seoul"; // dbstudy스키마
	String user = "root";
	String passwd = "0000"; // MySQL에 저장한 root 계정의 비밀번호를 적어주면 된다.

	public Database() { /// * 데이터베이스 관련 코드는 try-catch문으로 예외 처리를 꼭 해주어야 한다. */
		try {
			// 데이터베이스 연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, passwd);
			stmt = con.createStatement();
			// 메뉴 리스트
			System.out.println("[1] 데이터 추가");
			System.out.println("[2] 데이터 삭제");
			System.out.println("[3] 데이터 변경");
			System.out.println("[4] 데이터 조회");

			System.out.print("= 번호 입력 : ");
			Scanner s = new Scanner(System.in);
			int num = s.nextInt();

			switch (num) {
			case 1:
				insertData(); // 데이터 삽입
				break;
			case 2:
				removeData(); // 데이터 삭제
				break;
			case 3:
				changeData(); // 데이터 수정
				break;
			case 4:
				viewData(); // 데이터 조회
				break;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			System.out.println("DB접속 오류");
		} finally {
			try {

				stmt.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}

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
		try {
			// 실행할 sql문장 작성
			String sql = "select * from books";
			stmt = con.prepareStatement(sql);
			System.out.println("stmt : " + stmt);
			result = stmt.executeQuery();
			System.out.println("rs : " + result);

			// books테이블에서 불러오기
			while (result.next()) {
				String no = result.getString("BOOK_NO");
				String title = result.getString("BOOK_TITLE");
				String author = result.getString("AUTHOR");
				String publisher = result.getString("PUBLISHER");
				String year = result.getString("PUBLISHER_YEAR");

				Object data[] = { no, title, author, publisher, year };
				model.addRow(data);
				System.out.println(no + ", " + title + ", " + author + ", " + publisher + ", " + year);

			}
		} catch (Exception e) {
			System.out.println("select() 실행오류 : " + e);
		}
	}
	// noText, titleText, authorText, publisherText, yearText;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == noText) { // 도서번호 입력 후 enter 친 경우
			titleText.requestFocus(); // 커서입력
		} else if (e.getSource() == titleText) {
			authorText.requestFocus();
		} else if (e.getSource() == authorText) {
			publisherText.requestFocus();
		} else if (e.getSource() == publisherText) {
			yearText.requestFocus();
		} else if (e.getSource() == yearText) {
			if (noText.getText().equals("") && titleText.getText().equals("") && authorText.getText().equals("")
					&& publisherText.getText().equals("") && yearText.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "값을 입력하세요."); // (부모명, 경고메세지)
				noText.requestFocus(); // 이름필드부터 입력할 수 있게 커서 이동
				return;
			}
		}

		Object data[] = { noText.getText(), titleText.getText(), authorText.getText(), publisherText.getText(),
				yearText.getText() };
		model.addRow(data);
		noText.setText("");
		titleText.setText("");
		authorText.setText("");
		publisherText.setText("");
		yearText.setText("");
		noText.requestFocus();
	}

	// 삽입 - 정상동작확인
	public void insertData() {
		try {

			Scanner sc = new Scanner(System.in);
			System.out.println("추가할 도서의 정보를 입력해주세요.");

			System.out.print("도서번호 :");
			String bno = sc.nextLine();
			System.out.print("도서명 :");
			String btitle = sc.nextLine();
			System.out.print("저자 : ");
			String bauthor = sc.nextLine();
			System.out.print("출판사 :");
			String bpublisher = sc.nextLine();
			System.out.print("출판년도 :");
			String byear = sc.nextLine();

			String insertStr = "INSERT INTO books VALUES(\'" + bno + "\', \'" + btitle + "\', \'" + bauthor + "\', \'"
					+ bpublisher + "\', \'" + byear + "\')";
			stmt.executeUpdate(insertStr);
			System.out.println("데이터 추가 성공!");
		} catch (Exception e) {
			System.out.println("데이터 추가 실패 이유 : " + e.toString());
		}
	}

	// 삭제 - 정상동작확인
	public void removeData() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("삭제하길 원하는 도서명:");
			String title = sc.nextLine();

			String removeStr = "DELETE FROM books where BOOK_TITLE =\'" + title + "\'";
			stmt.executeUpdate(removeStr);
			System.out.println("데이터 삭제 성공!");

			sc.close();

		} catch (Exception e) {
			System.out.println("데이터 삭제 실패 이유 : " + e.toString());
		}
	}

	// 수정
	public void changeData() { // *****!!주의: 조건 걸어주지 않으면 전체데이터 변경됨!!
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("수정하길 원하는 도서명:");
			String title = sc.nextLine();

			System.out.println("수정할 도서의 정보를 입력해주세요.");

			System.out.print("도서명 :");
			String chtitle = sc.nextLine();
			System.out.print("저자 : ");
			String chauthor = sc.nextLine();
			System.out.print("출판사 :");
			String chpublisher = sc.nextLine();
			System.out.print("출판년도 :");
			String chyear = sc.nextLine();

			String changeStr = "UPDATE books SET BOOK_TITLE=\'" + chtitle + "\', AUTHOR=\'" + chauthor
					+ "\', PUBLISHER=\'" + chpublisher + "\', PUBLISHER_YEAR=\'" + chyear + "\' WHERE BOOK_TITLE = \'"
					+ title + "\'";

			stmt.executeUpdate(changeStr);
			System.out.println("데이터 변경 성공!");
		} catch (Exception e) {
			System.out.println("데이터 변경 실패 이유 : " + e.toString());
		}
	}

	// 조회 - 정상동작확인
	public void viewData() {
		try {
			System.out.println("== books 테이블 조회 ==");
			String viewStr1 = "SELECT * FROM books";
			ResultSet result1 = stmt.executeQuery(viewStr1);
			int cnt1 = 0;
			while (result1.next()) {
				System.out.print(result1.getString("BOOK_NO") + "\t" + result1.getString("BOOK_TITLE") + "\t"
						+ result1.getString("AUTHOR") + "\t" + result1.getString("PUBLISHER") + "\t"
						+ result1.getString("PUBLISHER_YEAR") + "\t" + "\n");
				cnt1++;
			}

			System.out.println("");
			System.out.println("데이터 조회 성공!");
		} catch (Exception e) {
			System.out.println("데이터 조회 실패 이유 : " + e.toString());
		}
	}

	public static void main(String[] args) {
		new Database();
	}

}