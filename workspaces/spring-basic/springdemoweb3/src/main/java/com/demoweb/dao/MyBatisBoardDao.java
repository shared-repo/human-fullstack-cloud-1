package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;
import com.demoweb.dto.MemberDto;

@Component("mybatisBoardDao")
public class MyBatisBoardDao implements BoardDao {
	
	private SqlSessionTemplate sessionTemplate;
	public MyBatisBoardDao(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	
	final String BOARD_MAPPER = "com.demoweb.mapper.BoardMapper";
	
	@Override
	public void insertBoard(BoardDto board) {
		// board.getBoardNo() --> 0
		sessionTemplate.insert(BOARD_MAPPER + ".insertBoard", board);
		// board.getBoardNo() --> 새로 생성된 글 번호
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
		
		HashMap<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("count", count);
		List<BoardDto> boards = 
				sessionTemplate.selectList(BOARD_MAPPER + ".selectBoardListByPage", params);

		return new ArrayList<BoardDto>(boards);
	}

	@Override
	public BoardDto selectBoardByBoardNo(int boardNo) {		
//		BoardDto board = 
//			sessionTemplate.selectOne(BOARD_MAPPER + ".selectBoardByBoardNo", 
//										  boardNo);
		BoardDto board = 
			sessionTemplate.selectOne(BOARD_MAPPER + ".selectBoardByBoardNoWithResultMap", 
									  boardNo);
		return board;		
	}

	@Override
	public void updateBoardReadCount(int boardNo) {				
		sessionTemplate.update(BOARD_MAPPER + ".updateBoardReadCount", boardNo);		
	}
	@Override
	public void deleteBoard(int boardNo) {
		sessionTemplate.update(BOARD_MAPPER + ".deleteBoard", boardNo);
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
		
		List<BoardAttachDto> attachments = 
			sessionTemplate.selectList(
				BOARD_MAPPER + ".selectBoardAttachmentsByBoardNo", boardNo);
			
		return new ArrayList<BoardAttachDto>(attachments);
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

	
	@Override
	public BoardDto selectBoardByBoardNo2(int boardNo) {
//		BoardDto board = sessionTemplate.selectOne(
//				BOARD_MAPPER + ".selectBoardByBoardNoWithJoin", boardNo);
		
		BoardDto board = sessionTemplate.selectOne(
				BOARD_MAPPER + ".selectBoardByBoardNoWithSelect", boardNo);		
		
		return board;
	}


}





