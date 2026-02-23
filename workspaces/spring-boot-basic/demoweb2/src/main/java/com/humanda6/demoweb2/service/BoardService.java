package com.humanda6.demoweb2.service;

import java.util.List;

import com.humanda6.demoweb2.dto.BoardAttachDto;
import com.humanda6.demoweb2.dto.BoardDto;

public interface BoardService {

	int writeBoard(BoardDto boardDto);

	List<BoardDto> findAllBoard();

	int findBoardCount();

	List<BoardDto> findBoardByPage(int pageNo, int pageSize);

	BoardDto findBoardByBoardNo(int boardNo);

	void deleteBoardByBoardNo(int boardNo);

	boolean updateBoard(BoardDto board);
	
	BoardAttachDto findBoardAttachByAttachNo(int attachNo);

}