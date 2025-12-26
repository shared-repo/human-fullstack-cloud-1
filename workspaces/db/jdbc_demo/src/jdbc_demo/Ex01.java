package jdbc_demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex01 {

	public static void main(String[] args) throws SQLException {
		
		//1. 드라이버 준비
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		
		//2. 연결 객체 만들기
		Connection conn = 
		DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila", // 서버와 DB 위치 지정 
									"human", "human"); // 아이디, 패스워드
		
		// 3. SQL 작성 + 명령 객체 만들기
		String sql = "select * from film limit 20"; 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
		ResultSet rs = pstmt.executeQuery();
		
		// 5. 결과가 있다면 (select인 경우) 결과 처리
		while (rs.next()) {
			System.out.printf("[%d][%s][%s]\n", 
					rs.getInt(1), rs.getString(2), rs.getString(11));
		}
		
		// 6. 연결 종료
		rs.close();
		pstmt.close();
		conn.close();
	}

}
