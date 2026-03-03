package com.humanda6.demoweb2.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardCommentDto {
	
	private int commentNo; 	// PK
	private int boardNo;	// FK
	private String writer;	// FK
	private String content;
	private Date writeDate = new Date();
	private Date modifyDate = new Date();
	private boolean deleted = false;
	
	// 아래 세 개의 변수는 글의 논리적인 순서를 관리
	private int groupNo;	// 그룹 번호
	private int step;		// 그룹 내 순서 번호
	private int depth;		// 들여쓰기 정도

}
