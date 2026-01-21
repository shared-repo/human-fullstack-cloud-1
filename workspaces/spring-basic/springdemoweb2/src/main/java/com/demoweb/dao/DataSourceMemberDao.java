package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

import com.demoweb.dto.MemberDto;

@Component("dataSourceMemberDao")
public class DataSourceMemberDao implements MemberDao {
	
	private DataSource dataSource;
	public DataSourceMemberDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public void insertMember(MemberDto member) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		try {
			//1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = dataSource.getConnection();
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = 
				"insert into tbl_member (memberid, passwd, email) values (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMemberId()); // 첫 번째 ?에 적용할 데이터
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getEmail());
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			// pstmt.executeQuery(); // select
			pstmt.executeUpdate(); // insert, update, delete, create, alter, drop, ...
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리			

		} catch (Exception ex) {
			ex.printStackTrace(); // 오류 메시지를 화면에 출력
		} finally {
			// 6. 연결 종료
			JdbcUtils.closeStatement(pstmt);
			JdbcUtils.closeConnection(conn);
		}
		
	}

	// selectMemberByIdAndPasswd 메서드 만들기
	// 1. 전달인자 : String 타입의 memberId와 passwd
	// 2. 결과형   : MemberDto
	// 3. 구현내용 : tbl_member 테이블에서 memberId와 passwd로 데이터 조회 
	//             LottoDao의 selectLottoByRnd 메서드 참고
	@Override
	public MemberDto selectMemberByIdAndPasswd(String memberId, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto member = null; // 죄회 결과를 저장할 변수
		
		try {
			//1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = dataSource.getConnection();
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = "select * from tbl_member where memberid = ? and passwd = ?"; // ? : 여기에 데이터가 결합될 예정
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId); // 첫 번째 ?에 적용할 데이터
			pstmt.setString(2, passwd);
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리
			if (rs.next()) { // 다음 행으로 이동 ( 더 이상 데이터가 없으면 false 반환 )
				// 한 행을 읽어서 LottoDto 객체에 저장
				member = new MemberDto();
				member.setMemberId(rs.getString(1));
				member.setEmail(rs.getString(3));
				member.setUserType(rs.getString(4));
				member.setJoinDate(rs.getDate(5));
				member.setActive(rs.getBoolean(6));
				member.setUserGrade(rs.getInt(7));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 6. 연결 종료
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(pstmt);
			JdbcUtils.closeConnection(conn);
		}
		
		return member;
	}

	@Override
	public int selectMemberCountById(String memberId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0; // 죄회 결과를 저장할 변수
		
		try {
			//1. 드라이버 준비
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//2. 연결 객체 만들기
			conn = dataSource.getConnection();
			
			// 3. SQL 작성 + 명령 객체 만들기			
			String sql = "select count(*) from tbl_member where memberid = ?"; // ? : 여기에 데이터가 결합될 예정
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId); // 첫 번째 ?에 적용할 데이터
			
			// 4. 명령 실행 + 결과가 있다면(select 인 경우) 결과 수신 
			rs = pstmt.executeQuery();
			
			// 5. 결과가 있다면 (select인 경우) 결과 처리
			if (rs.next()) { // 다음 행으로 이동 ( 더 이상 데이터가 없으면 false 반환 )
				count = rs.getInt(1);
			}

		} catch (Exception ex) {
			
		} finally {
			// 6. 연결 종료
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(pstmt);
			JdbcUtils.closeConnection(conn);
		}
		
		return count;
	}
	
}
