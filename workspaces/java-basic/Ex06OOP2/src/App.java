import shape.TheRectangle;

public class App {

	public static void main(String[] args) {
		
		ThePoint point1 = new ThePoint(); // 전달인자 없는 생성자 메서드 호출
		point1.x = 100;
		point1.y = 200;
		String info = point1.info();
		System.out.println(info);
		
		ThePoint point2 = new ThePoint(10, 20); // 전달인자 있는 생성자 메서드 호출
		System.out.println( point2.info() );
		
		ThePoint2 point3 = new ThePoint2(); // 전달인자 없는 생성자 메서드 호출
//		point3.x = 50; // 오류 : private 멤버는 외부에서 사용할 수 없음
//		point3.y = 70; // 오류 : private 멤버는 외부에서 사용할 수 없음
		point3.setX(50);
		point3.setY(70);
		System.out.println( point3.info() );
		
		ThePoint2.shared = 100; // static member는 클래스 이름으로 접근
		System.out.println(ThePoint2.shared);
		System.out.println(point3.shared); // static member는 공유하기 때문에 다른 곳에서 변경한 값이 적용됩니다.
		
		
		// shape.TheRectangle r = new shape.TheRectangle();
		TheRectangle r = new TheRectangle(); // import를 사용하면 package 이름 생략 가능
		// r.leftX = 10; // 오류 : 다른 패키지에 있는 클래스의 default 멤버 사용할 수 없음
		

	}

}
