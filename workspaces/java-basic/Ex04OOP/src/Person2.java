
public class Person2 { 
	
	String name;
	String phone;
	String email;
	int age;
	
	// 생성자 메서드 
	// 1. 클래스 이름과 같은 메서드
	// 2. 결과형이 없음 ( void 표시도 없어야 합니다 ) 
	// 3. 인스턴스를 만들 때 ( new 할 때 ) 자동으로 호출 -> 직접 명시적으로 호출할 수 없습니다.
	// 4. 오버로딩 가능 ( 여러 개의 생성자 메서드 만들 수 있습니다 )
	
	Person2() {
		System.out.println("전달인자 없는 생성자 메서드");
	}
	
}
