package front_gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnetDB {

	Connection con = null;
	Statement stmt = null;
	String url = "jdbc:mysql://localhost/bookdb_schema?serverTimezone=Asia/Seoul"; // dbstudy스키마
	String user = "root";
	String passwd = "0000"; // MySQL에 저장한 root 계정의 비밀번호를 적어주면 된다.

	// ex) A03_DatabaseDao.java를 만들고,
	// 필드, 공통메서드, public void deptList()로 선언하여
	// sql을 select * from dept로 처리된 행을 출력하는 내용까지 처리.

	public static void main(String[] args) {
		try {
			// 데이터베이스 연결

			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bookdb_schema?serverTimezone=Asia/Seoul", "root",
					"0000");
			stmt = con.createStatement();

			try {
				// System.out.println("== books 테이블 조회 ==");
				String viewStr1 = "SELECT * FROM books";
				ResultSet result = stmt.executeQuery(viewStr1);
				int cnt1 = 0;

				// books테이블에서 불러오기
				while (result.next()) {
					String no = result.getString("BOOK_NO");
					String title = result.getString("BOOK_TITLE");
					String author = result.getString("AUTHOR");
					String publisher = result.getString("PUBLISHER");
					String year = result.getString("PUBLISHER_YEAR");

					// object[]만들어 model에 추가해 JTable에서 결과확인
					Object data[] = { no, title, author, publisher, year };
					// model.addRow(data);
					System.out.println(no + ", " + title + ", " + author + ", " + publisher + ", " + year);

//					System.out.print(result.getString("BOOK_NO") + "\t" + result.getString("BOOK_TITLE") + "\t"
//							+ result.getString("AUTHOR") + "\t" + result.getString("PUBLISHER") + "\t"
//							+ result.getString("PUBLISHER_YEAR") + "\t" + "\n");
//					cnt1++;
				}

				System.out.println("");
				System.out.println("데이터 조회 성공!");
			} catch (Exception e) {
				System.out.println("데이터 조회 실패 이유 : " + e.toString());
			}

		} catch (Exception e) {
			System.out.println(e.toString());

		} finally {
			try {
				stmt.close();
				con.close();
			} catch (Exception e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
			System.out.println("접속성공");

		}

	}

}
