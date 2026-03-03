package com.humanda6.demoweb2.entity;

import com.humanda6.demoweb2.dto.MemberDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity // 이 클래스는 데이터베이스의 테이블과 연결됩니다.
@Table(name="tbl_member") // 연결할 데이터베이스 테이블 이름 지정
public class MemberEntity {
	
	// 생성자 (constructor) : python의 __init__(self) 함수와 같은 역할 수행 : 객체가 생성될 때 자동으로 호출
	public MemberEntity() {		
	}
	// DTO를 사용해서 Entity를 만드는 역할
	public MemberEntity(MemberDto memberDto) {
		email = memberDto.getEmail();
		passwd = memberDto.getPasswd();
		userName = memberDto.getUserName();
	}
	
	@Id // 아래 변수는 PK 컬럼으로 사용됩니다.
	private String email;
	
	@Column // 아래 변수는 일반 컬럼으로 사용됩니다.
	private String passwd;
	
	@Column // 아래 변수는 일반 컬럼으로 사용됩니다.
	private String userName;
	
	// Entity -> DTO 만드는 역할
	public MemberDto toMemberDto() {
		MemberDto member = new MemberDto();
		member.setEmail(email);
		member.setUserName(userName);
		member.setPasswd(passwd);
		
		return member;
	}

}









