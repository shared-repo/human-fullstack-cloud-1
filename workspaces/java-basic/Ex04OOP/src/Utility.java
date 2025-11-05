
public class Utility {
	
	void drawBox() {
		
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
	
	void drawBox2(int width, int height, String s) {		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (row == 0 || row == height - 1 || 
					col == 0 || col == width - 1) {
					System.out.print(s);
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
	}
	
	
	// 메서드 오버로딩 :
	// 이름이 같아도 전달인자의 갯수 또는 자료형이 다르면 다른 메서드
	int sum(int a, int b) {		
		int result = a + b;
		return result; // 호출한 곳으로 result를 반환
	}	
	int sum(int a, int b, int c) {		
		int result = a + b + c;
		return result; // 호출한 곳으로 result를 반환
	}
	
	// int...values : int 형 전달인자를 갯수와 관계 없이 배열로 받는 전달인자
	int sumAll(int...values) { // values는 배열
		int sum = 0;
		for (int value: values) {
			sum += value;
		}
		return sum;
	}
	

}









