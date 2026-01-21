package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.dto.MemberDto;

@Component("boardDao")
public class MySqlBoardDao implements BoardDao {
	
	@Override
	public void insertBoard(BoardDto board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
			pstmt.close();
					
			sql = "select last_insert_id()"; // 현재 세션에서 마지막으로 생성된 auto_increment 값
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 5. 결과가 있다면 (select인 경우) 결과 처리
			if (rs.next()) {
				int newBoardNo = rs.getInt(1);
				board.setBoardNo(newBoardNo);
			}

		} catch (Exception ex) {
			ex.printStackTrace(); // 오류 메시지를 화면에 출력
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
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
	
	@Override
	public ArrayList<BoardDto> selectBoardListByPage(int start, int count) {
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
			String sql = "select boardno, title, writer, writedate, modifydate, readcount, deleted " +
						 "from tbl_board " + 
						 "order by boardno desc " +						 
						 "limit ?, ? "; // 몇 번째부터 몇 개
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, count);
			
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
				board.setDeleted(rs.getBoolean(7));
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

	@Override
	public BoardDto selectBoardByBoardNo(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto board = null;
		
		try {
			//1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/labdb", // 서버와 DB 위치 지정 
										"human", "human"); // 아이디, 패스워드
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = "select boardno, title, writer, content, writedate, modifydate, readcount " +
						 "from tbl_board " + 
						 "where boardno = ? and deleted = false ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리
			if (rs.next()) { // 다음 행으로 이동 ( 더 이상 데이터가 없으면 false 반환 )
				// 한 행을 읽어서 BoardDto 객체에 저장
				board = new BoardDto();
				board.setBoardNo(rs.getInt(1));
				board.setTitle(rs.getString(2));
				board.setWriter(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setWriteDate(rs.getDate(5));
				board.setModifyDate(rs.getDate(6));
				board.setReadCount(rs.getInt(7));
			}

		} catch (Exception ex) {
			
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return board;
	}

	@Override
	public void updateBoardReadCount(int boardNo) {
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
				"update tbl_board set readcount = readcount + 1 where boardno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo); // 첫 번째 ?에 적용할 데이터
			
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
	public void deleteBoard(int boardNo) {
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
				// "delete from tbl_board where boardno = ?";
					"update tbl_board set deleted = true where boardno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo); // 첫 번째 ?에 적용할 데이터
			
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
	public int selectBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int dataCount = 0;
		
		try {
			//1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/labdb", // 서버와 DB 위치 지정 
										"human", "human"); // 아이디, 패스워드
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = "select count(*) from tbl_board ";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리
			if (rs.next()) { // 다음 행으로 이동 ( 더 이상 데이터가 없으면 false 반환 )
				dataCount = rs.getInt(1);
			}

		} catch (Exception ex) {
			
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return dataCount;
	}

	@Override
	public void insertBoardAttach(BoardAttachDto attach) {
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
				"insert into tbl_boardattach (boardno, userfilename, savedfilename) " + 
				"values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getBoardNo()); // 첫 번째 ?에 적용할 데이터
			pstmt.setString(2, attach.getUserFileName());
			pstmt.setString(3, attach.getSavedFileName());
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신
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
	public ArrayList<BoardAttachDto> selectBoardAttachmentsByBoardNo(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardAttachDto> attachments = new ArrayList<>();
		
		try {
			//1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/labdb", // 서버와 DB 위치 지정 
										"human", "human"); // 아이디, 패스워드
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = "select attachno, boardno, userfilename, savedfilename, downloadcount " +
						 "from tbl_boardattach " + 
						 "where boardno = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리
			while (rs.next()) { // 다음 행으로 이동 ( 더 이상 데이터가 없으면 false 반환 )
				BoardAttachDto attach = new BoardAttachDto();
				attach.setAttachNo(rs.getInt(1));
				attach.setBoardNo(rs.getInt(2));
				attach.setUserFileName(rs.getString(3));
				attach.setSavedFileName(rs.getString(4));
				attach.setDownloadCount(rs.getInt(5));
				attachments.add(attach);
			}

		} catch (Exception ex) {
			
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return attachments;
	}

	@Override
	public BoardAttachDto selectBoardAttachByAttachNo(int attachNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardAttachDto attach = null;
		
		try {
			//1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/labdb", // 서버와 DB 위치 지정 
										"human", "human"); // 아이디, 패스워드
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = "select attachno, boardno, userfilename, savedfilename, downloadcount " +
						 "from tbl_boardattach " + 
						 "where attachno = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attachNo);
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리
			while (rs.next()) { // 다음 행으로 이동 ( 더 이상 데이터가 없으면 false 반환 )
				attach = new BoardAttachDto();
				attach.setAttachNo(rs.getInt(1));
				attach.setBoardNo(rs.getInt(2));
				attach.setUserFileName(rs.getString(3));
				attach.setSavedFileName(rs.getString(4));
				attach.setDownloadCount(rs.getInt(5));
			}

		} catch (Exception ex) {
			
		} finally {
			// 6. 연결 종료
			try { rs.close(); } catch (Exception ex) {}
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
		return attach;
	}

	@Override
	public void updateBoard(BoardDto board) {
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
				"update tbl_board set title = ?, content = ? where boardno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle()); // 첫 번째 ?에 적용할 데이터
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getBoardNo());
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			// pstmt.executeQuery(); // select
			pstmt.executeUpdate(); // insert, update, delete, create, alter, drop, ...
			

		} catch (Exception ex) {
			ex.printStackTrace(); // 오류 메시지를 화면에 출력
		} finally {
			// 6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}
	
	@Override
	public void deleteBoardAttach(int attachNo) {
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
				"delete from tbl_boardattach where attachno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attachNo);
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			// pstmt.executeQuery(); // select
			pstmt.executeUpdate(); // insert, update, delete, create, alter, drop, ...
			

		} catch (Exception ex) {
			ex.printStackTrace(); // 오류 메시지를 화면에 출력
		} finally {
			// 6. 연결 종료
			try { pstmt.close(); } catch (Exception ex) {}
			try { conn.close(); } catch (Exception ex) {}
		}
		
	}


}
