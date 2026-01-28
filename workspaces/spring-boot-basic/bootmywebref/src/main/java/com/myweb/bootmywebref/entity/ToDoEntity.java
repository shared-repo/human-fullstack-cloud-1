package com.myweb.bootmywebref.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity // database의 테이블과 연결된 클래스
@Table(name = "tbl_todo")
public class ToDoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // mysql의 auto_increment
	private int idx;
	
	@Column(length = 200, nullable = false)
	private String title;
	
	@Column(length = 1000, nullable = false)
	private String content;
	
	@Column
	private Date writeDate = new Date(); // 데이터베이스 기본 값 설정
	
	@Column
	private boolean completed = false;

}
