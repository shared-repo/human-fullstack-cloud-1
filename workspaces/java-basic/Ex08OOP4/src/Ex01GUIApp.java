import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ex01GUIApp {	

	public static void main(String[] args) {
		
		JFrame frame = new JFrame(); // 윈도우 인스턴스 만들기
		
		frame.setLayout(null); // 컴포넌트 배치 방법 설정 ( 절대 좌표로 설정 )
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 윈도우가 닫힐 때 프로그램 종료
		frame.setSize(500, 400); // 윈도우 크기 설정
		frame.setTitle("자바 GUI 연습"); // 캡션바에 표시할 내용 설정
		
		JButton btn = new JButton("클릭해 주세요"); // 버튼 만들기
		btn.setSize(140, 50);	// 버튼 크기 설정
		btn.setLocation(20, 150);
		ButtonClickHandler handler = new ButtonClickHandler();
		btn.addActionListener(handler); // 버튼 클릭 처리기 등록
		frame.add(btn); // 윈도우에 버튼 부착
		
		JButton btn2 = new JButton("클릭해 주세요"); // 버튼 만들기
		btn2.setSize(140, 50);	// 버튼 크기 설정
		btn2.setLocation(180, 150);		
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "클릭해 주셔서 감사합니다 2.");
			}
		}); // 버튼 클릭 처리기 등록
		frame.add(btn2); // 윈도우에 버튼 부착
		
		JButton btn3 = new JButton("클릭해 주세요"); // 버튼 만들기
		btn3.setSize(140, 50);	// 버튼 크기 설정
		btn3.setLocation(340, 150);		
		btn3.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "클릭해 주셔서 감사합니다 3.");
		}); // 버튼 클릭 처리기 등록
		frame.add(btn3); // 윈도우에 버튼 부착
		
		frame.setVisible(true); // 윈도우를 화면에 표시
	}
}

// ActionListener : Button 클릭에 대해 호출할 메서드 규격
class ButtonClickHandler implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, "클릭해 주셔서 감사합니다."); // javascript의 alert
	}
}









