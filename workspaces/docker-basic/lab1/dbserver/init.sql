
-- 1. 사용자 계정 생성
CREATE USER human@localhost IDENTIFIED BY "human";
CREATE USER human@"%" IDENTIFIED BY "human";

-- 2. 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS labdb;

-- 3. 권한 부여
GRANT ALL PRIVILEGES ON labdb.* TO human@localhost;
GRANT ALL PRIVILEGES ON labdb.* TO human@"%";

-- 4. 작업 데이터베이스 변경
USE labdb;

-- 5. 테이블 생성 + 데이터 추가

DROP TABLE IF EXISTS `tbl_member`;

CREATE TABLE `tbl_member` (
  `memberid` varchar(20) NOT NULL,
  `passwd` varchar(200) NOT NULL,
  `email` varchar(50) NOT NULL,
  `usertype` varchar(10) DEFAULT (_utf8mb4'user'),
  `joindate` datetime DEFAULT (now()),
  `active` tinyint(1) DEFAULT (true),
  `usergrade` int DEFAULT NULL,
  PRIMARY KEY (`memberid`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tbl_board`;

CREATE TABLE `tbl_board` (
  `boardno` int NOT NULL AUTO_INCREMENT,
  `writer` varchar(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(10000) NOT NULL,
  `writedate` datetime DEFAULT (now()),
  `modifydate` datetime DEFAULT (now()),
  `readcount` int DEFAULT (0),
  `category` varchar(50) DEFAULT NULL,
  `deleted` tinyint DEFAULT '0',
  PRIMARY KEY (`boardno`),
  KEY `fk_member_board` (`writer`),
  CONSTRAINT `fk_member_board` FOREIGN KEY (`writer`) REFERENCES `tbl_member` (`memberid`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tbl_boardattach`;

CREATE TABLE `tbl_boardattach` (
  `attachno` int NOT NULL AUTO_INCREMENT,
  `boardno` int NOT NULL,
  `userfilename` varchar(100) NOT NULL,
  `savedfilename` varchar(100) NOT NULL,
  `downloadcount` int DEFAULT (0),
  PRIMARY KEY (`attachno`),
  KEY `fk_board_boardattach` (`boardno`),
  CONSTRAINT `fk_board_boardattach` FOREIGN KEY (`boardno`) REFERENCES `tbl_board` (`boardno`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tbl_comment`;
CREATE TABLE `tbl_comment` (
  `commentno` int NOT NULL AUTO_INCREMENT,
  `writer` varchar(20) NOT NULL,
  `boardno` int NOT NULL,
  `content` varchar(500) NOT NULL,
  `writedate` datetime DEFAULT (now()),
  `modifydate` datetime DEFAULT (now()),
  PRIMARY KEY (`commentno`),
  KEY `fk_board_comment` (`boardno`),
  KEY `fk_member_comment` (`writer`),
  CONSTRAINT `fk_board_comment` FOREIGN KEY (`boardno`) REFERENCES `tbl_board` (`boardno`),
  CONSTRAINT `fk_member_comment` FOREIGN KEY (`writer`) REFERENCES `tbl_member` (`memberid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tbl_gallery`;
CREATE TABLE `tbl_gallery` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `deleted` bit(1) DEFAULT NULL,
  `description` varchar(1000) NOT NULL,
  `regi_date` datetime(6) DEFAULT NULL,
  `saved_file_name` varchar(255) NOT NULL,
  `title` varchar(100) NOT NULL,
  `user_file_name` varchar(255) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `tbl_todo`;

CREATE TABLE `tbl_todo` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `completed` bit(1) DEFAULT NULL,
  `content` varchar(1000) NOT NULL,
  `title` varchar(200) NOT NULL,
  `write_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `newtodo`;
CREATE TABLE `newtodo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `writeDate` date DEFAULT (now()),
  `completed` tinyint(1) DEFAULT (false),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `newtodo2`;

CREATE TABLE `newtodo2` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `writedate` datetime DEFAULT (now()),
  `completed` tinyint(1) DEFAULT (false),
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `lotto`;
CREATE TABLE `lotto` (
  `rnd` int NOT NULL,
  `gameDate` date NOT NULL,
  `number1` smallint NOT NULL,
  `number2` smallint NOT NULL,
  `number3` smallint NOT NULL,
  `number4` smallint NOT NULL,
  `number5` smallint NOT NULL,
  `number6` smallint NOT NULL,
  `bonus` smallint NOT NULL,
  PRIMARY KEY (`rnd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

