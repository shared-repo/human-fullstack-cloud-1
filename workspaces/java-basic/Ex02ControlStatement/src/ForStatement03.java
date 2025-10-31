
public class ForStatement03 {

	public static void main(String[] args) {
		
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		
		// 1 ~ 9 범위의 숫자 입력
		// 입력된 단의 구구단 출력
		// 예) 5 입력
		// 5 * 1 = 5
		// 5 * 2 = 10
		// ...
		// 5 * 9 = 45
		System.out.print("출력할 단 : ");
		int dan = scanner.nextInt();
		
		for (int i = 1; i < 10; i++) {
			System.out.printf("%d * %d = %2d\n", dan, i, dan * i);
			// %2d : 데이터를 2자리에 출력 (오른쪽정렬), 왼쪽정렬을 %-2d
		}
		
		///////////////////////////////////////

		// 1단 ~ 9단 모두 출력
		// 1 * 1 = 1   2 * 1 = 2   ... 9 * 1 = 9
		// 1 * 2 = 2   2 * 2 = 4   ... 9 * 2 = 18
		// ...
		// 1 * 9 = 9   2 * 9 = 18  ... 9 * 9 = 81
		for (int y = 1; y < 10; y++) {
			for (int x = 1; x < 10; x++) {
				System.out.printf("%d x %d = %2d  ", x, y, x*y);
			}
			System.out.println();
		}
		
		

	}

}








