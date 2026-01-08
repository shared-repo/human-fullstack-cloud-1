import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoApp {
	
	private Scanner scanner = new Scanner(System.in);
	
	private ToDoDao dao = new ToDoDao();
	
	public String selectTask() {
		System.out.println("***************** 연락처 관리 ******************");
		System.out.print("* 1.등록 ");
		System.out.print("2.수정 ");
		System.out.print("3.삭제 ");
		System.out.print("4.목록 ");
		System.out.print("5.검색 ");
		System.out.print("6.저장 ");
		System.out.println("0.종료 *");
		System.out.println("**********************************************");
		System.out.print("작업을 선택하세요 : ");
		String selectedTask = scanner.nextLine();
		return selectedTask;
	}
	
	public void add() {
		System.out.print("할 일 : ");
		String title = scanner.nextLine();
		NewToDo todo = new NewToDo(title);
		dao.insertToDo(todo);
	}
	public void showList(ArrayList<NewToDo> list) {
		for (NewToDo todo : list) {
			System.out.println(todo);
		}
	}
	public ArrayList<NewToDo> search() {
		System.out.print("검색 키워드 : ");
		String searchWord = scanner.nextLine();
		ArrayList<NewToDo> result = dao.selectToDoByTitle(searchWord);
		return result;
	}
	public void delete() {
		// 검색
		ArrayList<NewToDo> result = search();
		// 목록표시
		showList(result);
		// 삭제선택
		System.out.print("삭제할 할 일 번호 : ");
		String sid = scanner.nextLine();
		int id = Integer.parseInt(sid);
		// 삭제
		dao.deleteToDo(id);
		
	}
	
	public void run() {
		
		// 여기에 파일에서 데이터를 읽는 코드 구현
		
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
			case "3":
				delete();
				break;
			case "4":
				ArrayList<NewToDo> todos = dao.selectAllToDo();
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
