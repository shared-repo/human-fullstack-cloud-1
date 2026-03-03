package com.humanda6.demoweb2.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.humanda6.demoweb2.dto.BoardAttachDto;
import com.humanda6.demoweb2.dto.BoardDto;
import com.humanda6.demoweb2.dto.MemberDto;
import com.humanda6.demoweb2.entity.BoardAttachEntity;
import com.humanda6.demoweb2.entity.BoardEntity;
import com.humanda6.demoweb2.entity.MemberEntity;
import com.humanda6.demoweb2.repository.BoardRepository;
import com.humanda6.demoweb2.repository.MemberRepository;

@Service(value="boardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	public BoardRepository boardRepository;
	
	@Autowired
	public MemberRepository memberRepository;
	
	@Override
	public int writeBoard(BoardDto boardDto) {
		
		BoardEntity entity = new BoardEntity(boardDto);
		MemberEntity memberEntity = memberRepository.findById(boardDto.getWriter().getEmail()).orElse(null);
		entity.setWriter(memberEntity);
		
		boardRepository.save(entity);
		
		return entity.getBoardNo();
		
	}
	
	@Override
	public List<BoardDto> findAllBoard() {
		
		List<BoardEntity> entities = boardRepository.findAll(Sort.by(Direction.DESC, "writeDate"));
		List<BoardDto> boards = entities.stream().map(board -> board.toBoardDto()).toList();
		
		return boards;
	}
	
	@Override
	public int findBoardCount() {
		
		return (int)boardRepository.count();
	}
	
	@Override
	public List<BoardDto> findBoardByPage(int pageNo, int pageSize) {
		
		int from = (pageNo - 1) * pageSize;
		int count = pageSize;
		
		List<BoardEntity> entities = boardRepository.findAllWithPage(from, count);
		List<BoardDto> boards = entities.stream().map(board -> board.toBoardDto()).toList();
		
		return boards;		
	}

//	@Override
//	public BoardDto findBoardByBoardNo(int boardNo) {
//		BoardEntity boardEntity = boardRepository.findById(boardNo).orElseGet(() -> null);
//		BoardDto boardDto = boardEntity != null ? boardEntity.toBoardDto() : null;
//		return boardDto;
//	}
	
	@Override
	public BoardDto findBoardByBoardNo(int boardNo) {
		BoardEntity boardEntity = boardRepository.findById(boardNo).orElseGet(() -> null); // BoardAttach 테이블 조회 병행 : FetchType.EAGER
		BoardDto boardDto = boardEntity != null ? boardEntity.toBoardDto() : null;
		
		Collection<BoardAttachEntity> attachmentEntities = boardEntity.getAttachments(); // BoardAttach 테이블 조회 : FetchType.LAZY
		if (attachmentEntities != null && attachmentEntities.size() > 0) {
			List<BoardAttachDto> attachments = 
					attachmentEntities.stream().map((attachmentEntity) -> attachmentEntity.toBoardAttachDto()).toList();
			boardDto.setAttachments(attachments);
		}
		
		return boardDto;
	}
	
	@Override
	public void deleteBoardByBoardNo(int boardNo) {
		
		boardRepository.deleteById(boardNo);
		
	}

	@Override
	public boolean updateBoard(BoardDto board) {
		// DB에 저장되어 있는 해당 게시글 데이터 조회 ( Entity 형식으로 )
		BoardEntity boardEntity = 
				boardRepository.findById(board.getBoardNo()).orElseGet(() -> null);
		if (boardEntity == null) {
			return false;
		}
		
		// Entity 수정
		boardEntity.setTitle(board.getTitle());
		boardEntity.setContent(board.getContent());
		
		// 수정 내용 저장 -> DB에 데이터 수정
		boardRepository.save(boardEntity);
		
		return true;
		
	}
	
	@Override
	public BoardAttachDto findBoardAttachByAttachNo(int attachNo) {
		
		BoardAttachEntity boardAttachEntity = boardRepository.findBoardAttachByAttachNo(attachNo);
		BoardAttachDto boardAttachDto = boardAttachEntity.toBoardAttachDto();
		
		return boardAttachDto;
	}

}
