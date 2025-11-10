package app;

import java.util.Scanner;

import pa.suba.TheClassTwo; // 이후에 나타나는 TheClassTwo는 pa.suba.TheClassTwo 입니다.

//public 클래스는 반드시 이름이 파일 이름과 일치해야 합니다.
public class TheMainOne {

	public static void main(String[] args) {
		
		// 1. 패키지에 포함된 클래스를 사용하려면 패키지 이름도 명시해야 합니다.
		// TheClassOne o = new TheClassOne();
		pa.TheClassOne o = new pa.TheClassOne();
		
		// 2. 패키지 이름은 import 구문을 통해 생략할 수 있습니다.
		// pa.suba.TheClassTwo o2 = new pa.suba.TheClassTwo();
		TheClassTwo o2 = new TheClassTwo();
		
		// java.util.Scanner s = new java.util.Scanner(System.in);
		Scanner s = new Scanner(System.in);
		
		// 3. public이 아닌 클래스 사용
		// pa.subb.TheClassThree o3; // 오류

	}

}

// 같은 파일에 여러 개의 클래스를 만들 수 있습니다. 
// 단, public 클래스는 한 개만 말들 수 있습니다.
class OtherClass {
	
}








