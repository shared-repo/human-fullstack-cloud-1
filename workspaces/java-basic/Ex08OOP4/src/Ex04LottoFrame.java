import java.awt.Dimension;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Ex04LottoFrame extends JFrame {

	public Ex04LottoFrame() {
		initUI();
	}
	
	private JButton btnSelect;
	private JTextField[] textFields = new JTextField[6];
	private Lotto lotto = new Lotto();
	
	private void initUI() {
		super.setLayout(null); // 절대 좌표 및 절대 크기를 사용해서 컴포넌트 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 윈도우 닫기 -> 프로그램 종료
		setSize(new Dimension(400, 300));
		setTitle("로또");
		setResizable(false); // 윈도우 크기 변경 불가능 설정
		
		btnSelect = new JButton("당첨 예상 번호 뽑기");
		btnSelect.setSize(350, 50);
		btnSelect.setLocation(20, 50);
		btnSelect.addActionListener(e -> {
			int[] numbers = lotto.selectWinningNumbers();
			for (int i = 0; i < numbers.length; i++) {
				textFields[i].setText(String.valueOf(numbers[i]));
			}
		});
		add(btnSelect);
		
		for (int i = 0; i < textFields.length; i++) {
			textFields[i] = new JTextField();
			textFields[i].setSize(50, 50);
			textFields[i].setLocation(20 + i * 60, 130);
			textFields[i].setEditable(false);
			textFields[i].setHorizontalAlignment(JTextField.CENTER);
			add(textFields[i]);
		}
	}
	
	public static void main(String[] args) {
		Ex04LottoFrame frame = new Ex04LottoFrame();
		frame.setVisible(true);

	}

}
