
public class ForStatement04 {

	public static void main(String[] args) {
		
		//1. 
		for (int r = 0; r < 10; r++) {
			
			for (int c = 0; c < r + 1; c++) {
				System.out.print("*");
			}
			System.out.println();
			
		}
		
		//2.
		for (int r = 0; r < 10; r++) {
			// 공백 출력 반복
			for (int c = 0; c < 10 - r - 1; c++) {
				System.out.print(" ");
			}
			// * 출력 반복
			for (int c = 0; c < r + 1; c++) {
				System.out.print("*");
			}
			System.out.println();
			
		}
		
		// 3. 
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		System.out.println();
		
		// 4. 
		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				if (row == 0 || row == 9 || col == 0 || col == 9) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		

	}

}









