package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.demoweb.dto.BoardDto;
import com.demoweb.dto.MemberDto;

@Component("boardDao")
public class MySqlBoardDao implements BoardDao {
	
	@Override
	public void insertBoard(BoardDto board) {
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
			String sql = 
				"insert into tbl_board (title, writer, content) values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle()); // 첫 번째 ?에 적용할 데이터
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
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

	@Override
	public ArrayList<BoardDto> selectBoardList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardDto> boards = new ArrayList<>();
		
		try {
			//1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/labdb", // 서버와 DB 위치 지정 
										"human", "human"); // 아이디, 패스워드
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = "select boardno, title, writer, writedate, modifydate, readcount " +
						 "from tbl_board " + 
						 "order by boardno desc ";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리
			while (rs.next()) { // 다음 행으로 이동 ( 더 이상 데이터가 없으면 false 반환 )
				// 한 행을 읽어서 BoardDto 객체에 저장
				BoardDto board = new BoardDto();
				board.setBoardNo(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setWriteDate(rs.getDate(4));
				board.setModifyDate(rs.getDate(5));
				board.setReadCount(rs.getInt(6));
				// 목록에 추가
				boards.add(board);
			}

		} catch (Exception ex) {
			
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return boards;
	}

	

}
