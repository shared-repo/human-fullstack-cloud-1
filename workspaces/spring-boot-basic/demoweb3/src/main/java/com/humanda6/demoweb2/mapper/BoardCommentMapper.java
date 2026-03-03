package com.humanda6.demoweb2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.humanda6.demoweb2.dto.BoardCommentDto;

@Mapper
public interface BoardCommentMapper {

	// @Insert("sql") // ~Mapper.xml 파일의 내용을 대체
	@Insert("INSERT INTO tbl_boardcomment (board_no, email, content, write_date, modify_date, deleted, group_no, step, depth) " +
			"VALUES (#{ boardNo }, #{ writer }, #{ content }, #{ writeDate }, #{ modifyDate }, #{ deleted }, -1, 1, 0)")
	@Options(useGeneratedKeys = true, keyColumn = "comment_no", keyProperty = "commentNo")
	void insertBoardComment(BoardCommentDto commentDto);
	
	@Update("UPDATE tbl_boardcomment " +
			"SET group_No = #{ groupNo } " +
			"WHERE comment_No = #{ commentNo }")
	void updateGroupNo(@Param("commentNo")int commentNo, @Param("groupNo")int groupNo);
	
	@Select("SELECT comment_no, email writer, content, write_date, modify_date, deleted, group_no, step, depth " +
			"FROM tbl_boardcomment " +
			"WHERE board_no = #{ boardNo } " +
			"ORDER BY group_no DESC, step ASC")
	List<BoardCommentDto> selectCommentByBoardNo(int boardNo);
	
	@Update("UPDATE tbl_boardcomment " +
			"SET deleted = TRUE " +
			"WHERE comment_no = #{ commentNo }")
	void deleteComment(int commentNo);
	
	@Update("UPDATE tbl_boardcomment " +
			"SET content = #{ content } " +
			"WHERE comment_no = #{ commentNo }")
	void updateComment(BoardCommentDto comment);
	
	@Select("SELECT comment_no, board_no, email writer, content, write_date, modify_date, group_no, step, depth " + 
			"FROM tbl_boardcomment " +
			"WHERE comment_no = #{ commentNo }")
	BoardCommentDto selectCommentByCommentNo(int commentNo);

	@Update("UPDATE tbl_boardcomment " +
			"SET step = step + 1 " +
			"WHERE group_no = #{ groupNo } and step > #{ step }")
	void updateStepNo(@Param("groupNo") int groupNo, @Param("step") int step);
	
	@Insert("INSERT INTO tbl_boardcomment (board_no, email, content, write_date, modify_date, deleted, group_no, step, depth) " +
			"VALUES (#{ boardNo }, #{ writer }, #{ content }, #{ writeDate }, #{ modifyDate }, #{ deleted }, #{ groupNo }, #{ step }, #{ depth })")
	void insertReComment(BoardCommentDto commentDto);

}













