package com.demoweb.dao;

import java.util.ArrayList;

import com.demoweb.dto.BoardAttachDto;
import com.demoweb.dto.BoardDto;

public interface BoardDao {

	void insertBoard(BoardDto board);

	ArrayList<BoardDto> selectBoardList();
	ArrayList<BoardDto> selectBoardListByPage(int start, int count);

	BoardDto selectBoardByBoardNo(int boardNo);
	
	void updateBoardReadCount(int boardNo);
	
	void deleteBoard(int boardNo);

	int selectBoardCount();

	void insertBoardAttach(BoardAttachDto a);

	ArrayList<BoardAttachDto> selectBoardAttachmentsByBoardNo(int boardNo);

	BoardAttachDto selectBoardAttachByAttachNo(int attachNo);

	void updateBoard(BoardDto board);

	void deleteBoardAttach(int attachNo);

	BoardDto selectBoardByBoardNo2(int boardNo);

}