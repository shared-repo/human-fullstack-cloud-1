-- 1. 작업 데이터베이스 변경
USE labdb;

-- 2. 테이블 목록 조회
SHOW TABLES;

-- 3. tbl_board 테이블 정보 조회
DESCRIBE tbl_board;

-- 4. 데이터 삽입
--    auto_increment 컬럼은 생략 가능 ( 자동으로 값 생성 )
INSERT INTO tbl_board (boardno, writer, title, content)
VALUES (1, 'iamuserone', '첫 번째 게시글', '글쓰기 연습');

INSERT INTO tbl_board (boardno, writer, title, content)
VALUES (10, 'iamuserone', '두 번째 게시글', '글쓰기 연습 2');

INSERT INTO tbl_board (writer, title, content) 
VALUES ('iamuserone', '세 번째 게시글', '글쓰기 연습 3');

INSERT INTO tbl_board (writer, title, content)
VALUES ('iamuserone', '네 번째 게시글', '글쓰기 연습 4');




