
public class LottoApp {

	public static void main(String[] args) {
		
		// 로또 번호 추출기
		// 1. 숫자 여섯 개를 저장할 배열 만들기
		int[] numbers = new int[6];
		
		// 2. 당첨 예상 번호 뽑기
		while (true) {
			// 2-1. [1, 45] 범위의 (중복되지 않는) 6개의 난수 뽑기 -> 배열에 저장 : ( 반복문 사용 )
			for (int i = 0; i < 6; i++) {
				numbers[i] = (int)(Math.random() * 45) + 1; // [1 ~ 45]
				for (int j = 0; j < i; j++) { // 현재 뽑힌 위치 이전까지 반복
					if (numbers[i] == numbers[j]) { // 현재 뽑힌 숫자와 이전 숫자가 같다면(중복)
						i--; // for 문에 i가 증가하더라도 현재 i 위치를 다시 뽑도록 미리 1감소
						// i = -1; // for 문에 i가 증가하더라도 처음부터 다시 뽑도록 -1로 설정
					}
				}
			}
			
			// 2-2. 평균이 [20 ~ 26] 범위를 벗어나면 2-1부터 다시 (다시뽑기)
			int sum = 0;
			for (int i = 0; i < 6; i++) {
				sum += numbers[i];
			}
			int average = sum / 6;
			if (average >= 20 && average <= 26) { // 정상
				System.out.printf("[AVERAGE %2d]", average);
				break;
			}
		}
		
		// 3. 뽑힌 숫자 출력 : ( 반복문 사용 )
		for (int number : numbers) {
			System.out.printf("[%2d]", number);
		}
		
		// 4. 사용자가 원할 때까지 반복 : 2 ~ 4  while 반복
		//    사용자 선택 입력 --> 반복을 원하면 2부터 다시

	}

}
