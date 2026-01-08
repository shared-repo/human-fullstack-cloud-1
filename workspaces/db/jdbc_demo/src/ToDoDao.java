import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ToDoDao {
	
	public void insertToDo(NewToDo todo) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		try {
			//1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/labdb", // 서버와 DB 위치 지정 
										"human", "human"); // 아이디, 패스워드
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = "insert into newtodo (title, writeDate, completed) values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, todo.getTitle());
			
			long tick = todo.getWriteDate().getTime();			
			java.sql.Date writeDate = new java.sql.Date(tick);			
			pstmt.setDate(2, writeDate);
			
			pstmt.setBoolean(3, todo.isCompleted());
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			pstmt.executeUpdate();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리			

		} catch (Exception ex) {
			ex.printStackTrace(); // 오류 메시지를 화면에 출력
		} finally {
			// 6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}

	
	public ArrayList<NewToDo> selectAllToDo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<NewToDo> todos = new ArrayList<>();// 죄회 결과를 저장할 변수
		
		try {
			//1. 드라이버 준비
			// DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/labdb", // 서버와 DB 위치 지정 
										"human", "human"); // 아이디, 패스워드
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = "select * from newtodo";
			pstmt = conn.prepareStatement(sql);			
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리
			while (rs.next()) { // 다음 행으로 이동 ( 더 이상 데이터가 없으면 false 반환 )
				// 한 행을 읽어서 FilmDto 객체에 저장
				NewToDo todo = new NewToDo();
				todo.setId(rs.getInt(1));
				todo.setTitle(rs.getString(2));
				todo.setWriteDate(rs.getDate(3));
				todo.setCompleted(rs.getBoolean(4));
				// 한 행의 데이터를 저장한 NewToDo 객체를 목록에 추가
				todos.add(todo);
			}

		} catch (Exception ex) {
			
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return todos;
	}
	
	
	public ArrayList<NewToDo> selectToDoByTitle(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<NewToDo> todos = new ArrayList<>();// 죄회 결과를 저장할 변수
		
		try {
			//1. 드라이버 준비
			// DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/labdb", // 서버와 DB 위치 지정 
										"human", "human"); // 아이디, 패스워드
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = "select * from newtodo where title like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리
			while (rs.next()) { // 다음 행으로 이동 ( 더 이상 데이터가 없으면 false 반환 )
				// 한 행을 읽어서 FilmDto 객체에 저장
				NewToDo todo = new NewToDo();
				todo.setId(rs.getInt(1));
				todo.setTitle(rs.getString(2));
				todo.setWriteDate(rs.getDate(3));
				todo.setCompleted(rs.getBoolean(4));
				// 한 행의 데이터를 저장한 NewToDo 객체를 목록에 추가
				todos.add(todo);
			}

		} catch (Exception ex) {
			
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return todos;
	}
	
	public void deleteToDo(int id) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		try {
			//1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/labdb", // 서버와 DB 위치 지정 
										"human", "human"); // 아이디, 패스워드
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = "delete from newtodo where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			pstmt.executeUpdate();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리			

		} catch (Exception ex) {
			ex.printStackTrace(); // 오류 메시지를 화면에 출력
		} finally {
			// 6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}
}











