import java.util.ArrayList;
import java.util.Scanner;

public class ToDoApp {
	
	// 연락처 목록을 관리하는 변수
	private ArrayList<NewToDo> todos = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);
	
	// 기능 - 메서드
	// 1. 메뉴 보여주기 + 선택
	// 2. 등록
	// 3. 목록 보기
	// 4. 검색
	// 5. 삭제
	// 6. 수정
	
	public String selectTask() {
		System.out.println("************** 연락처 관리 ***************");
		System.out.print("* 1.등록 ");
		System.out.print("2.수정 ");
		System.out.print("3.삭제 ");
		System.out.print("4.목록 ");
		System.out.print("5.검색 ");
		System.out.println("0.종료 *");
		System.out.println("****************************************");
		System.out.print("작업을 선택하세요 : ");
		String selectedTask = scanner.nextLine();
		return selectedTask;
	}
	
	public void add() {
		System.out.print("할 일 : ");
		String title = scanner.nextLine();
		NewToDo todo = new NewToDo(title);
		todos.add(todo);
	}
	public void showList(ArrayList<NewToDo> list) {
		for (NewToDo todo : list) {
			System.out.println(todo);
		}
	}
	public ArrayList<NewToDo> search() {
		System.out.print("검색 키워드 : ");
		String searchWord = scanner.nextLine();
		ArrayList<NewToDo> result = new ArrayList<>();
		for (NewToDo todo : todos) {
			if (todo.getTitle().contains(searchWord)) { // 부분 일치 검색
				result.add(todo);
			}
		}
		return result;
	}
	public void delete() {
		// 검색
		ArrayList<NewToDo> result = search();
		// 목록표시
		showList(result);
		// 삭제선택
		System.out.print("삭제할 할 일 번호 : ");
		String id = scanner.nextLine();
		// 삭제
		for (int i = result.size() - 1; i >= 0; i--) { // 뒤에서 앞으로 진행하는 반복
			NewToDo todo = result.get(i);
			if (todo.getId() == Integer.parseInt(id)) {
				todos.remove(todo);
				break;
			}
		}
	}
	
	public void run() {
		main: while (true) {
			System.out.println();
			String task = selectTask();
			System.out.println();
			
			switch (task) {
			case "0": 
				break main; // main 이름이 붙은 반복문 또는 switch문 종료
			case "1": 
				add();
				break;
			case "4":
				if (todos.size() == 0) {
					System.out.println("등록된 할 일이 없습니다.");
				} else {
					showList(todos);
				}
				break;
			case "5":
				ArrayList<NewToDo> result = search();
				if (result.size() == 0) { // size: ArrayList에 포함된 요소 갯수 (배열의 length)
					System.out.println("검색 결과가 없습니다.");
				} else {
					showList(result);
				}
				break;
			default : 
				System.out.println("지원하지 않는 태스크입니다.");
			}
		}
		
		System.out.println("프로그램을 종료합니다.");
	}	

	public static void main(String[] args) {
		
		ToDoApp app = new ToDoApp();
		app.run();

	}

}
