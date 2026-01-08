package com.exampleweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.exampleweb.dto.LottoDto;

public class LottoDao {

	// 로또 번호 세트를 테이블에 저장하는 메서드
	public void insertLotto(LottoDto lotto) {
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
			String sql = "insert into lotto values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, lotto.getRnd()); // 첫 번째 ?에 적용할 데이터
			
			long tick = lotto.getGameDate().getTime(); // 날짜 -> Tick (1970.1.1 이후 경과한 1/1000초)			
			java.sql.Date gameDate = new java.sql.Date(tick); // java.util.Date -> java.sql.Date
			
			pstmt.setDate(2, gameDate); // 두 번째 ?에 적용할 데이터
			pstmt.setInt(3, lotto.getNumber1());
			pstmt.setInt(4, lotto.getNumber2());
			pstmt.setInt(5, lotto.getNumber3());
			pstmt.setInt(6, lotto.getNumber4());
			pstmt.setInt(7, lotto.getNumber5());
			pstmt.setInt(8, lotto.getNumber6());
			pstmt.setInt(9, lotto.getBonus());
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			// pstmt.executeQuery(); // select
			pstmt.executeUpdate(); // insert, update, delete, create, alter, drop, ...
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리			

		} catch (Exception ex) {
			ex.printStackTrace(); // 오류 메시지를 화면에 출력
		} finally {
			// 6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}
	
	// 테일블에 저장된 모든 로또 번호 세트를 삭제하는 메서드
	public void deleteAll() {
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
			String sql = "delete from lotto";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			// pstmt.executeQuery(); // select
			pstmt.executeUpdate(); // insert, update, delete, create, alter, drop, ...
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리			

		} catch (Exception ex) {
			ex.printStackTrace(); // 오류 메시지를 화면에 출력
		} finally {
			// 6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
	}
	
	// 회차번호로 당첨 결과를 조회하는 메서드
	public LottoDto selectLottoByRnd(int rnd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LottoDto lotto = null; // 죄회 결과를 저장할 변수
		
		try {
			//1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/labdb", // 서버와 DB 위치 지정 
										"human", "human"); // 아이디, 패스워드
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = "select * from lotto where rnd = ?"; // ? : 여기에 데이터가 결합될 예정
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rnd); // 첫 번째 ?에 적용할 데이터
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리
			if (rs.next()) { // 다음 행으로 이동 ( 더 이상 데이터가 없으면 false 반환 )
				// 한 행을 읽어서 LottoDto 객체에 저장
				lotto = new LottoDto();
				lotto.setRnd(rs.getInt(1));
				lotto.setGameDate(rs.getDate(2));
				lotto.setNumber1(rs.getInt(3));
				lotto.setNumber2(rs.getInt(4));
				lotto.setNumber3(rs.getInt(5));
				lotto.setNumber4(rs.getInt(6));
				lotto.setNumber5(rs.getInt(7));
				lotto.setNumber6(rs.getInt(8));
				lotto.setBonus(rs.getInt(9));
			}

		} catch (Exception ex) {
			
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return lotto;
	}
}
