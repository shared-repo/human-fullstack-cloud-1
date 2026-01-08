

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ex01 {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//1. 드라이버 준비
			// DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sakila", // 서버와 DB 위치 지정 
										"human", "human"); // 아이디, 패스워드
			
			// 3. SQL 작성 + 명령 객체 만들기
			String sql = "select * from film limit 20"; 
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리
			while (rs.next()) { // 다음 행으로 이동 ( 더 이상 데이터가 없으면 false 반환 )
				System.out.printf("[%d][%s][%s]\n", 
						rs.getInt(1), rs.getString(2), rs.getString(11));
			}

		} catch (Exception ex) {
			
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}

}
