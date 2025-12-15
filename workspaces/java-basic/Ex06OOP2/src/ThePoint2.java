
public class ThePoint2 {
	
	public static int shared;

	private int x;
	private int y;

	public int getX() {
		return x;
	}
	public void setX(int x) {
		if (x < 0) {
			return;
		}
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	// 생성자 메서드 : 클래스 이름과 같고 결과형이 없음 (void도 안됨)
	public ThePoint2() {
		System.out.println("전달인자 없는 생성자 메서드");
	}
	public ThePoint2(int x, int y) {
		System.out.println("전달인자 있는 생성자 메서드");
		this.x = x;
		this.y = y;
	}
	
	public String info() {
		// String.format : javascript의 backtick string (템플릿 리터럴)과 같은 역할
		return String.format("[X : %d][Y : %d]", x, y);
	}
	
}
