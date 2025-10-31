
public class ForStatement02 {

	public static void main(String[] args) {
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		// 1. 숫자 사용자 입력
		System.out.println("숫자를 입력하세요 : ");
		int n = scanner.nextInt();
		// 2. 1부터 사용자가 입력한 숫자까지 순서대로 출력
		// 예) 사용자가 10을 입력하면 1, 2, 3...10 까지 순서대로 출력
		for (int i = 1; i <= n; i++) {
			System.out.printf("[ROUND %d]\n", i);
		}
		
		
		// 1. 숫자 사용자 입력
		System.out.println("숫자를 입력하세요 : ");
		int n2 = scanner.nextInt();
		// 2. 1부터 사용자가 입력한 숫자까지의 합을 계산
		int sum = 0;
		for (int i = 1; i <= n2; i++) {			
			sum += i; // sum = sum + i;
		}
		// 3. 계산된 합을 출력
		// 예) 사용자가 10을 입력하면 1 + 2 + 3 + 4 + ... 10를 계산하고 출력
		System.out.printf("SUM : %d\n", sum);

	}

}
