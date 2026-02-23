package com.humanda6.demoweb2.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardDto {
	
	private int boardNo;
	private String title;
	private String content;
	private Date writeDate;
	private Date modifyDate;
	private int readCount;
	private boolean deleted;
	
	private MemberDto writer;
	
	// Board 테이블과 BoartAttachment 테이블 사이의 1:Many 관계를 구현한 필드 (변수)
	private List<BoardAttachDto> attachments;

}
