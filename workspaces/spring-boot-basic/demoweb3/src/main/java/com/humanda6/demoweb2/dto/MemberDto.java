package com.humanda6.demoweb2.dto;

import lombok.Data;

@Data // 모든 변수에 대해 getter, setter를 자동으로 생성
public class MemberDto {
	
	private String userName;
	private String email;
	private String passwd;

}
