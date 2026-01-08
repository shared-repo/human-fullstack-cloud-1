

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FilmDao {
	
	// 전달인자는 BizLogic 클래스에서 넘어오는 데이터 표시
	// 결과형은 BizLogic 클래스로 반환하는 데이터 표시
	public ArrayList<FilmDto> selectFilmsByTitle(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FilmDto> films = new ArrayList<>();// 죄회 결과를 저장할 변수
		
		try {
			//1. 드라이버 준비
			// DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sakila", // 서버와 DB 위치 지정 
										"human", "human"); // 아이디, 패스워드
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = "select * from film where title like ? limit 20"; // ? : 여기에 데이터가 결합될 예정
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%"); // 첫 번째 ?에 적용할 데이터
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리
			while (rs.next()) { // 다음 행으로 이동 ( 더 이상 데이터가 없으면 false 반환 )
				// 한 행을 읽어서 FilmDto 객체에 저장
				FilmDto film = new FilmDto();
				film.setFilmId(rs.getInt(1));
				film.setTitle(rs.getString(2));
				film.setRating(rs.getString("rating"));
				// 한 행의 데이터를 저장한 FilmDto 객체를 목록에 추가
				films.add(film);
			}

		} catch (Exception ex) {
			
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return films;
	}

}
