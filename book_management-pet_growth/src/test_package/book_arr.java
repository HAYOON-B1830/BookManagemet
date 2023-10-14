package test_package;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*도서관리 프로그램
 * 도서정보: 도서번호, 도서제목,저자,출판사 *****수정사항: 출판년도 제외
 * 도서정보를 갖는 클래스 생성 후 Arraylist에 저장
 * add 도서추가기능
 * search 도서검색기능(도서제목으로 검색))
 * remove 도서삭제기능(삭제할 도서제목을 입력)
 * fix 도서수정기능(도서제목을 입력받아 도서제목,저자, 출판사를 수정)
 * print 전체도서정보출력기능
 * 
 * 
 * */

class Book {
	ArrayList<Book> list = new ArrayList<>();
	private int num;
	private String title;
	private String author;
	private String publish_house;
	// private int publish_year;

	public Book() {
	}

	public Book(int num, String title, String author, String publish_house) {
		this.num = num;
		this.title = title;
		this.author = author;
		this.publish_house = publish_house;
	}

	// book add
	public void add() {
		Scanner scan = new Scanner(System.in);

		System.out.println("도서 번호 : ");
		num = scan.nextInt();
		System.out.println("도서 제목 : "); // *****문제: 제목 띄어쓰기 하면 다음입력으로 입력됨
		title = scan.next();
		System.out.println("도서 작가 : ");
		author = scan.next();
		System.out.println("도서 출판사 : ");
		publish_house = scan.next();

		Book book = new Book(num, title, author, publish_house);
		list.add(book);
	}

	// book search
	public void search() {
		Scanner scan = new Scanner(System.in);
		System.out.println("도서 번호 : ");
		num = scan.nextInt();
		boolean check = false;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getNum() == num) {
				System.out.println(list.get(i).info());
				check = true;
				break;
			}
		}
		if (!check) {
			System.out.println("해당 도서가 없습니다.");
		}
	}

	// book remove
	public void remove() {
		Scanner scan = new Scanner(System.in);
		System.out.println("도서 번호 : ");
		num = scan.nextInt();
		boolean check = false;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getNum() == num) {
				list.remove(i);
				System.out.println(num + "번호를 삭제");
				check = true;
				break;
			}
		}
		if (!check) {
			System.out.println("해당 도서가 없습니다.");
		}
	}

	// book fix
	public void fix() {
		Scanner scan = new Scanner(System.in);
		System.out.println("도서 번호 : ");
		num = scan.nextInt();
		boolean check = false;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getNum() == num) {
				System.out.println("수정할 도서제목 : ");
				String iTitle = scan.next();
				System.out.println("수정할 작가 : ");
				String iAuthor = scan.next();
				System.out.println("수정할 출판사 : ");
				String iPublish_house = scan.next();

				list.set(i, new Book(num, iTitle, iAuthor, iPublish_house));
				check = true;
				break;
			}
		}
		if (!check) {
			System.out.println("해당 도서가 없습니다");
		}
	}

	// full output print
	public void print() {
		Iterator<Book> it = list.iterator();
		while (it.hasNext()) {
			Book b = it.next();
			System.out.println(b.info());
		}
	}

	public String info() {
		return "도서 번호 : " + num + "/도서 제목 : " + title + "/작가 : " + author + "/출판사 : " + publish_house;
	}

	public int getNum() {
		return num;
	}
}

public class book_arr {

	public static void main(String[] args) {
		System.out.println("출력할 화면");
		Scanner scan = new Scanner(System.in);
		Book book = new Book();

		int f_num = 0;
		while (f_num != 6) {
			System.out.println("1.도서목록 2.도서추가 3.도서검색 4.도서수정 5.도서삭제 6.종료");
			f_num = scan.nextInt();
			switch (f_num) {
			case 1:
				System.out.println("전체 도서목록 출력");
				book.print();
				break;
			case 2:
				System.out.println("도서추가 : 도서번호, 제목, 작가, 출판사 입력");
				book.add();
				break;
			case 3:
				System.out.println("도서검색 : 해당 도서번호 입력");
				book.search();
				break;
			case 4:
				System.out.println("도서수정 : 도서번호, 제목, 작가, 출판사 입력");
				book.fix();
				break;
			case 5:
				System.out.println("도서삭제 : 해당 도서번호 입력");
				book.remove();
				break;
			case 6:
				System.out.println("종료");
				break;
			default:
				System.out.println("재입력 해주세요");
			}
		}
	}
}
