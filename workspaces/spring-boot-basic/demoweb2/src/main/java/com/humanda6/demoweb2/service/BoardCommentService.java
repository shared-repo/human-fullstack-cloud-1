package com.humanda6.demoweb2.service;

import java.util.List;

import com.humanda6.demoweb2.dto.BoardCommentDto;

public interface BoardCommentService {
	
	void writeComment(BoardCommentDto commentDto);

	void updateGroupNo(int commentNo, int groupNo);

	List<BoardCommentDto> findBoardCommentByBoardNo(int boardNo);

	void writeReComment(BoardCommentDto commentDto);

	void deleteComment(int commentNo);

	void updateComment(BoardCommentDto comment);

}
