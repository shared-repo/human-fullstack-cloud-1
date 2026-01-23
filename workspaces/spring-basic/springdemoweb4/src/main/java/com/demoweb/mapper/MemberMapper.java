package com.demoweb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.demoweb.dto.MemberDto;

@Mapper // root-context.xml 파일의 <bean id="memberMapper" 를 대신하는 annotation
public interface MemberMapper {

	void insertMember(MemberDto member);
	MemberDto selectMemberByIdAndPasswd(
			@Param("memberId") String memberId, @Param("passwd") String passwd);
	int selectMemberCountById(String memberId);
	
}
