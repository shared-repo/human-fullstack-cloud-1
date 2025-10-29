
public class IfStatement01 {

	public static void main(String[] args) {
		
//		int a = 0;
		
		double a = Math.random(); // 0 ~ 1 범위의 난수 발생 (0은 포함, 1은 포함 X)
		a = a - 0.5; // -0.5 ~ 0.5
		
//		java.util.Scanner scanner = new java.util.Scanner(System.in);
//		System.out.print("정수를 입력하세요 : ");
//		int a = scanner.nextInt(); // 정수 입력 받는 명령
		
		if (a > 0) {
			System.out.println("a는 0보다 큽니다.");
		}
		
		System.out.println("===========================");
		
		if (a > 0) {
			System.out.println("a는 0보다 큽니다.");
		} else {
			System.out.println("a는 0보다 작습니다.");
		}
		
		System.out.println("===========================");
		
		if (a > 0) {
			System.out.println("a는 0보다 큽니다.");
		} else if (a < 0) {
			System.out.println("a는 0보다 작습니다.");
		} else { // a == 0
			System.out.println("a는 0입니다.");
		}

	}

}
