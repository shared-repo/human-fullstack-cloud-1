package com.myweb.bootmywebref.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ToDoDto {

	private int idx;
	private String title;
	private String content;
	private Date writeDate;
	private boolean completed;
	
}
