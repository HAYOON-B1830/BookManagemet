package test_package;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

//*****test code*****

public class test_class extends Frame {
	/*
	 * public test_class() { //test_class 나중에 Frame으로 바꿀예정 super("구성테스트");
	 * 
	 * f.setSize(800, 500); // 가로800 세로500
	 * 
	 * Toolkit tk = Toolkit.getDefaultToolkit();// 화면크기구하는 Dimension screenSize =
	 * tk.getScreenSize(); setLocation(screenSize.width / 2 - 400, screenSize.height
	 * / 2 - 250);// 모니터 정중앙에 화면출력
	 * 
	 * }
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame f = new Frame("test");
		f.setSize(800, 500); // 가로800 세로500

		Toolkit tk = Toolkit.getDefaultToolkit();// 화면크기구하는
		Dimension screenSize = tk.getScreenSize();
		tk.getScreenSize();

		System.out.println(screenSize); // 화면크기출력
		f.setLocation(screenSize.width / 2 - 400, screenSize.height / 2 - 250);// 모니터 정중앙에 화면출력
		f.setVisible(true);

		/*
		 * 0 int i = 1; System.out.printf("%d\n", i);
		 * 
		 * Scanner in = new Scanner(System.in); // scanner객체 생성 String bookName =
		 * in.nextLine(); // 키보드로 입력된 문자열데이터 대입 System.out.printf("제목 : %s", bookName);
		 */

	}

}