
public class WhileStatement01 {

	public static void main(String[] args) {

		int limit = 10;
		int idx = 0;
		while (idx < limit) {
			System.out.printf("%d번째 실행\n", idx);
			idx = idx + 1;
		}
		
		idx = 0;
		while (true) { // 무한 반복
			System.out.printf("%d번째 실행\n", idx);
			idx = idx + 1;
			if (idx >= limit) {
				break; // 반복문을 종료하는 명령
			}
		}
	}

}
