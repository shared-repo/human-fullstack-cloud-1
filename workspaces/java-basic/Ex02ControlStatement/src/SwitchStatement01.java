
public class SwitchStatement01 {

	public static void main(String[] args) {

		// 계산기 만들기
		// 1. 숫자 입력 -> 변수에 저장
		// 2. 연산자 ( 문자 / +,-,*,/ ) 입력 -> 변수에 저장 // String
		// 3. 숫자 입력 -> 변수에 저장

		// 4. 연산자에 따라 연산 수행 -> 변수에 저장
		// '+' 입력 -> +연산,
		// '-' 입력 -> -연산,
		// ...

		// 5. 결과 출력

		//
		// 1.
		java.util.Scanner scanner = new java.util.Scanner(System.in);

		System.out.print("첫 번째 숫자 : ");
		int operand1 = scanner.nextInt(); // 숫자만 읽기 ( 버퍼에 엔터가 남아있음 )
		scanner.nextLine(); // 입력 버퍼 비우기

		System.out.print("연산자 (+,-,*,/,%) : ");
		String op = scanner.nextLine();

		System.out.print("두 번째 숫자 : ");
		int operand2 = scanner.nextInt();
		scanner.nextLine(); // 입력 버퍼 비우기

		// System.out.printf("%d %s %d", operand1, op, operand2);

		double result = 0; // 초기화 : 변수를 만들면서 특정 값을 저장하는 것
		boolean valid = true;
		switch (op) { 
		case "+":
			result = operand1 + operand2;
			break;
		case "-":
			result = operand1 - operand2;
			break;
		case "*":
			result = operand1 * operand2;
			break;
		case "/":
			result = (double) operand1 / operand2;
			break;
		case "%":
			result = operand1 % operand2; // % : 나눗셈의 나머지를 반환하는 연산
			break;
		default:
			System.out.println("에러");
			valid = false;
		}

		if (valid) {
			System.out.printf("%d %s %d = %f", operand1, op, operand2, result);
		}

	}

}
