
public class Array01 {

	public static void main(String[] args) {
		
//		String str = new String("Hello, String"); 	// 직접 공간을 만들고 사용
//		String str2 = "Hello, String";				// 자동으로 공간을 만들고 사용
		
		// 1. 배열 만들기 + 사용
		// int[] ar = new int[10]; // int형 데이터 10개를 모아서 저장하는 배열 생성
		int[] ar;
		ar = new int[10];
		for (int i = 0; i < 10; i++) {
			ar[i] = (int)(Math.random() * 900) + 100; // 0~1 -> 0~900 -> 100~1000
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(ar[i]);
		}
		
		// 2. 배열 초기화 1
		int[] ar2 = new int[] { 1, 2, 3, 4, 5 };
		for (int i = 0; i < 5; i++) {
			System.out.println(ar2[i]);
		}
		
		// 3. 배열 초기화 2
		int[] ar3 = { 1, 2, 3, 4, 5 };
		for (int i = 0; i < 5; i++) {
			System.out.println(ar3[i]);
		}
		
		// 4. 배열 각 요소의 초깃값
		int[] ar4 = new int[5];
		for (int i = 0; i < 5; i++) {
			System.out.println(ar4[i]);
		}
		
		// 5. 배열의 속성 : 배열.length
		int[] ar5 = new int[5];
		for (int i = 0; i < ar5.length; i++) { // 배열.length : 배열에 포함된 요소 갯수
			ar5[i] = (int)(Math.random() * 900) + 100; // 0~1 -> 0~900 -> 100~1000
		}
		for (int i = 0; i < ar5.length; i++) {
			System.out.println(ar5[i]);
		}
		
		// 6. enhanced for
		for (int n : ar5) { // ar5 배열에서 처음부터 끝까지 순서대로 값을 하나씩 꺼내서 n에 저장
			System.out.println(n);
		}
		
		// ref. 참조 변수의 초기화
		int[] ar7 = null; // null : 참조변수가 가르키는 인스턴스가 없음
		System.out.println(ar7[0]); // null로 초기화 되면 컴파일 오류는 사라지지만 실행 오류 발생
	
		
		
	}

}















