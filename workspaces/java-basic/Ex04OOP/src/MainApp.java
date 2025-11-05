
public class MainApp {

	public static void main(String[] args) {
				
		//1. Person 클래스 변수 만들기 및 사용
//		// int a; // int : 자료형
//		Person person; // 참조 변수 만들기
//		person = new Person(); // 인스턴스 만들기 + 인스턴스의 주소를 person 변수에 저장
//		
//		person.age = 35;	// person 인스턴스의 age 필드(변수)에 값 저장
//		person.name = "John Doe";
//		person.email = "johndoe@example.com";
//		person.phone = "010-6589-4123";
//		
//		System.out.printf("[%s][%s][%s][%d]\n", 
//				person.name, person.email, person.phone, person.age);
		
		/////////////////////////
		
		// 2. Utility 클래스 사용 ( 메서드 연습 )
//		Utility utility = new Utility();
//		utility.drawBox();
//		utility.drawBox();
//		utility.drawBox();
		
		
		// 3. Utility 클래스 사용 2 ( 메서드 연습 2)
//		int width = 20;		
//		int height = 10;
//		String s = "$";
//		utility.drawBox2(width, height, s);
		
		// 4. Utility 클래스 사용 3 ( 메서드 연습 3 )
//		int result = utility.sum(100, 50);		
//		System.out.println(result);
//		
//		int result2 = utility.sum(100, 50, 200);		
//		System.out.println(result2);
//		
//		int result3 = utility.sumAll(100, 50, 200, 60, 333, 45);		
//		System.out.println(result3);
		
		// 5
		// . 2-1. Lotto 변수(인스턴스) 만들기
//		Lotto lotto = new Lotto();
//		int[] numbers = lotto.selectWinningNumbers();
//		// . 2-4. 결과 출력
//		lotto.printNumbers(numbers);
		
//		// 6-1. 생성자 메서드 1
//		Person2 p2 = new Person2();
//		System.out.println(p2.name);
//		
//		// 6-2. 생성자 메서드 2
//		String name = "John Doe";
//		Person2 p3 = new Person2(name, "010-6923-7712", "johndoe@example.com", 35);
//		System.out.printf("[%s][%s][%s][%d]\n", p3.name, p3.email, p3.phone, p3.age);
//		
//		// 6-3. 생성자 메서드 3
//		ConstructorTest ct = new ConstructorTest();
		
		
		// 7. 멤버 접근 제어
		Person3 p7 = new Person3();
		// p7.name = "홍길동"; // 오류 : private 멤버는 외부에서 접근 할 수 없음
		p7.setName("홍길동");
		p7.setEmail("hkd@example.com");
		p7.setPhone("010-6523-7789");
		p7.setAge(30);
		
		System.out.printf("[%s][%s][%s][%d]\n",
				p7.getName(),
				p7.getEmail(),
				p7.getPhone(),
				p7.getAge());
		
		// 8. static
		System.out.println(StaticAndFinal.sno); // static 초기화 구문에서 저장한 값 사용
		
		StaticAndFinal saf1 = new StaticAndFinal();
		saf1.ino = 1;
		saf1.sno = 1;
		StaticAndFinal saf2 = new StaticAndFinal();
		saf2.ino = 2;
		saf2.sno = 2;
		
		System.out.println(saf1.ino + "/" + saf1.sno); // 1 / 1
		System.out.println(saf2.ino + "/" + saf2.sno); // 2 / 2
		
		// static 멤버는 참조 변수로 접근하지 않고 클래스 이름으로 접근합니다.
		System.out.println(saf1.getSno());
		System.out.println(StaticAndFinal.getSno());
		
		// static 사용 사례
		double f = Math.random();
		System.out.println(f); // 여기서 out은 static 멤버
	}
	

}












