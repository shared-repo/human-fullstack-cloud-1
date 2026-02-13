package com.myweb.bootmywebref.entity;

import java.util.Date;

import com.myweb.bootmywebref.dto.GalleryDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="tbl_gallery")
public class GalleryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idx;
	@Column(nullable = false, length = 100)
	private String title;
	@Column(nullable = false, length = 1000)
	private String description;
	@Column(nullable = false)
	private String userFileName;
	@Column(nullable = false)
	private String savedFileName;
	@Column(nullable = false)
	private Date regiDate = new Date();
	@Column(nullable = false)
	private boolean deleted = false;
	
	public static GalleryEntity fromDto(GalleryDto dto) {
		GalleryEntity entity = new GalleryEntity();
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		entity.setSavedFileName(dto.getSavedFileName());
		entity.setUserFileName(dto.getUserFileName());
		
		return entity;
	}
	
	public GalleryDto toDto() {
		GalleryDto dto = new GalleryDto();
		dto.setIdx(idx);
		dto.setTitle(title);
		dto.setDescription(description);
		dto.setUserFileName(userFileName);
		dto.setSavedFileName(savedFileName);
		dto.setRegiDate(regiDate);
		dto.setDeleted(deleted);
		
		return dto;
	}
	
}

















