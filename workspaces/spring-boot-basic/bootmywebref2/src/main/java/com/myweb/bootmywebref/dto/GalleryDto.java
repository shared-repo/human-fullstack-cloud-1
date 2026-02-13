package com.myweb.bootmywebref.dto;

import java.util.Date;

import lombok.Data;

@Data
public class GalleryDto {

	private int idx;
	private String title;
	private String description;
	private String userFileName;
	private String savedFileName;
	private Date regiDate;
	private boolean deleted;
	
}
