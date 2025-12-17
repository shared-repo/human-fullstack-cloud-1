
public class Lotto {
	
	// 1. 기본 번호 뽑는 메서드 만들기 (LottoApp2 활용)
	int[] selectBasicNumbers() {
		
		int[] numbers = new int[6];
		for (int i = 0; i < 6; i++) {
			numbers[i] = (int) (Math.random() * 45) + 1; // [1 ~ 45]
			for (int j = 0; j < i; j++) { // 현재 뽑힌 위치 이전까지 반복
				if (numbers[i] == numbers[j]) { // 현재 뽑힌 숫자와 이전 숫자가 같다면(중복)
					i--; // for 문에 i가 증가하더라도 현재 i 위치를 다시 뽑도록 미리 1감소
					// i = -1; // for 문에 i가 증가하더라도 처음부터 다시 뽑도록 -1로 설정
				}
			}
		}
		return numbers;		
	}
	
	// 2. 6개 번호를 출력하는 메서드 만들기 (전달인자 int[], 결과형 : void)
	void printNumbers(int[] numbers) {
		for (int number : numbers) {
			System.out.printf("[%d]", number);
		}
	}
	
	// 3. 평균을 검사하는 함수 만들기
	boolean checkAverage(int[] numbers) {
		int sum = 0;
		for (int i = 0; i < 6; i++) {
			sum += numbers[i];
		}
		int average = sum / 6;
//		if (average >= 20 && average <= 26) { // 정상
//			return true;
//		} else {
//			return false;
//		}
		return (average >= 20 && average <= 26);
	}
	
	// 4. 위의 개별 기능 메서드를 사용해서 당첨 예상 번호를 뽑는 메서드
	int[] selectWinningNumbers() {
		int[] numbers = null;
		boolean valid = false;
		do {
			// 같은 클래스에 있는 다른 메서드는 직접 사용 가능
			numbers = selectBasicNumbers();
			valid = checkAverage(numbers);
		} while (!valid);
		
		return numbers;
	}
	

}



















