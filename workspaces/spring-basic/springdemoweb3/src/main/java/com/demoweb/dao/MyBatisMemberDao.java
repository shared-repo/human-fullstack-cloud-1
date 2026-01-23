package com.demoweb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Component;

import com.demoweb.dto.MemberDto;

@Component("mybatisMemberDao")
public class MyBatisMemberDao implements MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	private SqlSessionTemplate sessionTemplate;
	public MyBatisMemberDao(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
	
	final String MEMBER_MAPPER = "com.demoweb.mapper.MemberMapper";
	
	@Override
	public void insertMember(MemberDto member) {

		sessionTemplate.insert(MEMBER_MAPPER + ".insertMember", member);
		
	}
	
	@Override
	public MemberDto selectMemberByIdAndPasswd(String memberId, String passwd) {
		
		HashMap<String, String> params = new HashMap<>();
		params.put("memberId", memberId);
		params.put("passwd", passwd);
		MemberDto member = 
				sessionTemplate.selectOne(MEMBER_MAPPER + ".selectMemberByIdAndPasswd", params);

		return member;
	}

	@Override
	public int selectMemberCountById(String memberId) {
		
		int count = 
				sessionTemplate.selectOne(MEMBER_MAPPER + ".selectMemberCountById", memberId);
		
		return count;
	}
	
}
