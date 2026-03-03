package com.humanda6.demoweb2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.humanda6.demoweb2.dto.MemberDto;

@Mapper
public interface MemberMapper {

	void insertMember(MemberDto member);
	MemberDto selectMemberByEmailAndPasswd(@Param("email") String email, @Param("passwd") String passwd);
	
}
