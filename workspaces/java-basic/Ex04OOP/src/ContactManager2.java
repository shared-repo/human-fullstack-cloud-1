import java.util.Scanner;

public class ContactManager2 {
	
	// 입력기 인스턴스 만들기
	private Scanner scanner = new Scanner(System.in);
	
	private Contact[] contacts = new Contact[1000]; // 연락처를 저장할 배열
	private int nextIdx = 0; // 다음 연락처가 저장될 위치

	public void run() {		

		outer: while (true) {
			String selection = selectTask();
			
			System.out.println();
			
			switch (selection) {
			case "1":
				printAllContacts();
				break;
			case "2":
				searchContact();				
				break;
			case "3":
				registerContact();
				break;
			case "4":
				break;
			case "5":
				deleteContact();
				break;
			case "9":
				System.out.println("프로그램을 종료합니다.");
				break outer; // outer 이름이 붙은 switch 또는 반복문 break
			default:
				System.out.println("지원하지 않는 명령입니다.");
			}
			
			System.out.println();
			
		}
	}

	private void deleteContact() {
		// 삭제할 연락처 이름 입력 -> 변수에 저장
		// 검색 결과 표시
		System.out.print("삭제할 연락처 이름 : ");
		String name = scanner.nextLine();
		System.out.println("*** 삭제 대상 연락처 목록 ***");
		boolean found = false; // 삭제 대상 발견 여부를 저장하는 변수
		for (int i = 0; i < nextIdx; i++) {
			if (contacts[i].getName().contains(name)) {
				String info = contacts[i].info();
				System.out.println(info);
				found = true; // 삭제 대상 발견 변수를 true로 변경
			}
		}
		if (found == false) {
			System.out.println("삭제 대상 검색 실패");
			return;
		}				
		// 삭제 대상 선택 ( 번호로 입력 )
		System.out.print("삭제 대상 연락처 번호 : ");
		int idxToDelete = scanner.nextInt();
		scanner.nextLine(); // 입력 버퍼 clear
		// 번호에 해당하는 연락처 삭제				
		// 삭제된 연락처 번호 이후 전체 연락처를 앞으로 한 칸 이동
		for (int i = idxToDelete; i < nextIdx;i++) {
			contacts[i-1] = contacts[i]; // 한 칸 앞으로 이동
			contacts[i-1].setNo(contacts[i-1].getNo() - 1); // 번호 1 감소
		}
		nextIdx--; // nextIdx = nextIdx - 1
	}

	private void searchContact() {
		// 검색할 연락처 이름 입력 -> 변수에 저장
		System.out.print("검색할 연락처 이름 : ");
		String name = scanner.nextLine();
		// 목록을 순회하면서 각 연락처의 이름과 일치 여부 확인 -> 일치 하면 연락처 출력
		System.out.println("*** 연락처 목록 ***");
		for (int i = 0; i < nextIdx; i++) {
			// if (name.equals(contacts[i].getName())) { // 완전 일치 검색
			// if (contacts[i].getName().indexOf(name) > -1) {
			if (contacts[i].getName().contains(name)) {
				String info = contacts[i].info();
				System.out.println(info);
			}
		}
	}

	private void printAllContacts() {
		System.out.println("*** 연락처 목록 ***");
		for (int i = 0; i < nextIdx; i++) {
			String info = contacts[i].info();
			System.out.println(info);
		}
	}

	private void registerContact() {
		// 이름, 전화번호, 이메일 입력
		System.out.println("*** 새 연락처 정보 입력 ***");
		System.out.print("이름 : ");
		String name = scanner.nextLine();
		System.out.print("전화번호 : ");
		String phone = scanner.nextLine();
		System.out.print("이메일 : ");
		String email = scanner.nextLine();
		// 입력받은 데이터를 사용해서 Contact 인스턴스 만들기
		Contact contact = new Contact(nextIdx + 1, name, phone, email);
		// 만든 인스턴스를 배열에 저장
		contacts[nextIdx] = contact;
		nextIdx++;
	}

	private String selectTask() {
		// 메뉴 표시
		System.out.println("***********************************");
		System.out.println("* 1. 연락처 목록 보기");
		System.out.println("* 2. 연락처 검색");
		System.out.println("* 3. 연락처 등록");
		System.out.println("* 4. 연락처 수정");
		System.out.println("* 5. 연락처 삭제");
		System.out.println("* 9. 종료");
		System.out.println("***********************************");
		System.out.print("작업을 선택하세요 : ");
		String selection = scanner.nextLine();
		return selection;
	}

	public static void main(String[] args) {

		ContactManager2 manager = new ContactManager2();
		manager.run();
		

	}

}
