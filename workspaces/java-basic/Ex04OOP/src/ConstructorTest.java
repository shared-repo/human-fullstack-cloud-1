
public class ConstructorTest {
	
	String s;
	int i;

	// 기본생성자 : 생성자 메서드를 만들지 않으면 자동으로 전달인자 없는 생성자 메서드가 만들어집니다.
	ConstructorTest() {
		this("test", 20); // 같은 클래스에 다른 생성자 메서드 호출하는 구문 (순환 호출은 안됩니다)
		System.out.println("기본 생성자");
	}

	// 다른 생성자 메서드를 만들면 기본 생성자 메서드가 자동으로 만들어지지 않습니다.
	ConstructorTest(String s, int i) {
		// this(); // 같은 클래스에 다른 생성자 메서드 호출하는 구문 (순환 호출은 안됩니다)
		System.out.println("추가 생성자");
		this.s = s;
		this.i = i;
	}
	

}
