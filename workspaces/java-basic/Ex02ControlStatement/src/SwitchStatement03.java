
public class SwitchStatement03 {

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
		
		// System.out.println(average);
		System.out.printf("\"평균\" : %.2f\n", average); // %.2f : 소숫점 이하 2자리까지 출력
		// 문자열 안에서 특수한 문자를 표기하는 방법 (escape sequence)
		// \n : enter
		// \t : tab
		// \" : "
		// \' : '
		// \r : home
		// \b : backspace
		// \f : form feed
				
		// 4.
		char grade = '_';
		boolean valid = true;
//		switch ((int)average / 10) {
//		case 9, 10:	grade = 'A'; break;
//		case 8: grade = 'B'; break;
//		case 7: grade = 'C'; break;
//		case 6: grade = 'D'; break;
//		case 5, 4, 3, 2, 1, 0: grade = 'F'; break;
//		default: valid = false;
//		}		
		
		switch ((int)average / 10) {
		case 9, 10 -> grade = 'A';
		case 8 -> grade = 'B';
		case 7 -> grade = 'C';
		case 6 -> grade = 'D';
		case 5, 4, 3, 2, 1, 0 -> grade = 'F';
		default -> valid = false;
		}		
		
		if (valid) {
			System.out.printf("등급 : %c", grade);
		}
		// 출력 서식 ( printf과 같은 메서드에서 사용 )
		// %s : String 형식 데이터
		// %c : char
		// %d : 정수 계열
		// %f : 실수 계열
		
	}

}













