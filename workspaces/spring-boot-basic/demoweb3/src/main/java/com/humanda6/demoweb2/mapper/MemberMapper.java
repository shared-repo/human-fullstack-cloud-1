package com.humanda6.demoweb2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.humanda6.demoweb2.dto.MemberDto;
import com.humanda6.demoweb2.dto.RoleDto;

@Mapper
public interface MemberMapper {

	void insertMember(MemberDto member);
	MemberDto selectMemberByEmailAndPasswd(@Param("email") String email, @Param("passwd") String passwd);
	MemberDto selectMemberByEmail(@Param("email") String email);
	
	List<RoleDto> selectAllRoles();
	List<RoleDto> selectRolesByEmail(@Param("email") String email);
	void insertMemberRole(@Param("email") String email, @Param("roleNo") int roleNo);
}
