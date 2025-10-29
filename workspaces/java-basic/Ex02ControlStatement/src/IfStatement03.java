
public class IfStatement03 {

	public static void main(String[] args) {
		
		// 계산기 만들기
		// 1. 숫자 입력 -> 변수에 저장
		// 2. 연산자 ( 문자 / +,-,*,/ ) 입력 -> 변수에 저장 // String
		// 3. 숫자 입력 -> 변수에 저장
		
		// 4. 연산자에 따라 연산 수행 -> 변수에 저장 
		//    '+' 입력 -> +연산, 
		//    '-' 입력 -> -연산, 
		//    ...
		
		// 5. 결과 출력
		
		//
		//1. 
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		System.out.print("첫 번째 숫자 : ");
		int operand1 = scanner.nextInt(); // 숫자만 읽기 ( 버퍼에 엔터가 남아있음 ) 
		// scanner.nextLine(); // 입력 버퍼 비우기
		
		System.out.print("연산자 (+,-,*,/,%) : ");
		// String op = scanner.nextLine(); 	// 문자열 입력 (엔터 입력 O)
		String op = scanner.next(); 		// 문자열 입력 (엔터는 입력 X)
		
		System.out.print("두 번째 숫자 : ");
		int operand2 = scanner.nextInt();
		// scanner.nextLine(); // 입력 버퍼 비우기
		
		// System.out.printf("%d %s %d", operand1, op, operand2);
		
		boolean valid = true;
		double result = 0; // 초기화 : 변수를 만들면서 특정 값을 저장하는 것		
		// if (op == "+") { 	// 문자열을 비교할 때에는 == 연산자를 사용할 수 없음
		if (op.equals("+")) { 	// 문자열을 비교할 때에는 equals 사용  
			result = operand1 + operand2;
		} else if (op.equals("-")) {
			result = operand1 - operand2;
		} else if (op.equals("*")) {
			result = operand1 * operand2;
		} else if (op.equals("/")) {
			result = (double)operand1 / operand2;
		} else if (op.equals("%")) {
			result = (double)operand1 % operand2;
		} else {
			System.out.println("에러");
			valid = false;
		}
		
		if (valid) {
			System.out.println(operand1 + " " + op + " " + operand2 + " " + result);
			System.out.printf("%d %s %d = %f", operand1, op, operand2, result);
		}

	}

}

















