class TheBase {
	private int no;
	String name = "test";
	public void pm() {
		System.out.println("TheBase.pm()이 호출되었습니다.");
	}
}
class TheDerived1 extends TheBase { // TheBase의 모든 멤버를 자동으로 포함
	// 상속 받은 클래스는 새로운 멤버를 추가하거나 기존 멤버를 변경해야 합니다.
	String desc;
	public void cm() {
		name = "John Doe"; // 자식 클래스에서 부모의 멤버 사용 가능
		System.out.println(name);
		// no = 100; // 오류 : 부모 클래스의 private 멤버는 자식 클래스에서 사용할 수 없습니다.		
		System.out.println("TheDerived1.cm()이 호출되었습니다.");
	}
}
public class AppA { // public class의 이름과 파일 이름은 일치해야 합니다.
	public static void main(String[] args) {
		TheBase b1 = new TheBase();
		b1.pm();
		// b1.cm(); // 오류 : 부모 클래스는 자식의 멤버를 사용할 수 없습니다.
		
		TheDerived1 d1 = new TheDerived1();
		d1.pm(); // TheBase에서 상속한 멤버 사용
		d1.cm();
	}
}