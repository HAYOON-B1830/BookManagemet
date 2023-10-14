package member;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUp extends JDialog {
	private JPanel signUpPanel = new JPanel(new GridLayout(11, 0));
	private JTextField idText = new JTextField("ID");
	private JPasswordField pwText = new JPasswordField();
	private JPasswordField pwCheckText = new JPasswordField();
	private JTextField nameText = new JTextField("이름");
	private JTextField phoneNumberText = new JTextField("핸드폰 번호");
	private JButton signUpbtn = new JButton("회원가입");
	private JLabel pwlabel = new JLabel("PW");
	private JLabel pwChecklabel = new JLabel("PW 확인");

	private boolean membershipProgress = false;

	public SignUp() {

		this.setTitle("회원가입");

		this.signUpPanel.add(idText);
		this.signUpPanel.add(pwlabel);
		this.signUpPanel.add(pwText);
		this.signUpPanel.add(pwChecklabel);
		this.signUpPanel.add(pwCheckText);
		this.signUpPanel.add(nameText);
		this.signUpPanel.add(phoneNumberText);
		this.signUpPanel.add(signUpbtn);

		this.setContentPane(signUpPanel);
		this.setSize(300, 500);
		this.setLocationRelativeTo(null);

		FocusEvent();
		checkValue();
	}

	private void FocusEvent() {
		idText.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if (idText.getText().trim().length() == 0) {
					idText.setText("ID");
				}
			}

			public void focusGained(FocusEvent e) {
				if (idText.getText().trim().equals("ID")) {
					idText.setText("");
				}
			}
		});

		nameText.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if (nameText.getText().trim().length() == 0) {
					nameText.setText("이름");
				}
			}

			public void focusGained(FocusEvent e) {
				if (nameText.getText().trim().equals("이름")) {
					nameText.setText("");
				}
			}
		});

		phoneNumberText.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				if (phoneNumberText.getText().trim().length() == 0) {
					phoneNumberText.setText("핸드폰 번호");
				}
			}

			public void focusGained(FocusEvent e) {
				if (phoneNumberText.getText().trim().equals("핸드폰 번호")) {
					phoneNumberText.setText("");
				}
			}
		});
	}

	private void checkValue() {
		signUpbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (idText.getText().trim().length() == 0 || idText.getText().trim().equals("아이디")) {
					JOptionPane.showMessageDialog(null, "ID를 입력해 주세요.", "ID 입력", JOptionPane.WARNING_MESSAGE);
					idText.grabFocus();
					return;
				}

				if (pwText.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "PW를 입력해 주세요.", "PW 입력", JOptionPane.WARNING_MESSAGE);
					pwText.grabFocus();
					return;
				}

				if (pwCheckText.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "PW 확인을 입력해 주세요.", "PW 확인 입력", JOptionPane.WARNING_MESSAGE);
					pwCheckText.grabFocus();
					return;
				}

				if (!(pwText.getText().trim().equals(pwCheckText.getText().trim()))) {
					JOptionPane.showMessageDialog(null, "PW가 같지 않습니다.!!", "PW 확인", JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (nameText.getText().trim().length() == 0 || nameText.getText().trim().equals("이름")) {
					JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.", "이름 입력", JOptionPane.WARNING_MESSAGE);
					nameText.grabFocus();
					return;
				}

				if (phoneNumberText.getText().trim().length() == 0
						|| phoneNumberText.getText().trim().equals("핸드폰 번호")) {
					JOptionPane.showMessageDialog(null, "핸드폰 번호를 입력해 주세요.", "핸드폰 번호 입력", JOptionPane.WARNING_MESSAGE);
					phoneNumberText.grabFocus();
					return;
				}

				// 아이디랑 패스워드 구분하기 위해 사용
				String txt = idText.getText() + "|" + pwText.getText();
				txt += "\n";

				// c드라이브에 book_member폴더, member.txt파일
				String fileName = "C:\\book_member\\member.txt";

				try {
					BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));

					fw.write(txt);
					fw.flush();

					fw.close();

				} catch (Exception errmsg) {
					errmsg.printStackTrace();
				}

				membershipProgress = true;

				JOptionPane.showMessageDialog(null, "회원 가입이 완료 되었습니다.", "회원 가입 완료.", JOptionPane.WARNING_MESSAGE);

				setVisible(false);
			}
		});
	}

	public String getIdText() {
		return this.idText.getText().trim();
	}

	public String getPwText() {
		return this.pwText.getText().trim();
	}

	public String getPwCheckText() {
		return this.pwCheckText.getText().trim();
	}

	public String getNameText() {
		return this.nameText.getText().trim();
	}

	public String getPhoneNumberText() {
		return this.phoneNumberText.getText().trim();
	}

	public boolean memberCheck() {
		return membershipProgress;
	}

}
