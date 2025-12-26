-- 1. 작업 데이터베이스 변경

USE labdb;

-- 2. 테이블 만들기
/*
이름 : tbl_board
컬럼 : 
     boardno 정수형, 필수, 기본키
	 writer 최대 20개의 가변 문자형, 필수
	 title 최대 100개의 가변 문자형, 필수
	 content 최대 2000개의 가변 문자형, 필수
	 writedate 년월일시분초 표시 자료형, 선택, 기본값 = 현재시간
	 modifydate 년월일시분초 표시 자료형, 선택, 기본값 = 현재시간
	 readcount 정수형, 선택, 기본값 = 0
     deleted 진위형, 선택, 기본값 = false
*/

-- 자동 증가 속성 지정 : AUTO_INCREMENT
-- 데이터를 새로 저장할 때 자동으로 마지막 번호 다음 번호로 뽑기

 
-- CREATE TABLE IF NOT EXISTS tbl_board
CREATE TABLE tbl_board
(
	boardno INT PRIMARY KEY AUTO_INCREMENT,
    writer VARCHAR(20) NOT NULL,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(2000) NOT NULL,
    writedate DATETIME NULL DEFAULT NOW(),
    modifydate DATETIME NULL DEFAULT CURRENT_TIMESTAMP(),
    readcount INT NULL DEFAULT (0),
    deleted BOOLEAN NULL DEFAULT (FALSE)
);

DROP TABLE tbl_board;










