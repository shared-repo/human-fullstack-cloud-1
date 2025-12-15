
class TheBase5 { // 상속 불가능 클래스
	final int x = 10; // 값을 변경할 수 없는 상수
	final void bm() { // 재정의 불가능 메서드
		// x = 20; // 오류 : final 값 변경 불가능
	}
}
class TheDerived5 extends TheBase5 {	
	// @Override void bm() {} // 오류 : final 메서드는 재정의 할 수 없습니다.
}

final class TheBase52 { // 상속 불가능 클래스
}
// class TheDerived52 extends TheBase52 {} // 오류 : final 클래스는 상속할 수 없습니다.

public class AppE {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
