package com.humanda6.demoweb2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.humanda6.demoweb2.entity.BoardAttachEntity;
import com.humanda6.demoweb2.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
	
	// 실제 테이블 이름을 사용하고 SQL 문법에 따라 작성
	@Query(	value = "SELECT * FROM tbl_board ORDER BY board_no DESC LIMIT :from, :count",
			nativeQuery = true)
	List<BoardEntity> findAllWithPage(@Param("from") int from, @Param("count") int count);
	
	// Entity 이름을 사용하고 JPQL 문법에 따라 작성
	@Query(value = "SELECT ba FROM BoardAttachEntity ba WHERE ba.attachNo = :attachNo")
	BoardAttachEntity findBoardAttachByAttachNo(@Param("attachNo") int attachNo);

}
