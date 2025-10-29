
public class DataTypeAndVariable {

	public static void main(String[] args) {
		
		// 1. 변수 만들기 ( 변수 선언 )
		int a; // 메모리에 4byte 정수형 공간을 만들고 이름을 a로 지정
		a = 100; // a 변수에 100 저장
		a = a * 10; // a 변수에 a * 10 저장
		System.out.println("a에 저장된 값 : " + a);
		
		// 2. 변수 만들기 2
		double b; // 메모리에 8byte 실수형 공간을 만들고 이름을 b로 지정
		b = 12.34; // b에 12.34 저장
		System.out.println("b에 저장된 값 : " + b);
		
		// 3. 한글 이름의 변수 만들기
		int 나이;
		나이 = 32;
		System.out.println("나이 : " + 나이);
		
		/////////////////////////////////////////
		
		// 형변환
		
		int c; // 4byte 정수형 변수
		// c = 12.23; // 오류 : 실수형 데이터를 정수형 변수에 저장할 수 없음
		c = (int)12.23; // 명시적 형변환 : 12.23을 정수형으로 변경
		System.out.println(c);
		
		double d;
		d = 100; // 암시적 형변환 ( 정수 -> 부동소수점 )
		System.out.println(d);
		
		// 문자형
		// 문자열 : 0개 이상의 문자 집합 : "..." : String 자료형
		// 문자 : 1개의 문자 : 'A' : char 자료형
		
		char ch = 'a'; // 'a'에 해당하는 코드 값 (97) 저장
		System.out.println(ch);
		System.out.println( (int)ch ); // 문자를 숫자로 명시적 형변환
		
		///////
		
		int e = 2147483647; // 4byte 정수 자료형이 표현할 수 있는 한계 값
		int f = e + 2;
		System.out.println(f);
		
		

	}

}
