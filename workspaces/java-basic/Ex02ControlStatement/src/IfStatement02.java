
public class IfStatement02 {

	public static void main(String[] args) {
		
		// 점수 처리기 만들기
		// 1. 변수를 만들고 사용자 입력을 받아서 변수에 저장 * 3
		// 2. 세 변수에 저장된 값을 더해서 다른 변수에 저장
		// 3. 합을 3으로 나누어서 평균을 계산하고 다른 변수에 저장
		// 4. 합과 평균을 출력
		
		// 5. 평균을 기준으로 등급을 계산하고 계산된 등급을 출력
		//    90 ~ 100 : A
		//    80 ~  90 : B
		//    70 ~  80 : C
		//    60 ~  70 : D
		//     0 ~  60 : F		
		
		///////////////////////////////////
		
		//1.
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		System.out.println("첫 번째 점수를 입력하세요 (0 ~ 100) : ");
		int score1 = scanner.nextInt();
		
		System.out.println("두 번째 점수를 입력하세요 (0 ~ 100) : ");
		int score2 = scanner.nextInt();
		
		System.out.println("세 번째 점수를 입력하세요 (0 ~ 100) : ");
		int score3 = scanner.nextInt();
		
		// System.out.println(score1 + "/" + score2 + "/" + score3);
		
		//2. 
		int total = score1 + score2 + score3;
		
		// System.out.println(total);
		
		//3. 
		double average = total / 3.;
		
		System.out.println(average);
		
		// 4.
		char grade;
		if ( 90 <= average ) {
			// System.out.println("등급 : A");
			grade = 'A';
		} else if (average >= 80) {
			// System.out.println("등급 : B");
			grade = 'B';
		} else if (average >= 70) {
			// System.out.println("등급 : C");
			grade = 'C';
		} else if (average >= 60) {
			// System.out.println("등급 : D");
			grade = 'D';
		} else {
			// System.out.println("등급 : F");
			grade = 'F';
		}
				
		System.out.printf("등급 : %c", grade);
		// 출력 서식 ( printf과 같은 메서드에서 사용 )
		// %s : String 형식 데이터
		// %c : char
		// %d : 정수 계열
		// %f : 실수 계열
		
	}

}













