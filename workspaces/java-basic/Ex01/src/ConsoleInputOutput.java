
public class ConsoleInputOutput {

	public static void main(String[] args) {
		
		java.util.Scanner scanner = new java.util.Scanner(System.in); // 터미널 환경에서 사용자 입력을 받는 입력기
		
		// 문자열 : 0개 이상의 문자 집합 : "...." : String
		// 문자 : 1개의 문자 : 'ㅁ' : char
		
		System.out.print("이름을 입력하세요 : "); // print : 출력 이후에 엔터를 처리하지 않습니다.
		String input = scanner.nextLine(); // 사용자의 입력을 문자열 형식으로 받는 명령
		System.out.println(input + "님 반갑습니다"); // println : 출력 이후에 엔터를 처리합니다.
		
		System.out.printf("%s님 반갑습니다", input); // printf : 출력 이후에 엔터를 처리합니다.
		
	}

}
