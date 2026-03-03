package com.humanda6.demoweb2.entity;

import com.humanda6.demoweb2.dto.BoardAttachDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_boardattach")
public class BoardAttachEntity {

	public BoardAttachEntity(BoardAttachDto boardAttach) {
		attachNo = boardAttach.getAttachNo();
		userFileName = boardAttach.getUserFileName();
		savedFileName = boardAttach.getSavedFileName();
		downloadCount = boardAttach.getDownloadCount();
 	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int attachNo;
	
	// 아래 컬럼은 관계를 통해 자동 생성되도록 구현
	// @Column(nullable = false)
	// private int boardNo;
	
	@Column(nullable = false)
	private String userFileName;
	
	@Column(nullable = false)
	private String savedFileName;
	
	@Column
	private int downloadCount = 0;
	
	public BoardAttachDto toBoardAttachDto() {
		BoardAttachDto boardAttach = new BoardAttachDto();
		boardAttach.setAttachNo(attachNo);
		boardAttach.setUserFileName(userFileName);
		boardAttach.setSavedFileName(savedFileName);
		boardAttach.setDownloadCount(downloadCount);
		
		return boardAttach;
	}
	
}