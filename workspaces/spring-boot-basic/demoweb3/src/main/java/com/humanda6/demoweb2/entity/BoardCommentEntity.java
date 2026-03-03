package com.humanda6.demoweb2.entity;

import java.util.Date;

import com.humanda6.demoweb2.dto.BoardCommentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbl_boardcomment")
public class BoardCommentEntity {
	
	public BoardCommentEntity(BoardCommentDto boardCommentDto) {
		commentNo = boardCommentDto.getCommentNo();
		
		content = boardCommentDto.getContent();
		if (boardCommentDto.getWriteDate() != null) {
			writeDate = boardCommentDto.getWriteDate();
		}
		if (boardCommentDto.getModifyDate() != null) {
			modifyDate = boardCommentDto.getModifyDate();
		}
		deleted = boardCommentDto.isDeleted();
		
		writer = new MemberEntity();
		writer.setEmail(boardCommentDto.getWriter());
	}
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentNo;

    @Column(nullable = false, length = 500)
    private String content;
    
    @Column
    private Date writeDate = new Date();
    @Column
    private Date modifyDate = new Date();
    @Column
    private boolean deleted = false;

    @Column
    private int groupNo;
    @Column(nullable = false)
    private int step;
    @Column(nullable = false)
    private int depth;
    
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "email")
	private MemberEntity writer;

    @ManyToOne
    @JoinColumn(name = "boardNo")
    private BoardEntity board;
    
    public BoardCommentDto toBoardCommentDto() {
		BoardCommentDto boardComment = new BoardCommentDto();
		boardComment.setCommentNo(commentNo);
		boardComment.setWriter(writer.getEmail());
		boardComment.setContent(content);
		boardComment.setWriteDate(writeDate);
		boardComment.setModifyDate(modifyDate);
		boardComment.setDeleted(deleted);
		
		boardComment.setGroupNo(groupNo);
		boardComment.setStep(step);
		boardComment.setDepth(depth);
		
		return boardComment;
	}
}