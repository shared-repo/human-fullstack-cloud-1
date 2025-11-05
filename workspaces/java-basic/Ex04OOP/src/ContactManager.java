
public class ContactManager {
	
	// 연락처 관리 프로그램
	// 1. 연락처 클래스 만들기
	//    - 클래스 이름 : Contact
	//    - 필드 : no(int), name(String), phone(String), email(String)
	//    - 메서드 : info, 각 필드의 값을 하나의 문자열로 만들어서 반환 ( no + " " + name .. )
	//    - 메서드 : getter / setter
	//    - 메서드 : 생성자
	
	// 2. 연락처 클래스 사용
	//    연락처 3개를 저장하는 배열 만들기
	//    배열의 각 요소에 연락처를 만들어서 저장
	//    배열에 저장된 모든 연락처 출력

	public static void main(String[] args) {
		
		// 연습 1
//		Contact contact = new Contact(1, "손석구", "010-9963-2547", "ssk@example.com");
//		String info = contact.info();
//		System.out.println(info);
		
		// 연습 2
		int[] ar = new int[3];
		String[] ar2 = new String[3];
		Contact[] contacts = new Contact[3];
		
		for (int i = 0; i < 3; i++) {
			contacts[i] = new Contact();
			contacts[i].setNo(i + 1);
			contacts[i].setName("Name " + (i+1));
			contacts[i].setEmail("Email " + (i+1));
			contacts[i].setPhone("Phone " + (i+1));
		}
		
		for (Contact contact : contacts) {
			System.out.println(contact.info());
		}
	}

}












