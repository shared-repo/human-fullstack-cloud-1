package com.humanda6.demoweb2.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.humanda6.demoweb2.dto.BoardAttachDto;
import com.humanda6.demoweb2.dto.BoardDto;
import com.humanda6.demoweb2.dto.MemberDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_board")
public class BoardEntity {
	
	public BoardEntity(BoardDto boardDto) {
		boardNo = boardDto.getBoardNo();
		title = boardDto.getTitle();
		content = boardDto.getContent();
		if (boardDto.getWriteDate() != null) {
			writeDate = boardDto.getWriteDate();
		}
		if (boardDto.getModifyDate() != null) {
			modifyDate = boardDto.getModifyDate();
		}
		readCount = boardDto.getReadCount();
		deleted = boardDto.isDeleted();
		
		writer = new MemberEntity();
		writer.setEmail(boardDto.getWriter().getEmail());
		
		List<BoardAttachDto> dtos = boardDto.getAttachments();
		if (dtos != null) {
			attachments = dtos.stream().map((attachment) -> new BoardAttachEntity(attachment)).toList();
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // mysql의 auto_increment
	private int boardNo;
	
	@Column(nullable = false)
	private String title;
		
	@Column(length = 1000, nullable = false)
	private String content;
		
	@Column
	private Date writeDate = new Date();

	@Column
	private Date modifyDate = new Date();
	
	@Column
	private int readCount = 0;
		
	@Column
	private boolean deleted = false;	
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "email")
	private MemberEntity writer;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "boardNo")
	private Collection<BoardAttachEntity> attachments;
	
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JoinColumn(name = "boardNo")
    private List<BoardCommentEntity> comments;
	
	public BoardDto toBoardDto() {
		BoardDto boardDto = new BoardDto();
		boardDto.setBoardNo(boardNo);
		boardDto.setTitle(title);
		boardDto.setContent(content);
		boardDto.setWriteDate(writeDate);
		boardDto.setModifyDate(modifyDate);
		boardDto.setReadCount(readCount);
		boardDto.setDeleted(deleted);
		
		boardDto.setWriter(writer.toMemberDto());
		
		if (attachments != null) {
			List<BoardAttachDto> dtos = attachments.stream().map((attachment) -> attachment.toBoardAttachDto()).toList();
			boardDto.setAttachments(dtos);
		}
		
		return boardDto;
	}

}