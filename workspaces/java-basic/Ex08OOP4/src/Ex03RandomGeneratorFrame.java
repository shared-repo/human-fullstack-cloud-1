import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Ex03RandomGeneratorFrame extends JFrame {
	
	private JButton btnGenerate;
	private JTextField txtMessage;
	
	public Ex03RandomGeneratorFrame() {
		initUI();
	}
	
	private void initUI() {
		super.setLayout(null); // 절대 좌표 및 절대 크기를 사용해서 컴포넌트 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 윈도우 닫기 -> 프로그램 종료
		// setSize(400, 300);
		setSize(new Dimension(400, 300));
		setTitle("난수 발생기");
		setResizable(false); // 윈도우 크기 변경 불가능 설정
		
		btnGenerate = new JButton("난수 만들기");
		btnGenerate.setSize(200, 50);
		btnGenerate.setLocation(95, 50);
//		btnGenerate.addActionListener(new ActionListener() {			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				int n = (int)Math.floor(Math.random() * 900 + 100);
//				txtMessage.setText(String.valueOf(n)); // String.valueOf : 정수 -> 문자열
//			}
//		});
		btnGenerate.addActionListener(e -> {
			int n = (int)Math.floor(Math.random() * 900 + 100);
			txtMessage.setText(String.valueOf(n)); // String.valueOf : 정수 -> 문자열			
		});
		add(btnGenerate);
		
		txtMessage = new JTextField();
		txtMessage.setSize(200, 50);
		txtMessage.setLocation(95, 120);
		txtMessage.setEditable(false);
		txtMessage.setHorizontalAlignment(JTextField.CENTER);
		Font f = new Font(null, Font.BOLD, 20);
		txtMessage.setFont(f);
		
		add(txtMessage);
	}

	public static void main(String[] args) {
		
		Ex03RandomGeneratorFrame frame = new Ex03RandomGeneratorFrame();
		frame.setVisible(true);

	}

}
