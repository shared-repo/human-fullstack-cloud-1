
public class ThePoint {

	int x;
	int y;
	
	// 생성자 메서드 : 클래스 이름과 같고 결과형이 없음 (void도 안됨)
	ThePoint() {
		System.out.println("전달인자 없는 생성자 메서드");
	}
	ThePoint(int x, int y) {
		System.out.println("전달인자 있는 생성자 메서드");
		this.x = x;
		this.y = y;
	}
	
	String info() {
		// String.format : javascript의 backtick string (템플릿 리터럴)과 같은 역할
		return String.format("[X : %d][Y : %d]", x, y);
	}
	
}
