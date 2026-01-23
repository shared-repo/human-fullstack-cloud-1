package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

import com.demoweb.dto.MemberDto;

@Component("jdbcTemplateMemberDao")
public class JdbcTemplateMemberDao implements MemberDao {
	
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	public JdbcTemplateMemberDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void insertMember(MemberDto member) {
						
		String sql = 
			"insert into tbl_member (memberid, passwd, email) values (?, ?, ?)";
		jdbcTemplate.update(sql, 
				member.getMemberId(), member.getPasswd(), member.getEmail());
		
	}

	
	@Override
	public MemberDto selectMemberByIdAndPasswd(String memberId, String passwd) {
			
		// 3. SQL 작성 + 명령 객체 만들기			
		String sql = "select * from tbl_member where memberid = ? and passwd = ?"; // ? : 여기에 데이터가 결합될 예정
		MemberDto member = jdbcTemplate.queryForObject(
				sql, 
				new RowMapper<MemberDto>() {
					@Override
					public MemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
						MemberDto member = new MemberDto();
						member.setMemberId(rs.getString(1));
						member.setEmail(rs.getString(3));
						member.setUserType(rs.getString(4));
						member.setJoinDate(rs.getDate(5));
						member.setActive(rs.getBoolean(6));
						member.setUserGrade(rs.getInt(7));
						return member;
					}
				}, 
				memberId, passwd);

		return member;
	}

	@Override
	public int selectMemberCountById(String memberId) {
		// 3. SQL 작성 + 명령 객체 만들기			
		String sql = "select count(*) from tbl_member where memberid = ?"; // ? : 여기에 데이터가 결합될 예정

		int count = jdbcTemplate.queryForObject(
						sql, 
						new RowMapper<Integer>() {
							@Override
							public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
								return rs.getInt(1);
							}
						}, 
						memberId);			
		
		return count;
	}
	
}
